package GameElement;

public class Position {
    private int lane;
    private int box;
    public Position(int lane, int box)
    {
        this.lane=lane;
        this.box=box;
    }
    public int Lane(int y)
    {
        if(y>=87 && y<=247)
            return 1;
        else if (y>247 && y<=407) {
            return 2;
        } else if (y>407 && y<=567) {
            return 3;
        } else if(y>567 && y<=727) {
            return 4;
        }
        else return 5;
    }
    public int Box(int x){
        if(x>=375 && x<498)
            return 1;
        else if (x>=498 && x<621) {
            return 2;
        }
        else if (x>=621&& x<744){
            return 3;
        }
        else if (x>=744 && x<867) {
            return 4;
        }
        else if (x>=867 && x<990) {
            return 5;
        }
        else if (x>=990 && x<1113){
            return 6;
        }
        else if (x>=1113 && x<1236){
            return 7;
        }
        else if (x>=1236 && x<1359){
            return 8;
        }
        else return 9;
    }

    public int getBox() {
        return box;
    }
    public int getLane() {
        return lane;
    }
    public void setBox(int box) {
        this.box = box;
    }
    public void setLane(int lane) {
        this.lane = lane;
    }
}
