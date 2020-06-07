package com.codeman.thread.activeObject;

/**
 * 任务实现类，实际做事的类
 */
class Servant implements ActiveObject {

    @Override
    public String makeString(int length, char ch) {
        char[] chars = new char[length];
        for (int i = 0; i < chars.length; i++) {
            chars[i] = ch;
        }
        String s = new String(chars);
        return s;
    }

    @Override
    public String displayString(String text) {
        return text;
    }
}
