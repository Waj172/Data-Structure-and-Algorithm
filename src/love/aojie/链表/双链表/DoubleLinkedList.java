package love.aojie.链表.双链表;

import org.junit.Test;

/**
 * @author: JieGe
 * @time:
 * @function:
 */
public class DoubleLinkedList {

    public CharacterNode head = new CharacterNode(0,"head",null,null);

    {
        CharacterNode ls1 = new CharacterNode(1, "林三");
        CharacterNode xqx2 = new CharacterNode(2, "肖青璇");
        CharacterNode xyr3 = new CharacterNode(3, "萧玉若");
        CharacterNode xys4 = new CharacterNode(4, "萧玉霜");
        CharacterNode qxe5 = new CharacterNode(5, "秦仙儿");
        CharacterNode dqq6 = new CharacterNode(6, "测试");

        addNodeByOrder(ls1);
        addNodeByOrder(xqx2);
        addNodeByOrder(xyr3);
        addNodeByOrder(xys4);
        addNodeByOrder(qxe5);
        addNodeByOrder(dqq6);
    }
    /**
     *  测试双链表按顺序添加
     */
    @Test
    public void testAddNodeByOrder(){
        showLinkedList();
    }

    /**
     * 测试双链表的修改
     */
    @Test
    public void testUpdate(){
        CharacterNode dqq6 = new CharacterNode(6, "董巧巧");

        update(dqq6);

        showLinkedList();
    }

    /**
     *  测试双链表的删除
     */
    @Test
    public void testDelete(){
        delete(3);

        showLinkedList();
    }
    // 查看链表元素
    public void showLinkedList(){
        // 先判断链表是否为空
        if (head.next == null){
            System.out.println("链表为空");
            return;
        }
        //头节点只是辅助，所以要从头结点开始
        CharacterNode temp = head;

        // 使用循环打印出链表
        while (true){
            // 先做结束条件判断
            if (temp.next == null){
                break;
            }
            // 执行下一个节点
            temp = temp.next;
            // 循环一次打印一个节点
            System.out.println(temp);
        }
    }

    // 按顺序添加下一个节点
    public void addNodeByOrder(CharacterNode node){
        // 定义一个标志，判断是否找到添加节点的位置
        boolean flag = false;

        // 老规矩，头结点不能动，先用临时变量代替
        CharacterNode temp = head;

        // 接着循环找出需要添加节点的位置
        while (true){
            // 先判断结束条件(可能添加的节点就处在链表的尾部)
            if (temp.next == null){
                break;
            }
            // 寻找节点插入位置
            if (temp.next.no > node.no){
                // 找到了添加节点的位置
                // 添加的节点编号小于当前节点的下一个编号，
                // 此时就把添加的节点放到当前节点的后面,
                // 此时结束循环，在外面做判断
                break;
            }
            // 当前节点与添加的节点编号相同，表示添加的节点编号已经存在
            if (temp.no == node.no){
                flag = true;
            }
            // 没找到节点后移
            temp = temp.next;
        }
        // 循环结束后，可以判断是否找到添加节点的位置
        if (flag){
            System.out.println("当前节点已存在，添加失败！");
        }else {
            // 如果找到添加节点的位置，
            // 把添加节点的next域指向当前节点的next域
            node.next = temp.next;
            // 把当前节点的next域指向添加节点的next域
            temp.next = node;
            // 将添加节点的pre指向temp
            node.pre = temp;
        }
    }

    // 修改节点信息
    public void update(CharacterNode newCharacterNode){
        // 判断找到修改节点的编号
        boolean flag = false;

        // 头结点不能动，依旧使用辅助节点
        CharacterNode temp = head;

        // 首先判断链表是否有节点
        if (temp.next == null){
            System.out.println("链表为空，删除失败，请先添加数据");
            return;
        }

        // 遍历链表，找到需要修改节点的编号
        while (true){
            // 判断循环结束条件
            if (temp.next == null){
                break;
            }
            // 找到节点信息
            if (temp.next.no == newCharacterNode.no){
                flag = true;
                break;
            }
            // 没找到节点，就后移一位
            temp = temp.next;
        }
        // 判断是否找到节点信息
        if (flag){
            temp.next.name = newCharacterNode.name;
        }else {
            System.out.println("未找到修改的节点，请先添加。");
        }

    }

    // 删除链表信息
    public void delete(int no){
        // 判断是否找到节点标志
        boolean flag = false;

        // 临时变量代替头结点
        CharacterNode temp = head;

        // 判断链表是否为空
        if (temp.next == null){
            System.out.println("链表为空，删除失败，请先添加数据");
        }

        // 循环遍历，找出删除链表的编号
        while (true){
            // 判断结束条件
            if (temp.next == null){
                break;
            }
            // 判断找到节点的条件
            if (temp.no == no){
                flag = true;
                break;
            }
            // 没找到，节点后移
            temp = temp.next;
        }
        // 判断是否找到节点信息
        if (flag){
            temp.next.pre = temp.pre;
            temp.pre.next = temp.next;
        }else {
            System.out.println("未找到删除的节点，请先添加");
        }
    }
}
