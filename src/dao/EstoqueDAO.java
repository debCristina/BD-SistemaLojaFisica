package dao;

import model.Estoque;
import util.ConexaoUtil;
import model.Estoque;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EstoqueDAO {
    private ConexaoUtil conexao;
    private Connection conn;

    public EstoqueDAO() {
        this.conexao = new ConexaoUtil();
        this.conn = this.conexao.getConexao();
    }

    public void inserir(Estoque estoque) {
        String sql = "INSERT INTO estoques(id_produto, quantidade) VALUES (?, ?)";
        try {
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setInt(1, estoque.getIdProduto());
            stmt.setInt(2, estoque.getQuantidade());
            stmt.execute();
        } catch (SQLException e) {
            System.out.println("Erro ao inserir estoque: " + e.getMessage());
        }
    }

    public List<Estoque> listar() {
        List<Estoque> produtosEstoque = new ArrayList<>();
        String sql = "SELECT * FROM estoques";
        try {
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Estoque produtoEstoque = new Estoque();
                produtoEstoque.setIdEstoque(rs.getInt("id_estoque"));
                produtoEstoque.setIdProduto(rs.getInt("id_produto"));
                produtoEstoque.setQuantidade(rs.getInt("quantidade"));
                produtosEstoque.add(produtoEstoque);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar estoques: " + e.getMessage());
        }
        return produtosEstoque;
    }

    public Estoque buscarPorId(int idProduto) {
        Estoque estoque = null;
        String sql = "SELECT * FROM estoques WHERE id_produto= ?";
        try {
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setInt(1, idProduto);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                estoque = new Estoque();
                estoque.setIdEstoque(rs.getInt("id_estoque"));
                estoque.setIdProduto(rs.getInt("id_produto"));
                estoque.setQuantidade(rs.getInt("quantidade"));
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar estoque: " + e.getMessage());
        }
        return estoque;
    }

    public void atualizar(Estoque estoque) {
        String sql = "UPDATE estoques SET quantidade = ? WHERE id_estoque = ? AND id_produto = ?";
        try {
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setInt(1, estoque.getQuantidade());
            stmt.setInt(2, estoque.getIdEstoque());
            stmt.setInt(3, estoque.getIdProduto());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar estoque: " + e.getMessage());
        }
    }

    public void remover(int idProduto) {
        String sql = "DELETE FROM estoques WHERE id_produto = ?";
        try {
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setInt(1, idProduto);
            stmt.execute();
        } catch (SQLException e) {
            System.out.println("Erro ao deletar estoque: " + e.getMessage());
        }
    }
}
