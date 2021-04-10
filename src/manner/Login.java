package manner;

import java.awt.BorderLayout;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import manager.KTVManager;
import manager.UserManager;
import util.DBUtil;
/*

 */

public class Login extends JDialog implements ActionListener {
	/**
	 *   �û���¼��
	 *   ��Ϊ����Ա��¼���û���¼
	 */
	private static final long serialVersionUID = 1L;
	private static String Username = null;//���þ�̬���ԣ��˺�
	private JLabel jlUsername = new JLabel("�˺�");//�������Ա�ǩ
	private JLabel jlPwd = new JLabel("����");
	private JLabel jlUserType = new JLabel("�û����      ");
	private JTextField jtUsername = new JTextField(20);//�˺��ı���
	private JPasswordField jtPwd = new JPasswordField(20);//�����
	private JComboBox<String> jcbUserType = new JComboBox<String>();//�û����
	private JButton jbOK = new JButton("��¼");//���ð�ť
	private JButton jbCancel = new JButton("�˳�");
	private JButton jbRegister = new JButton("�˿�ע��");
	public Login() {
		Container container = this.getContentPane();//��ȡ����
		this.getRootPane().setDefaultButton(jbOK);//����Ĭ�ϰ�ť
		JPanel jpanel1 = new JPanel();//�����µ����
		JPanel jpanel2 = new JPanel();
		JPanel jpanel3 = new JPanel();
		JPanel jpanel4 = new JPanel();
		JPanel jpanel5 = new JPanel(); 
		jpanel3.setLayout(new BorderLayout());//������ɱ߿򲼾�
		jpanel1.add(this.jlUsername);//�����˺ź������ǩ���ı���
		jpanel1.add(this.jtUsername);
		jpanel2.add(this.jlPwd);
		jpanel2.add(this.jtPwd);
		
		jcbUserType.addItem("����Ա");//�����û����
		jcbUserType.addItem("�˿�");
		jcbUserType.setSelectedIndex(0);//���ó�ʼ�û����
		jpanel4.add(this.jlUserType);
		jpanel4.add(jcbUserType);
		jpanel5.add(this.jbOK);//��Ӱ�ť
		jpanel5.add(this.jbCancel);
		jpanel5.add(this.jbRegister);
		jpanel3.add(jpanel4,BorderLayout.NORTH);//���ò���
		jpanel3.add(jpanel5,BorderLayout.SOUTH);
		container.add(jpanel1,BorderLayout.NORTH);
		container.add(jpanel2,BorderLayout.CENTER);
		container.add(jpanel3,BorderLayout.SOUTH);
		this.setTitle("ktv����ϵͳ��½����");//���ñ���
		this.setSize(300, 200);//���ô�С
		this.setLocationRelativeTo(null);//������ʾ
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);//���ùر�ȱʡ����

		jbOK.addActionListener(this);//ע�������
		jbCancel.addActionListener(this);
		jbRegister.addActionListener(this);
		this.setVisible(true);//���ÿɼ���
	}
	public static String getUsername() {//��ȡ�˺ŷ���
		return Username;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == jbOK) {//�ύ�������ݿ��в�ѯ�˺�����
			Connection con = new DBUtil().connection("manager_prime");
			PreparedStatement stm = null;
			ResultSet st =null;
			if(jcbUserType.getSelectedIndex()==0) {//����ǹ���Ա��¼����������Ա����
				try {
					@SuppressWarnings("deprecation")
					String s = "select * from manager where managerID='"+jtUsername.getText()+"' and managerPwd='"+jtPwd.getText()+"'";
					stm = con.prepareStatement(s);
					st = stm.executeQuery();
					if(st.next()) {
							Login.Username = jtUsername.getText();
							new KTVManager();
							this.dispose();
					}
					else {//�������
						JOptionPane.showMessageDialog(null, "��������˺Ż������벻��ȷ��","�������", JOptionPane.ERROR_MESSAGE);
						jtUsername.setText("");
						jtPwd.setText("");
					}
				}catch(Exception e1) {//�׳��쳣
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null, e1.toString(),"ERROR", JOptionPane.WARNING_MESSAGE);
					jtUsername.setText("");
					jtPwd.setText("");
				}
			}
			else {//����ǹ˿͵�¼�������˿ͽ���
				try {
					@SuppressWarnings("deprecation")
					String s = "select * from users where userID='"+jtUsername.getText()+"' and userPwd='"+jtPwd.getText()+"'";
					stm = con.prepareStatement(s);
					st = stm.executeQuery();
					if(st.next()) {
						Login.Username = jtUsername.getText();
						new UserManager();
						this.dispose();
					}
					else {//�������
						JOptionPane.showMessageDialog(null, "��������˺Ż������벻��ȷ��","�������", JOptionPane.ERROR_MESSAGE);
						jtUsername.setText("");
						jtPwd.setText("");
					}
					st.close();//�ر����ݿ�
					stm.close();
					con.close();
				}catch(Exception e1) {//�׳��쳣
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null, e1.toString(),"ERROR", JOptionPane.WARNING_MESSAGE);
					jtUsername.setText("");
					jtPwd.setText("");
				}
			}
		}else if(e.getSource() == jbCancel) {//�˳�����
			System.exit(0);
		}else {//�û�ע��
			new Register();
		}
	}
}
