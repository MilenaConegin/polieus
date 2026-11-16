
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

    private RelatorioBO relatorioBO = new RelatorioBO();

    @PostMapping
    public ResponseEntity<?> save(@RequestBody @Valid RelatorioTO relatorio){
        try {
            RelatorioTO resultado = relatorioBO.save(relatorio);
            return ResponseEntity.status(HttpStatus.CREATED).body(resultado);
        }catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Erro ao salvar relatório");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno ao salvar relatório");
        }
    }

    @GetMapping
    public ResponseEntity<List<RelatorioTO>> findAll(){
        List<RelatorioTO> relatorios = relatorioBO.findAll();
        if (relatorios != null){
            return ResponseEntity.status(HttpStatus.OK).body(relatorios);
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(relatorios);
        }
    }

    @GetMapping("/{idRelatorio}")
    public ResponseEntity<?> findById(@PathVariable Integer idRelatorio){
        RelatorioTO relatorio = relatorioBO.findById(idRelatorio);
        if (relatorio != null){
            return ResponseEntity.status(HttpStatus.OK).body(relatorio);
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Relatório não encontrado!");
        }
    }

    @DeleteMapping("/{idRelatorio}")
    public ResponseEntity<String> delete(@PathVariable Integer idRelatorio){
        if (relatorioBO.delete(idRelatorio)){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Relatório deletado com sucesso!");
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Relatório não encontrado!");
        }
    }

    @PutMapping("/{idRelatorio}")
    public ResponseEntity<?> update(@PathVariable Integer idRelatorio, @RequestBody @Valid RelatorioTO relatorio){
        try{
            relatorio.setIdRelatorio(idRelatorio);
            RelatorioTO resultado = relatorioBO.update(relatorio);
            return ResponseEntity.status(HttpStatus.CREATED).body(resultado);

        }catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Erro ao atualizar relatório");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno ao atualizar relatório");
        }
    }
}
