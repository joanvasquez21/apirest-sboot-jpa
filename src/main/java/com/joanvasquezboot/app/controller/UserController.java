package com.joanvasquezboot.app.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joanvasquezboot.app.entity.User;
import com.joanvasquezboot.app.services.UserService;

// @RestController - Una anotacion que es una combinacion entre la anotacion Controller y ResponseBody
@RestController
@RequestMapping("/api/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	// Create a new user
	// PostMapping para crear nuestro metodo publico que devovlera una respuesta que sera entidad usuario
	@PostMapping
	public ResponseEntity<?> create (@RequestBody User user){
		return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(user));
	}
	
	// Read an user
	//
	@GetMapping("/{id}")
	public ResponseEntity<?> read(@PathVariable(value="id") Long userId){
		//Aqui llamamo al Optinal de nuestro UserService
		Optional<User> optUser = userService.findById(userId);
		//Si no encuentra un objeto user nos devuelve responseEntity.
		if(!optUser.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		// Si esta presente obtenemos el usuario y lo devolvemos
		return ResponseEntity.ok(optUser);
		
	}
	//Update an User
	//Le pasamos un usuario que tendra los datos nuevos que queramos modificar
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@RequestBody User userDetails, @PathVariable(value="id") Long userId ){
		Optional<User> user = userService.findById(userId);
			if(!user.isPresent()) {
				return ResponseEntity.notFound().build();
			}
			
			//BeanUtils.copyProperties(userDetails, user.get());
			
			user.get().setName(userDetails.getName());
			user.get().setSurname(userDetails.getSurname());
			user.get().setEmail(userDetails.getEmail());
			user.get().setEnabled(userDetails.getEnabled());
			
			// Una vez actualizado devolvemos 
			return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(user.get()));

	}
	// Borrar un usuario
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable(value ="id") Long userId){
		 if(!userService.findById(userId).isPresent()) { // Si no hay un usuario presente
			return ResponseEntity.notFound().build();         // 	devolver un return
		 }
		 userService.deleteById(userId);
		 
		 return ResponseEntity.ok().build(); 
	}
	// Leer todos los usuarios de nuestra BBDD
	@GetMapping
	public List<User> readAll(){
		// Para transformar un Iterable(Iterable) a una lista(List), stream le pasamos el iterable userServie.findAll 
		List<User> users = StreamSupport.stream(userService.findAll().spliterator(), false) //.spliterator iterar sobre los elementos que tenga nuestro iterable, .false sera secuencialmente
										.collect(Collectors.toList()); // Nos convertira la coleccion en una lista 
	
	return users;
	
	}
	
}
