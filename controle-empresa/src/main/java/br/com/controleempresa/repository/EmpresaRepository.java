package br.com.controleempresa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.controleempresa.modelo.Empresa;


@Repository
public interface EmpresaRepository extends JpaRepository<Empresa, Long> {

	Empresa findByNome(String nome);

}
