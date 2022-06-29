package com.trab_sd.spring.security.login.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600) //permite restringir os recursos implementados em navegadores web
@RestController
@RequestMapping("/api/test")
public class TestController {
  @GetMapping("/all") // ROTA PUBLICA
  public String allAccess() {
    return "Todos podem ver aqui";//MENSAGEM DE TEST
  }


  @GetMapping("/user") //ROTA QUE CUIDA DO USUÁRIO OU MODERADOR OU ADMIN JÁ QUE ADMIN E MODERADOR TEM PRIVILEGIOS MAIORES QUE USUARIO 
  @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')") //Verifica a função do usuário autenticado pra ver se tem permissão de acesso
  // o metodo ñ é executado e um http não autorizado é retornado
  public String userAccess() {
    return "Conteúdo do USUÁRIO";
  }

  @GetMapping("/mod")//ROTA
  @PreAuthorize("hasRole('MODERATOR')") //Verifica a função do usuário autenticado pra ver se tem permissão de acesso
  // o metodo ñ é executado e um http não autorizado é retornado
  public String moderatorAccess() {
    return "Conteúdo do moderador, você tem privilégios de MODERADOR do sistema";//MENSAGEM DE TEST
  }

  @GetMapping("/admin")//ROTA
  @PreAuthorize("hasRole('ADMIN')") // PreAuthorize para determinar se a solicitação deve ser autenticada, caso contrario
  // o metodo ñ é executado e um http não autorizado é retornado
  public String adminAccess() {
    return "Conteúdo do administrador, você tem privilégios de ADMINISTRADOR do sistema";//MENSAGEM DE TEST
  }
}


