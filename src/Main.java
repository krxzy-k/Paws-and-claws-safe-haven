import java.util.InputMismatchException;
import java.util.Scanner;
public class Main {
    public static void main(String[]Args){
        ShelterManagement shelter = new ShelterManagement();
        shelter.loadFromFile();
        Scanner scanner = new Scanner(System.in);

        System.out.println("--Welcome to Paws and claws safe haven shelter management--");

        while (true) {
            System.out.println("1. Read existing animals");
            System.out.println("2. Display all animals");
            System.out.println("3. Search for animal");
            System.out.println("4. Add new animal");
            System.out.println("5. Adopt an animal");
            System.out.println("6. Display available animals for adoption");
            System.out.println("7. Save animals");
            System.out.println("8. Exit");
            System.out.print("Enter your choice (1-8): ");

            if (!scanner.hasNextInt()) {
                System.out.println("Invalid input please enter a number between 1 and 8");
                scanner.next(); //Helps clear invalid input
                continue;
            }
            int option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    shelter.loadFromFile();
                    break;
                case 2:
                    shelter.displayAllAnimals();
                    break;
                case 3:
                    System.out.print("Enter animal id: ");
                    shelter.findAndDisplayAnimal(scanner.nextLine().trim());
                    break;
                case 4:
                    System.out.println("Entering a new animal");
                    System.out.print("Enter animal type(Dog/Cat): ");
                    String type = scanner.nextLine().trim();

                    System.out.print("Enter animal ID: ");
                    String id = scanner.nextLine().trim();

                    System.out.print("Enter animal name: ");
                    String name = scanner.nextLine().trim();

                    System.out.print("Enter animal age:");
                    int age = Integer.parseInt(scanner.nextLine().trim());

                    String status = "Available"; //Default status for a new animal is always Available

                    Animal newAnimal;
                    if (type.equalsIgnoreCase("Dog")) {
                        newAnimal = new Dog(id, name, age, status, "Dog");
                    } else if (type.equalsIgnoreCase("Cat")) {
                        newAnimal = new Cat(id, name, age, status, "Cat");
                    } else {
                        System.out.println("Invalid input must be Dog or Cat");
                        break; // Stops the program from adding a null animal
                    }
                    shelter.addAnimal(newAnimal);

                    break;
                case 5:
                    System.out.print("Enter animal id to adopt: ");
                    shelter.adoptAnimal(scanner.nextLine().trim());
                    break;
                case 6:
                    shelter.displayAvailableAnimals();
                    break;
                case 7:
                    shelter.saveToFIle();
                    System.out.println("Info saved successfully");
                    break;
                case 8:
                    shelter.saveToFIle();
                    System.out.println("Exiting application, Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice enter a number between 1 and 8");
            }
        }
    }
}
