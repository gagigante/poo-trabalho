/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ifnet.src.Classes;

/**
 *
 * @author gabriel
 */
public class Curso {
    private String nome;
    private String sigla;
    private int numeroDeSemestres;
    private Disciplina[] disciplinas;

    public Curso(String nome, String sigla, int numeroDeSemestres, Disciplina[] disciplinas) {
        this.nome = nome;
        this.sigla = sigla;
        this.numeroDeSemestres = numeroDeSemestres;
        this.disciplinas = disciplinas;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public int getNumeroDeSemestres() {
        return numeroDeSemestres;
    }

    public void setNumeroDeSemestres(int numeroDeSemestres) {
        this.numeroDeSemestres = numeroDeSemestres;
    }

    public Disciplina[] getDisciplinas() {
        return disciplinas;
    }

    public void setDisciplinas(Disciplina[] disciplinas) {
        this.disciplinas = disciplinas;
    }   
}
