package CA3.Project.src.model;

public class Key {
    private char wrongKey;
    private int wrongCount;

    public char getWrongKey() {
        return wrongKey;
    }

    public void setWrongKey(char wrongKey) {
        this.wrongKey = wrongKey;
    }

    public int getWrongCount() {
        return wrongCount;
    }

    public void setWrongCount(int wrongCount) {
        this.wrongCount = wrongCount;
    }

    public Key(char wrongKey) {
        this.wrongKey = wrongKey;
        this.wrongCount = 0;
    }

    public Key(char wrongKey, int wrongCount) {
        this.wrongKey = wrongKey;
        this.wrongCount = wrongCount;
    }

    @Override
    public String toString() {
        return "Key{" +
                "wrongKey=" + wrongKey +
                ", wrongCount=" + wrongCount +
                '}';
    }
}
