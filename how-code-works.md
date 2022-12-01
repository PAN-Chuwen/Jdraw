#### 在哪里调用`g.drawLine(...)`这样的方法画画?

`DrawCanvas.java `中的`paintComponent()`负责画出所有图形, 其他所有地方都只应该调用`repaint()`, 让`repaint()`去调用`paintComponent()`



#### 怎么存储所有的图形?

`Shape.java`中定义了所有图形的数据结构, 在`DrawCanvas.java`中我们用一个栈`shapeStack`来存储所有可能被创建的图形.



#### 图形什么时候被创建?

从用户角度来看, 每当鼠标点击一次, 就应该创建一个新的图形. 

`DrawCanvas.java` 中我们添加了 `addMouseListener ->mousePressed() ` . 当鼠标被点击时该方法会自动被调用,   当中包括了创建新图形, 并把它push进`shapeStack`的代码.

 

#### 怎么判断创建图形的类型?

从`SideBar`中获取`ShapeType`.



`paintComponent()`中怎么实现每个shape画出对应的形状?

直接调用`shape.draw()` 即可, 因为我们是用interface操作的, 每个图形都overrider了这个方法.



#### 怎么实现图形变化(例如矩形的伸缩)?

`addMouseMotionListener -> mouseDragged()` 更改对应`Shape` 的数据然后`repaint()`



如何实现undo/redo功能?





### 功能

- [x] 画出各种图形
- [x] 添加 `Sidebar`(先用Text描述即可, 后续替换成Icon)
- [x] 调整颜色(包括取色框), 仅改变下一个画出来的图形颜色, 如果要改已经画出来的图形颜色会比较复杂
- [ ] 橡皮擦
- [x] 图形线条粗细/橡皮擦大小
- [ ] 打开图片
- [ ] 保存图片
- [ ] 撤销 undo / redo
- [ ] 放大/缩小
- [ ] 重新选择画出来的对象(一开始默认的行为)



SideBar.java 和 drawCanvas.java 中包括了Controller(主要通过listener实现)的代码

#### SideBar中改变颜色/粗细怎么传递到Canvas中?



#### 为什么用colorChooser重选颜色后shape会变成Rectangle?

在负责改变形状的buttonList中不小心加入了colorChooserButton.



#### 第一步

画线 + 清除

能够 gradle run



graphics

几个自己执行, 不需要在代码里调用的方法

paint()

repaint()

Listener()



#### [Java GUI: about getContentPane( ) method and content](https://stackoverflow.com/questions/16744152/java-gui-about-getcontentpane-method-and-content)

#### [How do you format code in Visual Studio Code (VSCode)?](https://stackoverflow.com/questions/29973357/how-do-you-format-code-in-visual-studio-code-vscode)

#### [repaint() method in JFrame or Jpanel?](https://stackoverflow.com/questions/36368485/repaint-method-in-jframe-or-jpanel)

#### [What does super.paintComponent(g) do?](https://stackoverflow.com/questions/28724609/what-does-super-paintcomponentg-do)

#### [who calls paintComponent()](http://www.fredosaurus.com/notes-java/GUI-lowlevel/graphics/15who-calls-paintcomponent.html)

#### [How to get the name of a class without the package?](https://stackoverflow.com/questions/2690333/how-to-get-the-name-of-a-class-without-the-package)

#### [Java swing draw rectangle in mouse drag and drop](https://stackoverflow.com/questions/40945461/java-swing-draw-rectangle-in-mouse-drag-and-drop)

#### [color chooser](https://docs.oracle.com/javase/tutorial/uiswing/components/colorchooser.html)

#### [How to display a color selector when clicking a button?](https://stackoverflow.com/questions/26565166/how-to-display-a-color-selector-when-clicking-a-button)

每个component都有对应的类(一般来说只有一个, 例如 `BtnCountListener`), 这个类实现了interface ActionListener, 并且override方法`actionPerformed(ActionEvent evt)`



然后在构造函数里需要创建这个类, 并且绑定

```java
BtnCountListener listener1 = new BtnCountListener();
btnCount.addActionListener(listener1);
```







绑定完后, 点按钮时相当于执行了以下代码

```java
ActionEvent evt = new ActionEvent( ...... );
listener1.actionPerformed(evt);   // for all its listener(s)
```



最关键的是中间有一个`ActionEvent`类, 这个类代码中只在listener的方法中作为参数出现, 但是在点击按钮时会执行代码来创建`ActionEvent` 对象





```tex
用Java的awt和swing做一个简单的绘图工具，以CAD的方式操作，能放置直线、矩形、圆和文字，能选中图形，修改参数，如颜色等，能拖动图形和调整大小，可以保存和恢复。功能请参考视频演示。

要求上传：

源码；
实验报告；
可执行的jar文件。
实验报告中须注明使用的Java版本、在Linux平台上编译源码及运行的命令。
```







![Java Swing Class Hierarchy Diagram](./.assets/GUI/java-swing-class-hierarchy.jpg) 

![Swing_ClassDiagram.png](./.assets/GUI/Swing_ClassDiagram.png) 