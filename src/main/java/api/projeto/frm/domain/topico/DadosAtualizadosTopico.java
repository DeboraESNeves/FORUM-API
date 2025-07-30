package api.projeto.frm.domain.topico;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DadosAtualizadosTopico(
        @NotNull
        Long id,
        String titulo,
        String mensagem,
        String autor,
        String curso,
        LocalDateTime dataDeCriacao) {
}
