package ifnetpoo.Models;

public class Disciplina implements Comparable<Disciplina> {
    private final int id;
    private final String nome;
    private final String sigla;

    public Disciplina(String nome, String sigla) {
        this.id = 0;
        this.nome = nome;
        this.sigla = sigla;
    }
    
    public Disciplina(int id, String nome, String sigla) {
        this.id = id;
        this.nome = nome;
        this.sigla = sigla;
    }
    
    public int getId() {
        return this.id;
    }
    
    public String getNome() {
        return nome;
    }

    public String getSigla() {
        return sigla;
    }

    @Override
    public int compareTo(Disciplina disciplina) {
        return this.getNome().equals(disciplina.getNome()) ? 1 : -1;
    }
}
