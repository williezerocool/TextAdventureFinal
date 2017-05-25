/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package textadventurefinal;

import java.util.Scanner;

/**
 *
 * @author kendrabooker
 */
class Room {

    int roomNumber;
    String roomName;
    String description;
    int numExits;
    String[] exits = new String[10];
    int[] destinations = new int[10];
    
}

public class TextAdventureFinal {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Scanner keyboard = new Scanner(System.in);
        
        Room[] rooms = loadRoomsFromFile("text-adventure-room.txt");
        
        int currentRoom = 0;
        String ans;
        
      /*  While( currentRoom >= 0 ){
        
            Room cur = rooms[currentRoom];
            System.out.print(cur.description);
            System.out.print("> ");
            ans = keyboard.nextLine();
            
            boolean found = false;
            for(int i=0; i<cur.numExits; i++){
            
                if(cur.exits[i].equals(ans)){
                
                    currentRoom = cur.destinations[i];
                    found = true;
                }
            }
            if(! found)
                System.out.println("Sorry, I don't understand. ");
        }*/
    }
    
    public static Room[] loadRoomsFromFile(String filename) {
    
        Scanner file = null;
        
        try{
        
            file = new Scanner(new java.io.File(filename));
        }
        catch (java.io.IOException e) {
        
            System.err.println("Can't open'" + filename + "' for reading. ");
            System.exit(1);
        }
        
        int numRooms = file.nextInt();
        Room[] rooms = new Room[numRooms];
        
        int roomNum = 0;
        while(file.hasNext()) {
        
            Room r = getRoom(file);
            
            if(r.roomNumber != roomNum){
            
                System.err.print("Just read room # " + r.roomNumber);
                System.err.println(", but " + roomNum + " was expected. ");
                System.exit(2);
            }
            rooms[roomNum] = r;
            roomNum++;
        }
        file.close();
        
        return rooms;
    }
    
    public static void showAllRooms(Room[] rooms){
    
        for(Room r : rooms){
        
            String exitString = "";
            for(int i=0; i<r.numExits; i++)
                exitString += "\t" + r.exits[i] + " (" + r.destinations[i] + ")";
            System.out.println(r.roomNumber + ") " + r.roomName);
            System.out.println(exitString);
        }
    }
    
    public static Room getRoom(Scanner f){
    
        if(! f.hasNext())
            return null;
        
        Room r = new Room();
        String line;
        
        r.roomNumber = f.nextInt();
        f.nextLine();
        
        r.roomName = f.nextLine();
        
        r.description = "";
        
        while(true){
        
            line = f.nextLine();
            if(line.equals("%%"))
                break;
            r.description += line + "\n";
        }
        
        int i = 0;
        while(true){
        
            line = f.nextLine();
            if(line.equals("%%"))
                break;
            String[] parts = line.split(":");
            r.exits[i] = parts[0];
            r.destinations[i] = Integer.parseInt(parts[1]);
            i++;
        }
        r.numExits = i;
        
        return r;
    }
}
