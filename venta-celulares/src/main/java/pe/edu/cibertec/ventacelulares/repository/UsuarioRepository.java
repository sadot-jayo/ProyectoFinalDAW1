package pe.edu.cibertec.ventacelulares.repository;

import org.springframework.data.repository.CrudRepository;
import pe.edu.cibertec.ventacelulares.entity.Usuario;

public interface UsuarioRepository extends CrudRepository<Usuario, Integer> {
    Usuario findByUsuario(String usuario);
}
