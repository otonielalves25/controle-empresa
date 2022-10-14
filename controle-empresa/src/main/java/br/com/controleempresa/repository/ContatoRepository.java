package br.com.controleempresa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.controleempresa.modelo.Contato;

@Repository
public interface ContatoRepository extends JpaRepository<Contato, Long> {

	Contato findByNome(String nome);

}
