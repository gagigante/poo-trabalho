/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ifnet;

import java.util.Scanner;

/**
 *
 * @author gabriel
 */
public class IFNet {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        String nome;
               
        while (true) {
            System.out.println("Selecione uma opção:\n\n");
            System.out.println("1 - Cadastrar usuário");
            System.out.println("2 - Cadastrar disciplina");
            
            scanner.next();
        }
    }
    
}
