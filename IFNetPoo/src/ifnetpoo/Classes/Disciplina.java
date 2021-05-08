package ifnetpoo.Classes;

public class Disciplina implements Comparable<Disciplina> {
    private final String nome;
    private final String sigla;

    public Disciplina(String nome, String sigla) {
        this.nome = nome;
        this.sigla = sigla;
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
