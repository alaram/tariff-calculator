package tariffs.calculator.vehicletaxtariff.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@ResponseStatus(BAD_REQUEST)
public class TariffCityNotFoundException extends RuntimeException {

    /**
     *
     * @param message
     */
    public TariffCityNotFoundException(String message) {
        super(message);
    }
}