/**
 * @author jawc
 */
package br.com.jawc.online.shop.controller;

import br.com.jawc.online.shop.domain.Client;
import br.com.jawc.online.shop.usecase.RegisterClient;
import br.com.jawc.online.shop.usecase.SearchClient;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/client")
@RequiredArgsConstructor
@Tag(name = "Client", description = "Endpoints for client management")
public class ClientController {

    private final SearchClient searchClient;
    private final RegisterClient registerClient;

    @GetMapping
    @Operation(summary="Search for a paginated list of clients")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Returns the list of clients"),
            @ApiResponse(responseCode = "400", description = "sintax error or bad request",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, examples = @ExampleObject(value = "BAD_REQUEST"))),
            @ApiResponse(responseCode = "500", description = "An exception was made",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, examples = @ExampleObject(value = "INTERNAL_SERVER_ERROR"))),
    })
    public ResponseEntity<Page<Client>> searchClients(Pageable pageable) {
        return ResponseEntity.ok(searchClient.searchClients(pageable));
    }

    @GetMapping(value = "/{id}")
    @Operation(summary = "search client by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Client found"),
            @ApiResponse(responseCode = "404", description = "Client not found")
    })
    public ResponseEntity<Client> searchClientById(@PathVariable(value = "id", required = true) String id) {
        return ResponseEntity.ok(searchClient.searchClientById(id));
    }

    @GetMapping(value = "/cpf/{cpf}")
    @Operation(summary = "search client by CPF")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Client found"),
            @ApiResponse(responseCode = "404", description = "Client not found")
    })
    public ResponseEntity<Client> searchClientByCPF(@PathVariable(value = "cpf", required = true)String cpf) {
        return ResponseEntity.ok(searchClient.searchClientByCpf(cpf));
    }

    @GetMapping(value = "/exists/{id}")
    @Operation(summary = "check if the client exists")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Client is registered"),
            @ApiResponse(responseCode = "404", description = "Client is not registered")
    })
    public ResponseEntity<Boolean> exists(@PathVariable(value = "id", required = true)String id){
        return ResponseEntity.ok(searchClient.exists(id));
    }
}
