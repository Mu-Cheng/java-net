import java.io.*;
import java.net.*;
class TCPClient{
    public static void main(String argv[]) throws Exception{
        String sentence;
        String modifiedSentence;
      
        while(true){
            BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
            //创建socket对象
            Socket clientSocket = new Socket("118.89.162.148",6789);
            //为进程提供到套接字的输出
            DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
            //接受套接字的输入
            BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            sentence = inFromUser.readLine();
            //发送至TCP管道
            outToServer.writeBytes(sentence + '\n');
            //读取结果
            modifiedSentence = inFromServer.readLine();
            System.out.println("From server: " + modifiedSentence);
            clientSocket.close();
        }
    }
}