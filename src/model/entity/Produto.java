package model.entity;

public class Produto {
    private String numeroChassi;
    private String placa;
    private String modelo;
    private String nome;
    private double valor;

    // Construtor
    public Produto(String numeroChassi, String placa, String modelo, String nome, double valor) {
        this.numeroChassi = numeroChassi;
        this.placa = placa;
        this.modelo = modelo;
        this.nome = nome;
        this.valor = valor;
    }

    // Getters e Setters
    public String getNumeroChassi() {
        return numeroChassi;
    }

    public void setNumeroChassi(String numeroChassi) {
        this.numeroChassi = numeroChassi;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }
}