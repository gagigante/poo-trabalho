package ifnetpoo.Models;

import java.util.ArrayList;

public abstract class Usuario implements Comparable<Usuario>{
    private final String nome;
    private final String prontuario;
    private final String email;
    private final ArrayList<Disciplina> disciplinas = new ArrayList<>();
    
    public Usuario(String nome, String prontuario, String email) {
        this.nome = nome;
        this.prontuario = prontuario;
        this.email = email;
    }
    
    public String getNome() {
        return nome;
    }

    public String getProntuario() {
        return prontuario;
    }

    public String getEmail() {
        return email;
    }
    
    public ArrayList<Disciplina> getDisciplinas() {
        return this.disciplinas;
    }
    
    public void cadastraDisciplina(Disciplina disciplina) {
        this.disciplinas.add(disciplina);
    }
    
    @Override
    public int compareTo(Usuario usuario) {
        if (usuario.getProntuario().equals(this.getProntuario())) {
            return 1;
        }
        
        return 0;
    }
    
    public abstract String tipoUsuario();
}
