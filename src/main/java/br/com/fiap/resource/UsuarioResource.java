
package br.com.fiap.resource;

import br.com.fiap.bo.UsuarioBO;
import br.com.fiap.to.UsuarioTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/polieus/usuario")
public class UsuarioResource {

    private UsuarioBO usuarioBO = new UsuarioBO();

    @PostMapping
    public ResponseEntity<?> save(@RequestBody @Valid UsuarioTO usuario) {
        try {
            UsuarioTO resultado = usuarioBO.save(usuario);
            return ResponseEntity.status(HttpStatus.CREATED).body(resultado);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Erro ao salvar usuário");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno ao salvar usuário");
        }
    }

    @GetMapping
    public ResponseEntity<List<UsuarioTO>> findAll() {
        List<UsuarioTO> usuarios = usuarioBO.findAll();
        if (usuarios != null) {
            return ResponseEntity.status(HttpStatus.OK).body(usuarios);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(usuarios);
        }
    }

    @GetMapping("/{idUsuario}")
    public ResponseEntity<?> findById(@PathVariable Integer idUsuario) {
        UsuarioTO usuario = usuarioBO.findById(idUsuario);
        if (usuario != null) {
            return ResponseEntity.status(HttpStatus.OK).body(usuario);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado!");
        }
    }

    @DeleteMapping("/{idUsuario}")
    public ResponseEntity<String> delete(@PathVariable Integer idUsuario) {
        try {
            if (usuarioBO.delete(idUsuario)) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Usuário deletado com sucesso!");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado!");
            }
        }catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }  catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno ao excluir histórico");
        }
    }

    @PutMapping("/{idUsuario}")
    public ResponseEntity<?> update(@PathVariable Integer idUsuario, @RequestBody @Valid UsuarioTO usuario) {
        try {
            usuario.setIdUsuario(idUsuario);
            UsuarioTO resultado = usuarioBO.update(usuario);

            if (resultado != null){
                return ResponseEntity.status(HttpStatus.OK).body(resultado);
            }else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado.");
            }
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Erro ao atualizar usuário");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno ao atualizar usuário");
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UsuarioTO usuario) {

        try {
            UsuarioTO resultado = usuarioBO.login(usuario);
            if (resultado == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("E-mail ou senha incorretos.");
            }
            return ResponseEntity.status(HttpStatus.CREATED).body(resultado);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Não foi possível acessar sua conta neste momento.");
        }
    }
}
