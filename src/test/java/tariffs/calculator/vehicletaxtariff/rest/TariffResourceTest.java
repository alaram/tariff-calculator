package tariffs.calculator.vehicletaxtariff.rest;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.Mock;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import tariffs.calculator.vehicletaxtariff.domain.Tariff;
import tariffs.calculator.vehicletaxtariff.domain.Vehicle;
import tariffs.calculator.vehicletaxtariff.web.TariffController;
import tariffs.calculator.vehicletaxtariff.service.TariffService;
import tariffs.calculator.vehicletaxtariff.domain.TestDataCreator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.lenient;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;

import static org.assertj.core.api.Assertions.assertThat;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.HttpStatus.CREATED;

@ExtendWith(MockitoExtension.class)
class TariffResourceTest {

    @Mock
    private TariffService tariffService;

    //@Mock
    //private BindingResult bindingResult;

    @InjectMocks
    private TariffController tariffController;

    @Test
    void createTariffTest() {

        BindingResult bindingResult = mock(BindingResult.class);
        lenient().when(bindingResult.hasErrors()).thenReturn(false);

        Tariff tariff = TestDataCreator.createTariff();
        lenient().when(tariffService.saveOrUpdateProject(any(Tariff.class))).thenReturn(tariff);
        ResponseEntity responseEntity = tariffController.createTariff(tariff);
        assertNotNull(responseEntity);
        assertThat(tariff).isInstanceOf(Tariff.class);
        assertEquals(CREATED, responseEntity.getStatusCode());
    }

    @Test
    void carTariffWithDateAndVehicleTypeAndCityTest() {
        Vehicle vehicle = TestDataCreator.createCar();
        lenient().when(tariffService.findVehicleTariffByDateAndVehicleTypeAndCity(anyString(), anyString(), anyString())).thenReturn(vehicle);
        ResponseEntity responseEntity = tariffController.getVehicleTariffByDateAndVehicleTypeAndCity(anyString(), anyString(), anyString());
        assertNotNull(responseEntity);
        assertEquals(OK, responseEntity.getStatusCode());
    }
}