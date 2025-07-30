package api.projeto.frm.domain.topico;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Table(name= "topicos")
@Entity(name = "Topico")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Topico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String mensagem;

    @Column(name = "dataDeCriacao", nullable = false, updatable = false)
    private LocalDateTime dataDeCriacao = LocalDateTime.now();

    private String autor;
    private String curso;

    @Enumerated(EnumType.STRING)
    private Status status = Status.NAO_RESPONDIDO;

    public Topico(DadosCadastroTopico dados) {
        this.titulo = dados.titulo();
        this.mensagem = dados.mensagem();
        this.dataDeCriacao = LocalDateTime.now();
        this.autor = dados.autor();
        this.curso = dados.curso();
    }

    public void atualizarDados(DadosAtualizadosTopico dados) {
        if (dados.titulo() != null){
            this.titulo = dados.titulo();
        }
        if (dados.mensagem() != null) {
            this.mensagem = dados.mensagem();
        }
        if (dados.autor() != null){
            this.autor = dados.autor();
        }
        if (dados.curso() != null){
            this.curso = dados.curso();
        }
    }
    public void verificarDuplicidade(TopicosRepository repository, DadosAtualizadosTopico dados) {
        if (dados.titulo() != null && dados.mensagem() != null) {
            boolean duplicado = repository.existsByTituloAndMensagemAndIdNot(dados.titulo(), dados.mensagem(), this.id);
            if (duplicado) {
                throw new IllegalArgumentException("Outro tópico com o mesmo título e mensagem já existe.");
            }
        }
    }

    public void excluir(TopicosRepository repository) {
        if (!repository.existsById(this.id)) {
            throw new IllegalArgumentException("Tópico não encontrado.");
        }

        repository.deleteById(this.id);
    }

}
