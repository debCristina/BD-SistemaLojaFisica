package dao;

import conexao.ConexaoUtil;
import model.Produto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ProdutoDAO {
    private ConexaoUtil conexao;
    private Connection conn;

    public ProdutoDAO() {
        this.conexao = new ConexaoUtil();
        this.conn = this.conexao.getConexao();
    }

    public void inserir(Produto produto) {
        String sql = "INSERT INTO produto(preco, nome, quantidade) VALUES (?, ?, ?)";
        try {
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setFloat(1, produto.getPreco());
            stmt.setString(2, produto.getNome());
            stmt.setInt(3, produto.getQuantidade());
            stmt.execute();

            System.out.println("Produto inserido com sucesso");
        } catch (SQLException e) {
            System.out.println("Erro ao inserir curso: " +e.getMessage());
        }
    }


}
