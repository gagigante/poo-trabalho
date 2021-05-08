package ifnetpoo.Classes;

public class Aluno extends Usuario {
    public Aluno(String nome, String prontuario, String email) {
        super(nome, prontuario, email);
    }
    
    @Override
    public String tipoUsuario() {
        return "aluno";
    }
}
