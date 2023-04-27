USE RecipEE;
INSERT INTO Tags(id, name) VALUES
    (1, 'Vegan'),
    (2, 'Thai'),
    (3, 'Coréen'),
    (4, 'Rapide'),
    (5, 'Facile'),
    (6, 'Soupe'),
    (7, 'Végétarien');

INSERT INTO Categories(name) VALUES ('Entrée'), ('Plat'), ('Dessert'), ('Snack');

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
    (4, 'Carottes rapées', ' - Des carottes\n - De la vinaigrette', 10, '1)Rapez les carottes\n2) Mélangez les carottes et la vinaigrette', 0, 1);
INSERT INTO RecipeTags(idRecipes, idTags) VALUES (4, 1), (4, 5);


INSERT INTO Users(email, password, firstName, lastName, avatarURL) VALUES
    ('admin@recipee.io', 'admin', 'Ad', 'Min', ''),
    ('user@recipee.io', 'user', 'Jean', 'Michel', '');
INSERT INTO UserRecipe(idUser, idRecipes, lastCooked) VALUES
        (1, 1, DATE '2023-01-01'),
        (1, 4, DATE '2023-03-12'),
        (1, 3, DATE '2023-04-26'),
        (1, 2, DATE '2023-04-25');

SELECT r.name, r.ingredients, r.preparationTime, r.instructions, r.cookingTime, r.idCategory, rt.idRecipes FROM RecipeTags rt INNER JOIN Recipes r ON rt.idRecipes = r.id WHERE rt.idTags = 3;
SELECT r.id, r.name, r.ingredients, r.preparationTime, r.instructions, r.cookingTime, c.id FROM Recipes r INNER JOIN Categories c on r.idCategory = c.id WHERE c.id = 1;

SELECT t.name, rt.idRecipes, rt.idTags AS id FROM RecipeTags rt INNER JOIN Tags t ON t.id = rt.idTags WHERE idRecipes = 1;

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

SELECT lastCooked FROM UserRecipe WHERE idUser = 2 AND idRecipes = 3;

SELECT DISTINCT r.*
FROM Categories c
         LEFT JOIN Recipes r ON c.id = r.idCategory
         LEFT JOIN RecipeTags rt ON r.id = rt.idRecipes
         LEFT JOIN Tags t ON rt.idTags = t.id
WHERE c.name LIKE '%urr%'
   OR r.name LIKE '%urr%'
   OR t.name LIKE '%urr%';

