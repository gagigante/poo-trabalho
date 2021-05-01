/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ifnet.Classes;

/**
 *
 * @author gabriel
 */
public abstract class Usuario {
    private String nome;
    private String prontuario;
    private String email;
    
    public Usuario(String nome, String prontuario, String email) {
        this.nome = nome;
        this.prontuario = prontuario;
        this.email = email;
    }
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getProtuario() {
        return prontuario;
    }

    public void setProtuario(String prontuario) {
        this.prontuario = prontuario;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
}
