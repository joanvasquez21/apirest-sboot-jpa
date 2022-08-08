package com.joanvasquezboot.app.services;

import java.awt.print.Pageable;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.joanvasquezboot.app.entity.User;
import com.joanvasquezboot.app.repository.UserRepository;


@Service
public class UserServiceImpl implements UserService{

	// Nos inyecte nuestro userRepository
	@Autowired
	private UserRepository userRepository;
	
	// JPA nos implementa metodos transaccionales(@Transactional), le decimos de solo lectura
	// De solo lectura no va a guardar nada en la BBDD, no va a cambiar el estado en la BBDD
	@Override
	@Transactional(readOnly = true)
	public Iterable<User> findAll() {
		return userRepository.findAll ();
	}

	@Override
	@Transactional(readOnly = true)
	public Page<User> findAll(Pageable pageable) {
		return (Page<User>) userRepository.findAll((Sort) pageable);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<User> findById(Long id) {
		return userRepository.findById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public User save(User user) {
		return userRepository.save(user);
	}

	@Override
	@Transactional(readOnly = true)
	public void deleteById(Long id) {
		userRepository.deleteById(id);
		
	}

	@Override
	public Optional<User> findById(long id) {
		// TODO Auto-generated method stub
		return userRepository.findById(id);

	}
	
	
}
