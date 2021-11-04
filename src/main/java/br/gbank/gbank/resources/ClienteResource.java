package br.gbank.gbank.resources;

import br.gbank.gbank.dto.ClienteDTO;
import br.gbank.gbank.model.Cliente;
import br.gbank.gbank.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteResource {

    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public ResponseEntity<Page<ClienteDTO>> getAll(Pageable pageable) {
        Page<ClienteDTO> list = clienteService.getAll(pageable);
        return list.isEmpty()?ResponseEntity.noContent().build():ResponseEntity.ok().body(list);
    }

    @PostMapping
    public ResponseEntity<Cliente> create(@RequestBody ClienteDTO clienteDTO) {
        Cliente cliente = clienteService.create(clienteDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{codigo}").buildAndExpand(cliente.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }
}
