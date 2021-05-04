/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ifnetpoo.Classes;

import java.util.ArrayList;

/**
 *
 * @author gabriel
 */
public abstract class Grupo {
    private final String nome;    
    private final ArrayList<Aluno> alunos = new ArrayList<>();
    private final Usuario criador;

    public Grupo(String nome, Usuario criador) {
        this.nome = nome;
        this.criador = criador;
    }

    public String getNome() {
        return this.nome;
    }
    
    public Usuario getCriador() {
        return this.criador;
    }

    public ArrayList<Aluno> getAlunos() {
        return this.alunos;
    }
    
    public void adicionaAlunoNoGrupo(Aluno aluno) {
        for (Aluno al : this.alunos) {
            if (al.compareTo(aluno) == 1) {
                throw new Error("Você já está nesse grupo");
            }
        }
        
        this.alunos.add(aluno);
    }

    public int getQuantidadeAlunos() {
        int cont = 0;
        
        for (Aluno aluno : this.alunos) {
            cont = cont + 1;
        }
        
        return cont;
    }
    
    public abstract String getGrupoOverview();
}
