import java.io.*;
import java.util.ArrayList;

public class ShelterManagement  {
    private final ArrayList<Animal> animals = new ArrayList<>();
    public ShelterManagement(){}

    //LoadFromFile() is used to read the existing animals from the "animals.txt" file
    public void loadFromFile() {
       File file = new File("animals.txt");
       try ( BufferedReader reader = new BufferedReader(new FileReader(file))){
           String id, name, ageStr, status, type;
           while (animals.size() < 30 && (id = reader.readLine()) != null) {
                name = reader.readLine();
                ageStr = reader.readLine();
                status = reader.readLine();
                type = reader.readLine();

                if (name == null || ageStr == null || status == null || type == null) break;
                int age = Integer.parseInt(ageStr.trim());

                if( type.trim().equalsIgnoreCase("Dog")) {
                    animals.add(new Dog(id.trim(), name.trim(), age, status.trim(), "Dog"));
                }else if( type.trim().equalsIgnoreCase("Cat")) {
                    animals.add(new Cat(id.trim(), name.trim(), age, status.trim(), "Cat"));
                }
           }System.out.println("Loaded " + animals.size() + " animal(s) from file.");
       } catch (Exception e) {
           System.out.println("Error reading file: " + e.getMessage());
       }
    }

    //SaveToFile() is used to write all the existing animals in the animals array list to a text file(animals.txt).
    public void saveToFIle(){
        try (PrintWriter writer = new PrintWriter(new FileWriter("animals.txt"))) {
            for (Animal a : animals) {
                writer.println(a.getAnimalId());
                writer.println(a.getName());
                writer.println(a.getAge());
                writer.println(a.getStatus());
                writer.println(a.getType());
            }
        }catch (IOException e){
            System.out.println("Error saving to file: " + e.getMessage());
        }
    }

    //Displays all animals from the animals array list
    public void displayAllAnimals(){
        if(animals.isEmpty()){
            System.out.println("No animals found");
            return;
        }
        for(int i = 0; i < animals.size(); i++){
            System.out.println((i + 1) + ".");
            animals.get(i).displayInfo();
        }
    }

    // Searches for the animal with the corresponding id
    public Animal searchAnimal(String id) {
        for (Animal a : animals) {
            if (a.getAnimalId().equalsIgnoreCase(id.trim())) {
                return a; // Returns the matching animal object if found
            }
        }
        return null; // Returns null if no matching ID exists in the list
    }

    //Displays the animal with the corresponding id
    public void findAndDisplayAnimal(String id) {
        Animal found = searchAnimal(id); // Using searchAnimal() method
        if (found != null) {
            System.out.println("\n--- Animal Found ---");
            found.displayInfo(); // Or whatever your display method is named in Animal class
            found.makeSound();
        } else {
            System.out.println("Error: An animal with ID '" + id + "' does not exist.");
        }
    }

    //Allows the user to add an animal so long as the shelter capacity doesn't excide 30
    public void addAnimal(Animal animal){
        //Checking that we don't add more than 30 animals
        if(animals.size() >= 30){
            System.out.println("Shelter has reached maximum capacity (30)");
            return;
        }
        //Checking if animal id already exists
        if(searchAnimal(animal.getAnimalId()) != null){
            System.out.println("Entered animal id already exists: " +  animal.getAnimalId());
            System.out.println("Animal not added.");
            return;
        }
        if(animal.getAge() < 0){
            System.out.println("Age cannot be less that 0");
        }
        //If all checks pass animals will be added
        animals.add(animal);
        System.out.println("Animal added successfully");
    }

    //Adopt an animal using its id
    public void adoptAnimal(String id){
        //Finding the animal
        Animal animal = searchAnimal(id);

        //Checking if animal exists
        if(animal == null){
            System.out.println("Animal with ID '" + id + "' does not exist.");
            return;
        }
        //Checking if the animal is already adopted
        if (animal.getStatus().equalsIgnoreCase("Adopted")) {
            System.out.println("Error: This animal has already been adopted.");
            return;
        }
        //Changing the status to adopted
        animal.setStatus("Adopted");
        System.out.println("Success: Animal " + animal.getName() + " (" + animal.getAnimalId() + ") is now marked as Adopted.");
    }

    //Display animals which have a status of "Available"
    public void displayAvailableAnimals() {
        boolean foundAny = false;
        System.out.println("---Animals available for adoption---");
        for (Animal a : animals) {
            if(a.getStatus().equalsIgnoreCase("Available")) {
                a.displayInfo();
                foundAny = true;
            }
        }
        if(!foundAny){
            System.out.println("No animals currently available for adoption");
        }
    }
}
