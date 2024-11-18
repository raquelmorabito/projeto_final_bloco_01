package ControleAmostrasLab.model;

import java.util.Date;

public class ControleAmostraUrina extends ControleAmostra {

    private String corUrina;

    public ControleAmostraUrina(int id, String nomeCliente, Date dataRecebimento, String corUrina) {
        super(id, nomeCliente, "Urina", dataRecebimento);
        this.corUrina = corUrina;
    }

    public String getCorUrina() {
        return corUrina;
    }

    public void setCorUrina(String corUrina) {
        this.corUrina = corUrina;
    }

    @Override
    public void visualizarAmostra() {
        super.visualizarAmostra();
        System.out.println("Cor da Urina: " + this.corUrina);
    }
}


