package br.com.fenda.helpdesk.services;

import br.com.fenda.helpdesk.domain.Cliente;
import br.com.fenda.helpdesk.domain.Pessoa;
import br.com.fenda.helpdesk.domain.dtos.ClienteDTO;
import br.com.fenda.helpdesk.repositories.ClienteRepository;
import br.com.fenda.helpdesk.repositories.PessoaRepository;
import br.com.fenda.helpdesk.services.exceptions.DataIntegrityViolationException;
import br.com.fenda.helpdesk.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepository repository;
    @Autowired
    private PessoaRepository pessoaRepository;


    public Cliente findByID(Integer id) {
        Optional<Cliente> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id));
    }

    public List<Cliente> findAll() {
        return repository.findAll();
    }

    public Cliente create(ClienteDTO clienteDTO){
        clienteDTO.setId(null);
        validaDuplicidade(clienteDTO);
        Cliente novoCliente = new Cliente(clienteDTO);
        return repository.save(novoCliente);

    }

    public Cliente update(Integer id, ClienteDTO clienteDTO) {
        clienteDTO.setId(id);
        Cliente oldCliente = findByID(id);
        validaDuplicidade(clienteDTO);
        oldCliente = new Cliente(clienteDTO);

        return repository.save(oldCliente);
    }

    public void delete(Integer id) {
        Cliente cliente = findByID(id);
        if(cliente.getChamados().size() > 0){
            throw new DataIntegrityViolationException("Cliente possui ordens de serviço e não pode ser deletado!");
        }else{
            repository.deleteById(id);
        }

    }

    private void validaDuplicidade(ClienteDTO clienteDTO){
        Optional<Pessoa> pessoa = pessoaRepository.findPessoaByCpf(clienteDTO.getCpf());
        if(pessoa.isPresent() && !Objects.equals(pessoa.get().getId(), clienteDTO.getId())){
            throw new DataIntegrityViolationException("CPF já cadastrado no Sistema!");
        }
        pessoa = pessoaRepository.findPessoaByEmail(clienteDTO.getEmail());
        if(pessoa.isPresent() && !Objects.equals(pessoa.get().getId(), clienteDTO.getId())){
            throw new DataIntegrityViolationException("Email já cadastrado no Sistema!");
        }
    }
}
