package com.codeman.thread.activeObject;

/**
 * {@link ActiveObject#makeString(int, char)}
 */
public class MakeStringRequest implements MethodRequest<String> {

    private int length;

    private char ch;

    private final ActiveObject servant;

    private final FutureResult<String> result;

    public MakeStringRequest(ActiveObject servant, FutureResult<String> result, int length, char ch) {
        this.servant = servant;
        this.result = result;
        this.length = length;
        this.ch = ch;
    }

    @Override
    public void execute() {
        String resultStr = servant.makeString(length, ch);
        RealResult<String> realResult = new RealResult<>();
        realResult.setReslut(resultStr);
        result.setResult(realResult);
    }
}
