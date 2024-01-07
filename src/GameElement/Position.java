package GameElement;

public class Position {
    private int lane;
    private int box;
    public Position(int box, int lane)
    {
        this.lane=lane;
        this.box=box;
    }
    public int Lane(int y)
    {
        if(y>=110 && y<=265)
            return 1;
        else if (y>265 && y<=411) {
            return 2;
        } else if (y>411 && y<=577) {
            return 3;
        } else if(y>577 && y<=710) {
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
        else if (x>=867 && x<985) {
            return 5;
        }
        else if (x>=985 && x<1104){
            return 6;
        }
        else if (x>=1113 && x<1223){
            return 7;
        }
        else if (x>=1236 && x<1345){
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
