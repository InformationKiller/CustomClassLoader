package testcode;

import classes.TestAPI;

public class Main {

    public static int zero = 0;

    public static void main(String[] args) {
        System.out.print("Default Classloader: ");
        System.out.println(Main.class.getClassLoader().getClass());
        System.out.println();

        TestAPI.doSomething();
        System.out.print("Main.zero = ");
        System.out.println(zero);
        System.out.println();

        System.out.println("1 + 2 = " + TestAPI.add(1, 2));
        System.out.println();
        
        TestAPI.throwAmazingException();
    }

    public static void invokeClosure(Runnable closure) {
        closure.run();
    }
}
