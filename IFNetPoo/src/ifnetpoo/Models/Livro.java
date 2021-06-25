package ifnetpoo.Models;

import ifnetpoo.Interfaces.IMaterial;

public class Livro implements IMaterial {
    private final int id;
    private final String titulo;
    private final String categoria;
    private final Usuario criador;
    private final Disciplina disciplina;
    private final String autor;
    private final int numeroDePaginas;
    private final int edicao;

    public Livro(
        String titulo, 
        String categoria, 
        Usuario criador,
        Disciplina disciplina,
        String autor, 
        int numeroDePaginas,
        int edicao
    ) {
        this.id = 0; // DEVERIA INICIAR COMO NULL
        this.titulo = titulo;
        this.categoria = categoria;
        this.criador = criador;
        this.disciplina = disciplina;
        this.autor = autor;
        this.numeroDePaginas = numeroDePaginas;
        this.edicao = edicao;
    }
    
     public Livro(
        int id,
        String titulo, 
        String categoria, 
        Usuario criador,
        Disciplina disciplina,
        String autor, 
        int numeroDePaginas,
        int edicao
    ) {
        this.id = id;
        this.titulo = titulo;
        this.categoria = categoria;
        this.criador = criador;
        this.disciplina = disciplina;
        this.autor = autor;
        this.numeroDePaginas = numeroDePaginas;
        this.edicao = edicao;
    }
    
    @Override
    public int getId() {
        return this.id;
    }
    
    @Override
    public String getTitulo() {
        return this.titulo;
    }

    @Override
    public String getCategoria() {
        return this.categoria;
    }

    @Override
    public Usuario getCriador() {
        return this.criador;
    }
    
    @Override
    public Disciplina getDisciplina() {
        return this.disciplina;
    }

    @Override
    public String getOverviewMaterial() {
        return "Tipo: " + this.getTipoMaterial() + "; Título: " + this.getTitulo() + "; Categoria: " + this.getCategoria() +
            "; Criador: " + this.getCriador().getNome() + "; Disciplina: " + this.getDisciplina() + "; Autor: " + this.getAutor() + ";" +
            "; Número de páginas: " + this.getNumeroDePaginas() + "; Edição: " + this.getEdicao();
    }

    @Override
    public String getTipoMaterial() {
        return "livro";
    }
    
    public String getAutor() {
        return this.autor;
    }
    
    public int getNumeroDePaginas() {
        return this.numeroDePaginas;
    }
    
    public int getEdicao() {
        return this.edicao;
    }
}
