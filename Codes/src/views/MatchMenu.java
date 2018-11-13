package views;

import java.util.Scanner;

public class MatchMenu {
    public void matchMenu() {
        boolean end = false;
        Scanner input = new Scanner(System.in);
        int num;
        while(!end) {
            System.out.println("Gerenciar Partidas");
            System.out.println("1- Inserir Partida");
            System.out.println("2- Excluir Partida");
            System.out.println("3- Consultar Lista de Partidas de um Time");
            System.out.println("0- Voltar ao Menu Principal");
            System.out.print(">> ");
            num = input.nextInt();

            switch (num) {
                case 1: break;
                case 2: break;
                case 3: break;
                case 0: end = !end; break;
                default: break;
            }
        }
    }
}
