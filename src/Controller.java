public class Controller {
    private BlockController blockController;
    private boolean gameOver;
    private int remainingSafeBlocks;

    public Controller(int size, int numBombs) {
        if (size < 3 || size > 10) {
            throw new IllegalArgumentException("Board size is out of bounds.");
        }
        if (numBombs <= 0 || numBombs >= size * size) {
            throw new IllegalArgumentException("Invalid number of bombs.");
        }

        blockController = new BlockController(size, numBombs);
        remainingSafeBlocks = size * size - numBombs;
        gameOver = false;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public void openBlock(int row, int col) {
        if (gameOver) {
            return;
        }

        Block block = blockController.getBlock(row, col);
        if (block.isOpened()) {
            throw new IllegalStateException("This block is already opened.");
        }

        block.setOpened(true);

        if (block.isBomb()) {
            gameOver = true;
            System.out.println("The Game is over!");
        } else {
            remainingSafeBlocks--;
            if (remainingSafeBlocks == 0) {
                gameOver = true;
                System.out.println("You won the game.");
            }
        }
    }

    public void printBoard() {
        blockController.printBoard();
    }

    public boolean isValidPosition(int row, int col) {
        return blockController.isValidPosition(row, col);
    }

    public void printDeveloperBoard(){
        blockController.printDeveloperBoard();
    }
}
