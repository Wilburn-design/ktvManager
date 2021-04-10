package fuction_user;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.WindowConstants;

import manner.Login;
import util.DBUtil;
/*
 * �޸��������Ի���
 */
@SuppressWarnings("serial")
public class AltPwd extends JDialog implements ActionListener {
	JLabel jlOldPwd = new JLabel("������");//���ñ�ǩ
	JLabel jlNewPwd = new JLabel("������");
	JLabel jlIdentify = new JLabel("ȷ������");
	JPasswordField jpfOldPwd = new JPasswordField(20);//�����
	JPasswordField jpfNewPwd = new JPasswordField(20);
	JPasswordField jpfIdentify = new JPasswordField(20);
	JButton jbSubmit = new JButton("�ύ");//��ť
	JButton jbCancel = new JButton("����");
	public AltPwd() {//���췽�����ò��ּ�ע�������
		Container cp = this.getContentPane();
		this.getRootPane().setDefaultButton(jbSubmit);//����Ĭ�ϰ�ť
		cp.setLayout(new FlowLayout());
		cp.add(jlOldPwd);
		cp.add(jpfOldPwd);
		cp.add(jlNewPwd);
		cp.add(jpfNewPwd);
		cp.add(jlIdentify);
		cp.add(jpfIdentify);
		cp.add(jbSubmit);
		cp.add(jbCancel);
		this.setTitle("�޸�����");//���ñ���
		this.setSize(300, 180);//���ô�С
		this.setLocationRelativeTo(null);//������ʾ
		this.setResizable(false);//���ò��ɸı��С
		this.setModal(true);//����ģ̬
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);//���ùر�ȱʡ����
		
		jbSubmit.addActionListener(this);
		jbCancel.addActionListener(this);
		this.setVisible(true);//���ÿɼ���
	}
	@SuppressWarnings("deprecation")
	@Override
	public void actionPerformed(ActionEvent e) {//���ü�����
		if(e.getSource() == jbSubmit) {
			if(jpfOldPwd.getText().length() == 0 ||jpfNewPwd.getText().length() == 0 || jlIdentify.getText().length() == 0) {
				JOptionPane.showMessageDialog(null, "�����������");
				jpfOldPwd.setText("");
				jpfNewPwd.setText("");
				jlIdentify.setText("");
			}
			else {
				Connection con = new DBUtil().connection("manager_prime");
				PreparedStatement stm = null;
				ResultSet rs = null;
				try {
					stm = con.prepareStatement("select userPwd from users where userID='"+Login.getUsername()+"'");
					rs = stm.executeQuery();
					rs.next(); 
					if(rs.getString(1).equals(jpfOldPwd.getText())) {
						if(jpfNewPwd.getText().equals(jpfIdentify.getText())) {
							try {
								stm = con.prepareStatement("update users set userPwd='"+jpfNewPwd.getText()+"' where userID='"+Login.getUsername()+"'");
								stm.executeUpdate();
								JOptionPane.showMessageDialog(null, "�޸ĳɹ���");
								this.dispose();
							} catch (SQLException e1) {
								e1.printStackTrace();
								JOptionPane.showMessageDialog(null, "�������벻���Ϲ淶�����������룡","�������", JOptionPane.ERROR_MESSAGE);
								jpfOldPwd.setText("");
								jpfNewPwd.setText("");
								jpfIdentify.setText("");
							}
						}
						else {
							JOptionPane.showMessageDialog(null, "�����������벻һ�£����������룡","�������", JOptionPane.ERROR_MESSAGE);
							jpfOldPwd.setText("");
							jpfNewPwd.setText("");
							jpfIdentify.setText("");
						}
					}
					else {
						JOptionPane.showMessageDialog(null, "ԭ��������������������룡","�������", JOptionPane.ERROR_MESSAGE);
						jpfOldPwd.setText("");
						jpfNewPwd.setText("");
						jpfIdentify.setText("");
					}
					rs.close();//�ر����ݿ�
					stm.close();
					con.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}	
			}		
		}
		else {
			this.dispose();
		}
	}
}
