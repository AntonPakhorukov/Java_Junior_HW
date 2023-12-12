import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * Создайте абстрактный класс "Animal" с полями "name" и "age".
 * Реализуйте два класса-наследника от "Animal" (например, "Dog" и "Cat") с уникальными полями и методами.
 * Создайте массив объектов типа "Animal" и с использованием Reflection API выполните следующие действия:
 * Выведите на экран информацию о каждом объекте.
 * Вызовите метод "makeSound()" у каждого объекта, если такой метод присутствует.
 *
 * Дополнительная задача:
 *
 * Доработайте метод генерации запроса на удаление объекта из таблицы БД (DELETE FROM <Table> WHERE ID = '<id>')
 * В классе QueryBuilder который мы разработали на семинаре.
 */
public class Main {
    public static void main(String[] args) throws ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException, NoSuchFieldException {
        Class<?> cat = Class.forName("Cat");
        Constructor<?>[] constructorCat = cat.getConstructors();
        Object tristan = constructorCat[0].newInstance("Tristan", 5);
        System.out.println(tristan);

        Field color = tristan.getClass().getDeclaredField("color");
        color.setAccessible(true);
        color.set(tristan, "Black");

        Field genderCat = tristan.getClass().getDeclaredField("gender");
        genderCat.setAccessible(true);
        genderCat.set(tristan, "male");

        Method getFullInfo = tristan.getClass().getDeclaredMethod("getFullInfo");

        Class<?> dog = Class.forName("Dog");
        Constructor<?>[] constructorsDog = dog.getConstructors();
        Object geycha = constructorsDog[0].newInstance("Geycha", 12);
        System.out.println(geycha);

        Field breed = geycha.getClass().getDeclaredField("breed");
        breed.setAccessible(true);
        breed.set(geycha, "German Shepherd");

        Field genderDog = geycha.getClass().getDeclaredField("gender");
        genderDog.setAccessible(true);
        genderDog.set(geycha, "female");

        System.out.println();
        Animal[] array = new Animal[2];
        array[0] = (Animal) tristan;
        array[1] = (Animal) geycha;
        /**
         * Выведите на экран информацию о каждом объекте
         */
        for (Animal animal : array) {
            System.out.println(animal.getClass());
            if (animal.getClass().getSimpleName().equals("Cat")) {
                getFullInfo.invoke(tristan);
            } else {
                System.out.println(geycha);
            }
        }
        System.out.println();
        /**
         * Вызовите метод "makeSound()" у каждого объекта, если такой метод присутствует.
         */
        callMethod(array);
    }
    private static void callMethod(Animal[] array) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        for (Animal animal : array) {
            Method[] methodsAnimal = animal.getClass().getDeclaredMethods();
            for (Method method : methodsAnimal) {
                if (method.getName().equals("makeSound")){
                    Method mSound = animal.getClass().getDeclaredMethod("makeSound");
                    mSound.invoke(animal);
                }
            }
        }
    }
}
