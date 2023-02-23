# 自定义ClassLoader

测试了一下ClassLoader是怎么运作的，为以后要做的东西做准备。

代码加载类时会从当前类的类加载器进行加载。  
不同类加载器加载的同一个类无法做类型转换，会报ClassCastException，没经验的一看就懵了，这什么鬼报错。
```
Exception in thread "main" java.lang.ClassCastException: classes.Test cannot be cast to classes.Test
	at classes.TestAPI.throwException(TestAPI.java:23)
	at testcode.Main.main(Main.java:22)
```
基于上面的东西可以做出A类可以访问B类但B类无法访问A类的单向访问效果，其实有这些东西能做的可多了去了，反正很好玩的~  
闭包内代码所属的加载器取决于闭包构造时代码所属的类加载器。  