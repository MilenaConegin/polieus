package br.com.fiap.resource;

import br.com.fiap.bo.OcorrenciaBO;
import br.com.fiap.to.OcorrenciaTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/polieus/ocorrencia")
public class OcorrenciaResource {

    private OcorrenciaBO ocorrenciaBO = new OcorrenciaBO();

    @PostMapping
    public ResponseEntity<?> save(@RequestBody @Valid OcorrenciaTO ocorrencia){
        try {
            OcorrenciaTO resultado = ocorrenciaBO.save(ocorrencia);
            return ResponseEntity.status(HttpStatus.CREATED).body(resultado);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Erro ao salvar ocorrência");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno ao salvar ocorrência");
        }
    }

    @GetMapping
    public ResponseEntity<List<OcorrenciaTO>> findAll(){
        List<OcorrenciaTO> ocorrencias = ocorrenciaBO.findAll();
        if (ocorrencias != null){
            return  ResponseEntity.status(HttpStatus.OK).body(ocorrencias);
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ocorrencias);
        }
    }

    @GetMapping("/{idOcorrencia}")
    public ResponseEntity<?> findById(@PathVariable Integer idOcorrencia){
        OcorrenciaTO ocorrencia = ocorrenciaBO.findById(idOcorrencia);
        if (ocorrencia != null){
            return ResponseEntity.status(HttpStatus.OK).body(ocorrencia);
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Ocorrência não encontrada!");
        }
    }

    @DeleteMapping("/{idOcorrencia}")
    public ResponseEntity<String> delete(@PathVariable Integer idOcorrencia){
        if (ocorrenciaBO.delete(idOcorrencia)){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Ocorrência deletada com sucesso!");
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Ocorrência não encontrada!");
        }
    }

    @PutMapping("/{idOcorrencia}")
    public ResponseEntity<?> update(@PathVariable Integer idOcorrencia, @RequestBody @Valid OcorrenciaTO ocorrencia){
        try{
            ocorrencia.setIdOcorrencia(idOcorrencia);
            OcorrenciaTO resultado = ocorrenciaBO.update(ocorrencia);
            return ResponseEntity.status(HttpStatus.CREATED).body(resultado);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Erro ao atualizar ocorrência");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno ao atualizar ocorrência");
        }
    }




}
