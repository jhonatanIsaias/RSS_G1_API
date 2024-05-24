package br.edu.ifs.rss_g1.notices_g1.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long category_id;

    @NotNull(message = "name cannot be null")
    private String name;

    @NotNull(message = "name cannot be null")
    private String link;

    @ManyToMany(mappedBy = "categories")
    @JsonIgnore
    private Set<User> users = new HashSet<>();
}
