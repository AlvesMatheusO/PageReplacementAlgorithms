import java.util.ArrayList;

public class CLOCK implements PageReplacementAlgorithm {
    
    private class Page {
        int number;
        boolean used;

        public Page(int number) {
            this.number = number;
            this.used = true;
        }
    }

    private ArrayList<Page> memory;
    private int capacity;
    private int pointer;

    public CLOCK (int capacity) {
        this.capacity = capacity;
        this.memory = new ArrayList<>(capacity);
        this.pointer = 0;
    }

    @Override
    public boolean addPage(int pageNumber) {
        for (Page page : memory) {
            if (page.number == pageNumber) {
                page.used = true;
                return false;
            }
        }
        if (memory.size() < capacity) {
            memory.add(new Page(pageNumber));
        } else {
            replacePage(new Page(pageNumber));
        }
        return true;
    }
    

    private void replacePage(Page newPage) {
        while (true) {
            Page currentPage = memory.get(pointer);

            if (!currentPage.used) {
                memory.set(pointer, newPage);
                pointer = (pointer + 1) % capacity;
                break;
            }

            currentPage.used = false;
            pointer = (pointer + 1) % capacity;
        }
    }

    @Override
    public ArrayList<Integer> getMemory() {
        ArrayList<Integer> pageNumbers = new ArrayList<>();

        for (Page page : memory) {
            pageNumbers.add(page.number);
        }
        return pageNumbers;
    }
}
