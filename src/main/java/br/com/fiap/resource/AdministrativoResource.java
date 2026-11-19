
package br.com.fiap.resource;

import br.com.fiap.bo.AdministrativoBO;
import br.com.fiap.to.AdministrativoTO;
import br.com.fiap.to.UsuarioTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/polieus/administrativo")
public class AdministrativoResource {

    private final AdministrativoBO administrativoBO;

    public AdministrativoResource(AdministrativoBO administrativoBO) {
        this.administrativoBO = administrativoBO;
    }
    @PostMapping
    public ResponseEntity<?> save(@Valid @RequestBody AdministrativoTO administrativo) {
            AdministrativoTO resultado = administrativoBO.save(administrativo);

            return ResponseEntity.status(HttpStatus.CREATED).header("Location", "/polieus/administrativo/"
                        + resultado.getIdAdmin()).body(resultado);
    }

    @GetMapping
    public ResponseEntity<List<AdministrativoTO>> findAll()  {
        List<AdministrativoTO> administrativos = administrativoBO.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(administrativos);
    }

    @GetMapping("/{idAdmin}")
    public ResponseEntity<?> findById(@PathVariable Integer idAdmin) {
        AdministrativoTO administradores = administrativoBO.findById(idAdmin);
        return ResponseEntity.status(HttpStatus.OK).body(administradores);


    }

    @DeleteMapping("/{idAdmin}")
    public ResponseEntity<String> delete(@PathVariable Integer idAdmin) {
        administrativoBO.delete(idAdmin);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Administrador deletado com sucesso!");

    }

    @PutMapping("/{idAdmin}")
    public ResponseEntity<?> update(@PathVariable Integer idAdmin ,@Valid @RequestBody AdministrativoTO administrativo) {
            administrativo.setIdAdmin(idAdmin);
            AdministrativoTO resultado = administrativoBO.update(administrativo);
            return ResponseEntity.status(HttpStatus.OK).body(resultado);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AdministrativoTO administrador) {
            AdministrativoTO resultado = administrativoBO.login(administrador);
            return ResponseEntity.status(HttpStatus.CREATED).body(resultado);
    }
}
