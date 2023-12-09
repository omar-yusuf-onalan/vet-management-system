package com.omaryusufonalan.Vet_Management_System.controller;

import com.omaryusufonalan.Vet_Management_System.dto.request.customer.CustomerSaveRequest;
import com.omaryusufonalan.Vet_Management_System.dto.request.customer.CustomerUpdateRequest;
import com.omaryusufonalan.Vet_Management_System.dto.response.PageResponse;
import com.omaryusufonalan.Vet_Management_System.dto.response.customer.CustomerResponse;
import com.omaryusufonalan.Vet_Management_System.result.Result;
import com.omaryusufonalan.Vet_Management_System.result.ResultWithData;
import com.omaryusufonalan.Vet_Management_System.service.interfaces.ICustomerService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vet/v1/customers")
public class CustomerController {
    private final ICustomerService customerService;

    public CustomerController(ICustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResultWithData<CustomerResponse> save(@Valid @RequestBody CustomerSaveRequest customerSaveRequest) {
        return this.customerService.save(customerSaveRequest);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultWithData<CustomerResponse> get(@PathVariable("id") Long id) {
        return this.customerService.get(id);
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultWithData<PageResponse<CustomerResponse>> getPage(
            @RequestParam(name = "page", required = false, defaultValue = "0") int page,
            @RequestParam(name = "pageSize", required = false, defaultValue = "10") int pageSize
    ) {
        return this.customerService.getPage(page, pageSize);
    }

    @GetMapping("/filter")
    @ResponseStatus(HttpStatus.OK)
    public ResultWithData<List<CustomerResponse>> getFilteredCustomersByName(@RequestParam(name = "name") String customerName) {
        return this.customerService.filterByCustomerName(customerName);
    }

    @PutMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultWithData<CustomerResponse> update(@Valid @RequestBody CustomerUpdateRequest customerUpdateRequest) {
        return this.customerService.update(customerUpdateRequest);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Result delete(@PathVariable("id") Long id) {
        return this.customerService.delete(id);
    }
}
