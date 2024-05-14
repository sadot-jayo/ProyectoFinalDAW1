package pe.edu.cibertec.ventacelulares.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pe.edu.cibertec.ventacelulares.entity.Producto;
import pe.edu.cibertec.ventacelulares.repository.ProductoRepository;
import pe.edu.cibertec.ventacelulares.response.AddResponse;
import pe.edu.cibertec.ventacelulares.response.DeleteResponse;
import pe.edu.cibertec.ventacelulares.response.GetResponse;
import pe.edu.cibertec.ventacelulares.response.UpdateResponse;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/producto")
public class ProductoService {
    @Autowired
    private ProductoRepository repository;

    @GetMapping("/all")
    public GetResponse<List<Producto>> getProductos(){
        Iterable<Producto> lista = repository.findAll();
        return new GetResponse("01", null, lista);
    }

    @GetMapping("/all/{id}")
    public GetResponse<Producto> getProductoById(@PathVariable("id") Integer id){
        Optional<Producto> obj = repository.findById(id);
        if (!obj.isPresent()) {
            return new GetResponse("99", "Producto no encontrado", null);
        }
        return new GetResponse("01", null, obj.get());
    }

    @PostMapping("/add")
    public AddResponse addProducto(@RequestBody Producto obj) {
        if (obj.getIdProducto() != null) {
            return new AddResponse("99", "Parámetro Id no permitido");
        }
        repository.save(obj);
        return new AddResponse("01", null);
    }

    @PutMapping("/update")
    public UpdateResponse updateProducto(@RequestBody Producto obj){
        if (!repository.findById(obj.getIdProducto()).isPresent()) {
            return new UpdateResponse("99", "Producto no encontrado");
        }
        repository.save(obj);
        return new UpdateResponse("01", null);
    }

    @DeleteMapping("/delete/{id}")
    public DeleteResponse deleteProducto(@PathVariable("id") Integer id){
        Optional<Producto> obj = repository.findById(id);
        if (!obj.isPresent()) {
            return new DeleteResponse("99", "Producto no encontrado");
        }
        repository.delete(obj.get());
        return new DeleteResponse("01", null);
    }

}
