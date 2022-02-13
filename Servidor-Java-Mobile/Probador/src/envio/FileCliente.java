package envio;

import java.io.*;
import java.net.*;
public class FileCliente {
    public static void main(String[] args) throws Exception{
        byte b[] = new byte [20002];
        Socket socket = new Socket("192.168.1.68",8001);//Cambiar a la IP De Su Telefono Aqui Profesor!!!
        FileInputStream fr = new FileInputStream("C:\\enviarDatos\\enviarPC.csv");//Archivo a tomar informacion
        fr.read(b,0,b.length);
        OutputStream os = socket.getOutputStream();
        os.write(b,0,b.length);
        fr.close();
        os.close();
        socket.close();
    }
}
