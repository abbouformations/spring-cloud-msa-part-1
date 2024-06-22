package ma.formations.msa.service;

import lombok.AllArgsConstructor;
import ma.formations.msa.dtos.CustomerDto;
import ma.formations.msa.entities.Customer;
import ma.formations.msa.repository.CustomerRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class CustomerServiceImpl implements ICustomerService {
    private CustomerRepository customerRepository;
    private ModelMapper modelMapper;

    @Override
    public List<CustomerDto> getAllCustomers() {
        return customerRepository.findAll().stream().map(bo -> modelMapper.map(bo, CustomerDto.class)).toList();
    }

    @Override
    public CustomerDto getByCustomerId(Long id) {
        return modelMapper.map(customerRepository.findById(id).
                orElseThrow(() -> new RuntimeException(String.format("No customer with id=%s exist", id))), CustomerDto.class);
    }

    @Override
    public String createCustomer(CustomerDto dto) {
        customerRepository.save(modelMapper.map(dto, Customer.class));
        return String.format("Customer %s is created with success", dto);
    }

    @Override
    public String updateCustomer(Long id, CustomerDto dto) {
        dto.setId(id);
        customerRepository.save(modelMapper.map(dto, Customer.class));
        return String.format("Customer %s is updated with success", dto);
    }

    @Override
    public String remove(Long id) {
        customerRepository.delete(customerRepository.findById(id).
                orElseThrow(() -> new RuntimeException(String.format("No customer with id=%s exist", id))));

        return String.format("Customer : %s is removed with success", id);
    }
}
