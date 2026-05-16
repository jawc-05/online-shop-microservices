/**
 * @author jawc
 */
package br.com.jawc.online.shop.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.index.Indexed;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @NotNull
    private String id;

    @NotNull
    @Size(min = 1, max = 50)
    private String code;


    @NotNull
    private BigDecimal price;

    @NotNull
    @Size(min = 1, max = 100)
    private String name;
}
