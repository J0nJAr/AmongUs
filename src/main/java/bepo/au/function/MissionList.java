package bepo.au.function;

import bepo.au.base.Mission;
import bepo.au.base.Mission.MissionType;
import bepo.au.base.Sabotage;
import bepo.au.missions.*;
import bepo.au.sabo.S_Communication;
import bepo.au.sabo.S_Fingerprint;
import bepo.au.sabo.S_FixLights;
import bepo.au.sabo.S_Oxygen;
import org.bukkit.Location;

import java.util.ArrayList;
import java.util.List;

public class MissionList {
	
	public static List<Sabotage> SABOTAGE = new ArrayList<Sabotage>();
	public static List<Mission> COMMON = new ArrayList<Mission>();
	public static List<Mission> EASY = new ArrayList<Mission>();
	public static List<Mission> HARD = new ArrayList<Mission>();
	
	// COMMON 2��
	public final static C_FixWriting fixwriting = 				new C_FixWriting(MissionType.COMMON, "FixWiring", "�輱 ����", 3, (Location) null);
	public final static C_Card card 		= 							new C_Card(MissionType.COMMON, "Card", "ī�� �ܱ�", 1, (Location) null);
	
	// EASY 8��
	public final static E_ChartCourse chartcourse = 			new E_ChartCourse(MissionType.EASY, "ChartCourse", "�׷� ��ȹ", 1, (Location) null);
	public final static E_StablizeSteering stablizesteering = 	new E_StablizeSteering(MissionType.EASY, "StablizeSteering", "�׷� ����", 1, (Location) null);
	public final static E_EmptyChute emptychute = 			new E_EmptyChute(MissionType.EASY, "EmptyChute", "������� û��", 1, (Location) null);
	public final static E_ActivatingShield activatingshield = 	new E_ActivatingShield(MissionType.EASY, "ActivatingShield", "�ǵ� ��ġ��", 1, (Location) null);
	public final static E_DivertPower divertpower =			new E_DivertPower(MissionType.EASY, "DivertPower", "������ ��ȯ�ϱ�", 2, (Location) null);
	public final static E_DistributePower distributepower =	new E_DistributePower(MissionType.EASY, "DistributePower", "������ �����ϱ�", 1, (Location) null);
	public final static E_OpenManifold openmanifold =		new E_OpenManifold(MissionType.EASY, "OpenManifold", "�Ŵ����� ����", 1, (Location) null);
	public final static E_Data data =								new E_Data(MissionType.EASY, "Data", "������ �����ϱ�", 2, (Location) null);
	 
	// HARD 7��
	public final static H_InspectSample inspectsample = 	new H_InspectSample(MissionType.HARD, "InspectSample", "���� �м�", 1, (Location) null);
	public final static H_Scanning scanning =					new H_Scanning(MissionType.HARD, "Scanning", "��ĵ ����", 1, (Location) null);
	public final static H_ActivatingReactor activatingreactor = new H_ActivatingReactor(MissionType.HARD, "ActivatingReactor", "���ڷ� �����ϱ�", 1, (Location) null);
	public final static H_AlignEngine alignengine =			new H_AlignEngine(MissionType.HARD, "AlignEngine", "���� ��� ���Ľ�Ű��", 2, (Location) null);
	public final static H_Gas gas = 								new H_Gas(MissionType.HARD, "Gas", "���� �����ϱ�", 4, (Location) null);
	public final static H_Shooting shooting =					new H_Shooting(MissionType.HARD, "Shooting", "���༺ �ı��ϱ�", 1, (Location) null);
	public final static H_EmptyGarbage emptygarbage = 	new H_EmptyGarbage(MissionType.HARD, "EmptyGarbage", "������ ������", 2, (Location) null);
	
	// SABOTAGE 5��
	public final static S_Communication communication =	new S_Communication(MissionType.SABOTAGE, "Communication", "��� ��� ����", 1, (Location) null);
	public final static S_Fingerprint fingerprint	=				new S_Fingerprint(MissionType.SABOTAGE, "Fingerprint", "���ڷ� Ȱ��ȭ", 1, (Location) null);
	public final static S_FixLights fixlights = 					new S_FixLights(MissionType.SABOTAGE, "FixLights", "���� ����", 1, (Location) null);
	public final static S_Oxygen oxygen =						new S_Oxygen(MissionType.SABOTAGE, "Oxygen", "��� Ȱ��ȭ", 2, (Location) null);

}
