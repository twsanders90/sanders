package com.nofluffkitchen.sanders.models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    @NotBlank(message = "Name Required")
    @Size(min = 2, message = "Must contain 2 or more characters")
    private String name;

    @NotBlank(message = "Ingredients Required")
    @Size(min = 2, message = "Ingredients list required")
    private String ingredients;

    @NotBlank(message = "Must be valid User Name")
    @Column(unique = true)
    private String createdBy;

    private String type;
    private String directions;
    private LocalDateTime created;
    private LocalDateTime modified;

    @ManyToOne
    private Category category;

    public Recipe() {
        this.name = "";
        this.ingredients = "";
        this.type = "";
        this.directions = "";
        this.createdBy = "";

    }

    public Recipe(String type, String directions, String name, String ingredients, String createdBy) {
        this.name = name;
        this.ingredients = ingredients;
        this.type = type;
        this.directions = directions;
        this.createdBy = createdBy;

    }

    public long getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDirections() {
        return directions;
    }

    public void setDirections(String directions) {
        this.directions = directions;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public LocalDateTime getModified() {
        return modified;
    }

    public void setModified(LocalDateTime modified) {
        this.modified = modified;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @PrePersist
    public void onCreate(){
        this.setCreated(LocalDateTime.now());
        this.setModified(LocalDateTime.now());
    }
    @PreUpdate
    public void onUpdate(){this.setModified(LocalDateTime.now());}

    @Override
    public String toString(){ return this.name + this.type + this.ingredients + this.directions;}

    @Override
    public boolean equals(Object o){
        if(!(o instanceof Recipe))
            return false;

         Recipe c  = (Recipe) o;
        return this.name.equals(c.name) && this.ingredients.equals(c.ingredients) && this.directions.equals(c.directions);
    }
    @Override
    public int hashCode(){
        return this.name.hashCode();
    }
}
