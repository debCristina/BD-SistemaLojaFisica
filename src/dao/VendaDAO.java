package dao;

import util.ConexaoUtil;
import model.Venda;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class VendaDAO {
    private ConexaoUtil conexao;
    private Connection conn;

    public VendaDAO() {
        this.conexao = new ConexaoUtil();
        this.conn = this.conexao.getConexao();
    }

    public void inserir(Venda venda) {
        String sql = "INSERT INTO venda(id_funcionario, id_cliente, id_produto, total, data_venda) VALUES (?, ?, ?, ?, ?)";
        try {
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setInt(1, venda.getIdFuncionario());
            stmt.setInt(2, venda.getIdCliente());
            stmt.setInt(3, venda.getIdProduto());
            stmt.setFloat(4, venda.getTotal());
            stmt.setObject(5, venda.getDataVenda());
            stmt.execute();
            System.out.println("Venda inserida com sucesso");
        } catch (SQLException e) {
            System.out.println("Erro ao inserir venda: " + e.getMessage());
        }
    }

    public Venda buscarPorId(int id) {
        Venda venda = null;
        String sql = "SELECT * FROM venda WHERE id_venda = ?";
        try {
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                venda = new Venda();
                venda.setIdVendas(rs.getInt("id_venda"));
                venda.setIdFuncionario(rs.getInt("id_funcionario"));
                venda.setIdCliente(rs.getInt("id_cliente"));
                venda.setIdProduto(rs.getInt("id_produto"));
                venda.setTotal(rs.getFloat("total"));
                venda.setDataVenda(rs.getObject("data_venda", LocalDateTime.class));
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar venda: " + e.getMessage());
        }
        return venda;
    }

    public void atualizar(Venda venda) {
        String sql = "UPDATE venda SET id_funcionario = ?, id_cliente = ?, id_produto = ?, total = ?, data_venda = ? WHERE id_venda = ?";
        try {
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setInt(1, venda.getIdFuncionario());
            stmt.setInt(2, venda.getIdCliente());
            stmt.setInt(3, venda.getIdProduto());
            stmt.setFloat(4, venda.getTotal());
            stmt.setObject(5, venda.getDataVenda());
            stmt.setInt(6, venda.getIdVendas());
            stmt.executeUpdate();
            System.out.println("Venda atualizada com sucesso");
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar venda: " + e.getMessage());
        }
    }

    public void deletar(int id) {
        String sql = "DELETE FROM venda WHERE id_venda = ?";
        try {
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.execute();
            System.out.println("Venda deletada com sucesso");
        } catch (SQLException e) {
            System.out.println("Erro ao deletar venda: " + e.getMessage());
        }
    }
}
