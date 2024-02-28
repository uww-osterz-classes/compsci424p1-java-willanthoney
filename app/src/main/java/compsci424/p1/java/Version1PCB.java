/* COMPSCI 424 Program 1
 * Name: Will Anthoney
 */
package compsci424.p1.java;

import java.util.LinkedList;

public class Version1PCB {

	int parent;
    LinkedList<Integer> children;

    public Version1PCB(int parent) {
        this.parent = parent;
        this.children = new LinkedList<>();
	
    }
}
