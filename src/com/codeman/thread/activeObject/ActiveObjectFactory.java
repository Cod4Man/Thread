package com.codeman.thread.activeObject;

/**
 * ActiveObject的创建工厂
 */
public class ActiveObjectFactory {

    private ActiveObjectFactory() {

    }


    public static ActiveObject createActiveObject() {
        Servant servant = new Servant();
        ActivationQueue activationQueue = new ActivationQueue();
        SchedularThread thread = new SchedularThread(activationQueue);

        thread.start();
        ActiveObject activeObject = new ActiveObjectProxy(servant, thread);

        return activeObject;
    }
}
