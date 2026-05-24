/**
 * @author jawc
 */
package br.com.jawc.online.shop.domain;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SaleItem {

    private String productCode;
    @Builder.Default
    private Integer quantity = 0;
    @Builder.Default
    private BigDecimal priceAtSale = BigDecimal.ZERO;

    public BigDecimal getTotalValue(){
        if (this.priceAtSale == null || this.quantity == null) {
            return BigDecimal.ZERO;
        }
        return this.priceAtSale.multiply(BigDecimal.valueOf(this.quantity));
    }




    public void add(Integer quantity) {
        this.quantity += quantity;
    }

    public void remove(Integer quantityToRemove) {
        if (quantityToRemove != null && quantityToRemove > this.quantity) {
            this.quantity = 0;
        } else {
            this.quantity -= quantityToRemove;
        }
    }

}
