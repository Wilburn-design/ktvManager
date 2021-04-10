package fuction_manager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.swing.JOptionPane;

import util.DBUtil;
/*
 *  ���ݿⱸ��
 */
public class Backup {
	public Backup(){
		super();
		Connection con = new DBUtil().connection("ktv_prime");
		Connection con1 = new DBUtil().connection("manager_prime");
		try{
			boolean result = bkData("D:\\sql����\\","ktv_prime",con);
			System.out.println("result = "+result);
			boolean result1 = bkData("D:\\sql����\\","manager_prime",con1);
			System.out.println("result1 = "+result1);
			if(result&&result1) {
				JOptionPane.showMessageDialog(null, "���ݳɹ���");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
			con.close();
			}catch(Exception e1){
				e1.printStackTrace();
			}

		//���ݿⱸ��
		
		}
	}
	public static boolean bkData(String path, String db_name, Connection conn) {
		boolean flag=false;
		@SuppressWarnings("unused")
		String bk_name = null;//Ҫ���صĵı�������
		//�����ݿ���в���
		PreparedStatement stm = null;
		String sql = "";
		try {
			String file = db_name + ".bak";
			sql = "backup database " + db_name + " to disk= '"+path+file+"' with format,name='full backup of "+db_name+"'";
			stm = conn.prepareStatement(sql);
			stm.executeUpdate();
			flag=true;//���ص��ļ���
		//�쳣
		}catch(Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, e.toString(), "Error", JOptionPane.WARNING_MESSAGE);
		}finally {//״̬���ͷ�
			try {
				stm.close();
			}catch(Exception e1) {
				e1.printStackTrace();
			}
		}
		//����
		return flag;
	}
}
