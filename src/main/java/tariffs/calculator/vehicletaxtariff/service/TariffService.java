package tariffs.calculator.vehicletaxtariff.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Date;
import java.util.Calendar;
import java.util.ArrayList;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import tariffs.calculator.vehicletaxtariff.domain.Bus;
import tariffs.calculator.vehicletaxtariff.domain.Car;
import tariffs.calculator.vehicletaxtariff.domain.Tariff;
import tariffs.calculator.vehicletaxtariff.domain.Foreign;
import tariffs.calculator.vehicletaxtariff.domain.Vehicle;
import tariffs.calculator.vehicletaxtariff.domain.Diplomat;
import tariffs.calculator.vehicletaxtariff.domain.Military;
import tariffs.calculator.vehicletaxtariff.domain.Emergency;
import tariffs.calculator.vehicletaxtariff.domain.Motorcycle;
import tariffs.calculator.vehicletaxtariff.exception.TariffIdException;
import tariffs.calculator.vehicletaxtariff.repositories.CarRepository;
import tariffs.calculator.vehicletaxtariff.repositories.TariffRepository;
import tariffs.calculator.vehicletaxtariff.business.CongestionTaxCalculator;
import tariffs.calculator.vehicletaxtariff.exception.TariffTaxDateException;
import tariffs.calculator.vehicletaxtariff.exception.TariffCityNotFoundException;
import tariffs.calculator.vehicletaxtariff.exception.TariffVehicleNotFoundException;

@Service
public class TariffService {

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private TariffRepository tariffRepository;

    @Autowired
    private CongestionTaxCalculator congestionTaxCalculator;

    private int vehicleTax;
    private Vehicle theVehicle;
    private Date[] tariffDates = {};
    private Calendar calendar = Calendar.getInstance();
    private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

    /**
     *
     * @return
     */
    public List<Tariff> getTariffs() {
        return tariffRepository.findAll();
    }

    /**
     *
     * @param tariffId
     * @return
     */
    public Tariff getTariff(Long tariffId) {

        Tariff saved = tariffRepository.getReferenceById(tariffId);

        if (saved == null) {
            throw new TariffIdException("Tariff with id: '" + tariffId + "' does not exists.");
        }

        return saved;
    }

    /**
     *
     * @param tariff
     * @return
     */
    public Tariff saveOrUpdateProject(Tariff tariff) {
        try {
            return tariffRepository.save(tariff);
        } catch (Exception e) {
            throw new TariffIdException("Tariff id: '" + tariff.getId() + "' already exists.");
        }
    }

    /**
     *
     * @param tariffId
     */
    public void removeTariff(Long tariffId) {
        tariffRepository.delete(tariffRepository.getReferenceById(tariffId));
    }

    /**
     *
     * @param vehicleType
     * @param city
     * @param date
     * @return
     */
    public Vehicle findVehicleTariffByDateAndVehicleTypeAndCity(String vehicleType, String city, String date) {

        if ( vehicleType == null || vehicleType.isEmpty() ) {
            throw new TariffVehicleNotFoundException("The Vehicle type is required in order to tax it!");
        }

        if ( city == null || city.isEmpty() ) {
            throw new TariffCityNotFoundException("The city is required in order to obtain correct tax tariff!");
        }

        if ( date == null || date.isEmpty() ) {
            throw new TariffTaxDateException("The date is required in order to tax the vehicle properly!");
        }

        theVehicle = this.getVehicleType(vehicleType.toLowerCase());
        tariffDates = getDateArray(date);
        vehicleTax = congestionTaxCalculator.getTax(theVehicle, tariffDates);
        return getVehicle(theVehicle.getVehicleType(), calendar.getTime().toString(), city);
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
     * @param date
     * @param city
     * @return
     */
    private Vehicle getVehicle(String vehicle, String date, String city) {
        Vehicle vehicleToReturn;
        city = city.isEmpty() ? "Göteborg" : city;
        switch (vehicle) {
            case "car":
                Car car = new Car();
                car.setDate(date);
                car.setCity(city);
                vehicleToReturn = car;
                break;
            case "bus":
                Bus bus = new Bus();
                bus.setDate(date);
                bus.setCity(city);
                vehicleToReturn = bus;
                break;
            case "diplomat":
                Diplomat diplomat = new Diplomat();
                diplomat.setDate(date);
                diplomat.setCity(city);
                vehicleToReturn = diplomat;
                break;
            case "emergency":
                Emergency emergency = new Emergency();
                emergency.setDate(date);
                emergency.setCity(city);
                vehicleToReturn = emergency;
                break;
            case "foreign":
                Foreign foreign = new Foreign();
                foreign.setDate(date);
                foreign.setCity(city);
                vehicleToReturn = foreign;
                break;
            case "military":
                Military military = new Military();
                military.setDate(date);
                military.setCity(city);
                vehicleToReturn = military;
                break;
            case "motorcycle":
                Motorcycle motorcycle = new Motorcycle();
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