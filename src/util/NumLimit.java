package util;

import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;


public class NumLimit implements CaretListener {//����λ�ø���ʱ����
	@Override
	public void caretUpdate(CaretEvent e) {
		JTextField jTextField = (JTextField)e.getSource();
		String text = jTextField.getText();//��ȡ�ı�
		if(text.length()==0) {
			return;
		}
		char ch = text.charAt(text.length()-1);//��ȡ�ı������һ���ַ�
		if(!(ch>='0'&&ch<='9')) {//�ж��ַ�
			JOptionPane.showMessageDialog(null, "ֻ����������","�������",JOptionPane.INFORMATION_MESSAGE);
			SwingUtilities.invokeLater(new Runnable() {//�����¼��ַ��߳̽�֮ǰ��������ֽ��б���
				@Override
				public void run() {
					jTextField.setText(text.substring(0,text.length()-1));
				}
			});
		}
		else
			return;
	}
}
