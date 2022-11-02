package tariffs.calculator.vehicletaxtariff.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TariffTest {

    @Test
    void testCreateTariff() {
        Tariff tariff = TestDataCreator.createTariff();
        assertNotNull(tariff);
        assertEquals(Integer.toString(8), tariff.getAmount());
        assertEquals("Göteborg", tariff.getCity());
    }
}