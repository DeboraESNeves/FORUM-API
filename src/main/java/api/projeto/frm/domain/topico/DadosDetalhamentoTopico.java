package api.projeto.frm.domain.topico;
//título, mensagem, data de criação, status, autor e curso

import java.time.LocalDateTime;

public record DadosDetalhamentoTopico(Long id,
                                      String titulo,
                                      String mensagem,
                                      LocalDateTime dataDeCriacao,
                                      String autor,
                                      String curso,
                                      Status status) {
    public DadosDetalhamentoTopico(Topico topico){
        this(
                topico.getId(),
                topico.getTitulo(),
                topico.getMensagem(),
                topico.getDataDeCriacao(),
                topico.getAutor(),
                topico.getCurso(),
                topico.getStatus()
        );
    }
}