/**
 * @author jawc
 */
package br.com.jawc.online.shop.usecase;

import br.com.jawc.online.shop.domain.Client;
import br.com.jawc.online.shop.exception.EntityNotFoundException;
import br.com.jawc.online.shop.repository.IClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SearchClient {
     private IClientRepository clientRepository;

     @Autowired
    public SearchClient(IClientRepository clientRepository) {
         this.clientRepository = clientRepository;
     }

     public Page<Client> searchClients(Pageable pageable) {
         return clientRepository.findAll(pageable);
     }

     public Client searchClientById(String id) {
         return clientRepository.findById(id)
                 .orElseThrow(()-> new EntityNotFoundException(Client.class, "id", id));
     }

     public Client searchClientByCpf(String cpf) {
         return clientRepository.findByCpf(cpf)
                 .orElseThrow(()-> new EntityNotFoundException(Client.class, "cpf", cpf));
     }

     public Boolean exists(String id){
         return clientRepository.existsById(id);
     }
}

