package api.projeto.frm.domain.topico;

import java.time.LocalDateTime;

//título, mensagem, data de criação, status, autor e curso
public record DadosListagemTopico(Long id, String titulo, String mensagem, LocalDateTime dataDeCriacao, String autor, String curso) {

    public DadosListagemTopico(Topico topico){
        this(topico.getId(), topico.getTitulo(), topico.getMensagem(), topico.getDataDeCriacao(), topico.getAutor(), topico.getCurso());
    }
}
