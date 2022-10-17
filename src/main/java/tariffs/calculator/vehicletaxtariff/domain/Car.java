package tariffs.calculator.vehicletaxtariff.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Id;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import javax.validation.constraints.NotBlank;

import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Car implements Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonFormat(pattern = "yyyyy-mm-dd HH:mm:ss")
    private String date;

    @NotBlank(message = "Vehicle type is required")
    private String vehicleType;

    @NotBlank(message = "City is required")
    private String city;

    private String amount;

    @Override
    public String getVehicleType() {
        return "car";
    }
}