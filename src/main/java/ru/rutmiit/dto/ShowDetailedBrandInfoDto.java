package ru.rutmiit.dto;


import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public class ShowDetailedBrandInfoDto {
    private String name;
    private LocalDate created;
    private AddModelDto model;

    private LocalDate modified;

    public AddModelDto getModel() {
        return model;
    }

    public void setModel(AddModelDto model) {
        this.model = model;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getCreated() {
        return created;
    }

    public void setCreated(LocalDate created) {
        this.created = created;
    }

    public LocalDate getModified() {
        return modified;
    }

    public void setModified(LocalDate modified) {
        this.modified = modified;
    }
}
