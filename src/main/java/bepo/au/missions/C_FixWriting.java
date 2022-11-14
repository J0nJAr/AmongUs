package bepo.au.missions;

import bepo.au.base.Mission;
import bepo.au.utils.Util;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Arrays;
import java.util.List;

public class C_FixWriting extends Mission {

	public C_FixWriting(MissionType mt2, String name, String korean, int clear, Location... locs) {
		super(true, mt2, name, korean, clear, locs);
	}

	
	protected int[][] wirecolorArray;
	protected boolean[][] connected;
	private final Material[] WIRECOLORARRAY = 
			{ Material.RED_STAINED_GLASS_PANE, Material.BLUE_STAINED_GLASS_PANE, Material.GREEN_STAINED_GLASS_PANE,
					Material.PURPLE_STAINED_GLASS_PANE };

	@Override
	public void onAssigned(Player p) {
		
		int a = Util.random(0, 2);
		int b = Util.random(a+1, 3);
		int c = Util.random(b+1, 4);
		locs = Arrays.asList(locs.get(a), locs.get(b), locs.get(c));
		assign(p);
		wirecolorArray = new int[][] { Util.difrandom(0, 3, 4), Util.difrandom(0, 3, 4), Util.difrandom(0, 3, 4) };
		for (int i = 0; i < 3; i++) {
			uploadInventory(p, 54, "FixWiring" + i);
		}
		
		connected = new boolean[4][4];
		for(int x=0;x<4;x++) {
			for(int y=0;y<4;y++) connected[x][y] = false;
		}
		
	}

	@Override
	public void onStart(Player p, int i) {
		
		if(i != cleared.size()) return;
		for(int y=0;y<4;y++) connected[i][y] = false;
		for (int slot = 0; slot < 54; slot++) {// gui�κ��丮
			int y = slot / 9, x = slot % 9;
			if (y == 0 || y == 2 || y == 3 || y == 5) {
				//Util.debugMessage(" wirecolorArray Ȯ�� " + wirecolorArray[i][yToidx(y)]);
				if (x == 8)
					Util.Stack(gui.get(i), slot, Material.BLACK_STAINED_GLASS_PANE, 1, " ");// ������ǥ��
				else if (x == 0)
					Util.Stack(gui.get(i), slot, Material.YELLOW_STAINED_GLASS_PANE, 1, " ");// ��� ǥ��
				else if (x == 1 || x == 2)
					fillWire(gui.get(i), slot, wirecolorArray[i][yToidx(y)], x, i);// ���� ���̾� ä���(����)
				else if (x == 7)
					fillWire(gui.get(i), slot, yToidx(y), i); // ������ ���̾� ä���(����)
				else if (x == 6)
					gui.get(i).clear(slot); // ������ ���̾� �����
				else
					Util.Stack(gui.get(i), slot, Material.WHITE_STAINED_GLASS_PANE, 1, " "); // ���
			} else
				Util.Stack(gui.get(i), slot, Material.WHITE_STAINED_GLASS_PANE, 1, " "); // ���
		}
		p.openInventory(gui.get(i));
	}

	@Override
	public void onStop(Player p, int i) {
		for (Material mat : WIRECOLORARRAY) {
			p.getInventory().remove(mat);
		}
	}

	@Override
	public void onClear(Player p, int i) {
		generalClear(p, i);
	}

	public static int yToidx(int y) {
		int idx = -1;
		switch (y) {
		case 0:
			idx = 0;
			break;
		case 2:
			idx = 1;
			break;
		case 3:
			idx = 2;
			break;
		case 5:
			idx = 3;
			break;
		}
		return idx;
	}

	public void fillWire(Inventory gui, int slot, int color, int num, int code) {
		List<String> lore = (num == 1 ? Arrays.asList("��4Ŭ���Ұ�") : Arrays.asList("��7��Ŭ���� �����մϴ�."));
		switch (color) {
		case 0:
			Util.Stack(gui, slot, WIRECOLORARRAY[0], num, "��cRed ��fWire", lore);
			break;
		case 1:
			Util.Stack(gui, slot, WIRECOLORARRAY[1], num, "��9Blue ��fWire", lore);
			break;
		case 2:
			Util.Stack(gui, slot, WIRECOLORARRAY[2], num, "��aGreen ��fWire", lore);
			break;
		case 3:
			Util.Stack(gui, slot, WIRECOLORARRAY[3], num, "��dPurple ��fWire", lore);
			break;
		case -1:
			gui.setItem(slot, new ItemStack(Material.BARRIER, num));
			break;
		}
	}

