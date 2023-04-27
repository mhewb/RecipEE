package io.m2i.recipee.api.dto;

import io.m2i.recipee.model.Category;
import io.m2i.recipee.model.Tag;

import java.time.LocalDate;
import java.util.List;

public class RecipeDTO {

    private Long id;
    private String name;
    private String ingredients;
    private int preparationTime;
    private String instructions;
    private int cookingTime;
    private Category category;
    private List<Tag> tags;
    private LocalDate lastCookedDate;

    public RecipeDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public int getPreparationTime() {
        return preparationTime;
    }

    public void setPreparationTime(int preparationTime) {
        this.preparationTime = preparationTime;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public int getCookingTime() {
        return cookingTime;
    }

    public void setCookingTime(int cookingTime) {
        this.cookingTime = cookingTime;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public LocalDate getLastCookedDate() {
        return lastCookedDate;
    }

    public void setLastCookedDate(LocalDate lastCookedDate) {
        this.lastCookedDate = lastCookedDate;
    }

}
