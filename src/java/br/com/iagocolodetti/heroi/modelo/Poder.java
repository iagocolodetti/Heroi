package br.com.iagocolodetti.heroi.modelo;

/**
 *
 * @author iagocolodetti
 */
public class Poder {

    private int id;
    private String descricao;
    private int idHeroi;

    public Poder() {
    }
    
    public Poder(String descricao) {
        this.descricao = descricao;
    }

    public Poder(String descricao, int idHeroi) {
        this.descricao = descricao;
        this.idHeroi = idHeroi;
    }

    public Poder(int id, String descricao, int idHeroi) {
        this.id = id;
        this.descricao = descricao;
        this.idHeroi = idHeroi;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getIdHeroi() {
        return idHeroi;
    }

    public void setIdHeroi(int idHeroi) {
        this.idHeroi = idHeroi;
    }
}
