import java.net.*;
import java.util.*;
import java.io.*;

public class NetworkClient {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        System.out.println("[\nCLIENT CONNECTION]");

        //define our variables that will be used in the switch statement below
        int portNumber = 9959, choice, numberLogin = 0, numberPassword = 0, usernameMessage = 0, count = 0, userData = 0;
        String login = "", tempMessage = "", tempUserInput = "", password = "", compareItem = "";
        ;
        boolean correctLogin = false;
        ArrayList<String> userLoggedIn = new ArrayList<>();

        Scanner scan = new Scanner(System.in);

        ArrayList<InterConnection> objects = null;

        Object object = null;
        ObjectInputStream objectInput = null;
        ServerSocket myServerSocket = null;
        Socket skt = null;

        myServerSocket = new ServerSocket(portNumber);
        skt = myServerSocket.accept();
        objectInput = new ObjectInputStream(skt.getInputStream());
        object = objectInput.readObject();

        //validate user input for options

        System.out.println("What would you like to do?\n1. Login\n2. Logout\n3. Send Message\n4. Turn off Server");
        choice = scan.nextInt();


        //message holder in format of username followed by message in a string
        ArrayList<InterConnection> messageContainer = new ArrayList<>();

        HashMap<String, String> messageContainerUser = new HashMap<>();

