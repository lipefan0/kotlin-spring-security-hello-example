package br.com.contis.kotlin_spring_security_hello_example.controller

import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class HelloController {

    @GetMapping("/hello")
    fun sayHello(@AuthenticationPrincipal user: UserDetails): String {
        // @AuthenticationPrincipal injeta os detalhes do usuário autenticado
        return "Hello, ${user.username}! Seu token é válido."
    }

    @GetMapping("/admin/hello")
    @PreAuthorize("hasRole('ADMIN')")
    fun sayHelloToAdmin(@AuthenticationPrincipal user: UserDetails): String {
        return "Olá, administrador ${user.username}! Você tem acesso especial."
    }
}