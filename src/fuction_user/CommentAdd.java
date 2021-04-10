package fuction_user;

import java.awt.BorderLayout;

import java.awt.Container;
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
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.WindowConstants;

import manner.Login;
import util.DBUtil;
/*
 * ������۶Ի���
 */
@SuppressWarnings("serial")
public class CommentAdd extends JDialog implements ActionListener {
	JLabel jlCtype = new JLabel("����������");//���۱�ǩ����
	JTextArea jtaContent = new JTextArea(); //�������ı�������
	JButton jbSubmit = new JButton("�ύ");//�ύ��ť����
	JButton jbCanel = new JButton("����");//���ذ�ť����
	public CommentAdd() {//���췽�����ò��ֺ�ע�������
		Container cp = this.getContentPane();
		this.getRootPane().setDefaultButton(jbSubmit);//����Ĭ�ϰ�ť
		cp.add(jtaContent,BorderLayout.CENTER);
		JPanel jp = new JPanel();
		jp.add(jbSubmit);
		jp.add(jbCanel);
		cp.add(jp,BorderLayout.SOUTH);
		this.setTitle("�������");//���ñ���
		this.setSize(500, 200);//���ô�С
		this.setLocationRelativeTo(null);//������ʾ
		this.setResizable(false);//���ò��ɸı��С
		this.setModal(true);//����ģ̬
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);//���ùر�ȱʡ����
	
		jbSubmit.addActionListener(this);
		jbCanel.addActionListener(this);
		
		this.setVisible(true);//���ÿɼ���
	}
	@Override
	public void actionPerformed(ActionEvent e) {//���ü�����
		if(e.getSource() == jbSubmit) {
			if(jtaContent.getText().length() == 0) {
				JOptionPane.showMessageDialog(null, "�������۲���Ϊ�գ�");
			}
			else {
				try {
					Connection con = new DBUtil().connection("ktv_prime");
					PreparedStatement stm = null;
					DateFormat date = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
					stm = con.prepareStatement("insert into Comment(Etime,Econtent,EuserID) values('"+date.format(new Date())+"','"+jtaContent.getText()+"','"+Login.getUsername()+"')");
					stm.executeUpdate();
					stm.close();
					con.close();
					JOptionPane.showMessageDialog(null, "��ӳɹ�");
				}catch (Exception e2) {
					e2.printStackTrace();
					JOptionPane.showMessageDialog(null, e2.toString(), "���ʧ��",JOptionPane.ERROR_MESSAGE);
				}
				this.dispose();
			}	
		}
		else
			this.dispose();	
	}
}