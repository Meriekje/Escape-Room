package cat.itacademy.project.business_logic.customer.application;

import cat.itacademy.project.business_logic.customer.domain.CustomerRepository;
import cat.itacademy.project.shared.domain.dtos.customer.CustomerDTO;

import java.util.Optional;

public class FindCustomerByEmailService {
    private final CustomerRepository repo;

    public FindCustomerByEmailService(CustomerRepository repo) {
        this.repo = repo;
    }

    public Optional<CustomerDTO> execute(String email) {
        return repo.findByEmail(email);
    }
}
