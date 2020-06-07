package com.codeman.thread.activeObject;

/**
 * {@link ActiveObject#displayString()}
 */
public class DisplayStringRequest implements MethodRequest {

    private final ActiveObject servant;

    private final FutureResult<String> result;

    private String text;

    public DisplayStringRequest(ActiveObject servant, FutureResult<String> result, String text) {
        this.servant = servant;
        this.result = result;
        this.text = text;
    }

    @Override
    public void execute() {
        String resultStr = servant.displayString(text);
        RealResult<String> realResult = new RealResult<>();
        realResult.setReslut(resultStr);
        result.setResult(realResult);
    }
}
