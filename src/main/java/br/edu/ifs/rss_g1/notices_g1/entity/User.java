package br.edu.ifs.rss_g1.notices_g1.entity;

import br.edu.ifs.rss_g1.notices_g1.enums.RoleEnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


import java.util.*;

@Entity
@Data
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;


    private String name;


    private String phone;


    private String login;


    private String email;

    private int role;


    private String password;


    @Temporal(TemporalType.DATE)
    private Date birth_date;


    private Boolean status;

    @Temporal(TemporalType.DATE)
    private Date created_at;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_category",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    @Setter
    private List<Category> categories = new ArrayList<>();


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
        return getLogin();
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
