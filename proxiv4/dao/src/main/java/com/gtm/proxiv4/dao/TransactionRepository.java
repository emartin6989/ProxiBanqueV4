package com.gtm.proxiv4.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gtm.proxiv4.metier.Transaction;

@Repository
public interface TransactionRepository  extends JpaRepository<Transaction,Long> {

}
