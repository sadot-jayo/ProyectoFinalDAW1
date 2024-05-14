package pe.edu.cibertec.ventacelulares.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.edu.cibertec.ventacelulares.entity.Usuario;
import pe.edu.cibertec.ventacelulares.repository.UsuarioRepository;
import pe.edu.cibertec.ventacelulares.response.LoginResponse;
import pe.edu.cibertec.ventacelulares.security.JWTAuthenticationConfig;

@RestController
@RequestMapping("/usuario")
public class UsuarioService {
    @Autowired
    private UsuarioRepository repository;

    @Autowired
    JWTAuthenticationConfig jwtAuthenticationConfig;

    @PostMapping("/login")
    public LoginResponse Login(@RequestBody() Usuario user) {
        Usuario userResult = repository.findByUsuario(user.getUsuario());
        if (userResult == null) {
            return new LoginResponse("99", "user not found", null);
        }
        if (new BCryptPasswordEncoder().matches(user.getContrasenia(), userResult.getContrasenia())) {
            return new LoginResponse("99", "Wrong password", null);
        }
        String token = jwtAuthenticationConfig.getJWTToken(user.getUsuario());
        return new LoginResponse("01", null, token);
    }

}
