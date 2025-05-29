package cat.itacademy.escaperoom.escaperoom.application;

import cat.itacademy.project.business_logic.escaperoom.application.FindEscapeRoomByIdService;
import cat.itacademy.project.business_logic.escaperoom.domain.EscapeRoomRepository;
import cat.itacademy.project.shared.domain.dtos.escape_room.EscapeRoomDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FindEscapeRoomByIdServiceTest {
    @Mock
    private EscapeRoomRepository repository;

    @Test
    void execute_returns_escape_room_when_found() {
        int idToFind = 1;
        EscapeRoomDTO expectedRoom = new EscapeRoomDTO(idToFind, "Escape Room 1", "url1");
        
        when(repository.findById(idToFind)).thenReturn(Optional.of(expectedRoom));
        
        FindEscapeRoomByIdService service = new FindEscapeRoomByIdService(repository);
        Optional<EscapeRoomDTO> result = service.execute(idToFind);
        
        verify(repository).findById(idToFind);
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(expectedRoom);
    }

    @Test
    void execute_returns_empty_when_not_found() {
        int idToFind = 999;
        
        when(repository.findById(idToFind)).thenReturn(Optional.empty());
        
        FindEscapeRoomByIdService service = new FindEscapeRoomByIdService(repository);
        Optional<EscapeRoomDTO> result = service.execute(idToFind);
        
        verify(repository).findById(idToFind);
        assertThat(result).isEmpty();
    }
}