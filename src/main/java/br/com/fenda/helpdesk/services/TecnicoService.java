package br.com.fenda.helpdesk.services;

import br.com.fenda.helpdesk.domain.Tecnico;
import br.com.fenda.helpdesk.repositories.TecnicoRepository;
import br.com.fenda.helpdesk.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TecnicoService {
    @Autowired
    private TecnicoRepository repository;

    public Tecnico findByID(Integer id) {
        Optional<Tecnico> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id));
    }
}
