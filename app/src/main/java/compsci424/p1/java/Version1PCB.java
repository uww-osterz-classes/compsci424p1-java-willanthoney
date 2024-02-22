/* COMPSCI 424 Program 1
 * Name: Will Anthoney
 */
package compsci424.p1.java;
import java.util.LinkedList;

class Version1PCB {
    private int parent;
    private LinkedList<Integer> children;

    public Version1PCB() {
        this.parent = -1;
        this.children = new LinkedList<>();
    }

    public int getParent() {
        return parent;
    }

    public void setParent(int parent) {
        this.parent = parent;
    }

    public LinkedList<Integer> getChildren() {
        return children;
    }

    public void addChild(int child) {
        this.children.add(child);
    }

    public void removeChild(int child) {
        this.children.remove((Integer) child);
    }
}
