import dao.EstoqueDAO;
import dao.VendaDAO;
import dao.VendaDAO;
import model.Venda;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class MenuVenda {
    public void menuVenda() {
        Venda venda = new Venda();
        VendaDAO vendaDAO = new VendaDAO();
        Scanner scanner = new Scanner(System.in);
        int opcaoUsuario = 0;

        do {
            System.out.println("""
                                ---- Venda ----
                                [1] Cadastrar venda
                                [2] Listar vendas
                                [3] Atualizar venda
                                [4] Remover venda
                                [0] Sair""");

            System.out.println("Opcao: ");
            opcaoUsuario = scanner.nextInt();

            switch (opcaoUsuario){
                case 1:
                    cadastrarVenda(scanner, venda, vendaDAO);
                    break;
                case 2:
                    listarVendas(vendaDAO);
                    break;
                case 3:
                    atualizarVenda(scanner, vendaDAO);
                    break;
                case 4:
                    deletarVenda(scanner, vendaDAO);
                    break;
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida");
            }

        }while (opcaoUsuario != 0);


    }

    private void cadastrarVenda(Scanner scanner,Venda venda,VendaDAO vendaDAO) {
        System.out.println("---- Cadastro Venda ----");
        System.out.println("Id do cliente: ");
        venda.setIdCliente(scanner.nextInt());

        System.out.println("Id do funcionario: ");
        venda.setIdFuncionario(scanner.nextInt());

        System.out.println("Id do produto: ");
        venda.setIdProduto(scanner.nextInt());

        System.out.println("Total da venda: ");
        venda.setTotal(scanner.nextFloat());
        scanner.nextLine();

        System.out.println("Data da venda (yyyy-mm-dd): ");
        String dataVendaStr = scanner.nextLine();
        venda.setDataVenda(dataVendaStr);
        vendaDAO.inserir(venda);

        System.out.println("Venda cadastrado com sucesso!\n");
    }

    private void listarVendas(VendaDAO vendaDAO) {
        List<Venda> vendas = vendaDAO.listar();
        for(Venda venda : vendas) {
            System.out.printf("""
                                ---- Detalhes da venda ----
                                Id da venda: %d
                                Id do funcionnario: %d
                                Id do cliente: %d
                                Id do produto: %d
                                Total: %.2f
                                Data: %s
                                """, venda.getIdVendas(),venda.getIdFuncionario(), venda.getIdProduto(),
                                venda.getIdCliente(), venda.getTotal(), venda.getDataVenda());
        }
    }

    private void atualizarVenda(Scanner scanner,VendaDAO vendaDAO) {

        System.out.println("Id para atualização: ");
        int idVenda = scanner.nextInt();

        Venda vendaAtualizar = vendaDAO.buscarPorId(idVenda);

        if (vendaAtualizar == null) {
            System.out.println("Venda não encontrada");
        } else {
            System.out.println("---- Atualização de venda ----");
            System.out.println("Novo id do funcionario: ");
            vendaAtualizar.setIdFuncionario(scanner.nextInt());
            System.out.println("Novo id do cliente: ");
            vendaAtualizar.setIdCliente(scanner.nextInt());
            System.out.println("Novo id do produto: ");
            vendaAtualizar.setIdProduto(scanner.nextInt());
            System.out.println("Novo total da venda: ");
            vendaAtualizar.setTotal(scanner.nextFloat());
            scanner.nextLine();
            System.out.println("Nova data da venda (yyyy-mm-dd): ");
            String dataVendaStr = scanner.next();
            vendaAtualizar.setDataVenda(dataVendaStr);


            vendaDAO.atualizar(vendaAtualizar);
            System.out.println("Venda atualizado");
        }
    }

    private void deletarVenda(Scanner scanner,VendaDAO vendaDAO) {
        System.out.println("Id para remoção: ");
        int idVenda = scanner.nextInt();

        Venda vendaDeletar = vendaDAO.buscarPorId(idVenda);
        if (vendaDeletar == null) {
            System.out.println("Venda não encontrada");
        } else{
            vendaDAO.remover(idVenda);
            System.out.println("Venda deletado");
        }
    }
}
