package com.example.pedrito.delivery_pizza.Pojo;

/**
 * Created by Pedrito on 29/10/2017.
 */

public class Pedidos {

    public String idUser ;
    public String NomeUsuario;
    public String NomeProduto;
    public String valorProduto;
    public String borda;
    public String ValorBorda;
    public String subtotal;

    public String bebida;
    public String ValorBebida;
    public String quantidade;
    public String subtotal2;
    public String formaPagamento;
    public String troco;
    public String Endereco;

    public Pedidos(String idUser, String nomeUsuario, String nomeProduto, String valorProduto, String borda, String valorBorda, String subtotal, String bebida, String valorBebida, String quantidade, String subtotal2, String formaPagamento,String troco, String endereco) {
        this.idUser = idUser;
        NomeUsuario = nomeUsuario;
        NomeProduto = nomeProduto;
        this.valorProduto = valorProduto;
        this.borda = borda;
        ValorBorda = valorBorda;
        this.subtotal = subtotal;
        this.bebida = bebida;
        ValorBebida = valorBebida;
        this.quantidade = quantidade;
        this.subtotal2 = subtotal2;
        this.formaPagamento = formaPagamento;
        this.troco = troco;
        Endereco = endereco;
    }

    public Pedidos() {

    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getNomeUsuario() {
        return NomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        NomeUsuario = nomeUsuario;
    }

    public String getNomeProduto() {
        return NomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        NomeProduto = nomeProduto;
    }

    public String getValorProduto() {
        return valorProduto;
    }

    public void setValorProduto(String valorProduto) {
        this.valorProduto = valorProduto;
    }

    public String getBorda() {
        return borda;
    }

    public void setBorda(String borda) {
        this.borda = borda;
    }

    public String getValorBorda() {
        return ValorBorda;
    }

    public void setValorBorda(String valorBorda) {
        ValorBorda = valorBorda;
    }

    public String getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(String subtotal) {
        this.subtotal = subtotal;
    }

    public String getBebida() {
        return bebida;
    }

    public void setBebida(String bebida) {
        this.bebida = bebida;
    }

    public String getValorBebida() {
        return ValorBebida;
    }

    public void setValorBebida(String valorBebida) {
        ValorBebida = valorBebida;
    }

    public String getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(String quantidade) {
        this.quantidade = quantidade;
    }

    public String getSubtotal2() {
        return subtotal2;
    }

    public void setSubtotal2(String subtotal2) {
        this.subtotal2 = subtotal2;
    }

    public String getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(String formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    public String getTroco() {
        return troco;
    }

    public void setTroco(String troco) {
        this.troco = troco;
    }

    public String getEndereco() {
        return Endereco;
    }

    public void setEndereco(String endereco) {
        Endereco = endereco;
    }
}
