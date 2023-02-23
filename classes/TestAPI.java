package classes;

import classloader.PublicInterfaces;

public class TestAPI extends PublicInterfaces {

    public static int add(int a, int b) {
        System.out.print("Calling add(a,b), Classloader: ");
        System.out.println(TestAPI.class.getClassLoader().getClass());

        return checkClassLoader(() -> {
            return a + b;
        }, a, b);
    }

    public static void doSomething() {
        checkClassLoader(() -> {
            new Test().doSomething();
        });
    }

    public static Test throwAmazingException() {
        return checkClassLoader(() -> {
            return new Test();
        });
    }
}
