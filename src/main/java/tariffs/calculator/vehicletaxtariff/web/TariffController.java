package tariffs.calculator.vehicletaxtariff.web;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;

import tariffs.calculator.vehicletaxtariff.domain.Tariff;
import tariffs.calculator.vehicletaxtariff.domain.Vehicle;
import tariffs.calculator.vehicletaxtariff.service.TariffService;
import tariffs.calculator.vehicletaxtariff.service.MapValidationErrorService;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.HttpStatus.CREATED;

@CrossOrigin
@RestController
@RequestMapping("/api/tariff")
public class TariffController {

    @Autowired
    private TariffService tariffService;

    @Autowired
    private MapValidationErrorService mapValidationErrorService;

    /**
     *
     * @param tariff
     * @return
     */
    @PostMapping("/create")
    public ResponseEntity<?> createNewTariff(@Valid @RequestBody Tariff tariff) { //, BindingResult bindingResult

        //ResponseEntity<?> errorMap =  mapValidationErrorService.MapValidationService(bindingResult);
        //if (errorMap != null)
        //    return errorMap;

        Tariff newTariff = tariffService.saveOrUpdateProject(tariff);
        return new ResponseEntity<>(newTariff, CREATED);
    }

    /**
     *
     * @param vehicleType
     * @param city
     * @param date
     * @return
     */
    @GetMapping("/{vehicleType}/{city}/{date}")
    public ResponseEntity<?> getVehicleTariffByDateAndVehicleTypeAndCity(@PathVariable String vehicleType,
                                                                         @PathVariable String city,
                                                                         @PathVariable String date) {

        Vehicle vehicleTariff = tariffService.findVehicleTariffByDateAndVehicleTypeAndCity(vehicleType, city, date);
        return new ResponseEntity<>(vehicleTariff, OK);
    }
}