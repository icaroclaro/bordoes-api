create database Bordoes;


create table Artista(
	Id int not null AUTO_INCREMENT,
    Nome varchar(40) not null,
    Detalhe varchar(255),
    Habilitado tinyint default 0,
    Instagram varchar(30),
    primary key(Id)
);

create table ImagemArtista(
	Id int not null AUTO_INCREMENT,
    IdArtista int not null,
    Url varchar(255) not null,
    Tipo varchar(10) not null,
    primary key(Id),
    constraint FK_ImagemArtista foreign key (IdArtista)
    references Artista(Id)
);