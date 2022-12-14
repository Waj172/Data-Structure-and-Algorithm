package love.aojie.链表.单链表;

/**
 * @author: JieGe
 * @time:
 * @function: 人物节点
 */
public class CharacterNode {
    public int no;
    public String name;
    // 指向下一个节点
    public CharacterNode next;

    public CharacterNode(){

    }
    public CharacterNode(int no, String name, CharacterNode next) {
        this.no = no;
        this.name = name;
        this.next = next;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
    }
}
