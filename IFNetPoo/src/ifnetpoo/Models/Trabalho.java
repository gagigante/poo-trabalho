package ifnetpoo.Models;

public class Trabalho extends Grupo {
    private Disciplina diciplina;

    public Trabalho(Disciplina diciplina, String nome, Usuario criador) {
        super(nome, criador);
        
        this.diciplina = diciplina;
    }

    public Disciplina getDiciplina() {
        return diciplina;
    }

    public void setDiciplina(Disciplina diciplina) {
        this.diciplina = diciplina;
    }
    
    @Override
    public String getTipoGrupo() {
        return "trabalho";
    }

    @Override
    public String getGrupoOverview() {
        return "Tipo: " + this.getTipoGrupo() + "; Nome: " + this.getNome() + "; Criador: " + this.getCriador().getNome() + 
            "; Disciplina: " + this.getDiciplina().getNome();
    }
}
