<h1 align="center">Trabalho final de Sistemas Distribuídos</h1>
<p href="#descricao" align="center">Spring Security</p>

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
  
<!--te-->

Introdução
==============
Neste projeto será implementado um sistema de validação de usuário e 
senha, ao ser autenticado (login e senha informado corretamente) o sistema irá retornar as informações do perfil resultante.

Dentro do sistema existe 3 funções distintas:
- Usuário (Permissões: Read)
- Moderador (Permissões: Read, Update)
- Admin (Permissões: Read, Update, Delete)

O objetivo desse algoritmo é demonstrar a implementação do Spring Security com o JSON Web Token.



Descrição das tecnologias utilizadas na implementação
====================

Spring Security, Spring Boot e JWT 



Arquitetura do sistema e Interface    
=====================

![WhatsApp Image 2022-06-23 at 7 56 32 PM](https://user-images.githubusercontent.com/31520652/175568755-366deab3-cd05-4e8a-8709-3c112db366ea.jpeg)
![WhatsApp Image 2022-06-23 at 9 30 35 AM](https://user-images.githubusercontent.com/31520652/175569002-7378afb8-44cf-45f1-9a4e-0a2675e251db.jpeg)
![WhatsApp Image 2022-06-23 at 9 30 35 AM (2)](https://user-images.githubusercontent.com/31520652/175569068-95c35994-7a77-4de5-8372-b1bd76842859.jpeg)
![WhatsApp Image 2022-06-23 at 9 30 35 AM (1)](https://user-images.githubusercontent.com/31520652/175569093-058cddd5-4c28-498b-8221-9ec9b51787a7.jpeg)



Funcionamento das Rotas do Sistema   
=====================
POST

    • Criar uma nova conta no banco de dados

    • URL: /api/auth/signup

    • JSON BODY:

 {
    "username": "exemplo", 
    
    "email": "exemplo@SistemasDistribuidos.com.br",
    
    "password": "12345678",
    
    "role": ["mod", "user"]
 }


    • Autenticar login e iniciar sessao de um conta 
	
    • URL: /api/auth/signin
	
    • JSON BODY:

{
    "username": "exemplo",
   
   "password": "12345678"
}

    • Finalizar a sessão (limpar os cookies)

    • URL: /api/auth/signout

    • JSON BODY: ~não tem body








GET 
    • Retorna todo o conteúdo disponível (public content) 

    • URL : /api/test/all

    • Acessar conteúdo do usuário (User) (Autenticação Necessária)

    • URL : /api/test/user

    • Conteúdo disponível para o Moderador (Autenticação Necessária)

    • URL : /api/test/mod

    • Conteúdo disponível para o Admin (Autenticação Necessária)

    • URL : /api/test/admin










