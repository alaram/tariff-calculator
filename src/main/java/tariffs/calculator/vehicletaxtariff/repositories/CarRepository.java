package tariffs.calculator.vehicletaxtariff.repositories;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import tariffs.calculator.vehicletaxtariff.domain.Car;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {

    /**
     *
     * @param vehicleType
     * @param city
     * @param date
     * @return
     */
    Car findVehicleTariffByDateAndVehicleTypeAndCity(String vehicleType, String city, String date);

    /**
     *
     * @param vehicleType
     * @param date
     * @return
     */
    Car findVehicleTariffByDateAndVehicleType(String vehicleType, String date);
}