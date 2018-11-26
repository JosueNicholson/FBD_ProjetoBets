package views;

import controllers.MatchController;
import controllers.TeamController;
import pojo.Match;
import pojo.Team;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class MatchMenu {
    MatchController matchController;

    public MatchMenu () {
        this.matchController = new MatchController();
    }

    public void matchMenu() {
        boolean end = false;
        Scanner input = new Scanner(System.in);
        int num = -1;
        while(!end) {
            System.out.println("\nGerenciar Partidas");
            System.out.println("1- Inserir Partida");
            System.out.println("2- Excluir Partida");
            System.out.println("3- Editar Partida");
            System.out.println("4- Listar Todas as Partidas");
            System.out.println("5- Consultar Lista de Partidas de um Time");
            System.out.println("0- Voltar ao Menu Principal");
            System.out.print(">> ");

            try {
                num = Integer.parseInt(input.nextLine());
            } catch (NumberFormatException e) {
                System.err.println("Digite somente números!");
            } finally {
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
    }

    public void addMatchPrompt() {
        try {
            Scanner input = new Scanner(System.in);
            System.out.print("Insira o ID do Primeiro Time: ");
            int idTeam1 = Integer.parseInt(input.nextLine());

            System.out.print("Insira o ID do Segundo Time: ");
            int idTeam2 = Integer.parseInt(input.nextLine());

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
        }  catch (NumberFormatException e) {
            System.err.println("Digite somente números!");
        }

    }

    public void deleteMatchPrompt() {
        try {
            Scanner input = new Scanner(System.in);
            System.out.print("Insira o ID da Partida a ser removida: ");
            int idMatch = Integer.parseInt(input.nextLine());

            if(matchController.deleteMatch(idMatch)) {
                System.out.println("\nPartida Removida!!!");
            } else {
                System.out.println("\nErro ao Remover a Partida!!!");
            }
        }  catch (NumberFormatException e) {
            System.err.println("Digite somente números!");
        }
    }

    public void editMatchPrompt() {
        try {
            Scanner input = new Scanner(System.in);
            System.out.print("Insira o ID da Partida a ser Editada: ");
            int idMatch = Integer.parseInt(input.nextLine());

            System.out.print("Insira o ID do Primeiro Time: ");
            int idTeam1 = Integer.parseInt(input.nextLine());

            System.out.print("Insira o ID do Segundo Time: ");
            int idTeam2 = Integer.parseInt(input.nextLine());

            System.out.print("Insira o Status da Partida (Agendada ou Finalizada): ");
            String status = input.nextLine();

            System.out.print("\nInsira: " +
                    "\n-1 Para Partidas Agendadas" +
                    "\n 0 Para Indicar que Houve um Empate" +
                    "\n 1 Para Indicar que o Primeiro Time Ganhou" +
                    "\n 2 Para Indicar que o Segundo Time Ganhou" +
                    "\n>> ");
            int winner = Integer.parseInt(input.nextLine());

            TeamController teamController = new TeamController();

            Team homeTeam = teamController.getTeamById(idTeam1);
            Team awayTeam = teamController.getTeamById(idTeam2);

            if (homeTeam != null && awayTeam != null) {
                if (matchController.updateMatch(idMatch, homeTeam, awayTeam, winner, status)) {
                    System.out.println("\nPartida Atualizada!!!");
                } else {
                    System.out.println("\nErro ao Atualizar a Partida!!!");
                }
            } else {
                System.out.println("\nTimes não encontrados!!!");
            }
        } catch (NumberFormatException e) {
            System.err.println("Digite somente números!");
        }
    }

    public void listMatches()  {
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

    public void getMatchByTeamId () {
        Scanner input = new Scanner(System.in);
        try {
            System.out.print("Insira o ID do Time: ");
            int idTeam = Integer.parseInt(input.nextLine());

            ArrayList<Match> list = matchController.getMatchByTeamId(idTeam);
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
        } catch (NumberFormatException e) {
            System.err.println("Digite somente números!");
        }
    }
}
