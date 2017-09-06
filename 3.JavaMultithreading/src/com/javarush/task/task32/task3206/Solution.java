package task32.task3206;

/**
 * Created by ukr-sustavov on 30.06.2017.
 */
import java.lang.reflect.Proxy;

public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        test(solution.getProxy(Item.class));                        //true false false
        test(solution.getProxy(Item.class, Small.class));           //true false true
        test(solution.getProxy(Item.class, Big.class, Small.class));//true true true
        test(solution.getProxy(Big.class, Small.class));            //true true true т.к. Big наследуется от Item
        test(solution.getProxy(Big.class));
    }

    private static void test(Object proxy) {
        boolean isItem = proxy instanceof Item;
        boolean isBig = proxy instanceof Big;
        boolean isSmall = proxy instanceof Small;

        System.out.format("%b %b %b\n", isItem, isBig, isSmall);
    }

    public <T extends Item>  T getProxy(Class mainClass, Class... classes) {

        ClassLoader cl = this.getClass().getClassLoader();

        Class[] interfaces = new Class[classes.length + 1];
        System.arraycopy(classes, 0, interfaces, 1, classes.length);
        interfaces[0] = mainClass;

        return (T) Proxy.newProxyInstance(cl, interfaces, new ItemInvocationHandler());
    }
}
