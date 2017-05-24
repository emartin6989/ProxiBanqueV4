package com.gtm.proxiv4.service.intercepteurs;

import java.util.Date;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gtm.proxiv4.dao.TransactionRepository;
import com.gtm.proxiv4.metier.Compte;
import com.gtm.proxiv4.metier.Transaction;

@Service
@Aspect
public class TransactionLog {


	//@Autowired
	//private Transaction transaction;

	@Autowired
	private TransactionRepository transactionRepo;
	
	



	@Pointcut("execution(* *.effectuerVirement(..)) && args(compteDebiteur, compteCrediteur, montant)")
	public void auditer(Compte compteDebiteur, Compte compteCrediteur, double montant) {
	}

	@Around("auditer(compteDebiteur, compteCrediteur, montant)")
	public Object Enregistrement(ProceedingJoinPoint pjp, Compte compteDebiteur, Compte compteCrediteur, double montant)
			throws Throwable {
		
		
		;
		Transaction transaction = new Transaction();
		transaction.setDate(new Date());
		transaction.setCompteCrediteur(compteCrediteur);
		transaction.setCompteDebiteur(compteDebiteur);
		transaction.setMontant(montant);

		transactionRepo.save(transaction);
	
		return pjp.proceed();

		

	}

}
