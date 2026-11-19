
package br.com.fiap.resource;

import br.com.fiap.bo.OcorrenciaBO;
import br.com.fiap.exception.RecursoNaoEncontradoException;
import br.com.fiap.to.OcorrenciaTO;
import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/polieus/ocorrencia")
public class OcorrenciaResource {

    private final OcorrenciaBO ocorrenciaBO;

    public OcorrenciaResource(OcorrenciaBO ocorrenciaBO) {
        this.ocorrenciaBO = ocorrenciaBO;
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody @Valid OcorrenciaTO ocorrencia) {

        OcorrenciaTO resultado = ocorrenciaBO.save(ocorrencia);

        return ResponseEntity.status(HttpStatus.CREATED).header("Location", "/polieus/ocorrencia/"
                        + resultado.getIdOcorrencia()).body(resultado);
    }

    @GetMapping
    public ResponseEntity<?> findAll() {
        List<OcorrenciaTO> ocorrencias = ocorrenciaBO.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(ocorrencias);

    }

    @GetMapping("/{idOcorrencia}")
    public ResponseEntity<?> findById(@PathVariable Integer idOcorrencia) {
        OcorrenciaTO ocorrencia = ocorrenciaBO.findById(idOcorrencia);
        return ResponseEntity.status(HttpStatus.OK).body(ocorrencia);
    }

    @GetMapping("/usuario/{idUsuario}")
    public ResponseEntity<?> findByUsuario(@PathVariable Integer idUsuario) {
        List<OcorrenciaTO> ocorrencias = ocorrenciaBO.findByUsuario(idUsuario);
        return ResponseEntity.status(HttpStatus.OK).body(ocorrencias);
    }

    @DeleteMapping("/{idOcorrencia}")
    public ResponseEntity<String> delete(@PathVariable Integer idOcorrencia) {
            if (ocorrenciaBO.delete(idOcorrencia)) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Ocorrência deletada com sucesso!");
            }
            throw new RecursoNaoEncontradoException("Ocorrência não encontrada!");
    }

    @PutMapping("/{idOcorrencia}")
    public ResponseEntity<?> update(@PathVariable Integer idOcorrencia, @RequestBody @Valid OcorrenciaTO ocorrencia) {

        ocorrencia.setIdOcorrencia(idOcorrencia);
        OcorrenciaTO resultado = ocorrenciaBO.update(ocorrencia);
        return ResponseEntity.status(HttpStatus.OK).body(resultado);
    }
}
