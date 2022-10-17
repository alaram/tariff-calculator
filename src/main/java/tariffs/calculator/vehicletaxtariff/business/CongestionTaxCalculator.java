package tariffs.calculator.vehicletaxtariff.business;

import org.springframework.stereotype.Component;

import tariffs.calculator.vehicletaxtariff.domain.Vehicle;

import java.util.Map;
import java.util.Date;
import java.util.HashMap;
import java.util.Calendar;

@Component
public class CongestionTaxCalculator {

    private static Map<String, Integer> tollFreeVehicles = new HashMap<>();

    static {
        tollFreeVehicles.put("motorcycle", 0);
        tollFreeVehicles.put("bus", 1);
        tollFreeVehicles.put("emergency", 2);
        tollFreeVehicles.put("diplomat", 3);
        tollFreeVehicles.put("foreign", 4);
        tollFreeVehicles.put("military", 5);
    }

    /**
     *
     * @param vehicle
     * @param dates
     * @return
     */
    public int getTax(Vehicle vehicle, Date[] dates) {
        Date intervalStart = dates[0];
        int totalFee = 0;

        for (int i = 0; i < dates.length; i++) {
            Date date = dates[i];
            int nextFee = GetTollFee(date, vehicle);
            int tempFee = GetTollFee(intervalStart, vehicle);

            long diffInMillis = date.getTime() - intervalStart.getTime();
            long minutes = diffInMillis/1000/60;

            if (minutes <= 60) {
                if (totalFee > 0) totalFee -= tempFee;
                if (nextFee >= tempFee) tempFee = nextFee;
                totalFee += tempFee;
            }
            else {
                totalFee += nextFee;
            }
        }

        if (totalFee > 60) totalFee = 60;
        return totalFee;
    }

    /**
     *
     * @param date
     * @param vehicle
     * @return
     */
    private int GetTollFee(Date date, Vehicle vehicle) {
        if (IsTollFreeDate(date) || IsTollFreeVehicle(vehicle)) return 0;

        int hour = date.getHours();
        int minute = date.getMinutes();

        if (hour == 6 && minute >= 0 && minute <= 29) return 8;
        else if (hour == 6 && minute >= 30 && minute <= 59) return 13;
        else if (hour == 7 && minute >= 0 && minute <= 59) return 18;
        else if (hour == 8 && minute >= 0 && minute <= 29) return 13;
        else if (hour >= 8 && hour <= 14 && minute >= 30 && minute <= 59) return 8;
        else if (hour == 15 && minute >= 0 && minute <= 29) return 13;
        else if (hour == 15 && minute >= 0 || hour == 16 && minute <= 59) return 18;
        else if (hour == 17 && minute >= 0 && minute <= 59) return 13;
        else if (hour == 18 && minute >= 0 && minute <= 29) return 8;
        else return 0;
    }

    /**
     *
     * @param vehicle
     * @return
     */
    private boolean IsTollFreeVehicle(Vehicle vehicle) {
        if (vehicle == null) return false;
        String vehicleType = vehicle.getVehicleType();
        return tollFreeVehicles.containsKey(vehicleType);
    }

    /**
     *
     * @param date
     * @return
     */
    private boolean IsTollFreeDate(Date date) {
        int year = date.getYear();
        int month = date.getMonth() + 1;
        int day = date.getDay() + 1;
        int dayOfMonth = date.getDate();

        if (day == Calendar.SATURDAY || day == Calendar.SUNDAY) return true;

        if (year == 2013) {
            if ((month == 1 && dayOfMonth == 1) ||
                (month == 3 && (dayOfMonth == 28 || dayOfMonth == 29)) ||
                (month == 4 && (dayOfMonth == 1 || dayOfMonth == 30)) ||
                (month == 5 && (dayOfMonth == 1 || dayOfMonth == 8 || dayOfMonth == 9)) ||
                (month == 6 && (dayOfMonth == 5 || dayOfMonth == 6 || dayOfMonth == 21)) ||
                (month == 7) ||
                (month == 11 && dayOfMonth == 1) ||
                (month == 12 && (dayOfMonth == 24 || dayOfMonth == 25 || dayOfMonth == 26 || dayOfMonth == 31))) {
                return true;
            }
        }
        return false;
    }
}