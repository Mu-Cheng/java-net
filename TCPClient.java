import java.io.*;
import java.net.*;
class TCPClient{
    public static void main(String argv[]) throw Exception{
        String sentence;
        String modifiedSentence;
        BufferedReader inFromUser = new BufferedReader(new InputStreamReader(system.in));
        Socket clientSocket = new Socket("118.89.162.148",6789);
        DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
        BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        sentence = inFromServer.readLine();
        outToServer.writeBytes(sentence + '\n');
        modifiedSentence = inFromServer.readLine();
        System.out.println("From server: " + modifiedSentence);
        clientSocket.close();
    }
}