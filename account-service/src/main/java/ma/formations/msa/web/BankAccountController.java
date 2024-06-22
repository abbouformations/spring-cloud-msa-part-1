package ma.formations.msa.web;

import lombok.AllArgsConstructor;
import ma.formations.msa.dtos.BankAccountDto;
import ma.formations.msa.service.IBankAccountservice;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class BankAccountController {
    private IBankAccountservice bankAccountservice;


    @GetMapping("/accounts")
    public ResponseEntity<List<BankAccountDto>> getAllAccounts() {
        return new ResponseEntity<>(bankAccountservice.getAllAccount(), HttpStatus.OK);
    }

    @GetMapping("/accounts/{id}")
    public ResponseEntity<BankAccountDto> getAccountById(@PathVariable(name = "id") String id) {
        return new ResponseEntity<>(bankAccountservice.getAccountById(id), HttpStatus.OK);

    }
}
