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
INSERT INTO Users(email, password, firstName, lastName, avatarURL) VALUES ('admin@recipee.io', 'admin', 'Ad', 'Min', ''), ('user@recipee.io', 'user', 'Jean', 'Michel', '');
INSERT INTO UserRecipe(idUser, idRecipes, lastCooked) VALUES (1, 1, DATE '2023-01-01');
INSERT INTO UserRecipe(idUser, idRecipes, lastCooked) VALUES (2, 1, DATE '2023-05-05');
INSERT INTO UserRecipe(idUser, idRecipes, lastCooked) VALUES (1, 2, DATE '2023-01-03');

SELECT r.name, r.ingredients, r.preparationTime, r.instructions, r.cookingTime, r.idCategory, rt.idRecipes FROM RecipeTags rt INNER JOIN Recipes r ON rt.idRecipes = r.id WHERE rt.idTags = 3;
SELECT r.id, r.name, r.ingredients, r.preparationTime, r.instructions, r.cookingTime, c.id FROM Recipes r INNER JOIN Categories c on r.idCategory = c.id WHERE c.id = 1;

SELECT t.name, rt.idRecipes, rt.idTags AS id FROM RecipeTags rt INNER JOIN Tags t ON t.id = rt.idTags WHERE idRecipes = 1;

SELECT r.id, r.name, r.ingredients, r.preparationTime, r.instructions, r.cookingTime, r.idCategory, ur.lastCooked, ur.idUser FROM Recipes r JOIN UserRecipe ur WHERE r.id = 1 AND ur.idUser = 1;
UPDATE UserRecipe SET lastCooked = DATE '2025-05-01' WHERE idUser = 1 AND idRecipes = 1;

SELECT r.id, r.name, r.ingredients, r.preparationTime, r.instructions, r.cookingTime, r.idCategory, ur.lastCooked, ur.idUser FROM Recipes r JOIN UserRecipe ur WHERE NOT EXISTS ( SELECT 1 FROM UserRecipe ur WHERE ur.idRecipes = r.id AND ur.idUser = 1 AND ur.lastCooked >= DATE_SUB(CURDATE(), INTERVAL 6 DAY) ) ORDER BY RAND() LIMIT 1;

SELECT r.id, r.name, c.name, t.name FROM Recipes r
    INNER JOIN RecipeTags rt ON r.id = rt.idRecipes
    INNER JOIN Tags t ON rt.idTags = t.id
    INNER JOIN Categories c ON r.idCategory = c.id
    WHERE c.name LIKE '%urry%' OR r.name LIKE '%urry%' OR t.name LIKE '%urry%' ;

SELECT r.id, r.name, t.name FROM Recipes r
     INNER JOIN RecipeTags rt ON r.id = rt.idRecipes
     INNER JOIN Tags t ON rt.idTags = t.id

SELECT t.name, rt.idRecipes, rt.idTags AS id  FROM RecipeTags rt INNER JOIN Tags t ON t.id = rt.idTags WHERE idRecipes = 1;

SELECT DISTINCT Recipes.*
FROM Categories
         LEFT JOIN Recipes ON Categories.id = Recipes.idCategory
         LEFT JOIN RecipeTags ON Recipes.id = RecipeTags.idRecipes
         LEFT JOIN Tags ON RecipeTags.idTags = Tags.id
WHERE Categories.name LIKE '%urr%'
   OR Recipes.name LIKE '%urr%'
   OR Recipes.ingredients LIKE '%urr%'
   OR Recipes.instructions LIKE '%urr%'
   OR Tags.name LIKE '%urr%';

SELECT DISTINCT r.*
FROM Categories c
         LEFT JOIN Recipes r ON c.id = r.idCategory
         LEFT JOIN RecipeTags rt ON r.id = rt.idRecipes
         LEFT JOIN Tags t ON rt.idTags = t.id
WHERE c.name LIKE '%urr%'
   OR r.name LIKE '%urr%'
   OR t.name LIKE '%urr%';

