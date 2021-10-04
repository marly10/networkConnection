import java.io.*;
import java.util.ArrayList;

/*
Serialization is the conversion of the state of an object into a byte stream; 
deserialization does the opposite. Stated differently, serialization is the conversion of a Java object into a static stream 
(sequence) of bytes which can then be saved to a database or transferred over a network.
*/

public class InterConnection implements Serializable {

    private String username = null;
    private String password = null;
    private String messageData = null;

    private ArrayList<String> data;

    //constructor

    public InterConnection() {
    }

    public InterConnection(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public InterConnection(String messageData) {
        this.messageData = messageData;
    }

    public void addUser(String username) {
        this.username = username;
    }

    public void addUPassword(String password) {
        this.password = password;
    }

    //return user
    public String getUsername() {
        return username;
    }

    //return pass
    public String getPassword() {
        return password;
    }

    //return pass
    public String getMessageData() {
        return messageData;
    }

    //print toString
    public String toStringMessage() {
        return messageData;
    }

    @Override
    public String toString() {
        return username + " " + password;
    }

    //equal method used for tap overridding the default equals
    public boolean equals(Object o) {
        if (!(o instanceof InterConnection)) {
            return false;
        } else {
            InterConnection InterConnection = (InterConnection) o;

            if (InterConnection.username.equals(username) && InterConnection.password.equals(password)) {

                return true;
            }
        }
        return false;
    }
}
