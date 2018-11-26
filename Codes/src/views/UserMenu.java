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
        int num = -1;
        while(!end) {
            System.out.println("\nGerenciar Usuários");
            System.out.println("1- Inserir Usuário");
            System.out.println("2- Excluir Usuário");
            System.out.println("3- Editar Usuário");
            System.out.println("4- Listar Usuários");
            System.out.println("0- Voltar ao Menu Principal");
            System.out.print(">> ");

            try {
            num = Integer.parseInt(input.nextLine());

            } catch (NumberFormatException e) {
                System.err.println("Digite somente números!");
            } finally {
                switch (num) {
                    case 1: addUserPrompt(); break;
                    case 2: deleteUserPrompt(); break;
                    case 3: editUserPrompt(); break;
                    case 4: listUsers(); break;
                    case 0: end = !end; break;
                    default: break;
                }
            }
        }
    }

    public void addUserPrompt() {
        try {
            Scanner input = new Scanner(System.in);
            System.out.print("Insira o nome do usuário: ");
            String nameUser = input.nextLine();

            if(userController.addUser(nameUser)) {
                System.out.println("\nUsuário Adicionado!!!"); } else {
                System.out.println("\nErro ao Adicionar Usuário!!!");
            }
        } catch (NumberFormatException e) {
            System.err.println("Digite somente números!");
        }
    }


    public void deleteUserPrompt() {
        try {
            Scanner input = new Scanner(System.in);
            System.out.print("Insira o ID do usuário a ser removido: ");
            int idUser = Integer.parseInt(input.nextLine());

            if(userController.deleteUser(idUser)) {
                System.out.println("\nUsuário Removido!!!");
            } else {
                System.out.println("\nErro ao Remover o Usuário!!!");
            }
        } catch (NumberFormatException e) {
            System.err.println("Digite somente números!");
        }
    }

    public void editUserPrompt() {
        try {
            Scanner input = new Scanner(System.in);
            System.out.print("Insira o ID do usuário: ");
            int idUser = Integer.parseInt(input.nextLine());

            System.out.print("Insira o Nome do usuário: ");
            String nameUser = input.nextLine();

            if(userController.updateUser(idUser, nameUser)) {
                System.out.println("\nUsuário Editado!!!"); } else {
                System.out.println("\nErro ao Editar Usuário!!!");
            }
        } catch (NumberFormatException e) {
            System.err.println("Digite somente números!");
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
