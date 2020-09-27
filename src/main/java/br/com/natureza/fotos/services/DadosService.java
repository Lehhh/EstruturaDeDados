package br.com.natureza.fotos.services;

import br.com.natureza.fotos.model.AnalisedeDados;
import br.com.natureza.fotos.repository.DadosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class DadosService {

    @Autowired
    private DadosRepository dadosRepository;

    public ArrayList<AnalisedeDados> mostrarTodosDados(){
        return dadosRepository.findAll();
    }

    public void salvarNovoDado(AnalisedeDados analisedeDados){
        dadosRepository.save(analisedeDados);
    }
}
