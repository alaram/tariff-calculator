package tariffs.calculator.vehicletaxtariff.rest;

import java.util.List;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
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

    private List<ResponseEntity> tariffList = new ArrayList<>();

    @Mock
    private TariffService tariffService;

    //@Mock
    //private BindingResult bindingResult;

    @InjectMocks
    private TariffController tariffController;

    /*@BeforeAll
    void setup() {

        Tariff tariff_0 = new Tariff("06:00–06:29", "Göteborg", "8", "2013");
        Tariff tariff_1 = new Tariff("06:30–06:59", "Göteborg", "13", "2013");
        Tariff tariff_2 = new Tariff("07:00–07:59", "Göteborg", "18", "2013");
        Tariff tariff_3 = new Tariff("08:00–08:29", "Göteborg", "13", "2013");
        Tariff tariff_4 = new Tariff("08:30–14:59", "Göteborg", "8", "2013");

        ResponseEntity saved_0 = tariffController.createTariff(tariff_0);
        ResponseEntity saved_1 = tariffController.createTariff(tariff_1);
        ResponseEntity saved_2 = tariffController.createTariff(tariff_2);
        ResponseEntity saved_3 = tariffController.createTariff(tariff_3);
        ResponseEntity saved_4 = tariffController.createTariff(tariff_4);

        tariffList.add(saved_0);
        tariffList.add(saved_1);
        tariffList.add(saved_2);
        tariffList.add(saved_3);
        tariffList.add(saved_4);
    }*/


    @Test
    void retrieveTariffsTest() {
        assertThat(true);
    }

    @Test
    void retrieveTariffTest() {

        Tariff tariff = TestDataCreator.createTariff();
        lenient().when(tariffService.saveOrUpdateProject(any(Tariff.class))).thenReturn(tariff);
        ResponseEntity responseEntity = tariffController.createTariff(tariff);
        assertNotNull(responseEntity);
        assertThat(tariff).isInstanceOf(Tariff.class);
        assertEquals(CREATED, responseEntity.getStatusCode());

        lenient().when(tariffService.getTariff(tariff.getId())).thenReturn(tariff);
        ResponseEntity responseEntitySaved = tariffController.retrieveTariff(tariff.getId());
        assertNotNull(responseEntitySaved);
        assertEquals(OK, responseEntitySaved.getStatusCode());
    }

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
    void removeTariffTest() {

        Tariff tariff = TestDataCreator.createTariff();
        lenient().when(tariffService.saveOrUpdateProject(any(Tariff.class))).thenReturn(tariff);
        ResponseEntity responseEntity = tariffController.removeTariff(tariff.getId());
        assertEquals(OK, responseEntity.getStatusCode());
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