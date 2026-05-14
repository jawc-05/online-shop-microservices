/**
 * @author jawc
 */
package br.com.jawc.online.shop.repository;

import br.com.jawc.online.shop.domain.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IProductRepository extends MongoRepository<Product, String> {

    Optional<Product> findByCode(String code);

    Optional<Product> findByName(String name);
}
