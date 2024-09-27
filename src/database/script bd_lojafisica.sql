create database bd_lojafisica;
use bd_lojafisica;

create table produtos(
	id_produto int primary key auto_increment not null unique,
    preco float not null,
    nome varchar(45) not null,
    quantidade int not null
);

create table estoques(
	id_estoque int primary key not null auto_increment unique,
    id_produto int not null unique,
    quantidade int not null,
	foreign key(id_produto) references produtos(id_produto)
);

create table clientes(
	id_cliente int primary key auto_increment not null unique,
    telefone varchar(11) not null,
    nome varchar(45) not null
);

create table funcionarios(
	id_funcionario int primary key auto_increment not null unique,
    nome varchar(45) not null,
    cargo varchar(45) not null
);


create table vendas(
	id_venda int primary key auto_increment not null unique,
	id_funcionario int not null ,
    id_cliente int not null ,
    id_produto int not null ,
	total float not null,
    data_venda date not null,
    
    foreign key(id_funcionario) references funcionarios(id_funcionario),
	foreign key(id_cliente) references clientes(id_cliente),
	foreign key(id_produto) references produtos(id_produto)
);

