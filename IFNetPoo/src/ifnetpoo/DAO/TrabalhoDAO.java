/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ifnetpoo.DAO;

import ifnetpoo.Classes.Trabalho;
import ifnetpoo.Classes.Usuario;
import ifnetpoo.Classes.Disciplina;
import java.util.ArrayList;

/**
 *
 * @author gabri
 */
public class TrabalhoDAO {
    private final ArrayList<Trabalho> gruposTrabalho = new ArrayList<>();
    
    public ArrayList<Trabalho> getGrupos() {
        return this.gruposTrabalho;
    }
    
    public Trabalho criarGrupo(Disciplina disciplina, String nome, Usuario criador) {
        Trabalho novoGrupo = new Trabalho(disciplina, nome, criador);
        
        this.gruposTrabalho.add(novoGrupo);
        
        return novoGrupo;
    }
}
