package br.com.fenda.helpdesk.resources;

import br.com.fenda.helpdesk.domain.Cliente;
import br.com.fenda.helpdesk.domain.Tecnico;
import br.com.fenda.helpdesk.domain.dtos.ClienteDTO;
import br.com.fenda.helpdesk.domain.dtos.TecnicoDTO;
import br.com.fenda.helpdesk.services.ClienteService;
import br.com.fenda.helpdesk.services.TecnicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value ="/clientes")
public class ClienteResouce {
    @Autowired
    private ClienteService service;
    @GetMapping(value = "/{id}")
    public ResponseEntity<ClienteDTO> findById(@PathVariable Integer id){
        Cliente cliente = service.findByID(id);
        return ResponseEntity.ok().body(new ClienteDTO(cliente));
    }
}
