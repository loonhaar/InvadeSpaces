package dev.me.InvadeSpaces;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import dev.me.InvadeSpaces.entity.Space;
import dev.me.InvadeSpaces.repository.SpaceRepository;
import dev.me.InvadeSpaces.service.SpaceService;

@ExtendWith(MockitoExtension.class)
public class SpaceServiceTests {

	@Mock
	private SpaceRepository spaceRepository;

	@InjectMocks
	private SpaceService spaceService;

	@Test
	public void testFindAll() {
		// Arrange
		when(spaceRepository.findAll()).thenReturn(List.of(
			new Space("Test Space", 10),
            new Space("Yet another test space", 11)
		));

		// Act
        var spaces = spaceService.findAll();

		// Assert
        Assertions.assertNotNull(spaces);
        Assertions.assertEquals(2, spaces.size());
        Assertions.assertEquals(10, spaces.get(0).getCapacity());
        verify(spaceRepository).findAll();
	}

    @Test
    public void testFindByNameIgnoreCaseOne() {
        // Arrange
        var name = "Tuff";
        var capacity = 32;
        when(spaceRepository.findByNameIgnoreCase(name)).thenReturn(List.of(
            new Space(name, capacity)
        ));

        // Act
        var spaces = spaceService.findByNameIgnoreCase(name);

        // Assert
        Assertions.assertNotNull(spaces);
        Assertions.assertEquals(1, spaces.size());
        Assertions.assertEquals(name, spaces.get(0).getName());
        Assertions.assertEquals(capacity, spaces.get(0).getCapacity());
        verify(spaceRepository).findByNameIgnoreCase(name);
    }

    @Test
    public void testFindByNameIgnoreCaseMultiple() {
        // Arrange
        var name = "Tuff";
        when(spaceRepository.findByNameIgnoreCase(name)).thenReturn(List.of(
            new Space(name, 2),
            new Space(name, 4),
            new Space(name, 8)
        ));

        // Act
        var spaces = spaceService.findByNameIgnoreCase(name);

        // Assert
        Assertions.assertNotNull(spaces);
        Assertions.assertEquals(3, spaces.size());
        Assertions.assertEquals(name, spaces.get(0).getName());
        Assertions.assertEquals(name, spaces.get(1).getName());
        Assertions.assertEquals(name, spaces.get(2).getName());
        verify(spaceRepository).findByNameIgnoreCase(name);
    }

    @Test
    public void testFindByNameIgnoreCase() {
        // Arrange
        var searchInput = "tuff";
        when(spaceRepository.findByNameIgnoreCase(searchInput)).thenReturn(List.of(
            new Space("Tuff", 4)
        ));

        // Act
        var spaces = spaceService.findByNameIgnoreCase(searchInput);

        // Assert
        Assertions.assertNotNull(spaces);
        Assertions.assertEquals(1, spaces.size());
        Assertions.assertEquals("Tuff", spaces.get(0).getName());
        verify(spaceRepository).findByNameIgnoreCase(searchInput);
    }

    
}
