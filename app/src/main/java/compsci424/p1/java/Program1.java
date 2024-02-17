/* COMPSCI 424 Program 1

 * Name: Will Anthoney
 * 
 * This is a template. Program1.java *must* contain the main class
 * for this program. Otherwise, feel free to edit these files, even
 * these pre-written comments or my provided code. You can add any
 * classes, methods, and data structures that you need to solve the
 * problem and display your solution in the correct format.
 */
package compsci424.p1.java;

import java.util.Scanner;
import java.util.LinkedList;
import java.util.List;
/**
 * Main class for this program. The required steps have been copied
 * into the main method as comments. Feel free to add more comments to
 * help you understand your code, or for any other reason. Also feel
 * free to edit this comment to be more helpful for you.
 */
public class Program1 {
    // Declare any class/instance variables that you need here.

    /**
     * @param args command-line arguments, which can be ignored
     */
    public static void main(String[] args) {
    	
    	Scanner sc = new Scanner(System.in);
    	List<String> commands = new LinkedList<>();

        // 1. Ask the user to enter commands of the form "create N",
        //    "destroy N", or "end", where N is an integer between 0 
        //    and 15.

        // 2. While the user has not typed "end", continue accepting
        //    commands. Add each command to a list of actions to take 
        //    while you run the simulation.
        // 3. When the user types "end" (or optionally any word that 
        //    is not "create" or "destroy"), stop accepting commands 
        //    and complete the following steps.
    	
    	System.out.println("Enter commands of the form 'create N', 'destroy N', or 'end' where N is an integer between 0 and 15:");
        String input;
        while (!(input = sc.nextLine()).equals("end")) {
            commands.add(input);
        }
        //
        // Hint: Steps 2 and 3 refer to the same loop. ;-)

        // 4. Create an object of the Version 1 class and an object of
        //    the Version 2 class.
        
        Version1 version1 = new Version1();
        Version2 version2 = new Version2();

        // 5. Run the command sequence once with the Version 1 object, 
        //    calling its showProcessTree method after each command to
        //    show the changes in the tree after each command.
        System.out.println("Version 1:");
        long startTimeV1 = System.currentTimeMillis();
        runCommands(version1, commands, true);
        long endTimeV1 = System.currentTimeMillis();
        long runTimeV1 = endTimeV1 - startTimeV1;
        System.out.println("Version 1 running time: " + runTimeV1 + " milliseconds");

        // 6. Repeat step 5, but with the Version 2 object.
        System.out.println("\nVersion 2:");
        long startTimeV2 = System.currentTimeMillis();
        runCommands(version2, commands, true);
        long endTimeV2 = System.currentTimeMillis();
        long runTimeV2 = endTimeV2 - startTimeV2;
        System.out.println("Version 2 running time: " + runTimeV2 + " milliseconds");

        // 7. Store the current system time in a variable

        // ... then run the command sequence 200 times with Version 1.

        // ... After this, store the new current system time in a
        //     second variable. Subtract the start time from the end 
        //     time to get the Version 1 running time, then display 
        //     the Version 1 running time.
        
        long startTimeLoopV1 = System.currentTimeMillis();
        for (int i = 0; i < 200; i++) {
            runCommands(version1, commands, false);
        }
        long endTimeLoopV1 = System.currentTimeMillis();
        long runTimeLoopV1 = endTimeLoopV1 - startTimeLoopV1;
        System.out.println("\nVersion 1 running time (200 loops): " + runTimeLoopV1 + " milliseconds");

        // 8. Repeat step 7, but with the Version 2 object.
        
        long startTimeLoopV2 = System.currentTimeMillis();
        for (int i = 0; i < 200; i++) {
            runCommands(version2, commands, false);
        }
        long endTimeLoopV2 = System.currentTimeMillis();
        long runTimeLoopV2 = endTimeLoopV2 - startTimeLoopV2;
        System.out.println("Version 2 running time (200 loops): " + runTimeLoopV2 + " milliseconds");     
    }
    
    private static void runCommands(ProcessManager processManager, List<String> commands, boolean showInfo) {
        for (String command : commands) {
            String[] parts = command.split(" ");
            String commandSplit = parts[0];
            int pid = Integer.parseInt(parts[1]);
            if (commandSplit.equals("create")) {
                processManager.create(pid);
                if (showInfo) {
                    processManager.showProcessInfo();
                }
            } else if (commandSplit.equals("destroy")) {
                processManager.destroy(pid);
                if (showInfo) {
                    processManager.showProcessInfo();
                }
            }
        }
    }
}
    
    interface ProcessManager {
        int create(int parentPid);
        int destroy(int targetPid);
        void showProcessInfo();
}
