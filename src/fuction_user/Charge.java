package fuction_user;

import java.awt.BorderLayout;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.WindowConstants;

import manner.Login;
import util.DBUtil;
/*
 * ��ֵ�Ի���
 */
@SuppressWarnings("serial")
public class Charge extends JDialog implements ActionListener,ItemListener {
	static double balance = 0;//��ֵ�������
	JLabel jlCharge = new JLabel("��ֵ���");//��ǩ
	JRadioButton jrb50 = new JRadioButton("50Ԫ");//��ֵ��ѡ��ť
	JRadioButton jrb100 = new JRadioButton("100Ԫ");
	JRadioButton jrb500 = new JRadioButton("500Ԫ");
	JRadioButton jrb1000 = new JRadioButton("1000Ԫ");
	JButton jbSubmit = new JButton("�ύ");//��ť
	JButton jbCancel = new JButton("����");
	public Charge(Balance owner) {//���췽�����ò��ּ�ע�������
		super(owner,"��ֵ");
		Container cp = this.getContentPane();
		this.getRootPane().setDefaultButton(jbSubmit);//����Ĭ�ϰ�ť
		JPanel jp = new JPanel();
		JPanel jp1 = new JPanel();
		ButtonGroup bg = new ButtonGroup();
		bg.add(jrb50);
		bg.add(jrb100);
		bg.add(jrb500);
		bg.add(jrb1000);
		cp.add(jlCharge,BorderLayout.NORTH);
		jp.setLayout(new FlowLayout());
		jp.add(jrb50);
		jp.add(jrb100);
		jp.add(jrb500);
		jp.add(jrb1000);
		cp.add(jp,BorderLayout.CENTER);
		jp1.setLayout(new FlowLayout());
		jp1.add(jbSubmit);
		jp1.add(jbCancel);
		cp.add(jp1,BorderLayout.SOUTH);
		this.setSize(300, 120);//���ô�С
		this.setLocationRelativeTo(null);//������ʾ
		this.setResizable(false);//���ò��ɸı��С
		this.setModal(true);//����ģ̬
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);//���ùر�ȱʡ����

		jbSubmit.addActionListener(this);
		jbCancel.addActionListener(this);
		jrb50.addItemListener(this);
		jrb50.setSelected(true);
		jrb100.addItemListener(this);
		jrb500.addItemListener(this);
		jrb1000.addItemListener(this);
		this.setVisible(true);//���ÿɼ���
		owner.dispose();
	}
	@Override
	public void actionPerformed(ActionEvent e) {//���ü�����
		if(e.getSource() == jbSubmit) {
			try {
				Balance.restBalance += Charge.balance;
				Connection con = new DBUtil().connection("ktv_prime");
				PreparedStatement stm = con.prepareStatement("select Mbalance from Vip where MuserID = '"+Login.getUsername()+"'");
				stm = con.prepareStatement("update Vip set Mbalance='"+Balance.restBalance+"' where MuserID='"+Login.getUsername()+"'");
				stm.executeLargeUpdate();
				stm.close();
				con.close();
				JOptionPane.showMessageDialog(null, "��ֵ�ɹ���");
				new Balance();
				this.dispose();
			} catch (Exception e1) {
				e1.printStackTrace();
				JOptionPane.showMessageDialog(null, e1.toString(),"ERROR", JOptionPane.WARNING_MESSAGE);
			}
		}
		else {
			this.dispose();
		}
	}
	@Override
	public void itemStateChanged(ItemEvent e) {
		if(jrb50.isSelected()) {
			balance = 50;
		}else if(jrb100.isSelected()) {
			balance = 100;
		}else if(jrb500.isSelected()) {
			balance = 500;
		}else {
			balance = 1000;
		}
	}
}
