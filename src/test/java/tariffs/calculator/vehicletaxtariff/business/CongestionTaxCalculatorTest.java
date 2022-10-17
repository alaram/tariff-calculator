package tariffs.calculator.vehicletaxtariff.business;

import org.junit.jupiter.api.Test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Map;
import java.util.Date;
import java.util.List;
import java.util.HashMap;
import java.util.Calendar;
import java.util.ArrayList;

import tariffs.calculator.vehicletaxtariff.domain.Vehicle;
import tariffs.calculator.vehicletaxtariff.domain.TestDataCreator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CongestionTaxCalculatorTest {

    private CongestionTaxCalculator congestionTaxCalculator = new CongestionTaxCalculator();

    private static Map<String, Integer> taxTariffByTime = new HashMap<>();
    static {
        taxTariffByTime.put("06:00–06:29", 8);
        taxTariffByTime.put("06:30–06:59", 13);
        taxTariffByTime.put("07:00–07:59", 18);
        taxTariffByTime.put("08:00–08:29", 13);
        taxTariffByTime.put("08:30–14:59", 8);
        taxTariffByTime.put("15:00–15:29", 13);
        taxTariffByTime.put("15:30–16:59", 18);
        taxTariffByTime.put("17:00–17:59", 13);
        taxTariffByTime.put("18:00–18:29", 8);
        taxTariffByTime.put("18:30–05:59", 0);
    }

    private static List<String> testDates = new ArrayList<>();
    static {

        testDates.add(0, "2013-01-14 21:00:00");
        testDates.add(1, "2013-01-15 21:00:00");
        testDates.add(2, "2013-02-07 06:23:27");
        testDates.add(3, "2013-02-07 15:27:00");
        testDates.add(4, "2013-02-08 06:27:00");
        testDates.add(5, "2013-02-08 06:20:27");
        testDates.add(6, "2013-02-08 14:35:00");
        testDates.add(7, "2013-02-08 15:29:00");
        testDates.add(8, "2013-02-08 15:47:00");
        testDates.add(9, "2013-02-08 16:01:00");
        testDates.add(10, "2013-02-08 16:48:00");
        testDates.add(11, "2013-02-08 17:49:00");
        testDates.add(12, "2013-02-08 18:29:00");
        testDates.add(13, "2013-02-08 18:35:00");
        testDates.add(14, "2013-03-26 14:25:00");
        testDates.add(15, "2013-03-28 14:07:27");
    }

    @Test
    public void getTaxVehicleBusTest() {

        Vehicle vehicle = TestDataCreator.createBus();
        assertNotNull(vehicle);
        assertEquals("bus", vehicle.getVehicleType());
        assertEquals(0, congestionTaxCalculator.getTax(vehicle, getDateArray(testDates.get(0))));
    }

    @Test
    public void getTaxVehicleDiplomatTest() {

        Vehicle vehicle = TestDataCreator.createDiplomat();
        assertNotNull(vehicle);
        assertEquals("diplomat", vehicle.getVehicleType());
        assertEquals(0, congestionTaxCalculator.getTax(vehicle, getDateArray(testDates.get(1))));
    }

    @Test
    public void getTaxVehicleCar_Tariff_1_Test() {

        Vehicle vehicle = TestDataCreator.createCar();
        assertNotNull(vehicle);
        assertEquals("car", vehicle.getVehicleType());
        assertEquals(8, congestionTaxCalculator.getTax(vehicle, getDateArray(testDates.get(2))));
    }

    @Test
    public void getTaxVehicleCar_Tariff_2_Test() {

        Vehicle vehicle = TestDataCreator.createCar();
        assertNotNull(vehicle);
        assertEquals("car", vehicle.getVehicleType());
        assertEquals(13, congestionTaxCalculator.getTax(vehicle, getDateArray(testDates.get(3))));
    }

    @Test
    public void getTaxVehicleCar_Tariff_3_Test() {

        Vehicle vehicle = TestDataCreator.createCar();
        assertNotNull(vehicle);
        assertEquals("car", vehicle.getVehicleType());
        assertEquals(0, congestionTaxCalculator.getTax(vehicle, getDateArray(testDates.get(1))));
    }

    /**
     *
     * @param date
     * @return
     */
    private Date[] getDateArray(String date) {
        Calendar calendar = Calendar.getInstance();
        List<Date> tariffList = new ArrayList<>();
        try {
            DateFormat sdf = new SimpleDateFormat("yyyyy-mm-dd HH:mm:ss");
            calendar.setTime(sdf.parse(date));
            tariffList.add(calendar.getTime());
        }
        catch (ParseException e) {
            e.printStackTrace();
        }
        return tariffList.toArray(Date[]::new);
    }
}