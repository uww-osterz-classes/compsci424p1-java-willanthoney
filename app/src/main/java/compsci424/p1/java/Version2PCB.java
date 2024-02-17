/* COMPSCI 424 Program 1
 * Name: Will Anthoney
 */
package compsci424.p1.java;

/**
 * The process control block structure that is used to track a
 * process's parent, first child, younger sibling, and older sibling
 * (if they exist) in Version 2.
 */
public class Version2PCB {
	
	int parent;
    int firstChild;
    int youngerSibling;
    int olderSibling;

    Version2PCB(int parent) {
        this.parent = parent;
        this.firstChild = -1;
        this.youngerSibling = -1;
        this.olderSibling = -1;
    }

    public int getParent() {
        return parent;
    }

    public int getFirstChild() {
        return firstChild;
    }

    public int getYoungerSibling() {
        return youngerSibling;
    }

    public int getOlderSibling() {
        return olderSibling;
    }   
}
