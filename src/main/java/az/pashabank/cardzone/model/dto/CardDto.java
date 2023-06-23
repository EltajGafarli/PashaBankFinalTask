package az.pashabank.cardzone.model.dto;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.PositiveOrZero;

@AllArgsConstructor
@NoArgsConstructor(force = true)
@Data
@Builder
public class CardDto {
    @NotNull
    private long id;

    @PositiveOrZero(message = "balance cannot be negative number")
    private double balance;

    @NotNull
    private long customerId;

    @NotNull
    @NotBlank
    @Min(value = 16)
    @Pattern(regexp = "(\\d){16}")
    private String pan;
}

