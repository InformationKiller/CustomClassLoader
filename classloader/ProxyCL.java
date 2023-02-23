package classloader;

import java.io.IOException;
import java.io.InputStream;

public class ProxyCL extends ClassLoader {

    public static final ProxyCL instance = new ProxyCL(ProxyCL.class.getClassLoader());

    public ProxyCL(ClassLoader parent) {
        super(parent);
    }

    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        synchronized (getClassLoadingLock(name)) {
            Class<?> c = findLoadedClass(name);

            if (c == null) {
                if (name.startsWith("classes.") || name.startsWith("classloader.") && !name.equals("classloader.ProxyCL")) {
                    InputStream s = this.getResourceAsStream(name.replace(".", "/") + ".class");
                    try {
                        byte[] bytes = new byte[s.available()];

                        s.read(bytes);

                        return this.defineClass(name, bytes, 0, bytes.length);
                    } catch (IOException e) {}
                }
            } else {
                return c;
            }
        }

        return super.loadClass(name);
    }
}
