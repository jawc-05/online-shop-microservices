/**
 * @author jawc
 */
package br.com.jawc.online.shop.usecase;

import br.com.jawc.online.shop.domain.Product;
import br.com.jawc.online.shop.exception.EntityNotFoundException;
import br.com.jawc.online.shop.repository.IProductRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegisterProduct {

    private final IProductRepository productRepository;

    @Autowired
    public RegisterProduct(IProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product register(@Valid  Product product) {
        product.setStatus(Product.Status.ACTIVE);
        return this.productRepository.insert(product);
    }

    public Product update(@Valid Product product) {
        return this.productRepository.save(product);
    }

    public Product delete(String id) {
        Product prod = productRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException(Product.class, "id", id));
        prod.setStatus(Product.Status.UNACTIVE);
        return this.productRepository.save(prod);
    }
}
