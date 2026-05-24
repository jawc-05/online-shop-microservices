/**
 * @author jawc
 */
package br.com.jawc.online.shop.integration;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "client-service")
public interface IClientService {

    @GetMapping("/client/exists/{id}")
    Boolean isCustomerRegistered(@PathVariable("id") String id);
}
