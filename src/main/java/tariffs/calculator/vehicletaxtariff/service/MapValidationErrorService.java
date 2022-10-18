package tariffs.calculator.vehicletaxtariff.service;

import java.util.Map;
import java.util.HashMap;

import org.springframework.stereotype.Service;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.BindingResult;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Service
public class MapValidationErrorService {

    /**
     *
     * @param bindingResult
     * @return
     */
    public ResponseEntity<?> MapValidationService(BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            Map<String, String> errorMap = new HashMap<>();
            for (FieldError error: bindingResult.getFieldErrors()) {
                errorMap.put(error.getField(), error.getDefaultMessage());
            }
            return new ResponseEntity<>(errorMap, BAD_REQUEST);
        }
        return null;
    }
}