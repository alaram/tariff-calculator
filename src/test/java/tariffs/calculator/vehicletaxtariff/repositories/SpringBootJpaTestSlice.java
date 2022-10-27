package tariffs.calculator.vehicletaxtariff.repositories;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestMethodOrder;

import org.springframework.test.annotation.Commit;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

import tariffs.calculator.vehicletaxtariff.domain.Tariff;

import static org.assertj.core.api.Assertions.assertThat;

@ComponentScan(basePackages = { "tariffs.calculator.vehicletaxtariff.bootstrap" })
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DataJpaTest
public class SpringBootJpaTestSlice {

    @Autowired
    TariffRepository tariffRepository;

    /**
     * The purpose of this is to ensure/demonstrate
     * a) That test will run in the specified order
     * b) That test will perform the transaction so that the transaction
     *    does not rollback, thus the datasource is empty for the coming test.
     *    99% of the cases, we want this to be but testing transaction is for
     *    demonstrating purposes.
     */
    @Commit
    @Order(1)
    @Test
    void testJpaTestSlice() {
        long countBefore = tariffRepository.count();
        assertThat(countBefore).isEqualTo(2);

        tariffRepository.save(new Tariff("05:00-05:59", "Stockholm", "1"));

        long countAfter = tariffRepository.count();

        assertThat(countBefore).isLessThan(countAfter);
    }

    @Order(2)
    @Test
    void testJpaTestSliceTransaction() {
        long countBefore = tariffRepository.count();

        assertThat(countBefore).isEqualTo(3);
    }
}