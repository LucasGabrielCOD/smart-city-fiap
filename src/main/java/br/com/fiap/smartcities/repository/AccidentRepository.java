package br.com.fiap.smartcities.repository;

import br.com.fiap.smartcities.model.Accident;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccidentRepository extends JpaRepository<Accident, Long> {
}

