package br.com.fenda.helpdesk.services;

import br.com.fenda.helpdesk.domain.Chamado;
import br.com.fenda.helpdesk.domain.Cliente;
import br.com.fenda.helpdesk.domain.Tecnico;
import br.com.fenda.helpdesk.domain.dtos.ChamadoDTO;
import br.com.fenda.helpdesk.domain.enums.Prioridade;
import br.com.fenda.helpdesk.domain.enums.Status;
import br.com.fenda.helpdesk.repositories.ChamadoRepository;
import br.com.fenda.helpdesk.services.exceptions.ObjectNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ChamadoService {
    @Autowired
    private ChamadoRepository repository;
    @Autowired
    private TecnicoService tecnicoService;
    @Autowired
    private ClienteService clienteService;

    public Chamado findByID(Integer id) {
        Optional<Chamado> chamado = repository.findById(id);
        return chamado.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! ID: " + id));
    }

    public List<Chamado> findAll() {
        return repository.findAll();
    }

    public Chamado create(ChamadoDTO chamadoDTO) {
        return repository.save(novoChamado(chamadoDTO));
    }

    public Chamado update(Integer id, @Valid ChamadoDTO chamadoDTO) {
        chamadoDTO.setId(id);
        Chamado oldChamado = findByID(id);
        oldChamado = novoChamado(chamadoDTO);
        return repository.save(oldChamado);
    }

    private Chamado novoChamado(ChamadoDTO chamadoDTO) {
        Tecnico tecnico = tecnicoService.findByID(chamadoDTO.getTecnico());
        Cliente cliente = clienteService.findByID(chamadoDTO.getCliente());

        Chamado chamado = new Chamado();
        if(chamadoDTO.getId() != null) {
            chamado.setId(chamadoDTO.getId());
        }

        if(chamadoDTO.getStatus().equals(2)) {
            chamado.setDataFechamento(LocalDate.now());
        }

        chamado.setTecnico(tecnico);
        chamado.setCliente(cliente);
        chamado.setPrioridade(Prioridade.toEnum(chamadoDTO.getPrioridade()));
        chamado.setStatus(Status.toEnum(chamadoDTO.getStatus()));
        chamado.setTitulo(chamadoDTO.getTitulo());
        chamado.setObservacoes(chamadoDTO.getObservacoes());
        return chamado;
    }
}
