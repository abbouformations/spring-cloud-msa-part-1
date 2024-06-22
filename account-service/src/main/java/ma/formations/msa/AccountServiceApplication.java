package ma.formations.msa;

import ma.formations.msa.clients.CustomerRestClient;
import ma.formations.msa.dtos.BankAccountDto;
import ma.formations.msa.enums.AccountType;
import ma.formations.msa.service.IBankAccountservice;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.util.UUID;

@SpringBootApplication
@EnableFeignClients
public class AccountServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(AccountServiceApplication.class, args);
    }

    @Bean
    public CommandLineRunner initDatabase(IBankAccountservice bankAccountservice, CustomerRestClient customerRestClient) {
        return args -> {
            customerRestClient.getAllCustomers().forEach(
                    customer -> {
                        bankAccountservice.createAccount(BankAccountDto.builder().
                                id(UUID.randomUUID().toString()).
                                balance(Math.random() * 100).
                                createdAt(LocalDate.now()).
                                type(AccountType.CURRENT_ACCOUNT.name()).
                                customerId(customer.getId()).
                                build());
                    }
            );

        };
    }

}
