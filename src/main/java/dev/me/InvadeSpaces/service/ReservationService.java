package dev.me.InvadeSpaces.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import dev.me.InvadeSpaces.entity.Reservation;
import dev.me.InvadeSpaces.entity.Space;
import dev.me.InvadeSpaces.repository.ReservationRepository;

@Service
public class ReservationService {

	private final ReservationRepository reservationRepository;

	public ReservationService(ReservationRepository reservationRepository) {
		this.reservationRepository = reservationRepository;
	}

	public List<Reservation> findAll() {
		return reservationRepository.findAll();
	}

	public List<Reservation> findAllWithSpaces() {
		return reservationRepository.findAllSpaceEmbedding();
	}

	public Reservation findById(Long id) {
		return reservationRepository.findById(id).orElseThrow(
			() -> new RuntimeException("No reservation found with given id"));
	}

	public Reservation addReservation(Long id, LocalDateTime startTime, LocalDateTime endTime, Space space) {
		Reservation newReservation = new Reservation(id, startTime, endTime, space);
		reservationRepository.save(newReservation);
		return newReservation;
	}

	public Reservation updateReservation(Long id, LocalDateTime startTime, LocalDateTime endTime, Space space) {
		Reservation reservationToUpdate = reservationRepository.findById(id).orElseThrow(
			() -> new RuntimeException("No reservation found with given id"));

		reservationToUpdate.setStartTime(startTime);
		reservationToUpdate.setEndTime(endTime);
		reservationToUpdate.setSpace(space);
		reservationRepository.save(reservationToUpdate);

		return reservationToUpdate;
	}

	public Reservation deleteReservation(Long id) {
		Reservation reservationToDelete = reservationRepository.findById(id).orElseThrow(
			() -> new RuntimeException("No reservation found with given id"));
		reservationRepository.deleteById(id);
		return reservationToDelete;
	}
}
