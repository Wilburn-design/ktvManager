package fuction_user;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import manner.Login;
import util.DBUtil;
/*
 * �鿴���Ի���
 */
@SuppressWarnings("serial")
public class Balance extends JDialog implements ActionListener {
	static double restBalance = 0;//ʣ���������
	JLabel jlBalance = new JLabel("���");//��ǩ
	JTextField jtBalance = new JTextField(10);//�ı���
	JButton jbCharge = new JButton("��ֵ");//��ť
	JButton jbCancel = new JButton("�ر�");
	public Balance() {//���췽�����ò��ּ�ע�������
		Container cp = this.getContentPane();
		this.getRootPane().setDefaultButton(jbCharge);//����Ĭ�ϰ�ť
		cp.setLayout(new FlowLayout());
		cp.add(jlBalance);
		cp.add(jtBalance);
		cp.add(jbCharge);
		cp.add(jbCancel);
		jtBalance.setEditable(false);
		try {
			Connection con = new DBUtil().connection("ktv_prime");
			PreparedStatement stm = con.prepareStatement("select Mbalance from Vip where MuserID = '"+Login.getUsername()+"'");
			ResultSet rs = stm.executeQuery();
			if(rs.next()) {
				restBalance = rs.getInt(1);					
			}
			this.jtBalance.setText(String.valueOf(restBalance));
			rs.close();
			stm.close();
			con.close();
			
		} catch (Exception e1) {
			e1.printStackTrace();
			JOptionPane.showMessageDialog(null, e1.toString(),"ERROR", JOptionPane.WARNING_MESSAGE);
		}
		this.setTitle("����ѯ");//���ñ���
		this.setSize(200, 100);//���ô�С
		this.setLocationRelativeTo(null);//������ʾ
		this.setModal(true);//����ģ̬
		this.setResizable(false);//���ò��ɸı��С
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);//���ùر�ȱʡ����
		
		jbCancel.addActionListener(this);
		jbCharge.addActionListener(this);
		
		this.setVisible(true);//���ÿɼ���	
	}
	@Override
	public void actionPerformed(ActionEvent e) {//���ü�����
		if(e.getSource() == jbCancel) {
			this.dispose();
		}
		else {
			new Charge(this);
		}
	}
}
