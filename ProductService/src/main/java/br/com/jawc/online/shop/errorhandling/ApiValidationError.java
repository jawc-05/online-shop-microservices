/**
 * @author jawc
 */
package br.com.jawc.online.shop.errorhandling;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
public class ApiValidationError extends ApiSubError {

    private String object;
    private String field;
    private Object rejectedValue;
    private String message;

    ApiValidationError(String object,String message) {
        this.message = message;
        this.object = object;
    }
}
