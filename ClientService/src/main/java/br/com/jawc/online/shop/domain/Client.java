/**
 * @author jawc
 */
package br.com.jawc.online.shop.domain;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "client")
@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(name = "Client", description = "Client")
public class Client {

    @Id
    @Schema(description = "unique identifier" )
    private String id;

    @NotNull
    @Size(min = 1, max = 100)
    @Schema(description = "name", minLength = 1, maxLength = 100, nullable = false)
    private String name;

    @NotNull
    @Indexed(unique = true)
    @Size(min = 11, max = 11)
    @Schema(description = "cpf", minLength = 11, maxLength = 11, nullable = false)
    private String cpf;

    @NotNull
    @Schema(description = "tel", nullable = false)
    private String tel;

    @NotNull
    @Size(min = 1, max = 100)
    @Indexed(unique = true)
    @Schema(description = "email", minLength = 1, maxLength = 100, nullable = false)
    @Pattern(regexp = ".+@.+\\..+", message = "Invalid Email")
    private String email;

    @NotNull
    @Size(min = 1, max = 125)
    @Schema(description = "address", nullable = false, minLength = 1, maxLength = 125)
    private String address;

    @NotNull
    @Schema(description = "house number", nullable = false)
    private Integer num;

    @NotNull
    @Schema(description = "city", nullable = false)
    private String city;

    @NotNull
    @Size(min = 1, max = 50)
    @Schema(description = "state", nullable = false, minLength = 1, maxLength = 50)
    private String state;


}
