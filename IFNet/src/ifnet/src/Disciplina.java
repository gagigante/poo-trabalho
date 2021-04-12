/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ifnet.src;

/**
 *
 * @author gabriel
 */
public class Disciplina {
    private String nome;
    private int Semestre;
    private String sigla;

    public Disciplina(String nome, int Semestre, String sigla) {
        this.nome = nome;
        this.Semestre = Semestre;
        this.sigla = sigla;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getSemestre() {
        return Semestre;
    }

    public void setSemestre(int Semestre) {
        this.Semestre = Semestre;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }
    
    
}
