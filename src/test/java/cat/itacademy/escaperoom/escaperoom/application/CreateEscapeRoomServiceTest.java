package cat.itacademy.escaperoom.escaperoom.application;

import cat.itacademy.project.business_logic.escaperoom.application.CreateEscapeRoomService;
import cat.itacademy.project.business_logic.escaperoom.domain.EscapeRoom;
import cat.itacademy.project.business_logic.escaperoom.domain.EscapeRoomRepository;
import cat.itacademy.project.shared.domain.dtos.escape_room.CreateEscapeRoomDTO;
import cat.itacademy.project.shared.domain.dtos.escape_room.EscapeRoomDTO;
import cat.itacademy.project.shared.domain.exceptions.EmptyFieldException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CreateEscapeRoomServiceTest {
    @Mock
    private EscapeRoomRepository repository;

    @Test
    void execute_an_escape_room() {
        CreateEscapeRoomDTO request = new CreateEscapeRoomDTO("Escape Room 1", "url");
        EscapeRoom mockEscapeRoom = EscapeRoom.create(request);

        when(repository.findByName("Escape Room 1")).thenReturn(Optional.empty()).thenReturn(Optional.of(mockEscapeRoom));

        CreateEscapeRoomService creator = new CreateEscapeRoomService(repository);
        Optional<EscapeRoomDTO> result = creator.execute(request);

        verify(repository).create(any(CreateEscapeRoomDTO.class));
        verify(repository, times(2)).findByName("Escape Room 1");

        assertThat(result).isPresent();
        EscapeRoomDTO dto = result.get();
        assertEquals("Escape Room 1", dto.name());
        assertEquals("url", dto.url());
    }


    @Test
    void throws_exception_when_fields_are_empty() {
        assertThatExceptionOfType(EmptyFieldException.class)
                .isThrownBy(() -> new CreateEscapeRoomDTO("", "url"))
                .withMessage("Name cannot be null or empty");
        assertThatExceptionOfType(EmptyFieldException.class)
                .isThrownBy(() -> new CreateEscapeRoomDTO("name", ""))
                .withMessage("URL cannot be null or empty");
    }

    @Test
    void throws_exception_when_fields_are_null() {
        assertThatExceptionOfType(EmptyFieldException.class)
                .isThrownBy(() -> new CreateEscapeRoomDTO(null, "url"))
                .withMessage("Name cannot be null or empty");
        assertThatExceptionOfType(EmptyFieldException.class)
                .isThrownBy(() -> new CreateEscapeRoomDTO("name", null))
                .withMessage("URL cannot be null or empty");
    }
}
