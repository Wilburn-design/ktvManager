package fuction_user;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import manner.Login;
import util.DBUtil;
/*
 * ����������ͶԻ���
 */
@SuppressWarnings("serial")
public class CustomerAdd extends JDialog implements ActionListener {
	JLabel jlCtype = new JLabel("��ѡ����������");//�������ͱ�ǩ����
	JComboBox<String> jcbCtype = new JComboBox<String>(); //������������
	JButton jbSubmit = new JButton("�ύ");//�ύ��ť����
	JButton jbCanel = new JButton("����");//��������
	public CustomerAdd() {
		Container cp = this.getContentPane();//��ȡ���
		this.getRootPane().setDefaultButton(jbSubmit);//����Ĭ�ϰ�ť
		cp.setLayout(new FlowLayout());//���ò���
		cp.add(jlCtype);
		jcbCtype.addItem("���ͷ���");//�������
		jcbCtype.addItem("���ͷ���");
		jcbCtype.addItem("С�ͷ���");
		cp.add(jcbCtype);
		JPanel jp = new JPanel();//���������
		jp.add(jbSubmit);
		jp.add(jbCanel);
		cp.add(jp);
		//������������Dialog����
		this.setTitle("�������");//���ñ���
		this.setSize(150, 150);//���ô�С
		this.setLocationRelativeTo(null);//������ʾ
		this.setResizable(false);//���ò��ɸı��С
		this.setModal(true);//����ģ̬
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);//���ùر�ȱʡ����

		//��Ӽ�����
		jbSubmit.addActionListener(this);
		jbCanel.addActionListener(this);
		
		this.setVisible(true);//���ÿɼ���
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		//���ü�����
		if(e.getSource() == jbSubmit) {
			try {
				Connection con = new DBUtil().connection("ktv_prime");
				PreparedStatement stm = null;
				if(jcbCtype.getSelectedIndex() == 0) {
					stm = con.prepareStatement("insert into Customer(CuserID,Ctype) values('"+Login.getUsername()+"','���ͷ���')");
					stm.executeUpdate();
				}else if(jcbCtype.getSelectedIndex() == 1) {
					stm = con.prepareStatement("insert into Customer(CuserID,Ctype) values('"+Login.getUsername()+"','���ͷ���')");
					stm.executeUpdate();
				}else {
					stm = con.prepareStatement("insert into Customer(CuserID,Ctype) values('"+Login.getUsername()+"','С�ͷ���')");
					stm.executeUpdate();
				}
				stm.close();
				con.close();
				JOptionPane.showMessageDialog(null, "��ӳɹ�");		
			}catch (Exception e2) {
				e2.printStackTrace();
				JOptionPane.showMessageDialog(null, e2.toString(), "���ʧ��",JOptionPane.ERROR_MESSAGE);
			}
			this.dispose();
		}
		else
			this.dispose();	

	}
}
