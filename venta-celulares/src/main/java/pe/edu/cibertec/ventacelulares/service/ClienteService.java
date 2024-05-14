package pe.edu.cibertec.ventacelulares.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pe.edu.cibertec.ventacelulares.entity.Categoria;
import pe.edu.cibertec.ventacelulares.entity.Cliente;
import pe.edu.cibertec.ventacelulares.repository.ClienteRepository;
import pe.edu.cibertec.ventacelulares.response.AddResponse;
import pe.edu.cibertec.ventacelulares.response.DeleteResponse;
import pe.edu.cibertec.ventacelulares.response.GetResponse;
import pe.edu.cibertec.ventacelulares.response.UpdateResponse;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cliente")
public class ClienteService {
    @Autowired
    private ClienteRepository repository;

    @GetMapping("/all")
    public GetResponse<List<Categoria>> getClientes(){
        Iterable<Cliente> lista = repository.findAll();
        return new GetResponse("01", null, lista);
    }

    @GetMapping("/all/{id}")
    public GetResponse<Cliente> getClienteById(@PathVariable("id") Integer id){
        Optional<Cliente> obj = repository.findById(id);
        if (!obj.isPresent()) {
            return new GetResponse("99", "Cliente no encontrado", null);
        }
        return new GetResponse("01", null, obj.get());
    }

    @PostMapping("/add")
    public AddResponse addCliente(@RequestBody Cliente obj) {
        if (obj.getIdCliente() != null) {
            return new AddResponse("99", "Parámetro Id no permitido");
        }
        repository.save(obj);
        return new AddResponse("01", null);
    }

    @PutMapping("/update")
    public UpdateResponse updateCliente(@RequestBody Cliente obj){
        if (!repository.findById(obj.getIdCliente()).isPresent()) {
            return new UpdateResponse("99", "Cliente no encontrado");
        }
        repository.save(obj);
        return new UpdateResponse("01", null);
    }

    @DeleteMapping("/delete/{id}")
    public DeleteResponse deleteCliente(@PathVariable("id") Integer id){
        Optional<Cliente> obj = repository.findById(id);
        if (!obj.isPresent()) {
            return new DeleteResponse("99", "Cliente no encontrado");
        }
        repository.delete(obj.get());
        return new DeleteResponse("01", null);
    }

}
