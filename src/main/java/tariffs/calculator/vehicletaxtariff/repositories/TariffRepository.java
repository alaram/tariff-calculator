package tariffs.calculator.vehicletaxtariff.repositories;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import tariffs.calculator.vehicletaxtariff.domain.Tariff;

@Repository
public interface TariffRepository extends JpaRepository<Tariff, Long> {

    /**
     *
     * @param city
     * @return
     */
    Tariff findByCity(String city);
}