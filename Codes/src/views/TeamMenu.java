package views;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import controllers.TeamController;
import pojo.Team;

public class TeamMenu {
	TeamController teamController;
	
	public TeamMenu() {
		this.teamController = new TeamController();
	}
	
    public void teamMenu() {
	    try {
            boolean end = false;
            Scanner input = new Scanner(System.in);
            int num;
            while(!end) {
                System.out.println("Gerenciar Times");
                System.out.println("1- Inserir Time");
                System.out.println("2- Excluir Time");
                System.out.println("3- Editar Time");
                System.out.println("4- Listar times");
                System.out.println("0- Voltar ao Menu Principal");
                System.out.print(">> ");
                num = Integer.parseInt(input.nextLine());

                switch (num) {
                    case 1: insertTeamPrompt(); break;
                    case 2: deleteTeamPrompt(); break;
                    case 3: editTeamPrompt(); break;
                    case 4: listTeamsPrompt(); break;
                    case 0: end = !end; break;
                    default: break;
                }
            }
        } catch (NumberFormatException e) {
            System.err.println("Digite somente números!");
        }
    }
    public void insertTeamPrompt() {
	    try {
            Scanner input = new Scanner(System.in);
            System.out.println("Insira o Nome do Time - Ex.: Ceará: ");
            String name = input.nextLine();
            System.out.println("\nInsira o Nome Abreviado do Time - Ex.: CER: ");
            String shortName = input.nextLine();

            if(teamController.addTeam(name, shortName)) {
                System.out.println("\nTime adicionado com sucesso!");
            }
            else {
                System.out.println("\nErro ao adicionar time");
            }
        } catch (InputMismatchException e) {
            System.err.println("Digite somente números!");
        }
    }

    public void deleteTeamPrompt() {
	    try {
            Scanner input = new Scanner(System.in);
            System.out.print("Insira o ID do Time a ser Removido:");
            int idTeamRemovido = Integer.parseInt(input.nextLine());

            if(teamController.deleteTeam(idTeamRemovido)) {
                System.out.println("\nTime removido com sucesso!");
            }
            else {
                System.out.println("\nErro ao remover time");
            }
        } catch (NumberFormatException e) {
            System.err.println("Digite somente números!");
        }
    }

    public void editTeamPrompt() {
        try {
            Scanner input = new Scanner(System.in);
            System.out.print("Insira o ID do time a ser Editado: ");
            int idTeam = Integer.parseInt(input.nextLine());

            System.out.print("Insira o Nome do Time - Ex.: Ceará: ");
            String name = input.nextLine();
            System.out.print("\nInsira o Nome Abreviado do Time - Ex.: CER: ");
            String shortName = input.nextLine();

            if(teamController.editTeam(idTeam, name, shortName)) {
                System.out.println("\nTime Editado com Sucesso!");
            }
            else {
                System.out.println("\nErro ao Editar Time!!!");
            }
        } catch (NumberFormatException e) {
            System.err.println("Digite somente números!");
        }
    }

    public void listTeamsPrompt() {
        ArrayList<Team> listTeams = teamController.listTeams();
        if (listTeams.isEmpty()) {
            System.out.println("Não existem times cadastrados");
        }
        else{
            System.out.println("Lista de times cadastrados:");
            for (Team team : listTeams) {
                System.out.println(team.getIdTeam() + " | " + team.getNameTeam()+ " | " + team.getShortName());
            }
            System.out.println();
        }
    }
}
