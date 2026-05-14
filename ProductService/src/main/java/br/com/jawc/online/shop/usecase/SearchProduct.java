/**
 * @author jawc
 */
package br.com.jawc.online.shop.usecase;

import br.com.jawc.online.shop.domain.Product;
import br.com.jawc.online.shop.exception.EntityNotFoundException;
import br.com.jawc.online.shop.repository.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SearchProduct {

    private IProductRepository productRepository;

    @Autowired
    public SearchProduct(IProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Page<Product> searchAllProducts(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    public Page<Product> searchAllActiveProducts(Pageable pageable, Product.Status status) {
        return  productRepository.findAllByStatus(pageable, Product.Status.ACTIVE);
    }

    public Product searchProductById(String id) {
        return productRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException(Product.class, "id", id));
    }

    public Product searchProductByCode(String code) {
        return productRepository.findByCode(code)
                .orElseThrow(()-> new EntityNotFoundException(Product.class, "code", code));
    }

    public Product searchProductByName(String productName) {
        return productRepository.findByName(productName)
                .orElseThrow(()-> new EntityNotFoundException(Product.class, "name", productName));
    }

    public Page<Product> searchAllUnactiveProducts(Pageable pageable, Product.Status status) {
        return productRepository.findAllByStatus(pageable, Product.Status.UNACTIVE);
    }

    public Boolean exists(String id){
        return productRepository.existsById(id);
    }
}
