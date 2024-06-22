package ma.formations.msa.clients;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import ma.formations.msa.dtos.CustomerDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "CUSTOMER-SERVICE")
public interface CustomerRestClient {
    @GetMapping("/customers/{id}")
    @CircuitBreaker(name = "customerService", fallbackMethod = "getDefaultCustomer")
    CustomerDto getCustomerById(@PathVariable(name = "id") Long id);

    @GetMapping("/customers")
    @CircuitBreaker(name = "customerService", fallbackMethod = "getDefaultAllCustomers")
    List<CustomerDto> getAllCustomers();

    default CustomerDto getDefaultCustomer(Long id, Exception e) {
        return CustomerDto.
                builder().
                id(id).
                lastname("is not available").
                firstname("is not available").
                build();
    }

    default List<CustomerDto> getDefaultAllCustomers(Exception e) {
        return List.of();
    }
}
