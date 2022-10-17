package tariffs.calculator.vehicletaxtariff.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import tariffs.calculator.vehicletaxtariff.domain.Car;
import tariffs.calculator.vehicletaxtariff.domain.Bus;
import tariffs.calculator.vehicletaxtariff.domain.Vehicle;
import tariffs.calculator.vehicletaxtariff.domain.Foreign;
import tariffs.calculator.vehicletaxtariff.domain.Military;
import tariffs.calculator.vehicletaxtariff.domain.Diplomat;
import tariffs.calculator.vehicletaxtariff.domain.Emergency;
import tariffs.calculator.vehicletaxtariff.domain.Motorcycle;
import tariffs.calculator.vehicletaxtariff.repositories.CarRepository;
import tariffs.calculator.vehicletaxtariff.business.CongestionTaxCalculator;
import tariffs.calculator.vehicletaxtariff.exception.TariffVehicleNotFoundException;

import java.util.List;
import java.util.Date;
import java.util.Calendar;
import java.util.ArrayList;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@Service
public class TariffService {

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private CongestionTaxCalculator congestionTaxCalculator;

    private int vehicleTax;
    private Vehicle theVehicle;
    private Date[] tariffDates = {};
    private Calendar calendar = Calendar.getInstance();
    private static final String DATE_FORMAT = "yyyyy-mm-dd HH:mm:ss";

    /**
     *
     * @param vehicleType
     * @return
     */
    public Vehicle getVehicleTariffByVehicleType(String vehicleType) {

        String city = "";
        // Use highest tariff for the vehicle
        String date = "2013-01-02 07:00:00";
        tariffDates = getDateArray(date);
        theVehicle = this.getVehicleType(vehicleType.toLowerCase());
        vehicleTax = congestionTaxCalculator.getTax(theVehicle, tariffDates);
        String vehicle = theVehicle.getVehicleType();
        return getVehicle(vehicle, Integer.toString(vehicleTax), calendar.getTime().toString(), city);
    }

    /**
     *
     * @param vehicleType
     * @param date
     * @return
     */
    public Vehicle findVehicleTariffByDateAndVehicleType(String vehicleType, String date) {

        String city = "";
        theVehicle = this.getVehicleType(vehicleType.toLowerCase());
        tariffDates = getDateArray(date);
        vehicleTax = congestionTaxCalculator.getTax(theVehicle, tariffDates);
        return getVehicle(theVehicle.getVehicleType(), Integer.toString(vehicleTax), calendar.getTime().toString(), city);
    }

    /**
     *
     * @param vehicleType
     * @param city
     * @param date
     * @return
     */
    public Vehicle findVehicleTariffByDateAndVehicleTypeAndCity(String vehicleType, String city, String date) {

        theVehicle = this.getVehicleType(vehicleType.toLowerCase());
        tariffDates = getDateArray(date);
        vehicleTax = congestionTaxCalculator.getTax(theVehicle, tariffDates);
        return getVehicle(theVehicle.getVehicleType(), Integer.toString(vehicleTax), calendar.getTime().toString(), city);
    }

    /**
     *
     * @param date
     * @return
     */
    private Date[] getDateArray(String date) {

        List<Date> tariffList = new ArrayList<>();
        try {
            DateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
            calendar.setTime(sdf.parse(date));
            tariffList.add(calendar.getTime());
        }
        catch (ParseException e) {
            e.printStackTrace();
        }
        return tariffList.toArray(Date[]::new);
    }

    /**
     *
     * @param vehicle
     * @return
     */
    private Vehicle getVehicleType(String vehicle) {

        Vehicle vehicleToReturn;
        switch (vehicle) {
            case "car":
                vehicleToReturn = new Car();
                break;
            case "bus":
                vehicleToReturn = new Bus();
                break;
            case "diplomat":
                vehicleToReturn = new Diplomat();
                break;
            case "emergency":
                vehicleToReturn = new Emergency();
                break;
            case "foreign":
                vehicleToReturn = new Foreign();
                break;
            case "military":
                vehicleToReturn = new Military();
                break;
            case "motorcycle":
                vehicleToReturn = new Motorcycle();
                break;
            default:
                throw new TariffVehicleNotFoundException("The vehicle that is being tried to tariff is not listed for this service: " + vehicle);
        }

        return vehicleToReturn;
    }

    /**
     *
     * @param vehicle
     * @param tariff
     * @param date
     * @param city
     * @return
     */
    private Vehicle getVehicle(String vehicle, String tariff, String date, String city) {
        Vehicle vehicleToReturn;
        city = city.isEmpty() ? "Göteborg" : city;
        switch (vehicle) {
            case "car":
                Car car = new Car();
                car.setAmount(tariff);
                car.setDate(date);
                car.setCity(city);
                vehicleToReturn = car;
                break;
            case "bus":
                Bus bus = new Bus();
                bus.setAmount(tariff);
                bus.setDate(date);
                bus.setCity(city);
                vehicleToReturn = bus;
                break;
            case "diplomat":
                Diplomat diplomat = new Diplomat();
                diplomat.setAmount(tariff);
                diplomat.setDate(date);
                diplomat.setCity(city);
                vehicleToReturn = diplomat;
                break;
            case "emergency":
                Emergency emergency = new Emergency();
                emergency.setAmount(tariff);
                emergency.setDate(date);
                emergency.setCity(city);
                vehicleToReturn = emergency;
                break;
            case "foreign":
                Foreign foreign = new Foreign();
                foreign.setAmount(tariff);
                foreign.setDate(date);
                foreign.setCity(city);
                vehicleToReturn = foreign;
                break;
            case "military":
                Military military = new Military();
                military.setAmount(tariff);
                military.setDate(date);
                military.setCity(city);
                vehicleToReturn = military;
                break;
            case "motorcycle":
                Motorcycle motorcycle = new Motorcycle();
                motorcycle.setAmount(tariff);
                motorcycle.setDate(date);
                motorcycle.setCity(city);
                vehicleToReturn = motorcycle;
                break;
            default:
                throw new TariffVehicleNotFoundException("The vehicle that is being tried to tariff is not listed for this service: " + vehicle);
        }
        return vehicleToReturn;
    }
}