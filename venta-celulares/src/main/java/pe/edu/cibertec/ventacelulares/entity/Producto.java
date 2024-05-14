package pe.edu.cibertec.ventacelulares.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_producto")
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_producto")
    private Integer idProducto;

    private String titulo;
    private String descripcion;
    private String imagen;
    private String marca;
    private Integer stock;

    @Column(name = "precio_unitario")
    private Double precioUnitario;

    @OneToOne()
    @JoinColumn(name = "id_categoria")
    private Categoria categoria;

}
