import dao.ClienteDAO;
import model.Cliente;
import model.Produto;

import java.util.List;
import java.util.Scanner;

public class MenuCliente {
    public void menuCliente() {
        Cliente cliente = new Cliente();
        ClienteDAO clienteDAO = new ClienteDAO();
        Scanner scanner = new Scanner(System.in);
        int opcaoUsuario = 0;

        
        do {
            System.out.println("""
                                ---- Cliente ----
                                [1] Cadastrar cliente
                                [2] Listar clientes
                                [3] Atualizar cliente
                                [4] Remover cliente
                                [0] Sair""");

            System.out.println("Opcao: ");
            opcaoUsuario = scanner.nextInt();
            scanner.nextLine();

            switch (opcaoUsuario){
                case 1:
                    cadastrarCliente(scanner, cliente, clienteDAO);
                    break;
                case 2:
                    listarClientes(clienteDAO);
                    break;
                case 3:
                    atualizarCliente(scanner, clienteDAO);
                    break;
                case 4:
                    deletarCliente(scanner, clienteDAO);
                    break;
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida");
            }
            
        }while (opcaoUsuario != 0);
        

    }

    private void cadastrarCliente(Scanner scanner, Cliente cliente,ClienteDAO clienteDAO) {
        System.out.println("---- Cadastro Cliente ----");
        System.out.println("Nome do cliente: ");
        cliente.setNome(scanner.nextLine());

        System.out.println("Telefone do cliente: ");
        cliente.setTelefone(scanner.nextLine());

        clienteDAO.inserir(cliente);
        System.out.println("Cliente cadastrado com sucesso!\n");
    }

    private void listarClientes(ClienteDAO clienteDAO) {
        List<Cliente> clientes = clienteDAO.listar();
        for(Cliente cliente : clientes) {
            System.out.printf("""
                                
                                Id: %d
                                Nome: %s
                                Telefone: %s
                                """, cliente.getIdCliente(), cliente.getNome(), cliente.getTelefone());
        }
    }

    private void atualizarCliente(Scanner scanner, ClienteDAO clienteDAO) {
        System.out.println("Id para atualização: ");
        int idCliente = scanner.nextInt();
        scanner.nextLine();

        Cliente clienteAtualizar = clienteDAO.buscarPorId(idCliente);

        if (clienteAtualizar == null) {
            System.out.println("Cliente não encontrado");
        } else {
            System.out.println("---- Atualização de cliente ----");
            System.out.println("Novo nome do cliente: ");
            clienteAtualizar.setNome(scanner.nextLine());

            System.out.println("Novo telefone do cliente: ");
            clienteAtualizar.setTelefone(scanner.nextLine());

            clienteDAO.atualizar(clienteAtualizar);
            System.out.println("Cliente atualizado");
        }
    }

    private void deletarCliente(Scanner scanner,ClienteDAO clienteDAO) {
        System.out.println("Id para remoção: ");
        int idCliente = scanner.nextInt();

        Cliente clienteDeletar = clienteDAO.buscarPorId(idCliente);
        if (clienteDeletar == null) {
            System.out.println("Cliente não encontrado");
        } else{
            clienteDAO.remover(idCliente);
            System.out.println("Cliente deletado");
        }
    }
}
