package etf.unsa.ba.user_management.service.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Collections;
import java.util.List;

@Data
@AllArgsConstructor
public class ApiError {
    private String message;
    private List<String> errors;

    public ApiError(String message) {
        super();
        this.message = message;
    }

    public ApiError(String message, String error) {
        super();
        this.message = message;
        errors = Collections.singletonList(error);
    }
}
