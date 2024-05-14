package pe.edu.cibertec.ventacelulares.repository;

import org.springframework.data.repository.CrudRepository;
import pe.edu.cibertec.ventacelulares.entity.Producto;

public interface ProductoRepository extends CrudRepository<Producto, Integer> {
}
