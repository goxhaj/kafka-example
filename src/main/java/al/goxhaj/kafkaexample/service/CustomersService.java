package al.goxhaj.kafkaexample.service;

import org.springframework.data.domain.Pageable;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import al.goxhaj.kafkaexample.repository.CustomersRepository;
import al.goxhaj.kafkaexample.repository.mapper.CustomersBidirectionalMapper;
import al.goxhaj.kafkaexample.service.dto.CustomersDto;
import lombok.AllArgsConstructor;

import java.util.List;

@Service
@AllArgsConstructor
public class CustomersService {

    private static final String TOPIC = "test";

    private CustomersRepository customersRepository;
    private CustomersBidirectionalMapper customersBidirectionalMapper;
    private KafkaTemplate<Long, CustomersDto> kafkaTemplate;

    public List<CustomersDto> getAll(Pageable page) {
        return customersBidirectionalMapper.toDtos(customersRepository.findAll(page).getContent());
    }

    public CustomersDto getById(Long id) {
        return customersBidirectionalMapper.toDto(customersRepository.findById(id).orElseThrow(() -> new RuntimeException("Customer not found")));
    }

    public CustomersDto save(CustomersDto customers) {
        kafkaTemplate.send(TOPIC, customers);
        return customersBidirectionalMapper.toDto(customersRepository.save(customersBidirectionalMapper.toEntity(customers)));
    }

    public void delete(Long id) {
        customersRepository.deleteById(id);
    }

}
