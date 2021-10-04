import java.net.*;
import java.util.*;
import java.io.*;

public class NetworkServer {

    public static void main(String[] args) throws IOException {

        System.out.println("\n[SERVER CONNECTION]");
        //set port
        int portNumber = 9959;

        String username = "", password = "";

        try {
            //creates socket
            Socket socket = new Socket("127.0.0.1", portNumber);

            //creates arraylist of user and pass
            ArrayList<InterConnection> user = new ArrayList<>();

            InterConnection access = new InterConnection();

            //read file line by line
            FileInputStream finput = null;
            BufferedReader reader = null;

            //define file iostreams & buffer
            finput = new FileInputStream("shadow.txt");
            reader = new BufferedReader(new InputStreamReader(finput));


            //reading the file again using the fileName since the entered name is out of this try scope
            Scanner read = new Scanner(new File("shadow.txt"));

            do {
                //reads string in txt file and set its to the switch statmentt
                username = read.next();
                password = read.next();
                //printing the name and user of .txt
                System.out.println("[USERNAME]: " + username);
                System.out.println("[PASSWORD]: " + password);
                //adds user and pass to object
                user.add(new InterConnection(username, password));


            }
            while (read.hasNext());

            try {
                //creates an object reader
                ObjectOutputStream objectOutput = new ObjectOutputStream(socket.getOutputStream());
                //sends the entire object of array list thru the netwoek
                objectOutput.writeObject(user);


            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }


    }
}