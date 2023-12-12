public class Dog extends Animal{
    private String name;
    private int age;
    private String gender;
    private String breed;

    public Dog(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
    public void makeSound(){
        System.out.println("Gaw");
    }

    public String getGender() {
        return gender;
    }
    public void gameWithBall(){
        System.out.println("Dog gamed with ball");
    }
    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    @Override
    public String toString() {
        return "Dog with name " + name + ", age " + age + " years";
    }
}
