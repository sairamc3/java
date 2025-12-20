package com.plurasight.orderservice.repo;

import com.plurasight.orderservice.model.Order;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends CrudRepository<Order, String> {

}
