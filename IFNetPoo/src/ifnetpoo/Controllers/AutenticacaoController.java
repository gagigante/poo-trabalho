/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ifnetpoo.Controllers;

import ifnetpoo.Models.Usuario;

import ifnetpoo.DAO.AlunoDAO;
import ifnetpoo.DAO.ProfessorDAO;

import ifnetpoo.Database.MySQLConnection;

/**
 *
 * @author gabriel
 */
public class AutenticacaoController {
    private final AlunoDAO alunoDAO;
    private final ProfessorDAO professorDAO;
    
    public AutenticacaoController() {
       var conn = new MySQLConnection();
       
       this.alunoDAO = new AlunoDAO(conn);
       this.professorDAO = new ProfessorDAO(conn);
    }
    
    public Usuario show(String prontuario, String tipoUsuario) {
        if (tipoUsuario.equals("professor")) {
            return this.professorDAO.buscarProfessorPeloProntuario(prontuario);
        }
        
        return this.alunoDAO.buscarAlunoPeloProntuario(prontuario);
    }
}
