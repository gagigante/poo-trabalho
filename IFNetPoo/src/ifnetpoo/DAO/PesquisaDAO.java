package ifnetpoo.DAO;

import ifnetpoo.Models.Pesquisa;
import ifnetpoo.Models.Professor;
import ifnetpoo.Models.Usuario;
import ifnetpoo.Models.Aluno;

import ifnetpoo.Interfaces.IDatabaseConnection;

import ifnetpoo.CustomExceptions.ExcessaoUsuarioJaAssociado;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PesquisaDAO {
    private IDatabaseConnection conn;    
    
    public PesquisaDAO(IDatabaseConnection conn) {
        this.conn = conn;
    }
    
    public ArrayList<Pesquisa> getGrupos() {
        PreparedStatement stmt;
        ResultSet rs=null;
        var grupos = new ArrayList<Pesquisa>();
        
        try {
            stmt = this.conn.getConn().prepareStatement("select g.*, u.id as id_usuario, u.nome, u.prontuario, u.email, u.area"
                 + " from grupos as g"
                 + " inner join usuarios as u on g.criador_id = u.id"
                 + " where g.tipo = 'pesquisa';");
            rs = stmt.executeQuery();
            
            while (rs.next()) {
                var criador = new Professor(rs.getInt("id_usuario"), rs.getString("nome"), rs.getString("prontuario"), rs.getString("email"), rs.getString("area"));
            
                var grupo = new Pesquisa(rs.getInt("id"), criador, rs.getString("titulo"));
                grupos.add(grupo);
            }
        } catch (SQLException e) {}
        
        return grupos;
    }
    
    public ArrayList<Pesquisa> getTresGruposMaisPopulares() {
        PreparedStatement stmt;
        ResultSet rs=null;
        var grupos = new ArrayList<Pesquisa>();
        
        try {
            stmt = this.conn.getConn().prepareStatement("select g.*, u.id as id_usuario, u.nome, u.prontuario, u.email, u.area,"
                 + " COUNT(ag.aluno_id) as qtd_alunos from grupos as g"
                 + " left join alunos_grupos as ag on g.id = ag.grupo_id"
                 + " inner join usuarios as u on g.criador_id = u.id"
                 + " where g.tipo = 'pesquisa'"
                 + " group by g.id limit 3;");
            rs = stmt.executeQuery();
            
            while (rs.next()) {
                var criador = new Professor(rs.getInt("id_usuario"), rs.getString("nome"), rs.getString("prontuario"), rs.getString("email"), rs.getString("area"));
            
                var grupo = new Pesquisa(rs.getInt("id"), criador, rs.getString("nome"));
                grupo.setQuantidadeAlunos(rs.getInt("qtd_alunos"));
                grupos.add(grupo);
            }
        } catch (SQLException e) {}
        
        return grupos;
    }
  
    public Pesquisa criarGrupo(Usuario orientador, String nome) {
        PreparedStatement stmt;
         
        try {
            stmt = this.conn.getConn().prepareStatement("insert into grupos (titulo, criador_id, tipo) values(?, ?, 'pesquisa')");
            stmt.setString(1, nome);
            stmt.setInt(2, orientador.getId());
            stmt.execute();
        } catch (SQLException e) {}
        
        Pesquisa novoGrupo = new Pesquisa(orientador, nome);
        
        return novoGrupo;
    }
    
    public void removerGrupo(int id) {
        PreparedStatement stmt;
        
        try {
            stmt = this.conn.getConn().prepareStatement("delete from grupos where id = ?");
            stmt.setInt(1, id);
            stmt.execute();
        } catch (SQLException e) {}
    }
    
    public boolean isUsuarioAssociadoComGrupo(int idGrupo, int idUsuario) {
        PreparedStatement stmt;
        ResultSet rs=null;
        
        try {
            stmt = this.conn.getConn().prepareStatement("select * from alunos_grupos where grupo_id = ? and aluno_id = ?;");
            stmt.setInt(1, idGrupo);
            stmt.setInt(2, idUsuario);
            rs = stmt.executeQuery();
            
            if (!rs.next()) {
                return false;
            }
            
            return true;
        } catch (SQLException e) {}
         
        return false;
    }
    
    public void cadastraAlunoEmGrupo(Aluno aluno, Pesquisa grupo) {
        PreparedStatement stmt;
         
        if (this.isUsuarioAssociadoComGrupo(grupo.getId(), aluno.getId())){
            throw new ExcessaoUsuarioJaAssociado("Usuário já associado com esse grupo");
        }
        
        try {
            stmt = this.conn.getConn().prepareStatement("insert into alunos_grupos (aluno_id, grupo_id) values(?, ?)");
            stmt.setInt(1, aluno.getId());
            stmt.setInt(2, grupo.getId());
            stmt.execute();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
}
