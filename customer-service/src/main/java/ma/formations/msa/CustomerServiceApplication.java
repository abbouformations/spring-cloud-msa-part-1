package ma.formations.msa;

import ma.formations.msa.dtos.CustomerDto;
import ma.formations.msa.service.ICustomerService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CustomerServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CustomerServiceApplication.class, args);
    }

    @Bean
    public CommandLineRunner initDatabase(ICustomerService customerService) {
        return args -> {
            customerService.createCustomer(CustomerDto.builder().firstname("alami").lastname("mohammed").build());
            customerService.createCustomer(CustomerDto.builder().firstname("amrani").lastname("ali").build());
            customerService.createCustomer(CustomerDto.builder().firstname("fohami").lastname("hanan").build());
            customerService.createCustomer(CustomerDto.builder().firstname("salim").lastname("samy").build());
        };
    }

}
