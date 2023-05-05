package br.com.fenda.helpdesk.resources;

import br.com.fenda.helpdesk.domain.Tecnico;
import br.com.fenda.helpdesk.domain.dtos.TecnicoDTO;
import br.com.fenda.helpdesk.services.TecnicoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value ="/tecnicos")
public class TecnicoResouce {
    @Autowired
    private TecnicoService service;
    @GetMapping(value = "/{id}")
    public ResponseEntity<TecnicoDTO> findById(@PathVariable Integer id){
        Tecnico tecnico = service.findByID(id);
        return ResponseEntity.ok().body(new TecnicoDTO(tecnico));
    }
    @GetMapping
    public ResponseEntity<List<TecnicoDTO>> findAll(){
        List<Tecnico> listTecnico = service.findAll();
        List<TecnicoDTO> listTecnicoDTO = listTecnico.stream().map(obj -> new TecnicoDTO(obj)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listTecnicoDTO);
    }
    @PostMapping
    public ResponseEntity<TecnicoDTO> create(@Valid @RequestBody TecnicoDTO tecnicoDTO){
        Tecnico novoTecnico = service.create(tecnicoDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(novoTecnico.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }
    @PutMapping(value = "/{id}")
    public ResponseEntity<TecnicoDTO> update(@PathVariable java.lang.Integer id, @Valid @RequestBody TecnicoDTO tecnicoDTO){
        Tecnico oldTecnico = service.update(id, tecnicoDTO);
        return ResponseEntity.ok().body(new TecnicoDTO(oldTecnico));
    }
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<TecnicoDTO> delete(@PathVariable java.lang.Integer id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
