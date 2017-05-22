package com.gtm.proxiv4.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gtm.proxiv4.dao.UserRepository;
import com.gtm.proxiv4.metier.User;
@Transactional
@Service
public class ServiceImp implements IService {

	@Autowired
	// @Qualifier("daoImpJPA")
	/// private IDao dao;
	private UserRepository userRepository;

	@Override
	public void ajouterUser(User u) {
		// dao.ajouterUser(u);
		userRepository.save(u);
	}

	@Override
	public List<User> listerUser() {
		// TODO Auto-generated method stub
		// return dao.listerUser();
		return userRepository.findAll();
	}

	@Override
	public void supprimerUser(long id) {
		// dao.supprimerUser(id);
		userRepository.delete(id);
	}

	@Override
	public User trouverUser(long id) {
		// TODO Auto-generated method stub
		// return dao.trouverUser(id);
		return userRepository.findOne(id);
	}

	@Override
	public List<User> listerUserParMc(String nom) {
		// TODO Auto-generated method stub
		// return dao.listerUserParMc(nom);
		return userRepository.findByNomContaining(nom);
	}

	@Override
	public User listerUserParNom(String nom) {
		// TODO Auto-generated method stub
		// return dao.listerUserParNom(nom);
		return userRepository.findFirstByNom(nom);
	}

}
