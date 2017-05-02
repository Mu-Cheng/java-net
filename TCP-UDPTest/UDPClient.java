import java.io.*;
import java.net.*;

class UDPClient{
    public static void main(String argv[]) throws Exception{
        BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
        

        byte[] sendData = new byte[1024];
        byte[] receiveData = new byte[1024];
        while(true){
            DatagramSocket clientSocket = new DatagramSocket();
            String sentence = inFromUser.readLine();
            sendData = sentence.getBytes();
            DatagramPacket sentPacket = new DatagramPacket(sendData,sendData.length,InetAddress.getByName("118.89.162.148"),9876);
            clientSocket.send(sentPacket);
            DatagramPacket receivePacket = new DatagramPacket(receiveData,receiveData.length);
            clientSocket.receive(receivePacket);
            String modifiedSentence = new String(receivePacket.getData());
            System.out.println("From server: "+modifiedSentence);
            clientSocket.close();
        }
    }
}