package views;

import controllers.MatchController;
import controllers.TeamController;
import pojo.Match;
import pojo.Team;

import java.util.ArrayList;
import java.util.Scanner;

public class MatchMenu {
    MatchController matchController;

    public MatchMenu () {
        this.matchController = new MatchController();
    }

    public void matchMenu() {
        boolean end = false;
        Scanner input = new Scanner(System.in);
        int num;
        while(!end) {
            System.out.println("\nGerenciar Partidas");
            System.out.println("1- Inserir Partida");
            System.out.println("2- Excluir Partida");
            System.out.println("3- Editar Partida");
            System.out.println("4- Listar Todas as Partidas");
            System.out.println("5- Consultar Lista de Partidas de um Time");
            System.out.println("0- Voltar ao Menu Principal");
            System.out.print(">> ");
            num = input.nextInt();

            switch (num) {
                case 1: addMatchPrompt(); break;
                case 2: deleteMatchPrompt(); break;
                case 3: editMatchPrompt(); break;
                case 4: listMatches(); break;
                case 5: getMatchByTeamId(); break;
                case 0: end = !end; break;
                default: break;
            }
        }
    }

    public void addMatchPrompt() {
        Scanner input = new Scanner(System.in);
        System.out.print("Insira o ID do Primeiro Time: ");
        int idTeam1 = input.nextInt();

        System.out.print("Insira o ID do Segundo Time: ");
        int idTeam2 = input.nextInt();

        TeamController teamController = new TeamController();

        Team homeTeam = teamController.getTeamById(idTeam1);
        Team awayTeam = teamController.getTeamById(idTeam2);

        if (homeTeam != null && awayTeam != null) {
            if (matchController.addMatch(homeTeam, awayTeam)) {
                System.out.println("\nPartida Adicionada!!!");
            } else {
                System.out.println("\nErro ao Adicionar a Partida!!!");
            }
        } else {
            System.out.println("\nTimes não encontrados!!!");
        }
    }

    public void deleteMatchPrompt() {
        Scanner input = new Scanner(System.in);
        System.out.print("Insira o ID da Partida a ser removida: ");
        int idMatch = input.nextInt();

        if(matchController.deleteMatch(idMatch)) {
            System.out.println("\nPartida Removida!!!");
        } else {
            System.out.println("\nErro ao Remover a Partida!!!");
        }
    }

    public void editMatchPrompt() {
//        Scanner input = new Scanner(System.in);
//        System.out.print("Insira o ID da Partida a ser removida: ");
//        int idMatch = input.nextInt();

//        System.out.print("Insira o ID do Primeiro Time: ");
//        int idTeam1 = input.nextInt();
//
//        System.out.print("Insira o ID do Segundo Time: ");
//        int idTeam2 = input.nextInt();
//
//        TeamController teamController = new TeamController();
//
//        Team homeTeam = teamController.getTeamById(idTeam1);
//        Team awayTeam = teamController.getTeamById(idTeam2);
//
//        if (homeTeam != null && awayTeam != null) {
//            if (matchController.addMatch(homeTeam, awayTeam)) {
//                System.out.println("\nPartida Adicionada!!!");
//            } else {
//                System.out.println("\nErro ao Adicionar a Partida!!!");
//            }
//        } else {
//            System.out.println("\nTimes não encontrados!!!");
//        }
    }

    public void listMatches() {
        ArrayList<Match> list = matchController.listMatches();
        System.out.println("\nLista de Partidas Cadastrados");
        if (list.size() == 0) {
            System.out.println("Empty");
        } else {
            for (Match match: list) {
                System.out.println(match.getIdMatch() + " | "
                        + match.getHomeTeam().getNameTeam() + " | "
                        + match.getAwayTeam().getNameTeam()+ " | "
                        + match.getStatus() + " | "
                        + match.getWinner());
            }
        }
    }

    public void getMatchByTeamId() {
//        ArrayList<User> list = userController.listUsers();
//        System.out.println("\nLista de Usuários Cadastrados");
//        if (list.size() == 0) {
//            System.out.println("Empty");
//        } else {
//            for (User user: list) {
//                System.out.println(user.getIdUser() + " | " + user.getNameUser());
//            }
//        }
    }
}
