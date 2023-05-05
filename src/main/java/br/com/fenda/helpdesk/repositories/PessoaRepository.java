package br.com.fenda.helpdesk.repositories;

import br.com.fenda.helpdesk.domain.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PessoaRepository extends JpaRepository<Pessoa, Integer> {
    Optional<Pessoa> findPessoaByCpf(String cpf);
    Optional<Pessoa> findPessoaByEmail(String email);
}
