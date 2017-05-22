package com.gtm.proxiv4.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gtm.proxiv4.metier.Client;

public interface ClientRepository extends JpaRepository<Client,Long> {

}
