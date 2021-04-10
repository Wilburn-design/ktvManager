package manager;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.SwingConstants;

import fuction_manager.Backup;
import fuction_manager.Delete;
import fuction_manager.NewManager;
import fuction_manager.SelectOrder;
import fuction_manager.SelectRoom;
import fuction_manager.SelectStore;
import fuction_manager.SelectVip;
import fuction_manager.SelectWorker;
import fuction_manager.PwdAlt;
import fuction_manager.Restore;
import fuction_manager.SelectComment;
import fuction_manager.SelectCustomer;
import fuction_manager.SelectDeposit;
import fuction_manager.SelectFood;
import manner.Login;
public class KTVManager extends JFrame implements ActionListener {
	/**
	 * 管理员界面
	 */
	private static final long serialVersionUID = 1L;

	JMenuBar jmb = new JMenuBar();//建立菜单栏
	
	JMenu jmSystem = new JMenu("系统");//设置菜单
	JMenu jmAccount = new JMenu("账户");
	JMenu jmOption = new JMenu("需求管理");
	JMenu jmDeposit = new JMenu("寄存管理");
	JMenu jmFood = new JMenu("点餐管理");
	JMenu jmOrder = new JMenu("订单管理");
	JMenu jmRoom = new JMenu("房间管理");
	JMenu jmStore = new JMenu("店面管理");
	JMenu jmVip = new JMenu("会员管理");
	JMenu jmWorker = new JMenu("员工管理");
	JMenu jmComment = new JMenu("评论查看");
	JMenu jmSafe = new JMenu("安全与维护");
	
	JMenuItem jmiReLog = new JMenuItem("重新登录");//设置菜单项
	JMenuItem jmiExit = new JMenuItem("退出");

	JMenuItem jmiAltPwd = new JMenuItem("修改密码");
	JMenuItem jmiDelete = new JMenuItem("注销账户");
	JMenuItem jmiNewManager = new JMenuItem("注册管理员");
	JMenuItem jmiSelectCustomer = new JMenuItem("查询需求");
	JMenuItem jmiSelectDeposit = new JMenuItem("查询寄存");
	JMenuItem jmiSelectFood = new JMenuItem("查询点餐");
	JMenuItem jmiSelectOrder = new JMenuItem("查询订单");
	JMenuItem jmiSelectRoom = new JMenuItem("查询房间");
	JMenuItem jmiSelectStore = new JMenuItem("查询店面");
	JMenuItem jmiSelectVip = new JMenuItem("查询会员");
	JMenuItem jmiSelectWorker = new JMenuItem("查询员工");
	JMenuItem jmiSelectComment = new JMenuItem("查询评论");
	JMenuItem jmiBackup = new JMenuItem("备份");
	JMenuItem jmiRestore = new JMenuItem("还原");
	
	ImageIcon img = new ImageIcon("./img/Manager.jpg");//添加图片
	JLabel jlimg = new JLabel(img,SwingConstants.CENTER);
	public KTVManager() {//构造方法设置界面及添加监听器
		super("管理员管理系统");
		this.setJMenuBar(jmb);
		getContentPane().add(jlimg);
		jmb.add(jmSystem);
		jmb.add(jmAccount);
		jmb.add(jmOption);
		jmb.add(jmDeposit);
		jmb.add(jmFood);
		jmb.add(jmOrder);
		jmb.add(jmRoom);
		jmb.add(jmStore);
		jmb.add(jmVip);
		jmb.add(jmWorker);
		jmb.add(jmComment);
		jmb.add(jmSafe);
		
		jmSystem.add(jmiReLog);
		jmSystem.addSeparator();
		jmSystem.add(jmiExit);
		jmAccount.add(jmiAltPwd);
		jmAccount.add(jmiNewManager);
		jmAccount.addSeparator();
		jmAccount.add(jmiDelete);
		jmOption.add(jmiSelectCustomer);
		jmDeposit.add(jmiSelectDeposit);
		jmFood.add(jmiSelectFood);
		jmOrder.add(jmiSelectOrder);
		jmRoom.add(jmiSelectRoom);
		jmStore.add(jmiSelectStore);
		jmVip.add(jmiSelectVip);
		jmWorker.add(jmiSelectWorker);
		jmComment.add(jmiSelectComment);
		jmSafe.add(jmiBackup);
		jmSafe.add(jmiRestore);
		
		this.setSize(800,400);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setResizable(false);

		jmiReLog.addActionListener(this);
		jmiExit.addActionListener(this);
		jmiAltPwd.addActionListener(this);
		jmiDelete.addActionListener(this);
		jmiNewManager.addActionListener(this);
		jmiSelectCustomer.addActionListener(this);
		jmiSelectDeposit.addActionListener(this);
		jmiSelectFood.addActionListener(this);
		jmiSelectOrder.addActionListener(this);
		jmiSelectRoom.addActionListener(this);
		jmiSelectStore.addActionListener(this);
		jmiSelectVip.addActionListener(this);
		jmiSelectWorker.addActionListener(this);
		jmiSelectComment.addActionListener(this);
		jmiBackup.addActionListener(this);
		jmiRestore.addActionListener(this);
		
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {//设置监听器
		if(e.getSource() == jmiReLog) {
			new Login();
			this.dispose();
		}
		if(e.getSource() == jmiExit) {
			System.exit(0);
		}
		if(e.getSource() == jmiAltPwd) {
			new PwdAlt();
		}
		if(e.getSource() == jmiDelete) {
			new Delete(this);
		}
		if(e.getSource() == jmiNewManager) {
			new NewManager();
		}
		if(e.getSource() == jmiSelectCustomer) {
			new SelectCustomer();
		}
		if(e.getSource() == jmiSelectDeposit) {
			new SelectDeposit();
		}
		if(e.getSource() == jmiSelectFood) {
			new SelectFood();
		}
		if(e.getSource() == jmiSelectOrder) {
			new SelectOrder();
		}
		if(e.getSource() == jmiSelectRoom) {
			new SelectRoom();
		}
		if(e.getSource() == jmiSelectStore) {
			new SelectStore();
		}
		if(e.getSource() == jmiSelectVip) {
			new SelectVip();
		}	
		if(e.getSource() == jmiSelectWorker) {
			new SelectWorker();
		}
		if(e.getSource() == jmiSelectComment) {
			new SelectComment();
		}
		if(e.getSource() == jmiBackup) {
			new Backup();
		}
		if(e.getSource() == jmiRestore) {
			new Restore();
		}
	}
}
