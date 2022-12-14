package love.aojie.队列;

import java.util.Scanner;

/**
 * @author: JieGe
 * @time:
 * @function: 用数组模拟队列
 */
public class ArrayOfQueue {
    //CircleArrayOfQueue
    public static void main(String[] args) {
        // 创建队列
        ArrayQueue arrayQueue = new ArrayQueue(3);
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
                        System.out.println("获取的头元素为: " + i + "\n");
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

// 使用数组模拟队列--编写一个 队列 类
class ArrayQueue {
    private int maxSize;  // 数组的最大容量
    private int front;    // 队列头
    private int rear;     // 队列尾
    private int[] arr;    // 该数组存放数据，模拟队列

    public ArrayQueue(){
    }

    // 空参构造器，用于初始化队列的前后指针,和最大容量
    public ArrayQueue(int maxSize){
        // 用户指定数组的最大容量
        this.maxSize = maxSize;
        // 初始化头指针，让他指向第一个元素的前面
        front = -1;
        // 出事啊尾指针，让他指向第一个元素（由于开始没有元素，所以他和头指针指向位置相同）
        rear = -1;
        // 创建数组，表示队列
        arr = new int[maxSize];
    }

    /**
     *   判断数组是否为满
     * @return
     */
    public boolean isFull(){
        if (maxSize - 1 <= rear){
            return true;
        }
        return false;
    }

    /**
     *  判断数组是否为空
     * @return
     */
    public boolean isEmpty(){
        if (front == rear){
            return true;
        }
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
        rear++;
        arr[rear] = n;
    }

    /**
     *  移除队列中的最前面的值
     * @return
     */
    public int removeOne(){
        if (isEmpty()){
            throw new RuntimeException("队列为空，获取失败");
        }
        front++;
        return arr[front];
    }

    /**
     *  获取队列头数据
     * @return
     */
    public int headQueue(){
        if (isEmpty()){
            throw new RuntimeException("队列为空，获取失败");
        }
        return arr[front + 1];
    }

    /**
     *  获取尾数据
     * @return
     */
    public int endQueue(){
        return arr[rear];
    }

    public void showQueue(){
        if (isEmpty()){
            throw new RuntimeException("队列为空，获取失败");
        }
        for (int i = front + 1; i <= rear; i++) {
            System.out.print(arr[i] + "\t");
        }
        System.out.println();
    }
}