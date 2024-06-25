package br.edu.ifs.rss_g1.notices_g1.entity;

import br.edu.ifs.rss_g1.notices_g1.enums.RoleEnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


import java.util.*;

@Entity
@Data
public class User implements  UserDetails {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")

    private UUID user_id;


    private String name;


    private String fone;


    private String login;

    @Getter
    private String email;

    private int role;

    @Getter
    private String password;


    @Temporal(TemporalType.DATE)
    private Date birth_date;

    @NotNull(message = "Status cannot be null")
    private Boolean status;

    @Temporal(TemporalType.DATE)
    private Date created_at;
    @ManyToMany
    @JoinTable(
            name = "user_category",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    @Setter
    private Set<Category> categories = new HashSet<>();


    public RoleEnum getRole() {
        return RoleEnum.valueOf(role);
    }

    public void setRole(RoleEnum role) {
        if (role != null) {
            this.role = role.getCode();
        }
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(this.getRole() == RoleEnum.ADMIN) return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_USER"));
        else return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getUsername() {
        return getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
