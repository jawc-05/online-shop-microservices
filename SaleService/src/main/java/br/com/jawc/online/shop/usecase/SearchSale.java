/**
 * @author jawc
 */
package br.com.jawc.online.shop.usecase;

import br.com.jawc.online.shop.domain.Sale;
import br.com.jawc.online.shop.exception.EntityNotFoundException;
import br.com.jawc.online.shop.repository.ISaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class SearchSale {

    private final ISaleRepository saleRepository;

    @Autowired
    public SearchSale(ISaleRepository saleRepository) {
        this.saleRepository = saleRepository;
    }

    public Page<Sale> searchSales(Pageable pageable) {
        return saleRepository.findAll(pageable);
    }

    public Sale searchSaleByCode(String saleCode) {
        return saleRepository.findByCode(saleCode)
                .orElseThrow(()-> new EntityNotFoundException(Sale.class, "saleCode", saleCode));
    }

    public Page<Sale> searchAllCompletedSales(Pageable pageable) {
        return  saleRepository.findAllByStatus(pageable, Sale.Status.COMPLETED);
    }

    public Page<Sale> searchAllCancelledSales(Pageable pageable) {
        return  saleRepository.findAllByStatus(pageable, Sale.Status.CANCELLED);
    }
}
