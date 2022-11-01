package tariffs.calculator.vehicletaxtariff.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Bus implements Vehicle {

    @JsonFormat(pattern = "yyyy-mm-dd HH:mm:ss")
    private String date;

    private String vehicleType;
    private String city;
    private String amount;

    public String getVehicleType() {
        return "bus";
    }

    public Bus(){ }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
}