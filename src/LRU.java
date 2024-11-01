import java.util.ArrayList;

public class LRU implements PageReplacementAlgorithm {

    private ArrayList<Integer> memory;
    private int capacity;

    public LRU(int capacity) {
        this.capacity = capacity;
        memory = new ArrayList<>();
    }

    public boolean addPage(int page) {
        if (memory.contains(page)) {
            memory.remove(Integer.valueOf(page));
            memory.add(page);
            return false;
        }

        if (memory.size() >= capacity) {
            memory.remove(0);
        }

        memory.add(page);
        return true;
    }

    public ArrayList<Integer> getMemory() {
        return memory;
    }
}
