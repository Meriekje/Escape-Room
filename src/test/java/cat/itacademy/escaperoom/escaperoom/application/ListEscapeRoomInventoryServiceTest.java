package cat.itacademy.escaperoom.escaperoom.application;

import cat.itacademy.project.business_logic.escaperoom.application.ListEscapeRoomInventoryService;
import cat.itacademy.project.business_logic.escaperoom.domain.EscapeRoomRepository;
import cat.itacademy.project.shared.domain.dtos.deco.DecoDTO;
import cat.itacademy.project.shared.domain.dtos.escape_room.EscapeRoomInventoryDto;
import cat.itacademy.project.shared.domain.dtos.puzzle.PuzzleDTO;
import cat.itacademy.project.shared.domain.dtos.room.RoomDTO;
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
class ListEscapeRoomInventoryServiceTest {
    @Mock
    private EscapeRoomRepository repository;

    @Test
    void execute_returns_escape_room_inventory() {
        int escapeRoomId = 1;
        int themeId = 1;
        
        List<RoomDTO> rooms = Arrays.asList(
            new RoomDTO(1, "Room 1", "easy", 10.0, themeId, "Theme 1")
        );
        
        List<PuzzleDTO> puzzles = Arrays.asList(
            new PuzzleDTO(1, "Puzzle 1", 1, "answer", "story", themeId, 5.0)
        );
        
        List<DecoDTO> decos = Arrays.asList(
            new DecoDTO(1, "Deco 1", "Description", "type", escapeRoomId, 2.0)
        );
        
        EscapeRoomInventoryDto expectedInventory = new EscapeRoomInventoryDto(rooms, puzzles, decos);
        
        when(repository.findInventoryByEscapeRoomId(escapeRoomId)).thenReturn(expectedInventory);
        
        ListEscapeRoomInventoryService service = new ListEscapeRoomInventoryService(repository);
        EscapeRoomInventoryDto result = service.execute(escapeRoomId);
        
        verify(repository).findInventoryByEscapeRoomId(escapeRoomId);
        assertThat(result).isEqualTo(expectedInventory);
    }
}