package cat.itacademy.project.business_logic.deco.domain;

import cat.itacademy.project.shared.domain.dtos.deco.CreateDecoDTO;
import cat.itacademy.project.shared.domain.dtos.deco.DecoDTO;

public class Deco {
    private int id;
    private String name;
    private String description;
    private String type;
    private int escapeRoomId;
    private double price;

    public Deco(int id, String name, String description, String type, int escapeRoomId, double price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.type = type;
        this.escapeRoomId = escapeRoomId;
        this.price = price;
    }

    public Deco(String name, String description, String type, int escapeRoomId, double price) {
        this.name = name;
        this.description = description;
        this.type = type;
        this.escapeRoomId = escapeRoomId;
        this.price = price;
    }

    public static Deco create(CreateDecoDTO dto) {
        return new Deco(dto.name(), dto.description(), dto.type(), dto.escapeRoomId(), dto.price());
    }

    public static Deco fromDatabase(DecoDTO dto) {
        return new Deco(dto.id(), dto.name(), dto.description(), dto.type(), dto.escapeRoomId(), dto.price());
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String value) {
        this.type = type;
    }

    public int getEscapeRoomId() {
        return escapeRoomId;
    }

    public void setEscapeRoomId(int escapeRoomId) {
        this.escapeRoomId = escapeRoomId;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public DecoDTO toDTO() {
        return new DecoDTO(id, name, description, type, escapeRoomId, price);
    }

    @Override
    public String toString() {
        return "Deco{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", type=" + type +
                ", themeId=" + escapeRoomId +
                ", price=" + price +
                '}';
    }


}
