/**
 * @author jawc
 */
package br.com.jawc.online.shop.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Document(collection="product")
@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(name = "Product", description = "db for products!")
public class Product {
    @Id
    @Schema(description = "unique identifier")
    private String id;

    @NotNull
    @Size(min = 1, max = 100)
    @Schema(description = "name", minLength = 1, maxLength = 100, nullable = false)
    private String name;

    @NotNull
    @Size(min = 1, max = 150)
    @Schema(description = "description", minLength = 1, maxLength = 150, nullable = false)
    private String description;

    @NotNull
    @Indexed(unique = true)
    @Size(min = 1, max = 50)
    @Schema(description = "code", minLength = 1, maxLength = 50, nullable = false)
    private String code;

    @NotNull
    @Schema(description = "price/value", nullable = false)
    private BigDecimal price;

    public enum Status{
        ACTIVE, UNACTIVE
    }
    private Status status;
}
