package cat.itacademy.escaperoom.escaperoom.application;

import cat.itacademy.project.business_logic.escaperoom.application.FindAllEscapeRoomsService;
import cat.itacademy.project.business_logic.escaperoom.domain.EscapeRoomRepository;
import cat.itacademy.project.shared.domain.dtos.escape_room.EscapeRoomDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FindAllEscapeRoomsServiceTest {
    @Mock
    private EscapeRoomRepository repository;

    @Test
    void findAll_returns_all_escape_rooms() {
        List<EscapeRoomDTO> expectedRooms = Arrays.asList(
            new EscapeRoomDTO(1, "Escape Room 1", "url1"),
            new EscapeRoomDTO(2, "Escape Room 2", "url2")
        );
        
        when(repository.findAll()).thenReturn(expectedRooms);
        
        FindAllEscapeRoomsService service = new FindAllEscapeRoomsService(repository);
        List<EscapeRoomDTO> result = service.findAll();
        
        verify(repository).findAll();
        assertThat(result).isEqualTo(expectedRooms);
    }
}