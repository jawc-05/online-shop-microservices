/**
 * @author jawc
 */
package br.com.jawc.online.shop.controller;

import br.com.jawc.online.shop.domain.Product;
import br.com.jawc.online.shop.domain.Sale;
import br.com.jawc.online.shop.usecase.RegisterSale;
import br.com.jawc.online.shop.usecase.SearchSale;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/sale")
@RequiredArgsConstructor
@Tag(name = "Sale", description = "Endpoints for sale management")
public class SaleController {

    private final SearchSale searchSale;
    private final RegisterSale registerSale;

    @GetMapping
    @Operation(summary="Search for a paginated list of sales")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Returns the list of sales"),
            @ApiResponse(responseCode = "400", description = "sintax error or bad request",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, examples = @ExampleObject(value = "BAD_REQUEST"))),
            @ApiResponse(responseCode = "500", description = "An exception was made",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, examples = @ExampleObject(value = "INTERNAL_SERVER_ERROR"))),
    })
    public ResponseEntity<Page<Sale>> searchSales(Pageable pageable) {
        return ResponseEntity.ok(searchSale.searchSales(pageable));
    }

    @GetMapping(value = "/completed")
    @Operation(summary = "search for a paginated list of completed sales")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Returns the list of sales"),
            @ApiResponse(responseCode = "400", description = "sintax error or bad request",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, examples = @ExampleObject(value = "BAD_REQUEST"))),
            @ApiResponse(responseCode = "500", description = "An exception was made",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, examples = @ExampleObject(value = "INTERNAL_SERVER_ERROR"))),
    })
    public ResponseEntity<Page<Sale>> searchCompletedSales(Pageable pageable) {
        return ResponseEntity.ok(searchSale.searchAllCompletedSales(pageable));
    }

    @GetMapping(value = "/cancelled")
    @Operation(summary = "search for a paginated list of CANCELLED sales")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Returns the list of sales"),
            @ApiResponse(responseCode = "400", description = "sintax error or bad request",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, examples = @ExampleObject(value = "BAD_REQUEST"))),
            @ApiResponse(responseCode = "500", description = "An exception was made",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, examples = @ExampleObject(value = "INTERNAL_SERVER_ERROR"))),
    })
    public ResponseEntity<Page<Sale>> searchCancelledSales(Pageable pageable) {
        return ResponseEntity.ok(searchSale.searchAllCancelledSales(pageable));
    }

    @GetMapping(value = "/code/{code}")
    @Operation(summary = "search sale by code")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "returns the sale"),
            @ApiResponse(responseCode = "404", description = "sale not found")
    })
    public ResponseEntity<Sale> searchSaleByCode(String saleCode){
        return ResponseEntity.ok(searchSale.searchSaleByCode(saleCode));
    }


}
