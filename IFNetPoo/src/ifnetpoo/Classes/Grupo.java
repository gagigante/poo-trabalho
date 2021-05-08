package ifnetpoo.Classes;

import java.util.ArrayList;

public abstract class Grupo {
    private final String nome;
    private final Usuario criador;
    private final ArrayList<Aluno> alunos = new ArrayList<>();

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
        return this.alunos.size();
    }
    
    public abstract String getTipoGrupo();
    
    public abstract String getGrupoOverview();
}
