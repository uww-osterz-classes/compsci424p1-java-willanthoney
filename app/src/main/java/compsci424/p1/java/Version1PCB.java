/* COMPSCI 424 Program 1
 * Name: Will Anthoney
 */
package compsci424.p1.java;

import java.util.List;

/**
 * The process control block structure that is used to track a
 * process's parent and children (if any) in Version 1.
 */
public class Version1PCB {
    
	int parent;
    List<Integer> children;

    Version1PCB(int parent) {
        this.parent = parent;
    }

    public int getParent() {
        return parent;
    }

    public List<Integer> getChildren() {
        return children;
    }
}
