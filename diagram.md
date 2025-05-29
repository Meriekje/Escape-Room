# Create Theme Use Case UML Diagram

```mermaid
classDiagram
    class CreateThemeMenu {
        -Integer escaperoom_id
        -String name
        -String description
        +execute(): Optional<Void>
        +getUSerInfo(): CreateThemeDTO
    }
    
    class CreateThemeController {
        -CreateNewThemeService service
        +CreateThemeController()
        +execute(CreateThemeDTO): void
    }
    
    class CreateNewThemeService {
        -ThemeRepository repo
        +CreateNewThemeService(ThemeRepository)
        +execute(CreateThemeDTO): void
    }
    
    class ThemeRepository {
        <<interface>>
        +create(CreateThemeDTO): void
        +update(Theme): void
        +delete(int): void
        +findById(int): Optional<ThemeDTO>
        +findAll(int): List<ThemeDTO>
        +findByName(String): Optional<Theme>
    }
    
    class ThemeMySQLRepository {
        -Connection connection
        +ThemeMySQLRepository(Connection)
        +create(CreateThemeDTO): void
        +update(Theme): void
        +delete(int): void
        +findById(int): Optional<ThemeDTO>
        +findAll(int): List<ThemeDTO>
        +findByName(String): Optional<Theme>
    }
    
    class Theme {
        -int id
        -String name
        -String description
        -EscapeRoom escapeRoom
        +Theme(String, String, EscapeRoom)
        -Theme(int, String, String, EscapeRoom)
        +static fromDatabase(ThemeDTO): Theme
        -static getEscaperoomFromDto(ThemeDTO): EscapeRoom
        -static getEscaperoomFromDto(CreateThemeDTO): EscapeRoom
        +static create(CreateThemeDTO): Theme
        +toDTO(): ThemeDTO
    }
    
    class CreateThemeDTO {
        +String name
        +String description
        +int escapeRoomId
        +CreateThemeDTO(String, String, int)
    }
    
    class ThemeDTO {
        +int id
        +String name
        +String description
        +int escapeRoomId
    }
    
    CreateThemeMenu ..> CreateThemeDTO : creates
    CreateThemeMenu ..> CreateThemeController : uses
    CreateThemeController ..> CreateNewThemeService : uses
    CreateNewThemeService ..> ThemeRepository : uses
    ThemeMySQLRepository ..|> ThemeRepository : implements
    ThemeMySQLRepository ..> CreateThemeDTO : uses
    Theme ..> ThemeDTO : creates
    Theme ..> CreateThemeDTO : uses
```

## Sequence Diagram for Create Theme Use Case

```mermaid
sequenceDiagram
    participant User
    participant CreateThemeMenu
    participant CreateThemeController
    participant CreateNewThemeService
    participant ThemeMySQLRepository
    participant Database
    
    User->>CreateThemeMenu: Input theme details
    CreateThemeMenu->>CreateThemeMenu: Validate input
    CreateThemeMenu->>CreateThemeDTO: Create DTO
    CreateThemeMenu->>CreateThemeController: execute(CreateThemeDTO)
    CreateThemeController->>CreateNewThemeService: execute(CreateThemeDTO)
    CreateNewThemeService->>ThemeMySQLRepository: create(CreateThemeDTO)
    ThemeMySQLRepository->>Database: INSERT INTO themes
    Database-->>ThemeMySQLRepository: Success
    ThemeMySQLRepository-->>CreateNewThemeService: Return
    CreateNewThemeService-->>CreateThemeController: Return
    CreateThemeController-->>CreateThemeMenu: Return
    CreateThemeMenu-->>User: Display success
```

## Component Descriptions

### Frontend
- **CreateThemeMenu**: User interface component that collects theme details from the user, validates them, and creates a CreateThemeDTO to pass to the controller.

### API Layer
- **CreateThemeController**: Acts as an intermediary between the frontend and the business logic. It instantiates the repository and service, and delegates the execution to the service.

### Business Logic
- **CreateNewThemeService**: Contains the business logic for creating a new theme. It receives a CreateThemeDTO and passes it to the repository.
- **Theme**: Domain object representing a theme with properties like id, name, description, and associated escape room.

### Data Access
- **ThemeRepository**: Interface defining methods for theme data operations.
- **ThemeMySQLRepository**: Implementation of ThemeRepository that interacts with a MySQL database.

### Data Transfer Objects
- **CreateThemeDTO**: DTO containing data needed to create a new theme (name, description, escapeRoomId).
- **ThemeDTO**: DTO representing a theme with all its properties (id, name, description, escapeRoomId).