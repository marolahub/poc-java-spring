package com.systembackpoc.entities;
import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "t_segments")
public class Segments implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    private Double price;

    @JsonIgnore
    @OneToMany(mappedBy = "segment")
    private List<Texts> listTexts = new ArrayList<>();

    public Segments(){}

    public Segments(Long id, String description, Double price) {
        this.id = id;
        this.description = description;
        this.price = price;
    }

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getDescription() { return description; }

    public void setDescription(String description) { this.description = description; }

    public Double getPrice() { return price; }

    public void setPrice(Double price) { this.price = price; }

    public List<Texts> getListTexts(){ return this.listTexts; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Segments)) return false;
        Segments segments = (Segments) o;
        return getId().equals(segments.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
