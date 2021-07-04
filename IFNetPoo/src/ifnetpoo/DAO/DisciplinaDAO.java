package ifnetpoo.DAO;

import ifnetpoo.Models.Disciplina;

import ifnetpoo.Interfaces.IDatabaseConnection;

import ifnetpoo.CustomExceptions.ExcessaoDuplicacao;
import ifnetpoo.CustomExceptions.ExcessaoItemNaoEncontrado;
import ifnetpoo.CustomExceptions.ExcessaoUsuarioJaAssociado;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DisciplinaDAO {
    private IDatabaseConnection conn;
    
    public DisciplinaDAO(IDatabaseConnection conn) {
        this.conn = conn;
    }
     
    public ArrayList<Disciplina> getDisciplinas() {
        PreparedStatement stmt;
        ResultSet rs=null;
        var disciplinas = new ArrayList<Disciplina>();
        
        try {
            stmt = this.conn.getConn().prepareStatement("select * from disciplinas");
            rs = stmt.executeQuery();
            
            while (rs.next()) {
                var disciplina = new Disciplina(rs.getInt("id"), rs.getString("nome"), rs.getString("sigla"));
                disciplinas.add(disciplina);
            }
        } catch (SQLException e) {}
        
        return disciplinas;
    }
    
    public Disciplina getDisciplinaPorIndex(int index) {
        var disciplinas = this.getDisciplinas();
        int size = disciplinas.size();
        
        if (index < 0 || index > size - 1) {
            throw new ExcessaoItemNaoEncontrado("Disciplina não encontrada");
        }
        
        return disciplinas.get(index);
    }
    
    public boolean isSiglaDisponivel(String sigla) {
        PreparedStatement stmt;
        ResultSet rs=null;
        
         try {
            stmt = this.conn.getConn().prepareStatement("select * from disciplinas where sigla = ?");
            stmt.setString(1, sigla);
            rs = stmt.executeQuery();
            
            if (!rs.next()) {
                return false;
            }
            
            return true;
        } catch (SQLException e) {}
         
        return false;
    }
    
    public Disciplina cadastraDisciplina(String nome, String sigla) {
        if (this.isSiglaDisponivel(sigla)) {
            throw new ExcessaoDuplicacao("Já existe uma disciplina com essa sigla", sigla);
        }
        
        PreparedStatement stmt;
         
        try {
            stmt = this.conn.getConn().prepareStatement("insert into disciplinas (nome, sigla) values(?, ?)");
            stmt.setString(1, nome);
            stmt.setString(2, sigla);
            stmt.execute();
        } catch (SQLException e) {}
        
        var novaDisciplina = new Disciplina(nome, sigla);
        
        return novaDisciplina;
    }
    
    public void removerDisciplina(int id) {
        PreparedStatement stmt;
        
        try {
            stmt = this.conn.getConn().prepareStatement("delete from disciplinas where id = ?");
            stmt.setInt(1, id);
            stmt.execute();
        } catch (SQLException e) {}   
    }
    
    public ArrayList<Disciplina> getDisciplinasUsuario(String prontuario) {              
        PreparedStatement stmt;
        ResultSet rs=null;
        var disciplinas = new ArrayList<Disciplina>();
        
        try {
            stmt = this.conn.getConn().prepareStatement("SELECT * from disciplinas as d" +
            " inner join usuarios_disciplinas as ud on d.id = ud.disciplina_id" +
            " inner join usuarios as u on ud.usuario_id = u.id" +
            " where u.prontuario = ?;");
            stmt.setString(1, prontuario);
            rs = stmt.executeQuery();
            
            while (rs.next()) {
                var disciplina = new Disciplina(rs.getInt("id"), rs.getString("nome"), rs.getString("sigla"));
                disciplinas.add(disciplina);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        
        return disciplinas;
    }
    
    public boolean isUsuarioAssociadoComDisciplina(int idDisciplina, int idUsuario) {
        PreparedStatement stmt;
        ResultSet rs=null;
        
        try {
            stmt = this.conn.getConn().prepareStatement("select * from usuarios_disciplinas where disciplina_id = ? and usuario_id = ?;");
            stmt.setInt(1, idDisciplina);
            stmt.setInt(2, idUsuario);
            rs = stmt.executeQuery();
            
            if (!rs.next()) {
                return false;
            }
            
            return true;
        } catch (SQLException e) {}
         
        return false;
    }
    
    public void associaDiciplinaUsuario(String siglaDisciplina, String prontuario) {
        PreparedStatement stmt;
        ResultSet rs=null;
        int idUsuario = 0;
        int idDisciplina = 0;
        
        try {
            stmt = this.conn.getConn().prepareStatement("select * from usuarios where prontuario = ?");
            stmt.setString(1, prontuario);
            rs = stmt.executeQuery();
            
            while (rs.next()) {
                idUsuario = rs.getInt("id");
            }
        } catch (SQLException e) {}
        
        try {
            stmt = this.conn.getConn().prepareStatement("select * from disciplinas where sigla = ?");
            stmt.setString(1, siglaDisciplina);
            rs = stmt.executeQuery();
            
            while (rs.next()) {
                idDisciplina = rs.getInt("id");
            }
        } catch (SQLException e) {}
        
        if (this.isUsuarioAssociadoComDisciplina(idDisciplina, idUsuario)){
            throw new ExcessaoUsuarioJaAssociado("Usuário já associado com essa disciplina");
        }
        
        try {
            stmt = this.conn.getConn().prepareStatement("insert into usuarios_disciplinas (disciplina_id, usuario_id) values(?, ?);");
            stmt.setInt(1, idDisciplina);
            stmt.setInt(2, idUsuario);
            stmt.execute();
        } catch (SQLException e) {}
    }
}
