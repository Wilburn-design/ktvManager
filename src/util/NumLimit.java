package util;

import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;


public class NumLimit implements CaretListener {//插入位置更新时调用
	@Override
	public void caretUpdate(CaretEvent e) {
		JTextField jTextField = (JTextField)e.getSource();
		String text = jTextField.getText();//获取文本
		if(text.length()==0) {
			return;
		}
		char ch = text.charAt(text.length()-1);//获取文本的最后一个字符
		if(!(ch>='0'&&ch<='9')) {//判断字符
			JOptionPane.showMessageDialog(null, "只能输入数字","输入错误",JOptionPane.INFORMATION_MESSAGE);
			SwingUtilities.invokeLater(new Runnable() {//创建事件分发线程将之前存过的数字进行保存
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
