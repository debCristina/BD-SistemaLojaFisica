package util;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConexaoUtil {
    private String caminho = "localhost";
    private String porta = "3306";
    private String nomeBancoDeDados = "bd_lojafisica";
    private String usuario = "root";
    private String senha = System.getenv("DB_MYSQL_PASSWORD");

    private String url = "jdbc:mysql://"+caminho+":"+porta+"/"+nomeBancoDeDados+"?serverTimezone=UTC&useSSL=false";

    public Connection getConexao () {
        try {
            Connection conn = DriverManager.getConnection(url, usuario, senha);
            return conn;
        }catch (Exception e){
            System.out.println("Erro ao conectar-se com o banco de dados" +e.getMessage());
            return null;
        }
    }
}
