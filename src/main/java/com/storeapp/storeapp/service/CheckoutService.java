package com.storeapp.storeapp.service;

import com.storeapp.storeapp.dto.Purchase;
import com.storeapp.storeapp.dto.PurchaseResponse;

public interface CheckoutService {

    PurchaseResponse placeOrder(Purchase purchase);
}
