/* COMPSCI 424 Program 1

 * Name: Will Anthoney
 * 
 * This is a template. Program1.java *must* contain the main class
 * for this program. Otherwise, feel free to edit these files, even
 * these pre-written comments or my provided code. You can add any
 * classes, methods, and data structures that you need to solve the
 * problem and display your solution in the correct format.
 * 
 */

package compsci424.p1.java;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Program1 {

    public static void main(String[] args) {
    	
    	 Scanner sc = new Scanner(System.in);
         List<String> actions = new LinkedList<>();
         // prompt and get user inputs in list
         System.out.println("Enter commands in the format 'create N', 'destroy N', or 'end':");
         
         while (true) {
        	 String command = sc.nextLine();
        	 if (command.equals("end")) {
        		 break;
        	 }
        	 actions.add(command);
         }
         
         Version1 v1 = new Version1(16);
         Version2 v2 = new Version2(16);
         //Version1, split commands based on input type create or destroy
         System.out.println("Version 1 Process Information:");
         for (String command : actions) {
             String[] parts = command.split(" ");
             int pid = Integer.parseInt(parts[1]);
             int result = -1;
             if (parts[0].equals("create")) {
                 result = v1.create(pid);
             } else if (parts[0].equals("destroy")) {
                 result = v1.destroy(pid);
             }
             if (result == 0) {
                 v1.showProcessInfo();
             }
         }
         
         //Version2
         System.out.println("\nVersion 2 Process Information:");
         for (String command : actions) {
             String[] parts = command.split(" ");
             int pid = Integer.parseInt(parts[1]);
             int result = -1;
             if (parts[0].equals("create")) {
                 result = v2.create(pid);
             } else if (parts[0].equals("destroy")) {
                 result = v2.destroy(pid);
             }
             if (result == 0) {
                 v2.showProcessInfo();
             }
         }
         
         long startTimeV1 = System.currentTimeMillis();
         for (int i = 0; i < 200; i++) {
             for (String command : actions) {
                 String[] parts = command.split(" ");
                 int pid = Integer.parseInt(parts[1]);
                 if (parts[0].equals("create")) {
                     v1.create(pid);
                 } else if (parts[0].equals("destroy")) {
                     v1.destroy(pid);
                 }
             }
         }
         long endTimeV1 = System.currentTimeMillis();
         long durationV1 = (endTimeV1 - startTimeV1) / 1000; // Convert to milliseconds
         System.out.println("\nVersion 1 Running Time: " + durationV1 + " milliseconds");

         long startTimeV2 = System.currentTimeMillis();
         for (int i = 0; i < 200; i++) {
             for (String command : actions) {
                 String[] parts = command.split(" ");
                 int pid = Integer.parseInt(parts[1]);
                 if (parts[0].equals("create")) {
                     v2.create(pid);
                 } else if (parts[0].equals("destroy")) {
                     v2.destroy(pid);
                 }
             }
         }
         long endTimeV2 = System.currentTimeMillis();
         long durationV2 = (endTimeV2 - startTimeV2) / 1000; // Convert to milliseconds
         System.out.println("Version 2 Running Time: " + durationV2 + " milliseconds");
         
         sc.close();
    }
}
/* Issues with attempting last  user input " destroy a hierarchy of processes". Stack overflow of recursive method being called too much. */
