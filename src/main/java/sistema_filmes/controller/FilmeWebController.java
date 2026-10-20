package sistema_filmes.controller;

import sistema_filmes.model.Filme;
import sistema_filmes.repository.FilmeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class FilmeWebController {

    @Autowired
    private FilmeRepository filmeRepository;

    // 1. Rota para exibir a página de listagem (filmes.html)
    @GetMapping("/filmes")
    public String listarFilmes(Model model) {
        model.addAttribute("filmes", filmeRepository.findAll());
        return "filmes"; 
    }

    // 2. Rota para exibir o formulário de cadastro de novo filme (filme-form.html)
    @GetMapping("/filmes/novo")
    public String novoFilme(Model model) {
        model.addAttribute("filme", new Filme());
        return "filme-form"; 
    }

    // 3. Rota para carregar o formulário já preenchido para edição
    @GetMapping("/filmes/editar/{id}")
    public String editarFilme(@PathVariable Long id, Model model) {
        Filme filme = filmeRepository.findById(id).orElse(new Filme());
        model.addAttribute("filme", filme);
        return "filme-form";
    }
}