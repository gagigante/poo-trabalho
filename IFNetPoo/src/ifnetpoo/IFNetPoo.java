/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ifnetpoo;

import ifnetpoo.Classes.Aluno;
import ifnetpoo.Classes.Disciplina;
import ifnetpoo.Classes.Grupo;
import ifnetpoo.Classes.Usuario;

import ifnetpoo.DAO.ProfessorDAO;
import ifnetpoo.DAO.AlunoDAO;
import ifnetpoo.DAO.CursoDAO;
import ifnetpoo.DAO.DisciplinaDAO;
import ifnetpoo.DAO.PesquisaDAO;
import ifnetpoo.DAO.TrabalhoDAO;

import ifnetpoo.CustomExceptions.ExcessaoDuplicacao;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

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
        DisciplinaDAO disciplinaDAO = new DisciplinaDAO();
        
        TrabalhoDAO trabalhoDAO = new TrabalhoDAO();
        PesquisaDAO pesquisaDAO = new PesquisaDAO();
        AlunoDAO alunoDAO = new AlunoDAO();
        CursoDAO cursoDAO = new CursoDAO();
        
        
        String opcaoSelecionada;
        
        Usuario usuarioLogado = null;
        
        // temp
        ArrayList<Grupo> grupos = new ArrayList<>();
        String nome;
        String prontuario;
        String email;
        String area;
        String nomeGrupo;
        String disciplina;
        String siglaDisciplina;
        String grupoSelecinado = null;
        String tipoGrupo = null;
        String disciplinaSelecionada;
        int i = 0;
        
        menu: while (true) {
            if (usuarioLogado != null) {
                System.out.println("\nAutenticado como: " + usuarioLogado.getNome() + "\n");
            }
            
            System.out.println("Selecione uma opção: \n");
            
            System.out.println("1 - Autenticar");
            System.out.println("2 - Sair");
            System.out.println("3 - Cadastrar professor");
            System.out.println("4 - Exibir lista de professores cadastrados");
            System.out.println("5 - Cadastrar aluno");
            System.out.println("6 - Exibir lista de alunos cadastrados");            
            System.out.println("7 - Cadastrar material:");
            System.out.println("8 - Listar materiais");
            
            System.out.println("");
            System.out.println("9 - Criar um grupo");
            System.out.println("10 - Excluir um grupo");
            System.out.println("11 - Listar grupos");
            System.out.println("12 - Listar grupos mais usuários");
            System.out.println("13 - Pesquisar grupos por disciplina");
            
            System.out.println("14 - Adicionar usuário");
            System.out.println("15 - Listar usuários mais populares");
            System.out.println("0 - Encerrar programa");
                                    
            opcaoSelecionada = scanner.next();

            switch (opcaoSelecionada) {
                case "1":
                    String tipoUsuario;                    
                    
                    do {
                        System.out.println("Você é um aluno ou um professor?\n");
                        System.out.println("1 - Aluno");
                        System.out.println("2 - Professor");
                        
                        tipoUsuario = scanner.next();
                        
                        if (!"1".equals(tipoUsuario) && !"2".equals(tipoUsuario)) {
                            System.out.println("\nOpção inválida\n");
                        }
                    } while (!"1".equals(tipoUsuario) && !"2".equals(tipoUsuario));
                    
                    System.out.println("Informe seu prontuário: ");
                    prontuario = scanner.next();
                    
                    if (tipoUsuario == "1") {
                        
                    }
                    
                    if ("2".equals(tipoUsuario)) {
                        try {
                            usuarioLogado = professorDAO.buscarProfessorPeloProntuario(prontuario);
                        } catch (Error err) {
                            System.out.println("\n" + err.getMessage() + "\n");
                        }
                    }
                    
                    break;
                case "2":
                    if (usuarioLogado != null) {
                        usuarioLogado = null;
                        System.out.println("\nAté mais!\n");                        
                    } else {
                        System.out.println("\nNão há nenhum usuário logado\n");
                    }
                    break;
                case "3":
                    System.out.println("Digite o nome:");
                    nome = scanner.next();
                    
                    System.out.println("\nDigite o prontuário:");
                    prontuario = scanner.next();
                    
                    System.out.println("\nDigite o e-mail:");
                    email = scanner.next();
                    
                    System.out.println("\nDigite a área:");
                    area = scanner.next();
                    
                    System.out.println("Cadastro da disciplina");
                    
                    try {
                        professorDAO.cadastraProfessor(nome, prontuario, email, area);
                        System.out.println("\nRegistro cadastrado com sucesso!\n");
                    } catch (ExcessaoDuplicacao err) {
                        System.out.println("\n" + err.getMessage() + "! Valor duplicado: " + err.getValorDuplicado() + "\n");
                    }
                    
                    break;
                case "4":
                    if (professorDAO.getProfessores().isEmpty()) {
                        System.out.println("\nNenhum professor cadastrado\n");
                        break;
                    }
                    
                    System.out.println("\n");
                    professorDAO.getProfessores().forEach(professor -> {
                        System.out.println("-----------------------------------");
                        System.out.println("Nome: " + professor.getNome());
                        System.out.println("Prontuário: " + professor.getProntuario());
                        System.out.println("E-mail: " + professor.getEmail());
                        System.out.println("Área: " + professor.getArea());
                    });
                    
                    System.out.println("-----------------------------------\n");
                    break;
                case "5":
                    ArrayList<Disciplina> disciplinasAlunos = new ArrayList<Disciplina>();
                    
                    System.out.println("Digite o nome: ");
                    nome = scanner.next();
                    
                    System.out.println("Digite o prontuário: ");
                    prontuario = scanner.next();
                    
                    System.out.println("Digite o e-mail: ");
                    email = scanner.next();
                    
                    System.out.println("\n");
                    
                    do {           
                        i = 1;
                        
                        System.out.println("-----------------------------------");
                        System.out.println("0 - Cadastrar uma nova Disciplina");
                        for (Disciplina d : disciplinaDAO.getDisciplinas()) {
                            if (!disciplinasAlunos.contains(d)) {
                                System.out.println(i + " - " + d.getNome());
                            }
                            i++;      
                        }
                        System.out.println(i + " - Sair");
                        System.out.println("-----------------------------------\n");

                        System.out.println("Selecione a disciplina: ");
                        disciplinaSelecionada = scanner.next();
                        
                        if (Integer.parseInt(disciplinaSelecionada) == 0) {
                            try {
                                System.out.println("Digite o nome da disciplina: ");
                                disciplina = scanner.next();

                                System.out.println("Digite a sigla da disciplina: ");
                                siglaDisciplina = scanner.next();

                                disciplinasAlunos.add(disciplinaDAO.cadastraDisciplina(disciplina, siglaDisciplina));
                            } catch (ExcessaoDuplicacao err) {
                                System.out.println("\n" + err.getMessage() + "! Valor duplicado: " + err.getValorDuplicado() + "\n");
                            }
                        }else{
                            if (Integer.parseInt(disciplinaSelecionada) > i) {
                                disciplinasAlunos.add(disciplinaDAO.getDisciplinaPorIndex(Integer.parseInt(disciplinaSelecionada) - 1));
                            }
                        }
                    } while (Integer.parseInt(disciplinaSelecionada) >= 0 && Integer.parseInt(disciplinaSelecionada) < i);
                                           
                    alunoDAO.cadastrarAluno(nome, prontuario, email, null, disciplinasAlunos);
                    break;
                case "6":
                    if (alunoDAO.getAlunos().isEmpty()) {
                        System.out.println("\nNenhum aluno cadastrado\n");
                        break;
                    }
                   
                    System.out.println("\n");
                    alunoDAO.getAlunos().forEach(aluno -> {
                        System.out.println("-----------------------------------");
                        System.out.println("Nome: " + aluno.getNome());
                        System.out.println("Prontuário: " + aluno.getProntuario());
                        System.out.println("E-mail: " + aluno.getEmail());
                        System.out.println("Disciplinas: ");
                        aluno.imprimeDisciplinas();
                        System.out.println(": " + aluno.getCursos());
                    });
                    
                    System.out.println("-----------------------------------\n");
                    break;
                    
                // GRUPOS
                case "9":
                    // CRIAR GRUPOS
                    if (usuarioLogado == null) {
                        System.out.println("\nVocê precisa estar autenticado\n");
                    } else {
                        if ("Aluno".equals(usuarioLogado.tipoUsuario())) {
                            System.out.println("Você precisa estar autenticado como um Professor");
                        } else {
                            System.out.println("Digite o nome do grupo: ");
                            nomeGrupo = scanner.next();
                            
                            tipoGrupo = null;
                            
                            do {
                                System.out.println("Qual é o tipo do grupo?\n");
                                
                                System.out.println("1 - Trabalho");
                                System.out.println("2 - Pesquisa");

                                tipoGrupo = scanner.next();

                                if (tipoGrupo != null && !"1".equals(tipoGrupo) && !"2".equals(tipoGrupo)) {
                                    System.out.println("\nOpção inválida\n");
                                }
                            } while (!"1".equals(tipoGrupo) && !"2".equals(tipoGrupo));
                            
                            // TRABALHO
                            if ("1".equals(tipoGrupo)) {
                                try {
                                    System.out.println("Digite o nome da disciplina: ");
                                    disciplina = scanner.next();

                                    System.out.println("Digite a sigla da disciplina: ");
                                    siglaDisciplina = scanner.next();
                                    
                                    Disciplina novaDisciplina = disciplinaDAO.cadastraDisciplina(disciplina, siglaDisciplina);
                                
                                    trabalhoDAO.criarGrupo(novaDisciplina, nomeGrupo, usuarioLogado);
                                } catch (ExcessaoDuplicacao err) {
                                    System.out.println("\n" + err.getMessage() + "! Valor duplicado: " + err.getValorDuplicado() + "\n");
                                }
                            }
                            
                            // PESQUISA
                            if ("2".equals(tipoGrupo)) {
                                try {
                                    pesquisaDAO.criarGrupo(usuarioLogado, nomeGrupo);
                                } catch (ExcessaoDuplicacao err) {
                                    System.out.println("\n" + err.getMessage() + "! Valor duplicado: " + err.getValorDuplicado() + "\n");
                                }
                            }
                        }
                    }
                    
                    break;
                case "10":
                    // EXCLUIR GRUPO
                    tipoGrupo = null;
                            
                    do {
                        System.out.println("Qual é o tipo do grupo que deseja remover?\n");

                        System.out.println("1 - Trabalho");
                        System.out.println("2 - Pesquisa");

                        tipoGrupo = scanner.next();

                        if (tipoGrupo != null && !"1".equals(tipoGrupo) && !"2".equals(tipoGrupo)) {
                            System.out.println("\nOpção inválida\n");
                        }
                    } while (!"1".equals(tipoGrupo) && !"2".equals(tipoGrupo));
                    
                    grupos.clear();
                    
                    // TRABALHO
                    if ("1".equals(tipoGrupo)) {
                        grupos.addAll(trabalhoDAO.getGrupos());
                  
                        if (grupos.isEmpty()) {
                            System.out.println("\nNenhum grupo cadastrado\n");
                        } else {
                            i = 1;

                            System.out.println("\n");
                            for (Grupo grupo : grupos) {
                                System.out.println("-----------------------------------");
                                System.out.println(i + " - " + grupo.getGrupoOverview());
                                i++;                            
                            }                        
                            System.out.println("-----------------------------------\n");
                            
                                  
                            System.out.println("Selecione o grupo que deseja excluir: ");
                            grupoSelecinado = scanner.next();
                            
                            try {
                                trabalhoDAO.removerGrupo(Integer.parseInt(grupoSelecinado) - 1);
                            } catch (Error err) {
                                System.out.println(err.getMessage());
                            }
                            
                        }
                    // PESQUISA
                    } else {
                        grupos.addAll(pesquisaDAO.getGrupos());
                  
                        if (grupos.isEmpty()) {
                            System.out.println("\nNenhum grupo cadastrado\n");
                        } else {
                            i = 1;

                            System.out.println("\n");
                            for (Grupo grupo : grupos) {
                                System.out.println("-----------------------------------");
                                System.out.println(i + " - " + grupo.getGrupoOverview());
                                i++;                            
                            }                        
                            System.out.println("-----------------------------------\n");
                            
                            System.out.println("Selecione o grupo que deseja excluir: ");
                            grupoSelecinado = scanner.next();
                            
                            try {
                                pesquisaDAO.removerGrupo(Integer.parseInt(grupoSelecinado) - 1);
                            } catch (Error err) {
                                System.out.println(err.getMessage());
                            }
                            
                        }
                    }

                    break;
                case "11":
                    // LISTAR GRUPOS
                    grupos.clear();
                    
                    grupos.addAll(pesquisaDAO.getGrupos());
                    grupos.addAll(trabalhoDAO.getGrupos());
                    
                    if (grupos.isEmpty()) {
                        System.out.println("\nNenhum grupo cadastrado\n");
                    } else {
                        grupos.forEach(grupo -> {
                            System.out.println("-----------------------------------");
                            System.out.println(grupo.getGrupoOverview());
                        });
                        
                        System.out.println("-----------------------------------\n");
                    }
                    break;
                case "12":
                    // TODO
                    // LISTAR GRUPOS COM MAIS USUÁRIOS (3 MAIS POPULARES)
                    grupos.clear();
                    
                    pesquisaDAO.getGrupos().forEach(grupo -> {
                        grupos.add(grupo);
                    });
                    
                    trabalhoDAO.getGrupos().forEach(grupo -> {
                        grupos.add(grupo);
                    });
                    
                    break;
                case "13":
                    // CONSULTAR GRUPOS DE TRABALHO POR DISCIPLINA
                    i = 1;

                    System.out.println("\n");
                    for (Disciplina d : disciplinaDAO.getDisciplinas()) {
                        System.out.println("-----------------------------------");
                        System.out.println(i + " - " + d.getNome());
                        i++;      
                    }
                    System.out.println("-----------------------------------\n");
                            
                    System.out.println("Selecione a disciplina: ");
                    disciplinaSelecionada = scanner.next();
                    
                    try {
                        Disciplina disc = disciplinaDAO.getDisciplinaPorIndex(Integer.parseInt(disciplinaSelecionada) - 1);
                        
                        for (Grupo trabalho : trabalhoDAO.getGruposPorDisciplina(disc)) {
                            System.out.println("-----------------------------------");
                            System.out.println(trabalho.getGrupoOverview());
                        }
                        System.out.println("-----------------------------------\n");
                    } catch (Error err) {
                        System.out.println(err.getMessage());
                    }
                    break;
                case "14":
                    if (usuarioLogado == null) {
                        System.out.println("\nVocê precisa estar autenticado\n");
                    } else {
                        if ("Professor".equals(usuarioLogado.tipoUsuario())) {
                            System.out.println("Você precisa estar autenticado como um Aluno");
                        } else {
                            tipoGrupo = null;
                            
                            do {
                                System.out.println("Qual é o tipo do grupo que deseja remover?\n");

                                System.out.println("1 - Trabalho");
                                System.out.println("2 - Pesquisa");

                                tipoGrupo = scanner.next();

                                if (tipoGrupo != null && !"1".equals(tipoGrupo) && !"2".equals(tipoGrupo)) {
                                    System.out.println("\nOpção inválida\n");
                                }
                            } while (!"1".equals(tipoGrupo) && !"2".equals(tipoGrupo));
                            
                            // TRABALHO
                            if ("1".equals(tipoGrupo)) {
                                i = 1;

                                System.out.println("\n");
                                for (Grupo grupo : trabalhoDAO.getGrupos()) {
                                    System.out.println("-----------------------------------");
                                    System.out.println(i + " - " + grupo.getGrupoOverview());
                                    i++;                            
                                }                        
                                System.out.println("-----------------------------------\n");
                            
                                  
                                System.out.println("Selecione o grupo que deseja entrar: ");
                                grupoSelecinado = scanner.next();
                                
                                try {
                                    trabalhoDAO.getGrupoPorIndex(Integer.parseInt(grupoSelecinado) - 1).adicionaAlunoNoGrupo((Aluno) usuarioLogado);
                                } catch (Error err) {
                                    System.out.println(err.getMessage());
                                }
                                
                            } else {
                            // PESQUISA
                                i = 1;

                                System.out.println("\n");
                                for (Grupo grupo : pesquisaDAO.getGrupos()) {
                                    System.out.println("-----------------------------------");
                                    System.out.println(i + " - " + grupo.getGrupoOverview());
                                    i++;                            
                                }                        
                                System.out.println("-----------------------------------\n");
                            
                                System.out.println("Selecione o grupo que deseja entrar: ");
                                grupoSelecinado = scanner.next();
                                
                                try {
                                    pesquisaDAO.getGrupoPorIndex(Integer.parseInt(grupoSelecinado) - 1).adicionaAlunoNoGrupo((Aluno) usuarioLogado);
                                } catch (Error err) {
                                    System.out.println(err.getMessage());
                                }
                            }
                        }
                    }
                    break;
                case "15":

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
