package out;

import util.Util;

public class PlayerGo extends OutPacket {
	
	String stageName;
	byte scenario;
	String entrance;
	boolean startScript;
	
	public PlayerGo(String stageName, int scenario, String entrance, boolean startScript) {
		this.stageName = stageName;
		this.scenario = (byte) scenario;
		this.entrance = entrance;
		this.startScript = startScript;
	}

	@Override
	public OutPacketType getOutPacketType() {
		return OutPacketType.PlayerGo;
	}

	@Override
	public byte[] getPlainData() {
		return Util.mergeArrays(new byte[] {scenario, (byte) stageName.length(), (byte) entrance.length()},
				stageName.getBytes(), entrance.getBytes(), new byte[] {(byte) (startScript ? 1 : 0)});
	}
	
	
	public static final String[] STAGES = new String[]{"CapWorldHomeStage", "WaterfallWorldHomeStage", "SandWorldHomeStage", "LakeWorldHomeStage", "ForestWorldHomeStage", "CloudWorldHomeStage", "ClashWorldHomeStage", "CityWorldHomeStage", "SnowWorldHomeStage", "SeaWorldHomeStage", "LavaWorldHomeStage", "BossRaidWorldHomeStage", "SkyWorldHomeStage", "MoonWorldHomeStage", "PeachWorldHomeStage", "Special1WorldHomeStage", "Special2WorldHomeStage", "MoonWorldBasementStage", "MoonWorldKoopa1Stage", "MoonWorldKoopa2Stage", "DotTowerExStage", "Special2WorldLavaStage", "FrogSearchExStage", "Cube2DExStage", "SandWorldPyramid001Stage", "SeaWorldSecretStage", "CapAppearExStage", "ForestWorldWaterExStage", "PeachWorldShopStage", "SkyWorldTreasureStage", "SnowWorldRaceExStage", "PushBlockExStage", "ShootingCityYoshiExStage", "SnowWorldLobbyExStage", "DemoChangeWorldFindKoopaShipStage", "DonsukeExStage", "BullRunExStage", "DemoBossRaidAttackStage", "PeachWorldPictureGiantWanderBossStage", "CityWorldShop01Stage", "PackunPoisonNoCapExStage", "RevengeBossKnuckleStage", "SenobiTowerYoshiExStage", "SnowWorldShopStage", "Theater2DExStage", "LavaWorldBubbleLaneExStage", "DemoChangeWorldBossRaidAttackStage", "Special1WorldTowerStackerStage", "Special1WorldTowerFireBlowerStage", "SandWorldCostumeStage", "LavaWorldFenceLiftExStage", "ForestWorldBossStage", "BikeSteelExStage", "FastenerExStage", "SandWorldRotateExStage", "SeaWorldSneakingManStage", "TrexBikeExStage", "SnowWorldRaceHardExStage", "SandWorldPressExStage", "DemoCrashHomeStage", "WaterValleyExStage", "CapWorldTowerStage", "KillerRailCollisionExStage", "ByugoPuzzleExStage", "CityWorldFactoryStage", "Special2WorldKoopaStage", "SandWorldUnderground001Stage", "IceWaterBlockExStage", "PackunPoisonExStage", "DemoEndingStage", "DotHardExStage", "SenobiTowerExStage", "GabuzouClockExStage", "PeachWorldPictureBossMagmaStage", "ClashWorldShopStage", "SeaWorldCostumeStage", "DemoCrashHomeFallStage", "MoonWorldSphinxRoom", "SandWorldUnderground000Stage", "ShootingCityExStage", "PeachWorldPictureBossRaidStage", "RailCollisionExStage", "RevengeBossRaidStage", "LavaWorldTreasureStage", "Special2WorldCloudStage", "DemoWorldMoveForwardArriveStage", "MoonWorldWeddingRoomStage", "SeaWorldVibrationStage", "DemoWorldMoveMoonForwardStage", "ForestWorldWoodsTreasureStage", "ForestWorldWoodsStage", "ForestWorldCloudBonusExStage", "PeachWorldPictureMofumofuStage", "CapRotatePackunExStage", "GotogotonExStage", "IceWalkerExStage", "PeachWorldPictureBossKnuckleStage", "RevengeBossMagmaStage", "ForestWorldTowerStage", "DemoStartWorldWaterfallStage", "PeachWorldCastleStage", "SkyWorldCostumeStage", "DemoWorldMoveMoonForwardFirstStage", "SkyWorldShopStage", "SnowWorldTownStage", "DemoLavaWorldScenario1EndStage", "RevengeGiantWanderBossStage", "SandWorldSphinxExStage", "DemoWorldMoveMoonBackwardStage", "SnowWorldRace000Stage", "SnowWorldCostumeStage", "BikeSteelNoCapExStage", "CapAppearLavaLiftExStage", "DemoHackKoopaStage", "RadioControlExStage", "TrexPoppunExStage", "TsukkunClimbExStage", "LavaWorldShopStage", "SandWorldSecretStage", "FukuwaraiKuriboStage", "ForkExStage", "JangoExStage", "DemoOpeningStage", "LakeWorldShopStage", "PoleGrabCeilExStage", "PoisonWaveExStage", "DemoWorldWarpHoleStage", "SandWorldVibrationStage", "LavaWorldClockExStage", "FukuwaraiMarioStage", "HomeShipInsideStage", "ImomuPoisonExStage", "CityWorldMainTowerStage", "SnowWorldRaceTutorialStage", "WorldStage", "SandWorldSlotStage", "RollingExStage", "SnowWorldRace001Stage", "SnowWorldLobby000Stage", "MoonWorldWeddingRoom2Stage", "LavaWorldUpDownExStage", "RevengeForestBossStage", "AnimalChaseExStage", "SeaWorldUtsuboCaveStage", "DemoTakeOffKoopaForMoonStage", "MoonWorldCaptureParadeStage", "LavaWorldCostumeStage", "Lift2DExStage", "Special1WorldTowerBombTailStage", "MoonWorldShopRoom", "SnowWorldCloudBonusExStage", "TogezoRotateExStage", "FrogPoisonExStage", "SkyWorldCloudBonusExStage", "KaronWingTowerStage", "WanwanClashExStage", "WaterTubeExStage", "DemoMeetCapNpcSubStage", "MoonAthleticExStage", "CloudExStage", "DemoHackFirstStage", "PeachWorldPictureBossForestStage", "ShootingElevatorExStage", "PeachWorldCostumeStage", "MeganeLiftExStage", "TrampolineWallCatchExStage", "CityWorldSandSlotStage", "ForestWorldBonusStage", "SwingSteelExStage", "TsukkunRotateExStage", "RocketFlowerExStage", "WindBlowExStage", "ForestWorldWoodsCostumeStage", "ElectricWireExStage", "DemoWorldMoveBackwardArriveStage", "Galaxy2DExStage", "IceWaterDashExStage", "ReflectBombExStage", "LavaWorldUpDownYoshiExStage", "JizoSwitchExStage", "RevengeMofumofuStage", "SnowWorldLobby001Stage", "YoshiCloudExStage", "KillerRoadExStage", "CityPeopleRoadStage", "Note2D3DRoomExStage", "DemoWorldMoveBackwardStage", "KillerRoadNoCapExStage", "DemoWorldMoveForwardStage", "SandWorldMeganeExStage", "LavaWorldExcavationExStage", "Special1WorldTowerCapThrowerStage", "DemoChangeWorldStage", "FogMountainExStage", "SandWorldPyramid000Stage", "SandWorldShopStage", "SandWorldKillerExStage", "PoleKillerExStage", "DemoWorldMoveForwardFirstStage", "StaffRollMoonRockDemo"};


}
