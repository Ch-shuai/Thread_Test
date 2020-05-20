package test;

import java.io.*;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.concurrent.*;

/**
 * 2020/5/20
 *
 * @author wuzhanhao
 * <p>
 * description:
 * 使用多线程读取文件
 * 1.读取文件
 * 2.使用线程池创建多线程进行读取文件
 */
public class FileTest {
	public static void main(String[] args) {
		String path = "C:\\Users\\bjkf0\\Desktop\\loginCmd.text";
		LinkedBlockingQueue<Runnable> blockingQueue = new LinkedBlockingQueue<>(5);
		ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
				3,
				5,
				5,
				TimeUnit.SECONDS,
				blockingQueue,
				Executors.defaultThreadFactory(),
				new ThreadPoolExecutor.AbortPolicy()
		);
		try {
			for (int i = 0; i < 5; i++) {
				threadPoolExecutor.execute(() -> {

				});
			}
		} finally {
			threadPoolExecutor.shutdown();
		}
	}
	private static StringBuilder  getFile(String path) throws IOException {
		BufferedReader bufferedReader = null;
		try {
			File file = new File(path);
			InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream(file), "utf-8");
			bufferedReader = new BufferedReader(inputStreamReader);
			String readLine = null;
			int line = 1;
			StringBuilder stringBuilder = new StringBuilder();
			while ((readLine = bufferedReader.readLine()) != null) {
				stringBuilder.append(readLine);
				line++;
			}
			return stringBuilder;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			bufferedReader.close();
		}
		return null;
	}
}
