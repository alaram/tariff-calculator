package tariffs.calculator.vehicletaxtariff.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@ResponseStatus(BAD_REQUEST)
public class TariffTaxCalculatorException extends RuntimeException {

    /**
     *
     * @param message
     */
    public TariffTaxCalculatorException(String message) {
        super(message);
    }
}