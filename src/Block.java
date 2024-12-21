public class Block {
    private boolean isOpened;
    private boolean isBomb;
    private int amount;

    public Block(boolean isBomb, int amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Amount cannot be negative.");
        }
        this.isOpened = false;
        this.isBomb = isBomb;
        this.amount = amount;
    }

    public boolean isOpened() {
        return isOpened;
    }

    public void setOpened(boolean opened) {
        isOpened = opened;
    }

    public boolean isBomb() {
        return isBomb;
    }

    public void setBomb(boolean bomb) {
        isBomb = bomb;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void open(){
        isOpened = true;
    }

    public String toString() {
        if (isOpened)
            return String.valueOf(amount);
        return "F";
    }
}
