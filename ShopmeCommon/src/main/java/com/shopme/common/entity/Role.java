package com.shopme.common.entity;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import jakarta.persistence.Id;

@Entity // to tell the JPA provider(Hibernate) that this class represents a table in the database.
@Table(name="roles") // @Table annotation is used to customize the mapping between the entity and the database table.
public class Role {
	
	@Id // to mark a the property in an entity class as the primary key of the corresponding database table.
	@GeneratedValue(strategy = GenerationType.IDENTITY) // .IDENTITY indicates that the ID primary key values are generated by the database using an auto-incremented column.
	private Integer id;
	
	@Column(length = 100, nullable = false, unique = true) // unique = true means that no two roles can have the same name
	private String name;
	

	@Column(length = 255, nullable = false)
	private String description;
	
	public Role() {
	}
	
	public Role(Integer id) {
		this.id = id;
	}
	public Role(String name) {
		this.name = name;
	}
	// Constructor of Role
	public Role(String name, String description) {
		this.name = name;
		this.description = description;
	}
	
	// Getters and Setters of attributes 
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	
	// Method hashCode()
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
	
	// Method equals()
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Role other = (Role) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return this.name;
	}
	
	
	

}
