package views;

import java.util.ArrayList;
import java.util.Scanner;

import controllers.BetController;
import controllers.MatchController;
import controllers.ShotController;
import controllers.UserController;
import pojo.Bet;
import pojo.Shot;
import pojo.User;

public class BetMenu {
	BetController betController;
	public BetMenu() {
    	betController = new BetController();
	}
    public void betMenu() {
        boolean end = false;
        Scanner input = new Scanner(System.in);
        int num;
        while(!end) {
            System.out.println("Gerenciar Apostas");
            System.out.println("1- Inserir Aposta");
            System.out.println("2- Excluir Aposta");
            System.out.println("3- Listar apostas de um usuário");
            System.out.println("4- Listar Usuários que Acertaram o Resultado de um Partida");
            System.out.println("0- Voltar ao Menu Principal");
            System.out.print(">> ");
            num = input.nextInt();

            switch (num) {
                case 1: this.insertBetPrompt(); break;
                case 2: this.deleteBetPrompt(); break;
                case 3: this.listBetsPrompt(); break;
                case 4: this.listUsersDidHitAShot(); break;
                case 0: end = !end; break;
                default: break;
            }
        }
    }
    public void insertBetPrompt() {
    	Scanner input = new Scanner(System.in);
    	System.out.println("Insira o id do usuario:");
    	int idUser = input.nextInt();
    	System.out.println("Insira o id do bet:");
    	int idBet = input.nextInt();
    	Bet bet = betController.addBet(idUser, idBet);
    	if(bet!=null) {
    		MenuShot(input, idBet);
    		int anotherShot = 1;
    		while(anotherShot==1) {
	    		System.out.println("Deseja dar outro palpite?");
	    		System.out.println("0- Não");
	    		System.out.println("1- Sim");
	    		anotherShot = input.nextInt();
	    		if(anotherShot==1) {
	    			MenuShot(input, idBet);
	    		}
    		}
    		System.out.println("Aposta feita!");
    	}
    	else {
    		System.out.println("Não foi possível adicionar. \nVerifique se o id do usuário e o id do bet que você inseriu estão corretos.");
    	}
    }
	private void MenuShot(Scanner input, int idBet) {
		System.out.println("Insira o id da partida:");
		int idMatch = input.nextInt();
		System.out.println("Digite o número correspondente o palpite:");
		MatchController matchController = new MatchController();
		
		String nameHomeTeam = matchController.getMatchById(idMatch).getHomeTeam().getNameTeam();
		String nameAwayTeam = matchController.getMatchById(idMatch).getAwayTeam().getNameTeam();
		
		System.out.println("0- Empate");
		System.out.println("1- "+nameHomeTeam+" vai ganhar");
		System.out.println("2- "+nameAwayTeam+" vai ganhar");
		
		int winner = input.nextInt();
		ShotController shotController = new ShotController();
		if (shotController.addShot(idBet, idMatch, winner)) {
			System.out.println("Palpite registrado");
		}
		else {
			System.out.println("Houve um erro ao registrar o palpite.");
		}
	}
    public void deleteBetPrompt() {
       	Scanner input = new Scanner(System.in);
    	System.out.println("Insira o id do bet:");
    	int id = input.nextInt();
    	if (betController.deleteBet(id)) {
    		System.out.println("Bet deletado com sucesso!");
    	}
    	else {
    		System.out.println("Erro ao deletar bet!");
    	}
    }
    public void listBetsPrompt() {
    	Scanner input = new Scanner(System.in);
    	System.out.println("Insira o id do usuario:");
    	int id = input.nextInt();
    	ArrayList<Bet> listBets = betController.getBetsByUser(id);
    	if(listBets.isEmpty()) {
    		System.out.println("\nEste usuário não possui apostas cadastradas");
    	}
    	else {
        	System.out.println("Lista de apostas deste usuário:");
        	System.out.println("IDBET|IDUSER:NAMEUSER");
        	for (Bet bet : listBets) {
        		System.out.println("  "+bet.getIdBet()+"  |  "+bet.getUser().getIdUser()+":"+bet.getUser().getNameUser());
        		ArrayList<Shot> listShots = new ShotController().getListShotsByIdBet(bet.getIdBet());
        		for (Shot shot : listShots) {
					System.out.println(shot.getMatch().getHomeTeam().getShortName()+
										" x "+shot.getMatch().getAwayTeam().getShortName()+
										" - Palpite : "+shot.getWinner());
				}
			}
    	}
    }
    public void listUsersDidHitAShot() {
    	UserController uc = new UserController();
    	System.out.println("Insira um id de uma partida:");
    	Scanner input = new Scanner(System.in);
    	int idMatch = input.nextInt();
    	ArrayList<User> listUsers = uc.getUsersWhoDidHitAShot(idMatch);
    	for (User user : listUsers) {
			System.out.println(user.getIdUser()+" "+user.getNameUser());
		}
    }
}
