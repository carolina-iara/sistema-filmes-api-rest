package sistema_filmes.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import sistema_filmes.model.Filme;
import sistema_filmes.repository.FilmeRepository;

@Controller
@RequestMapping("/filmes")
public class AnaliseController {

    private final FilmeRepository filmeRepository;

    public AnaliseController(FilmeRepository filmeRepository) {
        this.filmeRepository = filmeRepository;
    }

    @PostMapping("/{id}/analises")
    public String adicionarAnalise(
            @PathVariable Long id,
            @RequestParam String analise,
            @RequestParam int nota) {

        Filme filme = filmeRepository.buscarPorId(id);

        if (filme == null) {
            return "redirect:/filmes";
        }

        filmeRepository.adicionarAnalise(filme, analise, nota);

        return "redirect:/filmes/" + id;
    }
}