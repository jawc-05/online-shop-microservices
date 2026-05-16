/**
 * @author jawc
 */
package br.com.jawc.online.shop.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Document(collection = "sale")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Builder
public class Sale {

    public enum Status{
        STARTED, COMPLETED, CANCELLED;

        public static Status getByName(String value) {
            for (Status status : Status.values()) {
                if (status.name().equalsIgnoreCase(value)) {
                    return status;
                }
            }
            return null;
        }
    }

    @Id
    @Schema(description = "unique identifier" )
    private String id;

    @NotNull
    @Indexed(unique = true)
    @Size(min = 1, max = 50)
    @Schema(description = "code", minLength = 1, maxLength = 50, nullable = false)
    private String code;

    @NotNull
    @Builder.Default
    private Status status = Status.STARTED;

    @NotNull
    private String clientId;

    private Set<SaleItem> items;

    @Builder.Default
    private BigDecimal totalValue = BigDecimal.ZERO;

    @NotNull
    private LocalDate saleDate;

    private void validateStatus() {
        if (this.status == Status.COMPLETED || this.status == Status.CANCELLED) {
            throw new UnsupportedOperationException("Cannot modify a closed or cancelled sale.");
        }
    }

    public void recalculateTotalValue() {
        BigDecimal total = BigDecimal.ZERO;

        for (SaleItem item : this.items) {
            total = total.add(item.getTotalValue());
        }
        this.totalValue = total;
    }

    public void addProduct(String productCode, Integer quantity, BigDecimal priceAtSale) {
        if (this.items == null) {
            this.items = new HashSet<>();
        }
        validateStatus();

        Optional<SaleItem> op = this.items.stream()
                .filter(item -> item.getProductCode().equals(productCode))
                .findAny();
        if (op.isPresent()) {
            op.get().add(quantity);
        }else{
            SaleItem newItem =  SaleItem.builder()
                    .productCode(productCode)
                    .quantity(quantity)
                    .priceAtSale(priceAtSale)
                    .build();
            this.items.add(newItem);
        }

        recalculateTotalValue();
    }

    public void removeProduct(String productCode, Integer quantity) {
        validateStatus();

       Optional<SaleItem> op = items.stream()
               .filter(item -> item.getProductCode().equals(productCode))
               .findAny();
       if (op.isPresent()) {
           SaleItem existing = op.get();

           if (existing.getQuantity() > quantity) {
               existing.remove(quantity);
           }else{
               items.remove(existing);
           }
       }
       recalculateTotalValue();
    }
}
