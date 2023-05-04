package br.com.fenda.helpdesk.repositories;

import br.com.fenda.helpdesk.domain.Tecnico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TecnicoRepository extends JpaRepository<Tecnico, Integer> {
}
