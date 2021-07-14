package a;

import org.miser.core.lang.ConsoleTable;
import org.miser.core.lang.caller.CallerUtil;

public class A {
    private class InnerClass {
    }

    public A() {
        new InnerClass() {
            {
                int i = 0;
                Class<?> caller = CallerUtil.getCaller(i);
                final ConsoleTable t = new ConsoleTable();
                t.addHeader("类名", "类加载器");
                System.out.println("初始化 " + getClass() + " 的调用链为: ");
                while (caller != null && caller.getClassLoader() != null) {
                    t.addBody(caller.toString(), caller.getClassLoader().toString());
                    caller = CallerUtil.getCaller(++i);
                }
                t.print();
            }
        };
    }
}