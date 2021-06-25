package ifnetpoo.Models;

import ifnetpoo.Interfaces.IMaterial;

public class PaginaWeb implements IMaterial {
    private final int id;
    private final String titulo;
    private final String categoria;
    private final Usuario criador;
    private final Disciplina disciplina;
    private final String url;
    
    public PaginaWeb(String titulo, String categoria, Usuario criador, String url, Disciplina disciplina) {
        this.id = 0; // DEVERIA INICIAR COMO NULL
        this.titulo = titulo;
        this.categoria = categoria;
        this.criador = criador;
        this.url = url;
        this.disciplina = disciplina;
    }
    
    public PaginaWeb(int id, String titulo, String categoria, Usuario criador, String url, Disciplina disciplina) {
        this.id = id;
        this.titulo = titulo;
        this.categoria = categoria;
        this.criador = criador;
        this.url = url;
        this.disciplina = disciplina;
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
            "; Criador: " + this.getCriador().getNome() + "; URL: " + this.getUrl();
    }

    @Override
    public String getTipoMaterial() {
        return "web";
    }
        
    public String getUrl() {
        return url;
    }   
}
