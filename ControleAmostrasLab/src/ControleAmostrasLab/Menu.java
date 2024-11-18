package ControleAmostrasLab;

import ControleAmostrasLab.controller.ControleAmostrasLabController;
import ControleAmostrasLab.model.ControleAmostra;
import ControleAmostrasLab.model.ControleAmostraSangue;
import ControleAmostrasLab.model.ControleAmostraUrina;
import ControleAmostrasLab.util.cores;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {

    public static Scanner leia = new Scanner(System.in);

    public static void main(String[] args) {
        ControleAmostrasLabController controller = new ControleAmostrasLabController();
        int opcao;

        while (true) {
            System.out.println(cores.AZUL + "*****************************************************");
            System.out.println("            LABORATÓRIO DE CONTROLE DE AMOSTRAS      ");
            System.out.println("*****************************************************" + cores.RESETAR);
            System.out.println(cores.VERDE + "1 - Cadastrar Amostra");
            System.out.println("2 - Listar todas as Amostras");
            System.out.println("3 - Buscar Amostra por ID");
            System.out.println("4 - Atualizar Status da Amostra");
            System.out.println("5 - Inserir Resultado da Análise");
            System.out.println("6 - Excluir Amostra");
            System.out.println("7 - Sobre");
            System.out.println("8 - Sair" + cores.RESETAR);
            System.out.print(cores.PRETO + "Digite a opção desejada: " + cores.RESETAR);

            try {
                opcao = leia.nextInt();
            } catch (InputMismatchException e) {
                System.out.println(cores.VERMELHO + "\nErro: Entrada inválida. Por favor, digite um número inteiro." + cores.RESETAR);
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
                case 7 -> sobre();  // Chama o método sobre()
                case 8 -> {
                    System.out.println(cores.AMARELO + "\nSaindo... Até logo!" + cores.RESETAR);
                    leia.close();
                    System.exit(0);
                }
                default -> System.out.println(cores.VERMELHO + "\nOpção inválida. Tente novamente." + cores.RESETAR);
            }
        }
    }

    public static void cadastrarAmostra(ControleAmostrasLabController controller) {
        System.out.println("\nCadastrar Amostra");
        leia.nextLine(); 
        System.out.print(cores.PRETO + "Nome do cliente: " + cores.RESETAR);
        String nomeCliente = leia.nextLine();
        System.out.print(cores.PRETO + "Tipo de amostra (Sangue ou Urina): " + cores.RESETAR);
        String tipoAmostra = leia.nextLine();

        ControleAmostra novaAmostra = null;
        if (tipoAmostra.equalsIgnoreCase("Sangue")) {
            System.out.print(cores.PRETO + "ID da amostra: " + cores.RESETAR);
            int idSangue = leia.nextInt(); 
            leia.nextLine(); 
            System.out.print(cores.PRETO + "Tipo sanguíneo (ex.: O+): " + cores.RESETAR);
            String tipoSanguineo = leia.nextLine();
            System.out.print(cores.PRETO + "Data da coleta (formato dd/MM/yyyy): " + cores.RESETAR);
            String dataColetaSangue = leia.nextLine(); 

            Date dataColeta = null;
            try {
                SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
                dataColeta = formatoData.parse(dataColetaSangue);
            } catch (Exception e) {
                System.out.println(cores.VERMELHO + "Erro ao formatar a data." + cores.RESETAR);
            }

            novaAmostra = new ControleAmostraSangue(idSangue, nomeCliente, tipoSanguineo, dataColeta);
        } else if (tipoAmostra.equalsIgnoreCase("Urina")) {
            System.out.print(cores.PRETO + "ID da amostra: " + cores.RESETAR);
            int idUrina = leia.nextInt(); 
            leia.nextLine(); 
            System.out.print(cores.PRETO + "Data da coleta (formato dd/MM/yyyy): " + cores.RESETAR);
            String dataColetaUrina = leia.nextLine(); 
            System.out.print(cores.PRETO + "Cor da urina (ex.: Amarela): " + cores.RESETAR);
            String corUrina = leia.nextLine(); 

            Date dataColeta = null;
            try {
                SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
                dataColeta = formatoData.parse(dataColetaUrina);
            } catch (Exception e) {
                System.out.println(cores.VERMELHO + "Erro ao formatar a data." + cores.RESETAR);
            }

            novaAmostra = new ControleAmostraUrina(idUrina, nomeCliente, dataColeta, corUrina);
        }

        if (novaAmostra != null) {
            controller.cadastrar(novaAmostra);
        } else {
            System.out.println(cores.VERMELHO + "Erro: Tipo de amostra inválido." + cores.RESETAR);
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
            System.out.println(cores.VERMELHO + "Erro: Amostra não encontrada." + cores.RESETAR);
        } else {
            System.out.print(cores.PRETO + "Informe o novo status da amostra: " + cores.RESETAR);
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
            System.out.println(cores.VERMELHO + "Erro: Amostra não encontrada." + cores.RESETAR);
        } else {
            System.out.print(cores.PRETO + "Resultado da análise: " + cores.RESETAR);
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
            System.out.println(cores.VERMELHO + "Erro: Amostra não encontrada." + cores.RESETAR);
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

    public static void sobre() {
        System.out.println("\n*********************************************************");
        System.out.println("Projeto Desenvolvido por: Raquel Morabito "); 
        System.out.println("Raquel Morabito - raquelmorabito@hotmail.com"); 
        System.out.println("github.com/raquelmorabito"); 
        System.out.println("*********************************************************");
    }
}
