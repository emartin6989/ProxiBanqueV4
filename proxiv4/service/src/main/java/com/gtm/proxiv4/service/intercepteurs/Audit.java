package com.gtm.proxiv4.service.intercepteurs;

import java.util.Date;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.gtm.proxiv4.dao.TransactionRepository;
import com.gtm.proxiv4.metier.Compte;
import com.gtm.proxiv4.metier.Transaction;

@Controller
@Aspect
public class Audit {

	@Autowired
	private Transaction transaction;


	private TransactionRepository transactionRepo;

	@Pointcut("execution(* *.effectuerVirement(..))")
	public void auditer() {
	}

	@Around("auditer()")
	public Object Enregistrement(ProceedingJoinPoint pjp) throws Throwable {
 System.out.println(pjp);
		final Object[] args = pjp.getArgs();
		System.out.println(args);
		/*Compte compteDebiteur = (Compte) args[1];
		Compte compteCrediteur = (Compte) args[2];
		double montant = (double) args[3];
*/
		/*transaction.setDate(new Date());
		transaction.setCompteCrediteur(compteCrediteur);
		transaction.setCompteDebiteur(compteDebiteur);
		transaction.setMontant(montant);
		transactionRepo.save(transaction);
*/
		return pjp.proceed();

	}

}
