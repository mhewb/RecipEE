USE RecipEE;
INSERT INTO Tags(id, name) VALUES (1, 'Vegan'), (2, 'Thai'), (3, 'Coréen'), (4, 'Végétarien');
INSERT INTO Categories(name) VALUES ('STARTER'), ('MAIN'), ('DESSERT'), ('SNACK');
INSERT INTO Recipes(id, name, ingredients, preparationTime, instructions, cookingTime, idCategory) VALUES
                   (1, 'Curry Vert de légumes', 'ingrédients', 10, 'instructions', 30, 2);
INSERT INTO RecipeTags(idRecipes, idTags) VALUES (1, 1), (1, 2);
INSERT INTO Recipes(id, name, ingredients, preparationTime, instructions, cookingTime, idCategory) VALUES
    (2, 'Tteokbokki', 'ingrédients', 5, 'instructions', 20, 2);
INSERT INTO RecipeTags(idRecipes, idTags) VALUES (2, 1), (2, 3);
INSERT INTO Recipes(id, name, ingredients, preparationTime, instructions, cookingTime, idCategory) VALUES
    (3, 'Brownie', 'ingrédients', 20, 'instructions', 30, 3);
INSERT INTO RecipeTags(idRecipes, idTags) VALUES (3, 4);
INSERT INTO Recipes(id, name, ingredients, preparationTime, instructions, cookingTime, idCategory) VALUES
    (4, 'Carottes rapées', 'ingrédients', 10, 'instructions', 0, 1);
INSERT INTO RecipeTags(idRecipes, idTags) VALUES (4, 1);

SELECT r.name, r.ingredients, r.preparationTime, r.instructions, r.cookingTime, r.idCategory, rt.idRecipes FROM RecipeTags rt INNER JOIN Recipes r ON rt.idRecipes = r.id WHERE rt.idTags = 3;
SELECT r.id, r.name, r.ingredients, r.preparationTime, r.instructions, r.cookingTime, c.id FROM Recipes r INNER JOIN Categories c on r.idCategory = c.id WHERE c.id = 1;

SELECT t.name, rt.idRecipes, rt.idTags AS id  FROM RecipeTags rt JOIN Tags t WHERE idRecipes = 1;
SELECT t.name, rt.idRecipes, rt.idTags AS id  FROM RecipeTags rt INNER JOIN Tags t ON t.id = rt.idTags WHERE idRecipes = 1;

