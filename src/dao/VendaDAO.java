package dao;

import model.Funcionario;
import util.ConexaoUtil;
import model.Venda;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class VendaDAO {
    private ConexaoUtil conexao;
    private Connection conn;

    public VendaDAO() {
        this.conexao = new ConexaoUtil();
        this.conn = this.conexao.getConexao();
    }

    public void inserir(Venda venda) {
        String sql = "INSERT INTO vendas(id_funcionario, id_cliente, id_produto, total, data_venda) VALUES (?, ?, ?, ?, ?)";
        try {
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setInt(1, venda.getIdFuncionario());
            stmt.setInt(2, venda.getIdCliente());
            stmt.setInt(3, venda.getIdProduto());
            stmt.setFloat(4, venda.getTotal());
            stmt.setString(5, venda.getDataVenda());

            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro ao inserir venda: " + e.getMessage());
        }
    }

    public List<Venda> listar() {
        List<Venda> vendas = new ArrayList<>();
        String sql = "SELECT * FROM vendas";
        try {
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Venda venda = new Venda();
                venda.setIdVendas(rs.getInt("id_venda"));
                venda.setIdFuncionario(rs.getInt("id_funcionario"));
                venda.setIdCliente(rs.getInt("id_cliente"));
                venda.setIdProduto(rs.getInt("id_produto"));
                venda.setTotal(rs.getFloat("total"));
                venda.setDataVenda(rs.getString("data_venda"));
                vendas.add(venda);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar vendas: " + e.getMessage());
        }
        return vendas;
    }


    public Venda buscarPorId(int id) {
        Venda venda = null;
        String sql = "SELECT * FROM vendas WHERE id_venda = ?";
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
                venda.setDataVenda(rs.getString("data_venda"));
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar venda: " + e.getMessage());
        }
        return venda;
    }

    public void atualizar(Venda venda) {
        String sql = "UPDATE vendas SET id_funcionario = ?, id_cliente = ?, id_produto = ?, total = ?, data_venda = ? WHERE id_venda = ?";
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

    public void remover(int id) {
        String sql = "DELETE FROM vendas WHERE id_venda = ?";
        try {
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.execute();
        } catch (SQLException e) {
            System.out.println("Erro ao deletar venda: " + e.getMessage());
        }
    }
}
