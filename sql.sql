create database boate;
use boate;
CREATE TABLE cliente (
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    cpf VARCHAR(20) NOT NULL,
    nascimento VARCHAR(20) NOT NULL,
    credito FLOAT,
    login VARCHAR(50),
    senha VARCHAR(20) NOT NULL
);

CREATE TABLE funcionario (
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    funcao VARCHAR(20) NOT NULL,
    cpf VARCHAR(20) NOT NULL,
    endereco VARCHAR(100) NOT NULL,
    nascimento VARCHAR(20) NOT NULL,
    agencia VARCHAR(15) NOT NULL,
    conta VARCHAR(15) NOT NULL,
    banco VARCHAR(50) NOT NULL,
    salario FLOAT NOT NULL
);

CREATE TABLE bebidas (
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    tipo VARCHAR(20) NOT NULL,
    quant INT NOT NULL,
    valor FLOAT NOT NULL
);

CREATE TABLE ingredientes (
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    quant INT NOT NULL,
    valor FLOAT NOT NULL
);


CREATE TABLE petiscos (
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(20) NOT NULL,
    descricao VARCHAR(100) NOT NULL,
    valor FLOAT NOT NULL
);

CREATE TABLE usuario (
    id INT NOT NULL PRIMARY KEY,
    nome VARCHAR(40) NOT NULL,
    login VARCHAR(20) NOT NULL,
    senha VARCHAR(20) NOT NULL,
    funcao VARCHAR(20) NOT NULL,
    cpf VARCHAR(20) NOT NULL
);

CREATE TABLE ingressos (
    id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(45) NOT NULL,
    valor FLOAT NOT NULL
);

CREATE TABLE VendaIngresso (
    id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(45) NOT NULL,
    cpf VARCHAR(20) NOT NULL,
    nomeI VARCHAR(30) NOT NULL,
    valor FLOAT NOT NULL,
    dataCompra DATE NOT NULL
);

CREATE TABLE VendaPetiscos (
    id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(45) NOT NULL,
    cpf VARCHAR(20) NOT NULL,
    nomeI VARCHAR(30) NOT NULL,
    descricao VARCHAR(100) NOT NULL,
    quantidade INT NOT NULL,
    valor FLOAT NOT NULL,
    dataCompra DATE NOT NULL
);

CREATE TABLE VendaBebidas (
    id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(45) NOT NULL,
    cpf VARCHAR(20) NOT NULL,
    nomeI VARCHAR(30) NOT NULL,
    quantidade INT NOT NULL,
    valor FLOAT NOT NULL,
    dataCompra DATE NOT NULL
);
CREATE TABLE RecargaCredito (
    id INT NOT NULL,
    nomeFunc VARCHAR(45) NOT NULL,
    login VARCHAR(20) NOT NULL,
    nomecliente VARCHAR(45) NOT NULL,
    logincliente VARCHAR(20) NOT NULL,
    valor FLOAT NOT NULL,
    dataRecarga DATE NOT NULL
);
