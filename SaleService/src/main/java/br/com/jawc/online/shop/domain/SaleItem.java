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
    @Builder.Default
    private BigDecimal totalValue = BigDecimal.ZERO;



    public void add(Integer quantity) {
        this.quantity += quantity;

        BigDecimal itemPrice = this.priceAtSale.multiply(BigDecimal.valueOf(quantity));

        this.totalValue = this.totalValue.add(itemPrice);
    }

    public void remove(Integer quantity) {
        this.quantity -= quantity;

        BigDecimal newValue = this.priceAtSale.multiply(BigDecimal.valueOf(quantity));

        this.totalValue = this.totalValue.subtract(newValue);
    }

}
