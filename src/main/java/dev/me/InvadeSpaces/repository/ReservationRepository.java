package dev.me.InvadeSpaces.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import dev.me.InvadeSpaces.entity.Reservation;

//@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

	@Query("""
		SELECT r
		FROM Reservation r
		JOIN FETCH r.space
	""")
	List<Reservation> findAllSpaceEmbedding();
}
