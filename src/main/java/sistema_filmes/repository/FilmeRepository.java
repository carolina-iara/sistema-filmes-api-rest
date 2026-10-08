package sistema_filmes.repository;

import sistema_filmes.model.Analise;
import sistema_filmes.model.Filme;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class FilmeRepository {

    private final List<Filme> filmes = new ArrayList<>();

    private Long proximoIdFilme = 1L;
    private Long proximoIdAnalise = 1L;

    public List<Filme> listarTodos() {
        return filmes;
    }

    public Filme buscarPorId(Long id) {
        for (Filme filme : filmes) {
            if (filme.getId().equals(id)) {
                return filme;
            }
        }

        return null;
    }

    public Filme salvar(Filme filme) {
        filme.setId(proximoIdFilme);
        proximoIdFilme++;

        filmes.add(filme);

        return filme;
    }

    public Analise adicionarAnalise(Filme filme, String texto, int nota) {

        Analise analise = new Analise();

        analise.setId(proximoIdAnalise);
        proximoIdAnalise++;

        analise.setFilme(filme);
        analise.setAnalise(texto);
        analise.setNota(nota);

        filme.getAnalises().add(analise);

        return analise;
    }
}