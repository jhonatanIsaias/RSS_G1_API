package br.edu.ifs.rss_g1.notices_g1.service;

import br.edu.ifs.rss_g1.notices_g1.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Serviço de autorização responsável por carregar os detalhes do usuário com base no nome de usuário.
 */
@Service
public class AuthorizationService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    /**
     * Carrega os detalhes do usuário com base no nome de usuário.
     *
     * @param username O nome de usuário utilizado para buscar os detalhes do usuário.
     * @return Um objeto UserDetails contendo as informações do usuário.
     * @throws UsernameNotFoundException Caso o usuário não seja encontrado.
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByLogin(username);
    }
}
