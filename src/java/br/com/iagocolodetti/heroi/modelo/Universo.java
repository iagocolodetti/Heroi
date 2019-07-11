package br.com.iagocolodetti.heroi.modelo;

/**
 *
 * @author iagocolodetti
 */
public class Universo {

    private int id;
    private String nome;

    public Universo() {
    }
    
    public Universo(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
