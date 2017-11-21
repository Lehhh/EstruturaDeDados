package br.com.natureza.fotos;

import java.util.ArrayList;

import org.springframework.aop.target.SimpleBeanTargetSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.natureza.fotos.model.AnalisedeDados;
import br.com.natureza.fotos.model.Imagem;
import br.com.natureza.fotos.repository.DadosRepository;
import br.com.natureza.fotos.repository.ImagensRepository;

@Controller
public class FotosController {

	@Autowired(required = true)
	private ImagensRepository repository;
	
	@Autowired(required = true)
	private DadosRepository repositoryDados;

	public ArrayList<Imagem> imagenss = new ArrayList<Imagem>();
	public ArrayList<AnalisedeDados> dado = new ArrayList<AnalisedeDados>();
	
	
	@RequestMapping("/")
	public String index(Model model) {
		
		return "index";  
	}

	@RequestMapping("estruturadedados")
	public String estruturaDeDados(Model model) {

		imagenss = (ArrayList<Imagem>) repository.findAll();
		model.addAttribute("imagens", imagenss);
		
		dado = (ArrayList<AnalisedeDados>) repositoryDados.findAll();
		model.addAttribute("dados", dado);

		return "estruturadedados";
	}
	
	public void mostrarAnalise(Model model) {
		
		dado = (ArrayList<AnalisedeDados>) repositoryDados.findAll();
		model.addAttribute("dados", dado);
		
	}
	

	@RequestMapping(value = "adicionar", method = RequestMethod.POST)
	public String addEstrutura1(@RequestParam("nome") String nome, @RequestParam("descricao") String descricao,
			@RequestParam("categoria") String categoria, @RequestParam("url") String url,
			@RequestParam("id_metodo") int id_metodo, Model model) {

		Imagem novoDado = new Imagem(nome, descricao, categoria, url, id_metodo);

		repository.save(novoDado);
		
		dado = (ArrayList<AnalisedeDados>) repositoryDados.findAll();
		model.addAttribute("dados", dado);

		imagenss = (ArrayList<Imagem>) repository.findAll();
		model.addAttribute("imagens", imagenss);

		return "estruturadedados";
	}
	

	@RequestMapping(value = "deletar", method = RequestMethod.POST)
	public String removeEstruturaFila(Model model) {
		

		imagenss = (ArrayList<Imagem>) repository.findAll();
		repository.delete(imagenss.get(0).getId());
		imagenss = (ArrayList<Imagem>) repository.findAll();
		
		dado = (ArrayList<AnalisedeDados>) repositoryDados.findAll();
		model.addAttribute("dados", dado);
		
		model.addAttribute("imagens", imagenss);
		return "estruturadedados";

	}

	@RequestMapping(value = "organizarbubble", method = RequestMethod.GET)
	public String organizarEstruturaBubble(Model model) {

		
		imagenss = (ArrayList<Imagem>) repository.findAll();
		dado = (ArrayList<AnalisedeDados>) repositoryDados.findAll();
		Imagem aux = null;
		int i = 0;
		long cont = 0;
		
		long start = System.currentTimeMillis(); 
		for (i = 0; i < imagenss.size(); i++) {
			for (int j = 0; j < imagenss.size() - 1; j++) {
				if (imagenss.get(j).getId_metodo() > imagenss.get(j + 1).getId_metodo()) {
					aux = imagenss.get(j);
					imagenss.set(j, imagenss.get(j + 1));
					imagenss.set(j + 1, aux);
					cont++;
					
				}
			}
		}
		long elapsedTime = System.currentTimeMillis() - start;
		long elapsedTimeSec = elapsedTime/1000;
		AnalisedeDados dado = new AnalisedeDados("Bubble Sort",elapsedTimeSec, cont);
		repositoryDados.save(dado);
		
		mostrarAnalise(model);
		System.out.println("tempo gasto em segundos: "+ elapsedTime + " trocas feitas: " + cont);
		model.addAttribute("imagens", imagenss);

		return "estruturadedados";
	}

	@RequestMapping(value = "organizarselection", method = RequestMethod.GET)
	public String organizarEstruturaSelection(Model model) {

		imagenss = (ArrayList<Imagem>) repository.findAll();
		dado = (ArrayList<AnalisedeDados>) repositoryDados.findAll();
		Imagem menorValor = imagenss.get(0);
		int j = 0, k = 0;
				long cont = 0;
		int tamanho = imagenss.size();
		Imagem min_idx = new Imagem();
		// Primeiro for é para identificar a menor posição
		
		long start = System.currentTimeMillis(); 
		for (int i = 0; i < tamanho - 1; i++) {
			// armazena o menor valor do for
			min_idx = imagenss.get(i);

			menorValor = imagenss.get(i + 1);
			for (j = i + 2; j < tamanho; j++) {

				if (imagenss.get(j).getId_metodo() < menorValor.getId_metodo()) {
					menorValor = imagenss.get(j);
					k = j;
				}
			}
			imagenss.set(i, menorValor);
			imagenss.set(k, min_idx);
			cont++;
		}

		long elapsedTime = System.currentTimeMillis() - start;
		long elapsedTimeSec = elapsedTime/1000;
		AnalisedeDados dado = new AnalisedeDados("Selection Sort",elapsedTimeSec, cont);
		repositoryDados.save(dado);
		
		mostrarAnalise(model);
		System.out.println("tempo gasto em segundos: "+ elapsedTime + " trocas feitas: " + cont);
		model.addAttribute("imagens", imagenss);

		return "estruturadedados";
	}
	
	@RequestMapping(value = "organizarinsert", method = RequestMethod.GET)
	public String organizarEstruturaInsert(Model model) {
		
		imagenss = (ArrayList<Imagem>) repository.findAll();
		dado = (ArrayList<AnalisedeDados>) repositoryDados.findAll();
		int tamanho = imagenss.size();
		Imagem aux;
		int j;
		long cont = 0;
		long start = System.currentTimeMillis();
		for (int i= 0; i<tamanho;i++) {
			aux = imagenss.get(i);
			j = i;
				while(j>0 && imagenss.get(j-1).getId_metodo() > aux.getId_metodo()) {
				imagenss.set(j, imagenss.get(j-1));
				j--;
				}
				imagenss.set(j, aux);
				cont++;
			}
		
		long elapsedTime = System.currentTimeMillis() - start;
		long elapsedTimeSec = elapsedTime/1000;
		AnalisedeDados dado = new AnalisedeDados("Insert Sort",elapsedTimeSec, cont);
		repositoryDados.save(dado);
		
		mostrarAnalise(model);
		
		System.out.println("tempo gasto em segundos: "+ elapsedTime + " trocas feitas: " + cont);
		
		model.addAttribute("imagens", imagenss);
		
		return "estruturadedados";
	}
	

}
