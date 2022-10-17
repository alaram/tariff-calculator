package tariffs.calculator.vehicletaxtariff.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Foreign implements Vehicle {

    @JsonFormat(pattern = "yyyyy-mm-dd HH:mm:ss")
    private String date;

    private String vehicleType;
    private String city;
    private String amount;

    @Override
    public String getVehicleType() {
        return "foreign";
    }

    public Foreign() { }

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