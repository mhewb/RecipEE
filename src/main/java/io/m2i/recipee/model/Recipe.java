package io.m2i.recipee.model;

import java.time.LocalDate;
import java.util.List;

public class Recipe {
    private Long id;
    private String name;
    private String ingredients;
    private int preparationTime;
    private String instructions;
    private int cookingTime;
    private Category category;
    private List<Tag> tags;
    private LocalDate lastCooked;

    //TODO: Add creator ?

    public Recipe() {
    }

    public Recipe(Long id, String name, String ingredients, int preparationTime,
                  String instructions, int cookingTime, Category category, List<Tag> tags, LocalDate lastCooked) {
        this.id = id;
        this.name = name;
        this.ingredients = ingredients;
        this.preparationTime = preparationTime;
        this.instructions = instructions;
        this.cookingTime = cookingTime;
        this.category = category;
        this.tags = tags;
        this.lastCooked = lastCooked;
    }

    public Recipe(String name, String ingredients, int preparationTime, String instructions,
                  int cookingTime, Category category, List<Tag> tags, LocalDate lastCooked) {
        this.name = name;
        this.ingredients = ingredients;
        this.preparationTime = preparationTime;
        this.instructions = instructions;
        this.cookingTime = cookingTime;
        this.category = category;
        this.tags = tags;
        this.lastCooked = lastCooked;
    }

    // TODO: make attribute
    public int calculateTotalTime() {
        return this.getCookingTime() + this.getPreparationTime();
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

    public LocalDate getLastCooked() {
        return lastCooked;
    }

    public void setLastCooked(LocalDate lastCooked) {
        this.lastCooked = lastCooked;
    }
}
