package dao;

import util.ConexaoUtil;
import model.Cliente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {
    private ConexaoUtil conexao;
    private Connection conn;

    public ClienteDAO() {
        this.conexao = new ConexaoUtil();
        this.conn = this.conexao.getConexao();
    }

    public void inserir(Cliente cliente) {
        String sql = "INSERT INTO cliente(telefone, nome) VALUES (?, ?)";
        try {
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setInt(1, cliente.getTelefone());
            stmt.setString(2, cliente.getNome());
            stmt.execute();
            System.out.println("Cliente inserido com sucesso");
        } catch (SQLException e) {
            System.out.println("Erro ao inserir cliente: " + e.getMessage());
        }
    }

    public void atualizar(Cliente cliente) {
        String sql = "UPDATE cliente SET telefone = ?, nome = ? WHERE id_cliente = ?";
        try {
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setInt(1, cliente.getTelefone());
            stmt.setString(2, cliente.getNome());
            stmt.setInt(3, cliente.getIdCliente());
            stmt.execute();
            System.out.println("Cliente atualizado com sucesso");
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar cliente: " + e.getMessage());
        }
    }

    public void remover(int idCliente) {
        String sql = "DELETE FROM cliente WHERE id_cliente = ?";
        try {
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setInt(1, idCliente);
            stmt.execute();
            System.out.println("Cliente removido com sucesso");
        } catch (SQLException e) {
            System.out.println("Erro ao remover cliente: " + e.getMessage());
        }
    }

    public List<Cliente> listar() {
        List<Cliente> clientes = new ArrayList<>();
        String sql = "SELECT * FROM cliente";
        try {
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setIdCliente(rs.getInt("id_cliente"));
                cliente.setTelefone(rs.getInt("telefone"));
                cliente.setNome(rs.getString("nome"));
                clientes.add(cliente);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar clientes: " + e.getMessage());
        }
        return clientes;
    }
}
