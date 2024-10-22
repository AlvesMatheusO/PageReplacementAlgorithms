import javax.swing.SwingUtilities;

public class App {
    public static void main(String[] args) throws Exception {
        
        SwingUtilities.invokeLater(() -> {
            //Definir o tamanho do vetor representando a capacidade da memori
            GraphicInterface frame = new GraphicInterface(5);
            frame.setVisible(true); 
        });
    }
}
