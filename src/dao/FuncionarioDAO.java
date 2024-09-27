package dao;

import model.Cliente;
import util.ConexaoUtil;
import model.Funcionario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FuncionarioDAO {
    private ConexaoUtil conexao;
    private Connection conn;

    public FuncionarioDAO() {
        this.conexao = new ConexaoUtil();
        this.conn = this.conexao.getConexao();
    }

    public void inserir(Funcionario funcionario) {
        String sql = "INSERT INTO funcionarios(nome, cargo) VALUES (?, ?)";
        try {
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setString(1, funcionario.getNome());
            stmt.setString(2, funcionario.getCargo());
            stmt.execute();
        } catch (SQLException e) {
            System.out.println("Erro ao inserir funcionário: " + e.getMessage());
        }
    }

    public Funcionario buscarPorId(int idFuncionario) {
        Funcionario funcionario = null;
        String sql = "SELECT * FROM funcionarios WHERE id_funcionario = ?";
        try {
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setInt(1, idFuncionario);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                funcionario = new Funcionario();
                funcionario.setIdFuncionario(rs.getInt("id_funcionario"));
                funcionario.setNome(rs.getString("nome"));
                funcionario.setCargo(rs.getString("cargo"));
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar funcionário: " + e.getMessage());
        }
        return funcionario;
    }

    public List<Funcionario> listar() {
        List<Funcionario> funcionarios = new ArrayList<>();
        String sql = "SELECT * FROM funcionarios";
        try {
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Funcionario funcionario = new Funcionario();
                funcionario.setIdFuncionario(rs.getInt("id_funcionario"));
                funcionario.setNome(rs.getString("nome"));
                funcionario.setCargo(rs.getString("cargo"));
                funcionarios.add(funcionario);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar funcionarios: " + e.getMessage());
        }
        return funcionarios;
    }

    public void atualizar(Funcionario funcionario) {
        String sql = "UPDATE funcionarios SET nome = ?, cargo = ? WHERE id_funcionario = ?";
        try {
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setString(1, funcionario.getNome());
            stmt.setString(2, funcionario.getCargo());
            stmt.setInt(3, funcionario.getIdFuncionario());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar funcionário: " + e.getMessage());
        }
    }

    public void remover(int idFuncionario) {
        String sql = "DELETE FROM funcionarios WHERE id_funcionario = ?";
        try {
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setInt(1, idFuncionario);
            stmt.execute();
            System.out.println("Funcionário deletado com sucesso");
        } catch (SQLException e) {
            System.out.println("Erro ao deletar funcionário: " + e.getMessage());
        }
    }
}
