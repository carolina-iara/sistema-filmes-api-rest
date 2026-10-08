package sistema_filmes.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import sistema_filmes.model.Filme;
import sistema_filmes.repository.FilmeRepository;

@Controller
@RequestMapping("/filmes")
public class FilmeController {

    private final FilmeRepository filmeRepository;

    public FilmeController(FilmeRepository filmeRepository) {
        this.filmeRepository = filmeRepository;
    }

    @GetMapping
    public String listarFilmes(Model model) {
        model.addAttribute("filmes", filmeRepository.listarTodos());

        return "filmes";
    }

    @GetMapping("/novo")
    public String novoFilme(Model model) {
        model.addAttribute("filme", new Filme());

        return "filme-form";
    }

    @PostMapping
    public String salvarFilme(@ModelAttribute Filme filme) {
        filmeRepository.salvar(filme);

        return "redirect:/filmes";
    }

    @GetMapping("/{id}")
    public String detalhesFilme(@PathVariable Long id, Model model) {

        Filme filme = filmeRepository.buscarPorId(id);

        if (filme == null) {
            return "redirect:/filmes";
        }

        model.addAttribute("filme", filme);

        return "filme-detalhes";
    }
}