package ifnetpoo.DAO;

import ifnetpoo.Models.Disciplina;
import ifnetpoo.CustomExceptions.ExcessaoDuplicacao;
import ifnetpoo.CustomExceptions.ExcessaoItemNaoEncontrado;
import ifnetpoo.Interfaces.IDatabaseConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DisciplinaDAO {
    private IDatabaseConnection conn;
    
    // PROVISORIO
    private final ArrayList<Disciplina> disciplinas = new ArrayList<>();
    
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
                var disciplina = new Disciplina(rs.getString("nome"), rs.getString("sigla"));
                disciplinas.add(disciplina);
            }
        } catch (SQLException e) {}
        
        return disciplinas;
    }
    
    public Disciplina getDisciplinaPorIndex(int index) {
        int size = this.disciplinas.size();
        
        if (index < 0 || index > size - 1) {
            throw new Error("Disciplina não encontrada");
        }
        
        return this.disciplinas.get(index);
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
    
    public void removerDisciplinaPorIndex(int index) {
        var disciplinas = new ArrayList<Disciplina>();
        PreparedStatement stmt;
        
        disciplinas = this.getDisciplinas();
        int size = disciplinas.size();
        
        if (index < 0 || index > size - 1) {
            throw new ExcessaoItemNaoEncontrado("Disciplina não foi encontrada");
        }
        
        var disciplinaSelecionada = disciplinas.get(index);
        
        try {
            stmt = this.conn.getConn().prepareStatement("delete from disciplinas where sigla = ?");
            stmt.setString(1, disciplinaSelecionada.getSigla());
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
                var disciplina = new Disciplina(rs.getString("nome"), rs.getString("sigla"));
                disciplinas.add(disciplina);
            }
        } catch (SQLException e) {}
        
        return disciplinas;
    }
}
