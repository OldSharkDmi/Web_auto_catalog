package ru.rutmiit.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import ru.rutmiit.models.Brand;
import ru.rutmiit.models.Model;

import java.time.LocalDate;

public class ShowOfferInfoDto {

    public String id;
    private AddModelDto model;
    private String users;
    private Double price;
    private int year;
    private String seller;
    private LocalDate created;
    private ShowDetailedBrandInfoDto showDetailedBrandInfoDto;

    public ShowDetailedBrandInfoDto getShowDetailedBrandInfoDto() {
        return showDetailedBrandInfoDto;
    }

    public void setShowDetailedBrandInfoDto(ShowDetailedBrandInfoDto showDetailedBrandInfoDto) {
        this.showDetailedBrandInfoDto = showDetailedBrandInfoDto;
    }

    public LocalDate getCreated() {
        return created;
    }

    public void setCreated(LocalDate created) {
        this.created = created;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public AddModelDto getModel() {
        return model;
    }

    public void setModel(AddModelDto model) {
        this.model = model;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getSeller() {
        return seller;
    }

    public void setSeller(String seller) {
        this.seller = seller;
    }

    public String getUsers() {
        return users;
    }

    public void setUsers(String users) {
        this.users = users;
    }

}
