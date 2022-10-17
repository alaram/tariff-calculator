package tariffs.calculator.vehicletaxtariff.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;

import tariffs.calculator.vehicletaxtariff.domain.Vehicle;
import tariffs.calculator.vehicletaxtariff.service.TariffService;

@CrossOrigin
@RestController
@RequestMapping("/api/tariff")
public class TariffController {

    @Autowired
    private TariffService tariffService;

    /**
     *
     * @param vehicleType
     * @return
     */
    @GetMapping("/{vehicleType}")
    public ResponseEntity<?> getVehicleTariffByVehicleType(@PathVariable String vehicleType) {
        Vehicle vehicleTariff = tariffService.getVehicleTariffByVehicleType(vehicleType);
        return new ResponseEntity<>(vehicleTariff, HttpStatus.OK);
    }

    /**
     *
     * @param vehicleType
     * @param date
     * @return
     */
    @GetMapping("/{vehicleType}/{date}")
    public ResponseEntity<?> getVehicleTariffByDateAndVehicleType(@PathVariable String vehicleType, @PathVariable String date) {
        Vehicle vehicleTariff = tariffService.findVehicleTariffByDateAndVehicleType(vehicleType, date);
        return new ResponseEntity<>(vehicleTariff, HttpStatus.OK);
    }

    /**
     *
     * @param vehicleType
     * @param city
     * @param date
     * @return
     */
    @GetMapping("/{vehicleType}/{city}/{date}")
    public ResponseEntity<?> getVehicleTariffByDateAndVehicleTypeAndCity(@PathVariable String vehicleType, @PathVariable String city, @PathVariable String date) {
        Vehicle vehicleTariff = tariffService.findVehicleTariffByDateAndVehicleTypeAndCity(vehicleType, city, date);
        return new ResponseEntity<>(vehicleTariff, HttpStatus.OK);
    }
}