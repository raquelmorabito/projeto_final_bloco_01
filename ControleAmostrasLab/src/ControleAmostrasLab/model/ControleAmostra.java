package ControleAmostrasLab.model;

import java.util.Date;

public abstract class ControleAmostra {  

    private int id;
    private String nomeCliente;
    private String tipoAmostra;
    private Date dataRecebimento;
    private String status;
    private String resultado;

    public ControleAmostra(int id, String nomeCliente, String tipoAmostra, Date dataRecebimento) {
        this.id = id;
        this.nomeCliente = nomeCliente;
        this.tipoAmostra = tipoAmostra;
        this.dataRecebimento = dataRecebimento;
        this.status = "Recebida"; 
        this.resultado = ""; 
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public String getTipoAmostra() {
        return tipoAmostra;
    }

    public void setTipoAmostra(String tipoAmostra) {
        this.tipoAmostra = tipoAmostra;
    }

    public Date getDataRecebimento() {
        return dataRecebimento;
    }

    public void setDataRecebimento(Date dataRecebimento) {
        this.dataRecebimento = dataRecebimento;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

    public void atualizarStatus(String novoStatus) {
        this.status = novoStatus;
    }

    public void inserirResultado(String resultado) {
        this.resultado = resultado;
    }

    public void visualizarAmostra() {
        System.out.println("\n\n***********************************************************");
        System.out.println("Dados da Amostra:");
        System.out.println("***********************************************************");
        System.out.println("ID: " + this.id);
        System.out.println("Nome do Cliente: " + this.nomeCliente);
        System.out.println("Tipo de Amostra: " + this.tipoAmostra);
        System.out.println("Data de Recebimento: " + this.dataRecebimento);
        System.out.println("Status: " + this.status);
        System.out.println("Resultado: " + this.resultado);
    }
}
