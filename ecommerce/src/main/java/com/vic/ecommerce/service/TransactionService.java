package com.vic.ecommerce.service;

import com.vic.ecommerce.model.Order;
import com.vic.ecommerce.model.Seller;
import com.vic.ecommerce.model.Transaction;

import java.util.List;

public interface TransactionService {

    Transaction createTransaction(Order order);
    List<Transaction> getTransactionBySeller(Seller seller);
    List<Transaction>getAllTransactions();
}
