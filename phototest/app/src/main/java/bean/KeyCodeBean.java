package bean;

import android.view.KeyEvent;

public class KeyCodeBean {

    private int state;
    private int keyCode;
    private KeyEvent keyEvent;

    public KeyCodeBean(int state, int keyCode) {
        this.state = state;
        this.keyCode = keyCode;
    }

    public KeyCodeBean(int state, KeyEvent keyEvent) {
        this.state = state;
        this.keyEvent = keyEvent;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getKeyCode() {
        return keyCode;
    }

    public void setKeyCode(int keyCode) {
        this.keyCode = keyCode;
    }

    public KeyEvent getKeyEvent() {
        return keyEvent;
    }

    public void setKeyEvent(KeyEvent keyEvent) {
        this.keyEvent = keyEvent;
    }
}
