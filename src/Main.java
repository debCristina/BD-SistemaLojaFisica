import dao.ProdutoDAO;
import model.Produto;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ProdutoDAO produtoDAO = new ProdutoDAO();
        Scanner scanner = new Scanner(System.in);

        // Inserir um novo produto
        Produto produtoNovo = new Produto();
        System.out.println("Inserindo um novo produto...");

        System.out.print("Digite o nome do produto: ");
        produtoNovo.setNome(scanner.nextLine());

        System.out.print("Digite o preço do produto: ");
        produtoNovo.setPreco(scanner.nextFloat());

        System.out.print("Digite a quantidade do produto: ");
        produtoNovo.setQuantidade(scanner.nextInt());

        produtoDAO.inserir(produtoNovo);

        // Buscar produto por ID
        System.out.print("\nDigite o ID do produto para buscar: ");
        int idBusca = scanner.nextInt();
        Produto produtoBuscado = produtoDAO.buscarPorId(idBusca);
        if (produtoBuscado != null) {
            System.out.println("\nProduto encontrado:");
            System.out.println("ID: " + produtoBuscado.getId());
            System.out.println("Nome: " + produtoBuscado.getNome());
            System.out.println("Preço: " + produtoBuscado.getPreco());
            System.out.println("Quantidade: " + produtoBuscado.getQuantidade());
        } else {
            System.out.println("Produto não encontrado.");
        }

        // Atualizar um produto
        System.out.print("\nDigite o ID do produto que deseja atualizar: ");
        int idAtualizar = scanner.nextInt();
        Produto produtoAtualizar = produtoDAO.buscarPorId(idAtualizar);
        if (produtoAtualizar != null) {
            scanner.nextLine(); // Consumir a nova linha
            System.out.print("Digite o novo nome do produto: ");
            produtoAtualizar.setNome(scanner.nextLine());

            System.out.print("Digite o novo preço do produto: ");
            produtoAtualizar.setPreco(scanner.nextFloat());

            System.out.print("Digite a nova quantidade do produto: ");
            produtoAtualizar.setQuantidade(scanner.nextInt());

            produtoDAO.atualizar(produtoAtualizar);
        } else {
            System.out.println("Produto não encontrado para atualização.");
        }

        // Deletar um produto
        System.out.print("\nDigite o ID do produto que deseja deletar: ");
        int idDeletar = scanner.nextInt();
        produtoDAO.deletar(idDeletar);


    }
}
