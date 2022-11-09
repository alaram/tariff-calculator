package tariffs.calculator.vehicletaxtariff.web;

import javax.validation.Valid;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiOperation;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;

import tariffs.calculator.vehicletaxtariff.domain.Tariff;
import tariffs.calculator.vehicletaxtariff.domain.Vehicle;
import tariffs.calculator.vehicletaxtariff.service.TariffService;
import tariffs.calculator.vehicletaxtariff.service.MapValidationErrorService;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@CrossOrigin
@RestController
@RequestMapping("/api/tariff")
@Api(tags= { "TariffController" })
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
    @PostMapping(value = "/create",
                 consumes = APPLICATION_JSON_VALUE,
                 produces = APPLICATION_JSON_VALUE,
                 headers = APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Creates a Tariff based on a valid object, i.e. timestamp, city, amount and year (optional).",
                  notes = "This operation requires to send a timestamp in the right format in order to persist on the DB.",
                  consumes = APPLICATION_JSON_VALUE,
                  produces = APPLICATION_JSON_VALUE,
                  httpMethod = "POST",
                  code = 201)
    public ResponseEntity<?> createTariff(@ApiParam(value="A valid well formed Tariff object")
                                             @Valid @RequestBody Tariff tariff) { //, BindingResult bindingResult

        //ResponseEntity<?> errorMap =  mapValidationErrorService.MapValidationService(bindingResult);
        //if (errorMap != null)
        //    return errorMap;

        Tariff newTariff = tariffService.saveOrUpdateProject(tariff);
        return new ResponseEntity<>(newTariff, CREATED);
    }

    /**
     *
     * @param tariffId
     * @return
     */
    @PostMapping(value = "/remove/{tariffId}",
                   consumes = APPLICATION_JSON_VALUE,
                   produces = APPLICATION_JSON_VALUE,
                   headers = APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Tariff's id to remove.",
                  notes = "Removes a Tariff based on entity's ID, this value is required to remove the tariff.",
                  consumes = APPLICATION_JSON_VALUE,
                  produces = APPLICATION_JSON_VALUE,
                  httpMethod = "POST",
                  code = 200)
    public ResponseEntity<?> removeTariff(@ApiParam(value="Tariff's id") @PathVariable Long tariffId) {

        tariffService.removeTariff(tariffId);
        return new ResponseEntity<>("Tariff with id '" + tariffId + "' was removed", OK);
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