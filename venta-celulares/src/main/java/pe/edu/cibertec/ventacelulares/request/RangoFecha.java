package pe.edu.cibertec.ventacelulares.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RangoFecha {
    private String fecha1;
    private String fecha2;
}
