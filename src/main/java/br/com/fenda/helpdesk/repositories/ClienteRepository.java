package br.com.fenda.helpdesk.repositories;

import br.com.fenda.helpdesk.domain.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
}
