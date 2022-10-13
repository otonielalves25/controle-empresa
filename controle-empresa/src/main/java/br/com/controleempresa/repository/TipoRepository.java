package br.com.controleempresa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.controleempresa.modelo.Status;
import br.com.controleempresa.modelo.TipoEmpresa;

@Repository
public interface TipoRepository extends JpaRepository<TipoEmpresa, Long> {

	Status findByNome(String nome);

}
