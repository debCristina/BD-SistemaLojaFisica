package model;

public class Produto {
    private int idProduto;
    private float preco;
    private String nome;

    private int quantidade;

    public int getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(int id) {
        this.idProduto = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public float getPreco() {
        return preco;
    }

    public void setPreco(float preco) {
        this.preco = preco;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
}
