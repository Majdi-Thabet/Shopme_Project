package com.shopme.admin.user;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.shopme.common.entity.Role;

@DataJpaTest // used to test JPA (Java Persistence API) components in a Spring Boot application
@AutoConfigureTestDatabase(replace = Replace.NONE) // replace = Replace.NONE, it tells Spring Boot not to replace the database configured in the application with an in-memory database (like H2).
@Rollback(false)
public class RoleRepositoryTests {
	
	@Autowired // is used to automatically inject an instance of RoleRepository into this test class.
	private RoleRepository repo; 	// Simplifies access to repository methods (like save, findById, etc.) in tests. Avoids the need to manually instantiate the repository.

	
	@Test
	public void testCreateFirstRole() {
	    // Vérifie si le rôle "Admin" existe déjà
	    Role existingRole = repo.findByName("Admin").orElse(null);

	    if (existingRole == null) {
	        // Si le rôle n'existe pas, crée-le
	        Role roleAdmin = new Role("Admin", "Manage everything");
	        Role savedRole = repo.save(roleAdmin);

	        // Vérifie que le rôle a été correctement enregistré
	        assertThat(savedRole.getId()).isGreaterThan(0);
	    } else {
	        // Si le rôle existe déjà, affiche un message
	        System.out.println("Le rôle 'Admin' existe déjà dans la base de données.");
	    }
	}
	
	@Test
	public void testCreateRestRoles() {
	    Role roleSalesperson = new Role("Salesperson", "manage product price, "
	        + "customers, shipping, orders and sales report");

	    Role roleEditor = new Role("Editor", "manage categories, brands, "
	        + "products, articles and menus");

	    Role roleShipper = new Role("Shipper", "view products, view orders "
	        + "and update order status");

	    Role roleAssistant = new Role("Assistant", "manage questions and reviews");

	    repo.saveAll(List.of(roleSalesperson, roleEditor, roleShipper, roleAssistant));
	}


}

/*Resume :
  we will persist the first role object with the name as Admin and Description is to manage everything.
  
  so we've use :
 @DataJpaTest: Configures a test environment for JPA.

@AutoConfigureTestDatabase(replace = Replace.NONE): Uses the real database instead of an in-memory database.

RoleRepositoryTests: Test class for the RoleRepository.

@Autowired private RoleRepository repo;: Injects the repository for testing.

@Test: Marks a method as a test case.

testCreateFirstRole(): Test method to verify the creation of a role.
  */
