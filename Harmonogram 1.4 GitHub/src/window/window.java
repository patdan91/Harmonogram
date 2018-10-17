package window;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import sqloperations.sqloperations;
import window.window.DodajListener;
import window.window.ListaListener;
import window.window.ListaListener2;
import window.window.UsunListener;
import window.window.ZmienListener;

public class window {
	JFrame ramka;
	JPanel panel;
	JPanel panel1;
	JPanel panel2;
	JPanel panel11;
	JPanel panel12;
	JPanel panel21;
	JPanel panel22;
	int tydzien;
	elements e = new elements();
	JScrollPane przewijanie2;
	
	public void makewindow() {
		
		JFrame ramka = new JFrame();
		JPanel panel = new JPanel();
		JPanel panel1 = new JPanel();
		JPanel panel2 = new JPanel();
		JPanel panel11 = new JPanel();
		JPanel panel12 = new JPanel();
		JPanel panel21 = new JPanel();
		JPanel panel22 = new JPanel();
		panel.setBackground(Color.WHITE);
		panel1.setBackground(Color.WHITE);
		panel2.setBackground(Color.WHITE);
		panel11.setBackground(Color.WHITE);
		panel12.setBackground(Color.WHITE);
		panel21.setBackground(Color.WHITE);
		panel22.setBackground(Color.WHITE);
		
		
		
		ramka.getContentPane().setBackground(Color.WHITE);
		ramka.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ramka.getContentPane().add(BorderLayout.NORTH, e.imagelabel);
		ramka.getContentPane().add(BorderLayout.CENTER, panel);
		
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.add(panel1);
		panel.add(panel2);
		
		panel1.setLayout(new BoxLayout(panel1, BoxLayout.Y_AXIS));
		panel1.add(e.wybierztydzien);
		e.wybierztydzien.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		e.tworzliste();
		JScrollPane przewijanie = new JScrollPane(e.listatygodni);
		przewijanie.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		przewijanie.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		e.listatygodni.setBackground(Color.ORANGE);
		przewijanie.setBackground(Color.ORANGE);
		e.listatygodni.setVisibleRowCount(5);
		e.listatygodni.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		e.listatygodni.addListSelectionListener(new ListaListener());
		
		panel1.add(przewijanie);
		przewijanie.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		panel1.add(e.wykorzystanieppowierzchni);
		e.wykorzystanieppowierzchni.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel1.add(e.wykorzystaniepowierzchniprocent);
		e.wykorzystaniepowierzchniprocent.setAlignmentX(Component.CENTER_ALIGNMENT);
	
		panel2.setLayout(new BoxLayout(panel2, BoxLayout.Y_AXIS));
		panel2.add(panel21);
		panel2.add(panel22);
		

		panel21.setLayout(new BoxLayout(panel21, BoxLayout.Y_AXIS));
		panel21.add(e.wybierzprace);
		e.wybierzprace.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		e.tworzliste2();
		JScrollPane przewijanie2 = new JScrollPane(e.listaprac);
		przewijanie2.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		przewijanie2.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		e.listaprac.setBackground(Color.ORANGE);
		przewijanie2.setBackground(Color.ORANGE);
		e.listaprac.setVisibleRowCount(5);
		e.listaprac.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		e.listaprac.addListSelectionListener(new ListaListener2());
		
		panel21.add(przewijanie2);
		przewijanie2.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		panel21.add(e.numerpracy1);
		e.numerpracy1.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		panel21.add(e.tnumerpracy1);
		e.tnumerpracy1.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		panel21.add(e.numertygodnia1);
		e.numertygodnia1.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		panel21.add(e.tnumertygodnia1);
		e.tnumertygodnia1.setAlignmentX(Component.CENTER_ALIGNMENT);
				
		panel21.add(e.powdachu1);
		e.powdachu1.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel21.add(e.tpowdachu1);
		e.tpowdachu1.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		panel21.add(e.informacja);
		e.informacja.setAlignmentX(Component.CENTER_ALIGNMENT);
		e.informacja.setForeground(Color.RED);
				
		panel22.setLayout(new BoxLayout(panel22, BoxLayout.X_AXIS));
		
		panel22.add(e.zmien);
		e.zmien.setAlignmentX(Component.CENTER_ALIGNMENT);
		e.zmien.addActionListener(new ZmienListener());
		
		panel22.add(e.dodaj);
		e.dodaj.setAlignmentX(Component.CENTER_ALIGNMENT);
		e.dodaj.addActionListener(new DodajListener());
		
		panel22.add(e.usun);
		e.usun.setAlignmentX(Component.CENTER_ALIGNMENT);
		e.usun.addActionListener(new UsunListener());
	
		
		ramka.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ramka.setSize(500, 650);
		ramka.setVisible(true);

}
	
	
		class ListaListener implements ListSelectionListener {

		public void valueChanged(ListSelectionEvent le) {
			
			e.informacja.setText(" ");
			
			tydzien = e.listatygodni.getSelectedIndex() + 1;
			sqloperations sql1 = new sqloperations ();
			sql1.sqltydzien = tydzien;
			sql1.obliczprodukcje();
			e.listaprac.setModel(sql1.sqllistaprac);
			e.wykorzystaniepowierzchniprocent.setText(new DecimalFormat("##.##").format(sql1.sqlpowierzchniaprocent));
			e.tnumerpracy1.setText("xxx");
			e.tnumertygodnia1.setText(Integer.toString(tydzien));
			e.tpowdachu1.setText("xxx");
			
		}
		}
		
