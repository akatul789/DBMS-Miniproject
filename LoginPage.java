import java.sql.*;
import javax.swing.*;
import java.awt.*;
import javax.swing.text.*;
import java.lang.*;
import java.awt.event.*;
class Login extends JFrame implements ActionListener
{
	ImagePanel panel; //backgrd img
	JLabel cardLabel,cvvLabel,pinLabel;
	JFormattedTextField cardField,cvvText;
	JPasswordField pinText;
	JButton loginButton,registerButton ;
	Connection con;   //jdbc con.
	Statement st;     //jdbc con.

	Login()
	{
		panel = new ImagePanel(new ImageIcon("LoginImage.jpg").getImage());
		Font f = new Font("Algerian", Font.PLAIN, 20);
//Title----------------------------------------------------
		setTitle(" ## ATM Transaction ##  --------- Login Page ---------");
		panel.setLayout(null);
		setSize(800, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setResizable(false);
//JDBC Con.--------------------------------------------------
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
//Card-------------------------------------------------------
		cardLabel = new JLabel("Card number  >>");
		cardLabel.setFont(f);
		cardField = new JFormattedTextField();
		cardField.setFont(new Font(Font.MONOSPACED, Font.PLAIN,21));
		try{
		String inputMask = "HHHH-HHHH-HHHH-HHHH";
		cardField.setFormatterFactory(new DefaultFormatterFactory(new MaskFormatter(inputMask)));
		}catch(Exception ex){}
		cardLabel.setBounds(185, 260, 260, 30);
		cardField.setBounds(355, 260, 260, 30);
		panel.add(cardLabel);
		panel.add(cardField);
//CVV---------------------------------------------------------------
		cvvLabel = new JLabel("CVV                     >>");
		cvvText = new JFormattedTextField();
		cvvLabel.setFont(f);
		cvvText.setFont(new Font(Font.MONOSPACED, Font.PLAIN,21));
		try {
		String inputMask = "HHH";
		cvvText.setFormatterFactory(new DefaultFormatterFactory(new MaskFormatter(inputMask)));
		}catch (Exception ex) {}
		cvvLabel.setBounds(185,310 , 190, 30);
		cvvText.setBounds(355,310,50, 30);
		panel.add(cvvLabel);
		panel.add(cvvText);
//PIN--------------------------------------------------------------------
		pinLabel = new JLabel("PIN                      >>");
		pinText = new JPasswordField(4);
		pinLabel.setFont(f);
		pinText.setFont(new Font(Font.MONOSPACED, Font.PLAIN,21));
		pinLabel.setBounds(185, 360 , 200, 30);
		pinText.setBounds(355, 360, 65, 30);
		panel.add(pinLabel);
		panel.add(pinText);

		PlainDocument document = (PlainDocument)pinText.getDocument();
		document.setDocumentFilter(new DocumentFilter(){
		public void replace(DocumentFilter.FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
		String string = fb.getDocument().getText(0, fb.getDocument().getLength()) + text;
                if(string.length() <= 4){super.replace(fb, offset, length, text, attrs);}
		}});
//Buttons-----------------------------------------------------------------------
		loginButton = new JButton("Login");
		registerButton = new JButton("Register");
		loginButton.setFont(f);
		registerButton.setFont(f);
		loginButton.setBounds(185, 430, 110, 40);
		panel.add(loginButton);
		registerButton.setBounds(485, 430, 130, 40);
		panel.add(registerButton);  
//--------------------------------------------------------------------------------
		add(panel);
		loginButton.addActionListener(this);
		registerButton.addActionListener(this);
	}
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource() == loginButton)
		{
			String cardno_dash=cardField.getText();
			String cardno = cardno_dash.replaceAll("-", "");
			String cvvno=cvvText.getText();
			String pinno=pinText.getText();
			System.out.println(cardno + " " +cvvno+ "   " + pinno);
			try{
				ResultSet r=st.executeQuery("select card_no from card where cvv="+cvvno +" and pin="+pinno+"and card_no="+cardno);
				r.next();
				String dbcardno= r.getString("card_no");
				if(cardno.equals(dbcardno))
				{
			 		DetailsPage page=new DetailsPage(cardno_dash);
		 			page.setVisible(true);
					this.setVisible(false);
				}
				System.out.println(dbcardno);
			   }
			   catch(Exception ez)
			   {
				System.out.println("Enter the valid Card No. or pin");
				JOptionPane.showMessageDialog(this,"      Incorrect Card No. or PIN   ! \n\n          Please try Again  ! ! \n",
				"  Error Logging In ",JOptionPane.ERROR_MESSAGE);
			   }
		}
		else if(e.getSource() == registerButton)
		{
			RegisterPage page=new RegisterPage();
			page.setVisible(true);
			this.setVisible(false);
		}
	}
}

class LoginPage
{
	public static void main(String arg[])
	{
		try
		{ 
			Login frame=new Login();
			frame.setSize(800,600);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setVisible(true);
			frame.setResizable(false);
		}
		catch(Exception e)
		{JOptionPane.showMessageDialog(null, e.getMessage());}
	}
}

class ImagePanel extends JPanel
{
	private Image img;
	public ImagePanel(String img)
	{
		this(new ImageIcon(img).getImage());
	}
	public ImagePanel(Image img)
	{
		this.img = img;
		Dimension size = new Dimension(img.getWidth(null), img.getHeight(null));
		setPreferredSize(size);
		setMinimumSize(size);
		setMaximumSize(size);
		setSize(size);
		setLayout(null);
	}
	public void paintComponent(Graphics g)
	{
		g.drawImage(img, 0, 0, null);
	}
}