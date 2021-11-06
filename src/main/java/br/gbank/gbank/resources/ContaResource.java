package br.gbank.gbank.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.gbank.gbank.dto.ContaDTO;
import br.gbank.gbank.model.entity.Conta;
import br.gbank.gbank.service.ContaService;
import br.gbank.gbank.util.ApiUrlConstante;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "Contas", tags = "contas")
@RestController
@RequestMapping(value = ApiUrlConstante.CONTAS)
public class ContaResource {

    @Autowired
    private ContaService contaService;

    @GetMapping
    @ApiOperation("Lista todas as contas")
    public ResponseEntity<Page<ContaDTO>> getAll(Pageable pageable) {
        Page<ContaDTO> list = contaService.getAll(pageable).map(ContaDTO::fromConta);
        return list.isEmpty()?ResponseEntity.noContent().build():ResponseEntity.ok().body(list);
    }

    @GetMapping("/:id")
    @ApiOperation("Buscar por id")
    public ResponseEntity<ContaDTO> getById(@RequestParam(name = "id") Long id) {
        Conta conta = contaService.getById(id);
        return ResponseEntity.ok().body(ContaDTO.fromConta(conta));
    }

    @GetMapping("/numero/:numero")
    @ApiOperation("Buscar por numero")
    public ResponseEntity<ContaDTO> getByNumero(@RequestParam(name = "numero")Long numero) {
        Conta conta = contaService.getByNumero(numero);
        return ResponseEntity.ok().body(ContaDTO.fromConta(conta));
    }
    
}
