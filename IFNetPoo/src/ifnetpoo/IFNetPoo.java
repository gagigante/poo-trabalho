package ifnetpoo;

import ifnetpoo.Classes.Aluno;
import ifnetpoo.Classes.Apostila;
import ifnetpoo.Classes.Professor;
import ifnetpoo.Classes.Disciplina;
import ifnetpoo.Classes.Grupo;
import ifnetpoo.Classes.Livro;
import ifnetpoo.Classes.MySQLConnection;
import ifnetpoo.Classes.PaginaWeb;
import ifnetpoo.Classes.Usuario;

import ifnetpoo.Interfaces.IMaterial;

import ifnetpoo.DAO.ProfessorDAO;
import ifnetpoo.DAO.AlunoDAO;
import ifnetpoo.DAO.DisciplinaDAO;
import ifnetpoo.DAO.PesquisaDAO;
import ifnetpoo.DAO.TrabalhoDAO;
import ifnetpoo.DAO.MaterialDAO;

import ifnetpoo.CustomExceptions.ExcessaoDuplicacao;

import java.util.ArrayList;
import java.util.Scanner;

public class IFNetPoo {

    public static void main(String[] args) {
        MySQLConnection conn = new MySQLConnection();
        
        Scanner scanner = new Scanner(System.in);
        
        ProfessorDAO professorDAO = new ProfessorDAO();
        DisciplinaDAO disciplinaDAO = new DisciplinaDAO();
        TrabalhoDAO trabalhoDAO = new TrabalhoDAO();
        PesquisaDAO pesquisaDAO = new PesquisaDAO();
        AlunoDAO alunoDAO = new AlunoDAO();
        MaterialDAO materialDAO = new MaterialDAO();

        // temp
        Usuario usuarioLogado = null;
        
        final ArrayList<Grupo> grupos = new ArrayList<>();
        final ArrayList<Professor> professores = new ArrayList<>();
        final ArrayList<Aluno> alunos = new ArrayList<>();
        final ArrayList<Disciplina> disciplinas = new ArrayList<>();
        final ArrayList<IMaterial> materiais = new ArrayList<>();
        
        String opcaoSelecionada;
        String grupoSelecinado;
        String professorSelecionado;
        
        String nome;
        String prontuario;
        String email;
        String area;
        
        String disciplinaSelecionada;
        String nomeDisciplina;
        String siglaDisciplina;
        
        String tipoGrupo;
        String nomeGrupo;
        
        String materialSelecionado;
        String tipoMaterial;
        String nomeMaterial;
        String categoria;
        String autor;        
        int numeroDePaginas;
        int edicao;
        String url;
        int i;
        
        Disciplina disciplina;
  
        menu: while (true) {
            if (usuarioLogado != null) {
                System.out.println("\nAutenticado como: " + usuarioLogado.getNome() + "\n");
            }
            
            System.out.println("Selecione uma opção: \n");
            
            System.out.println("1 - Autenticar");
            System.out.println("2 - Sair");
            
            System.out.println();
            System.out.println("3 - Cadastrar professor");
            System.out.println("4 - Exibir lista de professores cadastrados");
            System.out.println("5 - Excluir professor");
            
            System.out.println();
            System.out.println("6 - Cadastrar aluno");
            System.out.println("7 - Exibir lista de alunos cadastrados");
            System.out.println("8 - Excluir aluno");
            
            System.out.println();
            System.out.println("9 - Cadastrar disciplina no sistema");
            System.out.println("10 - Excluir disciplina");
            System.out.println("11 - Listar disciplinas cadastradas");
            System.out.println("12 - Matricular-se em disciplina");
            System.out.println("13 - Ministrar disciplina");            
            
            System.out.println();
            System.out.println("14 - Criar um grupo");
            System.out.println("15 - Excluir um grupo");
            System.out.println("16 - Listar grupos");
            System.out.println("17 - Mostrar grupo mais popular");
            System.out.println("18 - Pesquisar grupos de trabalho por disciplina");
            System.out.println("19 - Entrar em um grupo");
            
            System.out.println();
            System.out.println("20 - Criar material");
            System.out.println("21 - Excluir material");
            System.out.println("22 - Listar materiais");                        
            
            System.out.println();
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
                    
                    if ("1".equals(tipoUsuario)) {
                        try {
                            usuarioLogado = alunoDAO.buscarAlunoPeloProntuario(prontuario);
                        } catch (Error err) {
                            System.out.println("\n" + err.getMessage() + "\n");
                        }
                    }
                    
                    if ("2".equals(tipoUsuario)) {
                        try {
                            usuarioLogado = professorDAO.buscarProfessorPeloProntuario(prontuario);
                        } catch (Error err) {
                            System.out.println("\n" + err.getMessage() + "\n");
                        }
                    }
                case "2":
                    // LOGOUT
                    if (usuarioLogado != null) {
                        usuarioLogado = null;
                        System.out.println("\nAté mais!\n");                        
                    } else {
                        System.out.println("\nNão há nenhum usuário logado\n");
                    }
                case "3": 
                    // CADASTRO PROFESSOR
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
                case "4":
                    // LISTAR PROFESSORES
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
                case "5":
                    // EXCLUIR PROFESSOR
                    professores.clear();
                    professores.addAll(professorDAO.getProfessores());
                  
                    if (professores.isEmpty()) {
                        System.out.println("\nNenhum professor cadastrado\n");
                    } else {
                        i = 1;

                        System.out.println("\n\n");
                        for (Professor professor : professores) {
                            System.out.println("-----------------------------------");
                            System.out.println(i + " - " + professor.getProntuario() + " - " + professor.getNome());
                            i++;
                        }                        
                        System.out.println("-----------------------------------\n");


                        System.out.println("Selecione o professor que deseja excluir: ");
                        professorSelecionado = scanner.next();

                        try {
                            professorDAO.removerProfessor(Integer.parseInt(professorSelecionado) - 1);
                        } catch (Error err) {
                            System.out.println(err.getMessage());
                        }
                    }
                case "6":
                    // CADASTRAR ALUNO
                    System.out.println("Digite o nome: ");
                    nome = scanner.next();
                    
                    System.out.println("Digite o prontuário: ");
                    prontuario = scanner.next();
                    
                    System.out.println("Digite o e-mail: ");
                    email = scanner.next();
                                           
                    alunoDAO.cadastrarAluno(nome, prontuario, email);
                
                case "7":
                    // LISTA DE ALUNOS
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
                        
                        if (!aluno.getDisciplinas().isEmpty()) {
                            for (Disciplina dic : aluno.getDisciplinas()) {
                                System.out.println("- " + dic.getNome());
                            }                    
                        }
                    });
                    
