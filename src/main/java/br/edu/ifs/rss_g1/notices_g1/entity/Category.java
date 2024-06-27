package br.edu.ifs.rss_g1.notices_g1.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Long categoryId;

    @NotNull(message = "name cannot be null")
    private String name;

    @NotNull(message = "name cannot be null")
    private String link;

    @ManyToMany
    @JsonIgnore
    private List<User> users;
}
