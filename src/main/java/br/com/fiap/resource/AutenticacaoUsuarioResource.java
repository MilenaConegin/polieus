
package br.com.fiap.resource;

import br.com.fiap.bo.AutenticacaoUsuarioBO;
import br.com.fiap.to.AutenticacaoUsuarioTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/polieus/autenticacao/usuario")
public class AutenticacaoUsuarioResource {

    private AutenticacaoUsuarioBO autenticacaoUsuarioBO = new AutenticacaoUsuarioBO();

    @PostMapping
    public ResponseEntity<?> save(@RequestBody @Valid AutenticacaoUsuarioTO autenticacao) {
        try {
            AutenticacaoUsuarioTO resultado = autenticacaoUsuarioBO.save(autenticacao);
            return ResponseEntity.status(HttpStatus.CREATED).body(resultado);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Erro ao salvar autenticação");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno ao salvar autenticação");
        }
    }

    @GetMapping
    public ResponseEntity<List<AutenticacaoUsuarioTO>> findAll() {
        List<AutenticacaoUsuarioTO> autenticacoes = autenticacaoUsuarioBO.findAll();
        if (autenticacoes != null) {
            return ResponseEntity.status(HttpStatus.OK).body(autenticacoes);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(autenticacoes);
        }
    }

    @GetMapping("/{idAutenticacao}")
    public ResponseEntity<?> findById(@PathVariable Integer idAutenticacao) {
        AutenticacaoUsuarioTO autenticacao = autenticacaoUsuarioBO.findById(idAutenticacao);
        if (autenticacao != null) {
            return ResponseEntity.status(HttpStatus.OK).body(autenticacao);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Autenticação não encontrada!");
        }
    }

    @DeleteMapping("/{idAutenticacao}")
    public ResponseEntity<String> delete(@PathVariable Integer idAutenticacao) {
        if (autenticacaoUsuarioBO.delete(idAutenticacao)) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Autenticação deletada com sucesso!");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Autenticação não encontrada!");
        }
    }

    @PutMapping("/{idAutenticacao}")
    public ResponseEntity<?> update(@PathVariable Integer idAutenticacao, @RequestBody @Valid AutenticacaoUsuarioTO autenticacao) {

        try {
            autenticacao.setIdAutenticacao(idAutenticacao);
            AutenticacaoUsuarioTO resultado = autenticacaoUsuarioBO.update(autenticacao);
            return ResponseEntity.status(HttpStatus.CREATED).body(resultado);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Erro ao atualizar autenticação");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno ao atualizar autenticação");
        }
    }
}
