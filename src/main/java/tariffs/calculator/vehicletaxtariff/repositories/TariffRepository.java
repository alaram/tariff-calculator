package tariffs.calculator.vehicletaxtariff.repositories;

import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;

import tariffs.calculator.vehicletaxtariff.domain.Tariff;

@Repository
public interface TariffRepository extends CrudRepository<Tariff, Long> {

    /**
     *
     * @param city
     * @return
     */
    Tariff findByCity(String city);
}