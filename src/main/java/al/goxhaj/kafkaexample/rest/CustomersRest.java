package al.goxhaj.kafkaexample.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import al.goxhaj.kafkaexample.service.CustomersService;
import al.goxhaj.kafkaexample.service.dto.CustomersDto;

import java.util.List;

@RestController
@RequestMapping(path = "customers")
public class CustomersRest {

    @Autowired
    private CustomersService customersService;


    @GetMapping(value = "")
    public ResponseEntity<List<CustomersDto>> getAll(Pageable page) {
        return new ResponseEntity<>(customersService.getAll(page), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<CustomersDto> getById(@PathVariable("id") long id) {
        return new ResponseEntity<>(customersService.getById(id), HttpStatus.OK);
    }

    @PostMapping(value = "")
    public ResponseEntity<CustomersDto> save(@RequestBody CustomersDto customers) {
        return new ResponseEntity<>(customersService.save(customers), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity delete(@PathVariable("id") long id) {
        return new ResponseEntity<>(id, HttpStatus.OK);
    }
}
