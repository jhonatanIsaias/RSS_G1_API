package br.edu.ifs.rss_g1.notices_g1.repository;

import br.edu.ifs.rss_g1.notices_g1.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    User findByEmail(String email);
    UserDetails findByLogin(String login);
}
