package io.m2i.recipee.api.resources;

import io.m2i.recipee.api.dto.RecipeDTO;
import io.m2i.recipee.model.Category;
import io.m2i.recipee.model.Recipe;
import io.m2i.recipee.service.CategoryService;
import io.m2i.recipee.service.RecipeService;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.ArrayList;
import java.util.List;

@Path("/recipe")
public class RecipeResource {
    RecipeService recipeService = new RecipeService();
    CategoryService categoryService = new CategoryService();

    @GET
    @Produces(value = MediaType.APPLICATION_JSON)
    public Response getAllRecipes(){

        List<RecipeDTO> recipeDTOList = new ArrayList<>();

        for (Recipe recipe: recipeService.findAllRecipes()) {
            recipeDTOList.add(recipe.toDTO());
        }

        return Response
                .status(Response.Status.OK)
                .entity(recipeDTOList)
                .build();
    }

    @Path("{categoryName}")
    @GET
    @Produces(value = MediaType.APPLICATION_JSON)
    public Response getRecipeByCategory(@PathParam("categoryName") String categoryName){

        List<RecipeDTO> recipeDTOList = new ArrayList<>();
        Category category = categoryService.getCategoryByName(categoryName);

        for (Recipe recipe: recipeService.getRecipesByCategory(category)) {
            recipeDTOList.add(recipe.toDTO());
        }

        return Response
                .status(Response.Status.OK)
                .entity(recipeDTOList)
                .build();
    }


}
