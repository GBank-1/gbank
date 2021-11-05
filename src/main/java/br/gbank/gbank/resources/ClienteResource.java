package br.gbank.gbank.resources;

import br.gbank.gbank.dto.ClienteDTO;
import br.gbank.gbank.model.Cliente;
import br.gbank.gbank.model.Conta;
import br.gbank.gbank.service.ClienteService;
import br.gbank.gbank.service.ContaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
@Api
@RestController
@RequestMapping(value = "/clientes")
public class ClienteResource {

    @Autowired
    private ClienteService clienteService;
    @Autowired
    private ContaService contaService;

    @ApiOperation("Lista todos os clientes")
    @GetMapping
    public Page<Cliente> getAll(Pageable pageable) {
        return clienteService.getAll(pageable);
    }

    @ApiOperation("Cadastra um Cliente")
    @PostMapping
    public ResponseEntity<Cliente> create(@RequestBody ClienteDTO clienteDTO) {
        Cliente cliente = clienteService.create(clienteDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{codigo}").buildAndExpand(cliente.getId()).toUri();
        if (true) {
            contaService.create(cliente);
        }
        return ResponseEntity.created(uri).body(cliente);
    }
}
