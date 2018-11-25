package views;

import java.util.ArrayList;
import java.util.Scanner;

import controllers.BetController;
import pojo.Bet;

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
            System.out.println("5- Listar Usuários que Acertaram o Resultado de um Partida");
            System.out.println("0- Voltar ao Menu Principal");
            System.out.print(">> ");
            num = input.nextInt();

            switch (num) {
                case 1: this.insertBetPrompt(); break;
                case 2: this.deleteBetPrompt(); break;
                case 3: this.listBetsPrompt(); break;
                case 4: break;
                case 5: break;
                case 0: end = !end; break;
                default: break;
            }
        }
    }
    public void insertBetPrompt() {
    	Scanner input = new Scanner(System.in);
    	System.out.println("Insira o id do usuario:");
    	int id = input.nextInt();
    	if(betController.addBet(id)) {
    		System.out.println("\n"); //PRECISA TERMINAR SHOTS E MATCHS PARA CONTINUAR...
    	}
    }
    public void deleteBetPrompt() {
       	Scanner input = new Scanner(System.in);
    	System.out.println("Insira o id do bet:");
    	int id = input.nextInt();
    	if (betController.deleteBet(id)) {
    		System.out.println("\n"); //PRECISA TERMINAR SHOTS E MATCHS PARA CONTINUAR...
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
        	for (Bet bet : listBets) {
        		//PRECISA TERMINAR SHOTS E MATCHS PARA CONTINUAR...
			}
    	}
    }
}
