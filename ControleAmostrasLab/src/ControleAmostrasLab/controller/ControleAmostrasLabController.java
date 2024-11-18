package ControleAmostrasLab.controller;

import ControleAmostrasLab.model.ControleAmostra;
import ControleAmostrasLab.ControleAmostrasLabRepository;
import java.util.ArrayList;
import java.util.List;

public class ControleAmostrasLabController implements ControleAmostrasLabRepository {

    private List<ControleAmostra> amostras;

    public ControleAmostrasLabController() {
        this.amostras = new ArrayList<>();
    }

    @Override
    public void cadastrar(ControleAmostra amostra) {
        try {
            if (amostra == null) {
                throw new IllegalArgumentException("Amostra não pode ser nula.");
            }
            amostras.add(amostra);
            System.out.println("Amostra cadastrada com sucesso!");
        } catch (IllegalArgumentException e) {
            System.out.println("Erro ao cadastrar a amostra: " + e.getMessage());
        }
    }

    @Override
    public void listarTodas(List<ControleAmostra> amostras) {
        try {
            if (amostras.isEmpty()) {
                throw new Exception("Nenhuma amostra cadastrada.");
            }
            for (ControleAmostra amostra : amostras) {
                amostra.visualizarAmostra();
            }
        } catch (Exception e) {
            System.out.println("Erro ao listar amostras: " + e.getMessage());
        }
    }

    @Override
    public ControleAmostra buscarPorId(int id) {
        try {
            ControleAmostra amostra = buscarNaCollection(id);
            if (amostra == null) {
                throw new Exception("Amostra não encontrada.");
            }
            return amostra;
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
            return null;
        }
    }

    @Override
    public void atualizar(ControleAmostra amostra) {
        try {
            if (amostra == null) {
                throw new IllegalArgumentException("Amostra não pode ser nula.");
            }
            for (int i = 0; i < amostras.size(); i++) {
                if (amostras.get(i).getId() == amostra.getId()) {
                    amostras.set(i, amostra);
                    System.out.println("Amostra atualizada com sucesso!");
                    return;
                }
            }
            throw new Exception("Amostra não encontrada para atualização.");
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    @Override
    public void deletar(int id) {
        try {
            ControleAmostra amostraParaExcluir = buscarNaCollection(id);
            if (amostraParaExcluir == null) {
                throw new Exception("Amostra não encontrada.");
            }
            amostras.remove(amostraParaExcluir);
            System.out.println("Amostra excluída com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    @Override
    public void inserirResultado(int id, String resultado) {
        try {
            ControleAmostra amostra = buscarPorId(id);
            if (amostra != null) {
                amostra.inserirResultado(resultado);
                System.out.println("Resultado inserido com sucesso!");
            }
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    public void atualizarStatus(int id, String status) {
        try {
            ControleAmostra amostra = buscarPorId(id);
            if (amostra != null) {
                amostra.atualizarStatus(status);
                System.out.println("Status atualizado com sucesso!");
            }
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private ControleAmostra buscarNaCollection(int id) {
        for (ControleAmostra amostra : amostras) {
            if (amostra.getId() == id) {
                return amostra;
            }
        }
        return null;
    }

    public List<ControleAmostra> getAmostras() {
        return amostras;
    }
}
