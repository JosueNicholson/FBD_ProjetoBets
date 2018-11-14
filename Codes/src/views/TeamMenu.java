package views;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

import controllers.TeamController;
import controllers.UserController;
import dao.TeamDao;
import pojo.Team;

public class TeamMenu {
	TeamController teamController;
	
	public TeamMenu() {
		this.teamController = new TeamController();
	}
	
    public void teamMenu() {
        boolean end = false;
        Scanner input = new Scanner(System.in);
        int num;
        while(!end) {
            System.out.println("Gerenciar Times");
            System.out.println("1- Inserir Time");
            System.out.println("2- Excluir Time");
            System.out.println("3- Listar times");
            System.out.println("0- Voltar ao Menu Principal");
            System.out.print(">> ");
            num = input.nextInt();

            switch (num) {
                case 1: insertTeamPrompt(); break;
                case 2: deleteTeamPrompt(); break;
                case 3: listTeamsPrompt(); break;
                case 0: end = !end; break;
                default: break;
            }
        }
    }
    public void insertTeamPrompt() {
    	Scanner input = new Scanner(System.in);
    	System.out.println("Insira o nome do time: ");
    	String name = input.nextLine();
    	System.out.println("\nInsira o nome abreviado do time");
    	String shortName = input.nextLine();
    	
    	if(teamController.addTeam(name, shortName)) {
    		System.out.println("\nTime adicionado com sucesso!");
    	}
    	else {
    		System.out.println("\nErro ao adicionar time");
    	}
    }
    public void deleteTeamPrompt() {
    	Scanner input = new Scanner(System.in);
    	System.out.println("Insira o ID do time a ser removido:");
    	int idTeamRemovido = input.nextInt();
    	
    	if(teamController.deleteTeam(idTeamRemovido)) {
    		System.out.println("\nTime removido com sucesso!");
    	}
    	else {
    		System.out.println("\nErro ao remover time");
    	}
    }
    public void listTeamsPrompt() {
    	ArrayList<Team> listTeams = new TeamDao().getListTeams();
        if (listTeams.size() == 0) {
            System.out.println("Empty");
        }
        else{
        	System.out.println("Lista de times cadastrados:");
        	for (Team team : listTeams) {   
        		System.out.println(team.getNameTeam()+ "|" + team.getShortName());
    		}
        	System.out.println();
        }

    }
}
