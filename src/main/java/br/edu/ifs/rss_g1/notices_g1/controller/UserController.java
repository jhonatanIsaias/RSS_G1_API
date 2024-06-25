package br.edu.ifs.rss_g1.notices_g1.controller;

import br.edu.ifs.rss_g1.notices_g1.dto.UserDTO;
import br.edu.ifs.rss_g1.notices_g1.dto.UserResponseDTO;
import br.edu.ifs.rss_g1.notices_g1.entity.User;
import br.edu.ifs.rss_g1.notices_g1.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    @PostMapping
    public ResponseEntity<UserResponseDTO> createUser(@Valid @RequestBody UserDTO user) {
         User userCreate =  userService.saveUser(user);
         UserResponseDTO userResponseDTO = new UserResponseDTO(
                 userCreate.getName(),
                 userCreate.getFone(),
                 userCreate.getLogin(),
                 userCreate.getEmail(),
                 userCreate.getRole(),
                 userCreate.getBirth_date(),
                 userCreate.getStatus(),
                 userCreate.getCreated_at(),
                 userCreate.getCategories());
         return ResponseEntity.ok().body(userResponseDTO);
    }

}
