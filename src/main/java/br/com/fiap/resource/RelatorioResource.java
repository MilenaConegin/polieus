
package br.com.fiap.resource;

import br.com.fiap.bo.RelatorioBO;
import br.com.fiap.to.RelatorioTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/polieus/relatorio")
public class RelatorioResource {

    private final RelatorioBO relatorioBO;

    public RelatorioResource(RelatorioBO relatorioBO) {
        this.relatorioBO = relatorioBO;
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody @Valid RelatorioTO relatorio){
            RelatorioTO resultado = relatorioBO.save(relatorio);

            return ResponseEntity.status(HttpStatus.CREATED)
                    .header("Location", "/polieus/relatorio/" + resultado.getIdRelatorio())
                    .body(resultado);
    }

    @GetMapping
    public ResponseEntity<List<RelatorioTO>> findAll(){
        List<RelatorioTO> relatorios = relatorioBO.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(relatorios);

    }

    @GetMapping("/{idRelatorio}")
    public ResponseEntity<?> findById(@PathVariable Integer idRelatorio){
        RelatorioTO relatorio = relatorioBO.findById(idRelatorio);
        return ResponseEntity.status(HttpStatus.OK).body(relatorio);
    }

    @DeleteMapping("/{idRelatorio}")
    public ResponseEntity<String> delete(@PathVariable Integer idRelatorio){

          relatorioBO.delete(idRelatorio);
          return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Relatório deletado com sucesso!");

    }

    @PutMapping("/{idRelatorio}")
    public ResponseEntity<?> update(@PathVariable Integer idRelatorio, @RequestBody @Valid RelatorioTO relatorio){
            relatorio.setIdRelatorio(idRelatorio);
            RelatorioTO resultado = relatorioBO.update(relatorio);
            return ResponseEntity.status(HttpStatus.OK).body(resultado);
    }
}
