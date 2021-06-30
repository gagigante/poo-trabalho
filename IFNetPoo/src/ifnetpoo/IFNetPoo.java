package ifnetpoo;

import ifnetpoo.Controllers.AlunoController;
import ifnetpoo.Controllers.ProfessorController;
import ifnetpoo.Models.Aluno;
import ifnetpoo.Models.Professor;
import ifnetpoo.Models.Disciplina;
import ifnetpoo.Models.Grupo;
import ifnetpoo.Database.MySQLConnection;
import ifnetpoo.Models.Usuario;

import ifnetpoo.Interfaces.IMaterial;

import ifnetpoo.DAO.ProfessorDAO;
import ifnetpoo.DAO.AlunoDAO;
import ifnetpoo.DAO.DisciplinaDAO;
import ifnetpoo.DAO.PesquisaDAO;
import ifnetpoo.DAO.TrabalhoDAO;
import ifnetpoo.DAO.MaterialDAO;

import ifnetpoo.CustomExceptions.ExcessaoDuplicacao;
import ifnetpoo.CustomExceptions.ExcessaoItemNaoEncontrado;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class IFNetPoo {

    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        
        ProfessorController professorController = new ProfessorController();
        AlunoController alunoController = new AlunoController();
        
        AlunoDAO alunoDAO = new AlunoDAO(new MySQLConnection());
        ProfessorDAO professorDAO = new ProfessorDAO(new MySQLConnection());
        DisciplinaDAO disciplinaDAO = new DisciplinaDAO(new MySQLConnection());
        TrabalhoDAO trabalhoDAO = new TrabalhoDAO(new MySQLConnection());
        PesquisaDAO pesquisaDAO = new PesquisaDAO(new MySQLConnection());
        
        MaterialDAO materialDAO = new MaterialDAO(new MySQLConnection());

        // temp
        int i;
        String opcaoSelecionada;
        
        Usuario usuarioLogado = null;
        
        final ArrayList<Professor> professores = new ArrayList<>();
        final ArrayList<Aluno> alunos = new ArrayList<>();
        
        int professorSelecionado;
        int alunoSelecionado;
        
        
        final ArrayList<Grupo> grupos = new ArrayList<>();
        final ArrayList<Disciplina> disciplinas = new ArrayList<>();
        final ArrayList<IMaterial> materiais = new ArrayList<>();
        
        
        String grupoSelecinado;
        
        
        String nome;
        String prontuario;
        String email;
        String area;
        
        String disciplinaSelecionada;
        int disciplinaIndex;
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
        
        
        Disciplina disciplina = null;
  
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
            System.out.println("9 - Cadastrar disciplina");
            System.out.println("10 - Exibir lista de disciplinas cadastradas");
            System.out.println("11 - Excluir disciplina");
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
                    
                    break;
                case "2":
                    // LOGOUT
                    if (usuarioLogado != null) {
                        usuarioLogado = null;
                        System.out.println("\nAté mais!\n");                        
                    } else {
                        System.out.println("\nNão há nenhum usuário logado\n");
                    }
                    
                    break;
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
                        professorController.store(nome, prontuario, email, area);
                        
                        System.out.println("\nRegistro cadastrado com sucesso!\n");
                    } catch (ExcessaoDuplicacao err) {
                        System.out.println("\n" + err.getMessage() + "! Valor duplicado: " + err.getValorDuplicado() + "\n");
                    }
                    
                    break;
                case "4":
                    // LISTAR PROFESSORES
                    professores.clear();
                    professores.addAll(professorController.index());
                    
                   if (professores.isEmpty()) {
                        System.out.println("\nNenhum professor cadastrado\n");
                        break;
                    }
                    
                    System.out.println("\n");
                    professores.forEach(professor -> {
                        System.out.println("-----------------------------------");
                        System.out.println("Nome: " + professor.getNome());
                        System.out.println("Prontuário: " + professor.getProntuario());
                        System.out.println("E-mail: " + professor.getEmail());
                        System.out.println("Área: " + professor.getArea());
                        
                        if (!professor.getDisciplinas().isEmpty()) {
                            System.out.println("Disciplinas Ministradas: ");
                            
                            professor.getDisciplinas().forEach(d -> {
                                System.out.println(" - " + d.getNome());
                            });
                        }
                    });
                    
                    System.out.println("-----------------------------------\n");
                    
                    break;
                case "5":
                    // EXCLUIR PROFESSOR
                    professores.clear();
                    professores.addAll(professorController.index());
                  
                    if (professores.isEmpty()) {
                        System.out.println("\nNenhum professor cadastrado\n");
                        break;
                    } 
                    
                    i = 1;

                    System.out.println("\n\n");
                    for (Professor professor : professores) {
                        System.out.println("-----------------------------------");
                        System.out.println(i + " - " + professor.getProntuario() + " - " + professor.getNome());
                        i++;
                    }                        
                    System.out.println("-----------------------------------\n");

                    System.out.println("Selecione o professor que deseja excluir: ");
                    professorSelecionado = Integer.parseInt(scanner.next()) - 1;

                    try {
                        professorController.destroy(professorSelecionado);
                    } catch (ExcessaoItemNaoEncontrado err) {
                        System.out.println("\n" + err.getMessage() + "\n");
                    }
                    
                    break;
                case "6":
                    // CADASTRAR ALUNO
                    System.out.println("Digite o nome: ");
                    nome = scanner.next();
                    
                    System.out.println("Digite o prontuário: ");
                    prontuario = scanner.next();
                    
                    System.out.println("Digite o e-mail: ");
                    email = scanner.next();
                                         
                    try {
                        alunoController.store(nome, prontuario, email);
                    } catch (ExcessaoDuplicacao err) {
                        System.out.println("\n" + err.getMessage() + "\n");
                    }
                    
                    break;
                case "7":
                    // LISTA DE ALUNOS
                    alunos.clear();
                    alunos.addAll(alunoController.index());
                    
                    if (alunos.isEmpty()) {
                        System.out.println("\nNenhum aluno cadastrado\n");
                        break;
                    }
                    
                    System.out.println("\n");
                    alunos.forEach(aluno -> {
                        System.out.println("-----------------------------------");
                        System.out.println("Nome: " + aluno.getNome());
                        System.out.println("Prontuário: " + aluno.getProntuario());
                        System.out.println("E-mail: " + aluno.getEmail());
                        
                        if (!aluno.getDisciplinas().isEmpty()) {
                            System.out.println("Disciplinas Matriculadas: ");
                            
                            aluno.getDisciplinas().forEach(d -> {
                                System.out.println(" - " + d.getNome());
                            });
                        }
                    });
                    
                    System.out.println("-----------------------------------\n");
                    
                    break;
                case "8":
                    // EXCLUIR ALUNOS                
                    alunos.clear();
                    alunos.addAll(alunoDAO.getAlunos());
                  
                    if (alunos.isEmpty()) {
                        System.out.println("\nNenhum aluno cadastrado\n");
                        break;
                    } 
                    
                    i = 1;

                    System.out.println("\n\n");
                    for (Aluno aluno : alunos) {
                        System.out.println("-----------------------------------");
                        System.out.println(i + " - " + aluno.getProntuario() + " - " + aluno.getNome());
                        i++;
                    }                        
                    System.out.println("-----------------------------------\n");

                    System.out.println("Selecione o aluno que deseja excluir: ");
                    alunoSelecionado = Integer.parseInt(scanner.next()) - 1;

                    try {
                        alunoController.destroy(alunoSelecionado);
                    } catch (ExcessaoItemNaoEncontrado err) {
                        System.out.println("\n" + err.getMessage() + "\n");
                    }
                    
                    break;
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
                    
                    break;
                case "10":
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
                    
                    break;
                case "11":
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
                            disciplinaDAO.removerDisciplinaPorIndex(Integer.parseInt(disciplinaSelecionada) - 1);
                        } catch (Error err) {
                            System.out.println(err.getMessage());
                        }
                    }
                    
                    break;
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
                        
                        disciplinaDAO.associaDiciplinaUsuario(disciplina.getSigla(), usuarioLogado.getProntuario());
                    } catch (Error err) {
                        System.out.println(err.getMessage());
                    }
                    
                    break;
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
                        
                        disciplinaDAO.associaDiciplinaUsuario(disciplina.getSigla(), usuarioLogado.getProntuario());
                    } catch (Error err) {
                        System.out.println(err.getMessage());
                    }
                    
                    break;
                case "14":
                    // CRIAR GRUPOS
                    if (usuarioLogado == null) {
                        System.out.println("\nVocê precisa estar autenticado\n");
                        break;
                    } 
                      
                    if ("Aluno".equals(usuarioLogado.tipoUsuario())) {
                        System.out.println("\nVocê precisa estar autenticado como um Professor\n");
                        break;
                    } 
                    
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
                    
                    System.out.println("Digite o nome do grupo: ");
                    nomeGrupo = scanner.next();

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
                    
                    break;
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
                            break;
                        } 
                        
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
                            trabalhoDAO.removerGrupoPorIndex(Integer.parseInt(grupoSelecinado) - 1);
                        } catch (Error err) {
                            System.out.println(err.getMessage());
                        }
                        
                        break;
                    }

                    // PESQUISA
                    if ("2".equals(tipoGrupo))  {
                        grupos.addAll(pesquisaDAO.getGrupos());
                  
                        if (grupos.isEmpty()) {
                            System.out.println("\nNenhum grupo cadastrado\n");
                            break;
                        }
                        
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
                            pesquisaDAO.removerGrupoPorIndex(Integer.parseInt(grupoSelecinado) - 1);
                        } catch (Error err) {
                            System.out.println(err.getMessage());
                        }
                        
                        break;
                    }
                    
                    break;
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
                    
                    break;
                case "17":
                    // Mostrar os 3 grupos mais populares
                    grupos.clear();
                    
                    pesquisaDAO.getTresGruposMaisPopulares().forEach(grupo -> {
                        grupos.add(grupo);
                    });
                    
                    trabalhoDAO.getTresGruposMaisPopulares().forEach(grupo -> {
                        grupos.add(grupo);
                    });
                    
                    if (grupos.isEmpty()) {
                        System.out.println("Nenhum grupo cadastrado");
                        break;
                    }
                    
                    Collections.sort(grupos);
                    
                    int limit = grupos.size() >= 3 ? 3 : grupos.size();
                    
                    for (i = 0; i < limit; i++) {
                        System.out.println(grupos.get(i).getGrupoOverview() + "; Quantidade de alunos: " + grupos.get(i).getQuantidadeAlunos());
                        System.out.println("-----------------------------------");
                    }
                    System.out.println();

                    
                    break;
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
                        var disc = disciplinaDAO.getDisciplinaPorIndex(Integer.parseInt(disciplinaSelecionada) - 1);
                        
                        for (Grupo trabalho : trabalhoDAO.getGruposPorDisciplina(disc.getId())) {
                            System.out.println("-----------------------------------");
                            System.out.println(trabalho.getGrupoOverview());
                        }
                        System.out.println("-----------------------------------\n");
                    } catch (Error err) {
                        System.out.println(err.getMessage());
                    }
                    
                    break;
                case "19":
                    // CADASTRAR ALUNO EM GRUPO
                    if (usuarioLogado == null) {
                        System.out.println("\nVocê precisa estar autenticado\n");
                        break;
                    } 
                    
                    if ("Professor".equals(usuarioLogado.tipoUsuario())) {
                        System.out.println("Você precisa estar autenticado como um Aluno");
                        break;
                    }
                    
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
                            var grupo = trabalhoDAO.getGrupoPorIndex(Integer.parseInt(grupoSelecinado) - 1);
                            trabalhoDAO.cadastraAlunoEmGrupo((Aluno) usuarioLogado, grupo);
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
                            var grupo = pesquisaDAO.getGrupoPorIndex(Integer.parseInt(grupoSelecinado) - 1);
                            pesquisaDAO.cadastraAlunoEmGrupo((Aluno) usuarioLogado, grupo);
                        } catch (Error err) {
                            System.out.println(err.getMessage());
                        }
                    }
                        
                    break;
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
                        } catch (ExcessaoItemNaoEncontrado err){
                            System.out.println(err.getMessage());
                            break;
                        }
                    }
                    
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
                        
                        try {
                            materialDAO.adicionarMaterial(nomeMaterial, categoria, autor, edicao, numeroDePaginas, usuarioLogado.getId(), disciplina.getId());
                        } catch (Error err) {
                            System.out.println(err.getMessage());
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
                        
                        try {
                            materialDAO.adicionarMaterial(nomeMaterial, categoria, usuarioLogado.getId(), area, disciplina.getId());
                        } catch (Error err) {
                            System.out.println(err.getMessage());
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
                  
                        try {
                            materialDAO.adicionarMaterial(nomeMaterial, categoria, url, usuarioLogado.getId(), disciplina.getId());
                        } catch (Error err) {
                            System.out.println(err.getMessage());
                        }
                    }
                    
                    break;
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
                    
                    break;
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
                    
                    break;
                case "0":
                    break menu;
                default: 
                    System.out.println("\nOpção inválida\n");
                    break;
            }
        };
    }
}
