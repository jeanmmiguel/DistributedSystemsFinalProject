package com.trab_sd.spring.security.login.controllers;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.trab_sd.spring.security.login.models.ERole;
import com.trab_sd.spring.security.login.models.Role;
import com.trab_sd.spring.security.login.models.User;
import com.trab_sd.spring.security.login.payload.request.LoginRequest;
import com.trab_sd.spring.security.login.payload.request.SignupRequest;
import com.trab_sd.spring.security.login.payload.response.MessageResponse;
import com.trab_sd.spring.security.login.payload.response.UserInfoResponse;
import com.trab_sd.spring.security.login.repository.RoleRepository;
import com.trab_sd.spring.security.login.repository.UserRepository;
import com.trab_sd.spring.security.login.security.jwt.JwtUtils;
import com.trab_sd.spring.security.login.security.services.UserDetailsImpl;



// código responsável pela autenticação do usuário 

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth") //rota de autenticação
public class AuthController { //classe responsável pela autenticação
  @Autowired
  RoleRepository roleRepository;

  @Autowired
  PasswordEncoder encoder;

  @Autowired
  AuthenticationManager authenticationManager;

  @Autowired
  UserRepository userRepository;



  @Autowired
  JwtUtils jwtUtils;

  @PostMapping("/signin")
  public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

    //realiza a autenticação apartir do nome e senha do request
    Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

    SecurityContextHolder.getContext().setAuthentication(authentication); //define autenticação

    //userDetails pega info do user que foi autenticado
    UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

    //gera o cookie da sessão
    ResponseCookie jwtCookie = jwtUtils.generateJwtCookie(userDetails);

    List<String> roles = userDetails.getAuthorities().stream()
        .map(item -> item.getAuthority())
        .collect(Collectors.toList());
  
    //retorna o body com as informaçoes    
    return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, jwtCookie.toString()) //define o cookie da sessão para o usuario
        .body(new UserInfoResponse(userDetails.getId(),
                                   userDetails.getUsername(),
                                   userDetails.getEmail(),
                                   roles));
  }

  @PostMapping("/signup") // rota que responde sobre o cadastro do usuário
  public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
    if (userRepository.existsByUsername(signUpRequest.getUsername())) { //verifica se o usuário já está cadastrado baseado no nome do usuário ex: jeanmmiguel
      return ResponseEntity.badRequest().body(new MessageResponse("Erro Usuário já existe!!!, cadastre outro usuário"));
    }

    if (userRepository.existsByEmail(signUpRequest.getEmail())) { //verifica se o email já está cadastrado baseado no email ex: jeanm@teste.com
      return ResponseEntity.badRequest().body(new MessageResponse("Erro Email já está sendo utilizado por outro usuário!!!"));
    }

    // Criar novo usuário
    User user = new User(signUpRequest.getUsername(), //recebe um usuário
                         signUpRequest.getEmail(), // recebe um email
                         encoder.encode(signUpRequest.getPassword())); //recebe a senha e criptografa pra não ficar em texto claro no banco

    Set<String> strRoles = signUpRequest.getRole(); // variavel que guarda qual a função do usuário no sistema ex: user,admin,moderador
    Set<Role> roles = new HashSet<>();

    if (strRoles == null) { // função tem que ser ou USER, ou ADMIN ou MODERATOR, senão especificar a função, é criado como USER
      Role userRole = roleRepository.findByName(ERole.ROLE_USER)
          .orElseThrow(() -> new RuntimeException("Error: Função não encontrado!!!"));
      roles.add(userRole);
    } else {
      strRoles.forEach(role -> {
        switch (role) {
        case "admin":
          Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
              .orElseThrow(() -> new RuntimeException("Erro Função não encontrado!!!"));
          roles.add(adminRole);

          break;
        case "mod":
          Role modRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
              .orElseThrow(() -> new RuntimeException("Erro Função não encontrado!!!"));
          roles.add(modRole);

          break;
        default:
          Role userRole = roleRepository.findByName(ERole.ROLE_USER)
              .orElseThrow(() -> new RuntimeException("Erro Função não encontrado!!!"));
          roles.add(userRole);
        }
      });
    }

    user.setRoles(roles);
    userRepository.save(user);

    return ResponseEntity.ok(new MessageResponse("Usuário registrado com sucesso!!"));
  }

  @PostMapping("/signout") //rota que faz logoff da conta
  public ResponseEntity<?> logoutUser() {
    ResponseCookie cookie = jwtUtils.getCleanJwtCookie(); // limpa  cookie da sessão pra nao ficar salvo DISCONNECT
    return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, cookie.toString()) // retorna mensagem para o usuario
        .body(new MessageResponse("Deslogado da sua conta"));
  }
}
