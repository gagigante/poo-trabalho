package ifnetpoo.Interfaces;

import ifnetpoo.Models.Disciplina;
import ifnetpoo.Models.Usuario;

public interface IMaterial {
    public int getId();
    
    public String getTitulo();
    
    public String getCategoria();
    
    public Usuario getCriador();
    
    public Disciplina getDisciplina();
    
    public String getTipoMaterial();
    
    public String getOverviewMaterial();
}
