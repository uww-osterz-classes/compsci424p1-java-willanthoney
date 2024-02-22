/* COMPSCI 424 Program 1
 * Name: Will Anthoney
 */
package compsci424.p1.java;

import java.util.LinkedList;

class Version1 {
    private Version1PCB[] pcbs;

    public Version1(int n) {
        pcbs = new Version1PCB[n];
        for (int i = 0; i < n; i++) {
            pcbs[i] = new Version1PCB();
        }
    }

    public void create(int parentPid, int pid) {
        if (parentPid < 0 || parentPid >= pcbs.length) {
            System.out.println("Parent process does not exist.");
            return;
        }
        Version1PCB pcb = new Version1PCB();
        pcb.setParent(parentPid);
        pcbs[pid] = pcb;
        pcbs[parentPid].addChild(pid);
    }

    public void destroy(int targetPid) {
        if (targetPid < 0 || targetPid >= pcbs.length) {
            System.out.println("Process to destroy does not exist.");
            return;
        }
        destroyHelper(targetPid);
    }

    private void destroyHelper(int targetPid) {
        for (int child : pcbs[targetPid].getChildren()) {
            destroyHelper(child);
        }
        pcbs[targetPid] = null;
    }

    public void showProcessInfo() {
        for (int i = 0; i < pcbs.length; i++) {
            System.out.print("Process " + i + ": parent is " + pcbs[i].getParent());
            LinkedList<Integer> children = pcbs[i].getChildren();
            if (!children.isEmpty()) {
                System.out.print(" and children are ");
                for (int child : children) {
                    System.out.print(child + " ");
                }
            } else {
                System.out.print(" and has no children");
            }
            System.out.println();
        }
    }
}
