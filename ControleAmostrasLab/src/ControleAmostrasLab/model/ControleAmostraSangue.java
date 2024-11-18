package ControleAmostrasLab.model;

import java.util.Date;

public class ControleAmostraSangue extends ControleAmostra {

    private String tipoSangue;

    public ControleAmostraSangue(int id, String nomeCliente, String tipoSangue, Date dataRecebimento) {
        super(id, nomeCliente, "Sangue", dataRecebimento);
        this.tipoSangue = tipoSangue;
    }

    public String getTipoSangue() {
        return tipoSangue;
    }

    public void setTipoSangue(String tipoSangue) {
        this.tipoSangue = tipoSangue;
    }

    @Override
    public void visualizarAmostra() {
        super.visualizarAmostra();
        System.out.println("Tipo de Sangue: " + this.tipoSangue);
    }
}
