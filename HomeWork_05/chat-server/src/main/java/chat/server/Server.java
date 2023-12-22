package chat.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    /**
     * Создаем ServerSocket и привязываем его через конструктор
     */
    private final ServerSocket serverSocket;

    public Server(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

    /**
     * Создаем метода, который будет запускать сервер
     */
    public void runServer(){
        try {
            while (!serverSocket.isClosed()) { // пока сокет не закрыт
                Socket socket = serverSocket.accept(); // метод accept() переводит сокет в режим ожидания подключения
                // как только новый клиент подключится, метод вернет ссылку на сокет нового клиента
                System.out.println("Подключился новый клиент!");
                // Как только клиент подключился, создаем клиентского менеджера
                ClientManager clientManager = new ClientManager(socket);
                // Так как клиентов может быть много, то для каждого клиентского менеджера нужен свой поток
                Thread thread = new Thread(clientManager);
                thread.start();
            }

        } catch (IOException e) {
            // Если что-то пойдет не так - вырубаем сервер
            closeServer();
        }
    }
    private void closeServer() {
        try {
            if (serverSocket != null) serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
