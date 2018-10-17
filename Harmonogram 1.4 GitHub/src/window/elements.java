package window;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTextField;

public class elements {
	
	ImageIcon image1 = new ImageIcon(getClass().getResource("/wiazar.jpg"));
	JLabel imagelabel = new JLabel(image1);
	JLabel wybierztydzien = new JLabel("Wybierz tydzien:");
	String tabelatygodni[] = new String [52];
	JList<String> listatygodni = new JList<String>(tabelatygodni);
	DefaultListModel<String> tabelaprac;
	JList<String> listaprac;
			
	public void tworzliste() {
		
		
		int x=0;
		while (x < 52) {
			
			String y = Integer.toString(x+1);
			tabelatygodni[x] = "Tydzien " + y;
			x++;
		}
	}
	
	JLabel wykorzystanieppowierzchni = new JLabel("Wykorzystanie produkcji [%] :");
	JLabel wykorzystaniepowierzchniprocent = new JLabel("0");
	
	JLabel wybierzprace = new JLabel("Wybierz prace:");
	
	public void tworzliste2() {
		tabelaprac = new DefaultListModel<String>();
		
		tabelaprac.addElement("Brak zapisanych prac");
		listaprac = new JList<String>(tabelaprac);
	}
	
	JLabel numerpracy1 = new JLabel("Numer pracy:");
	JTextField tnumerpracy1 = new JTextField("xxx");
	
	JLabel numertygodnia1 = new JLabel("Numer tygodnia:");
	JTextField tnumertygodnia1 = new JTextField("xxx");
	
	JLabel powdachu1 = new JLabel("Powierzchnia dachu:");
	JTextField tpowdachu1 = new JTextField("xxx");
	
	JButton zmien = new JButton("Zmien");
	
	JLabel dodajprace = new JLabel("Dodaj prace");

	JButton dodaj = new JButton("Dodaj");
	JButton usun = new JButton("Usun");
	
	JLabel informacja = new JLabel (" ");

}
