package cn.mh.view.friendShip;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;

import cn.mh.po.FU;
import cn.mh.po.Friendship;
import cn.mh.po.GroupUser;
import cn.mh.po.Groups;
import cn.mh.po.User;
import cn.mh.service.FriendshipService;
import cn.mh.service.GroupChatService;
import cn.mh.service.GroupService;
import cn.mh.service.GroupUserService;
import cn.mh.service.UserService;
import cn.mh.service.impl.FriendShipServiceImpl;
import cn.mh.service.impl.GroupChatServiceImpl;
import cn.mh.service.impl.GroupServiceImpl;
import cn.mh.service.impl.GroupUserServiceImpl;
import cn.mh.service.impl.UserServiceImpl;
import cn.mh.util.Config;
import cn.mh.view.chat.ChatUI;
import cn.mh.view.chat.RecordUI;

public class FriendListUI extends JFrame implements ActionListener {
	private FriendshipService fService;
	private GroupService groupService;
	private UserService uservice;
	GroupChatService groupChatService = new GroupChatServiceImpl();
	GroupUserService groupUserService = new GroupUserServiceImpl();
	JPopupMenu menu;
	JMenuItem mdel, mlook, mnew;
	JList<String> list;
	JButton bt1;
	JButton bt4;
	JButton bt5;
	Vector<String> v1;
	public static Map<String, ChatUI> ChatUIs = new HashMap<String, ChatUI>();

	public FriendListUI() {
		fService = new FriendShipServiceImpl();
		groupService = new GroupServiceImpl();
		uservice = new UserServiceImpl();
		this.setTitle("用户:  " + Config.nickname);
		this.setLayout(new BorderLayout());
		// 获取好友信息
		v1 = this.getGroups(Config.id);
		// 创建列表框，填充的数据为好友账号信息
		list = new JList<String>(v1);
		// 把列表框添加到具有滚动功能的容器中
		JScrollPane scollPane = new JScrollPane(list);
		// 把中间容器添加到窗口
		this.add(scollPane);
		this.RightMouse();
		menu.setVisible(false);
		list.add(menu);
		list.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					String groupName = list.getSelectedValue();
					List<Groups> gs = groupService.findAll(Config.id);
					v1.removeAllElements();
					for (Groups g : gs) {
						v1.addElement(g.getName());
						if (g.getName().equals(groupName)) {
							List<Friendship> fs = fService.findByGroupId(g.getId());
							for (Friendship f : fs) {
								v1.addElement("  " + f.getName() + "(" + f.getFriendId() + ")");
							}
						}
					}
					List<GroupUser> gList = groupUserService.findAll(Config.id);
					for (GroupUser g : gList) {
						v1.addElement("群聊: " + g.getName() + "(" + g.getGroupchatId() + ")");
							if(groupName.startsWith("群聊:")){
								if(groupName.split(":")[1].equals(" "+g.getName()+ "(" + g.getGroupchatId() + ")")){
									GroupUserService gus = new GroupUserServiceImpl();
									List<GroupUser> guss= gus.findBygId(g.getGroupchatId());
									for(GroupUser gsss : guss ){
										v1.addElement("   " +  gsss.getFriendId());
									}
								}
							}
					}
					list.setListData(v1);
				}

				if (e.getButton() == MouseEvent.BUTTON3) {
					menu.show(FriendListUI.this, e.getX(), e.getY());
				}
			}
		});

		// 添加按钮
		bt1 = new JButton("查找");
		bt1.addActionListener(this);
		bt4 = new JButton("消息");
		bt4.addActionListener(this);
		bt5 = new JButton("消息记录");
		bt5.addActionListener(this);
		final JPanel p1 = new JPanel();
		p1.add(bt4);
		p1.add(bt1);
		p1.add(bt5);
		this.add(p1, BorderLayout.SOUTH);
		this.setSize(250, 550);
		// 将窗口位置放置在屏幕中央
		Dimension dim1 = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (int) dim1.getWidth() - 200 - 50;
		this.setLocation(x, 0);
		this.setVisible(true);

	}

	public Vector<String> getFriends(Integer id) {
		List<Friendship> friendships = fService.findAll(id);
		Vector<String> v = new Vector<String>();
		for (Friendship f : friendships) {
			v.addElement(f.getName());
		}
		return v;
	}

	public Vector<String> getGroups(Integer id) {
		List<Groups> gs = groupService.findAll(id);
		Vector<String> v = new Vector<String>();
		for (Groups g : gs) {
			v.addElement(g.getName());
		}
		List<GroupUser> gList = groupUserService.findAll(Config.id);
		for (GroupUser g : gList) {
			v.addElement("群聊: " + g.getName() + "(" + g.getGroupchatId() + ")");
		}
		return v;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == bt1) {
			new FriendAddUI(this);
		}
		if (e.getSource() == bt4) {
			new CheckFriend();
		}
		if (e.getSource() == bt5) {
			new RecordUI();
		}
	}

	public void RightMouse() {
		JMenuItem mlook, mDel, mchat, mnew;

		menu = new JPopupMenu();
		mnew = new JMenuItem("新建");
		menu.add(mnew);
		mchat = new JMenuItem("聊天");
		menu.add(mchat);
		mlook = new JMenuItem("查看");
		menu.add(mlook);
		mDel = new JMenuItem("删除");
		menu.add(mDel);

		mnew.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				new newGroup();
			}
		});

		mchat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String val = list.getSelectedValue();
				if (val.indexOf("(") != -1) {
					ChatUI chatUI = new ChatUI(val);
					String id = val.substring(val.indexOf("(") + 1, val.indexOf(")"));
					ChatUIs.put(id, chatUI);
				}
			}
		});

		mlook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String val = list.getSelectedValue();
				if (val == null) {
					return;
				}
				if (val.indexOf("(") != -1) {
					String name = val.substring(val.indexOf("(") + 1, val.indexOf(")"));
					User u = uservice.findByName(name);
					JOptionPane.showMessageDialog(null, u.toString());
					return;
				}
			}
		});

		mDel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String val = list.getSelectedValue();

				if (val == null)
					return;
				if (val.startsWith("群聊")) {
					if (val.indexOf("(") != -1) {
						String name = val.substring(val.indexOf("(") + 1, val.indexOf(")"));
						GroupService gservice = new GroupServiceImpl();
						gservice.delete(Integer.valueOf(name));
						refresh();
					}
				} else {
					if (val.indexOf("(") != -1) {
						String name = val.substring(val.indexOf("(") + 1, val.indexOf(")"));
						fService.UpdateFriend(new FU(Config.id, Integer.valueOf(name), 2));
						refresh();
					} else {
						GroupService gservice = new GroupServiceImpl();
						gservice.delete(Config.id, val);
						refresh();
					}
				}
			}
		});

	}

	public void refresh() {
		v1 = this.getGroups(Config.id);
		list.setListData(v1);
	}

	public static void main(String[] args) {
		new FriendListUI();
	}
}
