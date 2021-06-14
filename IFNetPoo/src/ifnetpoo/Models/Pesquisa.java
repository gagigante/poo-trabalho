package ifnetpoo.Models;

public class Pesquisa extends Grupo {
    private final Usuario orientador;

    public Pesquisa(Usuario orientador, String nome) {
        super(nome, orientador);
        
        this.orientador = orientador;
    }

    public Usuario getOrientador() {
        return orientador;
    }
    
    @Override
    public String getTipoGrupo() {
        return "pesquisa";
    }
    
    @Override
    public String getGrupoOverview() {
        return "Tipo: " + this.getTipoGrupo()+ "; Nome: " + this.getNome() + "; Criador: " + this.getCriador().getNome() + "; "
            + "Orientador: " + this.getOrientador().getNome();
    }
}
