package ma.formations.msa.service;

import ma.formations.msa.dtos.CustomerDto;

import java.util.List;

public interface ICustomerService {
    List<CustomerDto> getAllCustomers();

    CustomerDto getByCustomerId(Long id);

    String createCustomer(CustomerDto dto);

    String updateCustomer(Long id, CustomerDto dto);

    String remove(Long id);
}

