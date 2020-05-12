package python;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class testPyhton {
    public static void main(String[] args) {
            Process process;
            try {
                process = Runtime.getRuntime().exec("python \\Users\\wuzhanhao\\python.py ");
                BufferedReader in = new BufferedReader(new InputStreamReader(process.getInputStream()));
                String line = null;
                while ((line = in.readLine()) != null) {
                    System.out.println(line);
                }
                in.close();
                process.waitFor();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
    }
}
