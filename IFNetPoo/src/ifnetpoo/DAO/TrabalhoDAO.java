package ifnetpoo.DAO;

import ifnetpoo.Models.Trabalho;
import ifnetpoo.Models.Usuario;
import ifnetpoo.Models.Disciplina;
import ifnetpoo.Models.Professor;
import ifnetpoo.Models.Aluno;

import ifnetpoo.Interfaces.IDatabaseConnection;

import ifnetpoo.CustomExceptions.ExcessaoUsuarioJaAssociado;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TrabalhoDAO {
    private IDatabaseConnection conn;
    
    public TrabalhoDAO(IDatabaseConnection conn) {
        this.conn = conn;
    }
    
    public ArrayList<Trabalho> getGrupos() {
        PreparedStatement stmt;
        ResultSet rs=null;
        var grupos = new ArrayList<Trabalho>();
        
        try {
            stmt = this.conn.getConn().prepareStatement("select g.*, u.id as id_usuario, u.nome, u.prontuario, u.email,"
                 + " u.area, d.nome as nome_disciplina, d.sigla as sigla_disciplina"
                 + " from grupos as g"
                 + " inner join usuarios as u on g.criador_id = u.id"
                 + " inner join disciplinas as d on g.disciplina_id = d.id"
                 + " where g.tipo = 'trabalho';");
            rs = stmt.executeQuery();
            
            while (rs.next()) {
                var criador = new Professor(rs.getInt("id_usuario"), rs.getString("nome"), rs.getString("prontuario"), rs.getString("email"), rs.getString("area"));
                var disciplina = new Disciplina(rs.getInt("disciplina_id"), rs.getString("nome_disciplina"), rs.getString("sigla_disciplina"));
                
                var grupo = new Trabalho(rs.getInt("id"), disciplina, rs.getString("titulo"), criador);
                grupos.add(grupo);
            }
        } catch (SQLException e) {}
        
        return grupos;
    }
    
    public ArrayList<Trabalho> getTresGruposMaisPopulares() {
        PreparedStatement stmt;
        ResultSet rs=null;
        var grupos = new ArrayList<Trabalho>();
        
        try {
            stmt = this.conn.getConn().prepareStatement("select g.*, u.id as id_usuario, u.nome, u.prontuario, u.email, u.area,"
                 + " COUNT(ag.aluno_id) as qtd_alunos, d.nome as nome_disciplina, d.sigla as sigla_disciplina"
                 + " from grupos as g"
                 + " left join alunos_grupos as ag on g.id = ag.grupo_id"
                 + " inner join usuarios as u on g.criador_id = u.id"
                 + " inner join disciplinas as d on g.disciplina_id = d.id"
                 + " where g.tipo = 'trabalho'"
                 + " group by g.id limit 3;");
            rs = stmt.executeQuery();
            
            while (rs.next()) {
                var criador = new Professor(rs.getInt("id_usuario"), rs.getString("nome"), rs.getString("prontuario"), rs.getString("email"), rs.getString("area"));
                var disciplina = new Disciplina(rs.getInt("disciplina_id"), rs.getString("nome_disciplina"), rs.getString("sigla_disciplina"));
                
                var grupo = new Trabalho(rs.getInt("id"), disciplina, rs.getString("titulo"), criador);
                grupo.setQuantidadeAlunos(rs.getInt("qtd_alunos"));
                grupos.add(grupo);
            }
        } catch (SQLException e) {}
        
        return grupos;
    }
    
    public Trabalho criarGrupo(Disciplina disciplina, String nome, Usuario criador) {
        PreparedStatement stmt;
         
        try {
            stmt = this.conn.getConn().prepareStatement("insert into grupos (titulo, criador_id, disciplina_id, tipo) values(?, ?, ?, 'trabalho')");
            stmt.setString(1, nome);
            stmt.setInt(2, criador.getId());
            stmt.setInt(3, disciplina.getId());
            stmt.execute();
        } catch (SQLException e) {}
        
        var novoGrupo = new Trabalho(disciplina, nome, criador);
        
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
    
    public ArrayList<Trabalho> getGruposPorDisciplina(int disciplina_id) {
        PreparedStatement stmt;
        ResultSet rs=null;
        var grupos = new ArrayList<Trabalho>();
        
        try {
            stmt = this.conn.getConn().prepareStatement("select g.*, u.id as id_usuario, u.nome, u.prontuario, u.email,"
                 + " u.area, d.nome as nome_disciplina, d.sigla as sigla_disciplina"
                 + " from grupos as g"
                 + " inner join usuarios as u on g.criador_id = u.id"
                 + " inner join disciplinas as d on g.disciplina_id = d.id"
                 + " where disciplina_id = ? and g.tipo = 'trabalho';");
            stmt.setInt(1, disciplina_id);
            rs = stmt.executeQuery();
            
            while (rs.next()) {
                var criador = new Professor(rs.getInt("id_usuario"), rs.getString("nome"), rs.getString("prontuario"), rs.getString("email"), rs.getString("area"));
                var disciplina = new Disciplina(rs.getInt("disciplina_id"), rs.getString("nome_disciplina"), rs.getString("sigla_disciplina"));
                
                var grupo = new Trabalho(rs.getInt("id"), disciplina, rs.getString("titulo"), criador);
                grupos.add(grupo);
            }
        } catch (SQLException e) {}
        
        return grupos;
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
    
    public void cadastraAlunoEmGrupo(Aluno aluno, Trabalho grupo) {
        PreparedStatement stmt;
        
        if (this.isUsuarioAssociadoComGrupo(grupo.getId(), aluno.getId())){
            throw new ExcessaoUsuarioJaAssociado("Usuário já associado com esse grupo");
        }
        
        try {
            stmt = this.conn.getConn().prepareStatement("insert into alunos_grupos (aluno_id, grupo_id) values(?, ?)");
            stmt.setInt(1, aluno.getId());
            stmt.setInt(2, grupo.getId());
            stmt.execute();
        } catch (SQLException e) {}
    }
}
