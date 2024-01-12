package GameElement;

public class Position {
    private int lane;
    private int box;

    public Position(int box, int lane) {
        this.lane = lane;
        this.box = box;
    }

    // Position for Plant have completed
    public int Lane(int y) {
        if (y >= 120 && y <= 240)
            return 1;
        else if (y > 240 && y <= 380) {
            return 2;
        } else if (y > 380 && y <= 505) {
            return 3;
        } else if (y > 510 && y <= 645) {
            return 4;
        } else
            return 5;
    }

    public int Box(int x) {
        if (x >= 315 && x < 442)
            return 1;
        else if (x >= 442 && x < 543) {
            return 2;
        } else if (x >= 543 && x < 654) {
            return 3;
        } else if (x >= 654 && x < 755) {
            return 4;
        } else if (x >= 755 && x < 871) {
            return 5;
        } else if (x >= 871 && x < 964) {
            return 6;
        } else if (x >= 964 && x < 1070) {
            return 7;
        } else if (x >= 1070 && x < 1175) {
            return 8;
        } else
            return 9;
    }

    public int BoxDraw(int x) {
        if (Box(x) == 1)
            return 330;
        else if (Box(x) == 2) {
            return 435;
        } else if (Box(x) == 3) {
            return 540;
        } else if (Box(x) == 4) {
            return 645;
        } else if (Box(x) == 5) {
            return 750;
        } else if (Box(x) == 6) {
            return 855;
        } else if (Box(x) == 7) {
            return 960;
        } else if (Box(x) == 8) {
            return 1065;
        } else
            return 1170;
    }

    public int LaneDraw(int y) {
        if (Lane(y) == 1)
            return 125;
        else if (Lane(y) == 2) {
            return 255;
        } else if (Lane(y) == 3) {
            return 385;
        } else if (Lane(y) == 4) {
            return 505;
        } else
            return 630;
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
