package week10;

public class Car1 {
    // private로 직접 접근 제한
    private int speed;
    private boolean stop;

    // Getter
    public int getSpeed() {
        return speed;
    }

    // Setter (데이터 검증)
    public void setSpeed(int speed) {
        if(speed < 0) {
            this.speed = 0;
            return;
        } else {
            this.speed = speed;
        }
    }

    public boolean isStop() {
        return stop;
    }

    public void setStop(boolean stop) {
        this.stop = stop;
        this.speed = 0;
    }
}