/* COMPSCI 424 Program 1
 * Name: Will Anthoney
 */
package compsci424.p1.java;

public class Version1 {

	private Version1PCB[] pcbArray;
	//create array and initialize every value as free
    public Version1(int n) {
    	pcbArray = new Version1PCB[n];
    	for (int i = 0; i < n; i++) {
    		pcbArray[i] = new Version1PCB(-1);
    	}
    }
    // create method, checks if there's a free PCB
    int create(int parentPid) {
    	
    	int childPid = findFreePCB();
    	if (childPid != -1) {
    		pcbArray[childPid].parent = parentPid;
    		if (parentPid >= 0 && parentPid < pcbArray.length) {
    			pcbArray[parentPid].children.add(childPid);
    		}
    		else {
    			System.out.println("No free PCB slots.");
    		}
    	}
        return 0;
    }
    // method to find free PCB within array
    private int findFreePCB() {
    	for (int i = 0; i < pcbArray.length; i++) {
    		if(pcbArray[i].parent == -1) {
    			return i;
    		}
    	}
    	return -1; // no free PCB
    }
    // destroy method
    int destroy (int targetPid) {

    	if(targetPid < 0 || targetPid >= pcbArray.length) {
    		System.out.println("Invalid process ID.");
    		return -1; // failed
    	}
    	destroyRecursively(targetPid);
        return 0;
    }
    // helper method for destroy method that recursively destroy child and then parent.
    private void destroyRecursively(int targetPid) {
    	for (int childPid : pcbArray[targetPid].children) {
            destroyRecursively(childPid);
        }
    	pcbArray[targetPid].parent= -1;
    	pcbArray[targetPid].children.clear();
    }
    // toString method to display results to user.
    void showProcessInfo() {
        for (int i = 0; i < pcbArray.length; i++) {
            System.out.print("Process " + i + ": parent is " + pcbArray[i].parent);
            if (!pcbArray[i].children.isEmpty()) {
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
}
