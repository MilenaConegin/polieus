
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

    private AdministrativoBO administrativoBO = new AdministrativoBO();

    @PostMapping
    public ResponseEntity<?> save(@Valid @RequestBody AdministrativoTO administrativo) {
        try {
            AdministrativoTO resultado = administrativoBO.save(administrativo);
            return ResponseEntity.status(HttpStatus.CREATED).body(resultado);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno ao salvar administrador");
        }
    }

    @GetMapping
    public ResponseEntity<List<AdministrativoTO>> findAll()  {
        List<AdministrativoTO> administrativos = administrativoBO.findAll();
        if (administrativos != null){
            return ResponseEntity.status(HttpStatus.OK).body(administrativos);
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(administrativos);
        }
    }

    @GetMapping("/{idAdmin}")
    public ResponseEntity<?> findById(@PathVariable Integer idAdmin) {
        AdministrativoTO administradores = administrativoBO.findById(idAdmin);
        if (administradores != null) {
            return ResponseEntity.status(HttpStatus.OK).body(administradores);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Administrador não encontrado!");
        }

    }

    @DeleteMapping("/{idAdmin}")
    public ResponseEntity<String> delete(@PathVariable Integer idAdmin) {
        if (administrativoBO.delete(idAdmin)){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Administrador deletado com sucesso!");
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Administrador não encontrado!");
        }
    }

    @PutMapping("/{idAdmin}")
    public ResponseEntity<?> update(@PathVariable Integer idAdmin ,@Valid @RequestBody AdministrativoTO administrativo) {
        try {
            administrativo.setIdAdmin(idAdmin);
            AdministrativoTO resultado = administrativoBO.update(administrativo);
            return ResponseEntity.status(HttpStatus.CREATED).body(resultado);
        }catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Erro ao atualizar administrador");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno ao atualizar administrador");
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AdministrativoTO administrador) {

        try {
            AdministrativoTO resultado = administrativoBO.login(administrador);
            if (resultado == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("E-mail ou senha incorretos.");
            }
            return ResponseEntity.status(HttpStatus.OK).body(resultado);

        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Erro ao atualizar usuário");
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Não foi possível acessar sua conta neste momento.");
        }
    }
}
