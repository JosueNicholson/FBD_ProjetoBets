package views;

import controllers.UserController;
import pojo.User;

import java.util.ArrayList;
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
            System.out.println("\nGerenciar Usuários");
            System.out.println("1- Inserir Usuário");
            System.out.println("2- Excluir Usuário");
            System.out.println("3- Listar Usuários");
            System.out.println("0- Voltar ao Menu Principal");
            System.out.print(">> ");
            num = input.nextInt();

            switch (num) {
                case 1: addUserPrompt(); break;
                case 2: deleteUserPrompt(); break;
                case 3: listUsers(); break;
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
            System.out.println("\nUsuário Adicionado!!!");
        } else {
            System.out.println("\nErro ao Adicionar Usuário!!!");
        }
    }

    public void deleteUserPrompt() {
        Scanner input = new Scanner(System.in);
        System.out.print("Insira o ID do usuário a ser removido: ");
        int idUser = input.nextInt();

        if(userController.deleteUser(idUser)) {
            System.out.println("\nUsuário Removido!!!");
        } else {
            System.out.println("\nErro ao Remover o Usuário!!!");
        }
    }

    public void listUsers() {
        ArrayList<User> list = userController.listUsers();
        System.out.println("\nLista de Usuários Cadastrados");
        if (list.size() == 0) {
            System.out.println("Empty");
        } else {
            for (User user: list) {
                System.out.println(user.getIdUser() + " | " + user.getNameUser());
            }
        }
    }
}
