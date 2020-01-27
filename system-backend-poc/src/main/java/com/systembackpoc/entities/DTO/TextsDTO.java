package com.systembackpoc.entities.DTO;

import com.systembackpoc.entities.Clients;
import com.systembackpoc.entities.Records;
import com.systembackpoc.entities.Segments;
import com.systembackpoc.entities.Texts;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class TextsDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String slug;
    private String description;
    private Double price;
    private String pathText;
    private Clients client;
    private Segments segment;
    private List<Records> records = new ArrayList<>();

    public TextsDTO(){}
    public TextsDTO(Texts text){
        this.id = text.getId();
        this.description = text.getDescription();
        this.price = text.getPrice();
        this.pathText = text.getPathText();
        this.client = text.getClient();
        this.segment = text.getSegment();
        this.records = text.getRecords();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
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

    public String getPathText() {
        return pathText;
    }

    public void setPathText(String pathText) {
        this.pathText = pathText;
    }

    public Clients getClient() {
        return client;
    }

    public void setClient(Clients client) {
        this.client = client;
    }

    public Segments getSegment() {
        return segment;
    }

    public void setSegment(Segments segment) {
        this.segment = segment;
    }

    public List<Records> getRecords() {
        return records;
    }

    public void setRecords(List<Records> records) {
        this.records = records;
    }
}
