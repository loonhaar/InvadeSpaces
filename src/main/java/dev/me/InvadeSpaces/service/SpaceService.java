package dev.me.InvadeSpaces.service;

import java.util.List;

import org.springframework.stereotype.Service;

import dev.me.InvadeSpaces.entity.Space;
import dev.me.InvadeSpaces.repository.SpaceRepository;

@Service
public class SpaceService {

	private final SpaceRepository spaceRepository;

	public SpaceService(SpaceRepository spaceRepository) {
		this.spaceRepository = spaceRepository;
	}

	public List<Space> findAll() {
		return spaceRepository.findAll();
	}

    public List<Space> findByNameIgnoreCase(String name) {
        return spaceRepository
			.findByNameIgnoreCase(name)
			.orElseThrow(
				() -> new RuntimeException("No spaces found with given name"));
    }

	public Space addSpace(String name, int capacity) {
		Space newSpace = new Space(name, capacity);
		spaceRepository.save(newSpace);
		return newSpace;
	}

	public Space deleteSpace(Long id) {
		Space spaceToDelete = spaceRepository.findById(id).orElseThrow(
			() -> new RuntimeException("No space found with given id"));

		spaceRepository.delete(spaceToDelete);
		return spaceToDelete;
	}

	public Space updateSpace(Long id, String name, int capacity) {
		Space spaceToUpdate = spaceRepository.findById(id).orElseThrow(
			() -> new RuntimeException("No space found with given id"));

		spaceToUpdate.setName(name);
		spaceToUpdate.setCapacity(capacity);
		spaceRepository.save(spaceToUpdate);

		return spaceToUpdate;
	}
}
