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
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
