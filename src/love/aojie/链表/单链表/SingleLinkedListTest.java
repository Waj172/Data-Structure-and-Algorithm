package love.aojie.链表.单链表;

/**
 * @author: JieGe
 * @time:
 * @function: 单链表
 */
public class SingleLinkedListTest {
    public static void main(String[] args) {
        // 创建一个链表
        SingleLinkedList linkedList = new SingleLinkedList();

        // 修改前的链表信息
        linkedList.showLinkedList();

        // 修改节点信息
        CharacterNode newCharacterNode = new CharacterNode(5, "杰戈", null);
        linkedList.update(newCharacterNode);

        System.out.println("修改后链表");
        // 查看链表节点
        linkedList.showLinkedList();

        // 删除链表节点
        linkedList.delete(5);
        linkedList.delete(4);
        linkedList.delete(3);
        linkedList.delete(2);
        linkedList.delete(1);

        System.out.println("删除后链表");

        // 查看链表节点
        linkedList.showLinkedList();

    }
}
