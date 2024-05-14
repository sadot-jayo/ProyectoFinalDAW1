package pe.edu.cibertec.ventacelulares.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pe.edu.cibertec.ventacelulares.entity.PedidoDetalle;
import pe.edu.cibertec.ventacelulares.repository.PedidoDetalleRepository;
import pe.edu.cibertec.ventacelulares.response.AddResponse;
import pe.edu.cibertec.ventacelulares.response.DeleteResponse;
import pe.edu.cibertec.ventacelulares.response.GetResponse;
import pe.edu.cibertec.ventacelulares.response.UpdateResponse;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pedido_detalle")
public class PedidoDetalleService {
    @Autowired
    private PedidoDetalleRepository repository;

    @GetMapping("/all")
    public GetResponse<List<PedidoDetalle>> getPedidoDetalles(){
        Iterable<PedidoDetalle> lista = repository.findAll();
        return new GetResponse("01", null, lista);
    }

    @GetMapping("/all/{id}")
    public GetResponse<PedidoDetalle> getPedidoDetalleById(@PathVariable("id") Integer id){
        Optional<PedidoDetalle> obj = repository.findById(id);
        if (!obj.isPresent()) {
            return new GetResponse("99", "PedidoDetalle no encontrado", null);
        }
        return new GetResponse("01", null, obj.get());
    }

    @PostMapping("/add")
    public AddResponse addPedidoDetalle(@RequestBody PedidoDetalle obj) {
        if (obj.getIdPedidoDetalle() != null) {
            return new AddResponse("99", "Parámetro Id no permitido");
        }
        repository.save(obj);
        return new AddResponse("01", null);
    }

    @PutMapping("/update")
    public UpdateResponse updatePedidoDetalle(@RequestBody PedidoDetalle obj){
        if (!repository.findById(obj.getIdPedidoDetalle()).isPresent()) {
            return new UpdateResponse("99", "PedidoDetalle no encontrado");
        }
        repository.save(obj);
        return new UpdateResponse("01", null);
    }

    @DeleteMapping("/delete/{id}")
    public DeleteResponse deletePedidoDetalle(@PathVariable("id") Integer id){
        Optional<PedidoDetalle> obj = repository.findById(id);
        if (!obj.isPresent()) {
            return new DeleteResponse("99", "PedidoDetalle no encontrado");
        }
        repository.delete(obj.get());
        return new DeleteResponse("01", null);
    }

}
