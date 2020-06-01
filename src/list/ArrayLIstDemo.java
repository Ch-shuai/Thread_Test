package list;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * 2020/5/21
 *
 * @author wuzhanhao
 * <p>
 * description:
 * ArrayList是基于数组实现的，
 * 		初始容量是16，当ArrayList扩容时，增加的容量是10000（16）>>[右移运算符]1 是1000（8）个容量，扩容之后是24，将原数组复制进去，
 * 		对于容量范围之内添加速度快，容量范围之外需要扩容添加速度慢，在频繁查找以及尾部的插入与删除场景下使用ArrayList
 */
public class ArrayLIstDemo {
	public static void main(String[] args) {
		//默认空的数据是{}，数组形式
		ArrayList<String> stringArrayList = new ArrayList<>();
		for (int i = 0; i < 16; i++) {
			stringArrayList.add(String.valueOf(i));
		}
		System.out.println(stringArrayList);
	}
}
