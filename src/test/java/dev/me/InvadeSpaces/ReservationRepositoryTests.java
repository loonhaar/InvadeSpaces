package dev.me.InvadeSpaces;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;

import dev.me.InvadeSpaces.entity.Reservation;
import dev.me.InvadeSpaces.entity.Space;
import dev.me.InvadeSpaces.repository.ReservationRepository;
import dev.me.InvadeSpaces.repository.SpaceRepository;

@DataJpaTest
public class ReservationRepositoryTests {

	@Autowired
	private ReservationRepository reservaitonRepository;

	@Autowired
	private SpaceRepository spaceRepository;

	@Test
	public void test() {
		var space = new Space(1L , "Wasabi", 14);
		spaceRepository.save(space);
		var reservation = new Reservation(
			1L,
			LocalDateTime.of(2027, 1, 26, 12, 0),
			LocalDateTime.of(2027, 1, 26, 16, 0),
			space
		);

		var saved = reservaitonRepository.save(reservation);

		Assertions.assertTrue(reservation.equals(saved));
	}
}
