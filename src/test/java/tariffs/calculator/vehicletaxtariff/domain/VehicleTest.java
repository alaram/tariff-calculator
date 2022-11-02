package tariffs.calculator.vehicletaxtariff.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class VehicleTest {

    @Test
    void testCreateVehicleTypeCar() {
        Vehicle vehicle = TestDataCreator.createCar();
        assertNotNull(vehicle);
        assertEquals("car", vehicle.getVehicleType());
        assertEquals("Göteborg", ((Car) vehicle).getCity());
    }

    @Test
    void testCreateVehicleTypeBus() {
        Vehicle vehicle = TestDataCreator.createBus();
        assertNotNull(vehicle);
        assertEquals("bus", vehicle.getVehicleType());
        assertEquals("2013-01-15 21:00:00", ((Bus) vehicle).getDate());
    }
}