package api.projeto.frm.controller;

import api.projeto.frm.domain.topico.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Optional;

@RestController
@RequestMapping("/forum")
public class TopicoController {

    @Autowired
    private TopicosRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroTopico dados, UriComponentsBuilder uriBuilder){
        var topico = new Topico(dados);

        if (repository.existsByTituloAndMensagem(dados.titulo(), dados.mensagem())){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Tópico já existente!");
        }

        repository.save(topico);

        var uri = uriBuilder.path("/forum/{id}").buildAndExpand(topico.getId()).toUri();

        return ResponseEntity.created(uri).body(new DadosDetalhamentoTopico(topico));
    }
    @GetMapping
    public ResponseEntity<Page<DadosListagemTopico>> listar(@PageableDefault(size=10, sort ={"titulo"})Pageable paginacao){
        var page = repository.findAll(paginacao).map(DadosListagemTopico::new);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity detalhar(@PathVariable Long id){
        var topico = repository.getReferenceById(id);
        return ResponseEntity.ok(new DadosDetalhamentoTopico(topico));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity atualizar(@PathVariable Long id, @RequestBody @Valid DadosAtualizadosTopico dados){
        var topico = repository.getReferenceById(id);
        try{
            topico.verificarDuplicidade(repository, dados);
            topico.atualizarDados(dados);
        } catch (IllegalArgumentException e){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Tópico já existente!");
        }
        return ResponseEntity.ok(new DadosDetalhamentoTopico(topico));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluir(@PathVariable Long id){
        Optional<Topico> optional = repository.findById(id);
        if (optional.isPresent()){
            var topico = optional.get();
            topico.excluir(repository);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Topico não encontrado");
        }

        return ResponseEntity.noContent().build();
    }

}
