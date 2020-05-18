package help;

import java.util.function.Function;

/**
 * 2020/5/18
 *
 * @author wuzhanhao
 * <p>
 * description:
 *  函数型接口：  Consumer,Function,Supplier,Operator
 */
public class FunctionInterfaceDemo {
    //传入参数T，返回类型R
    Function function = new Function<String,Object>(){

        @Override
        public Object apply(String s) {
            return null;
        }
    };
}
