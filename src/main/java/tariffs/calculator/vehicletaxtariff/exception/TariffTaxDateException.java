package tariffs.calculator.vehicletaxtariff.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@ResponseStatus(BAD_REQUEST)
public class TariffTaxDateException extends RuntimeException {

    /**
     *
     * @param message
     */
    public TariffTaxDateException(String message) {
        super(message);
    }
}