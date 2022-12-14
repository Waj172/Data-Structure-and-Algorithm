package love.aojie.链表.单链表;

import org.junit.Test;

import java.util.Stack;

/**
 * @author: JieGe
 * @time:
 * @function: 面试题
 *
 * 单链表的常见面试题有如下:
 *  1、求单链表中有效节点的个数
 *  2、查找单链表中的倒数第k个结点 【新浪面试题】
 *  3、单链表的反转【腾讯面试题，有点难度】
 *  4、从尾到头打印单链表 【百度，要求方式1：反向遍历 。 方式2：Stack栈】
 *  5、合并两个有序的单链表，合并之后的链表依然有序【课后练习.】
 */
public class Interview {


    /**
     * 1、求单链表中有效节点的个数
     */
    @Test
    public void testValidNodeCount(){
        SingleLinkedList linkedList = new SingleLinkedList();

        int validNodeCount = this.validNodeCount(linkedList);

        System.out.println("有效节点的个数为：" + validNodeCount);
    }

    /**
     * 2、查找单链表中的倒数第k个结点 【新浪面试题】
     */
    @Test
    public void testLastIndexNode(){
        SingleLinkedList linkedList = new SingleLinkedList();

        CharacterNode lastIndexNode = this.LastIndexNode(linkedList ,1);

        if (lastIndexNode == null){
            System.out.println("查找失败，该节点不存在");
        }else {
            System.out.println("查到节点为：" + lastIndexNode);
        }
    }

    /**
     * 3、单链表的反转【腾讯面试题，有点难度】
     */
    @Test
    public void testReverseLinkedList(){
        SingleLinkedList linkedList = new SingleLinkedList();

        linkedList.showLinkedList();

        SingleLinkedList reverse = this.reverse(linkedList);

        System.out.println("反转后的链表为：");

        reverse.showLinkedList();

    }

    /**
     * 4、从尾到头打印单链表 【百度，要求方式1：反向遍历 。 方式2：Stack栈】
     */
    @Test
    public void testReverseShowLinkedList(){
        SingleLinkedList linkedList = new SingleLinkedList();

        ReverseShowLinkedList(linkedList);
    }

    public int validNodeCount(SingleLinkedList linkedList){
        // 定义标志
        int count = 0;

        // 固定头结点
        CharacterNode temp = linkedList.head;

        while (true){
            // 结束条件
            if (temp.next == null){
                break;
            }
            // 没有结束就循环，
            temp = temp.next;
            count++;
        }
        return count;
    }

    public CharacterNode LastIndexNode(SingleLinkedList linkedList ,int index){
        CharacterNode node = null;

        // 固定头结点
        CharacterNode temp = linkedList.head;

        while (true){
            // 需要找到节点的前一个
            int frontIndex = validNodeCount(linkedList) - index;

            if (frontIndex < 0){
                break;
            }

            if (temp.next == null){
                break;
            }

            // 找到查询节点的前一个节点
            if (temp.no == frontIndex){
                node = temp.next;
                break;
            }

            temp = temp.next;
        }
        return node;
    }

    /**
     *  自己写的翻转，耦合性太强（需要使用自定义的链表方法）
     * @return
     */
    public SingleLinkedList reverseLinkedList(SingleLinkedList linkedList){
        SingleLinkedList reverseLinkedList = new SingleLinkedList();

        reverseLinkedList.head = new CharacterNode(0,null,null);

        CharacterNode temp = reverseLinkedList.head;

        for (int i = 1; i <= validNodeCount(linkedList); i++) {
            // 如果链表只有一个节点，直接返回原来的节点
            if (validNodeCount(linkedList) == 1){
                return linkedList;
            }

            temp.next = LastIndexNode(linkedList,i);
            temp = temp.next;
            // 把最后一个节点的next置为null，为了打印链表时考虑，
            // 因为展示链表的结束条件是最后一个next域为null
            if (i == 6){
                temp.next = null;
            }
        }
        return reverseLinkedList;
    }

    /**
     *  老师讲的
     * @return
     */
    public SingleLinkedList reverse(SingleLinkedList linkedList){

        CharacterNode reverseHead = new CharacterNode(0,null,null);

        CharacterNode temp = linkedList.head.next;
        CharacterNode next = null;

        if (temp == null){
            return linkedList;
        }

        while (true){
            // 判断结束条件
            if (temp == null){
                break;
            }
            // 保存当前节点的下一个节点
            // 因为我们需要把当前节点的next指向翻转头结点的next，所以，此时原来的链表就断了
            // 只有先用一个临时的保存当前节点的下一个节点
            next = temp.next;

            // 把当前节点，指向翻转头结点的下一个节点
            temp.next = reverseHead.next;

            // 连接翻转链表
            reverseHead.next = temp;

            // 此时把改变的temp在指向原来的下一个节点，然后拿到下一个节点
            temp = next;
        }
        // 将原来链表的头结点的next连接翻转后的头结点的next域
        linkedList.head.next = reverseHead.next;

        // 返回头结点
        return linkedList;
    }


    public void ReverseShowLinkedList(SingleLinkedList linkedList){

        CharacterNode temp = linkedList.head.next;
        // 判断链表是否为空
        if (temp == null){
            return ;
        }

        Stack<CharacterNode> characterNodes = new Stack<>();

        while (temp != null){
            characterNodes.push(temp);

            // 节点后移
            temp = temp.next;
        }

        while (characterNodes.size() > 0){
            System.out.println(characterNodes.pop());
        }
    }

}
