package tariffs.calculator.vehicletaxtariff.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TariffTest {

    @Test
    public void testCreateTariff() {
        Tariff tariff = TestDataCreator.createTariff();
        assertNotNull(tariff);
        assertEquals("Göteborg", tariff.getCity());
    }
}