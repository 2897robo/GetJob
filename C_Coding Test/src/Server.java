import java.io.*;
import java.net.*;

public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(9000);
        System.out.println("서버 대기 중.......");

        Socket clientSocket = serverSocket.accept();
        System.out.println("클라이언트 연결 완료 @@@@@");

        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
        out.println("안녕 클라이언트 !!!!!!!");

        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        String msg = in.readLine();
        System.out.println("클라이언트로부터 받은 메세지 : " + msg);

        out.close();
        in.close();
        clientSocket.close();
        serverSocket.close();
    }
}
