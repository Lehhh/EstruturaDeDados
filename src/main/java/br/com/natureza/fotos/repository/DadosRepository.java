package br.com.natureza.fotos.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.natureza.fotos.model.AnalisedeDados;


public interface DadosRepository extends CrudRepository<AnalisedeDados, Integer> {


}
