import javax.swing.*;
import java.awt.*;
import javax.swing.text.*;
import java.lang.*;
import java.awt.event.*;
import java.sql.*;
import java.util.*;
class RegisterPage extends JFrame implements ActionListener
 {
	JButton logoutbutton,backbutton;
	Connection con; //jdbc
	Statement st; //jdbc
	ImagePanel panel; //backgrd img
	JFormattedTextField phnoField,cardField,cvvText,cexpiryField;
	JFormattedTextField fnameField,lnameField,caddField,ctypeField,acctypeField;
	JPasswordField pinText,cpinText;

	RegisterPage()
	{
		int xcor=100,xsqlcor=270;
		panel = new ImagePanel(new ImageIcon("RegisterImage.jpg").getImage());
		Font f = new Font("Calibri", Font.BOLD, 14);
		panel.setLayout(null);
		setTitle("  ----------------- Registration of User -----------------");
		setSize(600, 570);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setResizable(false);
//JDBC Con.----------------------------------------------------------------------
		try{
		String driverclass="oracle.jdbc.driver.OracleDriver";
		String url="jdbc:oracle:thin:@localhost:1521:orcl2";
		String user="B046";
		String pass="atulakskk";
		Class.forName(driverclass);
		System.out.println("driver loaded....");
		con=DriverManager.getConnection(url,user,pass);
		System.out.println("connected");
		st=con.createStatement();
		}catch(Exception x){}
//Headings------------------------------------------
		JLabel accdet =new JLabel("Personal Details ->");
		accdet.setFont(new Font("Calibri", Font.BOLD,21));
		accdet.setBounds(xcor,30,500,22);
		panel.add(accdet);

		JLabel userdet =new JLabel("Card & Account Details ->");
		userdet.setFont(new Font("Calibri", Font.BOLD,21));
		userdet.setBounds(xcor,180,500,22);
		panel.add(userdet);

		//JLabel atmdet =new JLabel("ATM Details ->");
		//atmdet.setFont(new Font("Calibri", Font.BOLD,21));
		//atmdet.setBounds(xcor,440,500,22);
		//panel.add(atmdet);

		JLabel arrow;
		for(int i=0,z=60;i<11;i++)
		{
			arrow=new JLabel(">>");
			arrow.setFont(f);
			arrow.setBounds(220,z,30,25);
			if(z==150)
			z=210-30;
			else if(z==400)
			z=470-30;
			z=z+30;
			panel.add(arrow);
		}
			
//Labels-------------------------------------------------------

		JLabel cardLabel = new JLabel("First Name");
		cardLabel.setFont(f);
		cardLabel.setBounds(xcor,60, 120, 25);
		panel.add(cardLabel);

		JLabel expLabel = new JLabel("Last name");
		expLabel.setFont(f);
		expLabel.setBounds(xcor,90, 120, 25);
		panel.add(expLabel);

		JLabel typeLabel = new JLabel("Phone number");
		typeLabel.setFont(f);
		typeLabel.setBounds(xcor,120, 120, 25);
		panel.add(typeLabel);

		JLabel accnoLabel = new JLabel("Address");
		accnoLabel.setFont(f);
		accnoLabel.setBounds(xcor,150, 120, 25);
		panel.add(accnoLabel);


		JLabel balLabel = new JLabel("Card Number");
		balLabel.setFont(f);
		balLabel.setBounds(xcor,210, 250, 25);
		panel.add(balLabel);

		JLabel lastwithdrawLabel = new JLabel("Card Type");
		lastwithdrawLabel.setFont(f);
		lastwithdrawLabel.setBounds(xcor,240, 120, 25);
		panel.add(lastwithdrawLabel);

		JLabel ccidLabel = new JLabel("CVV");
		ccidLabel.setFont(f);
		ccidLabel.setBounds(xcor,270, 120, 25);
		panel.add(ccidLabel);

		JLabel cnameLabel = new JLabel("Expiry");
		cnameLabel.setFont(f);
		cnameLabel.setBounds(xcor,300, 120, 25);
		panel.add(cnameLabel);

		JLabel atmidLabel = new JLabel("Account Type");
		atmidLabel.setFont(f);
		atmidLabel.setBounds(xcor,330, 120, 25);
		panel.add(atmidLabel);

		JLabel cphnLabel = new JLabel("PIN");
		cphnLabel.setFont(f);
		cphnLabel.setBounds(xcor,360, 120, 25);
		panel.add(cphnLabel);

		JLabel caddLabel = new JLabel("Confirm PIN");
		caddLabel.setFont(f);
		caddLabel.setBounds(xcor,390, 120, 25);
		panel.add(caddLabel);

		JLabel note1Label = new JLabel("NOTE - Please deposit $2500 to the Main Desk as");
		note1Label.setFont(new Font("Segoe UI Black", Font.PLAIN, 14));
		note1Label.setBounds(xcor,420, 390, 25);
		panel.add(note1Label);

		JLabel note2Label = new JLabel("initial Fee to Activate your account.");
		note2Label.setFont(new Font("Segoe UI Black", Font.PLAIN, 14));
		note2Label.setBounds(xcor+55,435, 390, 25);
		panel.add(note2Label);

//Text Fields---------------------------------------------------------------------------------
	//fnameField-------------
		fnameField = new JFormattedTextField();
		fnameField.setFont(new Font(Font.MONOSPACED, Font.PLAIN,14));
		//try {
		//String inputMask = "AAAAAAAAAA";
		//fnameField.setColumns(10);
		//}catch (Exception ex) {}
		fnameField.setBounds(xsqlcor,60,100, 25);
		panel.add(fnameField);

	//lnameField-----------
		lnameField = new JFormattedTextField();
		lnameField.setFont(new Font(Font.MONOSPACED, Font.PLAIN,14));
		//try {
		//String inputMask = "AAAAAAAAAA";
		//lnameField.setFormatterFactory(new DefaultFormatterFactory(new MaskFormatter(inputMask)));
		//}catch (Exception ex) {}
		lnameField.setBounds(xsqlcor,90,100, 25);
		panel.add(lnameField);

	//phnoField----------------
		phnoField = new JFormattedTextField();
		phnoField.setFont(new Font(Font.MONOSPACED, Font.PLAIN,14));
		try {
		String inputMask = "HHHHHHHHHH";
		phnoField.setFormatterFactory(new DefaultFormatterFactory(new MaskFormatter(inputMask)));
		}catch (Exception ex) {}
		phnoField.setBounds(xsqlcor,120,100, 25);
		panel.add(phnoField);

	//caddField----------
		caddField = new JFormattedTextField();
		caddField.setFont(new Font(Font.MONOSPACED, Font.PLAIN,14));
		//try {
		//String inputMask = "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA";
		//caddField.setFormatterFactory(new DefaultFormatterFactory(new MaskFormatter(inputMask)));
		//}catch (Exception ex) {}
		caddField.setBounds(xsqlcor,150,160, 25);
		panel.add(caddField);



	//cardField-------------
		cardField = new JFormattedTextField();
		cardField.setFont(new Font(Font.MONOSPACED, Font.PLAIN,14));
		try{
		String inputMask = "HHHH-HHHH-HHHH-HHHH";
		cardField.setFormatterFactory(new DefaultFormatterFactory(new MaskFormatter(inputMask)));
		}catch(Exception ex){}
		cardField.setBounds(xsqlcor, 210, 160, 25);
		panel.add(cardField);
	//ctypeField--------

		ctypeField = new JFormattedTextField();
		ctypeField.setFont(new Font(Font.MONOSPACED, Font.PLAIN,14));
		//try {
		//String inputMask = "AAAAAAAAAAAAAAAAAAAA";
		//ctypeField.setFormatterFactory(new DefaultFormatterFactory(new MaskFormatter(inputMask)));
		//}catch (Exception ex) {}
		ctypeField.setBounds(xsqlcor,240,160, 25);
		panel.add(ctypeField);
	//CVV---------------------------------------------------------------------------------------------------
		cvvText = new JFormattedTextField();
		cvvText.setFont(new Font(Font.MONOSPACED, Font.PLAIN,14));
		try {
		String inputMask = "HHH";
		cvvText.setFormatterFactory(new DefaultFormatterFactory(new MaskFormatter(inputMask)));
		}catch (Exception ex) {}
		cvvText.setBounds(xsqlcor,270,35, 25);
		panel.add(cvvText);

	//cexpiryField-------------
		cexpiryField = new JFormattedTextField();
		cexpiryField.setFont(new Font(Font.MONOSPACED, Font.PLAIN,14));
		try{
		String inputMask = "HH-HH-HHHH";
		cexpiryField.setFormatterFactory(new DefaultFormatterFactory(new MaskFormatter(inputMask)));
		}catch(Exception ex){}
		cexpiryField.setBounds(xsqlcor, 300, 90, 25);
		panel.add(cexpiryField);
	//PIN---------------------------------------------------------------------------------------------------
		pinText = new JPasswordField(4);
		pinText.setFont(new Font(Font.MONOSPACED, Font.PLAIN,14));
		pinText.setBounds(xsqlcor, 360, 45, 25);
		panel.add(pinText);
		PlainDocument document = (PlainDocument)pinText.getDocument();
		document.setDocumentFilter(new DocumentFilter(){
		public void replace(DocumentFilter.FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
		String string = fb.getDocument().getText(0, fb.getDocument().getLength()) + text;
                if(string.length() <= 4){super.replace(fb, offset, length, text, attrs);}
		}});
		cpinText = new JPasswordField(4);
		cpinText.setFont(new Font(Font.MONOSPACED, Font.PLAIN,14));
		cpinText.setBounds(xsqlcor, 390, 45, 25);
		panel.add(cpinText);
		PlainDocument document1 = (PlainDocument)cpinText.getDocument();
		document1.setDocumentFilter(new DocumentFilter(){
		public void replace(DocumentFilter.FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
		String string = fb.getDocument().getText(0, fb.getDocument().getLength()) + text;
                if(string.length() <= 4){super.replace(fb, offset, length, text, attrs);}
		}});
	//acctypeField------------------------
		acctypeField = new JFormattedTextField();
		cvvText.setFont(new Font(Font.MONOSPACED, Font.PLAIN,14));
		//try {
		//String inputMask = "AAAAAAAAAAAAAAAAAAAA";
		//acctypeField.setFormatterFactory(new DefaultFormatterFactory(new MaskFormatter(inputMask)));
		//}catch (Exception ex) {}
		acctypeField.setBounds(xsqlcor,330,160, 25);
		panel.add(acctypeField);


//Buttons---------------------------------------------------------------------------------------------

		logoutbutton= new JButton("Sumbit");
		logoutbutton.setFont(f);
		logoutbutton.setBounds(460, 470, 100, 35);
		panel.add(logoutbutton); 
		logoutbutton.addActionListener(this);
		backbutton= new JButton("Back");
		backbutton.setFont(f);
		backbutton.setBounds(30, 470, 100, 35);
		panel.add(backbutton); 
		backbutton.addActionListener(this);
		add(panel);

	}


	public static void main(String arg[])
	{
		try
		{
		RegisterPage frame=new RegisterPage();
		frame.setSize(600, 570);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setResizable(false);
   		}
   		catch(Exception e)
   		{JOptionPane.showMessageDialog(null, e.getMessage());}
   	}

	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource() == backbutton)
		{
			this.setVisible(false);
			Login p=new Login();
			p.setVisible(true);
		}
		else if(e.getSource() == logoutbutton)
		{
			String cardno_dash=cardField.getText();
			String cardno = cardno_dash.replaceAll("-", "");
			String cvvno=cvvText.getText();
			String pin1=pinText.getText();
			String pin2=cpinText.getText();
			String cexp=cexpiryField.getText();
			String ctype=ctypeField.getText();

			Random rand = new Random();
			int cid = rand.nextInt(799)+200;
			
			String fname=fnameField.getText();
			String lname=lnameField.getText();
			String cadd=caddField.getText();
			String phno=phnoField.getText();

			long accono = rand.nextInt(9999999)+1000000000;
			int bal=2500;
			String addtype=acctypeField.getText();

			long atmid = rand.nextInt(9999999)+50000000;
			String atmbranch="London";
			String atmadd="221 Bakers street,London";

			int bid = rand.nextInt(79999)+20000;
			String bname="ICICI Bank";
			String ifsc="ICIC0000103";
			String badd="221 Bakers street,London";

			System.out.println("insert into card values("+cardno+","+"'"+ctype+"'"+","+cvvno+","+"'"+cexp+"'"+","+pin1+")");
			System.out.println("insert into customer values("+cid+","+"'"+fname+" "+lname+"'"+","+phno+","+"'"+cadd+"'"+")");
			System.out.println("insert into account values("+accono+","+bal+","+"'"+addtype+"'"+")");
			System.out.println("insert into atm_mc values("+atmid+","+"'"+atmbranch+"'"+","+"'"+atmadd+"'"+")");
			System.out.println("insert into bank values("+bid+","+"'"+bname+"'"+","+"'"+ifsc+"'"+","+"'"+badd+"'"+")");

			try{
				if(!(pin1.equals(pin2)))
				{throw new NullPointerException("demo");}
				st.executeUpdate("insert into card values("+cardno+","+"'"+ctype+"'"+","+cvvno+","+"'"+cexp+"'"+","+pin1+")");
				st.executeUpdate("insert into customer values("+cid+","+"'"+fname+" "+lname+"'"+","+phno+","+"'"+cadd+"'"+")");
				st.executeUpdate("insert into account values("+accono+","+bal+","+"'"+addtype+"'"+")");
				st.executeUpdate("insert into atm_mc values("+atmid+","+"'"+atmbranch+"'"+","+"'"+atmadd+"'"+")");
				st.executeUpdate("insert into bank values("+bid+","+"'"+bname+"'"+","+"'"+ifsc+"'"+","+"'"+badd+"'"+")");
				st.executeUpdate("insert into provides values("+cardno+","+accono+")");
				st.executeUpdate("insert into has values("+cid+","+cardno+","+accono+")");
				st.executeUpdate("insert into provides2 values("+atmid+","+cardno+","+accono+")");
				st.executeUpdate("insert into belongsto values("+atmid+","+bid+")");
				st.executeUpdate("insert into performs values("+cid+",0)");

				JOptionPane.showMessageDialog(this,"\n           Account Created !!\nPlease deposit $2500 at main desk !!\n");  

				this.setVisible(false);
				Login p=new Login();
				p.setVisible(true);

			   }
			   catch(Exception ez)
			   {
				System.out.println("Enter the valid Card No. or pin");
				JOptionPane.showMessageDialog(this,"      Incorrect  PIN or Wrong Input inserted !\n           Or Card no. already present !\n           Please check and try Again  ! ! \n",
				"  Error Logging In ",JOptionPane.ERROR_MESSAGE);
			   }

		}	
	
	}
}
