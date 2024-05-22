package com.storeapp.storeapp.dto;

import com.storeapp.storeapp.entity.Address;
import com.storeapp.storeapp.entity.Customer;
import com.storeapp.storeapp.entity.Order;
import com.storeapp.storeapp.entity.OrderItem;
import lombok.Data;

import java.util.Set;

@Data
public class Purchase {

    private Customer customer;
    private Address shippingAddress;
    private Address billingAddress;
    private Order order;
    private Set<OrderItem> orderItems;
}
