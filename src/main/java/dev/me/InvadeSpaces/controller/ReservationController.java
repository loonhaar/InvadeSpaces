package dev.me.InvadeSpaces.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.me.InvadeSpaces.entity.Reservation;
import dev.me.InvadeSpaces.entity.Space;
import dev.me.InvadeSpaces.service.ReservationService;

@RestController
@RequestMapping("/rsvs")
public class ReservationController {

	private final ReservationService reservationService;

	public ReservationController(ReservationService reservationService) {
		this.reservationService = reservationService;
	}

	@GetMapping
	public List<Reservation> findAll() {
		return reservationService.findAllWithSpaces();
	}

	@GetMapping("/{id}")
	public Reservation findById(Long id) {
		return reservationService.findById(id);
	}

	@PostMapping
	public Reservation addReservation(Long id, LocalDateTime startTime, LocalDateTime endTime, Space space) {
		return reservationService.addReservation(id, startTime, endTime, space);
	}

	@PutMapping
	public Reservation updateReservation(Long id, LocalDateTime startTime, LocalDateTime endTime, Space space) {
		return reservationService.updateReservation(id, startTime, endTime, space);
	}

	@DeleteMapping
	public Reservation deleteReservation(Long id) {
		return reservationService.deleteReservation(id);
	}
}
