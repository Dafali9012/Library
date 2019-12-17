import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Application {
    Application() {
        try {
            bookList = Files.readAllLines(path).toArray(String[]::new);
        } catch (IOException e) {
            e.printStackTrace();
        }
        PrintMainMenu();
    }

    private String bookArt =
            "    ______\n" +
            "  /      //\n" +
            " /______//\n" +
            "(______(/   ";

    private Path path = Paths.get("src/books.txt");
    private String[] bookList;

    private void PrintMainMenu() {
        System.out.print(bookArt);
        System.out.println("~Library");
        System.out.println("¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨");
        System.out.println("1. List of books");
        System.out.println();
        System.out.println("0. Exit application");
        System.out.println();
        System.out.print("Enter selection: ");

        int selection = Input(1, "Enter selection: ");
        System.out.println();
        if(selection==0) {
            System.out.println("Exiting...");
        } else if(selection == 1) {
            PrintBooksList();
        }
    }

    private void PrintBooksList() {
        System.out.print(bookArt);
        System.out.println("~Library/List of books");
        System.out.println("¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨");

        int i = 0;
        for (String book:bookList) {
            String[] bookArr = book.split("\\.");
            System.out.println(++i+". "+bookArr[0]);
        }

        System.out.println();
        System.out.println("0. Go back to the main menu");
        System.out.println();
        System.out.print("Enter selection: ");

        int selection = Input(i, "Enter selection: ");
        System.out.println();
        if(selection == 0) {
            PrintMainMenu();
        } else {
            String book = bookList[selection-1];
            String[] bookArr = book.split("\\.");
            System.out.println();
            PrintBookQuery(book);
        }
    }

    private void PrintBookQuery(String book) {
        String[] bookArr = book.split("\\.");
        System.out.print(bookArt);
        System.out.println("~Library/Book/"+bookArr[0]);
        System.out.println("¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨");
        System.out.println("Status: "+bookArr[1]);
        if(bookArr[1].equals("available")) {
            System.out.println("Would you like to borrow the selected book?");
        } else {
            System.out.println("Would you like to return the selected book?");
        }
        System.out.println();
        System.out.println("1. Yes");
        System.out.println("0. No");
        System.out.println();
        System.out.print("Enter selection: ");
        int selection;
        if(bookArr[1].equals("available")) {
            selection = Input(1, "Would you like to borrow the selected book?");
        } else {
            selection = Input(1, "Would you like to return the selected book?");
        }

        if(selection==1) {
            System.out.println("Sorry, not implemented yet!");
        } else {
            PrintBooksList();
        }
    }

    private int Input(int limit, String query) {
        Scanner scan = new Scanner(System.in);
        int input = 99;
        try {
            input = scan.nextInt();
            scan.nextLine();

            if(!(input >= 0 && input <= limit)) {
                System.out.println("Invalid input.");
                System.out.print(query);
                input = Input(limit, query);
            }
        } catch (Exception e) {
            //e.printStackTrace();
            System.out.println("Invalid input.");
            System.out.print(query);
            input = Input(limit, query);
        }
        return input;
    }
}
