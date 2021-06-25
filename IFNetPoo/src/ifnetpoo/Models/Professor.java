package ifnetpoo.Models;

public class Professor extends Usuario {
    private final String area;

    public String getArea() {
        return area;
    }
    
    public Professor(String nome, String prontuario, String email, String area) {
        super(nome, prontuario, email);
        
        this.area = area;
    }
    
    public Professor(int id, String nome, String prontuario, String email, String area) {
        super(id, nome, prontuario, email);
        
        this.area = area;
    }
    
    @Override
    public String tipoUsuario() {
        return "professor";
    }
}
