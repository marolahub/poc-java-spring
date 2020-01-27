package com.systembackpoc.entities;
import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "t_texts")
public class Texts implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String slug;
    private String description;
    private Double price;
    private String pathText;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Clients client;

    @ManyToOne
    @JoinColumn(name = "segment_id")
    private Segments segment;

    @JsonIgnore
    @OneToMany(mappedBy = "text")
    private List<Records> records = new ArrayList<>();

    public Texts(){}

    public Texts(Long id,
                 String slug,
                 String description,
                 Double price,
                 String pathText,
                 Clients client,
                 Segments segment) {
        this.id = id;
        this.slug = slug;
        this.description = description;
        this.price = price;
        this.pathText = pathText;
        this.client = client;
        this.segment = segment;
    }

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getSlug() { return slug; }

    public void setSlug(String slug) { this.slug = slug; }

    public Double getPrice() { return price; }

    public void setPrice(Double price) { this.price = price; }

    public String getPathText(){ return this.pathText; }

    public void setPathText(String pathText){ this.pathText = pathText; }

    public Clients getClient() { return client; }

    public void setClient(Clients client) { this.client = client; }

    public Segments getSegment() { return segment; }

    public void setSegment(Segments segment) { this.segment = segment; }

    public String getDescription(){ return this.description; }

    public void setDescription(String description){ this.description = description; }

    public List<Records> getRecords() { return records; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Texts)) return false;
        Texts texts = (Texts) o;
        return getId().equals(texts.getId()) &&
                getSlug().equals(texts.getSlug());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getSlug());
    }

    @Override
    public String toString() {
        return "Texts{" +
                "slug='" + slug + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
