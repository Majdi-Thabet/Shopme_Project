package com.shopme.admin.user;

import static org.assertj.core.api.Assertions.assertThat;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import com.shopme.common.entity.Role;
import com.shopme.common.entity.User;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE) // replace = Replace.NONE indique de ne pas remplacer la base de données configurée par une base en mémoire, ce qui signifie que les tests utiliseront la base de données configurée dans l'application (par exemple, MySQL).
@Rollback(false)
public class UserRepositoryTests {
	@Autowired
	private UserRepository repo;
	
	@Autowired
	private TestEntityManager entityManager;
	
	@Test 
	public void testCreateNewUserWithOneRole() {
		Role roleAdmin = entityManager.find(Role.class, 1);
		User userMaThabet = new User("mathabet@gmail.com", "Ma2024", "Majdi", "Thabet");
		userMaThabet.addRole(roleAdmin);
		
		User savedUser = repo.save(userMaThabet);
		assertThat(savedUser.getId() ).isGreaterThan(0);
	}
	
	// Donenr deux roles à un user : Editor and Assistant
	@Test 
	public void testCreateNewUserWithTwoRoles() {
		
		User userRavi = new User("ravi@gmail.com", "ravi2024", "Ravi", "Kumar");
		Role roleEditor = new Role(3);
		Role roleAssistant = new Role(5);
		
		userRavi.addRole(roleEditor);
		userRavi.addRole(roleAssistant);
		
		User savedUser = repo.save(userRavi);
		assertThat(savedUser.getId() ).isGreaterThan(0);
	}
	
	// Method de test of ListAllUsers
	@Test 
	public void testListAllUsers() {
		Iterable <User> listUsers = repo.findAll();
		listUsers.forEach(user -> System.out.println(user));
		
	}
	
	// Test By User Id
	@Test
	public void testGetUserById() {
		User userMaThabet =  repo.findById(1).get();
		System.out.println(userMaThabet);
		assertThat(userMaThabet).isNotNull();
	}
	
	// Test Update details exiting User(update email)
	@Test
	public void testUpdateUserDetails() {
		User userMaThabet =  repo.findById(1).get();
		userMaThabet.setEnabled(true);
		userMaThabet.setEmail("mathabetjavadev@gmail.com");
		
		repo.save(userMaThabet);	
	}
	
	// Method test Change the role assigned to Ravi Kumar
	@Test
	public void testUpdateUserRoles() {
		User userRavi =  repo.findById(2).get();
		Role roleEditor = new Role(3);
		Role roleSalesperson = new Role(2);
		userRavi.getRoles().remove(roleEditor);
		userRavi.addRole(roleSalesperson);
		
		repo.save(userRavi);
	}
	
	// Method Test Delete User
	@Test 
	public void TestDeleteUser() {
		Integer userId = 2;
		repo.deleteById(userId);
	}
	
	
	
}
