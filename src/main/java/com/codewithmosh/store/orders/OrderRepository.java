package com.codewithmosh.store.orders;

import com.codewithmosh.store.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query("select o from Order o where o.customer = :customer")
    List<Order> getAllByUserId(@Param("customer") User customer);
    @Query("select o from Order o where o.id = :orderId")
    Optional<Order> findById(@Param("orderId")Long orderId);
}