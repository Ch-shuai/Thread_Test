package list;

import java.util.LinkedList;

/**
 * 2020/5/21
 *
 * @author wuzhanhao
 * <p>
 * description:
 * 		LinckedList循环双向链表数据结构,
 * 		LinckedList.add()方法，和LinckedList.addLast()是同一个方法，所以LinckedList添加时在最后一位
 * 		LinckedList.addLast()，Node<E>为一个链表的节点，添加进的数据为一个节点，如果为空则为第一位，
 * 		LinckedList.add(int index, E element),先判断index在不在链表的长度范围之内，IndexOutOfBoundsException
 * 		LinckedList.addFirst()，Node<E>为一个链表的节点，添加进的数据为一个节点，如果为空则为第一位，
 *
 * 	频繁在任意位置进行元素插入与删除
 */
public class LinckedListDemo {
	public static void main(String[] args) {
		LinkedList<Object> linkedList = new LinkedList<>();
		for (int i = 0; i < 20; i++) {
			linkedList.add(i);
			linkedList.add(1,i);
			linkedList.addFirst(i);
			linkedList.addLast(i);
		}
	}
}
