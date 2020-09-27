package br.com.natureza.fotos.services;

import br.com.natureza.fotos.model.AnalisedeDados;
import br.com.natureza.fotos.model.Imagem;
import br.com.natureza.fotos.repository.ImagensRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class FotosServices {

   @Autowired
   private ImagensRepository imagensRepository;

   @Autowired
   private DadosService dadosService;

   private final Logger logger = LoggerFactory.getLogger(FotosServices.class);

    public ArrayList<Imagem> mostrarTodasImages(){
        return imagensRepository.findAll();
    }

    public void salvarNovaImagem(String nome, String descricao, String categoria, String url, int id_metodo){
        imagensRepository.save(new Imagem(nome, descricao, categoria, url, id_metodo));
    }

    public void deletarPrimeiraDaLista(){
        imagensRepository.delete(imagensRepository.findAll().get(0).getId());
    }

    public void organizarEstruturaBubble(){
        ArrayList<Imagem> imagens = mostrarTodasImages();
        Imagem aux = null;
        int i = 0;
        long cont = 0;
        long start = System.currentTimeMillis();
        for (i = 0; i < imagens.size(); i++) {
            for (int j = 0; j < imagens.size() - 1; j++) {
                if (imagens.get(j).getId_metodo() > imagens.get(j + 1).getId_metodo()) {
                    aux = imagens.get(j);
                    imagens.set(j, imagens.get(j + 1));
                    imagens.set(j + 1, aux);
                    cont++;
                }
            }
        }
        long elapsedTimeSec = (System.currentTimeMillis() - start) / 1000;
        dadosService.salvarNovoDado(new AnalisedeDados("Bubble Sort", elapsedTimeSec, cont));
        logger.info("tempo gasto em segundos: " + elapsedTimeSec + " trocas feitas: " + cont);
    }

    public void organizarEstruturaSelection(){
        ArrayList<Imagem> imagens = mostrarTodasImages();
        Imagem menorValor = imagens.get(0);
        int j = 0, k = 0;
        long cont = 0;
        int tamanho = imagens.size();
        Imagem min_idx = new Imagem();
        // Primeiro for é para identificar a menor posição
        long start = System.currentTimeMillis();
        for (int i = 0; i < tamanho - 1; i++) {
            // armazena o menor valor do for
            min_idx = imagens.get(i);

            menorValor = imagens.get(i + 1);
            for (j = i + 2; j < tamanho; j++) {

                if (imagens.get(j).getId_metodo() < menorValor.getId_metodo()) {
                    menorValor = imagens.get(j);
                    k = j;
                }
            }
            imagens.set(i, menorValor);
            imagens.set(k, min_idx);
            cont++;
        }
        long elapsedTimeSec = (System.currentTimeMillis() - start) / 1000;
        dadosService.salvarNovoDado(new AnalisedeDados("Selection Sort", elapsedTimeSec, cont));
        logger.info("tempo gasto em segundos: " + elapsedTimeSec + " trocas feitas: " + cont);
    }

    public  void organizarInsert(){
        ArrayList<Imagem> imagenss = mostrarTodasImages();
        int tamanho = imagenss.size();
        Imagem aux;
        int j;
        long cont = 0;
        long start = System.currentTimeMillis();
        for (int i = 0; i < tamanho; i++) {
            aux = imagenss.get(i);
            j = i;
            while (j > 0 && imagenss.get(j - 1).getId_metodo() > aux.getId_metodo()) {
                imagenss.set(j, imagenss.get(j - 1));
                j--;
            }
            imagenss.set(j, aux);
            cont++;
        }
        long elapsedTimeSec = (System.currentTimeMillis() - start) / 1000;
        dadosService.salvarNovoDado(new AnalisedeDados("Insert Sort", elapsedTimeSec, cont));
        System.out.println("tempo gasto em segundos: " + elapsedTimeSec + " trocas feitas: " + cont);
    }

}
