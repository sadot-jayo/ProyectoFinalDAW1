package pe.edu.cibertec.ventacelulares.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pe.edu.cibertec.ventacelulares.entity.Rol;
import pe.edu.cibertec.ventacelulares.repository.RolRepository;
import pe.edu.cibertec.ventacelulares.response.AddResponse;
import pe.edu.cibertec.ventacelulares.response.DeleteResponse;
import pe.edu.cibertec.ventacelulares.response.GetResponse;
import pe.edu.cibertec.ventacelulares.response.UpdateResponse;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/rol")
public class RolService {
    @Autowired
    private RolRepository repository;

    @GetMapping("/all")
    public GetResponse<List<Rol>> getRols(){
        Iterable<Rol> lista = repository.findAll();
        return new GetResponse("01", null, lista);
    }

    @GetMapping("/all/{id}")
    public GetResponse<Rol> getRolById(@PathVariable("id") Integer id){
        Optional<Rol> obj = repository.findById(id);
        if (!obj.isPresent()) {
            return new GetResponse("99", "Rol no encontrado", null);
        }
        return new GetResponse("01", null, obj.get());
    }

    @PostMapping("/add")
    public AddResponse addRol(@RequestBody Rol obj) {
        if (obj.getIdRol() != null) {
            return new AddResponse("99", "Parámetro Id no permitido");
        }
        repository.save(obj);
        return new AddResponse("01", null);
    }

    @PutMapping("/update")
    public UpdateResponse updateRol(@RequestBody Rol obj){
        if (!repository.findById(obj.getIdRol()).isPresent()) {
            return new UpdateResponse("99", "Rol no encontrado");
        }
        repository.save(obj);
        return new UpdateResponse("01", null);
    }

    @DeleteMapping("/delete/{id}")
    public DeleteResponse deleteRol(@PathVariable("id") Integer id){
        Optional<Rol> obj = repository.findById(id);
        if (!obj.isPresent()) {
            return new DeleteResponse("99", "Rol no encontrado");
        }
        repository.delete(obj.get());
        return new DeleteResponse("01", null);
    }

}
