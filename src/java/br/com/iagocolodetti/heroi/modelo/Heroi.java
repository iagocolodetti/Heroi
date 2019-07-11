package br.com.iagocolodetti.heroi.modelo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 *
 * @author iagocolodetti
 */
public class Heroi {

    private int id;
    private String nome;
    private Date dataCadastro;
    private int idUniverso;
    private String nomeUniverso;
    private List<Poder> poderes;

    public Heroi() {
    }

    public Heroi(String nome, int idUniverso, List<Poder> poderes) {
        this.nome = nome;
        this.dataCadastro = new Date();
        this.idUniverso = idUniverso;
        this.poderes = poderes;
    }
    
    public Heroi(int id, String nome, String dataCadastro, int idUniverso, String nomeUniverso, List<Poder> poderes) {
        this.id = id;
        this.nome = nome;
        setStringDataCadastro(dataCadastro);
        this.idUniverso = idUniverso;
        this.nomeUniverso = nomeUniverso;
        this.poderes = poderes;
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
    
    public Date getDataCadastro() {
        return dataCadastro;
    }
    
    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }
    
    public String getStringDataCadastro() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(dataCadastro);
    }
    
    public void setStringDataCadastro(String dataCadastro) {
        try {
            this.dataCadastro = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(dataCadastro);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public int getIdUniverso() {
        return idUniverso;
    }

    public void setIdUniverso(int idUniverso) {
        this.idUniverso = idUniverso;
    }
    
    public String getNomeUniverso() {
        return nomeUniverso;
    }

    public void setNomeUniverso(String nomeUniverso) {
        this.nomeUniverso = nomeUniverso;
    }

    public List<Poder> getPoderes() {
        return poderes;
    }

    public void setPoderes(List<Poder> poderes) {
        this.poderes = poderes;
    }
}
