package com.gtm.proxiv4.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gtm.proxiv4.metier.Gerant;

public interface RoleRepository extends JpaRepository<Gerant,Long> {

}