        while (choice == 1 || choice == 2 || choice == 3 || choice == 4 || choice > 4) {
            switch (choice) {
                //logs in
                case 1:

                    // System.out.println("[COUNT: ] " + count);
                    // System.out.println("[TEMP: ] " + tempUserInput);


                    //hashmap empty
                    if (userLoggedIn.size() < 1 && correctLogin) {
                        //check in map if the have messages
                        System.out.print("[NO-DATA IN HERE!]\n");
                    }

                    //first time in case - input data
                    if (!correctLogin) {
                        //gets user & password input
                        System.out.println();
                        System.out.println("Enter the [USERNAME]: ");
                        login = scan.next();
                        System.out.println("Enter the [PASSWORD]: ");
                        password = scan.next();
                        System.out.println();
                    }
                    //check the status of login to read mail
                    else {
                        if (correctLogin && count == 0) {
                            int mapSize = 0;
                            //check in map if the have messages
                            System.out.print("[ALREADY LOGGED IN]\n");

                            for (int i = 0; i < messageContainerUser.size(); i++) {
                                if (objects.get(i).getUsername().equals(userLoggedIn.get(0))) {
                                    mapSize++;
                                }
                            }
                            count = 0;
                            break;
                        }
                    }
                    //connection used to safely get object from network
                    //debugging code!
                    objects = (ArrayList<InterConnection>) object;

                    //debug test
                    for (int i = 0; i < objects.size(); i++) {

                        //user for testing
                        //System.out.println("NO-LOOP-USER]: " + messageContainerUser.get(objects.get(i).getUsername()));

                        //user and pass dont match
                        if (!(objects.get(i).getUsername().equals(login)) && !(objects.get(i).getPassword().equals(password))) {
                        }
                        //user match and pass dont match
                        else if ((objects.get(i).getUsername().equals(login)) && !(objects.get(i).getPassword().equals(password))) {
                            numberLogin = i;
                        }//user dont match and pass match
                        else if (!(objects.get(i).getUsername().equals(login)) && (objects.get(i).getPassword().equals(password))) {
                            numberPassword = i;
                        }
                        //if user and pass equal numberLogin & numberPassword gets converted to index
                        else if (objects.get(i).getUsername().equals(login) && objects.get(i).getPassword().equals(password)) {
                            //index value gets redefine to whatever is found there
                            numberLogin = i;
                            numberPassword = i;
                            count = 1;
                        }
                    }

                    //wrong username & correct password
                    if ((objects.get(numberPassword).getPassword().equals(password)) && !(objects.get(numberLogin).getUsername().equals(login))) {
                        //debug what is in [ARRAY]
                        System.out.println("[LIST USERNAME]: " + objects.get(numberLogin).getUsername());
                        System.out.println("[LIST PASSWORD]: " + objects.get(numberPassword).getPassword());
                        System.out.println("[INCORRECT LOGIN]\n");
                        count = 0;
                        //break;
                    }

                    //correct username & wrong password
                    else if ((objects.get(numberLogin).getUsername().equals(login)) && !(objects.get(numberPassword).getPassword().equals(password))) {
                        //debug what is in [ARRAY]
                        System.out.println("[LIST USERNAME]: " + objects.get(numberLogin).getUsername());
                        System.out.println("[LIST PASSWORD]: " + objects.get(numberPassword).getPassword());
                        System.out.println("[INCORRECT PASSWORD]\n");
                        count = 0;
                        //break;
                    }

                    //wrong username & wrong password
                    else if (!(objects.get(numberLogin).getUsername().equals(login)) && !(objects.get(numberPassword).getPassword().equals(password))) {
                        //debug what is in [ARRAY]
                        System.out.println("[LIST USERNAME]: " + objects.get(numberLogin).getUsername());
                        System.out.println("[LIST PASSWORD]: " + objects.get(numberPassword).getPassword());
                        System.out.println("[INCORRECT PASSWORD] & [INCORRECT LOGIN]\n");
                        count = 0;
                        //break;
                    }

                    //correct password, correct password
                    else if ((objects.get(numberLogin).getUsername().equals(login)) && (objects.get(numberPassword).getPassword().equals(password))) {
                        //debug what is in [ARRAY]
                        //System.out.println("[LIST USERNAME]: " + objects.get(numberLogin).getUsername());
                        //System.out.println("[LIST PASSWORD]: " + objects.get(numberPassword).getPassword());

                        correctLogin = true;
                        System.out.print("[LOGGED IN!]\n");
                        count = 1;
                        userLoggedIn.add(login);
                        //break;

                        //check if user is in
                        if (userLoggedIn.size() > 0 || userLoggedIn.get(0).equals(tempUserInput)) {
                            int mapSize = 0;
                            for (int i = 0; i < messageContainerUser.size(); i++) {
                                if (objects.get(i).getUsername().equals(userLoggedIn.get(0))) {
                                    mapSize++;
                                }
                            }//keep
                            System.out.print("[You have messages:!]\n");
                            for (int i = 0; i < messageContainer.size(); i++) {
                                if ( messageContainerUser.containsKey(tempUserInput) && userData == 1) {
                                    System.out.println("BY KEY]: " + messageContainerUser.get(objects.get(i).getUsername()));
                                    System.out.println("BY KEY - TEM]: " + messageContainerUser.get(tempUserInput));
                                    System.out.println("BY KEY - LOG]: " + messageContainerUser.get(login));
                                    //prints all messages in list
                                    //System.out.println("[MESSAGE] " + (i+1) + ": " + messageContainer.get(i).getMessageData());

                                } else {
                                    System.out.print("[You have 0 messages:]\n");
                                    break;
                                }
                            }
                            tempUserInput = "";
                            count = 0;

                        }
                    }
                    break;

                case 2:

                    //[USER]: Login correct, can log out
                    if (correctLogin) {
                        System.out.println("[LOGGING OUT...]");
                        correctLogin = false;
                    }
                    //[USER]: Login not correct, can't log out
                    else {
                        System.out.println("Sorry, you are not logged in!");
                    }
                    break;

                case 3:
                    //checks if logged in because if you aren't you won't be able to send a message
                    if (!correctLogin) {
                        System.out.println("Message can't be sent, [LOG IN]!");
                        break;
                    }

                    //asks for a user and takes down the message for said user
                    System.out.println("who do you want to message?");
                    tempUserInput = scan.next();

                    //checks if user exists in system

                    for (int i = 0; i < objects.size(); i++) {

                        if (!(objects.get(i).getUsername().equals(tempUserInput))) {
                        }
                        //if user and pass equal numberLogin & numberPassword gets converted to index
                        else if (objects.get(i).getUsername().equals(tempUserInput)) {
                            usernameMessage = i;
                        }

                    }
                    //debug code
                    //System.out.println("[TEMP - INDEX LOCATION(USERNAME)] " + usernameMessage);
                    //compareItem = objects.get(usernameMessage).getUsername();

                    //if user exists, saves the message for them into our messageContainer
                    if (objects.get(usernameMessage).getUsername().equals(tempUserInput)) {
                        //debug what is in [ARRAY]
                        //System.out.println("[LIST USERNAME]: " + objects.get(usernameMessage).getUsername());
                        // System.out.println("[LIST PASSWORD]: " + objects.get(usernameMessage).getPassword());

                        System.out.println("Please enter the message you wish to send.");
                        scan.useDelimiter("\n"); // use LF as the delimiter
                        tempMessage = scan.next();

                        messageContainer.add(new InterConnection(tempMessage));
                        //our messageContainer is what is holding everything

                        // Traversing through the map // usernameMessage = i;
                        messageContainerUser.put(tempUserInput, tempMessage);

                        userData = 1;


                        // System.out.println("NO-LOOP-USER]: " + messageContainerUser.get(tempUserInput));
                        for (int i = 0; i < messageContainer.size(); i++) {
                            System.out.println("Reading Data: " + messageContainer.get(i).getMessageData());
                        }
                    }
                    //not is database
                    else if (!(objects.get(numberLogin).getUsername().equals(tempUserInput))) {
                        System.out.println("Person does not exist in database!");
                        //exit case, ask for options
                        break;
                        //tempUserInput = scan.next();
                    }
                    break;

                case 4:
                    //end program
                    System.out.println("Shutting down...\nGoodbye!\n");
                    System.exit(0);
                    break;
            }
            //menu & choice input
            //validate user input for options
            System.out.println("What would you like to do?\n1. Login\n2. Logout\n3. Send Message\n4. Turn off Server");
            choice = scan.nextInt();
        }
    }
}
