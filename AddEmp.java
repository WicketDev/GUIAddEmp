package GUI;

import java.awt.EventQueue;
import java.io.*;
import javax.swing.JFrame;
import java.awt.CardLayout;
import java.awt.GridLayout;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import com.jgoodies.forms.factories.DefaultComponentFactory;
import javax.swing.JTextField;
import javax.swing.JFormattedTextField;
import javax.swing.SwingConstants;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.SystemColor;
import javax.swing.JTextArea;
import java.awt.print.*;

public class AddEmp implements Printable{

	private JFrame frame;
	private JPasswordField MgrSsnField;
	private JTextField Fnametxt;
	private JTextField minitTxt;
	private JTextField txtLname;
	private JTextField txtSsn;
	private JTextField txtAddress;
	private JTextField txtDepName;
	private JTextField txtTotHrs;
	private JPanel Login;
	private JPanel EmpForm;
	private JPanel Dependents;
	private JPanel Projects;
	private JPanel Confirm;
	static Connection conn;
	boolean ok = false;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) throws SQLException, IOException{
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("Connection Successful.");
			
			
		} catch (ClassNotFoundException x) {
			System.out.println("Driver could not be loaded.");
		}
		 conn = DriverManager.getConnection("jdbc:oracle:thin:@apollo.vse.gmu.edu:1521:ite10g", "jgordo14", "psytyh");
		 String id, ssn, fname, lname, bday, address, depName, depBday;
		 char sex, minit;
		 float hours;
		 double salary;
		
		
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddEmp window = new AddEmp();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @throws SQLException 
	 */
	public AddEmp() throws SQLException {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() throws SQLException {
		frame = new JFrame();
		frame.setBounds(100, 100, 677, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new CardLayout(0, 0));
		
		final JPanel Login = new JPanel();
		Login.setBackground(SystemColor.activeCaption);
		frame.getContentPane().add(Login, "name_846587032948846");
		Login.setLayout(null);
		Login.setVisible(true);
		
		final JPanel EmpForm = new JPanel();
		EmpForm.setBackground(SystemColor.activeCaption);
		frame.getContentPane().add(EmpForm, "name_846587050287769");
		EmpForm.setLayout(null);
		EmpForm.setVisible(false);
		
		final JPanel Dependents = new JPanel();
		Dependents.setBackground(SystemColor.activeCaption);
		frame.getContentPane().add(Dependents, "name_846587062544970");
		Dependents.setLayout(null);
		Dependents.setVisible(false);
		
		final JPanel Projects = new JPanel();
		Projects.setBackground(SystemColor.activeCaption);
		frame.getContentPane().add(Projects, "name_846587073394380");
		Projects.setLayout(null);
		Projects.setVisible(false);
		
		final JPanel Confirm = new JPanel();
		Confirm.setBackground(SystemColor.activeCaption);
		frame.getContentPane().add(Confirm, "name_915123095575015");
		Confirm.setLayout(null);
		Confirm.setVisible(false);
		
		JLabel lblMgrLogTitle = new JLabel("Manager Login");
		lblMgrLogTitle.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblMgrLogTitle.setBounds(250, 35, 165, 34);
		Login.add(lblMgrLogTitle);
		
		JLabel lblMgrSsn = new JLabel("Enter SSN: ");
		lblMgrSsn.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblMgrSsn.setBounds(190, 128, 89, 14);
		Login.add(lblMgrSsn);
		
		MgrSsnField = new JPasswordField();
		MgrSsnField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id = new String(MgrSsnField.getPassword());
				System.out.println(id);
				try {
					String ssn = null;
					String stmt1 = "Select Mgrssn from DEPARTMENT";
					PreparedStatement p = conn.prepareStatement(stmt1);
					ResultSet r = p.executeQuery();
				
					while(r.next())
					{
						ssn = r.getString(1);
						System.out.println(ssn + " " + id);
						if(id.equals(ssn)) 
						{
							//ok = true;
							EmpForm.setVisible(true);
							Login.setVisible(false);
							System.out.println("Match found.");
						}
					}
				} catch (SQLException ex)
				{
					System.out.println("SQL Exception.");
				};
			}
		});
		MgrSsnField.setBackground(SystemColor.menu);
		MgrSsnField.setBounds(314, 128, 99, 17);
		Login.add(MgrSsnField);
		
		JButton btnLoginButton = new JButton("OK");
		btnLoginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
	
				if(ok) {
					EmpForm.setVisible(true);
					Login.setVisible(false);
				}
				else
					System.out.println("Access Denied. Managers Only.");
					System.exit(0);
						
			}
		});
		btnLoginButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnLoginButton.setBounds(190, 189, 89, 23);
		Login.add(btnLoginButton);
		
		JButton btnCancelLoginButton = new JButton("CANCEL");
		btnCancelLoginButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
		});
		
		btnCancelLoginButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnCancelLoginButton.setBounds(326, 189, 89, 23);
		Login.add(btnCancelLoginButton);
		
		
		
		JLabel EmpInfoTitle = new JLabel("Employee Data");
		EmpInfoTitle.setFont(new Font("Tahoma", Font.BOLD, 17));
		EmpInfoTitle.setBounds(257, 11, 136, 26);
		EmpForm.add(EmpInfoTitle);
		
		JLabel lblFirstName = new JLabel("First Name:");
		lblFirstName.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblFirstName.setBounds(46, 54, 74, 14);
		EmpForm.add(lblFirstName);
		
		JLabel lblMinit = new JLabel("middle Initial:");
		lblMinit.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblMinit.setBounds(237, 54, 79, 14);
		EmpForm.add(lblMinit);
		
		JLabel lblLname = new JLabel("Last Name: ");
		lblLname.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblLname.setBounds(386, 54, 65, 14);
		EmpForm.add(lblLname);
		
		JLabel lblSsn = new JLabel("SSN:");
		lblSsn.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblSsn.setBounds(46, 110, 39, 14);
		EmpForm.add(lblSsn);
		
		JLabel lblBday = new JLabel("Birthdate:");
		lblBday.setToolTipText("yyyy-mm-dd");
		lblBday.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblBday.setBounds(206, 110, 65, 14);
		EmpForm.add(lblBday);
		
		JLabel lblAddress = new JLabel("Address: ");
		lblAddress.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblAddress.setBounds(386, 110, 65, 14);
		EmpForm.add(lblAddress);
		
		JCheckBox chckbxDependents = new JCheckBox("Dependents?");
		chckbxDependents.setFont(new Font("Tahoma", Font.BOLD, 11));
		chckbxDependents.setBounds(46, 186, 110, 23);
		EmpForm.add(chckbxDependents);
		chckbxDependents.setSelected(false);
		
		String[] sexMenu = {null, "Male", "Female"};
		JComboBox EmpSexDropDown = new JComboBox(sexMenu);
		EmpSexDropDown.setSelectedIndex(0);
		EmpSexDropDown.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JComboBox cb = (JComboBox)e.getSource();
		        String empSex = (String)cb.getSelectedItem();
			}
		});
		EmpSexDropDown.setMaximumRowCount(3);
		EmpSexDropDown.setFont(new Font("Tahoma", Font.BOLD, 11));
		EmpSexDropDown.setBounds(83, 151, 64, 20);
		EmpForm.add(EmpSexDropDown);
		
		String[] depts = {null,"Research", "Administration", "Headquarters"};
		JComboBox DeptDropDown = new JComboBox(depts);
		DeptDropDown.setSelectedIndex(0);
		DeptDropDown.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		DeptDropDown.setMaximumRowCount(4);
		DeptDropDown.setFont(new Font("Tahoma", Font.BOLD, 11));
		DeptDropDown.setBounds(237, 151, 79, 20);
		EmpForm.add(DeptDropDown);
		
		JLabel lblEmpSex = new JLabel("Sex:");
		lblEmpSex.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblEmpSex.setBounds(46, 154, 46, 14);
		EmpForm.add(lblEmpSex);
		
		JLabel lblDept = new JLabel("Department:");
		lblDept.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblDept.setBounds(161, 154, 80, 14);
		EmpForm.add(lblDept);
		
		JButton EmpInfoConfirm = new JButton("OK");
		EmpInfoConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EmpForm.setVisible(false);
				if(chckbxDependents.isSelected())
				Dependents.setVisible(true);
				else
					Projects.setVisible(true);
			}
		});
		
		EmpInfoConfirm.setFont(new Font("Tahoma", Font.BOLD, 11));
		EmpInfoConfirm.setBounds(161, 227, 89, 23);
		EmpForm.add(EmpInfoConfirm);
		
		JButton EmpInfoCancel = new JButton("CANCEL");
		EmpInfoCancel.setFont(new Font("Tahoma", Font.BOLD, 11));
		EmpInfoCancel.setBounds(345, 227, 89, 23);
		EmpForm.add(EmpInfoCancel);
		
		Fnametxt = new JTextField();
		Fnametxt.setText("John");
		Fnametxt.setBounds(128, 51, 86, 20);
		EmpForm.add(Fnametxt);
		Fnametxt.setColumns(10);
		
		minitTxt = new JTextField();
		minitTxt.setText("A");
		minitTxt.setBounds(326, 51, 28, 20);
		EmpForm.add(minitTxt);
		minitTxt.setColumns(10);
		
		txtLname = new JTextField();
		txtLname.setText("Smith");
		txtLname.setBounds(461, 51, 86, 20);
		EmpForm.add(txtLname);
		txtLname.setColumns(10);
		
		txtSsn = new JTextField();
		txtSsn.setText("999999999");
		txtSsn.setBounds(83, 107, 86, 20);
		EmpForm.add(txtSsn);
		txtSsn.setColumns(10);
		
		JFormattedTextField fmtxtBday = new JFormattedTextField();
		fmtxtBday.setText("1999-01-26");
		fmtxtBday.setBounds(281, 107, 79, 20);
		EmpForm.add(fmtxtBday);
		
		txtAddress = new JTextField();
		txtAddress.setText("123 Acme St, Houston, TX");
		txtAddress.setBounds(461, 107, 170, 20);
		EmpForm.add(txtAddress);
		txtAddress.setColumns(10);
		
		JLabel lblSalary = new JLabel("Salary  $");
		lblSalary.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblSalary.setBounds(339, 154, 54, 14);
		EmpForm.add(lblSalary);
		
		JFormattedTextField fmtxtSal = new JFormattedTextField();
		fmtxtSal.setHorizontalAlignment(SwingConstants.CENTER);
		fmtxtSal.setText("xxxxxx.xx");
		fmtxtSal.setBounds(398, 151, 71, 20);
		EmpForm.add(fmtxtSal);
		
		String[] projs = {null,"ProductX", "ProductY", "ProductZ", "Computerization", "Reorganization", "NewBenefits"};
		JComboBox ProjectDropDown = new JComboBox(projs);
		ProjectDropDown.setSelectedIndex(0);
		ProjectDropDown.setMaximumRowCount(7);
		ProjectDropDown.setBounds(547, 154, 84, 20);
		EmpForm.add(ProjectDropDown);
		
		JLabel lblProjs = new JLabel("Project(s):");
		lblProjs.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblProjs.setBounds(479, 157, 68, 14);
		EmpForm.add(lblProjs);
		
		
		
		JLabel DependentTitle = new JLabel("Dependent Info");
		DependentTitle.setFont(new Font("Tahoma", Font.BOLD, 19));
		DependentTitle.setBounds(224, 22, 192, 25);
		Dependents.add(DependentTitle);
		
		JLabel lblDependentName = new JLabel("Dependent Name:");
		lblDependentName.setToolTipText("First name only");
		lblDependentName.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblDependentName.setBounds(59, 75, 112, 14);
		Dependents.add(lblDependentName);
		
		JLabel lblDepBday = new JLabel("Birthdate:");
		lblDepBday.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblDepBday.setBounds(346, 75, 70, 14);
		Dependents.add(lblDepBday);
		
		JLabel lblDepSex = new JLabel("Sex:");
		lblDepSex.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblDepSex.setBounds(148, 141, 46, 14);
		Dependents.add(lblDepSex);
		
		JLabel lblRelation = new JLabel("Relationship:");
		lblRelation.setToolTipText("Relationship to Employee");
		lblRelation.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblRelation.setBounds(314, 141, 93, 14);
		Dependents.add(lblRelation);
		
		JButton EndDepButton = new JButton("FINISHED");
		EndDepButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Dependents.setVisible(false);
				Projects.setVisible(true);
			}
		});
		EndDepButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		EndDepButton.setBounds(280, 213, 89, 23);
		Dependents.add(EndDepButton);
		
		JButton CancelDepButton = new JButton("CANCEL");
		CancelDepButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Dependents.setVisible(false);
				Projects.setVisible(true);
			}
		});
		CancelDepButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		CancelDepButton.setBounds(400, 213, 89, 23);
		Dependents.add(CancelDepButton);
		
		JComboBox DepSexDropDown = new JComboBox(sexMenu);
		DepSexDropDown.setSelectedIndex(0);
		DepSexDropDown.setMaximumRowCount(3);
		//DepSexDropDown.addActionListener(1);
		DepSexDropDown.setBounds(186, 138, 65, 20);
		Dependents.add(DepSexDropDown);
		
		String[] relations = {null, "Spouse", "Son", "Daughter"};
		JComboBox RelationDropDown = new JComboBox(relations);
		RelationDropDown.setSelectedIndex(0);
		RelationDropDown.setMaximumRowCount(4);
		RelationDropDown.setBounds(417, 138, 72, 20);
		Dependents.add(RelationDropDown);
		
		txtDepName = new JTextField();
		txtDepName.setText("Sarah");
		txtDepName.setBounds(175, 72, 86, 20);
		Dependents.add(txtDepName);
		txtDepName.setColumns(10);
		
		JFormattedTextField fmtxtDepBday = new JFormattedTextField();
		fmtxtDepBday.setText("1999-01-28");
		fmtxtDepBday.setBounds(418, 72, 62, 20);
		Dependents.add(fmtxtDepBday);
		
		JButton AddlDepButton = new JButton("NEXT DEPENDENT");
		AddlDepButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Statement stmt2 = "";
			}
		});
		AddlDepButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		AddlDepButton.setBounds(127, 213, 124, 23);
		Dependents.add(AddlDepButton);
		
		
		
		JLabel lblProjects = new JLabel("Project:");
		lblProjects.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblProjects.setBounds(139, 67, 104, 14);
		Projects.add(lblProjects);
		
		JLabel lblHours = new JLabel("Hours:");
		lblHours.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblHours.setBounds(309, 67, 65, 14);
		Projects.add(lblHours);
		
		
		JComboBox ProjDropDown = new JComboBox(projs);
		ProjDropDown.setSelectedIndex(0);
		ProjDropDown.setMaximumRowCount(7);
		ProjDropDown.setBounds(149, 92, 110, 20);
		Projects.add(ProjDropDown);
		
		JFormattedTextField fmtxHours = new JFormattedTextField();
		fmtxHours.setText("10.0");
		fmtxHours.setBounds(319, 92, 40, 20);
		Projects.add(fmtxHours);
		
		JLabel messageHours = new JLabel("*Total hours cannot exceed 40.0.");
		messageHours.setBounds(219, 140, 179, 14);
		Projects.add(messageHours);
		
		JButton ConfirmHoursButton = new JButton("OK");
		ConfirmHoursButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Projects.setVisible(false);
				Confirm.setVisible(true);
			}
		});
		ConfirmHoursButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		ConfirmHoursButton.setBounds(197, 195, 89, 23);
		Projects.add(ConfirmHoursButton);
		
		JButton CancelHoursButton = new JButton("CANCEL");
		CancelHoursButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		CancelHoursButton.setBounds(309, 195, 89, 23);
		Projects.add(CancelHoursButton);
		
		JLabel ProjectsTitle = new JLabel("Assign Projects");
		ProjectsTitle.setFont(new Font("Tahoma", Font.BOLD, 18));
		ProjectsTitle.setBounds(234, 11, 148, 28);
		Projects.add(ProjectsTitle);
		
		JLabel lblTotalHours = new JLabel("Total Hrs:");
		lblTotalHours.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblTotalHours.setBounds(473, 69, 89, 14);
		Projects.add(lblTotalHours);
		
		txtTotHrs = new JTextField();
		txtTotHrs.setText("40.0");
		txtTotHrs.setFont(new Font("Tahoma", Font.BOLD, 11));
		txtTotHrs.setEditable(false);
		txtTotHrs.setBounds(473, 92, 86, 20);
		Projects.add(txtTotHrs);
		txtTotHrs.setColumns(10);
		
		
		
		JLabel lblEmployeeDataConfirmation = new JLabel("Employee Data Confirmation");
		lblEmployeeDataConfirmation.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblEmployeeDataConfirmation.setBounds(209, 11, 215, 14);
		Confirm.add(lblEmployeeDataConfirmation);
		
		JTextArea txtrNameAddressSsn = new JTextArea();
		txtrNameAddressSsn.setBackground(SystemColor.inactiveCaptionBorder);
		txtrNameAddressSsn.setText("Name: \r\nAddress:\r\nSSN:\t\t\t\t\t\tBirthdate:\t\t\t\t\tSex:\r\nDependents:\r\nSalary: \t\t\t\t\t\tDepartment:\t\t\t\tSupervisor:\t\r\nProjects:\t\t\t\t\tHours:\r\n");
		txtrNameAddressSsn.setLineWrap(true);
		txtrNameAddressSsn.setWrapStyleWord(true);
		txtrNameAddressSsn.setEditable(false);
		txtrNameAddressSsn.setBounds(10, 39, 641, 171);
		Confirm.add(txtrNameAddressSsn);
		
		JButton Printbtn = new JButton("Print");
		Printbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PrinterJob print = PrinterJob.getPrinterJob();
				
				
			}
		});
		Printbtn.setFont(new Font("Tahoma", Font.BOLD, 11));
		Printbtn.setBounds(209, 227, 89, 23);
		Confirm.add(Printbtn);
		
		JButton Editbtn = new JButton("Edit");
		Editbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Confirm.setVisible(false);
				EmpForm.setVisible(true);
			}
		});
		Editbtn.setFont(new Font("Tahoma", Font.BOLD, 11));
		Editbtn.setBounds(351, 227, 89, 23);
		Confirm.add(Editbtn);
	}

	@Override
	public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
		// TODO Auto-generated method stub
		return 0;
	}
}

