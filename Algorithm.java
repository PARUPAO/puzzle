import java.util.Random;

public class Algorithm {
    private int[][] puzzleBoard;

    public Algorithm() {
        puzzleBoard = new int[4][4];
        for (int y = 0; y < 4 ; y++) {
            for (int x = 0; x < 4; x++) {
                puzzleBoard[y][x] = (y * 4 + 1 + x);
            }
        }
        puzzleBoard[3][3] = 0;
    }

    public void test1() {
        for (int[] i : puzzleBoard) {
            for (int v : i) {
                System.out.print(v + "-");
            }
            System.out.println();
        }
    }

    public boolean Move(int x, int y) {
        if (x < 0 || x >= 4 || y < 0 || y >= 4) { return false;}
        //　クリックしたマス情報
        int v = puzzleBoard[y][x];
        boolean flg = false;
        //　上のマスが空白かどうか
        if (y > 0) {
            if (puzzleBoard[y - 1][x] == 0) {
                puzzleBoard[y - 1][x] = v;  flg = true;
            }
        }
        //　右のマスが空白かどうか
        if (x < 3) {
            if (puzzleBoard[y][x + 1] == 0) {
                puzzleBoard[y][x + 1] = v; flg = true;
            }
        }
        //　下のマスが空白かどうか
        if (y < 3) {
            if (puzzleBoard[y + 1][x] == 0) {
                puzzleBoard[y + 1][x] = v; flg = true;
            }
        }
        //　左のマスが空白かどうか
        if (x > 0) {
            if (puzzleBoard[y][x - 1] == 0) {
                puzzleBoard[y][x - 1] = v; flg = true;
            }
        }
        // 空白マスに移動できたなら
        if (flg == true) {
            puzzleBoard[y][x] = 0;
        }
        return flg;
    }
    
    public void jumpMove(int x, int y) {
        int zeroX = this.get0X();
        int zeroY = this.get0Y();
        if (x == zeroX) {
            if (y == zeroY) { return; }
            if (y > zeroY) {
                for (int i = 1; i <= y - zeroY; i++) {
                    this.Move(x, zeroY + i);
                }
                return;
            }
            if (y < zeroY) {
                for (int i = 1; i <= zeroY - y; i++) {
                    this.Move(x, zeroY - i);
                }
                return;
            }
        }
        if (y == zeroY) {
            if (x == zeroX) { return; }
            if (x > zeroX) {
                for (int i = 1; i <= x - zeroX; i++) {
                    this.Move(zeroX + i, y);
                }
                return;
            }
            if (x < zeroX) {
                for (int i = 1; i <= zeroX - x; i++) {
                    this.Move(zeroX - i, y);
                }
                return;
            }
        }

    }

    public int get0X() {
        int retrunX = 0;
        for (int y = 0; y < 4; y++) {
            for (int x = 0; x < 4; x++) {
                if (puzzleBoard[y][x] == 0) {
                    retrunX = x;
                    break;
                }
            }
        }
        return retrunX;
    }

    public int get0Y() {
        int returnY = 0;
        for (int y = 0; y < 4; y++) {
            for (int x = 0; x < 4; x++) {
                if (puzzleBoard[y][x] == 0) {
                    returnY = y;
                    break;
                }
            }
        }
        return returnY;
    }

    public void shuffle() {
        Random random = new Random();
        for (int i = 0; i < 500; i++) {
            int zeroX = get0X();
            int zeroY = get0Y();
            int v = random.nextInt(4);
            switch (v) {
                //  上に移動
                case 0 : Move(zeroX, zeroY + 1);
                break;
                //　右に移動
                case 1 : Move(zeroX - 1, zeroY);
                break;
                //下に移動
                case 2 : Move(zeroX, zeroY - 1);
                break;
                //左に移動
                case 3 : Move(zeroX + 1, zeroY);
                break;
            }
        }
    }

    public int getValue(int x, int y) {
        return puzzleBoard[y][x];
    }

    public boolean getClearFlg() {
        for (int y = 0; y < 4; y++) {
            for (int x = 0; x < 4; x++) {
                if (y == 3 && x == 3) {
                    if (puzzleBoard[y][x] == 0) { continue; }
                }
                if (puzzleBoard[y][x] != y * 4 + x + 1) { return false; }
            }
        }
        return true;
    }
}