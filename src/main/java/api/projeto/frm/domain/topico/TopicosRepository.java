package api.projeto.frm.domain.topico;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicosRepository extends JpaRepository <Topico, Long> {
    boolean existsByTituloAndMensagem(String titulo, String mensagem);
    boolean existsByTituloAndMensagemAndIdNot(String titulo, String Mensagem, Long id);

}
