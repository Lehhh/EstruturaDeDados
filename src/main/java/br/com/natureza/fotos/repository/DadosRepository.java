package br.com.natureza.fotos.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.natureza.fotos.model.AnalisedeDados;

import java.util.ArrayList;
import java.util.List;


public interface DadosRepository extends CrudRepository<AnalisedeDados, Integer> {
    ArrayList<AnalisedeDados> findAll();
}
