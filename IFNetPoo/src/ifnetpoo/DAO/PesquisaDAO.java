/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ifnetpoo.DAO;

import ifnetpoo.Classes.Pesquisa;
import ifnetpoo.Classes.Usuario;
import java.util.ArrayList;

/**
 *
 * @author gabri
 */
public class PesquisaDAO {
    private final ArrayList<Pesquisa> gruposPesquisa = new ArrayList<>();

    public ArrayList<Pesquisa> getGrupos() {
        return this.gruposPesquisa;
    }
    
    public Pesquisa criarGrupo(Usuario orientador, String nome) {
        Pesquisa novoGrupo = new Pesquisa(orientador, nome);
        
        this.gruposPesquisa.add(novoGrupo);
        
        return novoGrupo;
    }
}
