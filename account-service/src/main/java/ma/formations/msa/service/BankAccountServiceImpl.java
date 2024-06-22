package ma.formations.msa.service;

import lombok.AllArgsConstructor;
import ma.formations.msa.clients.CustomerRestClient;
import ma.formations.msa.dtos.BankAccountDto;
import ma.formations.msa.entities.BankAccount;
import ma.formations.msa.repository.BankAccountRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class BankAccountServiceImpl implements IBankAccountservice {
    private BankAccountRepository bankAccountRepository;
    private CustomerRestClient customerRestClient;
    private ModelMapper modelMapper;

    @Override
    public List<BankAccountDto> getAllAccount() {
        List<BankAccountDto> accounts = bankAccountRepository.findAll().
                stream().map(bo -> modelMapper.map(bo, BankAccountDto.class)).
                toList();

        return accounts.stream().map(account -> {
            account.setTheCustomer(customerRestClient.getCustomerById(account.getCustomerId()));
            return account;
        }).toList();

    }

    @Override
    public BankAccountDto getAccountById(String id) {
        BankAccountDto account = modelMapper.map(bankAccountRepository.findById(id).
                        orElseThrow(() -> new RuntimeException(String.format("No Account %d exist", id))),
                BankAccountDto.class);
        account.setTheCustomer(customerRestClient.getCustomerById(account.getCustomerId()));
        return account;
    }

    @Override
    public String createAccount(BankAccountDto bankAccountDto) {
        bankAccountRepository.save(modelMapper.map(bankAccountDto, BankAccount.class));
        return String.format("Account %s is created with success", bankAccountDto.getId());
    }
}
