package br.edu.ifs.rss_g1.notices_g1.controller;

import br.edu.ifs.rss_g1.notices_g1.dto.AutheticationDTO;
import br.edu.ifs.rss_g1.notices_g1.entity.User;
import br.edu.ifs.rss_g1.notices_g1.service.TokenService;
import br.edu.ifs.rss_g1.notices_g1.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="auth")
@RequiredArgsConstructor
public class AuthenticationController {

    @Autowired
    AuthenticationManager authenticationManager;

     @Autowired
     UserService userService;

     @Autowired
     TokenService tokenService;


    @PostMapping("/login")
    public ResponseEntity authentication(@RequestBody @Valid AutheticationDTO data) {

            var userNamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.password());
            var auth = this.authenticationManager.authenticate(userNamePassword);
            var token = tokenService.generateToken((User) auth.getPrincipal());
            return ResponseEntity.ok(token);

    }
}