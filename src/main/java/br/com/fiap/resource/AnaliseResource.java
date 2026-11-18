
package br.com.fiap.resource;

import br.com.fiap.bo.AnaliseBO;
import br.com.fiap.to.AnaliseTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/polieus/analise")
public class AnaliseResource {

    private AnaliseBO analiseBO = new AnaliseBO();

    @PostMapping
    public ResponseEntity<?> save(@RequestBody @Valid AnaliseTO analise) {
        try {
            AnaliseTO resultado = analiseBO.save(analise);
            return ResponseEntity.status(HttpStatus.CREATED).body(resultado);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Erro ao salvar análise");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno ao salvar análise");
        }
    }

    @GetMapping
    public ResponseEntity<List<AnaliseTO>> findAll() {
        List<AnaliseTO> analises = analiseBO.findAll();
        if (analises != null) {
            return ResponseEntity.status(HttpStatus.OK).body(analises);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(analises);
        }
    }

    @GetMapping("/{idAnalise}")
    public ResponseEntity<?> findById(@PathVariable Integer idAnalise) {
        AnaliseTO analise = analiseBO.findById(idAnalise);
        if (analise != null) {
            return ResponseEntity.status(HttpStatus.OK).body(analise);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Análise não encontrada!");
        }
    }

    @DeleteMapping("/{idAnalise}")
    public ResponseEntity<String> delete(@PathVariable Integer idAnalise) {
        try {
            if (analiseBO.delete(idAnalise)) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Análise deletada com sucesso!");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Análise não encontrada!");
            }
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno ao excluir ocorrência");
        }
    }

    @PutMapping("/{idAnalise}")
    public ResponseEntity<?> update(@PathVariable Integer idAnalise, @RequestBody @Valid AnaliseTO analise) {
        try {
            analise.setIdAnalise(idAnalise);
            AnaliseTO resultado = analiseBO.update(analise);

            if (resultado != null){
                return ResponseEntity.status(HttpStatus.OK).body(resultado);
            }else{
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Análise não encontrada!");
            }
        }catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Erro ao atualizar análise");
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno ao atualizar análise");
        }
    }
}
