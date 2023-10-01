package com.demidov.oldgrill;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.hibernate.validator.constraints.CreditCardNumber;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class BarbecueOrder implements Serializable {

    private static final long serialVersionUID = 1L;
    private Long id;
    private Date placedAt;
    @NotBlank(message = "Требуется указать имя заказчика")
    private String deliveryName;
    @NotBlank(message = "Требуется указать улицу")
    private String deliveryStreet;
    @NotBlank(message = "Требуется указать город")
    private String deliveryCity;
    @NotBlank(message = "Требуется указать номер дома")
    private String deliveryHouseNumber;
    @CreditCardNumber(message = "Недействительный номер кредитной карты")
    private String ccNumber;
    @Pattern(regexp = "^(0[1-9]|1[0-2])([\\\\/])([2-9][0-9])$",
            message = "Должно быть отформатировано ММ/ГГ")
    private String ccExpiration;
    @Digits(integer = 3, fraction = 0, message = "Недействительный CVV")
    private String ccCVV;
    private List<Barbecue> barbecues = new ArrayList<>();

    public void addBarbecue(Barbecue barbecue) {
        this.barbecues.add(barbecue);
    }
}
