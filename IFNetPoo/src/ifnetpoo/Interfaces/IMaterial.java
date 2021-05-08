package ifnetpoo.Interfaces;

import ifnetpoo.Classes.Disciplina;
import ifnetpoo.Classes.Usuario;

public interface IMaterial {
    public String getTitulo();
    
    public String getCategoria();
    
    public Usuario getCriador();
    
    public Disciplina getDisciplina();
    
    public String getTipoMaterial();
    
    public String getOverviewMaterial();
}
