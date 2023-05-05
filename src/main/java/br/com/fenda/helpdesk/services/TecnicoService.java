package br.com.fenda.helpdesk.services;

import br.com.fenda.helpdesk.domain.Pessoa;
import br.com.fenda.helpdesk.domain.Tecnico;
import br.com.fenda.helpdesk.domain.dtos.TecnicoDTO;
import br.com.fenda.helpdesk.repositories.PessoaRepository;
import br.com.fenda.helpdesk.repositories.TecnicoRepository;
import br.com.fenda.helpdesk.services.exceptions.DataIntegrityViolationException;
import br.com.fenda.helpdesk.services.exceptions.ObjectNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class TecnicoService {
    @Autowired
    private TecnicoRepository repository;
    @Autowired
    private PessoaRepository pessoaRepository;

    public Tecnico findByID(Integer id) {
        Optional<Tecnico> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id));
    }

    public List<Tecnico> findAll() {
        return repository.findAll();
    }

    public Tecnico create(TecnicoDTO tecnicoDTO){
        tecnicoDTO.setId(null);
        validaDuplicidade(tecnicoDTO);
        Tecnico novoTecnico = new Tecnico(tecnicoDTO);
        return repository.save(novoTecnico);

    }

    public Tecnico update(Integer id, @Valid TecnicoDTO tecnicoDTO) {
        tecnicoDTO.setId(id);
        Tecnico oldTecnico = findByID(id);

        validaDuplicidade(tecnicoDTO);
        oldTecnico = new Tecnico(tecnicoDTO);
        return repository.save(oldTecnico);
    }

    public void delete(Integer id) {
        Tecnico tecnico = findByID(id);
        if(tecnico.getChamados().size() > 0){
            throw new DataIntegrityViolationException("Técnico possui ordens de serviço e não pode ser deletado!");
        }else{
            repository.deleteById(id);
        }

    }

    private void validaDuplicidade(TecnicoDTO tecnicoDTO){
        Optional<Pessoa> pessoa = pessoaRepository.findPessoaByCpf(tecnicoDTO.getCpf());
        if(pessoa.isPresent() && !Objects.equals(pessoa.get().getId(), tecnicoDTO.getId())){
            throw new DataIntegrityViolationException("CPF já cadastrado no Sistema!");
        }
        pessoa = pessoaRepository.findPessoaByEmail(tecnicoDTO.getEmail());
        if(pessoa.isPresent() && !Objects.equals(pessoa.get().getId(), tecnicoDTO.getId())){
            throw new DataIntegrityViolationException("Email já cadastrado no Sistema!");
        }
    }
}
