package dev.me.InvadeSpaces.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dev.me.InvadeSpaces.entity.Space;
import dev.me.InvadeSpaces.service.SpaceService;

@RestController
@RequestMapping("/spaces")
public class SpaceController {

	private final SpaceService spaceService;

    public record SpaceRequest(String name, int capacity) {}

	public record SpaceResponse(String message, Space space) {}

	public SpaceController(SpaceService spaceService) {
		this.spaceService = spaceService;
	}

	@GetMapping
	public ResponseEntity<List<Space>> findAll() {
		System.out.println(spaceService.findAll().toString());
		return ResponseEntity.ok(spaceService.findAll());
	}

    @GetMapping("{name}")
    public ResponseEntity<List<Space>> findByName(@PathVariable String name) {

		var foundSpaces = spaceService.findByNameIgnoreCase(name);
		if (foundSpaces.isEmpty()) {
			throw new RuntimeException("No spaces with the given name were found");
		}
		return ResponseEntity.ok(foundSpaces);
    }

	@PostMapping
	public ResponseEntity<SpaceResponse> addSpace(@RequestBody SpaceRequest request) {

		Space newSpace = spaceService.addSpace(request.name(), request.capacity());
		var response = new SpaceResponse("Given space was created", newSpace);
		return ResponseEntity.created(null).body(response);
	}

	@DeleteMapping
	public ResponseEntity<SpaceResponse> deleteSpace(@RequestParam Long id) {
		HttpStatus status;
		SpaceResponse response;

		try {
			Space deletedSpace = spaceService.deleteSpace(id);
			response = new SpaceResponse(
				"Given space was deleted", deletedSpace);
			status = HttpStatus.OK;
		} catch (RuntimeException e) {
			response = new SpaceResponse(
				"No space with the given id was found", null);
			status = HttpStatus.NOT_FOUND;
		}

		return ResponseEntity.status(status).body(response);
	}

	@PutMapping
	public ResponseEntity<SpaceResponse> updateSpace(
		@RequestParam Long id,
		@RequestBody SpaceRequest request) {

		HttpStatus status;
		SpaceResponse response;

		try {
			Space updatedSpace = spaceService.updateSpace(id, request.name(), request.capacity());
			response = new SpaceResponse(
				"Given space was updated", updatedSpace);
			status = HttpStatus.OK;
		} catch (RuntimeException e) {
			response = new SpaceResponse(
				"No space with the given id was found", null);
			status = HttpStatus.NOT_FOUND;
		}

		return ResponseEntity.status(status).body(response);
	}
}
