package com.omaryusufonalan.Vet_Management_System.service.interfaces;

import com.omaryusufonalan.Vet_Management_System.dto.request.customer.CustomerSaveRequest;
import com.omaryusufonalan.Vet_Management_System.dto.request.customer.CustomerUpdateRequest;
import com.omaryusufonalan.Vet_Management_System.dto.response.PageResponse;
import com.omaryusufonalan.Vet_Management_System.dto.response.customer.CustomerResponse;
import com.omaryusufonalan.Vet_Management_System.result.Result;
import com.omaryusufonalan.Vet_Management_System.result.ResultWithData;

import java.util.List;

public interface ICustomerService {
    ResultWithData<CustomerResponse> save(CustomerSaveRequest customerSaveRequest);
    ResultWithData<CustomerResponse> get(Long id);
    ResultWithData<PageResponse<CustomerResponse>> getPage(int page, int pageSize);
    ResultWithData<List<CustomerResponse>> filterByCustomerName(String customerName);
    ResultWithData<CustomerResponse> update(CustomerUpdateRequest customerUpdateRequest);
    Result delete(Long id);
}
