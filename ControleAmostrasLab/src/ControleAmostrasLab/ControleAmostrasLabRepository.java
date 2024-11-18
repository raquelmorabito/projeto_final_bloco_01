package ControleAmostrasLab;

import ControleAmostrasLab.model.ControleAmostra;
import java.util.List;

public interface ControleAmostrasLabRepository {

    void cadastrar(ControleAmostra amostra);
    void listarTodas(List<ControleAmostra> amostras);
    ControleAmostra buscarPorId(int id);
    void atualizar(ControleAmostra amostra);
    void deletar(int id);
    
    void inserirResultado(int id, String resultado);
    void atualizarStatus(int id, String status);
}
