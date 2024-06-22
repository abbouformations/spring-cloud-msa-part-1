package ma.formations.msa.dtos;

import lombok.*;

@NoArgsConstructor @AllArgsConstructor @Builder @Getter @Setter
public class CustomerDto {
    private Long id;
    private String firstname;
    private String lastname;
}
