
package br.com.fiap.resource;

import br.com.fiap.bo.UsuarioBO;
import br.com.fiap.exception.RecursoNaoEncontradoException;
import br.com.fiap.to.UsuarioTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/polieus/usuario")
public class UsuarioResource {

    private final UsuarioBO usuarioBO;

    public UsuarioResource(UsuarioBO usuarioBO) {
        this.usuarioBO = usuarioBO;
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody @Valid UsuarioTO usuario) {
        UsuarioTO resultado = usuarioBO.save(usuario);

        return ResponseEntity.status(HttpStatus.CREATED)
                .header("Location", "/polieus/usuario/" + resultado.getIdUsuario())
                .body(resultado);
    }

    @GetMapping
    public ResponseEntity<List<UsuarioTO>> findAll() {
        List<UsuarioTO> usuarios = usuarioBO.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(usuarios);

    }

    @GetMapping("/{idUsuario}")
    public ResponseEntity<?> findById(@PathVariable Integer idUsuario) {
        UsuarioTO usuario = usuarioBO.findById(idUsuario);
        return ResponseEntity.status(HttpStatus.OK).body(usuario);

    }

    @DeleteMapping("/{idUsuario}")
    public ResponseEntity<String> delete(@PathVariable Integer idUsuario) {

            if (usuarioBO.delete(idUsuario)) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Usuário deletado com sucesso!");
            }
            throw new RecursoNaoEncontradoException("Usuário não encontrado!");
    }

    @PutMapping("/{idUsuario}")
    public ResponseEntity<?> update(@PathVariable Integer idUsuario, @RequestBody @Valid UsuarioTO usuario) {
            usuario.setIdUsuario(idUsuario);
            UsuarioTO resultado = usuarioBO.update(usuario);
            return ResponseEntity.status(HttpStatus.OK).body(resultado);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UsuarioTO usuario) {
            UsuarioTO resultado = usuarioBO.login(usuario);
            return ResponseEntity.status(HttpStatus.CREATED).body(resultado);

    }
}
