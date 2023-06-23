package az.pashabank.cardzone.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransactionDto {
    @Min(value = 0)
    private double amount;

    @NotNull
    private boolean hasCashback;

    @NotNull
    private String type;
}
