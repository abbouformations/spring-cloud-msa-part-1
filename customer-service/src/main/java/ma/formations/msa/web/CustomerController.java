package ma.formations.msa.web;

import lombok.AllArgsConstructor;
import ma.formations.msa.dtos.CustomerDto;
import ma.formations.msa.service.ICustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class CustomerController {
    private ICustomerService customerService;

    @GetMapping("/customers")
    public ResponseEntity<List<CustomerDto>> getAllCustomers() {
        return new ResponseEntity<>(customerService.getAllCustomers(), HttpStatus.OK);
    }

    @GetMapping("/customers/{id}")
    public ResponseEntity<CustomerDto> getCustomerById(@PathVariable(name = "id") Long id) {
        return new ResponseEntity<>(customerService.getByCustomerId(id), HttpStatus.OK);
    }


    @PostMapping("/customers")
    public ResponseEntity<String> createCustomer(@RequestBody CustomerDto dto) {
        return new ResponseEntity<>(customerService.createCustomer(dto), HttpStatus.CREATED);
    }

    @PutMapping("/customers/{id}")
    public ResponseEntity<String> updateCustomer(@PathVariable(name = "id") Long id, @RequestBody CustomerDto dto) {
        return new ResponseEntity<>(customerService.updateCustomer(id, dto), HttpStatus.CREATED);
    }

    @DeleteMapping("/customers/{id}")
    public ResponseEntity<String> deleteCustomer(@PathVariable(name = "id") Long id) {
        return new ResponseEntity<>(customerService.remove(id), HttpStatus.OK);
    }
}
