package bepo.au.manager;

import bepo.au.GameTimer;
import bepo.au.base.PlayerData;
import bepo.au.base.Sabotage;
import bepo.au.sabo.S_Fingerprint;
import bepo.au.sabo.S_Oxygen;
import io.github.thatkawaiisam.assemble.AssembleAdapter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class ScoreboardManager implements AssembleAdapter{
	
	@Override
	public void tick() {
	}
	
	@Override
	public String getTitle(Player player) {
		return "AMONG US";
	}

	@Override
	public List<String> getLines(Player player) {
		
		PlayerData pd = PlayerData.getPlayerData(player.getName());
		List<String> line = new ArrayList<String>();
		
		if(pd == null) {
			line.add("��7����� �������Դϴ�.");
			line.add("��f");
			line.add("������ �������� (" + GameTimer.getRemainImposter() + "��)");
			for(String name : GameTimer.ALIVE_IMPOSTERS) {
				line.add(PlayerData.getPlayerData(name).getColor().getChatColor() + name);
			}
			line.add("��f ");
			line.add("�ϰ� ���൵ " + GameTimer.CLEARED_MISSION + "/" + GameTimer.REQUIRED_MISSION);
		} else {
			
			boolean alive = pd.isAlive();
			boolean imposter = GameTimer.IMPOSTER.contains(player.getName());
			
			if(imposter) {
				line.add("��7�ϴ� �̼��� ����� �̼��Դϴ�.");
			}
			
			if(!alive) {
				if(imposter) {
					line.add("��c����� �׾�����, ");
					line.add("��c������ �纸Ÿ���� ����� �� �ֽ��ϴ�.");
				}
				else {
					line.add("��7����� �׾�����,");
					line.add("��7������ �ϰ��� ������ �� �ֽ��ϴ�.");
				}
			}
			
			if(Sabotage.isActivating(0)) {
				switch(Sabotage.Sabos.getType()) {
				case COMM: line.add("��c��� ��� �ļ�"); return line;
				case NUCL:
					int i = (S_Fingerprint.lowerPlayerList.size() > 0 ? 1 : 0) + (S_Fingerprint.upperPlayerList.size() > 0 ? 1 : 0);
					line.add("��c���ڷ� ���ر��� (" + i + "/2) (" + (Sabotage.Remain_Tick[0]/20+1) + "s)");
					break;
				case OXYG:
					int i2 = (S_Oxygen.CLEARED);
					line.add("��c��� ������ (" + i2 + "/2) (" + (Sabotage.Remain_Tick[0]/20+1) + "s)");
					break;
				case DOOR:
					break;
				case ELEC:
					line.add("��c���� ��ġ��");
					break;
				}
			}
			for(int index=0;index<pd.getMissions().size();index++) {
				if(!(pd.getMissions().get(index) instanceof Sabotage))
				line.add(pd.getMissions().get(index).getScoreboardMessage());
			}
		}
		
		return line;
	}
	

}
