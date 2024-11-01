import java.util.*;

public class NFU implements PageReplacementAlgorithm {

    private Map<Integer, Integer> memory;  // Mapa para armazenar a página e o contador de acessos
    private int capacity;

    public NFU(int capacity) {
        this.capacity = capacity;
        this.memory = new LinkedHashMap<>();
    }

    @Override
    public boolean addPage(int page) {
        // Verifica se a página já está na memória
        if (memory.containsKey(page)) {
            // Incrementa o contador de acessos se a página já estiver na memória
            memory.put(page, memory.get(page) + 1);
            return false; // Não houve falta de página
        }

        // Se a memória está cheia, é necessário remover uma página
        if (memory.size() >= capacity) {
            // Encontra a página com o menor contador de acessos para remover
            int pageToRemove = findLeastFrequentlyUsedPage();
            memory.remove(pageToRemove);
        }

        // Adiciona a nova página na memória com contador de acesso inicial 1
        memory.put(page, 1);
        return true; // Houve falta de página
    }

    // Encontra a página com o menor contador de acessos
    private int findLeastFrequentlyUsedPage() {
        int minAccessCount = Integer.MAX_VALUE;
        int pageToRemove = -1;

        for (Map.Entry<Integer, Integer> entry : memory.entrySet()) {
            int page = entry.getKey();
            int accessCount = entry.getValue();

            // Procura pela página com o menor contador de acessos
            if (accessCount < minAccessCount) {
                minAccessCount = accessCount;
                pageToRemove = page;
            }
        }

        return pageToRemove;
    }

    @Override
    public List<Integer> getMemory() {
        // Retorna apenas as páginas na memória, sem os contadores de acesso
        return new ArrayList<>(memory.keySet());
    }
}
