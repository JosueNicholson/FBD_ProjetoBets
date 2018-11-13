package views;

import java.util.Scanner;

public class MenuPrincipal {
    UserMenu userMenu;
    TeamMenu teamMenu;
    BetMenu betMenu;
    MatchMenu matchMenu;

    public MenuPrincipal() {
        userMenu = new UserMenu();
        teamMenu = new TeamMenu();
        betMenu = new BetMenu();
        matchMenu = new MatchMenu();
    }

    public void startMenu() {
        boolean end = false;
        Scanner input = new Scanner(System.in);
        int num;


        while(!end) {
            System.out.println("Bem Vindo ao Super Bolão da Copa");
            System.out.println("Comandos:");
            System.out.println("1- Gerenciar Usuários");
            System.out.println("2- Gerenciar Times");
            System.out.println("3- Gerenciar Partidas");
            System.out.println("4- Gerenciar Apostas");
            System.out.println("0- Finalizar Sistema");
            System.out.print(">> ");
            num = input.nextInt();

            switch (num) {
                case 1: userMenu.userMenu(); break;
                case 2: teamMenu.teamMenu(); break;
                case 3: matchMenu.matchMenu(); break;
                case 4: betMenu.betMenu(); break;
                case 0: end = !end; break;
                default: break;
            }
        }

        System.out.println("\nAplicação Finalizada!\n");
    }
}
