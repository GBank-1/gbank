package br.gbank.gbank.resources;

import br.gbank.gbank.dto.ClienteCadastroDTO;
import br.gbank.gbank.dto.ClienteDTO;
import br.gbank.gbank.exception.ConstraintException;
import br.gbank.gbank.model.entity.Cliente;
import br.gbank.gbank.service.ClienteService;
import br.gbank.gbank.util.ApiUrlConstante;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@Api(value = "Clientes", tags = "clientes")
@RequestMapping(value = ApiUrlConstante.CLIENTES)
public class ClienteResource {

    @Autowired
    private ClienteService clienteService;

    @ApiOperation("Lista todos os clientes")
    @GetMapping
    public ResponseEntity<Page<ClienteDTO>> getAll(Pageable pageable) {
        return ResponseEntity.ok().body(clienteService.getAll(pageable).map(ClienteDTO::fromCliente));
    }

    @ApiOperation("Busca por id")
    @GetMapping("/{id}")
    public ResponseEntity<ClienteDTO> getId(@PathVariable Long id) {
        return ResponseEntity.ok().body(ClienteDTO.fromCliente(clienteService.getById(id)));
    }

    @ApiOperation("Cadastra um Cliente")
    @PostMapping
    public ResponseEntity<ClienteDTO> create(@Valid @RequestBody ClienteCadastroDTO clienteDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ConstraintException(bindingResult.getAllErrors().get(0).getDefaultMessage());
        }
        Cliente cliente = clienteService.create(clienteDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{codigo}").buildAndExpand(cliente.getId()).toUri();
        return ResponseEntity.created(uri).body(ClienteDTO.fromCliente(cliente));
    }
}
