package br.com.natureza.fotos.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.natureza.fotos.model.Imagem;

public interface ImagensRepository extends CrudRepository<Imagem, Integer> {
}