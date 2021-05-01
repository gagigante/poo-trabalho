/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ifnetpoo.DAO;

import ifnetpoo.Classes.Disciplina;
import ifnetpoo.CustomExceptions.ExcessaoDuplicacao;
import java.util.ArrayList;

/**
 *
 * @author gabri
 */
public class DisciplinaDAO {
    private final ArrayList<Disciplina> disciplinas = new ArrayList<>();
    
     public Disciplina cadastraDisciplina(String nome, String sigla) {
        Disciplina novaDisciplina = new Disciplina(nome, sigla);

        this.disciplinas.forEach(disciplina -> {
            if (disciplina.compareTo(novaDisciplina) == 1) {
                throw new ExcessaoDuplicacao("Já existe uma disciplina com essa sigla", disciplina.getSigla());
            };
        });

        this.disciplinas.add(novaDisciplina);

        return novaDisciplina;
    }
}
