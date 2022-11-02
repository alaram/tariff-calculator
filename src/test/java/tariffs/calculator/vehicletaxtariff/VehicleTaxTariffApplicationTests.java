package tariffs.calculator.vehicletaxtariff;

import org.junit.jupiter.api.Test;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

import org.springframework.test.context.ActiveProfiles;

import tariffs.calculator.vehicletaxtariff.repositories.TariffRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;

@SpringBootTest
@ActiveProfiles({"local", "default"})
@AutoConfigureTestDatabase(replace = NONE)
class VehicleTaxTariffApplicationTests {

    @Autowired
    TariffRepository tariffRepository;

    @Test
    void testTariffRepository() {
        long count = tariffRepository.count();

        assertThat(count).isGreaterThan(0);
    }

    @Test
    void contextLoads() {}
}