public class Cat extends Animal{
    public Cat(String animalId, String name, int age, String status, String type) {
        super(animalId, name, age, status, type);
    }

    @Override
    public void makeSound() {
        System.out.println("Meow...");
    }
}
