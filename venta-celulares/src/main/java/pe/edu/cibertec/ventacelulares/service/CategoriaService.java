package pe.edu.cibertec.ventacelulares.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pe.edu.cibertec.ventacelulares.entity.Categoria;
import pe.edu.cibertec.ventacelulares.repository.CategoriaRepository;
import pe.edu.cibertec.ventacelulares.response.AddResponse;
import pe.edu.cibertec.ventacelulares.response.DeleteResponse;
import pe.edu.cibertec.ventacelulares.response.GetResponse;
import pe.edu.cibertec.ventacelulares.response.UpdateResponse;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/categoria")
public class CategoriaService {
    @Autowired
    private CategoriaRepository repository;

    @GetMapping("/all")
    public GetResponse<List<Categoria>> getCategorias(){
        Iterable<Categoria> lista = repository.findAll();
        return new GetResponse("01", null, lista);
    }

    @GetMapping("/all/{id}")
    public GetResponse<Categoria> getCategoriaById(@PathVariable("id") Integer id){
        Optional<Categoria> obj = repository.findById(id);
        if (!obj.isPresent()) {
            return new GetResponse("99", "Categoría no encontrada", null);
        }
        return new GetResponse("01", null, obj.get());
    }

    @PostMapping("/add")
    public AddResponse addCategoria(@RequestBody Categoria obj) {
        if (obj.getIdCategoria() != null) {
            return new AddResponse("99", "Parámetro Id no permitido");
        }
        repository.save(obj);
        return new AddResponse("01", null);
    }

    @PutMapping("/update")
    public UpdateResponse updateCategoria(@RequestBody Categoria obj){
        if (!repository.findById(obj.getIdCategoria()).isPresent()) {
            return new UpdateResponse("99", "Categoría no encontrada");
        }
        repository.save(obj);
        return new UpdateResponse("01", null);
    }

    @DeleteMapping("/delete/{id}")
    public DeleteResponse deleteCategoria(@PathVariable("id") Integer id){
        Optional<Categoria> obj = repository.findById(id);
        if (!obj.isPresent()) {
            return new DeleteResponse("99", "Categoría no encontrada");
        }
        repository.delete(obj.get());
        return new DeleteResponse("01", null);
    }

}
