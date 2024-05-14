package pe.edu.cibertec.ventacelulares.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pe.edu.cibertec.ventacelulares.entity.Distrito;
import pe.edu.cibertec.ventacelulares.repository.DistritoRepository;
import pe.edu.cibertec.ventacelulares.response.AddResponse;
import pe.edu.cibertec.ventacelulares.response.DeleteResponse;
import pe.edu.cibertec.ventacelulares.response.GetResponse;
import pe.edu.cibertec.ventacelulares.response.UpdateResponse;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/distrito")
public class DistritoService {
    @Autowired
    private DistritoRepository repository;

    @GetMapping("/all")
    public GetResponse<List<Distrito>> getDistritos(){
        Iterable<Distrito> lista = repository.findAll();
        return new GetResponse("01", null, lista);
    }

    @GetMapping("/all/{id}")
    public GetResponse<Distrito> getDistritoById(@PathVariable("id") Integer id){
        Optional<Distrito> obj = repository.findById(id);
        if (!obj.isPresent()) {
            return new GetResponse("99", "Distrito no encontrado", null);
        }
        return new GetResponse("01", null, obj.get());
    }

    @PostMapping("/add")
    public AddResponse addDistrito(@RequestBody Distrito obj) {
        if (obj.getIdDistrito() != null) {
            return new AddResponse("99", "Parámetro Id no permitido");
        }
        repository.save(obj);
        return new AddResponse("01", null);
    }

    @PutMapping("/update")
    public UpdateResponse updateDistrito(@RequestBody Distrito obj){
        if (!repository.findById(obj.getIdDistrito()).isPresent()) {
            return new UpdateResponse("99", "Distrito no encontrado");
        }
        repository.save(obj);
        return new UpdateResponse("01", null);
    }

    @DeleteMapping("/delete/{id}")
    public DeleteResponse deleteDistrito(@PathVariable("id") Integer id){
        Optional<Distrito> obj = repository.findById(id);
        if (!obj.isPresent()) {
            return new DeleteResponse("99", "Distrito no encontrado");
        }
        repository.delete(obj.get());
        return new DeleteResponse("01", null);
    }

}
