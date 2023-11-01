package com.example.security.service;

import com.example.security.dto.response.PersonResponseDto;
import com.example.security.entity.Order;
import com.example.security.entity.Person;
import com.example.security.enums.Role;
import com.example.security.enums.Status;
import com.example.security.mapper.PersonMapper;
import com.example.security.repository.OrderRepository;
import com.example.security.repository.PersonRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class PersonService {

    private final PersonRepository personRepository;
    private final PersonMapper personMapper;
    private final OrderRepository orderRepository;


    @Transactional(readOnly = true)
    public boolean existsPerson(Person person) {
        return personRepository.existsByLogin(person.getLogin());
    }

    public void registerUser(Person person) {
        person.setRole(Role.USER);
        register(person);
    }

    public void registerAdmin(Person person) {
        person.setRole(Role.ADMIN);
        register(person);
    }

    @Transactional
    public void register(Person person) {
        person.setPassword(person.getPassword());
        personRepository.save(person);
    }

    @Transactional
    public PersonResponseDto editPersonRole(Long id) {
        Person person = personRepository.findById(id).orElseThrow();
        if (Role.ADMIN.equals(person.getRole())) {
            person.setRole(Role.USER);
        } else {
            person.setRole(Role.ADMIN);
        }
        return personMapper.toResponseDto(person);
    }

    @Transactional
    public List<PersonResponseDto> getAll(){
        return personMapper.responseList(personRepository.findAll());
    }


    @Transactional(readOnly = true)
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Transactional
    public void editOrderStatus(int id) {
        Order order = orderRepository.findById(id).orElseThrow();
        if (Status.Ожидает.equals(order.getStatus())) {
            order.setStatus(Status.Получен);
        }
    }
}
