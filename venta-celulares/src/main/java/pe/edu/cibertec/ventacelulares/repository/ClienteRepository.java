package pe.edu.cibertec.ventacelulares.repository;

import org.springframework.data.repository.CrudRepository;
import pe.edu.cibertec.ventacelulares.entity.Cliente;

public interface ClienteRepository extends CrudRepository<Cliente, Integer> {
}
