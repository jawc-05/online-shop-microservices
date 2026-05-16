/**
 * @author jawc
 */
package br.com.jawc.online.shop.integration;

import br.com.jawc.online.shop.domain.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "product", url = "${app.product-service}")
public interface IProductService {

    @GetMapping(value = "/{code}", produces = "application/json", headers = "application/json")
    Product searchProductByCode(@PathVariable(value = "code", required = true)String code);
}
