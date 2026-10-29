package dev.me.InvadeSpaces;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Space")
public class Space {

	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column
	private String name;

	@Column
	private int capacity;

	@Override
	public String toString() {
		return String.format("Space[%d ; %s ; %d]", id, name, capacity);
	}
}
