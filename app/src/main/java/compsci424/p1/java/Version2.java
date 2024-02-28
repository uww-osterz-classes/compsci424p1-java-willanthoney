/* COMPSCI 424 Program 1
 * Name: Will Anthoney
 */
package compsci424.p1.java;

public class Version2 {
    // Declare any class/instance variables that you need here.
	private Version2PCB[] pcbArray;

    public Version2(int n) {
    	pcbArray = new Version2PCB[n];
    	for (int i = 0; i < n; i++) {
    		pcbArray[i] = new Version2PCB(-1);
    	}
    }

    int create(int parentPid) {

    	int childPid = findFreePCB();
    	if (childPid != -1) {
    		pcbArray[childPid].parent = parentPid;
    		if( parentPid >= 0 && parentPid < pcbArray.length) {
    			int youngerSibling = pcbArray[parentPid].firstChild;
    			if(youngerSibling == -1) {
    				pcbArray[parentPid].firstChild = childPid;
    			}
    			else {
    				while (pcbArray[youngerSibling].youngerSibling != -1) {
    					youngerSibling = pcbArray[youngerSibling].youngerSibling;
    				}
    				pcbArray[youngerSibling].youngerSibling = childPid;
    				pcbArray[childPid].olderSibling = youngerSibling;
    			}
    		}
    	}
        return 0;
    }
    
    private int findFreePCB() {
    	for (int i = 0; i < pcbArray.length; i++) {
    		if (pcbArray[i].parent == -1) {
    			return i;
    		}
    	}
    	return -1; // No free PCB slot
    }

    int destroy (int targetPid) {

    	if(targetPid < 0 || targetPid >= pcbArray.length) {
    		System.out.println("");
    		return - 1; // failed
    	}
    	destroyRecursively(targetPid);
       return 0;
   }
    
    private void destroyRecursively(int targetPid) {
    	int olderSibling = pcbArray[targetPid].olderSibling;
    	if (olderSibling != -1) {
    		pcbArray[olderSibling].youngerSibling = pcbArray[targetPid].youngerSibling;
    	}
    	int parentPid = pcbArray[targetPid].parent;
    	if (parentPid != -1) {
    		if (pcbArray[parentPid].firstChild == targetPid) {
    			pcbArray[parentPid].firstChild = pcbArray[targetPid].youngerSibling;
    		}
    	}
    	pcbArray[targetPid].parent = -1;
    	pcbArray[targetPid].firstChild = -1;
    	pcbArray[targetPid].youngerSibling = -1;
    	pcbArray[targetPid].olderSibling = -1;
    }

    void showProcessInfo() {
        for (int i = 0; i < pcbArray.length; i++) {
            System.out.print("Process " + i + ": parent is " + pcbArray[i].parent);
            int child = pcbArray[i].firstChild;
            if (child != -1) {
                System.out.print(" and children are ");
                while (child != -1) {
                    System.out.print(child + " ");
                    child = pcbArray[child].youngerSibling;
                }
            } else {
                System.out.print(" and has no children");
            }
            System.out.println();
        }
    }
}
