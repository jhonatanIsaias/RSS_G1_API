package br.edu.ifs.rss_g1.notices_g1.dto;

import br.edu.ifs.rss_g1.notices_g1.entity.Category;
import br.edu.ifs.rss_g1.notices_g1.enums.RoleEnum;

import java.util.Date;
import java.util.Set;

public record UserResponseDTO(

        String name,
        String fone,
        String login,
        String email,
        RoleEnum role,
        Date birth_date,
        Boolean status,
        Date created_at,
        Set<Category> categories
        )
         {
}