import java.io.*;
import java.net.*;
class TCPClient{
    public static void main(String argv[]) throws Exception{
        String sentence;
        String modifiedSentence;
        BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
        //InetAddress remoteAddr1 = InetAddress.getByName("118.89.162.148");
        Socket clientSocket = new Socket(InetAddress.getByName("118.89.162.148"),6789);
        DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
        BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        sentence = inFromUser.readLine();
        outToServer.writeBytes(sentence + '\n');
        modifiedSentence = inFromServer.readLine();
        System.out.println("From server: " + modifiedSentence);
        clientSocket.close();
    }
}