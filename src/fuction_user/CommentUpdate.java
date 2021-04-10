package fuction_user;

import java.awt.BorderLayout;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;

import manner.Login;
import util.DBUtil;
/*
 * �޸����۶Ի���
 */
@SuppressWarnings("serial")
public class CommentUpdate extends JDialog implements ActionListener {
	JLabel jlCID = new JLabel("���������۱��");//���ñ�ǩ
	JLabel jlContent = new JLabel("���޸ģ�");
	JTextField jtCID = new JTextField(8);//�ı���
	JTextArea jtaContent = new JTextArea(); //��������
	JButton jbSubmit = new JButton("�ύ");//��ť
	JButton jbCanel = new JButton("����");
	public CommentUpdate() {//���췽�����ò���
		Container cp = this.getContentPane();
		this.getRootPane().setDefaultButton(jbSubmit);//����Ĭ�ϰ�ť
		JPanel jp = new JPanel();
		JPanel jpCID = new JPanel();
		JPanel jpButton = new JPanel();
		jp.setLayout(new BorderLayout());
		jpCID.add(jlCID);
		jpCID.add(jtCID);
		jp.add(jpCID);
		jp.add(jlContent,BorderLayout.SOUTH);
		
		jpButton.add(jtaContent);
		jpButton.add(jbSubmit);
		jpButton.add(jbCanel);
		
		cp.add(jp,BorderLayout.NORTH);
		cp.add(jtaContent,BorderLayout.CENTER);
		cp.add(jpButton,BorderLayout.SOUTH);
		this.setTitle("�û������޸�");//���ñ���
		this.setSize(500, 300);//���ô�С
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
			if(jtCID.getText().length()==0||jtaContent.getText().length()==0) {
				JOptionPane.showMessageDialog(null, "����������");
			}
		else {
			try {
				Connection con1 = new DBUtil().connection("ktv_prime");
				PreparedStatement stm1 = con1.prepareStatement("select EuserID from Comment where EID='"+jtCID.getText()+"'");
				ResultSet rs = stm1.executeQuery();
				if(rs.next()) {
					if(rs.getString(1).equals(Login.getUsername())) {
						try {
							DateFormat date = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
							Connection con = new DBUtil().connection("ktv_prime");
							PreparedStatement stm = null;
							stm = con.prepareStatement("update Comment set Etime='"+date.format(new Date())+"',Econtent='"+jtaContent.getText()+"' where EuserID = '"+Login.getUsername()+"' and EID = '"+jtCID.getText()+"'");
							stm.executeUpdate();
							stm.close();
							con.close();
							JOptionPane.showMessageDialog(null, "�޸ĳɹ�");	
						}catch (Exception e2) {
							e2.printStackTrace();
							JOptionPane.showMessageDialog(null, e2.toString(), "�޸�ʧ��",JOptionPane.ERROR_MESSAGE);
						}
						this.dispose();
					}
					else {
						JOptionPane.showMessageDialog(null, "�����޸ı��˵�����");
					}
				}
				else {
					JOptionPane.showMessageDialog(null, "�ñ�Ų�����");
				}
			} catch (SQLException e1) {
				JOptionPane.showMessageDialog(null, e1.toString(), "�޸�ʧ��",JOptionPane.ERROR_MESSAGE);
				e1.printStackTrace();
			}
		}
	}
	else {
		this.dispose();
	}
		try {
			DefaultTableModel jTable1Model = new DefaultTableModel(new String[0][0] ,new String[] {"���۱��","����ʱ��","��������","�����û�"});//��������Ϊ��ά��
			CommentSelect.jtComment.setModel(jTable1Model);
			Connection con = new DBUtil().connection("ktv_prime");
			PreparedStatement stm = con.prepareStatement("select * from Comment");
			ResultSet rs = stm.executeQuery();
			while(rs.next()) {
				int i=1;
				jTable1Model.addRow(new String[]{rs.getString(i),rs.getString(i+1),rs.getString(i+2),rs.getString(i+3)});
			}
			rs.close();
			stm.close();
			con.close();
		} catch (SQLException e1) {
			JOptionPane.showMessageDialog(null, e1.toString(), "��ѯʧ��",JOptionPane.ERROR_MESSAGE);
			e1.printStackTrace();
		}			
		
	}
}	