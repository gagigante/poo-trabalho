package ifnetpoo.Models;

import java.util.ArrayList;

public abstract class Usuario implements Comparable<Usuario> {
    private final int id;
    private final String nome;
    private final String prontuario;
    private final String email;
    private ArrayList<Disciplina> disciplinas = new ArrayList<>();
    
    public Usuario(String nome, String prontuario, String email) {
        this.id = 0;
        this.nome = nome;
        this.prontuario = prontuario;
        this.email = email;
    }
    
    public Usuario(int id, String nome, String prontuario, String email) {
        this.id = id;
        this.nome = nome;
        this.prontuario = prontuario;
        this.email = email;
    }
    
    public int getId() {
        return this.id;
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
    
    public void setDisciplinas(ArrayList<Disciplina> disciplinas) {
        this.disciplinas = disciplinas;
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
