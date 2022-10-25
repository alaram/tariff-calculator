package tariffs.calculator.vehicletaxtariff.bootstrap;

import org.springframework.stereotype.Component;
import org.springframework.boot.CommandLineRunner;

import tariffs.calculator.vehicletaxtariff.domain.Tariff;
import tariffs.calculator.vehicletaxtariff.repositories.TariffRepository;

@Component
public class DataInitializer implements CommandLineRunner {

    private final TariffRepository tariffRepository;

    /**
     *
     * @param tariffRepository
     */
    public DataInitializer(TariffRepository tariffRepository) {
        this.tariffRepository = tariffRepository;
    }

    /**
     *
     * @param args
     * @throws Exception
     */
    @Override
    public void run(String... args) throws Exception {

        Tariff tariff_0 = new Tariff("06:00–06:29", "Göteborg", "8");
        Tariff tariff_1 = new Tariff("06:30–06:59", "Göteborg", "13");
        Tariff tariff_2 = new Tariff("07:00–07:59", "Göteborg", "18");
        Tariff tariff_3 = new Tariff("08:00–08:29", "Göteborg", "13");
        Tariff tariff_4 = new Tariff("08:30–14:59", "Göteborg", "8");
        Tariff tariff_5 = new Tariff("15:00–15:29", "Göteborg", "13");
        Tariff tariff_6 = new Tariff("15:30–16:59", "Göteborg", "18");
        Tariff tariff_7 = new Tariff("17:00–17:59", "Göteborg", "13");
        Tariff tariff_8 = new Tariff("18:00–18:29", "Göteborg", "8");
        Tariff tariff_9 = new Tariff("18:30–05:59", "Göteborg", "0");

        Tariff tariffSaved_1 = tariffRepository.save(tariff_1);
        Tariff tariffSaved_2 = tariffRepository.save(tariff_2);

        tariffRepository.findAll().forEach(tariff -> {
            System.out.println(tariff.getId());
            System.out.println(tariff.getCity());
            System.out.println(tariff.getAmount());
        });
    }
}