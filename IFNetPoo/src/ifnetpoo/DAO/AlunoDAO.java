package ifnetpoo.DAO;


import ifnetpoo.Models.Aluno;

import ifnetpoo.Interfaces.IDatabaseConnection;

import ifnetpoo.CustomExceptions.ExcessaoDuplicacao;
import ifnetpoo.CustomExceptions.ExcessaoItemNaoEncontrado;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AlunoDAO {
    private IDatabaseConnection conn;

    public AlunoDAO(IDatabaseConnection conn) {
        this.conn = conn;
    }
    
    public ArrayList<Aluno> getAlunos() {
        PreparedStatement stmt;
        ResultSet rs=null;
        var alunos = new ArrayList<Aluno>();
        
        try {
            stmt = this.conn.getConn().prepareStatement("select * from usuarios where tipo = 'aluno'");
            rs = stmt.executeQuery();
            
            while (rs.next()) {
                var aluno = new Aluno(rs.getInt("id") ,rs.getString("nome"), rs.getString("prontuario"), rs.getString("email"));
                alunos.add(aluno);
            }
        } catch (SQLException e) {}
        
        return alunos;
    }
    
    public Aluno buscarAlunoPeloProntuario(String prontuario) {
        PreparedStatement stmt;
        ResultSet rs=null;
        
        try {
            stmt = this.conn.getConn().prepareStatement("select * from usuarios where prontuario = ? and tipo = 'aluno'");
            stmt.setString(1, prontuario);
            rs = stmt.executeQuery();
            
            if (!rs.next()) {
                throw new ExcessaoItemNaoEncontrado("Aluno não foi encontrado");
            }
            
            var aluno = new Aluno(rs.getInt("id"), rs.getString("nome"), rs.getString("prontuario"), rs.getString("email"));
            return aluno;
        } catch (SQLException e) {}
         
        return null;
    }
    
    public boolean isProntuarioDisponivel(String prontuario) {
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
    
    public Aluno cadastrarAluno(String nome, String prontuario, String email) {
        if (this.isProntuarioDisponivel(prontuario)) {
            throw new ExcessaoDuplicacao("Já existe um usuário com esse prontuário", prontuario);
        }
        
        PreparedStatement stmt;
        
        try {
            stmt = this.conn.getConn().prepareStatement("insert into usuarios (nome, prontuario, email, tipo) values(?, ?, ?, 'aluno')");
            stmt.setString(1, nome);
            stmt.setString(2, prontuario);
            stmt.setString(3, email);
            stmt.execute();
        } catch (SQLException e) {}
        
        var novoAluno = new Aluno(nome, prontuario, email);
        
        return novoAluno;
    }
    
    public void removerAluno(int id) {
        PreparedStatement stmt;
        
        try {
            stmt = this.conn.getConn().prepareStatement("delete from usuarios where id = ?");
            stmt.setInt(1, id);
            stmt.execute();
        } catch (SQLException e) {}   
    }
}
