import java.io.*;
import java.net.*;

class TCPServer{
    public static void main(String argv[]) throws Exception{
        String clientSentence;
        String capitalizedSentence;
        //监听6789端口
        ServerSocket welcomeSocket = new ServerSocket(6789);
        while(true){
            //建立管道
            Socket connectionSocket = welcomeSocket.accept();
            System.out.println(connectionSocket);
            BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
            DataOutputStream outToclient = new DataOutputStream(connectionSocket.getOutputStream());
            clientSentence = inFromClient.readLine();
            capitalizedSentence = clientSentence.toUpperCase() + '\n';
            outToclient.writeBytes(capitalizedSentence);
        }
    }
}