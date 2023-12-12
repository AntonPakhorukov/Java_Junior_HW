public class Cat extends Animal{
    private String name;
    private int age;
    private String gender;
    private String color;

    public Cat(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
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
        System.out.println("Meow");
    }
    public void getFullInfo(){
        System.out.println("Its " + this.getClass().getSimpleName() + " with name: " +
                this.name + ", and his age: " + this.age + ".\nIt has color of " +
                this.getColor() + ", and his gender is " + this.getGender());
    }
    @Override
    public String toString() {
        return "Cat with name " + name + ", age " + age + " years";
    }
}
