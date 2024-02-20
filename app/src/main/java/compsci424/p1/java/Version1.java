/* COMPSCI 424 Program 1
 * Name: Will Anthoney
 */
package compsci424.p1.java;

/** 
 * Implements the process creation hierarchy for Version 1, which uses
 * linked lists.
 * 
 * This is a template. Program1.java *must* contain the main class
 * for this program. Otherwise, feel free to edit these files, even
 * these pre-written comments or my provided code. You can add any
 * classes, methods, and data structures that you need to solve the
 * problem and display your solution in the correct format.
 */
import java.util.LinkedList;

class Version1{
    // Declare any class/instance variables that you need here.
	private Version1PCB[] pcbArray;
    /**
     * Default constructor. Use this to allocate (if needed) and
     * initialize the PCB array, create the PCB for process 0, and do
     * any other initialization that is needed. 
     */
    public Version1() {	
    	pcbArray = new Version1PCB[16];
        pcbArray[0] = new Version1PCB(-1);
    }

    /**
     * Creates a new child process of the process with ID parentPid. 
     * @param parentPid the PID of the new process's parent
     * @return 0 if successful, not 0 if unsuccessful
     */
    public int create(int parentPid) {
        // If parentPid is not in the process hierarchy, do nothing; 
        // your code may return an error code or message in this case,
        // but it should not halt

        // Assuming you've found the PCB for parentPid in the PCB array:
        // 1. Allocate and initialize a free PCB object from the array
        //    of PCB objects
    	
    	if (parentPid < 0 || parentPid >= pcbArray.length || pcbArray[parentPid] == null) {
            return -1;
        }

        int newPid = findNextAvailablePid();
        pcbArray[newPid] = new Version1PCB(parentPid);
        if (pcbArray[parentPid].children == null) {
            pcbArray[parentPid].children = new LinkedList<>();
        }
        pcbArray[parentPid].children.add(newPid);

        // 2. Insert the newly allocated PCB object into parentPid's
        //    list of children

        // You can decide what the return value(s), if any, should be.
        // If you change the return type/value(s), update the Javadoc.
        return 0; // often means "success" or "terminated normally"
    }

    /**
     * Recursively destroys the process with ID parentPid and all of
     * its descendant processes (child, grandchild, etc.).
     * @param targetPid the PID of the process to be destroyed
     * @return 0 if successful, not 0 if unsuccessful
     */
    public int destroy (int targetPid) {
         // If targetPid is not in the process hierarchy, do nothing; 
         // your code may return an error code or message in this case,
         // but it should not halt

         // Assuming you've found the PCB for targetPid in the PCB array:
         // 1. Recursively destroy all descendants of targetPid, if it
         //    has any, and mark their PCBs as "free" in the PCB array 
         //    (i.e., deallocate them)

         // 2. Remove targetPid from its parent's list of children

         // 3. Deallocate targetPid's PCB and mark its PCB array entry
         //    as "free"
    	
    	destroyHelper(targetPid);

         // You can decide what the return value(s), if any, should be.
         // If you change the return type/value(s), update the Javadoc.
        return 0; // often means "success" or "terminated normally"
    }

    /**
     * Traverse the process creation hierarchy graph, printing
     * information about each process as you go. See Canvas for the
     * *required* output format. 
     *         
     * You can directly use "System.out.println" statements (or
     * similar) to send the required output to stdout, or you can
     * change the return type of this function to return the text to
     * the main program for printing. It's your choice. 
     */
    public void showProcessInfo() {

    	for (int i = 0; i < pcbArray.length; i++) {
            if (pcbArray[i] != null) {
                System.out.print("Process " + i + ": parent is " + pcbArray[i].parent);
                if (pcbArray[i].children != null && !pcbArray[i].children.isEmpty()) {
                    System.out.print(" and children are ");
                    for (int childPid : pcbArray[i].children) {
                        System.out.print(childPid + " ");
                    }
                } else {
                    System.out.print(" and has no children");
                }
                System.out.println();
            }
        }
        System.out.println();
    }

    /* If you need or want more methods, feel free to add them. */
    
    private void destroyHelper(int pid) {
        if (pcbArray[pid] == null) {
            return;
        }
        
        if (pcbArray[pid].children != null) {
            for (int childPid : pcbArray[pid].children) {
                destroyHelper(childPid);
            }
        }
        pcbArray[pid] = null;
    }
    
    private int findNextAvailablePid() {
        for (int i = 0; i < pcbArray.length; i++) {
            if (pcbArray[i] == null) {
                return i;
            }
        }
        return -1;
    } 
}
