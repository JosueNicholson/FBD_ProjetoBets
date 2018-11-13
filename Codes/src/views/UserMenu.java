package views;

import controllers.UserController;

import java.util.Scanner;

public class UserMenu {
    UserController userController;

    public UserMenu() {
        userController = new UserController();
    }

    public void userMenu() {
        boolean end = false;
        Scanner input = new Scanner(System.in);
        int num;
        while(!end) {
            System.out.println("Gerenciar Usuários");
            System.out.println("1- Inserir Usuário");
            System.out.println("2- Excluir Usuário");
            System.out.println("0- Voltar ao Menu Principal");
            System.out.print(">> ");
            num = input.nextInt();

            switch (num) {
                case 1: addUserPrompt(); break;
                case 2: addUserPrompt(); break;
                case 0: end = !end; break;
                default: break;
            }
        }
    }

    public void addUserPrompt() {
        Scanner input = new Scanner(System.in);
        System.out.print("Insira o nome do usuário: ");
        String nameUser = input.nextLine();

        if(userController.addUser(nameUser)) {
            System.out.println("Usuário Adicionado!!!");
        } else {
            System.out.println("Erro ao Adicionar Usuário!!!");
        }
    }
}
