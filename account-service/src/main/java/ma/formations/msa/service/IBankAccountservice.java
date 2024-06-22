package ma.formations.msa.service;

import ma.formations.msa.dtos.BankAccountDto;

import java.util.List;

public interface IBankAccountservice {
    List<BankAccountDto> getAllAccount();

    BankAccountDto getAccountById(String id);

    String createAccount(BankAccountDto bankAccountDto);


}
