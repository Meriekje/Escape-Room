package cat.itacademy.project.business_logic.themes;


import cat.itacademy.project.shared.domain.dtos.ThemeDTO;

import java.util.List;


public class FindAllThemesService {
    private final ThemeRepository repo;

    public FindAllThemesService(ThemeRepository repo) {
        this.repo = repo;
    }

    public List<ThemeDTO> findAll() {
        return repo.findAll()
                .stream()
                .map(Theme::toDTO)
                .toList();
    }
}