                    System.out.println("-----------------------------------\n");
                
                case "8":
                    // EXCLUIR ALUNOS                
                    alunos.clear();
                    alunos.addAll(alunoDAO.getAlunos());
                  
                    if (professores.isEmpty()) {
                        System.out.println("\nNenhum aluno cadastrado\n");
                    } else {
                        i = 1;

                        System.out.println("\n\n");
                        for (Aluno aluno : alunos) {
                            System.out.println("-----------------------------------");
                            System.out.println(i + " - " + aluno.getProntuario() + " - " + aluno.getNome());
                            i++;
                        }                        
                        System.out.println("-----------------------------------\n");


                        System.out.println("Selecione o aluno que deseja excluir: ");
                        professorSelecionado = scanner.next();

                        try {
                            alunoDAO.removerAluno(Integer.parseInt(professorSelecionado) - 1);
                        } catch (Error err) {
                            System.out.println(err.getMessage());
                        }
                    }
                
                case "9":
                    // CADASTRAR DISCIPLINA
                    System.out.println("Digite o nome da disciplina: ");
                    nomeDisciplina = scanner.next();
                    
                    System.out.println("Digite a sigla da disciplina: ");
                    siglaDisciplina = scanner.next();
                    
                    try {
                        disciplinaDAO.cadastraDisciplina(nomeDisciplina, siglaDisciplina);
                    } catch (ExcessaoDuplicacao err) {
                        System.out.println(err.getMessage());
                    }
                
                case "10":
                    // EXCLUIR DISCIPLINA
                    disciplinas.clear();
                    disciplinas.addAll(disciplinaDAO.getDisciplinas());
                  
                    if (disciplinas.isEmpty()) {
                        System.out.println("\nNenhuma disciplina cadastrada\n");
                    } else {
                        i = 1;

                        System.out.println("\n\n");
                        for (Disciplina d : disciplinas) {
                            System.out.println("-----------------------------------");
                            System.out.println(i + " - " + d.getNome());
                            i++;
                        }                        
                        System.out.println("-----------------------------------\n");

                        System.out.println("Selecione a disciplina que deseja excluir: ");
                        disciplinaSelecionada = scanner.next();

                        try {
                            disciplinaDAO.removerDisciplina(Integer.parseInt(disciplinaSelecionada) - 1);
                        } catch (Error err) {
                            System.out.println(err.getMessage());
                        }
                    }
                
