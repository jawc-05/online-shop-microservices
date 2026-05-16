/**
 * @author jawc
 */
package br.com.jawc.online.shop.usecase;

import br.com.jawc.online.shop.domain.Product;
import br.com.jawc.online.shop.domain.Sale;
import br.com.jawc.online.shop.exception.EntityNotFoundException;
import br.com.jawc.online.shop.integration.IProductService;
import br.com.jawc.online.shop.repository.ISaleRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class RegisterSale {
    private final ISaleRepository saleRepository;
    private final IProductService productService;

    @Autowired
    public RegisterSale(ISaleRepository saleRepository,  IProductService productService) {
        this.saleRepository = saleRepository;
        this.productService = productService;
    }

    public Sale registerSale(@Valid Sale sale) {
        sale.setStatus(Sale.Status.STARTED);
        return saleRepository.insert(sale);
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
