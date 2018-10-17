package sqloperations;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.DefaultListModel;

public class sqloperations {

	public int sqltydzien = 0;
	public int sqlpowierzchnia = 0;
	public double sqlpowierzchniaprocent;
	
	public int psqlpowierzchnia = 0;
	public double psqlpowierzchniaprocent;
	public String test1 = "Dziala";
	public DefaultListModel<String> sqllistaprac;
	
	public String sqlnumerpracy = "Nie wybrano pracy";
	public int sqlnrtygodnia = 0;
	public int sqlpow = 0;
	public double dsqlpow = 0;
	
	public boolean sqlistnieje = false;
	
	public String sqlinformacja = new String (" ");
	
	public void obliczprodukcje() {
		
		sqllistaprac = new DefaultListModel<String>();
		
		Connection con = null;
	    Statement st = null;
	    ResultSet rs = null;

	    String url = "jdbc:mysql://localhost:3306/wiazar_harmonogram";
	    String user = "root";
	    String password = "wiazar";

	    try {
	        Class.forName("com.mysql.jdbc.Driver");
	        con = DriverManager.getConnection(url, user, password);
	        System.out.println("Database connected!");
	        st = con.createStatement();
	        rs = st.executeQuery("SELECT * FROM harmonogram_2019 WHERE tydzien = '" +sqltydzien +"';");

	        while (rs.next()) {
	        	
	        
	        	sqllistaprac.addElement(rs.getString(1));
	        
	        	
	        }
	        if (sqllistaprac.isEmpty()) {
	        	sqllistaprac.addElement("Brak zapisanych prac");
	        }
	        
	        st = con.createStatement();
	        rs = st.executeQuery("SELECT SUM(powierzchnia) FROM harmonogram_2019 WHERE tydzien = '" +sqltydzien +"';");
	        
	        
	        while(rs.next()){
	        	
	     
	        	sqlpowierzchnia = rs.getInt(1);
	        	;
	        	
	        }
	        rs.close();
	        	   
	        sqlpowierzchniaprocent = sqlpowierzchnia;
	        sqlpowierzchniaprocent= sqlpowierzchniaprocent / 900 *100 ;
	       
	    }

	     catch (SQLException ex) {
	        
	    	test1 = "blad1";

	    } 
	    catch (ClassNotFoundException e) {
	    	
	    	test1 = "blad2";
		} 
	    	    
	} 
	
	public void wybierzpracesql() {
		

		Connection con = null;
	    Statement st = null;
	    ResultSet rs = null;

	    String url = "jdbc:mysql://localhost:3306/wiazar_harmonogram";
	    String user = "root";
	    String password = "wiazar";

	    try {
	        Class.forName("com.mysql.jdbc.Driver");
	        con = DriverManager.getConnection(url, user, password);
	        System.out.println("Database connected!");
	        st = con.createStatement();
	        rs = st.executeQuery("SELECT tydzien FROM harmonogram_2019 WHERE numer_pracy ='" +sqlnumerpracy +"';");

	        while (rs.next()) {
	        sqlnrtygodnia = rs.getInt(1);	
	        }
	        
	        rs = st.executeQuery("SELECT powierzchnia FROM harmonogram_2019 WHERE numer_pracy ='" +sqlnumerpracy +"';");

	        while (rs.next()) {
	        sqlpow = rs.getInt(1);	
	        }
	
	    }

	     catch (SQLException ex) {
	        
	    	test1 = "blad3";

	    } 
	    catch (ClassNotFoundException e) {
	    	
	    	test1 = "blad4";
		} 
		
	}

	public void dodajpracesql() {
	
		dsqlpow = sqlpow;
		dsqlpow = dsqlpow/900 *100;		
		sqlpowierzchniaprocent = sqlpowierzchniaprocent + dsqlpow;
	
		if (sqlpowierzchniaprocent<85) {
		

		Connection con = null;
	    Statement st = null;
	    ResultSet rs = null;

	    String url = "jdbc:mysql://localhost:3306/wiazar_harmonogram";
	    String user = "root";
	    String password = "wiazar";

	    try {
	        Class.forName("com.mysql.jdbc.Driver");
	        con = DriverManager.getConnection(url, user, password);
	        System.out.println("Database connected!");
	        st = con.createStatement();
	        st.executeUpdate("INSERT INTO harmonogram_2019 (numer_pracy, tydzien, powierzchnia) VALUES ('" + sqlnumerpracy +"'," + sqlnrtygodnia +"," + sqlpow +");");
	        
	    }

	     catch (SQLException ex) {
	        
	    	test1 = "blad5";

	    } 
	    catch (ClassNotFoundException e) {
	    	
	    	test1 = "blad6";
		} 
		}
		else {
			
			sqlinformacja = "Dodaj w innym, wolnym tygodniu";
					
		}
		
	}
	
	public void usunpracesql() {
			
			Connection con = null;
		    PreparedStatement pst = null;
		    ResultSet rs = null;

		    String url = "jdbc:mysql://localhost:3306/wiazar_harmonogram";
		    String user = "root";
		    String password = "wiazar";

		    try {
		        Class.forName("com.mysql.jdbc.Driver");
		        con = DriverManager.getConnection(url, user, password);
		        System.out.println("Database connected!");
		        pst = con.prepareStatement("DELETE FROM harmonogram_2019 WHERE numer_pracy ='" +sqlnumerpracy +"';"); 
		        pst.executeUpdate();
		        System.out.println("Usunieto");
		     
		    }

		     catch (SQLException ex) {
		        
		    	test1 = "blad7";

		    } 
		    catch (ClassNotFoundException e) {
		    	
		    	test1 = "blad8";
			} 
			
		}
		
