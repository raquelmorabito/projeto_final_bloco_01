package ControleAmostrasLab;

import ControleAmostrasLab.controller.ControleAmostrasLabController;
import ControleAmostrasLab.model.ControleAmostra;
import ControleAmostrasLab.model.ControleAmostraSangue;
import ControleAmostrasLab.model.ControleAmostraUrina;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Menu {

    public static Scanner leia = new Scanner(System.in);

    public static void main(String[] args) {
        ControleAmostrasLabController controller = new ControleAmostrasLabController();
        int opcao;

        while (true) {
            System.out.println("*****************************************************");
            System.out.println("            LABORATÓRIO DE CONTROLE DE AMOSTRAS      ");
            System.out.println("*****************************************************");
            System.out.println("1 - Cadastrar Amostra");
            System.out.println("2 - Listar todas as Amostras");
            System.out.println("3 - Buscar Amostra por ID");
            System.out.println("4 - Atualizar Status da Amostra");
            System.out.println("5 - Inserir Resultado da Análise");
            System.out.println("6 - Excluir Amostra");
            System.out.println("7 - Sair");
            System.out.print("Digite a opção desejada: ");

            try {
                opcao = leia.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("\nErro: Entrada inválida. Por favor, digite um número inteiro.");
                leia.nextLine(); 
                continue;
            }

            switch (opcao) {
                case 1 -> cadastrarAmostra(controller);
                case 2 -> controller.listarTodas(controller.getAmostras());
                case 3 -> buscarAmostraPorID(controller);
                case 4 -> atualizarStatusAmostra(controller);
                case 5 -> inserirResultadoAnalise(controller);
                case 6 -> excluirAmostra(controller);
                case 7 -> {
                    System.out.println("\nSaindo... Até logo!");
                    leia.close();
                    System.exit(0);
                }
                default -> System.out.println("\nOpção inválida. Tente novamente.");
            }
        }
    }

    public static void cadastrarAmostra(ControleAmostrasLabController controller) {
        System.out.println("\nCadastrar Amostra");
        leia.nextLine(); 
        System.out.print("Nome do cliente: ");
        String nomeCliente = leia.nextLine();
        System.out.print("Tipo de amostra (Sangue ou Urina): ");
        String tipoAmostra = leia.nextLine();

        ControleAmostra novaAmostra = null;
        if (tipoAmostra.equalsIgnoreCase("Sangue")) {
            System.out.print("ID da amostra: ");
            int idSangue = leia.nextInt(); 
            leia.nextLine(); 
            System.out.print("Tipo sanguíneo (ex.: O+): ");
            String tipoSanguineo = leia.nextLine();
            System.out.print("Data da coleta (formato dd/MM/yyyy): ");
            String dataColetaSangue = leia.nextLine(); 

            Date dataColeta = null;
            try {
                SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
                dataColeta = formatoData.parse(dataColetaSangue);
            } catch (Exception e) {
                System.out.println("Erro ao formatar a data.");
            }

            novaAmostra = new ControleAmostraSangue(idSangue, nomeCliente, tipoSanguineo, dataColeta);
        } else if (tipoAmostra.equalsIgnoreCase("Urina")) {
            System.out.print("ID da amostra: ");
            int idUrina = leia.nextInt(); 
            leia.nextLine(); 
            System.out.print("Data da coleta (formato dd/MM/yyyy): ");
            String dataColetaUrina = leia.nextLine(); 
            System.out.print("Cor da urina (ex.: Amarela): ");
            String corUrina = leia.nextLine(); 

            Date dataColeta = null;
            try {
                SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
                dataColeta = formatoData.parse(dataColetaUrina);
            } catch (Exception e) {
                System.out.println("Erro ao formatar a data.");
            }

            novaAmostra = new ControleAmostraUrina(idUrina, nomeCliente, dataColeta, corUrina);
        }

        if (novaAmostra != null) {
            controller.cadastrar(novaAmostra);
        } else {
            System.out.println("Erro: Tipo de amostra inválido.");
        }

        keyPress();
    }

    public static void buscarAmostraPorID(ControleAmostrasLabController controller) {
        System.out.print("\nInforme o ID da amostra para busca: ");
        int id = leia.nextInt();
        ControleAmostra amostra = controller.buscarPorId(id);
        if (amostra != null) {
            amostra.visualizarAmostra();
        }
        keyPress();
    }

    public static void atualizarStatusAmostra(ControleAmostrasLabController controller) {
        System.out.print("\nInforme o ID da amostra para atualizar o status: ");
        int id = leia.nextInt();
        leia.nextLine(); 

        ControleAmostra amostra = controller.buscarPorId(id);
        if (amostra == null) {
            System.out.println("Erro: Amostra não encontrada.");
        } else {
            System.out.print("Informe o novo status da amostra: ");
            String status = leia.nextLine();
            controller.atualizarStatus(id, status);
        }

        keyPress();
    }

    public static void inserirResultadoAnalise(ControleAmostrasLabController controller) {
        System.out.print("\nInforme o ID da amostra para inserir o resultado da análise: ");
        int id = leia.nextInt();
        leia.nextLine(); 

        ControleAmostra amostra = controller.buscarPorId(id);
        if (amostra == null) {
            System.out.println("Erro: Amostra não encontrada.");
        } else {
            System.out.print("Resultado da análise: ");
            String resultado = leia.nextLine();
            controller.inserirResultado(id, resultado);
        }

        keyPress();
    }

    public static void excluirAmostra(ControleAmostrasLabController controller) {
        System.out.print("\nInforme o ID da amostra para exclusão: ");
        int id = leia.nextInt();

        ControleAmostra amostra = controller.buscarPorId(id);
        if (amostra == null) {
            System.out.println("Erro: Amostra não encontrada.");
        } else {
            controller.deletar(id);
        }

        keyPress();
    }

    public static void keyPress() {
        System.out.println("Pressione qualquer tecla para continuar...");
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
