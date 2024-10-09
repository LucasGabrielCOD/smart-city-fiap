package br.com.fiap.smartcities.repository;

import br.com.fiap.smartcities.model.Emergency;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmergencyRepository extends JpaRepository<Emergency, Long> {
}

