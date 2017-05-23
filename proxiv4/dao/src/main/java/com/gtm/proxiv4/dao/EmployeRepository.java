package com.gtm.proxiv4.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gtm.proxiv4.metier.Employe;

@Repository
public interface EmployeRepository extends JpaRepository<Employe, Long> {

	public Employe findOneByEmail(String email);
}
