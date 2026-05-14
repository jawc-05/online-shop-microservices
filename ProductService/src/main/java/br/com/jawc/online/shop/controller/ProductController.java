/**
 * @author jawc
 */
package br.com.jawc.online.shop.controller;

import br.com.jawc.online.shop.domain.Product;
import br.com.jawc.online.shop.usecase.RegisterProduct;
import br.com.jawc.online.shop.usecase.SearchProduct;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/product")
@RequiredArgsConstructor
@Tag(name = "Product", description = "Endpoints for product management")
public class ProductController {

    private final SearchProduct searchProduct;
    private final RegisterProduct registerProduct;

    @GetMapping
    @Operation(summary="Search for a paginated list of products")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Returns the list of products"),
            @ApiResponse(responseCode = "400", description = "sintax error or bad request",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, examples = @ExampleObject(value = "BAD_REQUEST"))),
            @ApiResponse(responseCode = "500", description = "An exception was made",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, examples = @ExampleObject(value = "INTERNAL_SERVER_ERROR"))),
    })
    public ResponseEntity<Page<Product>> searchAllProducts(Pageable pageable) {
        return ResponseEntity.ok(searchProduct.searchAllProducts(pageable));
    }

    @GetMapping
    @Operation(summary="Search for a paginated list of active products")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Returns the list of products"),
            @ApiResponse(responseCode = "400", description = "sintax error or bad request",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, examples = @ExampleObject(value = "BAD_REQUEST"))),
            @ApiResponse(responseCode = "500", description = "An exception was made",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, examples = @ExampleObject(value = "INTERNAL_SERVER_ERROR"))),
    })
    public ResponseEntity<Page<Product>> searchAllActiveProducts(Pageable pageable, Product.Status status) {
        return ResponseEntity.ok(searchProduct.searchAllActiveProducts(pageable, status.ACTIVE));
    }

    @GetMapping
    @Operation(summary="Search for a paginated list of unactive products")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Returns the list of products"),
            @ApiResponse(responseCode = "400", description = "sintax error or bad request",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, examples = @ExampleObject(value = "BAD_REQUEST"))),
            @ApiResponse(responseCode = "500", description = "An exception was made",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, examples = @ExampleObject(value = "INTERNAL_SERVER_ERROR"))),
    })
    public ResponseEntity<Page<Product>> searchAllUnactiveProducts(Pageable pageable, Product.Status status) {
        return ResponseEntity.ok(searchProduct.searchAllUnactiveProducts(pageable, status.UNACTIVE));
    }

    @GetMapping(value = "/{id}")
    @Operation(summary = "search product by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "product FOUND"),
            @ApiResponse(responseCode = "404", description = "product NOT found")
    })
    public ResponseEntity<Product> searchProductById(@PathVariable (value = "id", required = true) String id) {
        return ResponseEntity.ok(searchProduct.searchProductById(id));
    }

    @GetMapping(value = "/code/{code}")
    @Operation(summary = "search product by code")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "product FOUND"),
            @ApiResponse(responseCode = "404", description = "product NOT found")
    })
    public ResponseEntity<Product> searchProductByCode(@PathVariable (value = "code", required = true) String code) {
        return ResponseEntity.ok(searchProduct.searchProductByCode(code));
    }

    @GetMapping(value = "/exists/{id}")
    @Operation(summary = "check if the product exists")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "the product EXIST"),
            @ApiResponse(responseCode = "404", description = "the product DOESNT EXIST")
    })
    public ResponseEntity<Boolean> exists(@PathVariable(value = "id", required = true) String id){
        return ResponseEntity.ok(searchProduct.exists(id));
    }

    @PostMapping
    @Operation(summary = "register a new product")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "product successfully registered"),
            @ApiResponse(responseCode = "400", description = "Validation error or duplicate key")
    })
    public ResponseEntity<Product> register(@RequestBody @Valid Product product) {
        return ResponseEntity.ok(registerProduct.register(product));
    }

    @PutMapping
    @Operation(summary = "updating a product")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "product successfully updated"),
            @ApiResponse(responseCode = "404", description = "prodcut not found")
    })
    public ResponseEntity<Product> update(@RequestBody @Valid Product product) {
        return ResponseEntity.ok(registerProduct.update(product));
    }

    @DeleteMapping(value = "/{id}")
    @Operation(summary = "'delete' product (unactive status)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "product successfully 'deleted'"),
            @ApiResponse(responseCode = "404", description = "product not found")
    })
    public ResponseEntity<Product> delete(@PathVariable(value = "id", required = true) String id) {
        return ResponseEntity.ok(registerProduct.delete(id));
    }
}

