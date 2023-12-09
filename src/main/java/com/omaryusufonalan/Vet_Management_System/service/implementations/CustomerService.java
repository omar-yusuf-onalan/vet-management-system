package com.omaryusufonalan.Vet_Management_System.service.implementations;

import com.omaryusufonalan.Vet_Management_System.dto.request.customer.CustomerSaveRequest;
import com.omaryusufonalan.Vet_Management_System.dto.request.customer.CustomerUpdateRequest;
import com.omaryusufonalan.Vet_Management_System.dto.response.PageResponse;
import com.omaryusufonalan.Vet_Management_System.dto.response.customer.CustomerResponse;
import com.omaryusufonalan.Vet_Management_System.entity.Customer;
import com.omaryusufonalan.Vet_Management_System.exception.NotFoundException;
import com.omaryusufonalan.Vet_Management_System.mapper.IModelMapperService;
import com.omaryusufonalan.Vet_Management_System.repository.ICustomerRepo;
import com.omaryusufonalan.Vet_Management_System.result.Message;
import com.omaryusufonalan.Vet_Management_System.result.Result;
import com.omaryusufonalan.Vet_Management_System.result.ResultGenerator;
import com.omaryusufonalan.Vet_Management_System.result.ResultWithData;
import com.omaryusufonalan.Vet_Management_System.service.interfaces.ICustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerService implements ICustomerService {
    private final ICustomerRepo customerRepo;
    private final IModelMapperService modelMapperService;

    @Override
    public ResultWithData<CustomerResponse> save(CustomerSaveRequest customerSaveRequest) {
        Customer customerToBeSaved = this.modelMapperService
                .forRequest()
                .map(customerSaveRequest, Customer.class);
        customerToBeSaved.setId(null);

        Optional<Customer> duplicateCustomer = this.customerRepo.findByPhoneOrMail(customerToBeSaved.getPhone(), customerToBeSaved.getMail());

        if (duplicateCustomer.isPresent())
            throw new DataIntegrityViolationException(Message.DATA_INTEGRITY_VIOLATION);

        this.customerRepo.save(customerToBeSaved);

        CustomerResponse customerResponse = this.modelMapperService
                .forResponse()
                .map(customerToBeSaved, CustomerResponse.class);

        return ResultGenerator.generateCreatedFor(customerResponse);
    }

    @Override
    public ResultWithData<CustomerResponse> get(Long id) {
        Customer customerToBeRetrieved = this.customerRepo.findById(id)
                .orElseThrow(() -> new NotFoundException(Message.generateNotFoundMessage(id, "Customer")));

        CustomerResponse customerResponse = this.modelMapperService
                .forResponse()
                .map(customerToBeRetrieved, CustomerResponse.class);

        return ResultGenerator.generateOkFor(customerResponse);
    }

    @Override
    public ResultWithData<PageResponse<CustomerResponse>> getPage(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        Page<Customer> customerPage = this.customerRepo.findAll(pageable);

        Page<CustomerResponse> customerResponsePage = customerPage
                .map(customer -> this.modelMapperService
                        .forResponse()
                        .map(customer, CustomerResponse.class));

        return ResultGenerator.generatePageFor(customerResponsePage);
    }

    @Override
    public ResultWithData<List<CustomerResponse>> filterByCustomerName(String customerName) {
        List<Customer> filteredCustomers = this.customerRepo.filterByName(customerName)
            .orElseThrow(() -> new NotFoundException(Message.generateNotFoundMessage(customerName, "Customer")));

        List<CustomerResponse> filteredCustomerResponse = filteredCustomers
                .stream().map(customer -> this.modelMapperService
                        .forResponse()
                        .map(customer, CustomerResponse.class)).toList();

        return ResultGenerator.generateOkFor(filteredCustomerResponse);
    }

    @Override
    public ResultWithData<CustomerResponse> update(CustomerUpdateRequest customerUpdateRequest) {
        this.customerRepo.findById(customerUpdateRequest.getId())
                .orElseThrow(() -> new NotFoundException(Message.generateNotFoundMessage(customerUpdateRequest.getId(), "Customer")));

        Customer customerToBeUpdated = this.modelMapperService
                .forRequest()
                .map(customerUpdateRequest, Customer.class);

        Customer checkDuplicateCustomer = this.customerRepo.findByPhoneOrMail(customerToBeUpdated.getPhone(), customerToBeUpdated.getMail())
                .orElseThrow();

        if (dataIntegrityViolated(customerToBeUpdated, checkDuplicateCustomer))
            throw new DataIntegrityViolationException(Message.DATA_INTEGRITY_VIOLATION);

        this.customerRepo.save(customerToBeUpdated);

        CustomerResponse customerResponse = this.modelMapperService
                .forResponse()
                .map(customerToBeUpdated, CustomerResponse.class);

        return ResultGenerator.generateOkFor(customerResponse);
    }

    private static boolean dataIntegrityViolated(Customer customerToBeUpdated, Customer checkDuplicateCustomer) {
        return !customerToBeUpdated.getId().equals(checkDuplicateCustomer.getId());
    }

    @Override
    public Result delete(Long id) {
        Customer customerToBeDeleted = this.customerRepo.findById(id)
                .orElseThrow(() -> new NotFoundException(Message.generateNotFoundMessage(id, "Customer")));

        this.customerRepo.delete(customerToBeDeleted);

        return ResultGenerator.generateOk();
    }
}
