
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

    private HistoricoBO historicoBO = new HistoricoBO();

    @PostMapping
    public ResponseEntity<?> save(@RequestBody @Valid HistoricoTO historico) {

        try {
            HistoricoTO resultado = historicoBO.save(historico);
            return ResponseEntity.status(HttpStatus.CREATED).body(resultado);
        }catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro interno ao cadastrar histórico");
        }
    }

    @GetMapping
    public ResponseEntity<?> findAll() {
        ArrayList<HistoricoTO> historicos = historicoBO.findAll();

        if(historicos != null){
          return  ResponseEntity.status(HttpStatus.OK).body(historicos);
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(historicos);
        }
    }

    @GetMapping("{idHistorico}")
    public ResponseEntity<?> findById(@PathVariable Integer idHistorico) {
        HistoricoTO historico = historicoBO.findById(idHistorico);
            if (historico != null) {
                return ResponseEntity.status(HttpStatus.OK).body(historico);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Histórico não encontrado!");
            }
    }

    @GetMapping("/ocorrencia/{idOcorrencia}")
    public ResponseEntity<?> findByOcorrencia(@PathVariable Integer idOcorrencia) {
        ArrayList<HistoricoTO> historicos = historicoBO.findByOcorrencia(idOcorrencia);
        if (historicos != null){
            return ResponseEntity.status(HttpStatus.OK).body(historicos);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(historicos);
        }
    }

    @DeleteMapping("/{idHistorico}")
    public ResponseEntity<?> delete(@PathVariable Integer idHistorico) {

        try {
            if (historicoBO.delete(idHistorico)){
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Histórico deletado com sucesso!");
            }else{
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Histórico não encontrado!");
            }
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno ao excluir histórico");
        }
    }

    @PutMapping("/{idHistorico}")
    public ResponseEntity<?> update(@PathVariable Integer idHistorico, @RequestBody @Valid HistoricoTO historico) {
        try {
            historico.setIdHistorico(idHistorico);
            HistoricoTO resultado = historicoBO.update(historico);

            if (resultado != null) {
                return ResponseEntity.status(HttpStatus.OK).body(resultado);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Histórico não encontrado.");
            }
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());

        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno ao atualizar histórico");
        }
    }

    @PutMapping("/ocorrencia/{idOcorrencia}/status")
    public ResponseEntity<?> updateStatus(@PathVariable Integer idOcorrencia, @RequestBody HistoricoTO historico) {

        try {
            historico.setIdOcorrencia(idOcorrencia);
            HistoricoTO resultado = historicoBO.updateStatus(idOcorrencia, historico.getStatus());

            if (resultado != null) {
                return ResponseEntity.status(HttpStatus.OK).body(resultado);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Histórico da ocorrência não encontrado.");
            }

        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(e.getMessage());

        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(e.getMessage());

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro interno ao atualizar status da ocorrência.");
        }
    }
}
