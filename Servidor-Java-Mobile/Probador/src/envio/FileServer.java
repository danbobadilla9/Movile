package envio;

import java.io.*;
import java.net.*;

public class FileServer {

    public static void main(String[] args) throws Exception {
        try {
            ServerSocket server = new ServerSocket(8000);
                Socket socket = server.accept();
                byte b[] = new byte[20002];
                InputStream is = socket.getInputStream();
                FileOutputStream fr = new FileOutputStream("C:\\enviarDatos\\entrada.csv");//Lugar donde se guardara el archivo
                is.read(b, 0, b.length);
                fr.write(b, 0, b.length);
            
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
