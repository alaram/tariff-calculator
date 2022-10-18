package tariffs.calculator.vehicletaxtariff.domain;

public class TestDataCreator {

    public static Car createCar() {
        Car car = new Car();
        car.setDate("2013-01-14 21:00:00");
        car.setCity("Göteborg");
        return car;
    }

    public static Bus createBus(){
        Bus bus = new Bus();
        bus.setDate("2013-01-15 21:00:00");
        bus.setCity("Göteborg");
        return bus;
    }

    public static Diplomat createDiplomat() {
        Diplomat diplomat = new Diplomat();
        diplomat.setDate("2013-02-07 06:23:27");
        diplomat.setCity("Göteborg");
        return diplomat;
    }

    public static Emergency createEmergency() {
        Emergency emergency = new Emergency();
        emergency.setDate("2013-02-07 15:27:00");
        emergency.setCity("Göteborg");
        return emergency;
    }

    public static Foreign createForeign() {
        Foreign foreign = new Foreign();
        foreign.setDate("2013-02-08 06:27:00");
        foreign.setCity("Göteborg");
        return foreign;
    }

    public static Military createMilitary() {
        Military military = new Military();
        military.setDate("2013-02-08 06:20:27");
        military.setCity("Göteborg");
        return military;
    }

    public static Motorcycle createMotorcycle() {
        Motorcycle motorcycle = new Motorcycle();
        motorcycle.setDate("2013-02-08 14:35:00");
        motorcycle.setCity("Göteborg");
        return motorcycle;
    }

    public static Tariff createTariff() {
        Tariff tariff = new Tariff();
        tariff.setTimeInterval("06:00–06:29");
        tariff.setCity("Göteborg");
        tariff.setAmount("8");
        return tariff;
    }
}