import dao.EstoqueDAO;
import dao.ProdutoDAO;
import model.Produto;

import java.util.List;
import java.util.Scanner;

public class MenuProduto {
    public void menuProduto() {
        Produto produto = new Produto();
        ProdutoDAO produtoDAO = new ProdutoDAO();
        Scanner scanner = new Scanner(System.in);
        int opcaoUsuario = 0;

        do {
            System.out.println("""
                                ---- Estoque ----
                                [1] Cadastrar produto
                                [2] Listar produtos
                                [3] Atualizar produto
                                [4] Remover produto
                                [0] Sair""");

            System.out.println("Opcao: ");
            opcaoUsuario = scanner.nextInt();

            switch (opcaoUsuario){
                case 1:
                    cadastrarProduto(scanner, produto, produtoDAO);
                    break;
                case 2:
                    listarProduto(produtoDAO);
                    break;
                case 3:
                    atualizarProduto(scanner, produtoDAO);
                    break;
                case 4:
                    deletarProduto(scanner, produtoDAO);
                    break;
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida");
            }

        }while (opcaoUsuario != 0);


    }

    private void cadastrarProduto(Scanner scanner,Produto produto,ProdutoDAO produtoDAO) {
        System.out.println("---- Cadastro Produto ----");
        System.out.println("Preco do produto: ");
        produto.setPreco(scanner.nextFloat());
        scanner.nextLine();

        System.out.println("Nome do produto: ");
        produto.setNome(scanner.nextLine());

        System.out.println("Quantidade do produto: ");
        produto.setQuantidade(scanner.nextInt());

        produtoDAO.inserir(produto);
        System.out.println("Produto cadastrado com sucesso!\n");
    }

    private void listarProduto(ProdutoDAO produtoDAO) {
        List<Produto> produtos = produtoDAO.listar();
        for(Produto produto : produtos) {
            System.out.printf("""
                                
                                Id: %d
                                Nome: %s
                                Preco: %.2f
                                Quantidade: %d
                                """, produto.getIdProduto(), produto.getNome(), produto.getPreco(), produto.getQuantidade());
        }
    }

    private void atualizarProduto(Scanner scanner,ProdutoDAO produtoDAO) {
        System.out.println("Id para atualização: ");
        int idProduto = scanner.nextInt();
        scanner.nextLine();

        Produto produtoAtualizar = produtoDAO.buscarPorId(idProduto);

        if (produtoAtualizar == null) {
            System.out.println("Cliente não encontrado");
        } else {
            System.out.println("---- Atualização de produto ----");
            System.out.println("Novo nome do produto: ");
            produtoAtualizar.setNome(scanner.nextLine());
            System.out.println("Novo preco do produto: ");
            produtoAtualizar.setPreco(scanner.nextFloat());
            System.out.println("Nova quantidade do produto: ");
            produtoAtualizar.setQuantidade(scanner.nextInt());

            produtoDAO.atualizar(produtoAtualizar);
            System.out.println("Produto atualizado");
        }
    }

    private void deletarProduto(Scanner scanner,ProdutoDAO produtoDAO) {
        System.out.println("Id para remoção: ");
        int idProduto = scanner.nextInt();

        Produto produtoDeletar = produtoDAO.buscarPorId(idProduto);
        if (produtoDeletar == null) {
            System.out.println("Produto não encontrado");
        } else{
            produtoDAO.remover(idProduto);
            System.out.println("Produto deletado");
        }
    }
}
