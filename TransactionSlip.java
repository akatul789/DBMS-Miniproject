import javax.swing.*;
import java.awt.*;
import javax.swing.text.*;
import java.lang.*;
import java.awt.event.*;
import java.sql.*;
import java.util.*;
class TransactionSlip extends JFrame implements ActionListener
 {
	JButton backbutton;
	Connection con; //jdbc
	Statement st; //jdbc
	ImagePanel panel; //backgrd img
	String cardno_global;
	TransactionSlip(String cardno_dash)
	{
		cardno_global=cardno_dash;
		int xcor=120,xsqlcor=270;
		panel = new ImagePanel(new ImageIcon("RegisterImage.jpg").getImage());
		Font f = new Font("Calibri", Font.BOLD, 18);
		panel.setLayout(null);
		setTitle("  ------------------- Transaction Slip -------------------");
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

		try{
			String cardno = cardno_dash.replaceAll("-", "");
			ResultSet r=st.executeQuery("select b.bank_name from bank b,belongsto x,atm_mc a,provides2 y,card c where b.bank_id=x.bank_id and x.atm_id=a.atm_id and a.atm_id=y.atm_id and y.card_no=c.card_no and c.card_no="+cardno);
			r.next();
			String bname= r.getString("bank_name");
			r=st.executeQuery("select x.branch from atm_mc x,provides2 y,card c where c.card_no=y.card_no and y.atm_id=x.atm_id and c.card_no="+cardno);
			r.next();
			String atmbran= r.getString("branch");
			JLabel bnameLabel =new JLabel(bname+" Of "+atmbran);
			bnameLabel.setFont(new Font("Calibri", Font.BOLD,30));
			bnameLabel.setBounds(110,30,500,50);
			panel.add(bnameLabel);
		   }
		 catch(Exception ez)
		   {
			System.out.println("Enter the valid Card No. or pin");
			JOptionPane.showMessageDialog(this,"      Incorrect Card No. or PIN   ! \n\n          Please try Again  ! ! \n",
			"  Error Logging In ",JOptionPane.ERROR_MESSAGE);
		   }


		JLabel dash1 =new JLabel("------------------------------------------------------------");
		dash1.setFont(new Font("Calibri", Font.BOLD,22));
		dash1.setBounds(80,65,600,22);
		panel.add(dash1);

		JLabel arrow;
		for(int i=0,z=100;i<7;i++)
		{
			arrow=new JLabel(">>");
			arrow.setFont(f);
			arrow.setBounds(280,z,30,25);
			z=z+40;
			panel.add(arrow);
		}
			
//Labels-------------------------------------------------------

		JLabel cardLabel = new JLabel("TRANSACTION ID");
		cardLabel.setFont(f);
		cardLabel.setBounds(xcor,100, 200, 25);
		panel.add(cardLabel);

		JLabel typeLabel = new JLabel("DATE");
		typeLabel.setFont(f);
		typeLabel.setBounds(xcor,140, 120, 25);
		panel.add(typeLabel);

		JLabel accnoLabel = new JLabel("TIME");
		accnoLabel.setFont(f);
		accnoLabel.setBounds(xcor,180, 120, 25);
		panel.add(accnoLabel);


		JLabel balLabel = new JLabel("ATM ID");
		balLabel.setFont(f);
		balLabel.setBounds(xcor,220, 250, 25);
		panel.add(balLabel);

		JLabel lastwithdrawLabel = new JLabel("CARD NO.");
		lastwithdrawLabel.setFont(f);
		lastwithdrawLabel.setBounds(xcor,260, 120, 25);
		panel.add(lastwithdrawLabel);

		JLabel ccidLabel = new JLabel("TXN AMOUNT");
		ccidLabel.setFont(f);
		ccidLabel.setBounds(xcor,300, 220, 25);
		panel.add(ccidLabel);

		JLabel RemLabel = new JLabel("REMARKS");
		RemLabel.setFont(f);
		RemLabel.setBounds(xcor,340, 220, 25);
		panel.add(RemLabel);

		JLabel dash2 =new JLabel("------------------------------------------------------------");
		dash2.setFont(new Font("Calibri", Font.BOLD,22));
		dash2.setBounds(80,410,600,22);
		panel.add(dash2);

		JLabel note1Label =new JLabel("PLEASE CONTACT BRANCH MANAGER");
		note1Label.setFont(new Font("Calibri", Font.BOLD,22));
		note1Label.setBounds(xcor,370,600,25);
		panel.add(note1Label);

		JLabel note3Label =new JLabel("FOR CLARIFICATIONS");
		note3Label.setFont(new Font("Calibri", Font.BOLD,22));
		note3Label.setBounds(xcor,395,600,25);
		panel.add(note3Label);

		//JLabel note1Label = new JLabel("NOTE - Please deposit $2500 to the Main Desk as");
		//note1Label.setFont(new Font("Segoe UI Black", Font.PLAIN, 14));
		//note1Label.setBounds(xcor,420, 390, 25);
		//panel.add(note1Label);

		JLabel note2Label = new JLabel("THANK YOU FOR BANKING WITH US");
		note2Label.setFont(new Font("Segoe UI Black", Font.PLAIN, 20));
		note2Label.setBounds(110,430, 600, 25);
		panel.add(note2Label);
//--------------------------------------------------------------------------------------

		try{
			String cardno = cardno_dash.replaceAll("-", "");
			ResultSet r=st.executeQuery("select x.trans_id from transactions x,performs y,customer z,has h,card c where x.trans_id=y.trans_id and y.c_id=z.c_id and h.c_id=z.c_id and h.card_no=c.card_no and c.card_no="+cardno);
			r.next();
			String tranid= r.getString("trans_id");
			JLabel tranidLabel =new JLabel(tranid);
			tranidLabel.setFont(f);
			tranidLabel.setBounds(320,100,100,25);
			panel.add(tranidLabel);

			r=st.executeQuery("select x.datez from transactions x,performs y,customer z,has h,card c where x.trans_id=y.trans_id and y.c_id=z.c_id and h.c_id=z.c_id and h.card_no=c.card_no and c.card_no="+cardno);
			r.next();
			String trandate= r.getString("datez");
			JLabel trandateLabel =new JLabel(trandate);
			trandateLabel.setFont(f);
			trandateLabel.setBounds(320,140,100,25);
			panel.add(trandateLabel);


			r=st.executeQuery("select x.timez from transactions x,performs y,customer z,has h,card c where x.trans_id=y.trans_id and y.c_id=z.c_id and h.c_id=z.c_id and h.card_no=c.card_no and c.card_no="+cardno);
			r.next();
			String trantime= r.getString("timez");
			JLabel trantimeLabel =new JLabel(trantime);
			trantimeLabel.setFont(f);
			trantimeLabel.setBounds(320,180,100,25);
			panel.add(trantimeLabel);


			r=st.executeQuery("select x.atm_id from atm_mc x,provides2 y,card c where c.card_no=y.card_no and y.atm_id=x.atm_id and c.card_no="+cardno);
			r.next();
			String atmid= r.getString("atm_id");
			JLabel atmidLabel =new JLabel(atmid);
			atmidLabel.setFont(f);
			atmidLabel.setBounds(320,220,100,25);
			panel.add(atmidLabel);

			JLabel cardnoLabel =new JLabel("XXXX-XXXX-XXXX-"+cardno.substring(12));
			cardnoLabel.setFont(f);
			cardnoLabel.setBounds(320,260,250,25);
			panel.add(cardnoLabel);


			r=st.executeQuery("select x.withdraw_amt from transactions x,performs y,customer z,has h,card c where x.trans_id=y.trans_id and y.c_id=z.c_id and h.c_id=z.c_id and h.card_no=c.card_no and c.card_no="+cardno);
			r.next();
			String wd= r.getString("withdraw_amt");
			JLabel wdLabel =new JLabel("$ "+wd);
			wdLabel.setFont(f);
			wdLabel.setBounds(320,300,100,25);
			panel.add(wdLabel);

			JLabel remLabel =new JLabel("NONE");
			remLabel.setFont(f);
			remLabel.setBounds(320,340,100,25);
			panel.add(remLabel);


		   }
		 catch(Exception ez)
		   {
			System.out.println("Enter the valid Card No. or pin");
			JOptionPane.showMessageDialog(this,"      Not Available \n\n          Please contact Adminstrator  ! ! \n",
			"  Error Logging In ",JOptionPane.ERROR_MESSAGE);
		   }

//Buttons---------------------------------------------------------------------------------------------
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
		TransactionSlip frame=new TransactionSlip("9998-8899-9888-9999");
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
			DetailsPage p=new DetailsPage(cardno_global);
			p.setVisible(true);
		}	
	
	}
}
