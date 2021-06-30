package ifnetpoo.DAO;

import ifnetpoo.Models.Professor;
import ifnetpoo.CustomExceptions.ExcessaoDuplicacao;
import ifnetpoo.CustomExceptions.ExcessaoItemNaoEncontrado;
import ifnetpoo.Interfaces.IDatabaseConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProfessorDAO {
    private final IDatabaseConnection conn;
    
    public ProfessorDAO(IDatabaseConnection conn) {
        this.conn = conn;
    }
     
    public ArrayList<Professor> getProfessores() {
        PreparedStatement stmt;
        ResultSet rs=null;
        var professores = new ArrayList<Professor>();
        
        try {
            stmt = this.conn.getConn().prepareStatement("select * from usuarios where tipo = 'professor'");
            rs = stmt.executeQuery();
            
            while (rs.next()) {
                var professor = new Professor(rs.getInt("id"), rs.getString("nome"), rs.getString("prontuario"), rs.getString("email"), rs.getString("area"));
                professores.add(professor);
            }
        } catch (SQLException e) {}
        
        return professores;
    }
    
    public Professor buscarProfessorPeloProntuario(String prontuario) {
        PreparedStatement stmt;
        ResultSet rs=null;
        
        try {
            stmt = this.conn.getConn().prepareStatement("select * from usuarios where prontuario = ? and tipo = 'professor'");
            stmt.setString(1, prontuario);
            rs = stmt.executeQuery();
            
            if (!rs.next()) {
                throw new ExcessaoItemNaoEncontrado("Professor não foi encontrado");
            }
            
            var professor = new Professor(rs.getInt("id"), rs.getString("nome"), rs.getString("prontuario"), rs.getString("email"), rs.getString("area"));
            return professor;
        } catch (SQLException e) {
             System.out.println(e);
        }
         
        return null;
    }
    
    private boolean isProntuarioDisponivel(String prontuario) {
        PreparedStatement stmt;
        ResultSet rs=null;
        
         try {
            stmt = this.conn.getConn().prepareStatement("select * from usuarios where prontuario = ?");
            stmt.setString(1, prontuario);
            rs = stmt.executeQuery();
            
            if (!rs.next()) {
                return false;
            }
            
            return true;
        } catch (SQLException e) {}
         
        return false;
    }
    
    public Professor cadastraProfessor(String nome, String prontuario, String email, String area) {
        if (this.isProntuarioDisponivel(prontuario)) {
            throw new ExcessaoDuplicacao("Já existe um usuário com esse prontuário", prontuario);
        }
        
        PreparedStatement stmt;
        
        try {
            stmt = this.conn.getConn().prepareStatement("insert into usuarios (nome, prontuario, email, area, tipo) values(?, ?, ?, ?, 'professor')");
            stmt.setString(1, nome);
            stmt.setString(2, prontuario);
            stmt.setString(3, email);
            stmt.setString(4, area);
            stmt.execute();
        } catch (SQLException e) {}
        
        var novoProfessor = new Professor(nome, prontuario, email, area);
        
        return novoProfessor;
    }
    
    public void removerProfessor(int id) {
        PreparedStatement stmt;
        
        try {
            stmt = this.conn.getConn().prepareStatement("delete from usuarios where id = ?");
            stmt.setInt(1, id);
            stmt.execute();
        } catch (SQLException e) {}   
    }
}
