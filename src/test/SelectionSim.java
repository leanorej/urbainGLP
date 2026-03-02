package test;
import javax.swing.*; 
import java.awt.*; 
import java.awt.event.*; 

public class SelectionSim extends JFrame implements ItemListener { 

    // frame 
    static JFrame frame; 
    // combobox 
    static JComboBox combobox; 
    // label 

    public static void main(String[] args) {
        // créer un nouvea frame 
        frame = new JFrame("frame"); 
  
        // créer un objet
        SelectionSim obj = new SelectionSim(); 
  
        // tableau de chaînes contenant des langages
        String s1[] = { "Pierre", "Maurizio", "Mimmo", "Dennis", "Rudy" }; 
  
        // créer une case à cocher
        combobox = new JComboBox(s1); 
  
        // ajouter ItemListener
        combobox.addItemListener(obj); 
  
        // créer un nouveau panneau
        JPanel p = new JPanel(); 

        // ajouter combobox et labels au panneau

        p.add(combobox); 


        // ajouter le panneau au frame
        frame.add(p); 

        // définir la taille du frame 
        frame.setSize(400, 200); 

        frame.show(); 
    }

	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub
		
	} 
}