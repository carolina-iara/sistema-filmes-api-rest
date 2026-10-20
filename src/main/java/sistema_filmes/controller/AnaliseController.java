package sistema_filmes.controller;

import sistema_filmes.model.Analise;
import sistema_filmes.model.Filme;
import sistema_filmes.repository.AnaliseRepository;
import sistema_filmes.repository.FilmeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/analises")
public class AnaliseController {

    @Autowired
    private AnaliseRepository analiseRepository;

    @Autowired
    private FilmeRepository filmeRepository;

    // 1. GET: Listar todas as análises cadastradas
    @GetMapping
    public List<Analise> listarTodas() {
        return analiseRepository.findAll();
    }

    // 2. POST: Adicionar uma análise vinculada a um filme (passando o ID do filme na URL)
    @PostMapping("/filme/{filmeId}")
    public ResponseEntity<Analise> criarAnalise(@PathVariable Long filmeId, @RequestBody Analise analise) {
        return filmeRepository.findById(filmeId).map(filme -> {
            analise.setFilme(filme);
            Analise novaAnalise = analiseRepository.save(analise);
            return ResponseEntity.ok().body(novaAnalise);
        }).orElse(ResponseEntity.notFound().build());
    }

    // 3. DELETE: Remover uma análise pelo ID dela
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletarAnalise(@PathVariable Long id) {
        return analiseRepository.findById(id).map(analise -> {
            analiseRepository.delete(analise);
            return ResponseEntity.noContent().build();
        }).orElse(ResponseEntity.notFound().build());
    }
}