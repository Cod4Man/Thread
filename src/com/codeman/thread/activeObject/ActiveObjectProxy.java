package com.codeman.thread.activeObject;

class ActiveObjectProxy implements ActiveObject {

    private final Servant servant;

    private final SchedularThread schedularThread;

    public ActiveObjectProxy(Servant servant, SchedularThread schedularThread) {
        this.servant = servant;
        this.schedularThread = schedularThread;
    }

    @Override
    public String makeString(int length, char ch) {
        FutureResult<String> futureResult = new FutureResult<>();
        MethodRequest<String> methodRequest = new MakeStringRequest(servant, futureResult, length, ch);
        schedularThread.invoke(methodRequest);
        return futureResult.getResultValue();
    }

    @Override
    public String displayString(String text) {
        FutureResult<String> futureResult = new FutureResult<>();
        MethodRequest<String> methodRequest = new DisplayStringRequest(servant, futureResult, text);
        schedularThread.invoke(methodRequest);
        return futureResult.getResultValue();
    }
}
