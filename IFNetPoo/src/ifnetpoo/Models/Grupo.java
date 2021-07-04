package ifnetpoo.Models;

import java.util.ArrayList;

public abstract class Grupo implements Comparable<Grupo>{
    private final int id;
    private final String nome;
    private final Usuario criador;
    private final ArrayList<Aluno> alunos = new ArrayList<>();
    private int quantidadeAlunos = 0;

    public Grupo(String nome, Usuario criador) {
        this.id = 0;
        this.nome = nome;
        this.criador = criador;
    }
    
    public Grupo(int id, String nome, Usuario criador) {
        this.id = id;
        this.nome = nome;
        this.criador = criador;
    }
    
    public int getId() {
        return this.id;
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
        return this.quantidadeAlunos;
    }
    
    public void setQuantidadeAlunos(int quantidade) {
        this.quantidadeAlunos = quantidade;
    }
    
    public abstract String getTipoGrupo();
    
    public abstract String getGrupoOverview();

    @Override
    public int compareTo(Grupo grupo) {
        if (this.quantidadeAlunos == grupo.getQuantidadeAlunos()) {
            return 0;
        }
        
        return this.quantidadeAlunos >= grupo.getQuantidadeAlunos() ? -1 : 1;
    }
}
