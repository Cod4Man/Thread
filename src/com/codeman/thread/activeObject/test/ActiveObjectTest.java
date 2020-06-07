package com.codeman.thread.activeObject.test;

import com.codeman.thread.activeObject.ActiveObject;
import com.codeman.thread.activeObject.ActiveObjectFactory;
import com.codeman.thread.activeObject.DisplayStringThread;
import com.codeman.thread.activeObject.MakeStringThread;

public class ActiveObjectTest {

    public static void main(String[] args) {
        ActiveObject activeObject = ActiveObjectFactory.createActiveObject();

        new MakeStringThread('A', activeObject).start();
        new MakeStringThread('B', activeObject).start();
        new MakeStringThread('C', activeObject).start();

        new DisplayStringThread(activeObject, "天气不错").start();
        new DisplayStringThread(activeObject, "下午不会下雨").start();

    }
}
