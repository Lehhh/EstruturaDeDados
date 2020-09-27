package br.com.natureza.fotos.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.natureza.fotos.model.Imagem;

import java.util.ArrayList;
import java.util.List;

public interface ImagensRepository extends CrudRepository<Imagem, Integer> {
    ArrayList<Imagem> findAll();
}