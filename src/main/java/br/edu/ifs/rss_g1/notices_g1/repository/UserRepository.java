package br.edu.ifs.rss_g1.notices_g1.repository;

import br.edu.ifs.rss_g1.notices_g1.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
    UserDetails findByLogin(String login);



}
