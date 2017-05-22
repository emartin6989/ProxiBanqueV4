package com.gtm.proxiv4.metier;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * La classe ClientParticulier permet de d�finir un client particulier
 */
@Component
@Scope("prototype")
@Entity
@DiscriminatorValue(value="ClientParticulier")
public class ClientParticulier extends Client {

	
}
