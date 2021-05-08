package ifnetpoo.DAO;

import ifnetpoo.Classes.Trabalho;
import ifnetpoo.Classes.Usuario;
import ifnetpoo.Classes.Disciplina;

import java.util.ArrayList;

public class TrabalhoDAO {
    private final ArrayList<Trabalho> gruposTrabalho = new ArrayList<>();
    
    public ArrayList<Trabalho> getGrupos() {
        return this.gruposTrabalho;
    }
    
    public Trabalho getGrupoPorIndex(int index) {
        int size = this.gruposTrabalho.size();
        
        if (index < 0 || index > size - 1) {
            throw new Error("Grupo não encontrado");
        }
        
        return this.gruposTrabalho.get(index); 
    }
    
    public Trabalho criarGrupo(Disciplina disciplina, String nome, Usuario criador) {
        Trabalho novoGrupo = new Trabalho(disciplina, nome, criador);
        
        this.gruposTrabalho.add(novoGrupo);
        
        return novoGrupo;
    }
    
    public void removerGrupo(int index) {
        this.getGrupoPorIndex(index);
        
        this.gruposTrabalho.remove(index);                
    }
    
    public ArrayList<Trabalho> getGruposPorDisciplina(Disciplina disciplina) {
        ArrayList<Trabalho> gruposFiltrados = new ArrayList<>();
        
        this.gruposTrabalho.stream().filter(grupo -> (grupo.getDiciplina().compareTo(disciplina) == 1)).forEachOrdered(grupo -> {
            gruposFiltrados.add(grupo);
        });
        
        if (gruposFiltrados.isEmpty()) {
            throw new Error("Nenhum grupo dessa disciplina foi encontrado");
        }
        
        return gruposFiltrados;
    }                    
}
