package com.systembackpoc.entities.DTO;
import com.systembackpoc.entities.Segments;
import com.systembackpoc.entities.Texts;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SegmentsDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String description;
    private Double price;
    private List<Texts> textsList = new ArrayList<>();

    public SegmentsDTO(){}
    public SegmentsDTO(Segments segments){
        this.id = segments.getId();
        this.description = segments.getDescription();
        this.price = segments.getPrice();
        this.textsList = segments.getListTexts();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public List<Texts> getTextsList() {
        return textsList;
    }

    public void setTextsList(List<Texts> textsList) {
        this.textsList = textsList;
    }
}
