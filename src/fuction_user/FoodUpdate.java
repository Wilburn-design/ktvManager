package fuction_user;

import java.awt.BorderLayout;
import java.awt.Container;
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
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;

import manner.Login;
import util.DBUtil;

@SuppressWarnings("serial")
public class FoodUpdate extends JDialog implements ActionListener {
	JLabel jlCID = new JLabel("ʳƷ���");//���ñ�ǩ
	JLabel jlAmount = new JLabel("�޸�����");
	JTextField jtCID = new JTextField(8);//�ı���
	JTextField jtAmount = new JTextField(8); //�޸���������
	JButton jbSubmit = new JButton("�ύ");//��ť
	JButton jbCanel = new JButton("����");
	public FoodUpdate() {//���췽�����ò���
		Container cp = this.getContentPane();
		this.getRootPane().setDefaultButton(jbSubmit);//����Ĭ�ϰ�ť
		JPanel jp = new JPanel();
		JPanel jpCID = new JPanel();
		JPanel jpButton = new JPanel();
		jpCID.add(jlCID);
		jpCID.add(jtCID);
		jp.add(jlAmount);
		jp.add(jtAmount);
		
		jpButton.add(jbSubmit);
		jpButton.add(jbCanel);
		
		cp.add(jpCID,BorderLayout.NORTH);
		cp.add(jp,BorderLayout.CENTER);
		cp.add(jpButton,BorderLayout.SOUTH);
		this.setTitle("�û������޸�");//���ñ���
		this.setSize(200, 150);//���ô�С
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
			if(jtAmount.getText().length()==0||jtCID.getText().length()==0) {
				JOptionPane.showConfirmDialog(null, "�����������!");
				jtAmount.setText("");
				jtCID.setText("");
			}
			else {
				try {
						Connection con = new DBUtil().connection("ktv_prime");
						PreparedStatement stm = null;
						stm = con.prepareStatement("update Food set Famount="+jtAmount.getText()+" where FuserID = '"+Login.getUsername()+"' and FID = "+jtCID.getText()+"");
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
		}	
		else {
			this.dispose();	
		}	
		try {
			DefaultTableModel jTable1Model = new DefaultTableModel(new String[0][0] ,new String[] {"ʳƷ���","��������","�۸�","ʳƷ����","����"});//��������Ϊ��ά��
			FoodSelect.jtFood.setModel(jTable1Model);
			Connection con = new DBUtil().connection("ktv_prime");
			PreparedStatement stm = con.prepareStatement("select FID,Fproduce,Fprice,Fname,Famount from Food");
			ResultSet rs = stm.executeQuery();
			while(rs.next()) {
				int i=1;
				jTable1Model.addRow(new String[]{rs.getString(i),rs.getString(i+1),rs.getString(i+2),rs.getString(i+3),rs.getString(i+4)});
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