import conexao.ConexaoUtil;
import dao.ProdutoDAO;
import model.Produto;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        ProdutoDAO produtoDAO = new ProdutoDAO();

        System.out.println("Nome do produto");
        String nome = scanner.nextLine();

        System.out.println("Preco: ");
        float preco = scanner.nextFloat();

        System.out.println("Quantidade");
        int quantidade= scanner.nextInt();

        Produto produto = new Produto(0, preco, nome, quantidade);
        produtoDAO.inserir(produto);
    }
}
