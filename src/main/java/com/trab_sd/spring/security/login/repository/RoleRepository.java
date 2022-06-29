package com.trab_sd.spring.security.login.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.trab_sd.spring.security.login.models.ERole;
import com.trab_sd.spring.security.login.models.Role;
//responsavel por listar as funções de cada controladora
@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
  Optional<Role> findByName(ERole name);
}
