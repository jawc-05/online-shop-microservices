/**
 * @author jawc
 */
package br.com.jawc.online.shop.usecase;

import br.com.jawc.online.shop.domain.Product;
import br.com.jawc.online.shop.domain.Sale;
import br.com.jawc.online.shop.dto.SaleDTO;
import br.com.jawc.online.shop.exception.EntityNotFoundException;
import br.com.jawc.online.shop.integration.IClientService;
import br.com.jawc.online.shop.integration.IProductService;
import br.com.jawc.online.shop.repository.ISaleRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashSet;

@Service
public class RegisterSale {
    private final ISaleRepository saleRepository;
    private final IProductService productService;
    private final IClientService clientService;

    @Autowired
    public RegisterSale(ISaleRepository saleRepository,  IProductService productService, IClientService clientService) {
        this.saleRepository = saleRepository;
        this.productService = productService;
        this.clientService = clientService;
    }

    public Sale registerSale(@Valid SaleDTO saleDTO) {
        validateCostumer(saleDTO.getClientId());

        Sale sale= Sale.builder()
                .code(saleDTO.getCode())
                .clientId(saleDTO.getClientId())
                .saleDate(saleDTO.getSaleDate())
                .status(Sale.Status.STARTED)
                .items(new HashSet<>())
                .totalValue(BigDecimal.ZERO)
                .build();
        sale.recalculateTotalValue();

        return saleRepository.insert(sale);
    }

    private void validateCostumer(@NotNull String clientId) {
        Boolean isRegistered = clientService.isCustomerRegistered(clientId);
        if(isRegistered == null || !isRegistered) {
            throw new EntityNotFoundException(Sale.class,"clientId",  clientId);
        }
    }

    public Sale addProductToSale(@Valid Integer quantity, String productCode, String saleCode) {
       Sale sale = searchSale(saleCode);
       Product product = searchProduct(productCode);

        sale.validateStatus();
        sale.addProduct(product.getCode(), quantity,  product.getPrice());



        return saleRepository.save(sale);
    }

    private Sale searchSale(String saleCode) {
        return saleRepository.findByCode(saleCode)
                .orElseThrow(() -> new EntityNotFoundException(Sale.class, "saleCode", saleCode));
    }

    private Product searchProduct(String productCode) {
        Product prod = productService.searchProductByCode(productCode);
        if (prod == null) {
            throw new EntityNotFoundException(Product.class, "code", productCode);
        }
        return prod;
    }
}
