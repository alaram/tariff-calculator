package tariffs.calculator.vehicletaxtariff.repositories;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestMethodOrder;

import org.springframework.test.context.ActiveProfiles;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

import tariffs.calculator.vehicletaxtariff.domain.Tariff;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;

@DataJpaTest
@ActiveProfiles("local")
@AutoConfigureTestDatabase(replace = NONE)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ComponentScan(basePackages = { "tariffs.calculator.vehicletaxtariff.bootstrap" })
class MySQLIntegrationTest {

    @Autowired
    TariffRepository tariffRepository;

    @Order(1)
    @Test
    void testJpaTestSliceTransaction() {
        long countBefore = tariffRepository.count();

        assertThat(countBefore).isEqualTo(2);
    }

    @Order(2)
    @Test
    void testRetrieveTariffs() {
        List<Tariff> tariffList = tariffRepository.findAll();

        assertThat(tariffList.size()).isNotNull();
        assertThat(tariffList.size()).isEqualTo(2);

        long countBefore = tariffRepository.count();
        assertThat(countBefore).isEqualTo(2);
    }

    @Order(3)
    @Test
    void testRetrieveTariff() {
        Tariff saved = tariffRepository.save(new Tariff("05:00-05:59", "Stockholm", "1", "2013"));

        assertThat(saved.getId()).isNotNull();
        assertThat(saved.getCity()).isEqualTo("Stockholm");

        long countBefore = tariffRepository.count();
        assertThat(countBefore).isEqualTo(3);
    }

    @Order(4)
    @Test
    void testRemoveTariff() throws JpaObjectRetrievalFailureException {
        Tariff saved = tariffRepository.save(new Tariff("05:00-05:59", "Stockholm", "1", "2013"));

        long countBefore = tariffRepository.count();
        assertThat(countBefore).isEqualTo(3);
        assertThat(saved).isNotNull();
        assertThat(saved.getId()).isNotNull();

        tariffRepository.delete(saved);

        JpaObjectRetrievalFailureException thrown = assertThrows(JpaObjectRetrievalFailureException.class, () -> {
                        tariffRepository.getReferenceById(saved.getId());
                },
         "Unable to find tariffs.calculator.vehicletaxtariff.domain.Tariff with id " + saved.getId());

        assertThat("Unable to find tariffs.calculator.vehicletaxtariff.domain.Tariff with id " + saved.getId() + "; " +
                "nested exception is javax.persistence.EntityNotFoundException: " +
                "Unable to find tariffs.calculator.vehicletaxtariff.domain.Tariff with id " + saved.getId()).isEqualTo(thrown.getMessage());
    }
}