import dao.ProdutoDAO;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcaoUsuario;

        do {
            System.out.println("""
                               ---- Sistema de Gestão ----
                               [1] Gerenciar Produtos
                               [2] Gerenciar Estoque
                               [3] Gerenciar Clientes
                               [4] Gerenciar Funcionários
                               [5] Gerenciar Vendas
                               [0] Sair""");
            System.out.print("Escolha uma opção: ");
            opcaoUsuario = scanner.nextInt();

            switch (opcaoUsuario) {
                case 1:
                    MenuProduto menuProduto = new MenuProduto();
                    menuProduto.menuProduto();
                    break;
                case 2:
                    MenuEstoque menuEstoque = new MenuEstoque();
                    menuEstoque.menuEstoque();
                    break;
                case 3:
                    MenuCliente menuCliente = new MenuCliente();
                    menuCliente.menuCliente();
                    break;
                case 4:
                    MenuFuncionario menuFuncionario = new MenuFuncionario();
                    menuFuncionario.menuFuncionario();
                    break;
                case 5:
                    MenuVenda menuVenda = new MenuVenda();
                    menuVenda.menuVenda();
                    break;
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }

        } while (opcaoUsuario != 0);


    }
}

