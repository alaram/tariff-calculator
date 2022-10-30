package tariffs.calculator.vehicletaxtariff.domain;

import javax.persistence.Id;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import javax.validation.constraints.NotBlank;

import lombok.Data;
import lombok.ToString;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@ToString
@EqualsAndHashCode
@NoArgsConstructor
public class Tariff {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "Time interval is required")
    private String timeInterval;

    @NotBlank(message = "City tariff is required")
    private String city;

    @NotBlank(message = "Tariff amount is required")
    private String amount;

    private String year;

    /**
     *
     * @param timeInterval
     * @param city
     * @param amount
     * @param year
     */
    public Tariff(String timeInterval, String city, String amount, String year) {
        this.timeInterval = timeInterval;
        this.city = city;
        this.amount = amount;
        this.year = year;
    }
}