package br.com.fenda.helpdesk.resources;

import br.com.fenda.helpdesk.domain.Chamado;
import br.com.fenda.helpdesk.domain.dtos.ChamadoDTO;
import br.com.fenda.helpdesk.domain.dtos.TecnicoDTO;
import br.com.fenda.helpdesk.repositories.ChamadoRepository;
import br.com.fenda.helpdesk.services.ChamadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value ="/chamados")
public class ChamadoResouce {
    @Autowired
    private ChamadoService service;
    @GetMapping(value = "/{id}")
    public ResponseEntity<ChamadoDTO> findById(@PathVariable Integer id){
        Chamado chamado = service.findByID(id);
        return ResponseEntity.ok().body(new ChamadoDTO(chamado));
    }
}
