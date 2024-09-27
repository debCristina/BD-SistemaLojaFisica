import dao.EstoqueDAO;
import dao.ProdutoDAO;
import model.Estoque;
import model.Produto;

import java.util.List;
import java.util.Scanner;

public class MenuEstoque {
    public void menuEstoque() {
        Estoque produtoInserido = new Estoque();
        EstoqueDAO estoqueDAO = new EstoqueDAO();
        Scanner scanner = new Scanner(System.in);
        int opcaoUsuario = 0;

        do {
            System.out.println("""
                                ---- Estoque ----
                                [1] Cadastrar produto no estoque
                                [2] Listar itens estoque
                                [3] Atualizar estoque
                                [4] Remover item do estoque
                                [0] Sair""");

            System.out.println("Opcao: ");
            opcaoUsuario = scanner.nextInt();
            scanner.nextLine();

            switch (opcaoUsuario){
                case 1:
                    cadastrarProdutoEstoque(scanner, produtoInserido, estoqueDAO);
                    break;
                case 2:
                    listarProdutoEstoque(estoqueDAO);
                    break;
                case 3:
                    atualizarEstoque(scanner,estoqueDAO);
                    break;
                case 4:
                    deletarEstoque(scanner, estoqueDAO);
                    break;
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida");
            }

        }while (opcaoUsuario != 0);


    }

    private void cadastrarProdutoEstoque(Scanner scanner,Estoque produtoInserido,EstoqueDAO estoqueDAO) {
        System.out.println("---- Cadastro de produto no estoque ---");
        System.out.println("ID do produto: ");
        produtoInserido.setIdProduto(scanner.nextInt());

        System.out.println("Quantidade: ");
        produtoInserido.setQuantidade(scanner.nextInt());

        estoqueDAO.inserir(produtoInserido);

        System.out.println("Produto inserido com sucesso!\n");
    }

    private void listarProdutoEstoque(EstoqueDAO estoqueDAO) {
        List<Estoque> produstosEstoque = estoqueDAO.listar();
        for(Estoque estoque : produstosEstoque) {
            System.out.printf("""
                                
                                Id estoque: %d
                                Id produto: %d
                                Quantidade: %d
                                """, estoque.getIdEstoque(),estoque.getIdProduto(), estoque.getQuantidade());
        }
    }

    private void atualizarEstoque(Scanner scanner,EstoqueDAO estoqueDAO) {
        System.out.println("Id para atualização: ");
        int idProduto = scanner.nextInt();
        scanner.nextLine();

        Estoque estoqueAtualizar = estoqueDAO.buscarPorId(idProduto);

        if (estoqueAtualizar == null) {
            System.out.println("Estoque não encontrado");
        } else {
            System.out.println("---- Atualização de estoque ----");
            System.out.println("Nova quantidade do produto: ");
            estoqueAtualizar.setQuantidade(scanner.nextInt());
            System.out.println("Id do estoque: ");
            estoqueAtualizar.setIdEstoque(scanner.nextInt());

            estoqueDAO.atualizar(estoqueAtualizar);
            System.out.println("Estoque atualizado");
        }
    }

    private void deletarEstoque(Scanner scanner, EstoqueDAO estoqueDAO) {
        System.out.println("Id para remoção: ");
        int idProduto = scanner.nextInt();

        Estoque produtoDeletar = estoqueDAO.buscarPorId(idProduto);
        if (produtoDeletar == null) {
            System.out.println("Estoque não encontrado");
        } else{
            estoqueDAO.remover(idProduto);
            System.out.println("Item deletado");
        }
    }
}
