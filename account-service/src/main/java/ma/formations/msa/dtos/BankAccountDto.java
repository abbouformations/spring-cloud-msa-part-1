package ma.formations.msa.dtos;

import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class BankAccountDto {

    private String id;
    private Double balance;
    private LocalDate createdAt;
    private String type;
    private String currency;
    private Long customerId;
    private CustomerDto theCustomer;
}
