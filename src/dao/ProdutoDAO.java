package dao;

import model.Produto;
import util.ConexaoUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO {
    private ConexaoUtil conexao;
    private Connection conn;

    public ProdutoDAO() {
        this.conexao = new ConexaoUtil();
        this.conn = this.conexao.getConexao();
    }

    public void inserir(Produto produto) {
        String sql = "INSERT INTO produtos(preco, nome, quantidade) VALUES (?, ?, ?)";
        try {
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setFloat(1, produto.getPreco());
            stmt.setString(2, produto.getNome());
            stmt.setInt(3, produto.getQuantidade());
            stmt.execute();
        } catch (SQLException e) {
            System.out.println("Erro ao inserir curso: " +e.getMessage());
        }
    }

    public Produto buscarPorId(int id) {
        Produto produto = null;
        String sql = "SELECT * FROM produtos WHERE id_produto = ?";
        try {
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                produto = new Produto();
                produto.setIdProduto(rs.getInt("id_produto"));
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
        String sql = "UPDATE produtos SET preco = ?, nome = ?, quantidade = ? WHERE id_produto = ?";
        try {
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setFloat(1, produto.getPreco());
            stmt.setString(2, produto.getNome());
            stmt.setInt(3, produto.getQuantidade());
            stmt.setInt(4, produto.getIdProduto());
            stmt.executeUpdate();
            System.out.println("Produto atualizado com sucesso");
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar produto: " + e.getMessage());
        }
    }

    public List<Produto> listar() {
        List<Produto> produtos = new ArrayList<>();
        String sql = "SELECT * FROM produtos";
        try {
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Produto produto = new Produto();
                produto.setIdProduto(rs.getInt("id_produto"));
                produto.setPreco(rs.getFloat("preco"));
                produto.setNome(rs.getString("nome"));
                produto.setQuantidade(rs.getInt("quantidade"));
                produtos.add(produto);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar produtos: " + e.getMessage());
        }
        return produtos;
    }

    public void remover(int id) {
        String sql = "DELETE FROM produtos WHERE id_produto = ?";
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
