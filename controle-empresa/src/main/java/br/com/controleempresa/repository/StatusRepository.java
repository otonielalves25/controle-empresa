package br.com.controleempresa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.controleempresa.modelo.Status;

@Repository
public interface StatusRepository extends JpaRepository<Status, Long> {

	Status findByNome(String nome);

}
