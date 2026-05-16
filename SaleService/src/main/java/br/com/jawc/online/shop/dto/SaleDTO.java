/**
 * @author jawc
 */
package br.com.jawc.online.shop.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SaleDTO {
    @NotNull
    private String code;

    @NotNull
    private String clientId;

    @NotNull
    private LocalDate saleDate;
}