		class ListaListener2 implements ListSelectionListener {

			public void valueChanged(ListSelectionEvent le2) {
				
				e.informacja.setText(" ");
				
				sqloperations sql1 = new sqloperations();
				sql1.sqlnumerpracy = e.listaprac.getSelectedValue();
				sql1.wybierzpracesql();
				e.tnumerpracy1.setText(sql1.sqlnumerpracy);
				e.tpowdachu1.setText(Integer.toString(sql1.sqlpow));
				e.tnumertygodnia1.setText(Integer.toString(sql1.sqlnrtygodnia));
				
			}
			}
		
		class DodajListener implements ActionListener {
			public void actionPerformed(ActionEvent zdarzenie) {
				
				e.informacja.setText(" ");
				
				sqloperations sql1 = new sqloperations();
				sql1.sqlnumerpracy = e.tnumerpracy1.getText();
				sql1.sqlnrtygodnia = Integer.parseInt(e.tnumertygodnia1.getText());
				sql1.sqlpow= Integer.parseInt(e.tpowdachu1.getText());			
				sql1.sqltydzien = sql1.sqlnrtygodnia;
				sql1.sprawdzprace();
				
				if (sql1.sqlistnieje == true) {
									
				e.informacja.setText(sql1.sqlinformacja);
				e.tnumerpracy1.setText(sql1.sqlnumerpracy);
				e.tnumertygodnia1.setText(Integer.toString(sql1.sqlnrtygodnia));
				e.tpowdachu1.setText(Integer.toString(sql1.sqlpow));
						
				}
				
				
				else {
				
				sql1.obliczprodukcje();
				
				sql1.dodajpracesql();
				
				sql1.obliczprodukcje();
				e.wykorzystaniepowierzchniprocent.setText(new DecimalFormat("##.##").format(sql1.sqlpowierzchniaprocent));
				e.listaprac.setModel(sql1.sqllistaprac);
				e.informacja.setText(sql1.sqlinformacja);
				
				if (sql1.sqlinformacja.equals(" ")) {
				
				e.tnumerpracy1.setText("xxx");
				e.tnumertygodnia1.setText("xxx");
				e.tpowdachu1.setText("xxx");
				}
				
				}
				}
			}
		
		
		class UsunListener implements ActionListener {
			public void actionPerformed(ActionEvent zdarzenie2) {
				
				e.informacja.setText(" ");
				
				sqloperations sql1 = new sqloperations();
				sql1.sqlnumerpracy = e.tnumerpracy1.getText();
				
				sql1.usunpracesql();
				
				sql1.sqltydzien = sql1.sqlnrtygodnia;
				
				
				e.wykorzystaniepowierzchniprocent.setText(new DecimalFormat("##.##").format(sql1.sqlpowierzchniaprocent));
				tydzien = e.listatygodni.getSelectedIndex() + 1;
				sql1.sqltydzien = tydzien;
				sql1.obliczprodukcje();
				e.listaprac.setModel(sql1.sqllistaprac);
				e.wykorzystaniepowierzchniprocent.setText(new DecimalFormat("##.##").format(sql1.sqlpowierzchniaprocent));
				e.tnumerpracy1.setText("xxx");
				e.tnumertygodnia1.setText("xxx");
				e.tpowdachu1.setText("xxx");
			
			
			}
		}
		
		class ZmienListener implements ActionListener {
			public void actionPerformed(ActionEvent zdarzenie2) {
				
				e.informacja.setText(" ");
				
				sqloperations sql1 = new sqloperations();
				
				sql1.sqlnumerpracy = e.tnumerpracy1.getText();
				sql1.sqlnrtygodnia = Integer.parseInt(e.tnumertygodnia1.getText());
				sql1.sqlpow= Integer.parseInt(e.tpowdachu1.getText());
				sql1.sqltydzien = sql1.sqlnrtygodnia;
				sql1.zmienpracesql1();
				System.out.println(sql1.sqlistnieje +"123");
				
				if (sql1.sqlistnieje == true) {
					
					sql1.sprawdzprodukcje();
					
					sql1.zmienpracesql2();
			
				sql1.sqltydzien = sql1.sqlnrtygodnia;
				sql1.obliczprodukcje();
				e.listaprac.setModel(sql1.sqllistaprac);
				e.wykorzystaniepowierzchniprocent.setText(new DecimalFormat("##.##").format(sql1.sqlpowierzchniaprocent));
				e.tnumerpracy1.setText(sql1.sqlnumerpracy);
				e.tpowdachu1.setText(Integer.toString(sql1.sqlpow));
				e.tnumertygodnia1.setText(Integer.toString(sql1.sqlnrtygodnia));
				e.informacja.setText(sql1.sqlinformacja);
				
				}
				else {
					e.informacja.setText("Nie mozna zmieniac numeru pracy / wprowadz nowa prace");
				}
				
		}
}
	

}
