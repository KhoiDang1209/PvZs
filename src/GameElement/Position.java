package GameElement;

public class Position {
    private int lane;
    private int box;

    public Position(int box, int lane) {
        this.lane = lane;
        this.box = box;
    }

    public int Lane(int y) {
        if (y >= 110 && y <= 265)
            return 1;
        else if (y > 265 && y <= 411) {
            return 2;
        } else if (y > 411 && y <= 577) {
            return 3;
        } else if (y > 577 && y <= 710) {
            return 4;
        } else
            return 5;
    }

    public int Box(int x) {
        if (x >= 375 && x < 498)
            return 1;
        else if (x >= 498 && x < 621) {
            return 2;
        } else if (x >= 621 && x < 744) {
            return 3;
        } else if (x >= 744 && x < 867) {
            return 4;
        } else if (x >= 867 && x < 985) {
            return 5;
        } else if (x >= 985 && x < 1104) {
            return 6;
        } else if (x >= 1113 && x < 1223) {
            return 7;
        } else if (x >= 1236 && x < 1345) {
            return 8;
        } else
            return 9;
    }

    public int BoxDraw(int x) {
        if (Box(x) == 1)
            return 375;
        else if (Box(x) == 2) {
            return 498;
        } else if (Box(x) == 3) {
            return 621;
        } else if (Box(x) == 4) {
            return 744;
        } else if (Box(x) == 5) {
            return 867;
        } else if (Box(x) == 6) {
            return 985;
        } else if (Box(x) == 7) {
            return 1113;
        } else if (Box(x) == 8) {
            return 1236;
        } else
            return 1349;
    }

    public int LaneDraw(int y) {
        if (Lane(y) == 1)
            return 265;
        else if (Lane(y) == 2) {
            return 411;
        } else if (Lane(y) == 3) {
            return 577;
        } else if (Lane(y) == 4) {
            return 710;
        } else
            return 859;
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
