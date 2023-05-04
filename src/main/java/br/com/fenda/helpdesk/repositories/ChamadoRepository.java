package br.com.fenda.helpdesk.repositories;

import br.com.fenda.helpdesk.domain.Chamado;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChamadoRepository extends JpaRepository<Chamado, Integer> {
}
