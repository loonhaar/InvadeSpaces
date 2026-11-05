package dev.me.InvadeSpaces.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "Space")
public class Space {

	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column
	@JsonProperty("Name")
	private String name;

	@Column
	@JsonProperty("Capacity")
	private int capacity;

	public Space(String name, int capacity) {
		this.name = name;
		this.capacity = capacity;
	}

	@Override
	public String toString() {
		return String.format("Space{%d ; %s ; %d}", id, name, capacity);
	}
}
