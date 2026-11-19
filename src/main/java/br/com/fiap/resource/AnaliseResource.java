
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

    private final AnaliseBO analiseBO;

    public AnaliseResource(AnaliseBO analiseBO) {
        this.analiseBO = analiseBO;
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody @Valid AnaliseTO analise) {

            AnaliseTO resultado = analiseBO.save(analise);

            return ResponseEntity.status(HttpStatus.CREATED).header("Location", "/polieus/analise/"
                            + resultado.getIdAnalise()).body(resultado);
    }

    @GetMapping
    public ResponseEntity<?> findAll() {
        List<AnaliseTO> analises = analiseBO.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(analises);
    }

    @GetMapping("/{idAnalise}")
    public ResponseEntity<?> findById(@PathVariable Integer idAnalise) {
        AnaliseTO analise = analiseBO.findById(idAnalise);
        return ResponseEntity.status(HttpStatus.OK).body(analise);
    }

    @DeleteMapping("/{idAnalise}")
    public ResponseEntity<String> delete(@PathVariable Integer idAnalise) {
        analiseBO.delete(idAnalise);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Análise deletada com sucesso!");
    }


    @PutMapping("/{idAnalise}")
    public ResponseEntity<?> update(@PathVariable Integer idAnalise, @RequestBody @Valid AnaliseTO analise) {
            analise.setIdAnalise(idAnalise);
            AnaliseTO resultado = analiseBO.update(analise);
            return ResponseEntity.status(HttpStatus.OK).body(resultado);
    }
}