	public void fillWire(Inventory inv, int slot, int color, int code) {
		fillWire(inv, slot, color, 1, code);
	}

	public void checkConnection(Player p, int code, int slot) {
		
		new BukkitRunnable() {
			public void run() {
				if (slot % 9 == 6 && slot < 54 && slot / 9 != 1 && slot / 9 != 4) {
					int idx = yToidx(slot / 9);
					//Util.debugMessage(slot + "���� ���� Ȯ��");
					if (!(gui.get(code).getItem(slot) == null)
							&& gui.get(code).getItem(slot).getType() == WIRECOLORARRAY[idx]) {
						//Util.debugMessage("�����" + (slot + 2) + "�� ��� ����");
						Util.Stack(gui.get(code), slot + 2, Material.YELLOW_STAINED_GLASS_PANE, 1, " ");// ���� ���� ǥ��
						p.playSound(p.getLocation(), Sound.BLOCK_END_PORTAL_FRAME_FILL, 1.0f, 1.2f); //�Ҹ����
						connected[code][idx] = true;
						for (int i = 0; i < 4; i++)
							if (connected[code][i] == false) {
								Util.debugMessage(i + "�� ����ȵ�");
								return;
							}
						Util.debugMessage("connected[" + code + "][" + idx + "]");
						Util.debugMessage("Ŭ����!"); // Ŭ����!
						onClear(p, code);
					} else {
						//Util.debugMessage("����ȵ�" + (slot + 2) + "�� ���� ����");
						Util.Stack(gui.get(code), slot + 2, Material.BLACK_STAINED_GLASS_PANE, 1, " ");// ���� ���� ǥ��
						connected[code][idx] = false;
					}
				}
			}
		}.runTaskLater(main, 0L);

	}

	@EventHandler
	public void onClick(InventoryClickEvent e) {
		if (!checkPlayer(e))
			return;

		String title = e.getView().getTitle();
		Util.debugMessage("Ŭ�� �νĵ�");
		int slot = e.getRawSlot();
		int code = Integer.parseInt(title.replace("FixWiring", ""));
		ItemStack itemstack = e.getCurrentItem();

		// Inventory gui = e.getClickedInventory();
		// Player p = (Player) e.getWhoClicked();

		if (e.isRightClick())
			Util.debugMessage("��Ŭ�� �νĵ�");
		if (e.getCurrentItem() != null) {
			if (!e.isRightClick() || // ��Ŭ���� ���
					(slot % 9 != 2 && slot % 9 != 6) || // Ŭ�� ������ x��ǥ
					slot / 9 == 1 || slot / 9 == 4 // Ŭ�� �Ұ��� y��ǥ
			) { //
				//Util.debugMessage("Ŭ�� �Ұ�");
				e.setCancelled(true);
			}
			if ((e.getCursor().getType() != Material.AIR || itemstack.getAmount() == 1)
					&& (slot % 9 == 1 || slot % 9 == 2 || slot % 9 == 7)) {// ������ �ϳ��Ͻ� Ŭ�� �Ұ� &
				//Util.debugMessage("Ŭ�� �Ұ�");
				e.setCancelled(true);
			}
		}
		checkConnection((Player) e.getWhoClicked(), code, slot);
	}

	@EventHandler
	public void onDrag(InventoryDragEvent e) {
		if (!checkPlayer(e)) return;
		
		String title = e.getView().getTitle();

		int code = Integer.parseInt(title.replace("FixWiring", ""));
		if (!e.getRawSlots().isEmpty()) {
			for (int slot : e.getRawSlots()) {
				checkConnection((Player) e.getWhoClicked(), code, slot);
			}
		}

	}
}