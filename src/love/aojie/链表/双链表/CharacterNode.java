package love.aojie.链表.双链表;

/**
 * @author: JieGe
 * @time:
 * @function:
 */
public class CharacterNode {
    public int no;
    public String name;
    // 指向下一个节点
    public CharacterNode next;
    // 指向前一个节点
    public CharacterNode pre;

    public CharacterNode() {
    }

    public CharacterNode(int no, String name) {
        this.no = no;
        this.name = name;
    }

    public CharacterNode(int no, String name, CharacterNode next, CharacterNode pre) {
        this.no = no;
        this.name = name;
        this.next = next;
        this.pre = pre;
    }

    @Override
    public String toString() {
        return "CharacterNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
    }
}
