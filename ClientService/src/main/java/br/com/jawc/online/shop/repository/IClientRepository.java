/**
 * @author jawc
 */
package br.com.jawc.online.shop.repository;

import br.com.jawc.online.shop.domain.Client;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IClientRepository extends MongoRepository<Client, String> {

    Optional<Client> findByCpf(String cpf);
}
