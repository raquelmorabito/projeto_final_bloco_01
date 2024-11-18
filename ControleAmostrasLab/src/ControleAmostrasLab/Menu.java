package ControleAmostrasLab;

import java.util.Scanner;

public class Menu {
    public static void main(String[] args) {

        Scanner leia = new Scanner(System.in);
        int opcao;

        while (true) {
            System.out.println("*****************************************************");
            System.out.println("                                                     ");
            System.out.println("            LABORATÓRIO DE CONTROLE DE AMOSTRAS      ");
            System.out.println("                                                     ");
            System.out.println("*****************************************************");
            System.out.println("                                                     ");
            System.out.println("            1 - Cadastrar Amostra                    ");
            System.out.println("            2 - Listar todas as Amostras             ");
            System.out.println("            3 - Buscar Amostra por ID                ");
            System.out.println("            4 - Atualizar Status da Amostra          ");
            System.out.println("            5 - Inserir Resultado da Análise         ");
            System.out.println("            6 - Excluir Amostra                      ");
            System.out.println("            7 - Sair                                 ");
            System.out.println("                                                     ");
            System.out.println("*****************************************************");
            System.out.println("Entre com a opção desejada:                          ");
            System.out.println("                                                     ");

            opcao = leia.nextInt();

            if (opcao == 7) {
                System.out.println("\nLaboratório de Controle de Amostras - Até logo!");
                leia.close();
                System.exit(0);
            }

            switch (opcao) {
                case 1:
                    System.out.println("Cadastrar Amostra\n\n");
                    System.out.print("Nome do cliente: ");
                    leia.nextLine(); 
                    System.out.print("Tipo de amostra: ");
                    System.out.print("Data de recebimento (dd/mm/aaaa): ");
                    break;

                case 2:
                    System.out.println("Listar todas as Amostras\n\n");
                    break;

                case 3:
                    System.out.println("Buscar Amostra por ID\n\n");
                    break;

                case 4:
                    System.out.println("Atualizar Status da Amostra\n\n");
                    break;

                case 5:
                    System.out.println("Inserir Resultado da Análise\n\n");
                    break;

                case 6:
                    System.out.println("Excluir Amostra\n\n");
                    break;

                default:
                    System.out.println("\nOpção Inválida!\n");
                    break;
            }
        }
    }

public static void sobre() {
	System.out.println("\n*********************************************************");
	System.out.println("Projeto Desenvolvido por: Raquel Morabito");
	System.out.println("Raquel Morabito - raquelmorabito@hotmail.com");
	System.out.println("github.com/raquelmorabito");
	System.out.println("*********************************************************");
}
}
