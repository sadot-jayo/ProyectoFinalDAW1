package pe.edu.cibertec.ventacelulares.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_cliente")
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cliente")
    private Integer idCliente;

    @Column(unique=true)
    private String usuario;
    private String contrasenia;
    private String nombres;
    private String apellidos;
    private String direccion;
    private String referencia;
    private String telefono;
    private String celular;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_distrito")
    private Distrito distrito;
    private String dni;

}
