package cat.itacademy.project.business_logic.customer.application;

import cat.itacademy.project.business_logic.customer.domain.CustomerRepository;
import cat.itacademy.project.shared.domain.dtos.customer.CustomerDTO;

import java.util.List;

public class FindAllCustomerSubscribedService {
    private final CustomerRepository repo;

    public FindAllCustomerSubscribedService(CustomerRepository repo) {
        this.repo = repo;
    }

    public List<CustomerDTO> execute() {
        return repo.FindAllCustomerSubscribed();
    }
}
