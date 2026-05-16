/**
 * @author jawc
 */
package br.com.jawc.online.shop.usecase;

import br.com.jawc.online.shop.domain.Client;
import br.com.jawc.online.shop.repository.IClientRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegisterClient {
    private final IClientRepository clientRepository;

    @Autowired
    public RegisterClient(IClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public Client register(@Valid Client client) {
        return this.clientRepository.insert(client);
    }

    public Client update(@Valid Client client) {
        return this.clientRepository.save(client);
    }

    public void delete(String id){
        this.clientRepository.deleteById(id);
    }
}
