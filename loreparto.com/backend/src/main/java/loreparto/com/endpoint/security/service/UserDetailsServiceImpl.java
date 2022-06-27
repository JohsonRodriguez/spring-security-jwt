package loreparto.com.endpoint.security.service;

import loreparto.com.endpoint.security.entity.Usuario;
import loreparto.com.endpoint.security.entity.UsuarioPrincipal;
import loreparto.com.endpoint.security.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceEmpl implements UserDetailsService {

    @Autowired
    UsuarioService usuarioService;
    @Override
    public UserDetails loadUserByUsername(String nombre) throws UsernameNotFoundException {
        Usuario usuario=usuarioService.getByNombre(nombre).get();
        return UsuarioPrincipal.build(usuario);

    }
}
