package br.com.fenda.helpdesk.repositories;

import br.com.fenda.helpdesk.domain.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PessoaRepository extends JpaRepository<Pessoa, Integer> {
}
