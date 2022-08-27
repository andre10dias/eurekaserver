package io.github.cursodsouza.msclientes.application;

import io.github.cursodsouza.msclientes.application.representation.clienteSaveRequest;
import io.github.cursodsouza.msclientes.domain.Cliente;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/clientes")
@RequiredArgsConstructor
@Slf4j
public class ClienteResource {

    private final ClienteService service;

    @GetMapping
    public String status() {
        log.info("Obtendo o status do microservice de clientes");
        return "ok";
    }

    @GetMapping(params = "cpf")
    public ResponseEntity dadosCliente(@RequestParam("cpf") String cpf) {
        Optional<Cliente> cliente = service.getByCpf(cpf);
        if (cliente.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(cliente);
    }

    @PostMapping
    public ResponseEntity save(@RequestBody clienteSaveRequest request) {
        Cliente cliente = request.toModel();
        service.save(cliente);

        /**
         * ServletUriComponentsBuilder - constrói urls dinâmicas, a partir
         * da url corrente.
         *
         * Exemplo:
         * url corrente: http://localhost:PORT/
         * url gerada: http://localhost:PORT/clientes?cpf=01234567890
        * */
        URI headerLocation = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .query("cpf={cpf}")
                .buildAndExpand(cliente.getCpf())
                .toUri();

        return ResponseEntity.created(headerLocation).build();
    }

}
