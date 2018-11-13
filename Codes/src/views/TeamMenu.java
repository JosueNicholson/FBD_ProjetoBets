package views;

import java.util.Scanner;

public class TeamMenu {
    public void teamMenu() {
        boolean end = false;
        Scanner input = new Scanner(System.in);
        int num;
        while(!end) {
            System.out.println("Gerenciar Times");
            System.out.println("1- Inserir Time");
            System.out.println("2- Excluir Time");
            System.out.println("0- Voltar ao Menu Principal");
            System.out.print(">> ");
            num = input.nextInt();

            switch (num) {
                case 1: break;
                case 2: break;
                case 0: end = !end; break;
                default: break;
            }
        }
    }
}
