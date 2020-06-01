package python;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class testPython {
    public static void main(String[] args) {
        Process proc;
        try {
            proc = Runtime.getRuntime().exec("python /Users/wuzhanhao/python.py ");
            //从字符输入流读取文本，缓冲字符，以提供字符，数组和行的高效读取
            BufferedReader in = new BufferedReader(new InputStreamReader(proc.getInputStream()));
            String line = null;
            //readLine,是一行一行读
            boolean ready = in.ready();
            System.out.println(ready);
            while ((line = in.readLine()) != null) {
                System.out.println(line);
            }
            in.close();
            proc.waitFor();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
