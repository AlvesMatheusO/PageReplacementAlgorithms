import javax.swing.SwingUtilities;

public class App {
    public static void main(String[] args) throws Exception {
        
        SwingUtilities.invokeLater(() -> {
            GraphicInterface frame = new GraphicInterface(5);
            frame.setVisible(true); 
        });
    }
}
