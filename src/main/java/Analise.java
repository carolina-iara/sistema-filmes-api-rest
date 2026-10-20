package sistema_filmes.model;

import jakarta.persistence.*;

@Entity
@Table(name = "analise")
public class Analise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome_usuario", nullable = false)
    private String nomeUsuario;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String comentario;

    @Column(nullable = false)
    private int nota;

    @ManyToOne
    @JoinColumn(name = "filme_id", nullable = false)
    private Filme filme;

    // Construtor vazio obrigatório para o JPA/Hibernate
    public Analise() {
    }

    public Analise(Long id, String nomeUsuario, String comentario, int nota, Filme filme) {
        this.id = id;
        this.nomeUsuario = nomeUsuario;
        this.comentario = comentario;
        this.nota = nota;
        this.filme = filme;
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    // Atalho compatível caso algum trecho do código chame setAnalise
    public void setAnalise(String comentario) {
        this.comentario = comentario;
    }

    public int getNota() {
        return nota;
    }

    public void setNota(int nota) {
        this.nota = nota;
    }

    public Filme getFilme() {
        return filme;
    }

    public void setFilme(Filme filme) {
        this.filme = filme;
    }
}