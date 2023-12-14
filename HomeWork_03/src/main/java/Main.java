import javax.crypto.spec.PSource;
import java.io.*;

/**
 * Разработайте класс Student с полями String name, int age, transient double GPA (средний балл).
 * Обеспечьте поддержку сериализации для этого класса.
 * Создайте объект класса Student и инициализируйте его данными.
 * Сериализуйте этот объект в файл.
 * Десериализуйте объект обратно в программу из файла.
 * Выведите все поля объекта, включая GPA, и обсудите,
 * почему значение GPA не было сохранено/восстановлено.
 */
public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Student student = new Student("Alex", 21, 5.0);
        System.out.println(student);
        serialObject(student, "Student.txt");
        System.out.println("\nОбъект \"student\" был сериализован.");
        Student student1 = (Student) deSerialObject("Student.txt");
        System.out.println("\nОбъект \"student\" был десериализован в объект \"student1\":");
        System.out.println(student1);
        System.out.println("\nТак как объект имеет поле \"GPA\" с модификатором \"transient\"," +
                " мы видим, что само поле присутствует,\nно его значение не было сериализовано," +
                " и соответственно, не было десериализовано.");
    }
    public static void serialObject(Object o, String file) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(o);
        objectOutputStream.close();
    }
    public static Object deSerialObject(String file) throws IOException, ClassNotFoundException {
        FileInputStream fileInputStream = new FileInputStream(file);
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        return objectInputStream.readObject();
    }
}
