package cat.itacademy.escaperoom.escaperoom.application;

import cat.itacademy.project.business_logic.escaperoom.application.DeleteEscapeRoomService;
import cat.itacademy.project.business_logic.escaperoom.domain.EscapeRoomRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class DeleteEscapeRoomServiceTest {
    @Mock
    private EscapeRoomRepository repository;

    @Test
    void execute_deletes_escape_room() {
        int idToDelete = 1;
        
        DeleteEscapeRoomService service = new DeleteEscapeRoomService(repository);
        service.execute(idToDelete);
        
        verify(repository).delete(idToDelete);
    }
}