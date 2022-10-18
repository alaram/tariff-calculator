package tariffs.calculator.vehicletaxtariff.rest;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.Mock;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import org.springframework.http.ResponseEntity;

import tariffs.calculator.vehicletaxtariff.domain.Tariff;
import tariffs.calculator.vehicletaxtariff.domain.Vehicle;
import tariffs.calculator.vehicletaxtariff.web.TariffController;
import tariffs.calculator.vehicletaxtariff.service.TariffService;
import tariffs.calculator.vehicletaxtariff.domain.TestDataCreator;
import tariffs.calculator.vehicletaxtariff.service.MapValidationErrorService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import static org.mockito.Mockito.lenient;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;

import static org.springframework.http.HttpStatus.OK;

@ExtendWith(MockitoExtension.class)
public class TariffResourceTest {

    @Mock
    private TariffService tariffService;

    @Mock
    private MapValidationErrorService mapValidationErrorService;

    @InjectMocks
    private TariffController tariffController;

    @Test
    public void createTariffTest() {
        Tariff tariff = TestDataCreator.createTariff();
        lenient().when(tariffService.saveOrUpdateProject(any(Tariff.class))).thenReturn(tariff);
        //ResponseEntity responseEntity = tariffController.createNewTariff(tariff, any);
        //assertNotNull(responseEntity);
        //assertEquals(tariff, responseEntity.getClass());
        //assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
    }

    @Test
    public void carTariffWithDateAndVehicleTypeAndCityTest() {
        Vehicle vehicle = TestDataCreator.createCar();
        lenient().when(tariffService.findVehicleTariffByDateAndVehicleTypeAndCity(anyString(), anyString(), anyString())).thenReturn(vehicle);
        ResponseEntity responseEntity = tariffController.getVehicleTariffByDateAndVehicleTypeAndCity(anyString(), anyString(), anyString());
        assertNotNull(responseEntity);
        assertEquals(OK, responseEntity.getStatusCode());
    }
}