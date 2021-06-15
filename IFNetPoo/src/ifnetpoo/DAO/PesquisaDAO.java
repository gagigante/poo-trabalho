package ifnetpoo.DAO;

import ifnetpoo.Interfaces.IDatabaseConnection;
import ifnetpoo.Models.Pesquisa;
import ifnetpoo.Models.Usuario;

import java.util.ArrayList;

public class PesquisaDAO {
    private IDatabaseConnection conn;
    
    // PROVISORIO
    private final ArrayList<Pesquisa> gruposPesquisa = new ArrayList<>();
    
    public PesquisaDAO(IDatabaseConnection conn) {
        this.conn = conn;
    }
    
    public ArrayList<Pesquisa> getGrupos() {
        return this.gruposPesquisa;
    }
    
     public Pesquisa getGrupoPorIndex(int index) {
        int size = this.gruposPesquisa.size();
        
        if (index < 0 || index > size - 1) {
            throw new Error("Grupo não encontrado");
        }
        
        return this.gruposPesquisa.get(index); 
    }
     
    public Pesquisa criarGrupo(Usuario orientador, String nome) {
        Pesquisa novoGrupo = new Pesquisa(orientador, nome);
        
        this.gruposPesquisa.add(novoGrupo);
        
        return novoGrupo;
    }
    
    public void removerGrupo(int index) {
        this.getGrupoPorIndex(index);
        
        this.gruposPesquisa.remove(index);
    }
}
