import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import javax.swing.*;

public class GraphicInterface extends JFrame{
    
    private PageReplacementAlgorithm algorithm;
    private JTextField inputPage;
    private JTextArea memoryDisplay;
    private JComboBox<String> algorithmSelector;
    private Map<String, PageReplacementAlgorithm> algorithms;
    
    public GraphicInterface(int capacity) {
        setTitle("Simulador de Substituição de Páginas");
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        //Algoritmos disponiveis para a paginação:
        algorithms = new HashMap<>();
        algorithms.put("FIFO (First In First Out)", new FIFO(capacity));
        algorithms.put("LRU", new LRU(capacity));

        algorithmSelector = new JComboBox<>(algorithms.keySet().toArray(new String[0]));
        algorithmSelector.addActionListener(e -> selectAlgorithm()); //Implementar

        inputPage = new JTextField(10);
        JButton addButton = new JButton("Adicionar Pagina");

        memoryDisplay = new JTextArea();
        memoryDisplay.setEditable(false);

        JPanel inputPanel = new JPanel();
        inputPanel.add(new JLabel("Pagina: "));
        inputPanel.add(inputPage);
        inputPanel.add(addButton);
        inputPanel.add(algorithmSelector);

        add(inputPanel, BorderLayout.NORTH);
        add(new JScrollPane(memoryDisplay), BorderLayout.CENTER);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int page = Integer.parseInt(inputPage.getText());
                    boolean pageFault = algorithm.addPage(page);
                    updateMemoryDisplay(pageFault);
                    inputPage.setText("");
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Digite um numero valido.");
                }
            }
        });

        selectAlgorithm();

    }

    private void selectAlgorithm() {
        String selected = (String) algorithmSelector.getSelectedItem();
        algorithm = algorithms.get(selected);
        memoryDisplay.setText("");
    }

    private void updateMemoryDisplay(boolean pageFault) {
        StringBuilder sb = new StringBuilder();
        sb.append("Memoria: ").append(algorithm.getMemory()).append("\n");
        sb.append("Page Fault: ").append(pageFault ? "Sim" : "Nao").append("\n");
        memoryDisplay.setText(sb.toString());
    }
    
    
}
