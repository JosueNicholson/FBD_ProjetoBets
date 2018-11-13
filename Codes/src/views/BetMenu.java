package views;

import java.util.Scanner;

public class BetMenu {
    public void betMenu() {
        boolean end = false;
        Scanner input = new Scanner(System.in);
        int num;
        while(!end) {
            System.out.println("Gerenciar Apostas");
            System.out.println("1- Inserir Aposta");
            System.out.println("2- Excluir Aposta");
            System.out.println("5- Listar Usuários que Acertaram o Resultado de um Partida");
            System.out.println("0- Voltar ao Menu Principal");
            System.out.print(">> ");
            num = input.nextInt();

            switch (num) {
                case 1: break;
                case 2: break;
                case 3: break;
                case 4: break;
                case 5: break;
                case 0: end = !end; break;
                default: break;
            }
        }
    }
}
