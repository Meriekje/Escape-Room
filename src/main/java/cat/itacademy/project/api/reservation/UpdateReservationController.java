package cat.itacademy.project.api.reservation;

import cat.itacademy.project.business_logic.customer.domain.CustomerRepository;
import cat.itacademy.project.business_logic.customer.infraestructure.CustomerMySQLRepository;
import cat.itacademy.project.business_logic.puzzle.domain.PuzzleRepository;
import cat.itacademy.project.business_logic.puzzle.infrastructure.PuzzleMySQLRepository;
import cat.itacademy.project.business_logic.reservation.application.UpdateReservationService;
import cat.itacademy.project.business_logic.reservation.domain.ReservationRepository;
import cat.itacademy.project.business_logic.reservation.infraestructure.ReservationMySQLRepository;
import cat.itacademy.project.business_logic.room.domain.RoomDecoRepository;
import cat.itacademy.project.business_logic.room.domain.RoomRepository;
import cat.itacademy.project.business_logic.room.infraestructure.RoomDecoMySQLRepository;
import cat.itacademy.project.business_logic.room.infraestructure.RoomMySQLRepository;
import cat.itacademy.project.shared.domain.dtos.reservation.UpdateReservationDTO;
import cat.itacademy.project.shared.domain.events.EventManager;
import cat.itacademy.project.shared.infrastructure.database.mysql.MySqlConnection;

import java.util.Optional;

public class UpdateReservationController {
    private final EventManager eventManager;
    private final UpdateReservationService service;

    public UpdateReservationController() {
        ReservationRepository repo = new ReservationMySQLRepository(MySqlConnection.getInstance());
        CustomerRepository customerRepo = new CustomerMySQLRepository(MySqlConnection.getInstance());
        PuzzleRepository puzzleRepo = new PuzzleMySQLRepository(MySqlConnection.getInstance());
        RoomRepository roomRepo = new RoomMySQLRepository(MySqlConnection.getInstance());
        RoomDecoRepository roomDecoRepo = new RoomDecoMySQLRepository(MySqlConnection.getInstance());
        this.eventManager = EventManager.getInstance();

        this.service = new UpdateReservationService(repo, customerRepo, puzzleRepo, roomRepo, roomDecoRepo);
    }

    public Optional<Void> execute(UpdateReservationDTO request) {

        service.execute(request);
        eventManager.publish("puzzle.completed", request);
        return Optional.empty();
    }
}
