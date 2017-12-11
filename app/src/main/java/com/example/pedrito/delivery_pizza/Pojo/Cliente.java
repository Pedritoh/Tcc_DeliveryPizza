package com.example.pedrito.delivery_pizza.Pojo;

public class Cliente {

    public  String id;
    public String Nome;


    public Cliente(String id, String nome) {
        this.id = id;
        this.Nome = nome;
    }

    public String getId(String idUsuario) {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String nome) {
        Nome = nome;
    }
}


