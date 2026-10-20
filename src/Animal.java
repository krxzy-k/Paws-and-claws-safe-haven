public abstract class Animal {
    private String animalId;
    private String name;
    private int age;
    private String Status;
    private String type;

    public Animal(String animalId, String name, int age, String Status, String type) {
        this.animalId = animalId;
        this.name = name;
        this.age = age;
        this.Status = Status;
        this.type = type;
    }

//    Getter methods
    public String getAnimalId() {
        return animalId;
    }
    public String getName() {
        return name;
    }
    public int getAge() {
        return age;
    }
    public String getStatus() {
        return Status;
    }
    public String getType() {
        return type;
    }

//    Setter methods
    public void setAnimalId(String animalId){
        this.animalId = animalId;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public void setStatus(String Status) {
        this.Status = Status;
    }
    public void setType(String type) {
        this.type = type;
    }

    public void displayInfo(){
        System.out.println("ID: " + animalId + ", Name: " + name +
                ", Age: " + age + ", Status: " + Status +
                ", Type: " + type);
    }

    public abstract void makeSound();
}
