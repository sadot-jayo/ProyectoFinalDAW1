package pe.edu.cibertec.ventacelulares.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import pe.edu.cibertec.ventacelulares.entity.Pedido;
import pe.edu.cibertec.ventacelulares.entity.PedidosPorFecha;

import java.util.Date;
import java.util.List;

public interface PedidoRepository extends CrudRepository<Pedido, Integer> {
    @Query("SELECT NEW pe.edu.cibertec.ventacelulares.entity.PedidosPorFecha(p.nroPedido, p.fechaPedido, c.nombres, c.apellidos, c.direccion, d.distrito, pd.cantidad, pd.precioUnitario) FROM Pedido p " +
            "INNER JOIN PedidoDetalle pd ON p.idPedido=pd.pedido.idPedido " +
            "INNER JOIN Cliente c ON p.cliente.idCliente = c.idCliente " +
            "INNER JOIN Distrito d ON c.distrito.idDistrito = d.idDistrito " +
            "WHERE p.fechaPedido BETWEEN ?1 AND ?2 ")
    List<PedidosPorFecha> findPedidosPorFecha(Date fecha1, Date fecha2);
}
