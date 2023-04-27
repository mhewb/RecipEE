DROP DATABASE IF EXISTS RecipEE;
CREATE DATABASE RecipEE;
USE RecipEE;

create table if not exists Categories
(
    id   int auto_increment
        primary key,
    name varchar(100) null,
    constraint name
        unique (name)
);

create table if not exists Recipes
(
    id              int auto_increment primary key,
    name            varchar(250) not null,
    ingredients     text         not null,
    preparationTime int          null,
    instructions    text         not null,
    cookingTime     int          null,
    idCategory      int          not null,
    constraint name
        unique (name),
    constraint recipes_ibfk_1
        foreign key (idCategory) references Categories (id)
);

create index idCategory
    on Recipes (idCategory);

create table if not exists Tags
(
    id   int auto_increment primary key,
    name varchar(100) null,
    constraint name
        unique (name)
);

create table if not exists RecipeTags
(
    idRecipes int not null,
    idTags    int not null,
    primary key (idRecipes, idTags),
    constraint recipetags_ibfk_1
        foreign key (idRecipes) references Recipes (id),
    constraint recipetags_ibfk_2
        foreign key (idTags) references Tags (id)
);

create index idTags
    on RecipeTags (idTags);

create table if not exists Users
(
    id        int auto_increment primary key,
    email     varchar(100) null,
    password  varchar(100) null,
    firstName varchar(100) null,
    lastName  varchar(100) null,
    avatarURL varchar(500) null,
    constraint email
        unique (email)
);

create table if not exists UserRecipe
(
    idUser     int  not null,
    idRecipes  int  not null,
    lastCooked date null,
    primary key (idUser, idRecipes),
    constraint userrecipe_ibfk_1
        foreign key (idUser) references Users (id) on delete cascade,
    constraint userrecipe_ibfk_2
        foreign key (idRecipes) references Recipes (id) on delete cascade
);

create index idRecipes
    on UserRecipe (idRecipes);

