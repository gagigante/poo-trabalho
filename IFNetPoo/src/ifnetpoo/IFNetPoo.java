package ifnetpoo;

import ifnetpoo.Models.Aluno;
import ifnetpoo.Models.Professor;
import ifnetpoo.Models.Disciplina;
import ifnetpoo.Models.Grupo;
import ifnetpoo.Models.Usuario;

import ifnetpoo.Controllers.MaterialController;
import ifnetpoo.Controllers.AlunoController;
import ifnetpoo.Controllers.AutenticacaoController;
import ifnetpoo.Controllers.DisciplinaController;
import ifnetpoo.Controllers.GrupoDePesquisaController;
import ifnetpoo.Controllers.GrupoDeTrabalhoController;
import ifnetpoo.Controllers.ProfessorController;

import ifnetpoo.Interfaces.IMaterial;

import ifnetpoo.CustomExceptions.ExcessaoDuplicacao;
import ifnetpoo.CustomExceptions.ExcessaoItemNaoEncontrado;
import ifnetpoo.CustomExceptions.ExcessaoUsuarioJaAssociado;
import ifnetpoo.Models.Pesquisa;
import ifnetpoo.Models.Trabalho;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class IFNetPoo {

    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        
        AutenticacaoController autenticacaoController = new AutenticacaoController();
        ProfessorController professorController = new ProfessorController();
        AlunoController alunoController = new AlunoController();
        DisciplinaController disciplinaController = new DisciplinaController();
        GrupoDePesquisaController grupoDePesquisaController = new GrupoDePesquisaController();
        GrupoDeTrabalhoController grupoDeTrabalhoController = new GrupoDeTrabalhoController();
        MaterialController materialController = new MaterialController();

        // temp
        int i;
        String opcaoSelecionada;
        
        Usuario usuarioLogado = null;
        
        final ArrayList<Professor> professores = new ArrayList<>();
        final ArrayList<Aluno> alunos = new ArrayList<>();
        final ArrayList<Disciplina> disciplinas = new ArrayList<>();
        final ArrayList<Grupo> grupos = new ArrayList<>();
        final ArrayList<IMaterial> materiais = new ArrayList<>();
        
        int professorSelecionado;
        int alunoSelecionado;
        int disciplinaSelecionada;
        int materialSelecionado;
        int grupoSelecinado;
        
        String tipoUsuario;
        
        String nome;
        String prontuario;
        String email;
        String area;
        
        Disciplina disciplina = null;
        
        String nomeDisciplina;
        String siglaDisciplina;
            
        Grupo grupo = null;
        
        String tipoGrupo;
        String nomeGrupo;
        
        String tipoMaterial;
        String nomeMaterial;
        String categoriaMaterial;
        String areaMaterial;
        String autorMaterial;
        int numeroDePaginasMaterial;
        int edicaoMaterial;
        String urlMaterial;
        
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
            System.out.println("17 - Mostrar grupos mais populares");
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
                            usuarioLogado = autenticacaoController.show(prontuario, "aluno");
                        } catch (ExcessaoItemNaoEncontrado err) {
                            System.out.println("\n" + err.getMessage() + "\n");
                        }
                    }
                    
                    if ("2".equals(tipoUsuario)) {
                        try {
                            usuarioLogado = autenticacaoController.show(prontuario, "professor");
                        } catch (ExcessaoItemNaoEncontrado err) {
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
                    for (var professor : professores) {
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
                    alunos.addAll(alunoController.index());
                  
                    if (alunos.isEmpty()) {
                        System.out.println("\nNenhum aluno cadastrado\n");
                        break;
                    } 
                    
                    i = 1;

                    System.out.println("\n\n");
                    for (var aluno : alunos) {
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
                        disciplinaController.store(nomeDisciplina, siglaDisciplina);
                    } catch (ExcessaoDuplicacao err) {
                        System.out.println("\n" + err.getMessage() + "\n");
                    }
                    
                    break;
                case "10":
                    // LISTAR DISCIPLINAS
                    disciplinas.clear();
                    disciplinas.addAll(disciplinaController.index());
                    
                    if (disciplinas.isEmpty()) {
                        System.out.println("Nenhuma disciplina cadastrada");
                        break;
                    }
                    
                    System.out.println();
                    for (var d : disciplinas) {
                        System.out.println("-----------------------------------");
                        System.out.println("Sigla: " + d.getSigla() + "; Nome: " + d.getNome());
                    }
                    System.out.println("-----------------------------------\n");
                    
                    break;
                case "11":
                    // EXCLUIR DISCIPLINA
                    disciplinas.clear();
                    disciplinas.addAll(disciplinaController.index());
                  
                    if (disciplinas.isEmpty()) {
                        System.out.println("\nNenhuma disciplina cadastrada\n");
                        break;
                    }
                    
                    i = 1;

                    System.out.println("\n\n");
                    for (var d : disciplinas) {
                        System.out.println("-----------------------------------");
                        System.out.println(i + " - " + d.getNome());
                        i++;
                    }                        
                    System.out.println("-----------------------------------\n");

                    System.out.println("Selecione a disciplina que deseja excluir: ");
                    disciplinaSelecionada = Integer.parseInt(scanner.next()) - 1;

                    try {
                        disciplinaController.destroy(disciplinaSelecionada);
                    } catch (ExcessaoItemNaoEncontrado err) {
                        System.out.println(err.getMessage());
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
                    disciplinas.addAll(disciplinaController.index());
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
                    disciplinaSelecionada = Integer.parseInt(scanner.next()) - 1;
                                       
                    try {
                        disciplina = disciplinaController.show(disciplinaSelecionada); 
                    } catch (ExcessaoItemNaoEncontrado err) {
                        System.out.println("\n" + err.getMessage() + "\n");
                    }
                    
                    try {
                        disciplinaController.associaAluno(disciplina, (Aluno) usuarioLogado);
                    } catch (ExcessaoUsuarioJaAssociado err) {
                        System.out.println("\n" + err.getMessage() + "\n");
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
                    disciplinas.addAll(disciplinaController.index());
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
                    disciplinaSelecionada = Integer.parseInt(scanner.next()) - 1;
                    
                    try {
                        disciplina = disciplinaController.show(disciplinaSelecionada);
                    } catch (ExcessaoItemNaoEncontrado err) {
                        System.out.println("\n" + err.getMessage() + "\n");
                    }
                    
                    try {
                        disciplinaController.associaProfessor(disciplina, (Professor) usuarioLogado);
                    } catch (ExcessaoUsuarioJaAssociado err) {
                        System.out.println("\n" + err.getMessage() + "\n");
                    }
                    
                    break;
                case "14":
                    // CRIAR GRUPOS
                    if (usuarioLogado == null) {
                        System.out.println("\nVocê precisa estar autenticado\n");
                        break;
                    } 
                      
                    if ("aluno".equals(usuarioLogado.tipoUsuario())) {
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
                        disciplinas.addAll(disciplinaController.index());

                        if (disciplinas.isEmpty()) {
                            System.out.println("\nNenhuma disciplina cadastrada\n");
                            break;
                        } 
                        
                        i = 1;

                        System.out.println("\n\n");
                        for (Disciplina d : disciplinas) {
                            System.out.println("-----------------------------------");
                            System.out.println(i + " - " + d.getNome());
                            i++;
                        }                        
                        System.out.println("-----------------------------------\n");

                        System.out.println("Selecione a disciplina: ");
                        disciplinaSelecionada = Integer.parseInt(scanner.next()) - 1;

                        try {
                            disciplina = disciplinaController.show(disciplinaSelecionada);

                            grupoDeTrabalhoController.store(disciplina, nomeGrupo, usuarioLogado);
                        } catch (ExcessaoItemNaoEncontrado err) {
                            System.out.println("\n" + err.getMessage() + "\n");
                        }   
                    }

                    // PESQUISA
                    if ("2".equals(tipoGrupo)) { 
                        grupoDePesquisaController.store(usuarioLogado, nomeGrupo);
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
                        grupos.addAll(grupoDeTrabalhoController.index());
                  
                        if (grupos.isEmpty()) {
                            System.out.println("\nNenhum grupo cadastrado\n");
                            break;
                        } 
                        
                        i = 1;

                        System.out.println("\n\n");
                        for (var gp : grupos) {
                            System.out.println("-----------------------------------");
                            System.out.println(i + " - " + gp.getGrupoOverview());
                            i++;
                        }                        
                        System.out.println("-----------------------------------\n");


                        System.out.println("Selecione o grupo que deseja excluir: ");
                        grupoSelecinado = Integer.parseInt(scanner.next()) - 1;

                        try {
                            grupoDeTrabalhoController.destroy(grupoSelecinado);
                        } catch (ExcessaoItemNaoEncontrado err) {
                            System.out.println("\n" + err.getMessage() + "\n");
                        }
                        
                        break;
                    }

                    // PESQUISA
                    if ("2".equals(tipoGrupo))  {
                        grupos.addAll(grupoDePesquisaController.index());
                  
                        if (grupos.isEmpty()) {
                            System.out.println("\nNenhum grupo cadastrado\n");
                            break;
                        }
                        
                        i = 1;

                        System.out.println("\n");
                        for (var gp : grupos) {
                            System.out.println("-----------------------------------");
                            System.out.println(i + " - " + gp.getGrupoOverview());
                            i++;
                        }                        
                        System.out.println("-----------------------------------\n");

                        System.out.println("Selecione o grupo que deseja excluir: ");
                        grupoSelecinado = Integer.parseInt(scanner.next()) - 1;

                        try {
                            grupoDePesquisaController.destroy(grupoSelecinado);
                        } catch (ExcessaoItemNaoEncontrado err) {
                            System.out.println("\n" + err.getMessage() + "\n");
                        }
                        
                        break;
                    }
                    
                    break;
                case "16":
                    // LISTAR GRUPOS
                    grupos.clear();
                    
                    grupos.addAll(grupoDePesquisaController.index());
                    grupos.addAll(grupoDeTrabalhoController.index());
                    
                    if (grupos.isEmpty()) {
                        System.out.println("\nNenhum grupo cadastrado\n");
                        break;
                    }
                    
                    grupos.forEach(gp -> {
                        System.out.println("-----------------------------------");
                        System.out.println(gp.getGrupoOverview());
                    });

                    System.out.println("-----------------------------------\n");
                    
                    break;
                case "17":
                    // Mostrar os 3 grupos mais populares
                    grupos.clear();
                    
                    grupoDePesquisaController.getMaisPopulares().forEach(gp -> {
                        grupos.add(gp);
                    });
                    
                    grupoDeTrabalhoController.getMaisPopulares().forEach(gp -> {
                        grupos.add(gp);
                    });
                    
                    if (grupos.isEmpty()) {
                        System.out.println("Nenhum grupo cadastrado");
                        break;
                    }
                    
                    Collections.sort(grupos);

                    int limit = grupos.size() >= 3 ? 3 : grupos.size();
                    
                    System.out.println();
                    for (i = 0; i < limit; i++) {
                        System.out.println(grupos.get(i).getGrupoOverview() + "; Quantidade de alunos: " + grupos.get(i).getQuantidadeAlunos());
                        System.out.println("-----------------------------------");
                    }
                    System.out.println();
                    
                    break;
                case "18":
                    // CONSULTAR GRUPOS DE TRABALHO POR DISCIPLINA
                    i = 1;

                    if (grupoDeTrabalhoController.index().isEmpty()) {
                        System.out.println("\nNão há nenhum grupo de trabalho\n");
                        break;
                    }                                        
                    
                    System.out.println("\n");
                    for (Disciplina d : disciplinaController.index()) {
                        System.out.println("-----------------------------------");
                        System.out.println(i + " - " + d.getNome());
                        i++;      
                    }
                    System.out.println("-----------------------------------\n");
                            
                    System.out.println("Selecione a disciplina: ");
                    disciplinaSelecionada = Integer.parseInt(scanner.next()) - 1;
                    
                    try {
                        disciplina = disciplinaController.show(disciplinaSelecionada);
                        
                        for (Grupo trabalho : grupoDeTrabalhoController.index(disciplina)) {
                            System.out.println("-----------------------------------");
                            System.out.println(trabalho.getGrupoOverview());
                        }
                        System.out.println("-----------------------------------\n");
                    } catch (ExcessaoItemNaoEncontrado err) {
                        System.out.println("\n" + err.getMessage() + "\n");
                    }
                    
                    break;
                case "19":
                    // CADASTRAR ALUNO EM GRUPO
                    if (usuarioLogado == null) {
                        System.out.println("\nVocê precisa estar autenticado\n");
                        break;
                    } 
                    
                    if ("professor".equals(usuarioLogado.tipoUsuario())) {
                        System.out.println("\nVocê precisa estar autenticado como um Aluno\n");
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
                        for (var gp : grupoDeTrabalhoController.index()) {
                            System.out.println("-----------------------------------");
                            System.out.println(i + " - " + gp.getGrupoOverview());
                            i++;
                        }           
                        System.out.println("-----------------------------------\n");


                        System.out.println("Selecione o grupo que deseja entrar: ");
                        grupoSelecinado = Integer.parseInt(scanner.next()) - 1;

                        try {
                            grupo = grupoDeTrabalhoController.show(grupoSelecinado);
                        } catch (ExcessaoItemNaoEncontrado err) {
                            System.out.println("\n" + err.getMessage() + "\n");
                        }
                        
                        try {
                            grupoDeTrabalhoController.associaAluno((Aluno) usuarioLogado, (Trabalho) grupo);
                        } catch (ExcessaoUsuarioJaAssociado err) {
                            System.out.println("\n" + err.getMessage() + "\n");
                        }
                        
                        break;
                    }
                    
                    // PESQUISA
                    i = 1;

                    System.out.println("\n");
                    for (var gp : grupoDePesquisaController.index()) {
                        System.out.println("-----------------------------------");
                        System.out.println(i + " - " + gp.getGrupoOverview());
                        i++;
                    }                        
                    System.out.println("-----------------------------------\n");

                    System.out.println("Selecione o grupo que deseja entrar: ");
                    grupoSelecinado = Integer.parseInt(scanner.next()) - 1;

                    try {
                        grupo = grupoDePesquisaController.show(grupoSelecinado);
                    } catch (ExcessaoItemNaoEncontrado err) {
                        System.out.println("\n" + err.getMessage() + "\n");
                    }
                    
                    try {
                        grupoDePesquisaController.associaAluno((Aluno) usuarioLogado, (Pesquisa) grupo);
                    } catch (ExcessaoUsuarioJaAssociado err) {
                        System.out.println("\n" + err.getMessage() + "\n");
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
                    disciplinas.addAll(disciplinaController.index());
                  
                    if (disciplinas.isEmpty()) {
                        System.out.println("\nNenhuma disciplina cadastrada\n");
                        break;
                    }
                    
                    i = 1;
                    
                    System.out.println("\n\n");                                        
                    for (Disciplina d : disciplinas) {
                        System.out.println("-----------------------------------");
                        System.out.println(i + " - " + d.getNome());
                        i++;
                    }                        
                    System.out.println("-----------------------------------\n");

                    System.out.println("Selecione a disciplina: ");
                    disciplinaSelecionada = Integer.parseInt(scanner.next()) - 1;

                    try {
                        disciplina = disciplinaController.show(disciplinaSelecionada);
                    } catch (ExcessaoItemNaoEncontrado err){
                        System.out.println("\n" + err.getMessage() + "\n");
                        break;
                    }
                    
                    // LIVRO
                    if ("1".equals(tipoMaterial)) {
                        System.out.println("Informe o nome do livro: ");
                        nomeMaterial = scanner.next();
                        
                        System.out.println("Informe a categoria: ");
                        categoriaMaterial = scanner.next();
                        
                        System.out.println("Informe o autor: ");
                        autorMaterial = scanner.next();
                        
                        System.out.println("Informe a quantidade de páginas: ");
                        numeroDePaginasMaterial = Integer.parseInt(scanner.next());
                        
                        System.out.println("Informe a edição: ");
                        edicaoMaterial = Integer.parseInt(scanner.next());
                                                
                        materialController.store(nomeMaterial, categoriaMaterial, autorMaterial, edicaoMaterial, numeroDePaginasMaterial, usuarioLogado, disciplina);
                    }
                    
                    // APOSTILA
                    if ("2".equals(tipoMaterial)) {
                        System.out.println("Informe o nome da apostila: ");
                        nomeMaterial = scanner.next();
                        
                        System.out.println("Informe a categoria: ");
                        categoriaMaterial = scanner.next();
                        
                        System.out.println("Informe a área da apostila: ");
                        areaMaterial = scanner.next();
                        
                        materialController.store(nomeMaterial, categoriaMaterial, usuarioLogado, areaMaterial, disciplina);
                    }
                    
                    // PÁGINA WEB
                    if ("3".equals(tipoMaterial)) {
                        System.out.println("Informe o nome da página: ");
                        nomeMaterial = scanner.next();
                        
                        System.out.println("Informe a categoria: ");
                        categoriaMaterial = scanner.next();
                        
                        System.out.println("Informe a url da página: ");
                        urlMaterial = scanner.next();
                        
                        materialController.store(nomeMaterial, categoriaMaterial, urlMaterial, usuarioLogado, disciplina);
                    }
                    
                    break;
                case "21":
                   // EXCLUIR MATERIAL
                   materiais.clear();
                   materiais.addAll(materialController.index());
                  
                    if (materiais.isEmpty()) {
                        System.out.println("\nNenhum material cadastrado\n");
                        break;
                    }
                    
                    i = 1;

                    System.out.println("\n\n");
                    for (IMaterial m : materiais) {
                        System.out.println("-----------------------------------");
                        System.out.println(i + " - " + m.getOverviewMaterial());
                        i++;
                    }                        
                    System.out.println("-----------------------------------\n");

                    System.out.println("Selecione o material que deseja excluir: ");
                    materialSelecionado = Integer.parseInt(scanner.next()) - 1;

                    try {
                        materialController.destroy(materialSelecionado);
                    } catch (ExcessaoItemNaoEncontrado err) {
                        System.out.println("\n" + err.getMessage() + "\n");
                    }
                    
                    break;
                case "22":
                    // LISTAR MATERIAIS
                    materiais.clear();
                    materiais.addAll(materialController.index());
                    
                    if (materiais.isEmpty()) {
                        System.out.println("\nNenhum material cadastrado\n");
                        break;
                    }
                    
                    materiais.forEach(material -> {
                        System.out.println("-----------------------------------");
                        System.out.println(material.getOverviewMaterial());
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
