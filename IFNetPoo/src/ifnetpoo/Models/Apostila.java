package ifnetpoo.Models;

import ifnetpoo.Interfaces.IMaterial;

public class Apostila implements IMaterial {    
    private final String titulo;
    private final String categoria;
    private final Usuario criador;
    private final Disciplina disciplina;
    private final String area;
    
    public Apostila(String titulo, String categoria, Usuario criador, String area, Disciplina disciplina) {
        this.titulo = titulo;
        this.categoria = categoria;
        this.criador = criador; 
        this.disciplina = disciplina;
        this.area = area;
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
    public  String getTipoMaterial() {
        return "apostila";
    }
    
    @Override
    public String getOverviewMaterial() {
        return "Tipo: " + this.getTipoMaterial() + "; Título: " + this.getTitulo() + "; Categoria: " + this.getCategoria() + 
            "; Criador: " + this.getCriador().getNome() + "; Disciplina: " + this.getDisciplina() + "; Área: " + this.getArea();
    }

    public String getArea() {
        return area;
    }                 
}
