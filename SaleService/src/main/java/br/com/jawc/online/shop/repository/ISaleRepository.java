/**
 * @author jawc
 */
package br.com.jawc.online.shop.repository;

import br.com.jawc.online.shop.domain.Product;
import br.com.jawc.online.shop.domain.Sale;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ISaleRepository extends MongoRepository<Sale, String> {

    Optional<Sale> findByCode(String code);

    Page<Sale> findAllByStatus(Pageable pageable, Sale.Status status);

}
