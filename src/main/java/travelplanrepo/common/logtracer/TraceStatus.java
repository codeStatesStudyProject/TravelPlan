package travelplanrepo.common.logtracer;

import lombok.Getter;

import java.util.Stack;
import java.util.UUID;

@Getter
public class TraceStatus {
    private String id;
    private int level;
    private Stack<String> messageStack;
    private Stack<Long> startTimeMsStack;

    public TraceStatus() {
        this.id = createId();
        this.level = 0;
    }

    private String createId() {
        return UUID.randomUUID().toString().substring(0,8);
    }

    public void beNextLevel(String message) {
        this.level++;
        this.messageStack.push(message);
    }

    public void bePreviousId() {
        this.level--;
    }

    public boolean isFirstLevel() {
        return level == 0;
    }

    public void setStartTimeMs(Long startTimeMs) {
        this.startTimeMsStack.push(startTimeMs);
    }
}
