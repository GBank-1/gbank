package br.gbank.gbank.resources;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.gbank.gbank.dto.ClienteCadastroDTO;
import br.gbank.gbank.dto.ClienteDTO;
import br.gbank.gbank.exception.HandleException;
import br.gbank.gbank.model.entity.Cliente;
import br.gbank.gbank.service.ClienteService;
import br.gbank.gbank.util.ApiUrlConstante;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
@RestController
@Api(value = "Clientes", tags = "clientes")
@RequestMapping(value = ApiUrlConstante.CLIENTES)
public class ClienteResource {

    @Autowired
    private ClienteService clienteService;

    @ApiOperation("Lista todos os clientes")
    @GetMapping
    public ResponseEntity<Page<ClienteDTO>> getAll(Pageable pageable) {
        Page<ClienteDTO> list = clienteService.getAll(pageable);
        return list.isEmpty()?ResponseEntity.noContent().build():ResponseEntity.ok().body(list);
    }

    @ApiOperation("Busca um cliente por")
    @GetMapping("/:id")
    public ResponseEntity<ClienteDTO> getId(Long id) {
        Cliente cliente = clienteService.getById(id);
        return ResponseEntity.ok().body(ClienteDTO.fromCliente(cliente));
    }

    @ApiOperation("Cadastra um Cliente")
    @PostMapping
    public ResponseEntity create(@RequestBody ClienteCadastroDTO clienteDTO) {
        try {
            Cliente cliente = clienteService.create(clienteDTO);
            URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{codigo}").buildAndExpand(cliente.getId()).toUri();
            return ResponseEntity.created(uri).body(cliente);
        } catch(HandleException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch(Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }
}
