/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ifnetpoo;

import ifnetpoo.DAO.ProfessorDAO;
import ifnetpoo.CustomExceptions.ExcessaoDuplicacao;
import ifnetpoo.DAO.AlunoDAO;
import ifnetpoo.DAO.CursoDAO;
import java.util.Scanner;

/**
 *
 * @author gabri
 */
public class IFNetPoo {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        ProfessorDAO professorDAO = new ProfessorDAO();
        AlunoDAO alunoDAO = new AlunoDAO();
        CursoDAO cursoDAO = new CursoDAO();
        
        String opcaoSelecionada;
        
        String nome;
        String prontuario;
        String email;
        String area;
        
        menu: while (true) {
            System.out.println("Selecione uma opção: \n");
            
            System.out.println("1 - Cadastrar professor");
            System.out.println("2 - Exibir lista de professores cadastrados");
            System.out.println("3 - Cadastrar aluno");
            System.out.println("4 - Exibir lista de alunos cadastrados");
            System.out.println("0 - Encerrar programa");
                                    
            opcaoSelecionada = scanner.next();

            switch(opcaoSelecionada) {
                case "1":
                    System.out.println("Digite o nome:");
                    nome = scanner.next();
                    
                    System.out.println("\nDigite o prontuário:");
                    prontuario = scanner.next();
                    
                    System.out.println("\nDigite o e-mail:");
                    email = scanner.next();
                    
                    System.out.println("\nDigite a área:");
                    area = scanner.next();
                    
                    try {
                        professorDAO.cadastraProfessor(nome, prontuario, email, area);
                        System.out.println("\nRegistro cadastrado com sucesso!\n");
                    } catch (ExcessaoDuplicacao err) {
                        System.out.println("\n" + err.getMessage() + "! Valor duplicado: " + err.getValorDuplicado() + "\n");
                    }
                    
                    break;
                case "2":
                    if (professorDAO.getProfessores().isEmpty()) {
                        System.out.println("\nNenhum professor cadastrado\n");
                        break;
                    }
                    
                    System.out.println("\n");
                    professorDAO.getProfessores().forEach(professor -> {
                        System.out.println("-----------------------------------");
                        System.out.println("Nome: " + professor.getNome());
                        System.out.println("Prontuário: " + professor.getProtuario());
                        System.out.println("E-mail: " + professor.getEmail());
                        System.out.println("Área: " + professor.getArea());
                    });
                    
                    System.out.println("-----------------------------------\n");
                    break;
                case "3":
                    System.out.println("Digite o nome: ");
                    nome = scanner.next();
                    
                    System.out.println("Digite o prontuário: ");
                    prontuario = scanner.next();
                    
                    System.out.println("Digite o e-mail: ");
                    email = scanner.next();
                            
                    // ArrayList<Curso> cursos
                    break;
                case "4":
                    if (alunoDAO.getAlunos().isEmpty()) {
                        System.out.println("\nNenhum aluno cadastrado\n");
                        break;
                    }
                   
                    System.out.println("\n");
                    alunoDAO.getAlunos().forEach(aluno -> {
                        System.out.println("-----------------------------------");
                        System.out.println("Nome: " + aluno.getNome());
                        System.out.println("Prontuário: " + aluno.getProtuario());
                        System.out.println("E-mail: " + aluno.getEmail());
                        System.out.println(": " + aluno.getCursos());
                    });
                    
                    System.out.println("-----------------------------------\n");
                    break;
                case "0":
                    break menu;
                default:
                    System.out.println("\nOpção inválida\n");
                    break;
            }
        }
    }
    
}
