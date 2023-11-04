package com.example.security.service;

import com.example.security.controller.dto.response.PersonResponseDto;
import com.example.security.domain.entity.Order;
import com.example.security.domain.entity.Person;

import java.util.List;

public interface PersonService {

    boolean existsPerson(Person person);

    void registerUser(Person person);

    void registerAdmin(Person person);

    void register(Person person);

    PersonResponseDto editPersonRole(Long id);

    List<PersonResponseDto> getAll();

    List<Order> getAllOrders();

    void editOrderStatus(int id);
}
