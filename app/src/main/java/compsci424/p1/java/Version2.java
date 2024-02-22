/* COMPSCI 424 Program 1
 * Name: Will Anthoney
 */
package compsci424.p1.java;

class Version2 {
    private Version2PCB[] pcbs;

    public Version2(int n) {
        pcbs = new Version2PCB[n];
        for (int i = 0; i < n; i++) {
            pcbs[i] = new Version2PCB();
        }
    }

    public void create(int parentPid, int pid) {
        if (parentPid < 0 || parentPid >= pcbs.length) {
            System.out.println("Parent process does not exist.");
            return;
        }
        Version2PCB pcb = new Version2PCB();
        pcb.setParent(parentPid);
        pcbs[pid] = pcb;

        if (pcbs[parentPid].getFirstChild() == -1) {
            pcbs[parentPid].setFirstChild(pid);
        } else {
            int youngestChild = pcbs[parentPid].getFirstChild();
            while (pcbs[youngestChild].getYoungerSibling() != -1) {
                youngestChild = pcbs[youngestChild].getYoungerSibling();
            }
            pcbs[youngestChild].setYoungerSibling(pid);
            pcbs[pid].setOlderSibling(youngestChild);
        }
    }

    public void destroy(int targetPid) {
        if (targetPid < 0 || targetPid >= pcbs.length) {
            System.out.println("Process to destroy does not exist.");
            return;
        }
        destroyHelper(targetPid);
    }

    private void destroyHelper(int targetPid) {
        int olderSibling = pcbs[targetPid].getOlderSibling();
        int youngerSibling = pcbs[targetPid].getYoungerSibling();
        int parent = pcbs[targetPid].getParent();

        if (olderSibling != -1) {
            pcbs[olderSibling].setYoungerSibling(youngerSibling);
        } else {
            pcbs[parent].setFirstChild(youngerSibling);
        }

        if (youngerSibling != -1) {
            pcbs[youngerSibling].setOlderSibling(olderSibling);
        }

        for (int child = pcbs[targetPid].getFirstChild(); child != -1; ) {
            destroyHelper(child);
            int nextSibling = pcbs[child].getYoungerSibling();
            pcbs[child] = null;
            child = nextSibling;
        }
        pcbs[targetPid] = null;
    }

    public void showProcessInfo() {
        for (int i = 0; i < pcbs.length; i++) {
            System.out.print("Process " + i + ": parent is " + pcbs[i].getParent());
            int child = pcbs[i].getFirstChild();
            if (child != -1) {
                System.out.print(" and children are ");
                while (child != -1) {
                    System.out.print(child + " ");
                    child = pcbs[child].getYoungerSibling();
                }
            } else {
                System.out.print(" and has no children");
            }
            System.out.println();
        }
    }
}
