package dao;

import util.ConexaoUtil;
import model.Estoque;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EstoqueDAO {
    private ConexaoUtil conexao;
    private Connection conn;

    public EstoqueDAO() {
        this.conexao = new ConexaoUtil();
        this.conn = this.conexao.getConexao();
    }

    public void inserir(Estoque estoque) {
        String sql = "INSERT INTO estoque(id_produto, quantidade) VALUES (?, ?)";
        try {
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setInt(1, estoque.getIdProduto());
            stmt.setInt(2, estoque.getQuantidade());
            stmt.execute();
            System.out.println("Estoque inserido com sucesso");
        } catch (SQLException e) {
            System.out.println("Erro ao inserir estoque: " + e.getMessage());
        }
    }

    public Estoque buscarPorId(int idEstoque) {
        Estoque estoque = null;
        String sql = "SELECT * FROM estoque WHERE id_estoque = ?";
        try {
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setInt(1, idEstoque);
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
        String sql = "UPDATE estoque SET id_produto = ?, quantidade = ? WHERE id_estoque = ?";
        try {
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setInt(1, estoque.getIdProduto());
            stmt.setInt(2, estoque.getQuantidade());
            stmt.setInt(3, estoque.getIdEstoque());
            stmt.executeUpdate();
            System.out.println("Estoque atualizado com sucesso");
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar estoque: " + e.getMessage());
        }
    }

    public void deletar(int idEstoque) {
        String sql = "DELETE FROM estoque WHERE id_estoque = ?";
        try {
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setInt(1, idEstoque);
            stmt.execute();
            System.out.println("Estoque deletado com sucesso");
        } catch (SQLException e) {
            System.out.println("Erro ao deletar estoque: " + e.getMessage());
        }
    }
}
