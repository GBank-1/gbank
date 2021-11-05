package br.gbank.gbank.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import br.gbank.gbank.dto.ContaDTO;
import br.gbank.gbank.model.Conta;
import br.gbank.gbank.service.ContaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api
@RestController
@RequestMapping(value = "/contas")
public class ContaResource {

    @Autowired
    private ContaService contaService;

    @GetMapping
    @ApiOperation("Lista todas as contas")
    public ResponseEntity<Page<ContaDTO>> getAll(Pageable pageable) {
        Page<ContaDTO> list = contaService.getAll(pageable);
        return list.isEmpty()?ResponseEntity.noContent().build():ResponseEntity.ok().body(list);
    }
    
}
