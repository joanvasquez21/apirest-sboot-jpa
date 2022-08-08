package com.joanvasquezboot.app.services;

import java.awt.print.Pageable;
import java.util.Optional;
import org.springframework.data.domain.Page;
import com.joanvasquezboot.app.entity.User;

public interface UserService {
	
	// Declaramos nuestros metodos
	public Iterable<User> findAll();
	
	// Page - paginacion desde el lado del servidor, le pasamos un objeto de tipo Pageable
	public Page<User> findAll(Pageable pageable);
	
	
		public Optional<User> findById(long id);
	
	
	// Metodo para guardar/actualizar entidad
	public User save(User user);
	
	// Para borrar un usuario por id
	public void deleteById(Long id);
	
	
	Optional<User> findById(Long id);
	
}