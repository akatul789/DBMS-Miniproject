import javax.swing.*;
import java.awt.*;
import javax.swing.text.*;
import java.lang.*;
import java.awt.event.*;
import java.sql.*;
class DetailsPage extends JFrame implements ActionListener
 {

	Connection con; //jdbc
	Statement st; //jdbc
	ImagePanel panel; //backgrd img
	JButton logoutbutton,translipBut;
	String cardno_global;
	DetailsPage(String cardno_dash)
	{
		cardno_global=cardno_dash;
		int xcor=100,xsqlcor=270;
		panel = new ImagePanel(new ImageIcon("DetailsImage.jpg").getImage());
		Font f = new Font("Calibri", Font.BOLD, 14);
		String cardno = cardno_dash.replaceAll("-", "");
		panel.setLayout(null);
		setTitle("  ------------------- Account Information -------------------");
		setSize(600, 780);
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
		JLabel accdet =new JLabel("Account Details ->");
		accdet.setFont(new Font("Calibri", Font.BOLD,21));
		accdet.setBounds(xcor,30,500,22);
		panel.add(accdet);

		JLabel userdet =new JLabel("User Details ->");
		userdet.setFont(new Font("Calibri", Font.BOLD,21));
		userdet.setBounds(xcor,280,500,22);
		panel.add(userdet);

		JLabel atmdet =new JLabel("ATM Details ->");
		atmdet.setFont(new Font("Calibri", Font.BOLD,21));
		atmdet.setBounds(xcor,440,500,22);
		panel.add(atmdet);

		JLabel arrow;
		for(int i=0,z=60;i<18;i++)
		{
			arrow=new JLabel(">>");
			arrow.setFont(f);
			arrow.setBounds(220,z,30,25);
			if(z==240)
			z=310-30;
			else if(z==400)
			z=470-30;
			z=z+30;
			panel.add(arrow);
		}
			
//Labels-------------------------------------------------------

		JLabel cardLabel = new JLabel("Card number");
		cardLabel.setFont(f);
		cardLabel.setBounds(xcor,60, 120, 25);
		panel.add(cardLabel);

		JLabel expLabel = new JLabel("Expiry");
		expLabel.setFont(f);
		expLabel.setBounds(xcor,90, 120, 25);
		panel.add(expLabel);

		JLabel typeLabel = new JLabel("Card Type");
		typeLabel.setFont(f);
		typeLabel.setBounds(xcor,120, 120, 25);
		panel.add(typeLabel);

		JLabel accnoLabel = new JLabel("Account No.");
		accnoLabel.setFont(f);
		accnoLabel.setBounds(xcor,150, 120, 25);
		panel.add(accnoLabel);

		JLabel acctypeLabel = new JLabel("Account Type");
		acctypeLabel.setFont(f);
		acctypeLabel.setBounds(xcor,180, 120, 25);
		panel.add(acctypeLabel);

		JLabel balLabel = new JLabel("Balance");
		balLabel.setFont(f);
		balLabel.setBounds(xcor,210, 200, 25);
		panel.add(balLabel);

		JLabel lastwithdrawLabel = new JLabel("Last WithDraw");
		lastwithdrawLabel.setFont(f);
		lastwithdrawLabel.setBounds(xcor,240, 120, 25);
		panel.add(lastwithdrawLabel);

		JLabel ccidLabel = new JLabel("Customer ID");
		ccidLabel.setFont(f);
		ccidLabel.setBounds(xcor,310, 120, 25);
		panel.add(ccidLabel);

		JLabel cnameLabel = new JLabel("Name");
		cnameLabel.setFont(f);
		cnameLabel.setBounds(xcor,340, 120, 25);
		panel.add(cnameLabel);

		JLabel cphnLabel = new JLabel("Phone no.");
		cphnLabel.setFont(f);
		cphnLabel.setBounds(xcor,370, 120, 25);
		panel.add(cphnLabel);

		JLabel caddLabel = new JLabel("Address");
		caddLabel.setFont(f);
		caddLabel.setBounds(xcor,400, 200, 25);
		panel.add(caddLabel);
//---------------------
		JLabel atmidLabel = new JLabel("ATM ID");
		atmidLabel.setFont(f);
		atmidLabel.setBounds(xcor,470, 120, 25);
		panel.add(atmidLabel);

		JLabel atmaddLabel = new JLabel("ATM Address");
		atmaddLabel.setFont(f);
		atmaddLabel.setBounds(xcor,500, 120, 25);
		panel.add(atmaddLabel);

		JLabel atmbranLabel = new JLabel("ATM Branch");
		atmbranLabel.setFont(f);
		atmbranLabel.setBounds(xcor,530, 120, 25);
		panel.add(atmbranLabel);

		JLabel bnameLabel = new JLabel("Bank name");
		bnameLabel.setFont(f);
		bnameLabel.setBounds(xcor,560, 120, 25);
		panel.add(bnameLabel);

		JLabel ifscLabel = new JLabel("IFSC code");
		ifscLabel.setFont(f);
		ifscLabel.setBounds(xcor,590, 120, 25);
		panel.add(ifscLabel);

		JLabel bidLabel = new JLabel("Bank ID");
		bidLabel.setFont(f);
		bidLabel.setBounds(xcor,620, 120, 25);
		panel.add(bidLabel);

		JLabel baddLabel = new JLabel("Bank address");
		baddLabel.setFont(f);
		baddLabel.setBounds(xcor,650, 200, 25);
		panel.add(baddLabel);
//Retriving data from DB---------------------------------------------------------------------------------
		JLabel sqlcardno = new JLabel(cardno_dash);
		sqlcardno.setFont(f);
		sqlcardno.setBounds(xsqlcor,60, 180, 25);
		panel.add(sqlcardno);
		
		try{
//SQL account details-------------------------------------------------------
			ResultSet r=st.executeQuery("select expiry from card where card_no="+cardno);
			r.next();
			String exp= r.getString("expiry");
			JLabel sqlexp = new JLabel(exp);
			sqlexp.setFont(f);
			sqlexp.setBounds(xsqlcor,90, 180, 25);
			panel.add(sqlexp);
			System.out.println(exp);
			
			r=st.executeQuery("select card_type from card where card_no="+cardno);
			r.next();
			String type= r.getString("card_type");
			JLabel sqlctype = new JLabel(type);
			sqlctype.setFont(f);
			sqlctype.setBounds(xsqlcor,120, 200, 25);
			panel.add(sqlctype);
			System.out.println(type);

			r=st.executeQuery("select x.acco_no  from account x,provides y, card c where c.card_no=y.card_no and y.acco_no=x.acco_no and c.card_no="+cardno);
			r.next();
			String accno= r.getString("acco_no");
			JLabel sqlaccno = new JLabel(accno);
			sqlaccno.setFont(f);
			sqlaccno.setBounds(xsqlcor,150, 120, 25);
			panel.add(sqlaccno);
			System.out.println(accno);

			r=st.executeQuery("select x.type  from account x,provides y, card c where c.card_no=y.card_no and y.acco_no=x.acco_no and c.card_no="+cardno);
			r.next();
			String acctype= r.getString("type");
			JLabel sqlacctype = new JLabel(acctype);
			sqlacctype.setFont(f);
			sqlacctype.setBounds(xsqlcor,180, 120, 25);
			panel.add(sqlacctype);
			System.out.println(acctype);

			r=st.executeQuery("select x.current_balance from account x,provides y, card c where c.card_no=y.card_no and y.acco_no=x.acco_no and c.card_no="+cardno);
			r.next();
			String accbal= r.getString("current_balance");
			JLabel sqlaccbal = new JLabel("$ "+accbal);
			sqlaccbal.setFont(f);
			sqlaccbal.setBounds(xsqlcor,210, 120, 25);
			panel.add(sqlaccbal);
			System.out.println(accbal);

			r=st.executeQuery("select x.datez from transactions x,performs y,customer z,has h,card c where x.trans_id=y.trans_id and y.c_id=z.c_id and h.c_id=z.c_id and h.card_no=c.card_no and c.card_no="+cardno);
			r.next();
			String acclastwd= r.getString("datez");
			r=st.executeQuery("select x.timez from transactions x,performs y,customer z,has h,card c where x.trans_id=y.trans_id and y.c_id=z.c_id and h.c_id=z.c_id and h.card_no=c.card_no and c.card_no="+cardno);
			r.next();
			String acclastwt= r.getString("timez");
			JLabel sqlacclast = new JLabel(acclastwt+"     "+acclastwd);
			sqlacclast.setFont(f);
			sqlacclast.setBounds(xsqlcor,240, 240, 25);
			panel.add(sqlacclast);
			System.out.println(acclastwd+"   "+acclastwt);
//SQL user details------------------------------------------------------

			r=st.executeQuery("select x.c_id from customer x,has y, card c where c.card_no=y.card_no and y.c_id=x.c_id and c.card_no="+cardno);
			r.next();
			String cid= r.getString("c_id");
			JLabel sqlcid = new JLabel(cid);
			sqlcid.setFont(f);
			sqlcid.setBounds(xsqlcor,310, 120, 25);
			panel.add(sqlcid);
			System.out.println(cid);

			r=st.executeQuery("select x.cname from customer x,has y, card c where c.card_no=y.card_no and y.c_id=x.c_id and c.card_no="+cardno);
			r.next();
			String cname= r.getString("cname");
			JLabel sqlcname = new JLabel(cname);
			sqlcname.setFont(f);
			sqlcname.setBounds(xsqlcor,340, 120, 25);
			panel.add(sqlcname);
			System.out.println(cname);

			r=st.executeQuery("select x.phone_no from customer x,has y, card c where c.card_no=y.card_no and y.c_id=x.c_id and c.card_no="+cardno);
			r.next();
			String cphn= r.getString("phone_no");
			JLabel sqlcphn = new JLabel("+64 "+cphn);
			sqlcphn.setFont(f);
			sqlcphn.setBounds(xsqlcor,370, 120, 25);
			panel.add(sqlcphn);
			System.out.println(cphn);

			r=st.executeQuery("select x.c_address from customer x,has y, card c where c.card_no=y.card_no and y.c_id=x.c_id and c.card_no="+cardno);
			r.next();
			String cadd= r.getString("c_address");
			JLabel sqlcadd = new JLabel(cadd);
			sqlcadd.setFont(f);
			sqlcadd.setBounds(xsqlcor,400, 200, 25);
			panel.add(sqlcadd);
			System.out.println(cadd);

//SQL atm details-------------------------------
			r=st.executeQuery("select x.atm_id from atm_mc x,provides2 y,card c where c.card_no=y.card_no and y.atm_id=x.atm_id and c.card_no="+cardno);
			r.next();
			String atmid= r.getString("atm_id");
			JLabel sqlatmid = new JLabel(atmid);
			sqlatmid.setFont(f);
			sqlatmid.setBounds(xsqlcor,470, 120, 25);
			panel.add(sqlatmid);
			System.out.println(atmid);

			r=st.executeQuery("select x.atm_address from atm_mc x,provides2 y,card c where c.card_no=y.card_no and y.atm_id=x.atm_id and c.card_no="+cardno);
			r.next();
			String atmadd= r.getString("atm_address");
			JLabel sqlatmadd = new JLabel(atmadd);
			sqlatmadd.setFont(f);
			sqlatmadd.setBounds(xsqlcor,500, 200, 25);
			panel.add(sqlatmadd);
			System.out.println(atmadd);

			r=st.executeQuery("select x.branch from atm_mc x,provides2 y,card c where c.card_no=y.card_no and y.atm_id=x.atm_id and c.card_no="+cardno);
			r.next();
			String atmbran= r.getString("branch");
			JLabel sqlatmbran = new JLabel(atmbran);
			sqlatmbran.setFont(f);
			sqlatmbran.setBounds(xsqlcor,530, 120, 25);
			panel.add(sqlatmbran);
			System.out.println(atmbran);

			r=st.executeQuery("select x.bank_name from bank x,belongsto y,card c,provides2 z,atm_mc a where c.card_no=z.card_no and y.bank_id=x.bank_id and y.atm_id=a.atm_id and a.atm_id=z.atm_id and c.card_no="+cardno);
			r.next();
			String bname= r.getString("bank_name");
			JLabel sqlbname = new JLabel(bname);
			sqlbname.setFont(f);
			sqlbname.setBounds(xsqlcor,560, 120, 25);
			panel.add(sqlbname);
			System.out.println(bname);

			r=st.executeQuery("select x.ifsc_code from bank x,belongsto y,card c,provides2 z,atm_mc a where c.card_no=z.card_no and y.bank_id=x.bank_id and y.atm_id=a.atm_id and a.atm_id=z.atm_id and c.card_no="+cardno);
			r.next();
			String bifsc= r.getString("ifsc_code");
			JLabel sqlbifsc = new JLabel(bifsc);
			sqlbifsc.setFont(f);
			sqlbifsc.setBounds(xsqlcor,590, 120, 25);
			panel.add(sqlbifsc);
			System.out.println(bifsc);

			r=st.executeQuery("select x.bank_id from bank x,belongsto y,card c,provides2 z,atm_mc a where c.card_no=z.card_no and y.bank_id=x.bank_id and y.atm_id=a.atm_id and a.atm_id=z.atm_id and c.card_no="+cardno);
			r.next();
			String bid= r.getString("bank_id");
			JLabel sqlbid = new JLabel(bid);
			sqlbid.setFont(f);
			sqlbid.setBounds(xsqlcor,620, 120, 25);
			panel.add(sqlbid);
			System.out.println(bid);

			r=st.executeQuery("select x.b_address from bank x,belongsto y,card c,provides2 z,atm_mc a where c.card_no=z.card_no and y.bank_id=x.bank_id and y.atm_id=a.atm_id and a.atm_id=z.atm_id and c.card_no="+cardno);
			r.next();
			String badd= r.getString("b_address");
			JLabel sqlbadd = new JLabel(badd);
			sqlbadd.setFont(f);
			sqlbadd.setBounds(xsqlcor,650, 200, 25);
			panel.add(sqlbadd);
			System.out.println(badd);
		   }
		   catch(Exception ez)
		   {
			System.out.println("Some Error in SQL Querie !");
		   }

//Buttons---------------------------------------------------------------------------------------------
		logoutbutton= new JButton("Log out");
		logoutbutton.setFont(f);
		logoutbutton.setBounds(460, 690, 100, 35);
		panel.add(logoutbutton); 
		translipBut=new JButton("Last Transaction Slip");
		translipBut.setFont(f);
		translipBut.setBounds(30, 690, 160, 35);
		panel.add(translipBut); 
		logoutbutton.addActionListener(this);
		translipBut.addActionListener(this);
		add(panel);
	}

	public static void main(String arg[])
	{
		try
		{
		DetailsPage frame=new DetailsPage("4848-4848-4848-4848");
		frame.setSize(600, 780);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setResizable(false);
   		}
   		catch(Exception e)
   		{JOptionPane.showMessageDialog(null, e.getMessage());}
   	}

	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource() == logoutbutton)
		{
			this.setVisible(false);
			Login p=new Login();
			p.setVisible(true);
		}
		else if(e.getSource()== translipBut)
		{
			this.setVisible(false);
			TransactionSlip p=new TransactionSlip(cardno_global);
			p.setVisible(true);
		}
	}
}
