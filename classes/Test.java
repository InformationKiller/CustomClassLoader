package classes;

import testcode.Main;

public class Test {
    public static int one = 1;

    public void doSomething() {
        System.out.println("Hello world.");
        System.out.print("Current Classloader: ");
        System.out.println(Test.class.getClassLoader().getClass());

        Main.invokeClosure(() -> {
            Test.one = 0;
        });
        System.out.println("One is not 1.");
        System.out.println("Test.one = " + one);

        Main.zero = 1;
        System.out.println("Zero is not 0.");
    }
}
