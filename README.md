<h1 align="center">Trabalho final de Sistemas Distribuídos</h1>

- Universidade Tecnológica Federal do Paraná -  Campus Campo Mourão
- Departamento Acadêmico de Ciência da Computação
- Bacharelado em Ciência da Computação
- Alunos: Jean Carlos Martins Miguel e Matheus Vinicius da Costa
- Professor: Dr. Rodrigo Campiolo


<div align="center">
  <img src="https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white">
  
 <img src="https://img.shields.io/badge/Spring-6DB33F?style=for-the-badge&logo=spring&logoColor=white">
  
  <img src="https://img.shields.io/badge/Spring_Security-6DB33F?style=for-the-badge&logo=Spring-Security&logoColor=white">
</div>

[comment]: <> (<h4 align="center"> )


[comment]: <> (</h4>)


Spring Security
=================
<!--ts-->
   * [Introdução](#pre-requisitos)
   * [Descrição das tecnologias utilizadas na implementação](#executando-o-projeto)
   * [Arquitetura do sistema e Interface](#bibliotecas-utilizadas)
   * [Exemplos de Uso](#exemplos-de-uso)
   * [Dependências](#dependências)
   * [Executando a aplicação](#executar-aplicacao)  
<!--te-->

Introdução
==============
Neste projeto será implementado um sistema de validação de usuário e 
senha, ao ser autenticado (login e senha informado corretamente) o sistema irá retornar as informações do perfil resultante.

Dentro do sistema existe 3 funções distintas:
- Usuário (Hipoteticamente teria poucas permissões, como por exemplo: Read)
- Moderador (Hipoteticamente teria mais permissões que o usuário, como por exemplo: Read, Update)
- Admin (Hipoteticamente teria todas as permissões, como por exemplo, Read, Update, Delete)

O objetivo desse algoritmo é demonstrar a implementação do Spring Security com o JSON Web Token.



Descrição das tecnologias utilizadas na implementação
====================

Spring Security, Spring Boot , JWT , H2 Database.

- H2 - Banco de dados em memória que permite todas as operações do CRUD, permitindo assim que possamos testar nossa aplicação mesmo sem um banco de dados já definido.
- Spring Security - É uma biblioteca que fornece proteção, mas também autenticação, autorização e armazenamento de senhas. Sendo que, para autenticação, ele trabalha com vários protocolos. Para armazenar senhas em um banco de dados, ele tem opção de vários encoders
- Spring Boot - É um framework Java open source que tem como objetivo facilitar esse processo em aplicações Java. Consequentemente, ele traz mais agilidade para o processo de desenvolvimento, uma vez que devs conseguem reduzir o tempo gasto com as configurações iniciais, tais como : Gerenciamento de dependências,configurações de bibliotecas, entre outros.
- JWT - JWT (JSON Web Token) é um método RCT 7519 padrão da indústria para realizar autenticação entre duas partes por meio de um token assinado que autentica uma requisição web. Esse token é um código em Base64 que armazena objetos JSON com os dados que permitem a autenticação da requisição.




Arquitetura do sistema e Interface    
=====================
![springlast](https://user-images.githubusercontent.com/31520652/176277024-bd363556-3f8f-4d45-8dd8-9fa4bc7c876b.png)
![WhatsApp Image 2022-06-23 at 9 30 35 AM](https://user-images.githubusercontent.com/31520652/175569002-7378afb8-44cf-45f1-9a4e-0a2675e251db.jpeg)
![WhatsApp Image 2022-06-23 at 9 30 35 AM (2)](https://user-images.githubusercontent.com/31520652/175569068-95c35994-7a77-4de5-8372-b1bd76842859.jpeg)
![WhatsApp Image 2022-06-23 at 9 30 35 AM (1)](https://user-images.githubusercontent.com/31520652/175569093-058cddd5-4c28-498b-8221-9ec9b51787a7.jpeg)



Funcionamento das Rotas do Sistema   
=====================
POST

    • Criar uma nova conta no banco de dados

    • URL: /api/auth/signup

    • JSON BODY:

```json
 { 
    "username": "exemplo",  
    "email": "exemplo@SistemasDistribuidos.com.br",   
    "password": "12345678", 
    "role": ["mod", "user"]
 }
```

    • Autenticar login e iniciar sessao de um conta 
	
    • URL: /api/auth/signin
	
    • JSON BODY:


```json
{  
   "username": "exemplo", 
   "password": "12345678"
}
```
    • Finalizar a sessão (limpar os cookies)

    • URL: /api/auth/signout

    • JSON BODY: ~não tem body








GET 

     Retorna todo o conteúdo disponível (public content) 

    • URL : /api/test/all

    • Acessar conteúdo do usuário (User) (Autenticação Necessária)

    • URL : /api/test/user

    • Conteúdo disponível para o Moderador (Autenticação Necessária)

    • URL : /api/test/mod

    • Conteúdo disponível para o Admin (Autenticação Necessária)

    • URL : /api/test/admin



Dependências
=====================
- maven
- java 11


Executando a aplicação
=====================

Na raiz do projeto execute o seguinte comando:
- mvn spring-boot:run





