package br.gbank.gbank.resources;

import br.gbank.gbank.dto.TransferenciaDTO;
import br.gbank.gbank.exception.MethodNotValidException;
import br.gbank.gbank.service.TransferenciaService;
import br.gbank.gbank.util.ApiUrlConstante;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@Api(value = "Transferencias", tags = "transferencias")
@RestController
@RequestMapping(value = ApiUrlConstante.TRANSFERENCIAS)
public class TransferenciaResource {

    @Autowired
    private TransferenciaService transferenciaService;

    @ApiOperation("Realiza uma transferência")
    @PostMapping
    public ResponseEntity<Void> tranferir(@Valid @RequestBody TransferenciaDTO transferenciaDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new MethodNotValidException(bindingResult.getAllErrors().get(0).getDefaultMessage());
        }

        transferenciaService.transferir(transferenciaDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/contas/{codigo}/extratos")
                .buildAndExpand(transferenciaDTO.getContaDebitoId()).toUri();
        return ResponseEntity.created(uri).build();


    }
}
