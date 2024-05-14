package pe.edu.cibertec.ventacelulares.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pe.edu.cibertec.ventacelulares.entity.Pedido;
import pe.edu.cibertec.ventacelulares.entity.PedidosPorFecha;
import pe.edu.cibertec.ventacelulares.repository.PedidoRepository;
import pe.edu.cibertec.ventacelulares.request.RangoFecha;
import pe.edu.cibertec.ventacelulares.response.AddResponse;
import pe.edu.cibertec.ventacelulares.response.DeleteResponse;
import pe.edu.cibertec.ventacelulares.response.GetResponse;
import pe.edu.cibertec.ventacelulares.response.UpdateResponse;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pedido")
public class PedidoService {
    @Autowired
    private PedidoRepository repository;

    @GetMapping("/all")
    public GetResponse<List<Pedido>> getPedidos(){
        Iterable<Pedido> lista = repository.findAll();
        return new GetResponse("01", null, lista);
    }

    @GetMapping("/all/{id}")
    public GetResponse<Pedido> getPedidoById(@PathVariable("id") Integer id){
        Optional<Pedido> obj = repository.findById(id);
        if (!obj.isPresent()) {
            return new GetResponse("99", "Pedido no encontrado", null);
        }
        return new GetResponse("01", null, obj.get());
    }

    @PostMapping("/add")
    public AddResponse addPedido(@RequestBody Pedido obj) {
        if (obj.getIdPedido() != null) {
            return new AddResponse("99", "Parámetro Id no permitido");
        }
        repository.save(obj);
        return new AddResponse("01", null);
    }

    @PutMapping("/update")
    public UpdateResponse updatePedido(@RequestBody Pedido obj){
        if (!repository.findById(obj.getIdPedido()).isPresent()) {
            return new UpdateResponse("99", "Pedido no encontrado");
        }
        repository.save(obj);
        return new UpdateResponse("01", null);
    }

    @DeleteMapping("/delete/{id}")
    public DeleteResponse deletePedido(@PathVariable("id") Integer id){
        Optional<Pedido> obj = repository.findById(id);
        if (!obj.isPresent()) {
            return new DeleteResponse("99", "Pedido no encontrado");
        }
        repository.delete(obj.get());
        return new DeleteResponse("01", null);
    }

    /*Con JPQL*/
    @PostMapping("/pedidos_fecha")
    public GetResponse<List<PedidosPorFecha>> addPedido(@RequestBody RangoFecha obj) throws ParseException {
        if (obj.getFecha1() == null || obj.getFecha2() == null) {
            return new GetResponse("99", "Parámetros incompletos", null);
        }
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date1 = formatter.parse(obj.getFecha1());
        Date date2 = formatter.parse(obj.getFecha2());
        Iterable<PedidosPorFecha> lista = repository.findPedidosPorFecha(date1, date2);
        return new GetResponse("01", null, lista);
    }

}
