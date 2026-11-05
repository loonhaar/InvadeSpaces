package dev.me.InvadeSpaces.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dev.me.InvadeSpaces.entity.Space;

@Repository
public interface SpaceRepository extends JpaRepository<Space, Long> {
	List<Space> findByName(String name);
}
