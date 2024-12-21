import java.util.Random;

public class BlockController {
    private Block[][] blocks;
    private int size;

    public BlockController(int size, int numBombs) {
        this.size = size;
        this.blocks = new Block[size][size];
        initializeBlocks();
        placeBombs(numBombs);
        calculateAmounts();
    }

    private void initializeBlocks() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                blocks[i][j] = new Block(false, 0);
            }
        }
    }

    private void placeBombs(int numBombs) {
        Random random = new Random();
        int placedBombs = 0;

        while (placedBombs < numBombs) {
            int row = random.nextInt(size);
            int col = random.nextInt(size);

            if (!blocks[row][col].isBomb()) {
                blocks[row][col].setBomb(true);
                placedBombs++;
            }
        }
    }

    private void calculateAmounts() {
        int[] dx = {-1, -1, -1, 0, 0, 1, 1, 1};
        int[] dy = {-1, 0, 1, -1, 1, -1, 0, 1};

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (blocks[i][j].isBomb()) {
                    continue;
                }

                int count = 0;
                for (int k = 0; k < 8; k++) {
                    int newRow = i + dx[k];
                    int newCol = j + dy[k];

                    if (isValidPosition(newRow,newCol))
                        if (blocks[newRow][newCol].isBomb())
                            count++;
                }

                blocks[i][j].setAmount(count);
            }
        }
    }

    public boolean isValidPosition(int row, int col) {
        return row >= 0 && row < size && col >= 0 && col < size;
    }

    public Block getBlock(int row, int col) {
        if (!isValidPosition(row, col)) {
            throw new IllegalArgumentException("Invalid row or column index.");
        }
        return blocks[row][col];
    }

    public int getSize(){
        return size;
    }

    public void printBoard() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.print(blocks[i][j] + " ");
            }
            System.out.println();
        }
    }

    public void printDeveloperBoard() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (blocks[i][j].isBomb()) {
                    System.out.print("B ");
                } else {
                    System.out.print(blocks[i][j].getAmount() + " ");
                }
            }
            System.out.println();
        }
    }
}