                case "11":
                    // LISTAR DISCIPLINAS
                    disciplinas.clear();
                    disciplinas.addAll(disciplinaDAO.getDisciplinas());
                    
                    if (disciplinas.isEmpty()) {
                        System.out.println("Nenhuma disciplina cadastrada");
                        break;
                    }
                    
                    System.out.println("\n-----------------------------------");
                    for (Disciplina d : disciplinas) {
                        System.out.println("Sigla: " + d.getSigla() + "; Nome: " + d.getNome());
                        System.out.println("-----------------------------------");
                    }
                
                case "12":
                    // MATRICULAR-SE EM DISCIPLINA
                    if (usuarioLogado == null) {
                        System.out.println("Você precisa estar autenticado");
                        break;
                    }
                    
                    if ("professor".equals(usuarioLogado.tipoUsuario())) {
                        System.out.println("Você precisa estar autenticado como aluno");
                        break;
                    }
                    
                    disciplinas.clear();
                    disciplinas.addAll(disciplinaDAO.getDisciplinas());
                    i = 1;
                    
                    if (disciplinas.isEmpty()) {
                        System.out.println("\nNenhuma disciplina cadastrada\n");
                        break;
                    }
                    
                    System.out.println("\n\n");
                    for (Disciplina d : disciplinas) {
                        System.out.println("-----------------------------------");
                        System.out.println(i + " - " + d.getNome());
                        i++;
                    }                        
                    System.out.println("-----------------------------------\n");

                    System.out.println("Selecione a disciplina que deseja se matricular: ");
                    disciplinaSelecionada = scanner.next();
                    
                    try {
                        disciplina = disciplinaDAO.getDisciplinaPorIndex(Integer.parseInt(disciplinaSelecionada) - 1);
                        
                        usuarioLogado.cadastraDisciplina(disciplina);
                    } catch (Error err) {
                        System.out.println(err.getMessage());
                    }
                
                case "13":
                    // MINISTRAR DISCIPLINA
                    if (usuarioLogado == null) {
                        System.out.println("Você precisa estar autenticado");
                        break;
                    }
                    
                    if ("aluno".equals(usuarioLogado.tipoUsuario())) {
                        System.out.println("Você precisa estar autenticado como professor");
                        break;
                    }
                    
                    disciplinas.clear();
                    disciplinas.addAll(disciplinaDAO.getDisciplinas());
                    i = 1;
                    
                    if (disciplinas.isEmpty()) {
                        System.out.println("\nNenhuma disciplina cadastrada\n");
                        break;
                    }
                    
                    System.out.println("\n\n");
                    for (Disciplina d : disciplinas) {
                        System.out.println("-----------------------------------");
                        System.out.println(i + " - " + d.getNome());
                        i++;
                    }                        
                    System.out.println("-----------------------------------\n");

                    System.out.println("Selecione a disciplina que deseja lecionar: ");
                    disciplinaSelecionada = scanner.next();
                    
                    try {
                        disciplina = disciplinaDAO.getDisciplinaPorIndex(Integer.parseInt(disciplinaSelecionada) - 1);
                        
                        usuarioLogado.cadastraDisciplina(disciplina);
                    } catch (Error err) {
                        System.out.println(err.getMessage());
                    }
                
