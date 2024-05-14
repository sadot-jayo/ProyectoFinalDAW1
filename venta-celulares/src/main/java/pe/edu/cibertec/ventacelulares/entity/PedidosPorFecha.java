package pe.edu.cibertec.ventacelulares.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PedidosPorFecha {
    private String nro_pedido;
    private Date fecha_pedido;
    private String nombres;
    private String apellidos;
    private String direccion;
    private String distrito;
    private int cantidad;
    private Double precio_unitario;

}
