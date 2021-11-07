package br.gbank.gbank.resources;

import br.gbank.gbank.dto.ContaDTO;
import br.gbank.gbank.model.entity.Conta;
import br.gbank.gbank.service.ContaService;
import br.gbank.gbank.util.ApiUrlConstante;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Api(value = "Contas", tags = "contas")
@RestController
@RequestMapping(value = ApiUrlConstante.CONTAS)
public class ContaResource {

    @Autowired
    private ContaService contaService;

    @GetMapping
    @ApiOperation("Lista todas as contas")
    public ResponseEntity<Page<ContaDTO>> getAll(Pageable pageable) {
        return ResponseEntity.ok().body(contaService.getAll(pageable).map(ContaDTO::fromConta));
    }

    @GetMapping("/{id}")
    @ApiOperation("Busca por id")
    public ResponseEntity<ContaDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok().body(ContaDTO.fromConta(contaService.getById(id)));
    }

    @GetMapping("/numero/{numero}")
    @ApiOperation("Busca pelo número da conta")
    public ResponseEntity<ContaDTO> getByNumero(@PathVariable Long numero) {
        Conta conta = contaService.getByNumero(numero);
        return ResponseEntity.ok().body(ContaDTO.fromConta(conta));
    }

    @PutMapping("/{id}/enable")
    @ApiOperation("Atualiza o estado de ativação da conta")
    public ResponseEntity<Void> updateAtiva(@PathVariable Long id) {
        contaService.updateAtiva(id);
        return ResponseEntity.noContent().build();
    }
}