                case "14":
                    // CRIAR GRUPOS
                    if (usuarioLogado == null) {
                        System.out.println("\nVocê precisa estar autenticado\n");
                    } else {
                        if ("Aluno".equals(usuarioLogado.tipoUsuario())) {
                            System.out.println("\nVocê precisa estar autenticado como um Professor\n");
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
                                disciplinas.clear();
                                disciplinas.addAll(disciplinaDAO.getDisciplinas());
                  
                                if (disciplinas.isEmpty()) {
                                    System.out.println("\nNenhuma disciplina cadastrada\n");
                                } else {
                                    i = 1;

                                    System.out.println("\n\n");
                                    for (Disciplina d : disciplinas) {
                                        System.out.println("-----------------------------------");
                                        System.out.println(i + " - " + d.getNome());
                                        i++;
                                    }                        
                                    System.out.println("-----------------------------------\n");
                            
                                    System.out.println("Selecione a disciplina: ");
                                    disciplinaSelecionada = scanner.next();                                                        
                            
                                    try {
                                        disciplina = disciplinaDAO.getDisciplinaPorIndex(Integer.parseInt(disciplinaSelecionada) - 1);
                                       
                                        trabalhoDAO.criarGrupo(disciplina, nomeGrupo, usuarioLogado);
                                    } catch (Error err) {
                                        System.out.println(err.getMessage());
                                    }
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
                
                case "15":
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

                            System.out.println("\n\n");
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
                    }

                    // PESQUISA
                    if ("1".equals(tipoGrupo))  {
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
                
                case "16":
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
                
                case "17":
                    // Mostrar grupo mais popular
                    Grupo grupoMaisPopular = null;
                    grupos.clear();
                    
                    pesquisaDAO.getGrupos().forEach(grupo -> {
                        grupos.add(grupo);
                    });
                    
                    trabalhoDAO.getGrupos().forEach(grupo -> {
                        grupos.add(grupo);
                    });
                    
                    if (grupos.isEmpty()) {
                        System.out.println("Nenhum grupo cadastrado");
                        break;
                    }
                    
                    for (Grupo grupo : grupos) {
                        if (grupoMaisPopular == null) {
                            grupoMaisPopular = grupo;
                        }
                        
                        if (grupoMaisPopular.getQuantidadeAlunos() <= grupo.getQuantidadeAlunos()) {
                           grupoMaisPopular = grupo;
                        }
                    }
                    
                    System.out.println(grupoMaisPopular.getGrupoOverview());
                
                case "18":
                    // CONSULTAR GRUPOS DE TRABALHO POR DISCIPLINA
                    i = 1;

                    if (trabalhoDAO.getGrupos().isEmpty()) {
                        System.out.println("\nNão há nenhum grupo de trabalho\n");
                        break;
                    }                                        
                    
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
                
                case "19":
                    // CADASTRAR ALUNO EM GRUPO
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
                                    trabalhoDAO.getGrupoPorIndex(Integer.parseInt(grupoSelecinado) - 1).adicionaAlunoNoGrupo((Aluno) usuarioLogado);                                        ;
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
                
                case "20":
                    // CADASTRO DE MATERIAL
                    if (usuarioLogado == null) {
                        System.out.println("Você precisa estar autenticado");
                        break;
                    }
                    
                    tipoMaterial = null;
                            
                    do {
                        System.out.println("Qual é o tipo do material?\n");

                        System.out.println("1 - Livro");
                        System.out.println("2 - Apostila");
                        System.out.println("3 - Página WEB");

                        tipoMaterial = scanner.next();

                        if (tipoMaterial != null && !"1".equals(tipoMaterial) && !"2".equals(tipoMaterial) && !"3".equals(tipoMaterial)) {
                            System.out.println("\nOpção inválida\n");
                        }
                    } while (!"1".equals(tipoMaterial) && !"2".equals(tipoMaterial) && !"3".equals(tipoMaterial));
                    
                    // LIVRO
                    if ("1".equals(tipoMaterial)) {
                        System.out.println("Informe o nome do livro: ");
                        nomeMaterial = scanner.next();
                        
                        System.out.println("Informe a categoria: ");
                        categoria = scanner.next();
                        
                        System.out.println("Informe o autor: ");
                        autor = scanner.next();
                        
                        System.out.println("Informe a quantidade de páginas: ");
                        numeroDePaginas = Integer.parseInt(scanner.next());
                        
                        System.out.println("Informe a edição: ");
                        edicao = Integer.parseInt(scanner.next());
                        
                        disciplinas.clear();
                        disciplinas.addAll(disciplinaDAO.getDisciplinas());
                  
                        if (disciplinas.isEmpty()) {
                            System.out.println("\nNenhuma disciplina cadastrada\n");
                        } else {
                            i = 1;

                            System.out.println("\n\n");
                            for (Disciplina d : disciplinas) {
                                System.out.println("-----------------------------------");
                                System.out.println(i + " - " + d.getNome());
                                i++;
                            }                        
                            System.out.println("-----------------------------------\n");
                            
                            System.out.println("Selecione a disciplina: ");
                            disciplinaSelecionada = scanner.next();                                                        
                            
                            try {
                                disciplina = disciplinaDAO.getDisciplinaPorIndex(Integer.parseInt(disciplinaSelecionada) - 1);
                                
                                Livro livro = new Livro(nomeMaterial, categoria, usuarioLogado, disciplina, autor, numeroDePaginas, edicao);
                                
                                materialDAO.adicionarMaterial(livro);
                            } catch (Error err) {
                                System.out.println(err.getMessage());
                            }
                        }                        
                    }
                    
                    // APOSTILA
                    if ("2".equals(tipoMaterial)) {
                        System.out.println("Informe o nome da apostila: ");
                        nomeMaterial = scanner.next();
                        
                        System.out.println("Informe a categoria: ");
                        categoria = scanner.next();
                        
                        System.out.println("Informe a área da apostila: ");
                        area = scanner.next();
                        
                        disciplinas.clear();
                        disciplinas.addAll(disciplinaDAO.getDisciplinas());
                  
                        if (disciplinas.isEmpty()) {
                            System.out.println("\nNenhuma disciplina cadastrada\n");
                        } else {
                            i = 1;

                            System.out.println("\n\n");
                            for (Disciplina d : disciplinas) {
                                System.out.println("-----------------------------------");
                                System.out.println(i + " - " + d.getNome());
                                i++;
                            }                        
                            System.out.println("-----------------------------------\n");
                            
                            System.out.println("Selecione a disciplina: ");
                            disciplinaSelecionada = scanner.next();                                                        
                            
                            try {
                                disciplina = disciplinaDAO.getDisciplinaPorIndex(Integer.parseInt(disciplinaSelecionada) - 1);
                                
                                Apostila apostila = new Apostila(nomeMaterial, categoria, usuarioLogado, area, disciplina);
                                
                                materialDAO.adicionarMaterial(apostila);
                            } catch (Error err) {
                                System.out.println(err.getMessage());
                            }
                        }
                    }
                    
                    // PÁGINA WEB
                    if ("3".equals(tipoMaterial)) {
                        System.out.println("Informe o nome da página: ");
                        nomeMaterial = scanner.next();
                        
                        System.out.println("Informe a categoria: ");
                        categoria = scanner.next();
                        
                        System.out.println("Informe a url da página: ");
                        url = scanner.next();
                        
                        disciplinas.clear();
                        disciplinas.addAll(disciplinaDAO.getDisciplinas());
                  
                        if (disciplinas.isEmpty()) {
                            System.out.println("\nNenhuma disciplina cadastrada\n");
                        } else {
                            i = 1;

                            System.out.println("\n\n");
                            for (Disciplina d : disciplinas) {
                                System.out.println("-----------------------------------");
                                System.out.println(i + " - " + d.getNome());
                                i++;
                            }                        
                            System.out.println("-----------------------------------\n");
                            
                            System.out.println("Selecione a disciplina: ");
                            disciplinaSelecionada = scanner.next();                                                        
                            
                            try {
                                disciplina = disciplinaDAO.getDisciplinaPorIndex(Integer.parseInt(disciplinaSelecionada) - 1);
                                
                                PaginaWeb web = new PaginaWeb(nomeMaterial, categoria, usuarioLogado, url, disciplina);
                                
                                materialDAO.adicionarMaterial(web);
                            } catch (Error err) {
                                System.out.println(err.getMessage());
                            }
                        }
                    }
                
                case "21":
                   // EXCLUIR MATERIAL
                   materiais.clear();
                   materiais.addAll(materialDAO.getMateriais());
                  
                    if (materiais.isEmpty()) {
                        System.out.println("\nNenhum material cadastrado\n");
                    } else {
                        i = 1;

                        System.out.println("\n\n");
                        for (IMaterial m : materiais) {
                            System.out.println("-----------------------------------");
                            System.out.println(i + " - " + m.getOverviewMaterial());
                            i++;
                        }                        
                        System.out.println("-----------------------------------\n");
                                  
                        System.out.println("Selecione o material que deseja excluir: ");
                        materialSelecionado = scanner.next();
                            
                        try {
                            materialDAO.removerMaterial(Integer.parseInt(materialSelecionado) - 1);
                        } catch (Error err) {
                            System.out.println(err.getMessage());
                        }
                    }
                
                case "22":
                    // LISTAR MATERIAIS
                    materiais.clear();
                    materiais.addAll(materialDAO.getMateriais());
                    
                    if (materiais.isEmpty()) {
                        System.out.println("\nNenhum material cadastrado\n");
                        break;
                    }
                    
                    for (IMaterial m : materiais) {
                        System.out.println("-----------------------------------");
                        System.out.println(m.getOverviewMaterial());
                    }
                    System.out.println("-----------------------------------\n");
                case "0":
                    break menu;
                default: 
                    System.out.println("\nOpção inválida\n");
                    break;
            }
        };
    }
}
