package dev.me.InvadeSpaces;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class SpaceService {

	private final SpaceRepository spaceRepository;

	public SpaceService(SpaceRepository spaceRepository) {
		this.spaceRepository = spaceRepository;
	}

	public List<Space> findAll() {
		return spaceRepository.findAll();
	}
}
