/* COMPSCI 424 Program 1
 * Name: Will Anthoney
 */
package compsci424.p1.java;

class Version2PCB {
    private int parent;
    private int firstChild;
    private int youngerSibling;
    private int olderSibling;

    public Version2PCB() {
        this.parent = -1;
        this.firstChild = -1;
        this.youngerSibling = -1;
        this.olderSibling = -1;
    }

    public int getParent() {
        return parent;
    }

    public void setParent(int parent) {
        this.parent = parent;
    }

    public int getFirstChild() {
        return firstChild;
    }

    public void setFirstChild(int firstChild) {
        this.firstChild = firstChild;
    }

    public int getYoungerSibling() {
        return youngerSibling;
    }

    public void setYoungerSibling(int youngerSibling) {
        this.youngerSibling = youngerSibling;
    }

    public int getOlderSibling() {
        return olderSibling;
    }

    public void setOlderSibling(int olderSibling) {
        this.olderSibling = olderSibling;
    }
}
