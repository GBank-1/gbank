package br.gbank.gbank.resources;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.gbank.gbank.dto.TransferenciaDTO;
import br.gbank.gbank.exception.ContaSemSaldoException;
import br.gbank.gbank.service.TransferenciaService;
import br.gbank.gbank.util.ApiUrlConstante;
import io.swagger.annotations.Api;

@Api(value = "Transferencias", tags = "transferencias")
@RestController
@RequestMapping(value = ApiUrlConstante.TRANSFERENCIAS)
public class TransferenciaResource {

    @Autowired
    private TransferenciaService transferenciaService;

    @PostMapping
    public ResponseEntity tranferir(@RequestBody TransferenciaDTO transferenciaDTO) {
        try {
            transferenciaService.transferir(transferenciaDTO);
            URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("contas/{codigo}/extratos")
                    .buildAndExpand(transferenciaDTO.getNumeroContaOrigem()).toUri();
            return ResponseEntity.created(uri).build();
        } catch (ContaSemSaldoException e) {
            return ResponseEntity.badRequest().build();
        }

    }
}
