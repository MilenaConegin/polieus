
package br.com.fiap.resource;

import br.com.fiap.bo.HistoricoBO;
import br.com.fiap.to.HistoricoTO;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Map;

@RestController
@RequestMapping("/polieus/historico")
public class HistoricoResource {

    private final HistoricoBO historicoBO;

    public HistoricoResource(HistoricoBO historicoBO) {
        this.historicoBO = historicoBO;
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody @Valid HistoricoTO historico) {

            HistoricoTO resultado = historicoBO.save(historico);

            return ResponseEntity.status(HttpStatus.CREATED).header("Location", "/polieus/historico/"
                            + resultado.getIdHistorico()).body(resultado);
    }

    @GetMapping
    public ResponseEntity<?> findAll() {
        ArrayList<HistoricoTO> historicos = historicoBO.findAll();
        return  ResponseEntity.status(HttpStatus.OK).body(historicos);
    }

    @GetMapping("/{idHistorico}")
    public ResponseEntity<?> findById(@PathVariable Integer idHistorico) {
        HistoricoTO historico = historicoBO.findById(idHistorico);
        return ResponseEntity.status(HttpStatus.OK).body(historico);

    }

    @GetMapping("/ocorrencia/{idOcorrencia}")
    public ResponseEntity<?> findByOcorrencia(@PathVariable Integer idOcorrencia) {
        ArrayList<HistoricoTO> historicos = historicoBO.findByOcorrencia(idOcorrencia);
        return ResponseEntity.status(HttpStatus.OK).body(historicos);
    }

    @DeleteMapping("/{idHistorico}")
    public ResponseEntity<?> delete(@PathVariable Integer idHistorico) {
        historicoBO.delete(idHistorico);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Histórico deletado com sucesso!");
    }

    @PutMapping("/{idHistorico}")
    public ResponseEntity<?> update(@PathVariable Integer idHistorico, @RequestBody @Valid HistoricoTO historico) {
            historico.setIdHistorico(idHistorico);
            HistoricoTO resultado = historicoBO.update(historico);
            return ResponseEntity.status(HttpStatus.OK).body(resultado);
    }

    @PutMapping("/ocorrencia/{idOcorrencia}/status")
    public ResponseEntity<?> updateStatus(@PathVariable Integer idOcorrencia, @RequestBody HistoricoTO historico) {
            HistoricoTO resultado = historicoBO.updateStatus(idOcorrencia, historico.getStatus());
            return ResponseEntity.status(HttpStatus.OK).body(resultado);
    }
}
