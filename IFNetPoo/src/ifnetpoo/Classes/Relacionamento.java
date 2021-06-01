/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ifnetpoo.Classes;

import java.util.Date;

/**
 *
 * @author gabriel
 */
public class Relacionamento {
    private Date dataInicioAmizade;
    private double confiabilidade;
    private int amigosEmComum;

    public Relacionamento(Date dataInicioAmizade, double confiabilidade, int amigosEmComum) {
        this.dataInicioAmizade = dataInicioAmizade;
        this.confiabilidade = confiabilidade;
        this.amigosEmComum = amigosEmComum;
    }

    public Date getDataInicioAmizade() {
        return dataInicioAmizade;
    }

    public void setDataInicioAmizade(Date dataInicioAmizade) {
        this.dataInicioAmizade = dataInicioAmizade;
    }

    public double getConfiabilidade() {
        return confiabilidade;
    }

    public void setConfiabilidade(double confiabilidade) {
        this.confiabilidade = confiabilidade;
    }

    public int getAmigosEmComum() {
        return amigosEmComum;
    }

    public void setAmigosEmComum(int amigosEmComum) {
        this.amigosEmComum = amigosEmComum;
    }
}
