package tariffs.calculator.vehicletaxtariff.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@ResponseStatus(BAD_REQUEST)
public class TariffIdException extends RuntimeException {

    /**
     *
     * @param message
     */
    public TariffIdException(String message) {
        super(message);
    }
}