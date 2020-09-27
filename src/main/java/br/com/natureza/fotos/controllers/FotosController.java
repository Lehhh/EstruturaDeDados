package br.com.natureza.fotos.controllers;

import br.com.natureza.fotos.services.DadosService;
import br.com.natureza.fotos.services.FotosServices;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class FotosController {

    @Autowired
    private FotosServices fotosServices;

    @Autowired
    private DadosService dadosService;

    @RequestMapping("/")
    public String index(Model model) {
        return "index";
    }

    private final Logger logger = LoggerFactory.getLogger(FotosController.class);

    @RequestMapping("estruturadedados")
    public String mostrarEstruturaDeDados(Model model) {
        model.addAttribute("imagens", fotosServices.mostrarTodasImages());
        model.addAttribute("dados", dadosService.mostrarTodosDados());
        return "estruturadedados";
    }

    @RequestMapping(value = "adicionar", method = RequestMethod.POST)
    public String adicionarNaEstrutura(@RequestParam("nome") String nome, @RequestParam("descricao") String descricao,
                                       @RequestParam("categoria") String categoria, @RequestParam("url") String url,
                                       @RequestParam("id_metodo") int id_metodo, Model model) {
        fotosServices.salvarNovaImagem(nome, descricao, categoria, url, id_metodo);
        mostrarEstruturaDeDados(model);
        return "estruturadedados";
    }


    @RequestMapping(value = "deletar", method = RequestMethod.POST)
    public String removeEstruturaFila(Model model) {
        fotosServices.deletarPrimeiraDaLista();
        mostrarEstruturaDeDados(model);
        return "estruturadedados";
    }

    @RequestMapping(value = "organizarbubble", method = RequestMethod.GET)
    public String organizarEstruturaBubble(Model model) {
        fotosServices.organizarEstruturaBubble();
        mostrarEstruturaDeDados(model);
        return "estruturadedados";
    }

    @RequestMapping(value = "organizarselection", method = RequestMethod.GET)
    public String organizarEstruturaSelection(Model model) {
        fotosServices.organizarEstruturaSelection();
        mostrarEstruturaDeDados(model);
        return "estruturadedados";
    }

    @RequestMapping(value = "organizarinsert", method = RequestMethod.GET)
    public String organizarEstruturaInsert(Model model) {
        fotosServices.organizarInsert();
        mostrarEstruturaDeDados(model);
        return "estruturadedados";
    }


}
