import dao.ClienteDAO;
import dao.FuncionarioDAO;
import model.Cliente;
import model.Funcionario;

import java.util.List;
import java.util.Scanner;

public class MenuFuncionario {
    public void menuFuncionario() {
        Funcionario funcionario = new Funcionario();
        FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
        Scanner scanner = new Scanner(System.in);
        int opcaoUsuario = 0;

        do {
            System.out.println("""
                                ---- Funcionario ----
                                [1] Cadastrar funcionario
                                [2] Listar funcionario
                                [3] Atualizar funcionario
                                [4] Remover funcionario
                                [0] Sair""");

            System.out.println("Opcao: ");
            opcaoUsuario = scanner.nextInt();
            scanner.nextLine();

            switch (opcaoUsuario){
                case 1:
                    cadastrarFuncionario(scanner, funcionario, funcionarioDAO);
                    break;
                case 2:
                    listarFuncionarios(funcionarioDAO);
                    break;
                case 3:
                    atualizarFuncionario(scanner, funcionarioDAO);
                    break;
                case 4:
                    deletarFuncionario(scanner, funcionarioDAO);
                    break;
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida");
            }

        }while (opcaoUsuario != 0);


    }

    private void cadastrarFuncionario(Scanner scanner,Funcionario funcionario,FuncionarioDAO funcionarioDAO) {
        System.out.println("---- Cadastro Funcionario ----");
        System.out.println("Nome do funcionario: ");
        funcionario.setNome(scanner.nextLine());

        System.out.println("Cargo do funcionario: ");
        funcionario.setCargo(scanner.nextLine());

        funcionarioDAO.inserir(funcionario);
        System.out.println("Funcionario cadastrado com sucesso!\n");
    }

    private void listarFuncionarios(FuncionarioDAO funcionarioDAO) {
        List<Funcionario> funcionarios = funcionarioDAO.listar();
        for(Funcionario funcionario : funcionarios) {
            System.out.printf("""
                                
                                Id: %d
                                Nome: %s
                                Cargo: %s
                                """, funcionario.getIdFuncionario(), funcionario.getNome(), funcionario.getCargo());
        }
    }

    private void atualizarFuncionario(Scanner scanner,FuncionarioDAO funcionarioDAO) {
        System.out.println("Id para atualização: ");
        int idFuncionario = scanner.nextInt();
        scanner.nextLine();

        Funcionario funcionarioAtualizar =  funcionarioDAO.buscarPorId(idFuncionario);

        if (funcionarioAtualizar == null) {
            System.out.println("Funcionario não encontrado");
        } else {
            System.out.println("---- Atualização de Funcionario ----");
            System.out.println("Novo nome do funcionario: ");
            funcionarioAtualizar.setNome(scanner.nextLine());
            System.out.println("Novo cargo do funcionario: ");
            funcionarioAtualizar.setCargo(scanner.nextLine());

            funcionarioDAO.atualizar(funcionarioAtualizar);
            System.out.println("Funcionario atualizado");
        }
    }

    private void deletarFuncionario(Scanner scanner, FuncionarioDAO funcionarioDAO) {
        System.out.println("Id para remoção: ");
        int idFuncionario = scanner.nextInt();

        Funcionario funcionarioDeletar = funcionarioDAO.buscarPorId(idFuncionario);
        if (funcionarioDeletar == null) {
            System.out.println("Funcionario não encontrado");
        } else{
            funcionarioDAO.remover(idFuncionario);
            System.out.println("Funcionario deletado");
        }
    }
}
