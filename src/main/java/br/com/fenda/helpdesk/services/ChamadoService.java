package br.com.fenda.helpdesk.services;

import br.com.fenda.helpdesk.domain.Chamado;
import br.com.fenda.helpdesk.domain.Tecnico;
import br.com.fenda.helpdesk.repositories.ChamadoRepository;
import br.com.fenda.helpdesk.repositories.TecnicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ChamadoService {
    @Autowired
    private ChamadoRepository repository;

    public Chamado findByID(Integer id) {
        Optional<Chamado> obj = repository.findById(id);
        return obj.orElse(null);
    }
}
