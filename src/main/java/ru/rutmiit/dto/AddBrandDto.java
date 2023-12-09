package ru.rutmiit.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import ru.rutmiit.utils.validation.UniqueBrandName;

import java.time.LocalDate;

public class AddBrandDto {
    @UniqueBrandName
    private String name;

    @NotEmpty(message = "Brand name cannot be null or empty!")
    @Size(min = 2, message = "Brand name should be at least 2 characters long!")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}