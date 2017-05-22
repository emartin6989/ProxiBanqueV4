package com.gtm.proxiv4.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gtm.proxiv4.metier.Transaction;

public interface TransactionRepository  extends JpaRepository<Transaction,Long> {

}
