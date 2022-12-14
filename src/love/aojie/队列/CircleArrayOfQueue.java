package love.aojie.队列;

import java.util.Scanner;

/**
 * @author: JieGe
 * @time:
 * @function: 用环形数组模拟队列
 *
 * 总结： 老师讲的是预留一个空间，我自己想喜欢尝试一下，不预留的，结果搞了几个小时，最后无功而返
 *        不预留最大的问题就是无法判断 当前数组是满，还是空，此时会造成两个问题，一个就是添加元素
 *        时，可以无限添加，因为无法准确的判断数组是否满了，另一个就是可以无限的删除，如果不预留一个位置，
 *        这个问题无法解决，因为你没有一个固定的条件去判断，无论引进多少变量，怎么去记录，都没有一个
 *        固定的值可以表明是满还是空。
 *     经过一个夜的的思考，最终解决，哈哈，昨天估计是思想收到局限。
 */
public class CircleArrayOfQueue {
    public static void main(String[] args) {
        // 创建队列
        CircleArrayQueue arrayQueue = new CircleArrayQueue(3);
        // 创建用户交互平台

        // 接收用户的输入信息
        char key = ' ' ;
        // 用于最退出处理
        boolean loop = true;

        Scanner scanner = new Scanner(System.in);
        while (loop){
            System.out.println("a(add)   :     添加元素到队列中");
            System.out.println("r(remove):     移除头元素");
            System.out.println("h(head)  :     查看头元素");
            System.out.println("e(end)   :     查看尾元素");
            System.out.println("s(show)  :     查看队列中元素");
            System.out.println("q(quit)  :     退出");
            System.out.println();

            key = scanner.next().charAt(0);
            switch (key){
                case 'a':
                    try {
                        System.out.println("请输入添加的元素");
                        int i = scanner.nextInt();
                        arrayQueue.addQueue(i);
                        System.out.println("添加成功" + "\n");
                    }catch (Exception e){
                        System.out.println(e.getMessage() + "\n");
                    }
                    break;
                case 'r':
                    try {
                        int i = arrayQueue.removeOne();
                        System.out.println("移除成功: " + i + "\n");
                    }catch (Exception e){
                        System.out.println(e.getMessage() + "\n");
                    }
                    break;
                case 'h':
                    try {
                        int i = arrayQueue.headQueue();
                        System.out.println("获取的头元素为: " + i + "\n");
                    }catch (Exception e){
                        System.out.println(e.getMessage() + "\n");
                    }
                    break;
                case 'e':
                    try {
                        int i = arrayQueue.endQueue();
                        System.out.println("获取的尾元素为: " + i + "\n");
                    }catch (Exception e){
                        System.out.println(e.getMessage() + "\n");
                    }
                    break;
                case 's':
                    try {
                        System.out.print("获取的队列元素为： ");
                        arrayQueue.showQueue();
                    }catch (Exception e){
                        System.out.println(e.getMessage() + "\n");
                    }
                    break;
                case 'q':
                    scanner.close();
                    loop = false;
                    break;
            }
        }
        System.out.println("退出成功");


    }

}

// 使用环形数组模拟队列--编写一个 队列 类
class CircleArrayQueue {
    private int maxSize;  // 数组的最大容量
    private int front;    // 队列头
    private int rear;     // 队列尾
    private int[] arr;    // 该数组存放数据，模拟队列

    private int flag;

    // 用于初始化队列的前后指针,和最大容量
    public CircleArrayQueue(int maxSize){
        // 用户指定数组的最大容量
        this.maxSize = maxSize;
        // 初始化头指针，指向第一个元素位置
        front = 0;
        // 出事啊尾指针，让他指向第一个元素（由于开始没有元素，所以他和头指针指向位置相同）
        rear = 0;
        // 创建数组，表示队列
        arr = new int[maxSize];
    }

    /**
     *   判断数组是否为满
     * @return
     */
    public boolean isFull(){
        if (!isEmpty()){
            if (rear % maxSize == front){
                return true;
            }
        }
//       if ((rear + 1) % maxSize == front){
//           return true;
//       }
        return false;
    }

    /**
     *  判断数组是否为空
     * @return
     */
    public boolean isEmpty(){
        if (flag == 0){
            return true;
        }
//        if (rear == front) {
//            return true;
//        }
        return false;
    }

    /**
     *  添加数据到队列中
     * @param n
     */
    public void addQueue(int n){
        if (isFull()){
            throw new RuntimeException("队列已满，添加失败");
        }
        if (flag == 3){
            flag = 0 ;
        }
        flag++;
        arr[rear % maxSize] = n;
        rear = ((rear + 1) % maxSize);
    }

    /**
     *  移除队列中的最前面的值
     * @return
     */
    public int removeOne(){
        if (isEmpty()){
            throw new RuntimeException("队列为空，获取失败");
        }
        flag--;
        int temp = arr[front];
        front = ((front + 1) % maxSize) ;
        return temp;
    }

    /**
     *  获取队列头数据
     * @return
     */
    public int headQueue(){
        if (isEmpty()){
            throw new RuntimeException("队列为空，获取失败");
        }
        return arr[front];
    }

    /**
     *  获取尾数据
     * @return
     */
    public int endQueue(){
        if (isEmpty()){
            throw new RuntimeException("队列为空，获取失败");
        }
        if (rear == 0){
            return arr[maxSize - 1];
        }
        return arr[rear - 1];
    }

    public void showQueue(){
        if (isEmpty()){
            throw new RuntimeException("队列为空，获取失败");
        }
        for (int i = front; i < front +  realCount() ; i++) {
            System.out.print(arr[i % maxSize] + "\t");
        }
        System.out.println();
    }

    /**
     *  获取队列中元素个数
     * @return
     */
    public int realCount(){
        if ((rear - front + maxSize) %  maxSize == 0){
            return maxSize ;
        }
        return (rear - front + maxSize) %  maxSize;
    }

}