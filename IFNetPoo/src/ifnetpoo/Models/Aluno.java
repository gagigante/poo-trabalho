package ifnetpoo.Models;

public class Aluno extends Usuario {
    public Aluno(String nome, String prontuario, String email) {
        super(nome, prontuario, email);
    }
    
    public Aluno(int id, String nome, String prontuario, String email) {
        super(id, nome, prontuario, email);
    }
    
    @Override
    public String tipoUsuario() {
        return "aluno";
    }
}
