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
	 * ����Ա����
	 */
	private static final long serialVersionUID = 1L;

	JMenuBar jmb = new JMenuBar();//�����˵���
	
	JMenu jmSystem = new JMenu("ϵͳ");//���ò˵�
	JMenu jmAccount = new JMenu("�˻�");
	JMenu jmOption = new JMenu("�������");
	JMenu jmDeposit = new JMenu("�Ĵ����");
	JMenu jmFood = new JMenu("��͹���");
	JMenu jmOrder = new JMenu("��������");
	JMenu jmRoom = new JMenu("�������");
	JMenu jmStore = new JMenu("�������");
	JMenu jmVip = new JMenu("��Ա����");
	JMenu jmWorker = new JMenu("Ա������");
	JMenu jmComment = new JMenu("���۲鿴");
	JMenu jmSafe = new JMenu("��ȫ��ά��");
	
	JMenuItem jmiReLog = new JMenuItem("���µ�¼");//���ò˵���
	JMenuItem jmiExit = new JMenuItem("�˳�");

	JMenuItem jmiAltPwd = new JMenuItem("�޸�����");
	JMenuItem jmiDelete = new JMenuItem("ע���˻�");
	JMenuItem jmiNewManager = new JMenuItem("ע�����Ա");
	JMenuItem jmiSelectCustomer = new JMenuItem("��ѯ����");
	JMenuItem jmiSelectDeposit = new JMenuItem("��ѯ�Ĵ�");
	JMenuItem jmiSelectFood = new JMenuItem("��ѯ���");
	JMenuItem jmiSelectOrder = new JMenuItem("��ѯ����");
	JMenuItem jmiSelectRoom = new JMenuItem("��ѯ����");
	JMenuItem jmiSelectStore = new JMenuItem("��ѯ����");
	JMenuItem jmiSelectVip = new JMenuItem("��ѯ��Ա");
	JMenuItem jmiSelectWorker = new JMenuItem("��ѯԱ��");
	JMenuItem jmiSelectComment = new JMenuItem("��ѯ����");
	JMenuItem jmiBackup = new JMenuItem("����");
	JMenuItem jmiRestore = new JMenuItem("��ԭ");
	
	ImageIcon img = new ImageIcon("./img/Manager.jpg");//���ͼƬ
	JLabel jlimg = new JLabel(img,SwingConstants.CENTER);
	public KTVManager() {//���췽�����ý��漰��Ӽ�����
		super("����Ա����ϵͳ");
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
	public void actionPerformed(ActionEvent e) {//���ü�����
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
