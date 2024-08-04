package br.edu.ifs.rss_g1.notices_g1.security;

import br.edu.ifs.rss_g1.notices_g1.repository.UserRepository;
import br.edu.ifs.rss_g1.notices_g1.service.TokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * Filtro de segurança que intercepta todas as requisições para validar o token JWT e autenticar o usuário.
 */
@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserRepository userRepository;

    /**
     * Filtra e valida o token JWT presente nas requisições HTTP.
     *
     * @param request     Objeto HttpServletRequest contendo a requisição HTTP.
     * @param response    Objeto HttpServletResponse contendo a resposta HTTP.
     * @param filterChain Objeto FilterChain para encadear o filtro.
     * @throws ServletException Caso ocorra um erro de servlet.
     * @throws IOException      Caso ocorra um erro de IO.
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var token = this.recoverToken(request);

        if (token != null) {
            var subject = tokenService.validateToken(token);
            UserDetails user = userRepository.findByLogin(subject);

            var authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());

            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        filterChain.doFilter(request, response);
    }

    /**
     * Recupera o token JWT do cabeçalho Authorization da requisição HTTP.
     *
     * @param request Objeto HttpServletRequest contendo a requisição HTTP.
     * @return O token JWT presente no cabeçalho Authorization ou null se não estiver presente.
     */
    private String recoverToken(HttpServletRequest request) {
        var authHeader = request.getHeader("Authorization");
        if (authHeader == null) return null;
        return authHeader.replace("Bearer ", "");
    }
}
