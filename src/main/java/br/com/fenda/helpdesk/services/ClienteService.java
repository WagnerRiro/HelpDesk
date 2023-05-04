package br.com.fenda.helpdesk.services;

import br.com.fenda.helpdesk.domain.Cliente;
import br.com.fenda.helpdesk.repositories.ClienteRepository;
import br.com.fenda.helpdesk.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepository repository;

    public Cliente findByID(Integer id) {
        Optional<Cliente> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id));
    }
}
