package fuction_manager;

import java.awt.Container;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import util.DBUtil;

public class NewManager extends JDialog implements ActionListener {
	/**
	 * ע���µĹ���Ա��
	 */
	private static final long serialVersionUID = 1L;
	JLabel jlManagerID = new JLabel("ע���˺�");//���ñ�ǩ
	JLabel jlPwd = new JLabel("Ȩ����");
	JLabel jlNewPwd = new JLabel("����");
	JLabel jlIdentify = new JLabel("ȷ������");
	JTextField jtfManagerID = new JTextField(20);
	JTextField jpfPwd = new JTextField(20);//�����
	JPasswordField jpfNewPwd = new JPasswordField(20);
	JPasswordField jpfIdentify = new JPasswordField(20);
	JButton jbSubmit = new JButton("�ύ");//��ť
	JButton jbCancel = new JButton("����");
	public NewManager() {//���췽�����ò��ּ�ע�������
		Container cp = this.getContentPane();
		this.getRootPane().setDefaultButton(jbSubmit);//����Ĭ�ϰ�ť
		cp.setLayout(new FlowLayout());
		cp.add(jlManagerID);
		cp.add(jtfManagerID);
		cp.add(jlPwd);
		cp.add(jpfPwd);
		cp.add(jlNewPwd);
		cp.add(jpfNewPwd);
		cp.add(jlIdentify);
		cp.add(jpfIdentify);
		cp.add(jbSubmit);
		cp.add(jbCancel);
		this.setTitle("ע�����Ա");//���ñ���
		this.setSize(290, 200);//���ô�С
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
			if(jtfManagerID.getText().length() == 0 || jpfPwd.getText().length() == 0 || jpfNewPwd.getText().length() == 0 || jlIdentify.getText().length() == 0) {
				JOptionPane.showMessageDialog(null, "�����������");
				jtfManagerID.setText("");
				jpfPwd.setText("");
				jpfNewPwd.setText("");
				jlIdentify.setText("");
			}
			else {
					
					if(jpfPwd.getText().equals("���ܱ���ʦ��JAVA���������")) {
						if(jpfNewPwd.getText().equals(jpfIdentify.getText())) {
							try {
								Connection con = new DBUtil().connection("manager_prime");
								PreparedStatement stm = null;
								stm = con.prepareStatement("insert into manager values('"+jtfManagerID.getText()+"','"+jpfNewPwd.getText()+"')") ;
								stm.executeUpdate();
								JOptionPane.showMessageDialog(null, "��ӳɹ���");
								//�ر����ݿ�
								stm.close();
								con.close();
								this.dispose();
							} catch (SQLException e1) {
								e1.printStackTrace();
								JOptionPane.showMessageDialog(null, "�������벻���Ϲ淶�����������룡","�������", JOptionPane.ERROR_MESSAGE);
								jtfManagerID.setText("");
								jpfPwd.setText("");
								jpfNewPwd.setText("");
								jpfIdentify.setText("");
							}
						}
						else {
							JOptionPane.showMessageDialog(null, "�����������벻һ�£����������룡","�������", JOptionPane.ERROR_MESSAGE);
							jtfManagerID.setText("");
							jpfPwd.setText("");
							jpfNewPwd.setText("");
							jpfIdentify.setText("");
						}
					}
					else {
						JOptionPane.showMessageDialog(null, "Ȩ�������","�������", JOptionPane.ERROR_MESSAGE);
						jtfManagerID.setText("");
						jpfPwd.setText("");
						jpfNewPwd.setText("");
						jpfIdentify.setText("");
					}
			}		
		}
		else {
			this.dispose();
		}
	}
}
