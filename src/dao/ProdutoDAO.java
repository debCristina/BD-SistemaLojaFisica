package dao;

import util.ConexaoUtil;
import model.Produto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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

    public Produto buscarPorId(int id) {
        Produto produto = null;
        String sql = "SELECT * FROM produto WHERE id_produto = ?";
        try {
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                produto = new Produto();
                produto.setId(rs.getInt("id_produto"));
                produto.setNome(rs.getString("nome"));
                produto.setPreco(rs.getFloat("preco"));
                produto.setQuantidade(rs.getInt("quantidade"));
            }
            return produto;
        } catch (SQLException e) {
            System.out.println("Erro ao buscar produto: " + e.getMessage());
            return null;
        }
    }

    public void atualizar(Produto produto) {
        String sql = "UPDATE produto SET preco = ?, nome = ?, quantidade = ? WHERE id_produto = ?";
        try {
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setFloat(1, produto.getPreco());
            stmt.setString(2, produto.getNome());
            stmt.setInt(3, produto.getQuantidade());
            stmt.setInt(4, produto.getId());
            stmt.executeUpdate();
            System.out.println("Produto atualizado com sucesso");
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar produto: " + e.getMessage());
        }
    }

    public void deletar(int id) {
        String sql = "DELETE FROM produto WHERE id_produto = ?";
        try {
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.execute();
            System.out.println("Produto deletado com sucesso");
        } catch (SQLException e) {
            System.out.println("Erro ao deletar produto: " + e.getMessage());
        }
    }
}
