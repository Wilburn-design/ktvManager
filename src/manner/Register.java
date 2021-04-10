package manner;

import java.awt.Container;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import util.DBUtil;
import util.NumLimit;

public class Register extends JDialog implements ActionListener {
	/**
	 * �û�vipע��ҳ�棬���õ�¼�˺ź�����
	 */
	private static final long serialVersionUID = 1L;
	JTextField jtaUserName = new JTextField(20);//�����˺�
	JPasswordField jpfPwd = new JPasswordField(20);//��������
	JPasswordField jpfIdentify = new JPasswordField(20);//ȷ������
	JTextField jtbalance = new JTextField(20);//�������
	JLabel jlUserName= new JLabel("�����˺�");//�ı����������ǩ
	JLabel jlPwd = new JLabel("��������");
	JLabel jlIdentify = new JLabel("ȷ������");
	JLabel jlbalance = new JLabel("Ԥ����");
	JButton jbSubmit = new JButton("�ύ");//�����ύ��ť
	JButton jbCancel = new JButton("����");//���÷��ص�¼���水ť
	public Register(){
		Container cp = this.getContentPane();//�������
		cp.setLayout(new FlowLayout());//������ʽ����
		cp.add(jlUserName);//����ǩ���ı�����벼����
		cp.add(jtaUserName);
		cp.add(jlPwd);
		cp.add(jpfPwd);
		cp.add(jlIdentify);
		cp.add(jpfIdentify);
		cp.add(jlbalance);
		cp.add(jtbalance);
		cp.add(jbSubmit);
		cp.add(jbCancel);

		this.setTitle("ע����Ϣ");//���ñ���
		this.setSize(300, 200);//���ô�С
		this.getRootPane().setDefaultButton(jbSubmit);//����Ĭ�ϰ�ť
		this.setLocationRelativeTo(null);//������ʾ
		this.setResizable(false);
		this.setModal(true);//����ģ̬
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);//���ùر�ȱʡ����

		jbSubmit.addActionListener(this);//ע�������
		jbCancel.addActionListener(this);
		jtbalance.addCaretListener(new NumLimit());//�����ֻ����������
		this.setVisible(true);//���ÿɼ���
	}
	@SuppressWarnings("deprecation")
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == jbSubmit) {//�ύ���ע��������ӵ���Ӧ�����ݿ�ı���
			if(jtaUserName.getText().length() == 0||jtbalance.getText().length() == 0 || jpfIdentify.getText().length() == 0 || jpfPwd.getText().length() == 0) {
				JOptionPane.showMessageDialog(null, "�����������");
			}
			else {
				Connection con1 = new DBUtil().connection("manager_prime");
				String s1 = "insert into users values('"+jtaUserName.getText()+"','"+jpfPwd.getText()+"')";
				Connection con2 = new DBUtil().connection("ktv_prime");
				DateFormat date = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
				String s2 = "insert into VIP(Mtime,Mbalance,MuserID) values('"+date.format(new Date())+"','"+jtbalance.getText()+"','"+jtaUserName.getText()+"')";
				PreparedStatement stm = null;
				if(jpfIdentify.getText().equals(jpfPwd.getText())) {//��������ȷ����ӳɹ�
					try {
						stm = con2.prepareStatement(s2);
						stm.executeUpdate();
						stm = con1.prepareStatement(s1);
						stm.executeUpdate();
						JOptionPane.showMessageDialog(null, "ע��ɹ���");
						stm.close();//�ر����ݿ�
						con1.close();
						con1.close();
						this.dispose();
					} catch (Exception e1) {//���˺��ѱ�ע����������ֵ�����Ϲ淶�򱨳�������ʾ��������ı���������
						e1.printStackTrace();
						JOptionPane.showMessageDialog(null, "�������벻���Ϲ淶�����������룡","�������", JOptionPane.ERROR_MESSAGE);
						jtaUserName.setText("");
						jpfPwd.setText("");
						jpfIdentify.setText("");
						jtbalance.setText("");
					}
				}
				else {//�������������벻һ������������ʾ��������ı���������
					JOptionPane.showMessageDialog(null, "�����������벻һ�£����������룡","�������", JOptionPane.ERROR_MESSAGE);
					jtaUserName.setText("");
					jpfPwd.setText("");
					jpfIdentify.setText("");
					jtbalance.setText("");
				}
			}	
		}
		else {//���غ��˳�����
			this.dispose();
		}
	}
}

