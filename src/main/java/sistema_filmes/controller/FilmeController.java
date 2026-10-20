package sistema_filmes.controller;

import sistema_filmes.model.Filme;
import sistema_filmes.repository.FilmeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/filmes")
public class FilmeController {

    @Autowired
    private FilmeRepository filmeRepository;

    // 1. GET: Listar todos os filmes (Retorna JSON)
    @GetMapping
    public List<Filme> listarTodos() {
        return filmeRepository.findAll();
    }

    // 2. GET por ID: Buscar um filme específico
    @GetMapping("/{id}")
    public ResponseEntity<Filme> buscarPorId(@PathVariable Long id) {
        return filmeRepository.findById(id)
                .map(filme -> ResponseEntity.ok().body(filme))
                .orElse(ResponseEntity.notFound().build());
    }

    // 3. POST: Cadastrar um novo filme via JSON
    @PostMapping
    public Filme criarFilme(@RequestBody Filme filme) {
        return filmeRepository.save(filme);
    }

    // 4. PUT: Atualizar um filme existente
    @PutMapping("/{id}")
    public ResponseEntity<Filme> atualizarFilme(@PathVariable Long id, @RequestBody Filme filmeDetalhes) {
        return filmeRepository.findById(id).map(filme -> {
            filme.setTitulo(filmeDetalhes.getTitulo());
            filme.setSinopse(filmeDetalhes.getSinopse());
            filme.setGenero(filmeDetalhes.getGenero());
            filme.setAnoLancamento(filmeDetalhes.getAnoLancamento());
            Filme atualizado = filmeRepository.save(filme);
            return ResponseEntity.ok().body(atualizado);
        }).orElse(ResponseEntity.notFound().build());
    }

    // 5. DELETE: Remover um filme
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletarFilme(@PathVariable Long id) {
        return filmeRepository.findById(id).map(filme -> {
            filmeRepository.delete(filme);
            return ResponseEntity.noContent().build();
        }).orElse(ResponseEntity.notFound().build());
    }
}