package tariffs.calculator.vehicletaxtariff.repositories;

import org.junit.jupiter.api.Test;

import org.springframework.test.context.ActiveProfiles;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

import static org.assertj.core.api.Assertions.assertThat;


@ComponentScan(basePackages = { "tariffs.calculator.vehicletaxtariff.bootstrap" })
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("local")
@DataJpaTest
public class MySQLIntegrationTest {

    @Autowired
    TariffRepository tariffRepository;

    @Test
    void testJpaTestSliceTransaction() {
        long countBefore = tariffRepository.count();

        assertThat(countBefore).isEqualTo(2);
    }
}