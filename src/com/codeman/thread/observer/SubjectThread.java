package com.codeman.thread.observer;

public class SubjectThread implements Runnable {
    private ThreadObserver observer;

    public SubjectThread(ThreadObserver observer) {
        this.observer = observer;
    }

    @Override
    public void run() {
        try {
            this.observer.notice(new Message(MessageStatus.RUNNING, null, "运行中"));
            int a = 1/0;
        } catch (Throwable e) {
            this.observer.notice(new Message(MessageStatus.ERROR, e, "发生异常"));
        } finally {
            this.observer.notice(new Message(MessageStatus.DEAD, null, "即将结束.."));
        }
    }

    private enum MessageStatus {
        DEAD, FREE, BLOCK, RUNNING, ERROR
    }

    public class Message {
        private MessageStatus messageStatus;
        private Throwable throwable;
        private String message;

        public Message(MessageStatus messageStatus, Throwable throwable, String message) {
            this.messageStatus = messageStatus;
            this.throwable = throwable;
            this.message = message;
        }

        public MessageStatus getMessageStatus() {
            return messageStatus;
        }

        public void setMessageStatus(MessageStatus messageStatus) {
            this.messageStatus = messageStatus;
        }

        public Throwable getThrowable() {
            return throwable;
        }

        public void setThrowable(Throwable throwable) {
            this.throwable = throwable;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }
}
