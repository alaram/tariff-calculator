package tariffs.calculator.vehicletaxtariff.rest;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.Mock;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import tariffs.calculator.vehicletaxtariff.domain.Vehicle;
import tariffs.calculator.vehicletaxtariff.web.TariffController;
import tariffs.calculator.vehicletaxtariff.service.TariffService;
import tariffs.calculator.vehicletaxtariff.domain.TestDataCreator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.anyString;

@ExtendWith(MockitoExtension.class)
public class TariffResourceTest {

    @Mock
    private TariffService tariffService;

    @InjectMocks
    private TariffController tariffController;

    @Test
    public void carTariffWithVehicleTypeTest() {
        Vehicle vehicle = TestDataCreator.createCar();
        when(tariffService.getVehicleTariffByVehicleType(anyString())).thenReturn(null);
        ResponseEntity response = tariffController.getVehicleTariffByVehicleType(anyString());
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void carTariffWithDateAndVehicleTypeTest() {
        Vehicle vehicle = TestDataCreator.createCar();
        when(tariffService.findVehicleTariffByDateAndVehicleType(anyString(), anyString())).thenReturn(null);
        ResponseEntity response = tariffController.getVehicleTariffByDateAndVehicleType(anyString(), anyString());
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void carTariffWithDateAndVehicleTypeAndCityTest() {
        Vehicle vehicle = TestDataCreator.createCar();
        when(tariffService.findVehicleTariffByDateAndVehicleTypeAndCity(anyString(), anyString(), anyString())).thenReturn(null);
        ResponseEntity response = tariffController.getVehicleTariffByDateAndVehicleTypeAndCity(anyString(), anyString(), anyString());
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}