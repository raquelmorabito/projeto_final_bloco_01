package ControleAmostrasLab;

import ControleAmostrasLab.model.ControleAmostra;
import ControleAmostrasLab.model.ControleAmostraSangue;
import ControleAmostrasLab.model.ControleAmostraUrina;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Menu {
    public static void main(String[] args) {

        Scanner leia = new Scanner(System.in);
        List<ControleAmostra> amostras = new ArrayList<>();
        int opcao;

        ControleAmostraSangue amostraSangue = new ControleAmostraSangue(1, "João Silva", "O+", new Date());
        amostraSangue.visualizarAmostra();

        ControleAmostraUrina amostraUrina = new ControleAmostraUrina(2, "Maria Souza", new Date(), "Clara");
        amostraUrina.visualizarAmostra();

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
                    String nomeCliente = leia.nextLine();
                    System.out.print("Tipo de amostra (Urina, Sangue, etc.): ");
                    String tipoAmostra = leia.nextLine();
                    System.out.print("Data de recebimento (dd/mm/aaaa): ");
                    String dataString = leia.nextLine();

                    Date dataRecebimento = new Date();
                    ControleAmostra novaAmostra = null;

                    if (tipoAmostra.equalsIgnoreCase("Sangue")) {
                        System.out.print("Tipo de Sangue (ex: A+, B-, O+): ");
                        String tipoSangue = leia.nextLine();
                        novaAmostra = new ControleAmostraSangue(amostras.size() + 1, nomeCliente, tipoSangue, dataRecebimento);
                    } else if (tipoAmostra.equalsIgnoreCase("Urina")) {
                        novaAmostra = new ControleAmostraUrina(amostras.size() + 1, nomeCliente, dataRecebimento, "Normal");
                    } else {
                        novaAmostra = new ControleAmostraSangue(amostras.size() + 1, nomeCliente, "Tipo Geral", dataRecebimento);
                    }
                    amostras.add(novaAmostra);
                    System.out.println("Amostra cadastrada com sucesso!\n\n");
                    break;

                case 2:
                    System.out.println("Listar todas as Amostras\n\n");
                    if (amostras.isEmpty()) {
                        System.out.println("Nenhuma amostra cadastrada.\n");
                    } else {
                        for (ControleAmostra amostra : amostras) {
                            amostra.visualizarAmostra();
                        }
                    }
                    break;

                case 3:
                    System.out.println("Buscar Amostra por ID\n\n");
                    System.out.print("Informe o ID da amostra: ");
                    int idBusca = leia.nextInt();
                    ControleAmostra amostraEncontrada = null;
                    for (ControleAmostra amostra : amostras) {
                        if (amostra.getId() == idBusca) {
                            amostraEncontrada = amostra;
                            break;
                        }
                    }
                    if (amostraEncontrada != null) {
                        amostraEncontrada.visualizarAmostra();
                    } else {
                        System.out.println("Amostra não encontrada.\n");
                    }
                    break;

                case 4:
                    System.out.println("Atualizar Status da Amostra\n\n");
                    System.out.print("Informe o ID da amostra: ");
                    int idStatus = leia.nextInt();
                    System.out.print("Novo Status: ");
                    leia.nextLine();
                    String statusNovo = leia.nextLine();
                    boolean achouStatus = false;

                    for (ControleAmostra amostra : amostras) {
                        if (amostra.getId() == idStatus) {
                            amostra.atualizarStatus(statusNovo);
                            System.out.println("Status atualizado com sucesso!\n");
                            achouStatus = true;
                            break;
                        }
                    }
                    if (!achouStatus) {
                        System.out.println("Amostra não encontrada.\n");
                    }
                    break;

                case 5:
                    System.out.println("Inserir Resultado da Análise\n\n");
                    System.out.print("Informe o ID da amostra: ");
                    int idResultado = leia.nextInt();
                    System.out.print("Resultado: ");
                    leia.nextLine();
                    String resultado = leia.nextLine();

                    boolean achouResultado = false;
                    for (ControleAmostra amostra : amostras) {
                        if (amostra.getId() == idResultado) {
                            amostra.inserirResultado(resultado);
                            System.out.println("Resultado inserido com sucesso!\n");
                            achouResultado = true;
                            break;
                        }
                    }
                    if (!achouResultado) {
                        System.out.println("Amostra não encontrada.\n");
                    }
                    break;

                case 6:
                    System.out.println("Excluir Amostra\n\n");
                    System.out.print("Informe o ID da amostra: ");
                    int idExcluir = leia.nextInt();
                    boolean achouExcluir = false;
                    for (ControleAmostra amostra : amostras) {
                        if (amostra.getId() == idExcluir) {
                            amostras.remove(amostra);
                            System.out.println("Amostra excluída com sucesso!\n");
                            achouExcluir = true;
                            break;
                        }
                    }
                    if (!achouExcluir) {
                        System.out.println("Amostra não encontrada.\n");
                    }
                    break;

                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
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