	public void zmienpracesql1() {

		
		Connection con = null;
	    PreparedStatement pst = null;
	    ResultSet rs = null;

	    String url = "jdbc:mysql://localhost:3306/wiazar_harmonogram";
	    String user = "root";
	    String password = "wiazar";
	
	    try {
	    	
	    
	        Class.forName("com.mysql.jdbc.Driver");
	        con = DriverManager.getConnection(url, user, password);
	        System.out.println("Database connected!");
	        
	        pst = con.prepareStatement("SELECT * FROM harmonogram_2019 WHERE numer_pracy ='" +sqlnumerpracy +"';"); 
	        
	   
	        rs = pst.executeQuery();

	        if (rs.next()) {
	        	
	        	sqlistnieje = true;
	        	System.out.println(sqlnumerpracy + sqlistnieje);
	            
	        } else {

	        	
	        	System.out.println(sqlnumerpracy + sqlistnieje);
	        }

	   
	    }

	     catch (SQLException ex) {
	        
	    	test1 = "blad9";

	    } 
	    catch (ClassNotFoundException e) {
	    	
	    	test1 = "blad10";
		} 
		
	}
	
	public void sprawdzprodukcje() {
		
		Connection con = null;
	    Statement st = null;
	    ResultSet rs = null;

	    String url = "jdbc:mysql://localhost:3306/wiazar_harmonogram";
	    String user = "root";
	    String password = "wiazar";
	    System.out.println("Sprawdzenie1");
	    
	    try {
	        Class.forName("com.mysql.jdbc.Driver");
	        con = DriverManager.getConnection(url, user, password);
	        System.out.println("Database connected!2");
	        st = con.createStatement();
	        rs = st.executeQuery("SELECT SUM(powierzchnia) FROM harmonogram_2019 WHERE tydzien = '" +sqltydzien +"' AND numer_pracy <> '" +sqlnumerpracy+ "' ;");
	       	        
	        while(rs.next()){
	        	
	     
	        	psqlpowierzchnia = rs.getInt(1);
	        	
	        }
	        rs.close();
	        
	        psqlpowierzchniaprocent = psqlpowierzchnia;        
	        psqlpowierzchniaprocent= psqlpowierzchniaprocent / 900 *100 ;    
		}
		


catch (SQLException ex) {
   
	test1 = "blad11";

} 
catch (ClassNotFoundException e) {
	
	test1 = "blad21";
} 
	}	    

	public void sprawdzprace() {
		
		Connection con = null;
	    Statement st = null;
	    ResultSet rs = null;

	    String url = "jdbc:mysql://localhost:3306/wiazar_harmonogram";
	    String user = "root";
	    String password = "wiazar";

	    try {
	        Class.forName("com.mysql.jdbc.Driver");
	        con = DriverManager.getConnection(url, user, password);
	        System.out.println("Database connected!");
	        st = con.createStatement(); 
	        rs = st.executeQuery("SELECT numer_pracy FROM harmonogram_2019 WHERE numer_pracy ='" +sqlnumerpracy +"';");

	        if (rs.next()) {
	        	
	        	sqlistnieje = true;
	        	sqlinformacja = "Praca ju¿ istnieje";
	        }
	        
	        
	
	    }

	     catch (SQLException ex) {
	        
	    	test1 = "blad3";

	    } 
	    catch (ClassNotFoundException e) {
	    	
	    	test1 = "blad4";
		} 
		
	}
		
	
	public void zmienpracesql2() {
		
		System.out.println(psqlpowierzchniaprocent);
		System.out.println(sqlpow);
		dsqlpow = sqlpow;
		dsqlpow = dsqlpow /900 *100;
		System.out.println(dsqlpow);
		psqlpowierzchniaprocent = psqlpowierzchniaprocent + dsqlpow;
		System.out.println(psqlpowierzchniaprocent);
		
		if (psqlpowierzchniaprocent<85) {
		
		Connection con = null;
	    PreparedStatement pst = null;
	    
	    String url = "jdbc:mysql://localhost:3306/wiazar_harmonogram";
	    String user = "root";
	    String password = "wiazar";

	    try {
	        Class.forName("com.mysql.jdbc.Driver");
	        con = DriverManager.getConnection(url, user, password);
	        System.out.println("Database connected!");
	        pst = con.prepareStatement("UPDATE HARMONOGRAM_2019 SET tydzien =" + sqlnrtygodnia + ", powierzchnia =" + sqlpow + " WHERE numer_pracy ='" + sqlnumerpracy +"';");
	        pst.executeUpdate();
	        pst.close();
	     
	    }

	     catch (SQLException ex) {
	        
	    	test1 = "blad7";

	    } 
	    catch (ClassNotFoundException e) {
	    	
	    	test1 = "blad8";
		} 
		}
		else {
			
			sqlinformacja = "Dodaj w innym, wolnym tygodniu";
			 System.out.println(psqlpowierzchniaprocent);
		}
	}
}

	

		

	

