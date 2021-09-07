package backGroundMap;

import java.awt.image.BufferedImage;

import frontMap.FrontMapItem;
import gameEffect.Animation;
import gameWorld.GameWorldMegamanX;

public class InitAnimationBGMegamanX {

	private Animation item1, item2, item3, item4, item5;
	private Animation item6, item7, item7daoNguoc, item8, item8daoNguoc, item9, item10, item10daoNguoc;
	private Animation item12s, item13s, item13sdaoNguoc, item14s, item14sdaoNguoc;
	private Animation item15s, item16s, item17s, item18s, item19s, item20s;
	private Animation item21s, item22s, item22sDaoNguoc, item23s, item23sDaoNguoc, item24s;
	private Animation npc;

	private BackGroundMapMegamanX b;

	public InitAnimationBGMegamanX(BackGroundMapMegamanX b) {
		this.b = b;
	}

	public void create() {
		item1 = b.getGame().getInputData().getListAnimation().get("introStageItem1");
		AnimationBackGroundMap item11 = new AnimationBackGroundMap(1102, 2634, (GameWorldMegamanX) b.getGame(), item1,
				32, 14);
		AnimationBackGroundMap item12 = new AnimationBackGroundMap(1166, 2634, (GameWorldMegamanX) b.getGame(), item1,
				32, 14);
		AnimationBackGroundMap item13 = new AnimationBackGroundMap(1230, 2634, (GameWorldMegamanX) b.getGame(), item1,
				32, 14);
		AnimationBackGroundMap item14 = new AnimationBackGroundMap(1294, 2634, (GameWorldMegamanX) b.getGame(), item1,
				32, 14);
		AnimationBackGroundMap item15 = new AnimationBackGroundMap(1358, 2634, (GameWorldMegamanX) b.getGame(), item1,
				32, 14);
		AnimationBackGroundMap item16 = new AnimationBackGroundMap(1422, 2634, (GameWorldMegamanX) b.getGame(), item1,
				32, 14);
		AnimationBackGroundMap item17 = new AnimationBackGroundMap(1486, 2634, (GameWorldMegamanX) b.getGame(), item1,
				32, 14);
		AnimationBackGroundMap item18 = new AnimationBackGroundMap(1550, 2634, (GameWorldMegamanX) b.getGame(), item1,
				32, 14);
		AnimationBackGroundMap item19 = new AnimationBackGroundMap(1614, 2634, (GameWorldMegamanX) b.getGame(), item1,
				32, 14);
		AnimationBackGroundMap item110 = new AnimationBackGroundMap(1678, 2634, (GameWorldMegamanX) b.getGame(), item1,
				32, 14);
		AnimationBackGroundMap item111 = new AnimationBackGroundMap(1742, 2634, (GameWorldMegamanX) b.getGame(), item1,
				32, 14);
		AnimationBackGroundMap item112 = new AnimationBackGroundMap(1806, 2634, (GameWorldMegamanX) b.getGame(), item1,
				32, 14);
		AnimationBackGroundMap item113 = new AnimationBackGroundMap(1870, 2634, (GameWorldMegamanX) b.getGame(), item1,
				32, 14);
		AnimationBackGroundMap item114 = new AnimationBackGroundMap(1934, 2634, (GameWorldMegamanX) b.getGame(), item1,
				32, 14);
		AnimationBackGroundMap item115 = new AnimationBackGroundMap(1998, 2634, (GameWorldMegamanX) b.getGame(), item1,
				32, 14);
		AnimationBackGroundMap item116 = new AnimationBackGroundMap(2062, 2634, (GameWorldMegamanX) b.getGame(), item1,
				32, 14);
		AnimationBackGroundMap item117 = new AnimationBackGroundMap(2126, 2634, (GameWorldMegamanX) b.getGame(), item1,
				32, 14);
		AnimationBackGroundMap item118 = new AnimationBackGroundMap(2190, 2634, (GameWorldMegamanX) b.getGame(), item1,
				32, 14);
		AnimationBackGroundMap item119 = new AnimationBackGroundMap(2254, 2634, (GameWorldMegamanX) b.getGame(), item1,
				32, 14);
		AnimationBackGroundMap item120 = new AnimationBackGroundMap(2318, 2634, (GameWorldMegamanX) b.getGame(), item1,
				32, 14);
		AnimationBackGroundMap item121 = new AnimationBackGroundMap(2382, 2634, (GameWorldMegamanX) b.getGame(), item1,
				32, 14);
		AnimationBackGroundMap item122 = new AnimationBackGroundMap(2446, 2634, (GameWorldMegamanX) b.getGame(), item1,
				32, 14);
		AnimationBackGroundMap item123 = new AnimationBackGroundMap(2638, 3114, (GameWorldMegamanX) b.getGame(), item1,
				32, 14);
		AnimationBackGroundMap item124 = new AnimationBackGroundMap(2702, 3114, (GameWorldMegamanX) b.getGame(), item1,
				32, 14);
		AnimationBackGroundMap item125 = new AnimationBackGroundMap(2766, 3114, (GameWorldMegamanX) b.getGame(), item1,
				32, 14);
		AnimationBackGroundMap item126 = new AnimationBackGroundMap(2830, 3114, (GameWorldMegamanX) b.getGame(), item1,
				32, 14);
		AnimationBackGroundMap item127 = new AnimationBackGroundMap(2894, 3114, (GameWorldMegamanX) b.getGame(), item1,
				32, 14);
		AnimationBackGroundMap item128 = new AnimationBackGroundMap(2958, 3114, (GameWorldMegamanX) b.getGame(), item1,
				32, 14);
		AnimationBackGroundMap item129 = new AnimationBackGroundMap(3022, 3114, (GameWorldMegamanX) b.getGame(), item1,
				32, 14);
		AnimationBackGroundMap item130 = new AnimationBackGroundMap(3086, 3114, (GameWorldMegamanX) b.getGame(), item1,
				32, 14);
		AnimationBackGroundMap item131 = new AnimationBackGroundMap(3150, 3114, (GameWorldMegamanX) b.getGame(), item1,
				32, 14);
		AnimationBackGroundMap item132 = new AnimationBackGroundMap(3214, 3114, (GameWorldMegamanX) b.getGame(), item1,
				32, 14);
		AnimationBackGroundMap item133 = new AnimationBackGroundMap(3278, 3114, (GameWorldMegamanX) b.getGame(), item1,
				32, 14);
		AnimationBackGroundMap item134 = new AnimationBackGroundMap(3342, 3114, (GameWorldMegamanX) b.getGame(), item1,
				32, 14);
		AnimationBackGroundMap item135 = new AnimationBackGroundMap(3406, 3114, (GameWorldMegamanX) b.getGame(), item1,
				32, 14);
		AnimationBackGroundMap item136 = new AnimationBackGroundMap(3470, 3114, (GameWorldMegamanX) b.getGame(), item1,
				32, 14);
		AnimationBackGroundMap item137 = new AnimationBackGroundMap(3534, 3114, (GameWorldMegamanX) b.getGame(), item1,
				32, 14);
		AnimationBackGroundMap item138 = new AnimationBackGroundMap(3598, 3114, (GameWorldMegamanX) b.getGame(), item1,
				32, 14);
		AnimationBackGroundMap item139 = new AnimationBackGroundMap(3662, 3114, (GameWorldMegamanX) b.getGame(), item1,
				32, 14);
		AnimationBackGroundMap item140 = new AnimationBackGroundMap(3726, 3114, (GameWorldMegamanX) b.getGame(), item1,
				32, 14);
		AnimationBackGroundMap item141 = new AnimationBackGroundMap(3790, 3114, (GameWorldMegamanX) b.getGame(), item1,
				32, 14);
		AnimationBackGroundMap item142 = new AnimationBackGroundMap(3854, 3114, (GameWorldMegamanX) b.getGame(), item1,
				32, 14);
		AnimationBackGroundMap item143 = new AnimationBackGroundMap(3918, 3114, (GameWorldMegamanX) b.getGame(), item1,
				32, 14);
		AnimationBackGroundMap item144 = new AnimationBackGroundMap(3982, 3114, (GameWorldMegamanX) b.getGame(), item1,
				32, 14);
		AnimationBackGroundMap item145 = new AnimationBackGroundMap(6222, 3114, (GameWorldMegamanX) b.getGame(), item1,
				32, 14);
		AnimationBackGroundMap item146 = new AnimationBackGroundMap(6286, 3114, (GameWorldMegamanX) b.getGame(), item1,
				32, 14);
		AnimationBackGroundMap item147 = new AnimationBackGroundMap(6350, 3114, (GameWorldMegamanX) b.getGame(), item1,
				32, 14);
		AnimationBackGroundMap item148 = new AnimationBackGroundMap(6414, 3114, (GameWorldMegamanX) b.getGame(), item1,
				32, 14);
		AnimationBackGroundMap item149 = new AnimationBackGroundMap(6478, 3114, (GameWorldMegamanX) b.getGame(), item1,
				32, 14);
		AnimationBackGroundMap item150 = new AnimationBackGroundMap(6798, 1578, (GameWorldMegamanX) b.getGame(), item1,
				32, 14);
		AnimationBackGroundMap item151 = new AnimationBackGroundMap(6862, 1578, (GameWorldMegamanX) b.getGame(), item1,
				32, 14);
		AnimationBackGroundMap item152 = new AnimationBackGroundMap(6926, 1578, (GameWorldMegamanX) b.getGame(), item1,
				32, 14);
		AnimationBackGroundMap item153 = new AnimationBackGroundMap(6990, 1578, (GameWorldMegamanX) b.getGame(), item1,
				32, 14);
		AnimationBackGroundMap item154 = new AnimationBackGroundMap(7054, 1578, (GameWorldMegamanX) b.getGame(), item1,
				32, 14);
		AnimationBackGroundMap item155 = new AnimationBackGroundMap(7118, 1578, (GameWorldMegamanX) b.getGame(), item1,
				32, 14);
		AnimationBackGroundMap item156 = new AnimationBackGroundMap(7182, 1578, (GameWorldMegamanX) b.getGame(), item1,
				32, 14);
		AnimationBackGroundMap item157 = new AnimationBackGroundMap(7246, 1578, (GameWorldMegamanX) b.getGame(), item1,
				32, 14);
		AnimationBackGroundMap item158 = new AnimationBackGroundMap(7310, 1578, (GameWorldMegamanX) b.getGame(), item1,
				32, 14);
		AnimationBackGroundMap item159 = new AnimationBackGroundMap(7374, 1578, (GameWorldMegamanX) b.getGame(), item1,
				32, 14);
		AnimationBackGroundMap item160 = new AnimationBackGroundMap(7438, 1578, (GameWorldMegamanX) b.getGame(), item1,
				32, 14);
		AnimationBackGroundMap item161 = new AnimationBackGroundMap(7502, 1578, (GameWorldMegamanX) b.getGame(), item1,
				32, 14);
		AnimationBackGroundMap item162 = new AnimationBackGroundMap(7566, 1578, (GameWorldMegamanX) b.getGame(), item1,
				32, 14);
		b.getListAnimationMap().add(item11);
		b.getListAnimationMap().add(item12);
		b.getListAnimationMap().add(item13);
		b.getListAnimationMap().add(item14);
		b.getListAnimationMap().add(item15);
		b.getListAnimationMap().add(item16);
		b.getListAnimationMap().add(item17);
		b.getListAnimationMap().add(item18);
		b.getListAnimationMap().add(item19);
		b.getListAnimationMap().add(item110);
		b.getListAnimationMap().add(item111);
		b.getListAnimationMap().add(item112);
		b.getListAnimationMap().add(item113);
		b.getListAnimationMap().add(item114);
		b.getListAnimationMap().add(item115);
		b.getListAnimationMap().add(item116);
		b.getListAnimationMap().add(item117);
		b.getListAnimationMap().add(item118);
		b.getListAnimationMap().add(item119);
		b.getListAnimationMap().add(item120);
		b.getListAnimationMap().add(item121);
		b.getListAnimationMap().add(item122);
		b.getListAnimationMap().add(item123);
		b.getListAnimationMap().add(item124);
		b.getListAnimationMap().add(item125);
		b.getListAnimationMap().add(item126);
		b.getListAnimationMap().add(item127);
		b.getListAnimationMap().add(item128);
		b.getListAnimationMap().add(item129);
		b.getListAnimationMap().add(item130);
		b.getListAnimationMap().add(item131);
		b.getListAnimationMap().add(item132);
		b.getListAnimationMap().add(item133);
		b.getListAnimationMap().add(item134);
		b.getListAnimationMap().add(item135);
		b.getListAnimationMap().add(item136);
		b.getListAnimationMap().add(item137);
		b.getListAnimationMap().add(item138);
		b.getListAnimationMap().add(item139);
		b.getListAnimationMap().add(item140);
		b.getListAnimationMap().add(item141);
		b.getListAnimationMap().add(item142);
		b.getListAnimationMap().add(item143);
		b.getListAnimationMap().add(item144);
		b.getListAnimationMap().add(item145);
		b.getListAnimationMap().add(item146);
		b.getListAnimationMap().add(item147);
		b.getListAnimationMap().add(item148);
		b.getListAnimationMap().add(item149);
		b.getListAnimationMap().add(item150);
		b.getListAnimationMap().add(item151);
		b.getListAnimationMap().add(item152);
		b.getListAnimationMap().add(item153);
		b.getListAnimationMap().add(item154);
		b.getListAnimationMap().add(item155);
		b.getListAnimationMap().add(item156);
		b.getListAnimationMap().add(item157);
		b.getListAnimationMap().add(item158);
		b.getListAnimationMap().add(item159);
		b.getListAnimationMap().add(item160);
		b.getListAnimationMap().add(item161);
		b.getListAnimationMap().add(item162);

		item2 = new Animation(b.getGame().getInputData().getListAnimation().get("introStageItem2"));
		AnimationBackGroundMap item21 = new AnimationBackGroundMap(1248, 2656, (GameWorldMegamanX) b.getGame(), item2,
				60, 26);
		AnimationBackGroundMap item22 = new AnimationBackGroundMap(1634, 2656, (GameWorldMegamanX) b.getGame(), item2,
				60, 26);
		AnimationBackGroundMap item23 = new AnimationBackGroundMap(1858, 2656, (GameWorldMegamanX) b.getGame(), item2,
				60, 26);
		AnimationBackGroundMap item24 = new AnimationBackGroundMap(2242, 2656, (GameWorldMegamanX) b.getGame(), item2,
				60, 26);
		AnimationBackGroundMap item25 = new AnimationBackGroundMap(6626, 3200, (GameWorldMegamanX) b.getGame(), item2,
				60, 26);
		AnimationBackGroundMap item26 = new AnimationBackGroundMap(7490, 1600, (GameWorldMegamanX) b.getGame(), item2,
				60, 26);
		b.getListAnimationMap().add(item21);
		b.getListAnimationMap().add(item22);
		b.getListAnimationMap().add(item23);
		b.getListAnimationMap().add(item24);
		b.getListAnimationMap().add(item25);
		b.getListAnimationMap().add(item26);

		item3 = new Animation(b.getGame().getInputData().getListAnimation().get("introStageItem3"));
		AnimationBackGroundMap item31 = new AnimationBackGroundMap(1186, 2904, (GameWorldMegamanX) b.getGame(), item3,
				28, 34);
		AnimationBackGroundMap item32 = new AnimationBackGroundMap(3586, 3354, (GameWorldMegamanX) b.getGame(), item3,
				28, 34);
		AnimationBackGroundMap item33 = new AnimationBackGroundMap(6850, 3416, (GameWorldMegamanX) b.getGame(), item3,
				28, 34);
		AnimationBackGroundMap item34 = new AnimationBackGroundMap(8610, 1240, (GameWorldMegamanX) b.getGame(), item3,
				28, 34);
		b.getListAnimationMap().add(item31);
		b.getListAnimationMap().add(item32);
		b.getListAnimationMap().add(item33);
		b.getListAnimationMap().add(item34);

		item4 = new Animation(b.getGame().getInputData().getListAnimation().get("introStageItem4"));
		AnimationBackGroundMap item41 = new AnimationBackGroundMap(1470, 2778, (GameWorldMegamanX) b.getGame(), item4,
				30, 64);
		AnimationBackGroundMap item42 = new AnimationBackGroundMap(2430, 2778, (GameWorldMegamanX) b.getGame(), item4,
				30, 64);
		AnimationBackGroundMap item43 = new AnimationBackGroundMap(2366, 3190, (GameWorldMegamanX) b.getGame(), item4,
				30, 64);
		AnimationBackGroundMap item44 = new AnimationBackGroundMap(3006, 3322, (GameWorldMegamanX) b.getGame(), item4,
				30, 64);
		AnimationBackGroundMap item45 = new AnimationBackGroundMap(6334, 3450, (GameWorldMegamanX) b.getGame(), item4,
				30, 64);
		AnimationBackGroundMap item46 = new AnimationBackGroundMap(6494, 3258, (GameWorldMegamanX) b.getGame(), item4,
				30, 64);
		AnimationBackGroundMap item47 = new AnimationBackGroundMap(7518, 1626, (GameWorldMegamanX) b.getGame(), item4,
				30, 64);
		b.getListAnimationMap().add(item41);
		b.getListAnimationMap().add(item42);
		b.getListAnimationMap().add(item43);
		b.getListAnimationMap().add(item44);
		b.getListAnimationMap().add(item45);
		b.getListAnimationMap().add(item46);
		b.getListAnimationMap().add(item47);

		item5 = new Animation(b.getGame().getInputData().getListAnimation().get("introStageItem5"));
		AnimationBackGroundMap item51 = new AnimationBackGroundMap(2174, 3322, (GameWorldMegamanX) b.getGame(), item5,
				32, 96);
		AnimationBackGroundMap item52 = new AnimationBackGroundMap(2558, 3322, (GameWorldMegamanX) b.getGame(), item5,
				32, 96);
		AnimationBackGroundMap item53 = new AnimationBackGroundMap(3934, 3674, (GameWorldMegamanX) b.getGame(), item5,
				32, 96);
		AnimationBackGroundMap item54 = new AnimationBackGroundMap(4094, 3834, (GameWorldMegamanX) b.getGame(), item5,
				32, 96);
		AnimationBackGroundMap item55 = new AnimationBackGroundMap(8446, 1594, (GameWorldMegamanX) b.getGame(), item5,
				32, 96);
		b.getListAnimationMap().add(item51);
		b.getListAnimationMap().add(item52);
		b.getListAnimationMap().add(item53);
		b.getListAnimationMap().add(item54);
		b.getListAnimationMap().add(item55);

		item6 = new Animation(b.getGame().getInputData().getListAnimation().get("introStageItem6"));
		AnimationBackGroundMap item61 = new AnimationBackGroundMap(1148, 3012, (GameWorldMegamanX) b.getGame(), item6,
				68, 20);
		AnimationBackGroundMap item62 = new AnimationBackGroundMap(1276, 3012, (GameWorldMegamanX) b.getGame(), item6,
				68, 20);
		AnimationBackGroundMap item63 = new AnimationBackGroundMap(1788, 3012, (GameWorldMegamanX) b.getGame(), item6,
				68, 20);
		AnimationBackGroundMap item64 = new AnimationBackGroundMap(1916, 3012, (GameWorldMegamanX) b.getGame(), item6,
				68, 20);
		AnimationBackGroundMap item65 = new AnimationBackGroundMap(2076, 2948, (GameWorldMegamanX) b.getGame(), item6,
				68, 20);
		AnimationBackGroundMap item66 = new AnimationBackGroundMap(2204, 2948, (GameWorldMegamanX) b.getGame(), item6,
				68, 20);
		AnimationBackGroundMap item67 = new AnimationBackGroundMap(2172, 3492, (GameWorldMegamanX) b.getGame(), item6,
				68, 20);
		AnimationBackGroundMap item68 = new AnimationBackGroundMap(2300, 3492, (GameWorldMegamanX) b.getGame(), item6,
				68, 20);
		AnimationBackGroundMap item69 = new AnimationBackGroundMap(2428, 3492, (GameWorldMegamanX) b.getGame(), item6,
				68, 20);
		AnimationBackGroundMap item610 = new AnimationBackGroundMap(2556, 3492, (GameWorldMegamanX) b.getGame(), item6,
				68, 20);
		AnimationBackGroundMap item611 = new AnimationBackGroundMap(2684, 3492, (GameWorldMegamanX) b.getGame(), item6,
				68, 20);
		AnimationBackGroundMap item612 = new AnimationBackGroundMap(3420, 3492, (GameWorldMegamanX) b.getGame(), item6,
				68, 20);
		AnimationBackGroundMap item613 = new AnimationBackGroundMap(3548, 3492, (GameWorldMegamanX) b.getGame(), item6,
				68, 20);
		AnimationBackGroundMap item614 = new AnimationBackGroundMap(3676, 3492, (GameWorldMegamanX) b.getGame(), item6,
				68, 20);
		AnimationBackGroundMap item615 = new AnimationBackGroundMap(3804, 3492, (GameWorldMegamanX) b.getGame(), item6,
				68, 20);
		AnimationBackGroundMap item616 = new AnimationBackGroundMap(6332, 3364, (GameWorldMegamanX) b.getGame(), item6,
				68, 20);
		AnimationBackGroundMap item617 = new AnimationBackGroundMap(6460, 3364, (GameWorldMegamanX) b.getGame(), item6,
				68, 20);
		AnimationBackGroundMap item618 = new AnimationBackGroundMap(6588, 3364, (GameWorldMegamanX) b.getGame(), item6,
				68, 20);
		AnimationBackGroundMap item619 = new AnimationBackGroundMap(6940, 3428, (GameWorldMegamanX) b.getGame(), item6,
				68, 20);
		AnimationBackGroundMap item620 = new AnimationBackGroundMap(7068, 1828, (GameWorldMegamanX) b.getGame(), item6,
				68, 20);
		AnimationBackGroundMap item621 = new AnimationBackGroundMap(7196, 1828, (GameWorldMegamanX) b.getGame(), item6,
				68, 20);
		AnimationBackGroundMap item622 = new AnimationBackGroundMap(7324, 1828, (GameWorldMegamanX) b.getGame(), item6,
				68, 20);
		AnimationBackGroundMap item623 = new AnimationBackGroundMap(7676, 1956, (GameWorldMegamanX) b.getGame(), item6,
				68, 20);
		AnimationBackGroundMap item624 = new AnimationBackGroundMap(7676, 1956, (GameWorldMegamanX) b.getGame(), item6,
				68, 20);
		AnimationBackGroundMap item625 = new AnimationBackGroundMap(7804, 1956, (GameWorldMegamanX) b.getGame(), item6,
				68, 20);
		AnimationBackGroundMap item626 = new AnimationBackGroundMap(7932, 1956, (GameWorldMegamanX) b.getGame(), item6,
				68, 20);
		AnimationBackGroundMap item627 = new AnimationBackGroundMap(8060, 1956, (GameWorldMegamanX) b.getGame(), item6,
				68, 20);
		AnimationBackGroundMap item628 = new AnimationBackGroundMap(8188, 1956, (GameWorldMegamanX) b.getGame(), item6,
				68, 20);
		AnimationBackGroundMap item629 = new AnimationBackGroundMap(8316, 1956, (GameWorldMegamanX) b.getGame(), item6,
				68, 20);
		AnimationBackGroundMap item630 = new AnimationBackGroundMap(8444, 1956, (GameWorldMegamanX) b.getGame(), item6,
				68, 20);
		b.getListAnimationMap().add(item61);
		b.getListAnimationMap().add(item62);
		b.getListAnimationMap().add(item63);
		b.getListAnimationMap().add(item64);
		b.getListAnimationMap().add(item65);
		b.getListAnimationMap().add(item66);
		b.getListAnimationMap().add(item67);
		b.getListAnimationMap().add(item68);
		b.getListAnimationMap().add(item69);
		b.getListAnimationMap().add(item610);
		b.getListAnimationMap().add(item611);
		b.getListAnimationMap().add(item612);
		b.getListAnimationMap().add(item613);
		b.getListAnimationMap().add(item614);
		b.getListAnimationMap().add(item615);
		b.getListAnimationMap().add(item616);
		b.getListAnimationMap().add(item617);
		b.getListAnimationMap().add(item618);
		b.getListAnimationMap().add(item619);
		b.getListAnimationMap().add(item620);
		b.getListAnimationMap().add(item621);
		b.getListAnimationMap().add(item622);
		b.getListAnimationMap().add(item623);
		b.getListAnimationMap().add(item624);
		b.getListAnimationMap().add(item625);
		b.getListAnimationMap().add(item626);
		b.getListAnimationMap().add(item627);
		b.getListAnimationMap().add(item628);
		b.getListAnimationMap().add(item629);
		b.getListAnimationMap().add(item630);

		item7 = new Animation(b.getGame().getInputData().getListAnimation().get("introStageItem7"));
		item7daoNguoc = new Animation(b.getGame().getInputData().getListAnimation().get("introStageItem7"));
		item7daoNguoc.daoNguoc();
		AnimationBackGroundMap item71 = new AnimationBackGroundMap(1374, 2882, (GameWorldMegamanX) b.getGame(), item7,
				16, 122);
		AnimationBackGroundMap item72 = new AnimationBackGroundMap(3342, 3362, (GameWorldMegamanX) b.getGame(),
				item7daoNguoc, 16, 122);
		AnimationBackGroundMap item73 = new AnimationBackGroundMap(7406, 1826, (GameWorldMegamanX) b.getGame(),
				item7daoNguoc, 16, 122);
		b.getListAnimationMap().add(item71);
		b.getListAnimationMap().add(item72);
		b.getListAnimationMap().add(item73);

		item8 = new Animation(b.getGame().getInputData().getListAnimation().get("introStageItem8"));
		item8daoNguoc = new Animation(b.getGame().getInputData().getListAnimation().get("introStageItem8"));
		item8daoNguoc.daoNguoc();
		AnimationBackGroundMap item81 = new AnimationBackGroundMap(2494, 2684, (GameWorldMegamanX) b.getGame(), item8,
				16, 62);
		AnimationBackGroundMap item82 = new AnimationBackGroundMap(2494, 2746, (GameWorldMegamanX) b.getGame(), item8,
				16, 62);
		AnimationBackGroundMap item83 = new AnimationBackGroundMap(2318, 3002, (GameWorldMegamanX) b.getGame(),
				item8daoNguoc, 16, 62);
		AnimationBackGroundMap item84 = new AnimationBackGroundMap(2430, 3226, (GameWorldMegamanX) b.getGame(), item8,
				16, 62);
		AnimationBackGroundMap item85 = new AnimationBackGroundMap(2094, 3194, (GameWorldMegamanX) b.getGame(),
				item8daoNguoc, 16, 62);
		AnimationBackGroundMap item86 = new AnimationBackGroundMap(2094, 3258, (GameWorldMegamanX) b.getGame(),
				item8daoNguoc, 16, 62);
		AnimationBackGroundMap item87 = new AnimationBackGroundMap(2606, 3130, (GameWorldMegamanX) b.getGame(),
				item8daoNguoc, 16, 62);
		AnimationBackGroundMap item88 = new AnimationBackGroundMap(2606, 3192, (GameWorldMegamanX) b.getGame(),
				item8daoNguoc, 16, 62);
		AnimationBackGroundMap item89 = new AnimationBackGroundMap(4030, 3130, (GameWorldMegamanX) b.getGame(), item8,
				16, 62);
		AnimationBackGroundMap item810 = new AnimationBackGroundMap(4030, 3482, (GameWorldMegamanX) b.getGame(), item8,
				16, 62);
		AnimationBackGroundMap item811 = new AnimationBackGroundMap(4030, 3738, (GameWorldMegamanX) b.getGame(), item8,
				16, 62);
		AnimationBackGroundMap item812 = new AnimationBackGroundMap(3630, 3738, (GameWorldMegamanX) b.getGame(),
				item8daoNguoc, 16, 62);
		AnimationBackGroundMap item813 = new AnimationBackGroundMap(4110, 3706, (GameWorldMegamanX) b.getGame(),
				item8daoNguoc, 16, 62);
		AnimationBackGroundMap item814 = new AnimationBackGroundMap(6590, 3642, (GameWorldMegamanX) b.getGame(), item8,
				16, 62);
		AnimationBackGroundMap item815 = new AnimationBackGroundMap(6590, 3994, (GameWorldMegamanX) b.getGame(), item8,
				16, 62);
		AnimationBackGroundMap item816 = new AnimationBackGroundMap(6526, 3066, (GameWorldMegamanX) b.getGame(), item8,
				16, 62);
		AnimationBackGroundMap item817 = new AnimationBackGroundMap(6910, 3450, (GameWorldMegamanX) b.getGame(), item8,
				16, 62);
		AnimationBackGroundMap item818 = new AnimationBackGroundMap(6910, 3514, (GameWorldMegamanX) b.getGame(), item8,
				16, 62);
		AnimationBackGroundMap item819 = new AnimationBackGroundMap(7038, 3162, (GameWorldMegamanX) b.getGame(), item8,
				16, 62);
		AnimationBackGroundMap item820 = new AnimationBackGroundMap(7038, 2938, (GameWorldMegamanX) b.getGame(), item8,
				16, 62);
		AnimationBackGroundMap item821 = new AnimationBackGroundMap(7038, 2842, (GameWorldMegamanX) b.getGame(), item8,
				16, 62);
		AnimationBackGroundMap item822 = new AnimationBackGroundMap(7038, 2778, (GameWorldMegamanX) b.getGame(), item8,
				16, 62);
		AnimationBackGroundMap item823 = new AnimationBackGroundMap(7038, 2426, (GameWorldMegamanX) b.getGame(), item8,
				16, 62);
		AnimationBackGroundMap item824 = new AnimationBackGroundMap(7038, 2362, (GameWorldMegamanX) b.getGame(), item8,
				16, 62);
		AnimationBackGroundMap item825 = new AnimationBackGroundMap(7038, 2298, (GameWorldMegamanX) b.getGame(), item8,
				16, 62);
		AnimationBackGroundMap item826 = new AnimationBackGroundMap(7038, 1914, (GameWorldMegamanX) b.getGame(), item8,
				16, 62);
		AnimationBackGroundMap item827 = new AnimationBackGroundMap(7038, 1850, (GameWorldMegamanX) b.getGame(), item8,
				16, 62);
		AnimationBackGroundMap item828 = new AnimationBackGroundMap(8158, 1542, (GameWorldMegamanX) b.getGame(), item8,
				16, 62);
		AnimationBackGroundMap item829 = new AnimationBackGroundMap(8574, 1370, (GameWorldMegamanX) b.getGame(), item8,
				16, 62);
		AnimationBackGroundMap item830 = new AnimationBackGroundMap(8574, 1594, (GameWorldMegamanX) b.getGame(), item8,
				16, 62);
		AnimationBackGroundMap item831 = new AnimationBackGroundMap(8574, 1690, (GameWorldMegamanX) b.getGame(), item8,
				16, 62);
		AnimationBackGroundMap item832 = new AnimationBackGroundMap(8574, 1882, (GameWorldMegamanX) b.getGame(), item8,
				16, 62);
		AnimationBackGroundMap item833 = new AnimationBackGroundMap(6766, 2746, (GameWorldMegamanX) b.getGame(),
				item8daoNguoc, 16, 62);
		AnimationBackGroundMap item834 = new AnimationBackGroundMap(6766, 2682, (GameWorldMegamanX) b.getGame(),
				item8daoNguoc, 16, 62);
		AnimationBackGroundMap item835 = new AnimationBackGroundMap(6766, 2362, (GameWorldMegamanX) b.getGame(),
				item8daoNguoc, 16, 62);
		AnimationBackGroundMap item836 = new AnimationBackGroundMap(6766, 2234, (GameWorldMegamanX) b.getGame(),
				item8daoNguoc, 16, 62);
		AnimationBackGroundMap item837 = new AnimationBackGroundMap(6766, 2170, (GameWorldMegamanX) b.getGame(),
				item8daoNguoc, 16, 62);
		AnimationBackGroundMap item838 = new AnimationBackGroundMap(6766, 1914, (GameWorldMegamanX) b.getGame(),
				item8daoNguoc, 16, 62);
		AnimationBackGroundMap item839 = new AnimationBackGroundMap(6766, 1626, (GameWorldMegamanX) b.getGame(),
				item8daoNguoc, 16, 62);
		AnimationBackGroundMap item840 = new AnimationBackGroundMap(6766, 1562, (GameWorldMegamanX) b.getGame(),
				item8daoNguoc, 16, 62);
		AnimationBackGroundMap item841 = new AnimationBackGroundMap(7694, 1690, (GameWorldMegamanX) b.getGame(),
				item8daoNguoc, 16, 62);
		AnimationBackGroundMap item842 = new AnimationBackGroundMap(8238, 1722, (GameWorldMegamanX) b.getGame(),
				item8daoNguoc, 16, 62);
		AnimationBackGroundMap item843 = new AnimationBackGroundMap(8238, 1658, (GameWorldMegamanX) b.getGame(),
				item8daoNguoc, 16, 62);
		AnimationBackGroundMap item844 = new AnimationBackGroundMap(8302, 1178, (GameWorldMegamanX) b.getGame(),
				item8daoNguoc, 16, 62);
		AnimationBackGroundMap item845 = new AnimationBackGroundMap(8302, 1306, (GameWorldMegamanX) b.getGame(),
				item8daoNguoc, 16, 62);
		AnimationBackGroundMap item846 = new AnimationBackGroundMap(8302, 1372, (GameWorldMegamanX) b.getGame(),
				item8daoNguoc, 16, 62);
		AnimationBackGroundMap item847 = new AnimationBackGroundMap(6190, 3130, (GameWorldMegamanX) b.getGame(),
				item8daoNguoc, 16, 62);
		AnimationBackGroundMap item848 = new AnimationBackGroundMap(6190, 3418, (GameWorldMegamanX) b.getGame(),
				item8daoNguoc, 16, 62);
		AnimationBackGroundMap item849 = new AnimationBackGroundMap(6766, 3386, (GameWorldMegamanX) b.getGame(),
				item8daoNguoc, 16, 62);
		AnimationBackGroundMap item850 = new AnimationBackGroundMap(6766, 3514, (GameWorldMegamanX) b.getGame(),
				item8daoNguoc, 16, 62);
		b.getListAnimationMap().add(item81);
		b.getListAnimationMap().add(item82);
		b.getListAnimationMap().add(item83);
		b.getListAnimationMap().add(item84);
		b.getListAnimationMap().add(item85);
		b.getListAnimationMap().add(item86);
		b.getListAnimationMap().add(item87);
		b.getListAnimationMap().add(item88);
		b.getListAnimationMap().add(item89);
		b.getListAnimationMap().add(item810);
		b.getListAnimationMap().add(item811);
		b.getListAnimationMap().add(item812);
		b.getListAnimationMap().add(item813);
		b.getListAnimationMap().add(item814);
		b.getListAnimationMap().add(item815);
		b.getListAnimationMap().add(item816);
		b.getListAnimationMap().add(item817);
		b.getListAnimationMap().add(item818);
		b.getListAnimationMap().add(item819);
		b.getListAnimationMap().add(item820);
		b.getListAnimationMap().add(item821);
		b.getListAnimationMap().add(item822);
		b.getListAnimationMap().add(item823);
		b.getListAnimationMap().add(item824);
		b.getListAnimationMap().add(item825);
		b.getListAnimationMap().add(item826);
		b.getListAnimationMap().add(item827);
		b.getListAnimationMap().add(item828);
		b.getListAnimationMap().add(item829);
		b.getListAnimationMap().add(item830);
		b.getListAnimationMap().add(item831);
		b.getListAnimationMap().add(item832);
		b.getListAnimationMap().add(item833);
		b.getListAnimationMap().add(item834);
		b.getListAnimationMap().add(item835);
		b.getListAnimationMap().add(item836);
		b.getListAnimationMap().add(item837);
		b.getListAnimationMap().add(item838);
		b.getListAnimationMap().add(item839);
		b.getListAnimationMap().add(item840);
		b.getListAnimationMap().add(item841);
		b.getListAnimationMap().add(item842);
		b.getListAnimationMap().add(item843);
		b.getListAnimationMap().add(item844);
		b.getListAnimationMap().add(item845);
		b.getListAnimationMap().add(item846);
		b.getListAnimationMap().add(item847);
		b.getListAnimationMap().add(item848);
		b.getListAnimationMap().add(item849);
		b.getListAnimationMap().add(item850);

		item9 = new Animation(b.getGame().getInputData().getListAnimation().get("introStageItem9"));
		AnimationBackGroundMap item91 = new AnimationBackGroundMap(6382, 3746, (GameWorldMegamanX) b.getGame(), item9,
				16, 58);
		b.getListAnimationMap().add(item91);

		item10 = new Animation(b.getGame().getInputData().getListAnimation().get("introStageItem10"));
		item10daoNguoc = new Animation(b.getGame().getInputData().getListAnimation().get("introStageItem10DaoNguoc"));
		AnimationBackGroundMap item101 = new AnimationBackGroundMap(1090, 2624, (GameWorldMegamanX) b.getGame(), item10,
				12, 4);
		AnimationBackGroundMap item102 = new AnimationBackGroundMap(1106, 2624, (GameWorldMegamanX) b.getGame(),
				item10daoNguoc, 12, 4);
		AnimationBackGroundMap item103 = new AnimationBackGroundMap(1122, 2624, (GameWorldMegamanX) b.getGame(), item10,
				12, 4);
		AnimationBackGroundMap item104 = new AnimationBackGroundMap(1138, 2624, (GameWorldMegamanX) b.getGame(),
				item10daoNguoc, 12, 4);
		AnimationBackGroundMap item105 = new AnimationBackGroundMap(1154, 2624, (GameWorldMegamanX) b.getGame(), item10,
				12, 4);
		AnimationBackGroundMap item106 = new AnimationBackGroundMap(1170, 2624, (GameWorldMegamanX) b.getGame(),
				item10daoNguoc, 12, 4);
		AnimationBackGroundMap item107 = new AnimationBackGroundMap(1186, 2624, (GameWorldMegamanX) b.getGame(), item10,
				12, 4);
		AnimationBackGroundMap item108 = new AnimationBackGroundMap(1202, 2624, (GameWorldMegamanX) b.getGame(),
				item10daoNguoc, 12, 4);
		AnimationBackGroundMap item109 = new AnimationBackGroundMap(1218, 2624, (GameWorldMegamanX) b.getGame(), item10,
				12, 4);
		AnimationBackGroundMap item1010 = new AnimationBackGroundMap(1234, 2624, (GameWorldMegamanX) b.getGame(),
				item10daoNguoc, 12, 4);
		AnimationBackGroundMap item1011 = new AnimationBackGroundMap(1250, 2624, (GameWorldMegamanX) b.getGame(),
				item10, 12, 4);
		AnimationBackGroundMap item1012 = new AnimationBackGroundMap(1266, 2624, (GameWorldMegamanX) b.getGame(),
				item10daoNguoc, 12, 4);
		AnimationBackGroundMap item1013 = new AnimationBackGroundMap(1282, 2624, (GameWorldMegamanX) b.getGame(),
				item10, 12, 4);
		AnimationBackGroundMap item1014 = new AnimationBackGroundMap(1298, 2624, (GameWorldMegamanX) b.getGame(),
				item10daoNguoc, 12, 4);
		AnimationBackGroundMap item1015 = new AnimationBackGroundMap(1314, 2624, (GameWorldMegamanX) b.getGame(),
				item10, 12, 4);
		AnimationBackGroundMap item1016 = new AnimationBackGroundMap(1330, 2624, (GameWorldMegamanX) b.getGame(),
				item10daoNguoc, 12, 4);
		AnimationBackGroundMap item1017 = new AnimationBackGroundMap(1346, 2624, (GameWorldMegamanX) b.getGame(),
				item10, 12, 4);
		AnimationBackGroundMap item1018 = new AnimationBackGroundMap(1362, 2624, (GameWorldMegamanX) b.getGame(),
				item10daoNguoc, 12, 4);
		AnimationBackGroundMap item1019 = new AnimationBackGroundMap(1378, 2624, (GameWorldMegamanX) b.getGame(),
				item10, 12, 4);
		AnimationBackGroundMap item1020 = new AnimationBackGroundMap(1394, 2624, (GameWorldMegamanX) b.getGame(),
				item10daoNguoc, 12, 4);
		AnimationBackGroundMap item1021 = new AnimationBackGroundMap(1410, 2624, (GameWorldMegamanX) b.getGame(),
				item10, 12, 4);
		AnimationBackGroundMap item1022 = new AnimationBackGroundMap(1426, 2624, (GameWorldMegamanX) b.getGame(),
				item10daoNguoc, 12, 4);
		AnimationBackGroundMap item1023 = new AnimationBackGroundMap(1442, 2624, (GameWorldMegamanX) b.getGame(),
				item10, 12, 4);
		AnimationBackGroundMap item1024 = new AnimationBackGroundMap(1458, 2624, (GameWorldMegamanX) b.getGame(),
				item10daoNguoc, 12, 4);
		AnimationBackGroundMap item1025 = new AnimationBackGroundMap(1474, 2624, (GameWorldMegamanX) b.getGame(),
				item10, 12, 4);
		AnimationBackGroundMap item1026 = new AnimationBackGroundMap(1490, 2624, (GameWorldMegamanX) b.getGame(),
				item10daoNguoc, 12, 4);
		AnimationBackGroundMap item1027 = new AnimationBackGroundMap(1506, 2624, (GameWorldMegamanX) b.getGame(),
				item10, 12, 4);
		AnimationBackGroundMap item1028 = new AnimationBackGroundMap(1522, 2624, (GameWorldMegamanX) b.getGame(),
				item10daoNguoc, 12, 4);
		AnimationBackGroundMap item1029 = new AnimationBackGroundMap(1538, 2624, (GameWorldMegamanX) b.getGame(),
				item10, 12, 4);
		AnimationBackGroundMap item1030 = new AnimationBackGroundMap(1554, 2624, (GameWorldMegamanX) b.getGame(),
				item10daoNguoc, 12, 4);
		AnimationBackGroundMap item1031 = new AnimationBackGroundMap(1570, 2624, (GameWorldMegamanX) b.getGame(),
				item10, 12, 4);
		AnimationBackGroundMap item1032 = new AnimationBackGroundMap(1586, 2624, (GameWorldMegamanX) b.getGame(),
				item10daoNguoc, 12, 4);
		AnimationBackGroundMap item1033 = new AnimationBackGroundMap(1602, 2624, (GameWorldMegamanX) b.getGame(),
				item10, 12, 4);
		AnimationBackGroundMap item1034 = new AnimationBackGroundMap(1618, 2624, (GameWorldMegamanX) b.getGame(),
				item10daoNguoc, 12, 4);
		AnimationBackGroundMap item1035 = new AnimationBackGroundMap(1634, 2624, (GameWorldMegamanX) b.getGame(),
				item10, 12, 4);
		AnimationBackGroundMap item1036 = new AnimationBackGroundMap(1650, 2624, (GameWorldMegamanX) b.getGame(),
				item10daoNguoc, 12, 4);
		AnimationBackGroundMap item1037 = new AnimationBackGroundMap(1666, 2624, (GameWorldMegamanX) b.getGame(),
				item10, 12, 4);
		AnimationBackGroundMap item1038 = new AnimationBackGroundMap(1682, 2624, (GameWorldMegamanX) b.getGame(),
				item10daoNguoc, 12, 4);
		AnimationBackGroundMap item1039 = new AnimationBackGroundMap(1698, 2624, (GameWorldMegamanX) b.getGame(),
				item10, 12, 4);
		AnimationBackGroundMap item1040 = new AnimationBackGroundMap(1714, 2624, (GameWorldMegamanX) b.getGame(),
				item10daoNguoc, 12, 4);
		AnimationBackGroundMap item1041 = new AnimationBackGroundMap(1730, 2624, (GameWorldMegamanX) b.getGame(),
				item10, 12, 4);
		AnimationBackGroundMap item1042 = new AnimationBackGroundMap(1746, 2624, (GameWorldMegamanX) b.getGame(),
				item10daoNguoc, 12, 4);
		AnimationBackGroundMap item1043 = new AnimationBackGroundMap(1762, 2624, (GameWorldMegamanX) b.getGame(),
				item10, 12, 4);
		AnimationBackGroundMap item1044 = new AnimationBackGroundMap(1778, 2624, (GameWorldMegamanX) b.getGame(),
				item10daoNguoc, 12, 4);
		AnimationBackGroundMap item1045 = new AnimationBackGroundMap(1794, 2624, (GameWorldMegamanX) b.getGame(),
				item10, 12, 4);
		AnimationBackGroundMap item1046 = new AnimationBackGroundMap(1810, 2624, (GameWorldMegamanX) b.getGame(),
				item10daoNguoc, 12, 4);
		AnimationBackGroundMap item1047 = new AnimationBackGroundMap(1826, 2624, (GameWorldMegamanX) b.getGame(),
				item10, 12, 4);
		AnimationBackGroundMap item1048 = new AnimationBackGroundMap(1842, 2624, (GameWorldMegamanX) b.getGame(),
				item10daoNguoc, 12, 4);
		AnimationBackGroundMap item1049 = new AnimationBackGroundMap(1858, 2624, (GameWorldMegamanX) b.getGame(),
				item10, 12, 4);
		AnimationBackGroundMap item1050 = new AnimationBackGroundMap(1874, 2624, (GameWorldMegamanX) b.getGame(),
				item10daoNguoc, 12, 4);
		AnimationBackGroundMap item1051 = new AnimationBackGroundMap(1890, 2624, (GameWorldMegamanX) b.getGame(),
				item10, 12, 4);
		AnimationBackGroundMap item1052 = new AnimationBackGroundMap(1906, 2624, (GameWorldMegamanX) b.getGame(),
				item10daoNguoc, 12, 4);
		AnimationBackGroundMap item1053 = new AnimationBackGroundMap(1922, 2624, (GameWorldMegamanX) b.getGame(),
				item10, 12, 4);
		AnimationBackGroundMap item1054 = new AnimationBackGroundMap(1938, 2624, (GameWorldMegamanX) b.getGame(),
				item10daoNguoc, 12, 4);
		AnimationBackGroundMap item1055 = new AnimationBackGroundMap(1954, 2624, (GameWorldMegamanX) b.getGame(),
				item10, 12, 4);
		AnimationBackGroundMap item1056 = new AnimationBackGroundMap(1970, 2624, (GameWorldMegamanX) b.getGame(),
				item10daoNguoc, 12, 4);
		AnimationBackGroundMap item1057 = new AnimationBackGroundMap(1986, 2624, (GameWorldMegamanX) b.getGame(),
				item10, 12, 4);
		AnimationBackGroundMap item1058 = new AnimationBackGroundMap(2002, 2624, (GameWorldMegamanX) b.getGame(),
				item10daoNguoc, 12, 4);
		AnimationBackGroundMap item1059 = new AnimationBackGroundMap(2018, 2624, (GameWorldMegamanX) b.getGame(),
				item10, 12, 4);
		AnimationBackGroundMap item1060 = new AnimationBackGroundMap(2034, 2624, (GameWorldMegamanX) b.getGame(),
				item10daoNguoc, 12, 4);
		AnimationBackGroundMap item1061 = new AnimationBackGroundMap(2050, 2624, (GameWorldMegamanX) b.getGame(),
				item10, 12, 4);
		AnimationBackGroundMap item1062 = new AnimationBackGroundMap(2066, 2624, (GameWorldMegamanX) b.getGame(),
				item10daoNguoc, 12, 4);
		AnimationBackGroundMap item1063 = new AnimationBackGroundMap(2082, 2624, (GameWorldMegamanX) b.getGame(),
				item10, 12, 4);
		AnimationBackGroundMap item1064 = new AnimationBackGroundMap(2098, 2624, (GameWorldMegamanX) b.getGame(),
				item10daoNguoc, 12, 4);
		AnimationBackGroundMap item1065 = new AnimationBackGroundMap(2114, 2624, (GameWorldMegamanX) b.getGame(),
				item10, 12, 4);
		AnimationBackGroundMap item1066 = new AnimationBackGroundMap(2130, 2624, (GameWorldMegamanX) b.getGame(),
				item10daoNguoc, 12, 4);
		AnimationBackGroundMap item1067 = new AnimationBackGroundMap(2146, 2624, (GameWorldMegamanX) b.getGame(),
				item10, 12, 4);
		AnimationBackGroundMap item1068 = new AnimationBackGroundMap(2162, 2624, (GameWorldMegamanX) b.getGame(),
				item10daoNguoc, 12, 4);
		AnimationBackGroundMap item1069 = new AnimationBackGroundMap(2178, 2624, (GameWorldMegamanX) b.getGame(),
				item10, 12, 4);
		AnimationBackGroundMap item1070 = new AnimationBackGroundMap(2194, 2624, (GameWorldMegamanX) b.getGame(),
				item10daoNguoc, 12, 4);
		AnimationBackGroundMap item1071 = new AnimationBackGroundMap(2210, 2624, (GameWorldMegamanX) b.getGame(),
				item10, 12, 4);
		AnimationBackGroundMap item1072 = new AnimationBackGroundMap(2226, 2624, (GameWorldMegamanX) b.getGame(),
				item10daoNguoc, 12, 4);
		AnimationBackGroundMap item1073 = new AnimationBackGroundMap(2242, 2624, (GameWorldMegamanX) b.getGame(),
				item10, 12, 4);
		AnimationBackGroundMap item1074 = new AnimationBackGroundMap(2258, 2624, (GameWorldMegamanX) b.getGame(),
				item10daoNguoc, 12, 4);
		AnimationBackGroundMap item1075 = new AnimationBackGroundMap(2274, 2624, (GameWorldMegamanX) b.getGame(),
				item10, 12, 4);
		AnimationBackGroundMap item1076 = new AnimationBackGroundMap(2290, 2624, (GameWorldMegamanX) b.getGame(),
				item10daoNguoc, 12, 4);
		AnimationBackGroundMap item1077 = new AnimationBackGroundMap(2306, 2624, (GameWorldMegamanX) b.getGame(),
				item10, 12, 4);
		AnimationBackGroundMap item1078 = new AnimationBackGroundMap(2322, 2624, (GameWorldMegamanX) b.getGame(),
				item10daoNguoc, 12, 4);
		AnimationBackGroundMap item1079 = new AnimationBackGroundMap(2338, 2624, (GameWorldMegamanX) b.getGame(),
				item10, 12, 4);
		AnimationBackGroundMap item1080 = new AnimationBackGroundMap(2354, 2624, (GameWorldMegamanX) b.getGame(),
				item10daoNguoc, 12, 4);
		AnimationBackGroundMap item1081 = new AnimationBackGroundMap(2370, 2624, (GameWorldMegamanX) b.getGame(),
				item10, 12, 4);
		AnimationBackGroundMap item1082 = new AnimationBackGroundMap(2386, 2624, (GameWorldMegamanX) b.getGame(),
				item10daoNguoc, 12, 4);
		AnimationBackGroundMap item1083 = new AnimationBackGroundMap(2402, 2624, (GameWorldMegamanX) b.getGame(),
				item10, 12, 4);
		AnimationBackGroundMap item1084 = new AnimationBackGroundMap(2418, 2624, (GameWorldMegamanX) b.getGame(),
				item10daoNguoc, 12, 4);
		AnimationBackGroundMap item1085 = new AnimationBackGroundMap(2434, 2624, (GameWorldMegamanX) b.getGame(),
				item10, 12, 4);
		AnimationBackGroundMap item1086 = new AnimationBackGroundMap(2450, 2624, (GameWorldMegamanX) b.getGame(),
				item10daoNguoc, 12, 4);
		AnimationBackGroundMap item1087 = new AnimationBackGroundMap(2466, 2624, (GameWorldMegamanX) b.getGame(),
				item10, 12, 4);
		AnimationBackGroundMap item1088 = new AnimationBackGroundMap(2482, 2624, (GameWorldMegamanX) b.getGame(),
				item10daoNguoc, 12, 4);
		AnimationBackGroundMap item1089 = new AnimationBackGroundMap(2626, 3104, (GameWorldMegamanX) b.getGame(),
				item10, 12, 4);
		AnimationBackGroundMap item1090 = new AnimationBackGroundMap(2642, 3104, (GameWorldMegamanX) b.getGame(),
				item10daoNguoc, 12, 4);
		AnimationBackGroundMap item1091 = new AnimationBackGroundMap(2658, 3104, (GameWorldMegamanX) b.getGame(),
				item10, 12, 4);
		AnimationBackGroundMap item1092 = new AnimationBackGroundMap(2674, 3104, (GameWorldMegamanX) b.getGame(),
				item10daoNguoc, 12, 4);
		AnimationBackGroundMap item1093 = new AnimationBackGroundMap(2690, 3104, (GameWorldMegamanX) b.getGame(),
				item10, 12, 4);
		AnimationBackGroundMap item1094 = new AnimationBackGroundMap(2706, 3104, (GameWorldMegamanX) b.getGame(),
				item10daoNguoc, 12, 4);
		AnimationBackGroundMap item1095 = new AnimationBackGroundMap(2722, 3104, (GameWorldMegamanX) b.getGame(),
				item10, 12, 4);
		AnimationBackGroundMap item1096 = new AnimationBackGroundMap(2738, 3104, (GameWorldMegamanX) b.getGame(),
				item10daoNguoc, 12, 4);
		AnimationBackGroundMap item1097 = new AnimationBackGroundMap(2754, 3104, (GameWorldMegamanX) b.getGame(),
				item10, 12, 4);
		AnimationBackGroundMap item1098 = new AnimationBackGroundMap(2770, 3104, (GameWorldMegamanX) b.getGame(),
				item10daoNguoc, 12, 4);
		AnimationBackGroundMap item1099 = new AnimationBackGroundMap(2786, 3104, (GameWorldMegamanX) b.getGame(),
				item10, 12, 4);
		AnimationBackGroundMap item10100 = new AnimationBackGroundMap(2802, 3104, (GameWorldMegamanX) b.getGame(),
				item10daoNguoc, 12, 4);
		AnimationBackGroundMap item10101 = new AnimationBackGroundMap(3874, 3104, (GameWorldMegamanX) b.getGame(),
				item10, 12, 4);
		AnimationBackGroundMap item10102 = new AnimationBackGroundMap(3890, 3104, (GameWorldMegamanX) b.getGame(),
				item10daoNguoc, 12, 4);
		AnimationBackGroundMap item10103 = new AnimationBackGroundMap(3906, 3104, (GameWorldMegamanX) b.getGame(),
				item10, 12, 4);
		AnimationBackGroundMap item10104 = new AnimationBackGroundMap(3922, 3104, (GameWorldMegamanX) b.getGame(),
				item10daoNguoc, 12, 4);
		AnimationBackGroundMap item10105 = new AnimationBackGroundMap(3938, 3104, (GameWorldMegamanX) b.getGame(),
				item10, 12, 4);
		AnimationBackGroundMap item10106 = new AnimationBackGroundMap(3954, 3104, (GameWorldMegamanX) b.getGame(),
				item10daoNguoc, 12, 4);
		AnimationBackGroundMap item10107 = new AnimationBackGroundMap(3970, 3104, (GameWorldMegamanX) b.getGame(),
				item10, 12, 4);
		AnimationBackGroundMap item10108 = new AnimationBackGroundMap(3986, 3104, (GameWorldMegamanX) b.getGame(),
				item10daoNguoc, 12, 4);
		AnimationBackGroundMap item10109 = new AnimationBackGroundMap(4002, 3104, (GameWorldMegamanX) b.getGame(),
				item10, 12, 4);
		AnimationBackGroundMap item10110 = new AnimationBackGroundMap(4018, 3104, (GameWorldMegamanX) b.getGame(),
				item10daoNguoc, 12, 4);
		AnimationBackGroundMap item10111 = new AnimationBackGroundMap(2818, 3104, (GameWorldMegamanX) b.getGame(),
				item10, 12, 4);
		AnimationBackGroundMap item10112 = new AnimationBackGroundMap(2834, 3104, (GameWorldMegamanX) b.getGame(),
				item10daoNguoc, 12, 4);
		AnimationBackGroundMap item10113 = new AnimationBackGroundMap(2850, 3104, (GameWorldMegamanX) b.getGame(),
				item10, 12, 4);
		AnimationBackGroundMap item10114 = new AnimationBackGroundMap(2866, 3104, (GameWorldMegamanX) b.getGame(),
				item10daoNguoc, 12, 4);
		AnimationBackGroundMap item10115 = new AnimationBackGroundMap(2882, 3104, (GameWorldMegamanX) b.getGame(),
				item10, 12, 4);
		AnimationBackGroundMap item10116 = new AnimationBackGroundMap(2898, 3104, (GameWorldMegamanX) b.getGame(),
				item10daoNguoc, 12, 4);
		AnimationBackGroundMap item10117 = new AnimationBackGroundMap(2914, 3104, (GameWorldMegamanX) b.getGame(),
				item10, 12, 4);
		AnimationBackGroundMap item10118 = new AnimationBackGroundMap(2930, 3104, (GameWorldMegamanX) b.getGame(),
				item10daoNguoc, 12, 4);
		AnimationBackGroundMap item10119 = new AnimationBackGroundMap(2946, 3104, (GameWorldMegamanX) b.getGame(),
				item10, 12, 4);
		AnimationBackGroundMap item10120 = new AnimationBackGroundMap(2962, 3104, (GameWorldMegamanX) b.getGame(),
				item10daoNguoc, 12, 4);
		AnimationBackGroundMap item10121 = new AnimationBackGroundMap(2978, 3104, (GameWorldMegamanX) b.getGame(),
				item10, 12, 4);
		AnimationBackGroundMap item10122 = new AnimationBackGroundMap(2994, 3104, (GameWorldMegamanX) b.getGame(),
				item10daoNguoc, 12, 4);
		AnimationBackGroundMap item10123 = new AnimationBackGroundMap(3010, 3104, (GameWorldMegamanX) b.getGame(),
				item10, 12, 4);
		AnimationBackGroundMap item10124 = new AnimationBackGroundMap(3026, 3104, (GameWorldMegamanX) b.getGame(),
				item10daoNguoc, 12, 4);
		AnimationBackGroundMap item10125 = new AnimationBackGroundMap(3042, 3104, (GameWorldMegamanX) b.getGame(),
				item10, 12, 4);
		AnimationBackGroundMap item10126 = new AnimationBackGroundMap(3058, 3104, (GameWorldMegamanX) b.getGame(),
				item10daoNguoc, 12, 4);
		AnimationBackGroundMap item10127 = new AnimationBackGroundMap(3074, 3104, (GameWorldMegamanX) b.getGame(),
				item10, 12, 4);
		AnimationBackGroundMap item10128 = new AnimationBackGroundMap(3090, 3104, (GameWorldMegamanX) b.getGame(),
				item10daoNguoc, 12, 4);
		AnimationBackGroundMap item10129 = new AnimationBackGroundMap(3106, 3104, (GameWorldMegamanX) b.getGame(),
				item10, 12, 4);
		AnimationBackGroundMap item10130 = new AnimationBackGroundMap(3122, 3104, (GameWorldMegamanX) b.getGame(),
				item10daoNguoc, 12, 4);
		AnimationBackGroundMap item10131 = new AnimationBackGroundMap(3138, 3104, (GameWorldMegamanX) b.getGame(),
				item10, 12, 4);
		AnimationBackGroundMap item10132 = new AnimationBackGroundMap(3154, 3104, (GameWorldMegamanX) b.getGame(),
				item10daoNguoc, 12, 4);
		AnimationBackGroundMap item10133 = new AnimationBackGroundMap(3170, 3104, (GameWorldMegamanX) b.getGame(),
				item10, 12, 4);
		AnimationBackGroundMap item10134 = new AnimationBackGroundMap(3186, 3104, (GameWorldMegamanX) b.getGame(),
				item10daoNguoc, 12, 4);
		AnimationBackGroundMap item10135 = new AnimationBackGroundMap(3202, 3104, (GameWorldMegamanX) b.getGame(),
				item10, 12, 4);
		AnimationBackGroundMap item10136 = new AnimationBackGroundMap(3218, 3104, (GameWorldMegamanX) b.getGame(),
				item10daoNguoc, 12, 4);
		AnimationBackGroundMap item10137 = new AnimationBackGroundMap(3234, 3104, (GameWorldMegamanX) b.getGame(),
				item10, 12, 4);
		AnimationBackGroundMap item10138 = new AnimationBackGroundMap(3250, 3104, (GameWorldMegamanX) b.getGame(),
				item10daoNguoc, 12, 4);
		AnimationBackGroundMap item10139 = new AnimationBackGroundMap(3266, 3104, (GameWorldMegamanX) b.getGame(),
				item10, 12, 4);
		AnimationBackGroundMap item10140 = new AnimationBackGroundMap(3282, 3104, (GameWorldMegamanX) b.getGame(),
				item10daoNguoc, 12, 4);
		AnimationBackGroundMap item10141 = new AnimationBackGroundMap(3298, 3104, (GameWorldMegamanX) b.getGame(),
				item10, 12, 4);
		AnimationBackGroundMap item10142 = new AnimationBackGroundMap(3314, 3104, (GameWorldMegamanX) b.getGame(),
				item10daoNguoc, 12, 4);
		AnimationBackGroundMap item10143 = new AnimationBackGroundMap(3330, 3104, (GameWorldMegamanX) b.getGame(),
				item10, 12, 4);
		AnimationBackGroundMap item10144 = new AnimationBackGroundMap(3346, 3104, (GameWorldMegamanX) b.getGame(),
				item10daoNguoc, 12, 4);
		AnimationBackGroundMap item10145 = new AnimationBackGroundMap(3362, 3104, (GameWorldMegamanX) b.getGame(),
				item10, 12, 4);
		AnimationBackGroundMap item10146 = new AnimationBackGroundMap(3378, 3104, (GameWorldMegamanX) b.getGame(),
				item10daoNguoc, 12, 4);
		AnimationBackGroundMap item10147 = new AnimationBackGroundMap(3394, 3104, (GameWorldMegamanX) b.getGame(),
				item10, 12, 4);
		AnimationBackGroundMap item10148 = new AnimationBackGroundMap(3410, 3104, (GameWorldMegamanX) b.getGame(),
				item10daoNguoc, 12, 4);
		AnimationBackGroundMap item10149 = new AnimationBackGroundMap(3426, 3104, (GameWorldMegamanX) b.getGame(),
				item10, 12, 4);
		AnimationBackGroundMap item10150 = new AnimationBackGroundMap(3442, 3104, (GameWorldMegamanX) b.getGame(),
				item10daoNguoc, 12, 4);
		AnimationBackGroundMap item10151 = new AnimationBackGroundMap(3458, 3104, (GameWorldMegamanX) b.getGame(),
				item10, 12, 4);
		AnimationBackGroundMap item10152 = new AnimationBackGroundMap(3474, 3104, (GameWorldMegamanX) b.getGame(),
				item10daoNguoc, 12, 4);
		AnimationBackGroundMap item10153 = new AnimationBackGroundMap(3490, 3104, (GameWorldMegamanX) b.getGame(),
				item10, 12, 4);
		AnimationBackGroundMap item10154 = new AnimationBackGroundMap(3506, 3104, (GameWorldMegamanX) b.getGame(),
				item10daoNguoc, 12, 4);
		AnimationBackGroundMap item10155 = new AnimationBackGroundMap(3522, 3104, (GameWorldMegamanX) b.getGame(),
				item10, 12, 4);
		AnimationBackGroundMap item10156 = new AnimationBackGroundMap(3538, 3104, (GameWorldMegamanX) b.getGame(),
				item10daoNguoc, 12, 4);
		AnimationBackGroundMap item10157 = new AnimationBackGroundMap(3554, 3104, (GameWorldMegamanX) b.getGame(),
				item10, 12, 4);
		AnimationBackGroundMap item10158 = new AnimationBackGroundMap(3570, 3104, (GameWorldMegamanX) b.getGame(),
				item10daoNguoc, 12, 4);
		AnimationBackGroundMap item10159 = new AnimationBackGroundMap(3586, 3104, (GameWorldMegamanX) b.getGame(),
				item10, 12, 4);
		AnimationBackGroundMap item10160 = new AnimationBackGroundMap(3602, 3104, (GameWorldMegamanX) b.getGame(),
				item10daoNguoc, 12, 4);
		AnimationBackGroundMap item10161 = new AnimationBackGroundMap(3618, 3104, (GameWorldMegamanX) b.getGame(),
				item10, 12, 4);
		AnimationBackGroundMap item10162 = new AnimationBackGroundMap(3634, 3104, (GameWorldMegamanX) b.getGame(),
				item10daoNguoc, 12, 4);
		AnimationBackGroundMap item10163 = new AnimationBackGroundMap(3650, 3104, (GameWorldMegamanX) b.getGame(),
				item10, 12, 4);
		AnimationBackGroundMap item10164 = new AnimationBackGroundMap(3666, 3104, (GameWorldMegamanX) b.getGame(),
				item10daoNguoc, 12, 4);
		AnimationBackGroundMap item10165 = new AnimationBackGroundMap(3682, 3104, (GameWorldMegamanX) b.getGame(),
				item10, 12, 4);
		AnimationBackGroundMap item10166 = new AnimationBackGroundMap(3698, 3104, (GameWorldMegamanX) b.getGame(),
				item10daoNguoc, 12, 4);
		AnimationBackGroundMap item10167 = new AnimationBackGroundMap(3714, 3104, (GameWorldMegamanX) b.getGame(),
				item10, 12, 4);
		AnimationBackGroundMap item10168 = new AnimationBackGroundMap(3730, 3104, (GameWorldMegamanX) b.getGame(),
				item10daoNguoc, 12, 4);
		AnimationBackGroundMap item10169 = new AnimationBackGroundMap(3746, 3104, (GameWorldMegamanX) b.getGame(),
				item10, 12, 4);
		AnimationBackGroundMap item10170 = new AnimationBackGroundMap(3762, 3104, (GameWorldMegamanX) b.getGame(),
				item10daoNguoc, 12, 4);
		AnimationBackGroundMap item10171 = new AnimationBackGroundMap(3778, 3104, (GameWorldMegamanX) b.getGame(),
				item10, 12, 4);
		AnimationBackGroundMap item10172 = new AnimationBackGroundMap(3794, 3104, (GameWorldMegamanX) b.getGame(),
				item10daoNguoc, 12, 4);
		AnimationBackGroundMap item10173 = new AnimationBackGroundMap(3810, 3104, (GameWorldMegamanX) b.getGame(),
				item10, 12, 4);
		AnimationBackGroundMap item10174 = new AnimationBackGroundMap(3826, 3104, (GameWorldMegamanX) b.getGame(),
				item10daoNguoc, 12, 4);
		AnimationBackGroundMap item10175 = new AnimationBackGroundMap(3842, 3104, (GameWorldMegamanX) b.getGame(),
				item10, 12, 4);
		AnimationBackGroundMap item10176 = new AnimationBackGroundMap(3858, 3104, (GameWorldMegamanX) b.getGame(),
				item10daoNguoc, 12, 4);
		AnimationBackGroundMap item10177 = new AnimationBackGroundMap(6210, 3104, (GameWorldMegamanX) b.getGame(),
				item10, 12, 4);
		AnimationBackGroundMap item10178 = new AnimationBackGroundMap(6226, 3104, (GameWorldMegamanX) b.getGame(),
				item10daoNguoc, 12, 4);
		AnimationBackGroundMap item10179 = new AnimationBackGroundMap(6242, 3104, (GameWorldMegamanX) b.getGame(),
				item10, 12, 4);
		AnimationBackGroundMap item10180 = new AnimationBackGroundMap(6258, 3104, (GameWorldMegamanX) b.getGame(),
				item10daoNguoc, 12, 4);
		AnimationBackGroundMap item10181 = new AnimationBackGroundMap(6274, 3104, (GameWorldMegamanX) b.getGame(),
				item10, 12, 4);
		AnimationBackGroundMap item10182 = new AnimationBackGroundMap(6290, 3104, (GameWorldMegamanX) b.getGame(),
				item10daoNguoc, 12, 4);
		AnimationBackGroundMap item10183 = new AnimationBackGroundMap(6306, 3104, (GameWorldMegamanX) b.getGame(),
				item10, 12, 4);
		AnimationBackGroundMap item10184 = new AnimationBackGroundMap(6322, 3104, (GameWorldMegamanX) b.getGame(),
				item10daoNguoc, 12, 4);
		AnimationBackGroundMap item10185 = new AnimationBackGroundMap(6338, 3104, (GameWorldMegamanX) b.getGame(),
				item10, 12, 4);
		AnimationBackGroundMap item10186 = new AnimationBackGroundMap(6354, 3104, (GameWorldMegamanX) b.getGame(),
				item10daoNguoc, 12, 4);
		AnimationBackGroundMap item10187 = new AnimationBackGroundMap(6370, 3104, (GameWorldMegamanX) b.getGame(),
				item10, 12, 4);
		AnimationBackGroundMap item10188 = new AnimationBackGroundMap(6386, 3104, (GameWorldMegamanX) b.getGame(),
				item10daoNguoc, 12, 4);
		AnimationBackGroundMap item10189 = new AnimationBackGroundMap(6402, 3104, (GameWorldMegamanX) b.getGame(),
				item10, 12, 4);
		AnimationBackGroundMap item10190 = new AnimationBackGroundMap(6418, 3104, (GameWorldMegamanX) b.getGame(),
				item10daoNguoc, 12, 4);
		AnimationBackGroundMap item10191 = new AnimationBackGroundMap(6434, 3104, (GameWorldMegamanX) b.getGame(),
				item10, 12, 4);
		AnimationBackGroundMap item10192 = new AnimationBackGroundMap(6450, 3104, (GameWorldMegamanX) b.getGame(),
				item10daoNguoc, 12, 4);
		AnimationBackGroundMap item10193 = new AnimationBackGroundMap(6466, 3104, (GameWorldMegamanX) b.getGame(),
				item10, 12, 4);
		AnimationBackGroundMap item10194 = new AnimationBackGroundMap(6482, 3104, (GameWorldMegamanX) b.getGame(),
				item10daoNguoc, 12, 4);
		AnimationBackGroundMap item10195 = new AnimationBackGroundMap(6498, 3104, (GameWorldMegamanX) b.getGame(),
				item10, 12, 4);
		AnimationBackGroundMap item10196 = new AnimationBackGroundMap(6514, 3104, (GameWorldMegamanX) b.getGame(),
				item10daoNguoc, 12, 4);
		AnimationBackGroundMap item10197 = new AnimationBackGroundMap(6786, 1568, (GameWorldMegamanX) b.getGame(),
				item10, 12, 4);
		AnimationBackGroundMap item10198 = new AnimationBackGroundMap(6802, 1568, (GameWorldMegamanX) b.getGame(),
				item10daoNguoc, 12, 4);
		AnimationBackGroundMap item10199 = new AnimationBackGroundMap(6818, 1568, (GameWorldMegamanX) b.getGame(),
				item10, 12, 4);
		AnimationBackGroundMap item10200 = new AnimationBackGroundMap(6834, 1568, (GameWorldMegamanX) b.getGame(),
				item10daoNguoc, 12, 4);
		AnimationBackGroundMap item10201 = new AnimationBackGroundMap(6850, 1568, (GameWorldMegamanX) b.getGame(),
				item10, 12, 4);
		AnimationBackGroundMap item10202 = new AnimationBackGroundMap(6866, 1568, (GameWorldMegamanX) b.getGame(),
				item10daoNguoc, 12, 4);
		AnimationBackGroundMap item10203 = new AnimationBackGroundMap(6882, 1568, (GameWorldMegamanX) b.getGame(),
				item10, 12, 4);
		AnimationBackGroundMap item10204 = new AnimationBackGroundMap(6898, 1568, (GameWorldMegamanX) b.getGame(),
				item10daoNguoc, 12, 4);
		AnimationBackGroundMap item10205 = new AnimationBackGroundMap(6914, 1568, (GameWorldMegamanX) b.getGame(),
				item10, 12, 4);
		AnimationBackGroundMap item10206 = new AnimationBackGroundMap(6930, 1568, (GameWorldMegamanX) b.getGame(),
				item10daoNguoc, 12, 4);
		AnimationBackGroundMap item10207 = new AnimationBackGroundMap(6946, 1568, (GameWorldMegamanX) b.getGame(),
				item10, 12, 4);
		AnimationBackGroundMap item10208 = new AnimationBackGroundMap(6962, 1568, (GameWorldMegamanX) b.getGame(),
				item10daoNguoc, 12, 4);
		AnimationBackGroundMap item10209 = new AnimationBackGroundMap(6978, 1568, (GameWorldMegamanX) b.getGame(),
				item10, 12, 4);
		AnimationBackGroundMap item10210 = new AnimationBackGroundMap(6994, 1568, (GameWorldMegamanX) b.getGame(),
				item10daoNguoc, 12, 4);
		AnimationBackGroundMap item10211 = new AnimationBackGroundMap(7010, 1568, (GameWorldMegamanX) b.getGame(),
				item10, 12, 4);
		AnimationBackGroundMap item10212 = new AnimationBackGroundMap(7026, 1568, (GameWorldMegamanX) b.getGame(),
				item10daoNguoc, 12, 4);
		AnimationBackGroundMap item10213 = new AnimationBackGroundMap(7042, 1568, (GameWorldMegamanX) b.getGame(),
				item10, 12, 4);
		AnimationBackGroundMap item10214 = new AnimationBackGroundMap(7058, 1568, (GameWorldMegamanX) b.getGame(),
				item10daoNguoc, 12, 4);
		AnimationBackGroundMap item10215 = new AnimationBackGroundMap(7074, 1568, (GameWorldMegamanX) b.getGame(),
				item10, 12, 4);
		AnimationBackGroundMap item10216 = new AnimationBackGroundMap(7090, 1568, (GameWorldMegamanX) b.getGame(),
				item10daoNguoc, 12, 4);
		AnimationBackGroundMap item10217 = new AnimationBackGroundMap(7106, 1568, (GameWorldMegamanX) b.getGame(),
				item10, 12, 4);
		AnimationBackGroundMap item10218 = new AnimationBackGroundMap(7122, 1568, (GameWorldMegamanX) b.getGame(),
				item10daoNguoc, 12, 4);
		AnimationBackGroundMap item10219 = new AnimationBackGroundMap(7138, 1568, (GameWorldMegamanX) b.getGame(),
				item10, 12, 4);
		AnimationBackGroundMap item10220 = new AnimationBackGroundMap(7154, 1568, (GameWorldMegamanX) b.getGame(),
				item10daoNguoc, 12, 4);
		AnimationBackGroundMap item10221 = new AnimationBackGroundMap(7170, 1568, (GameWorldMegamanX) b.getGame(),
				item10, 12, 4);
		AnimationBackGroundMap item10222 = new AnimationBackGroundMap(7186, 1568, (GameWorldMegamanX) b.getGame(),
				item10daoNguoc, 12, 4);
		AnimationBackGroundMap item10223 = new AnimationBackGroundMap(7202, 1568, (GameWorldMegamanX) b.getGame(),
				item10, 12, 4);
		AnimationBackGroundMap item10224 = new AnimationBackGroundMap(7218, 1568, (GameWorldMegamanX) b.getGame(),
				item10daoNguoc, 12, 4);
		AnimationBackGroundMap item10225 = new AnimationBackGroundMap(7234, 1568, (GameWorldMegamanX) b.getGame(),
				item10, 12, 4);
		AnimationBackGroundMap item10226 = new AnimationBackGroundMap(7250, 1568, (GameWorldMegamanX) b.getGame(),
				item10daoNguoc, 12, 4);
		AnimationBackGroundMap item10227 = new AnimationBackGroundMap(7266, 1568, (GameWorldMegamanX) b.getGame(),
				item10, 12, 4);
		AnimationBackGroundMap item10228 = new AnimationBackGroundMap(7282, 1568, (GameWorldMegamanX) b.getGame(),
				item10daoNguoc, 12, 4);
		AnimationBackGroundMap item10229 = new AnimationBackGroundMap(7298, 1568, (GameWorldMegamanX) b.getGame(),
				item10, 12, 4);
		AnimationBackGroundMap item10230 = new AnimationBackGroundMap(7314, 1568, (GameWorldMegamanX) b.getGame(),
				item10daoNguoc, 12, 4);
		AnimationBackGroundMap item10231 = new AnimationBackGroundMap(7330, 1568, (GameWorldMegamanX) b.getGame(),
				item10, 12, 4);
		AnimationBackGroundMap item10232 = new AnimationBackGroundMap(7346, 1568, (GameWorldMegamanX) b.getGame(),
				item10daoNguoc, 12, 4);
		AnimationBackGroundMap item10233 = new AnimationBackGroundMap(7362, 1568, (GameWorldMegamanX) b.getGame(),
				item10, 12, 4);
		AnimationBackGroundMap item10234 = new AnimationBackGroundMap(7378, 1568, (GameWorldMegamanX) b.getGame(),
				item10daoNguoc, 12, 4);
		AnimationBackGroundMap item10235 = new AnimationBackGroundMap(7394, 1568, (GameWorldMegamanX) b.getGame(),
				item10, 12, 4);
		AnimationBackGroundMap item10236 = new AnimationBackGroundMap(7410, 1568, (GameWorldMegamanX) b.getGame(),
				item10daoNguoc, 12, 4);
		AnimationBackGroundMap item10237 = new AnimationBackGroundMap(7426, 1568, (GameWorldMegamanX) b.getGame(),
				item10, 12, 4);
		AnimationBackGroundMap item10238 = new AnimationBackGroundMap(7442, 1568, (GameWorldMegamanX) b.getGame(),
				item10daoNguoc, 12, 4);
		AnimationBackGroundMap item10239 = new AnimationBackGroundMap(7458, 1568, (GameWorldMegamanX) b.getGame(),
				item10, 12, 4);
		AnimationBackGroundMap item10240 = new AnimationBackGroundMap(7474, 1568, (GameWorldMegamanX) b.getGame(),
				item10daoNguoc, 12, 4);
		AnimationBackGroundMap item10241 = new AnimationBackGroundMap(7490, 1568, (GameWorldMegamanX) b.getGame(),
				item10, 12, 4);
		AnimationBackGroundMap item10242 = new AnimationBackGroundMap(7506, 1568, (GameWorldMegamanX) b.getGame(),
				item10daoNguoc, 12, 4);
		AnimationBackGroundMap item10243 = new AnimationBackGroundMap(7522, 1568, (GameWorldMegamanX) b.getGame(),
				item10, 12, 4);
		AnimationBackGroundMap item10244 = new AnimationBackGroundMap(7538, 1568, (GameWorldMegamanX) b.getGame(),
				item10daoNguoc, 12, 4);
		AnimationBackGroundMap item10245 = new AnimationBackGroundMap(7554, 1568, (GameWorldMegamanX) b.getGame(),
				item10, 12, 4);
		AnimationBackGroundMap item10246 = new AnimationBackGroundMap(7570, 1568, (GameWorldMegamanX) b.getGame(),
				item10daoNguoc, 12, 4);
		AnimationBackGroundMap item10247 = new AnimationBackGroundMap(7586, 1568, (GameWorldMegamanX) b.getGame(),
				item10, 12, 4);
		AnimationBackGroundMap item10248 = new AnimationBackGroundMap(7602, 1568, (GameWorldMegamanX) b.getGame(),
				item10daoNguoc, 12, 4);
		b.getListAnimationMap().add(item101);
		b.getListAnimationMap().add(item102);
		b.getListAnimationMap().add(item103);
		b.getListAnimationMap().add(item104);
		b.getListAnimationMap().add(item105);
		b.getListAnimationMap().add(item106);
		b.getListAnimationMap().add(item107);
		b.getListAnimationMap().add(item108);
		b.getListAnimationMap().add(item109);
		b.getListAnimationMap().add(item1010);
		b.getListAnimationMap().add(item1011);
		b.getListAnimationMap().add(item1012);
		b.getListAnimationMap().add(item1013);
		b.getListAnimationMap().add(item1014);
		b.getListAnimationMap().add(item1015);
		b.getListAnimationMap().add(item1016);
		b.getListAnimationMap().add(item1017);
		b.getListAnimationMap().add(item1018);
		b.getListAnimationMap().add(item1019);
		b.getListAnimationMap().add(item1020);
		b.getListAnimationMap().add(item1021);
		b.getListAnimationMap().add(item1022);
		b.getListAnimationMap().add(item1023);
		b.getListAnimationMap().add(item1024);
		b.getListAnimationMap().add(item1025);
		b.getListAnimationMap().add(item1026);
		b.getListAnimationMap().add(item1027);
		b.getListAnimationMap().add(item1028);
		b.getListAnimationMap().add(item1029);
		b.getListAnimationMap().add(item1030);
		b.getListAnimationMap().add(item1031);
		b.getListAnimationMap().add(item1032);
		b.getListAnimationMap().add(item1033);
		b.getListAnimationMap().add(item1034);
		b.getListAnimationMap().add(item1035);
		b.getListAnimationMap().add(item1036);
		b.getListAnimationMap().add(item1037);
		b.getListAnimationMap().add(item1038);
		b.getListAnimationMap().add(item1039);
		b.getListAnimationMap().add(item1040);
		b.getListAnimationMap().add(item1041);
		b.getListAnimationMap().add(item1042);
		b.getListAnimationMap().add(item1043);
		b.getListAnimationMap().add(item1044);
		b.getListAnimationMap().add(item1045);
		b.getListAnimationMap().add(item1046);
		b.getListAnimationMap().add(item1047);
		b.getListAnimationMap().add(item1048);
		b.getListAnimationMap().add(item1049);
		b.getListAnimationMap().add(item1050);
		b.getListAnimationMap().add(item1051);
		b.getListAnimationMap().add(item1052);
		b.getListAnimationMap().add(item1053);
		b.getListAnimationMap().add(item1054);
		b.getListAnimationMap().add(item1055);
		b.getListAnimationMap().add(item1056);
		b.getListAnimationMap().add(item1057);
		b.getListAnimationMap().add(item1058);
		b.getListAnimationMap().add(item1059);
		b.getListAnimationMap().add(item1060);
		b.getListAnimationMap().add(item1061);
		b.getListAnimationMap().add(item1062);
		b.getListAnimationMap().add(item1063);
		b.getListAnimationMap().add(item1064);
		b.getListAnimationMap().add(item1065);
		b.getListAnimationMap().add(item1066);
		b.getListAnimationMap().add(item1067);
		b.getListAnimationMap().add(item1068);
		b.getListAnimationMap().add(item1069);
		b.getListAnimationMap().add(item1070);
		b.getListAnimationMap().add(item1071);
		b.getListAnimationMap().add(item1072);
		b.getListAnimationMap().add(item1073);
		b.getListAnimationMap().add(item1074);
		b.getListAnimationMap().add(item1075);
		b.getListAnimationMap().add(item1076);
		b.getListAnimationMap().add(item1077);
		b.getListAnimationMap().add(item1078);
		b.getListAnimationMap().add(item1079);
		b.getListAnimationMap().add(item1080);
		b.getListAnimationMap().add(item1081);
		b.getListAnimationMap().add(item1082);
		b.getListAnimationMap().add(item1083);
		b.getListAnimationMap().add(item1084);
		b.getListAnimationMap().add(item1085);
		b.getListAnimationMap().add(item1086);
		b.getListAnimationMap().add(item1087);
		b.getListAnimationMap().add(item1088);
		b.getListAnimationMap().add(item1089);
		b.getListAnimationMap().add(item1090);
		b.getListAnimationMap().add(item1091);
		b.getListAnimationMap().add(item1092);
		b.getListAnimationMap().add(item1093);
		b.getListAnimationMap().add(item1094);
		b.getListAnimationMap().add(item1095);
		b.getListAnimationMap().add(item1096);
		b.getListAnimationMap().add(item1097);
		b.getListAnimationMap().add(item1098);
		b.getListAnimationMap().add(item1099);
		b.getListAnimationMap().add(item10100);
		b.getListAnimationMap().add(item10101);
		b.getListAnimationMap().add(item10102);
		b.getListAnimationMap().add(item10103);
		b.getListAnimationMap().add(item10104);
		b.getListAnimationMap().add(item10105);
		b.getListAnimationMap().add(item10106);
		b.getListAnimationMap().add(item10107);
		b.getListAnimationMap().add(item10108);
		b.getListAnimationMap().add(item10109);
		b.getListAnimationMap().add(item10110);
		b.getListAnimationMap().add(item10111);
		b.getListAnimationMap().add(item10112);
		b.getListAnimationMap().add(item10113);
		b.getListAnimationMap().add(item10114);
		b.getListAnimationMap().add(item10115);
		b.getListAnimationMap().add(item10116);
		b.getListAnimationMap().add(item10117);
		b.getListAnimationMap().add(item10118);
		b.getListAnimationMap().add(item10119);
		b.getListAnimationMap().add(item10120);
		b.getListAnimationMap().add(item10121);
		b.getListAnimationMap().add(item10122);
		b.getListAnimationMap().add(item10123);
		b.getListAnimationMap().add(item10124);
		b.getListAnimationMap().add(item10125);
		b.getListAnimationMap().add(item10126);
		b.getListAnimationMap().add(item10127);
		b.getListAnimationMap().add(item10128);
		b.getListAnimationMap().add(item10129);
		b.getListAnimationMap().add(item10130);
		b.getListAnimationMap().add(item10131);
		b.getListAnimationMap().add(item10132);
		b.getListAnimationMap().add(item10133);
		b.getListAnimationMap().add(item10134);
		b.getListAnimationMap().add(item10135);
		b.getListAnimationMap().add(item10136);
		b.getListAnimationMap().add(item10137);
		b.getListAnimationMap().add(item10138);
		b.getListAnimationMap().add(item10139);
		b.getListAnimationMap().add(item10140);
		b.getListAnimationMap().add(item10141);
		b.getListAnimationMap().add(item10142);
		b.getListAnimationMap().add(item10143);
		b.getListAnimationMap().add(item10144);
		b.getListAnimationMap().add(item10145);
		b.getListAnimationMap().add(item10146);
		b.getListAnimationMap().add(item10147);
		b.getListAnimationMap().add(item10148);
		b.getListAnimationMap().add(item10149);
		b.getListAnimationMap().add(item10150);
		b.getListAnimationMap().add(item10151);
		b.getListAnimationMap().add(item10152);
		b.getListAnimationMap().add(item10153);
		b.getListAnimationMap().add(item10154);
		b.getListAnimationMap().add(item10155);
		b.getListAnimationMap().add(item10156);
		b.getListAnimationMap().add(item10157);
		b.getListAnimationMap().add(item10158);
		b.getListAnimationMap().add(item10159);
		b.getListAnimationMap().add(item10160);
		b.getListAnimationMap().add(item10161);
		b.getListAnimationMap().add(item10162);
		b.getListAnimationMap().add(item10163);
		b.getListAnimationMap().add(item10164);
		b.getListAnimationMap().add(item10165);
		b.getListAnimationMap().add(item10166);
		b.getListAnimationMap().add(item10167);
		b.getListAnimationMap().add(item10168);
		b.getListAnimationMap().add(item10169);
		b.getListAnimationMap().add(item10170);
		b.getListAnimationMap().add(item10171);
		b.getListAnimationMap().add(item10172);
		b.getListAnimationMap().add(item10173);
		b.getListAnimationMap().add(item10174);
		b.getListAnimationMap().add(item10175);
		b.getListAnimationMap().add(item10176);
		b.getListAnimationMap().add(item10177);
		b.getListAnimationMap().add(item10178);
		b.getListAnimationMap().add(item10179);
		b.getListAnimationMap().add(item10180);
		b.getListAnimationMap().add(item10181);
		b.getListAnimationMap().add(item10182);
		b.getListAnimationMap().add(item10183);
		b.getListAnimationMap().add(item10184);
		b.getListAnimationMap().add(item10185);
		b.getListAnimationMap().add(item10186);
		b.getListAnimationMap().add(item10187);
		b.getListAnimationMap().add(item10188);
		b.getListAnimationMap().add(item10189);
		b.getListAnimationMap().add(item10190);
		b.getListAnimationMap().add(item10191);
		b.getListAnimationMap().add(item10192);
		b.getListAnimationMap().add(item10193);
		b.getListAnimationMap().add(item10194);
		b.getListAnimationMap().add(item10195);
		b.getListAnimationMap().add(item10196);
		b.getListAnimationMap().add(item10197);
		b.getListAnimationMap().add(item10198);
		b.getListAnimationMap().add(item10199);
		b.getListAnimationMap().add(item10200);
		b.getListAnimationMap().add(item10201);
		b.getListAnimationMap().add(item10202);
		b.getListAnimationMap().add(item10203);
		b.getListAnimationMap().add(item10204);
		b.getListAnimationMap().add(item10205);
		b.getListAnimationMap().add(item10206);
		b.getListAnimationMap().add(item10207);
		b.getListAnimationMap().add(item10208);
		b.getListAnimationMap().add(item10209);
		b.getListAnimationMap().add(item10210);
		b.getListAnimationMap().add(item10211);
		b.getListAnimationMap().add(item10212);
		b.getListAnimationMap().add(item10213);
		b.getListAnimationMap().add(item10214);
		b.getListAnimationMap().add(item10215);
		b.getListAnimationMap().add(item10216);
		b.getListAnimationMap().add(item10217);
		b.getListAnimationMap().add(item10218);
		b.getListAnimationMap().add(item10219);
		b.getListAnimationMap().add(item10220);
		b.getListAnimationMap().add(item10221);
		b.getListAnimationMap().add(item10222);
		b.getListAnimationMap().add(item10223);
		b.getListAnimationMap().add(item10224);
		b.getListAnimationMap().add(item10225);
		b.getListAnimationMap().add(item10226);
		b.getListAnimationMap().add(item10227);
		b.getListAnimationMap().add(item10228);
		b.getListAnimationMap().add(item10229);
		b.getListAnimationMap().add(item10230);
		b.getListAnimationMap().add(item10231);
		b.getListAnimationMap().add(item10232);
		b.getListAnimationMap().add(item10233);
		b.getListAnimationMap().add(item10234);
		b.getListAnimationMap().add(item10235);
		b.getListAnimationMap().add(item10236);
		b.getListAnimationMap().add(item10237);
		b.getListAnimationMap().add(item10238);
		b.getListAnimationMap().add(item10239);
		b.getListAnimationMap().add(item10240);
		b.getListAnimationMap().add(item10241);
		b.getListAnimationMap().add(item10242);
		b.getListAnimationMap().add(item10243);
		b.getListAnimationMap().add(item10244);
		b.getListAnimationMap().add(item10245);
		b.getListAnimationMap().add(item10246);
		b.getListAnimationMap().add(item10247);
		b.getListAnimationMap().add(item10248);

		BufferedImage image11 = b.getGame().getInputData().getListFrame().get("introItem111").getImage();
		FrontMapItem itemTv1 = new FrontMapItem(2804, 3153, (GameWorldMegamanX) b.getGame(), image11);
		FrontMapItem itemTv2 = new FrontMapItem(3284, 3153, (GameWorldMegamanX) b.getGame(), image11);
		FrontMapItem itemTv3 = new FrontMapItem(3572, 3153, (GameWorldMegamanX) b.getGame(), image11);
		FrontMapItem itemTv4 = new FrontMapItem(6420, 3153, (GameWorldMegamanX) b.getGame(), image11);
		FrontMapItem itemTv5 = new FrontMapItem(7188, 1617, (GameWorldMegamanX) b.getGame(), image11);
		FrontMapItem itemTv6 = new FrontMapItem(7316, 1617, (GameWorldMegamanX) b.getGame(), image11);
		b.getListTv().add(itemTv1);
		b.getListTv().add(itemTv2);
		b.getListTv().add(itemTv3);
		b.getListTv().add(itemTv4);
		b.getListTv().add(itemTv5);
		b.getListTv().add(itemTv6);

		item12s = new Animation(b.getGame().getInputData().getListAnimation().get("introStageItem12"));
		AnimationBackGroundMap item12s1 = new AnimationBackGroundMap(2824, 3132, (GameWorldMegamanX) b.getGame(),
				item12s, 16, 12);
		AnimationBackGroundMap item12s2 = new AnimationBackGroundMap(3304, 3132, (GameWorldMegamanX) b.getGame(),
				item12s, 16, 12);
		AnimationBackGroundMap item12s3 = new AnimationBackGroundMap(3592, 3132, (GameWorldMegamanX) b.getGame(),
				item12s, 16, 12);
		AnimationBackGroundMap item12s4 = new AnimationBackGroundMap(6440, 3132, (GameWorldMegamanX) b.getGame(),
				item12s, 16, 12);
		AnimationBackGroundMap item12s5 = new AnimationBackGroundMap(6280, 3132, (GameWorldMegamanX) b.getGame(),
				item12s, 16, 12);
		AnimationBackGroundMap item12s6 = new AnimationBackGroundMap(7080, 1596, (GameWorldMegamanX) b.getGame(),
				item12s, 16, 12);
		AnimationBackGroundMap item12s7 = new AnimationBackGroundMap(7336, 1596, (GameWorldMegamanX) b.getGame(),
				item12s, 16, 12);
		AnimationBackGroundMap item12s8 = new AnimationBackGroundMap(7208, 1596, (GameWorldMegamanX) b.getGame(),
				item12s, 16, 12);
		b.getListAnimationMap().add(item12s1);
		b.getListAnimationMap().add(item12s2);
		b.getListAnimationMap().add(item12s3);
		b.getListAnimationMap().add(item12s4);
		b.getListAnimationMap().add(item12s5);
		b.getListAnimationMap().add(item12s6);
		b.getListAnimationMap().add(item12s7);
		b.getListAnimationMap().add(item12s8);

		item13s = new Animation(b.getGame().getInputData().getListAnimation().get("introStageItem13"));
		item13sdaoNguoc = new Animation(b.getGame().getInputData().getListAnimation().get("introStageItem13"));
		item13sdaoNguoc.daoNguoc();
		AnimationBackGroundMap item13s1 = new AnimationBackGroundMap(1408, 2906, (GameWorldMegamanX) b.getGame(),
				item13s, 46, 128);
		AnimationBackGroundMap item13s2 = new AnimationBackGroundMap(2034, 3194, (GameWorldMegamanX) b.getGame(),
				item13sdaoNguoc, 46, 128);
		AnimationBackGroundMap item13s3 = new AnimationBackGroundMap(3570, 3706, (GameWorldMegamanX) b.getGame(),
				item13sdaoNguoc, 46, 128);
		AnimationBackGroundMap item13s4 = new AnimationBackGroundMap(6624, 3610, (GameWorldMegamanX) b.getGame(),
				item13s, 46, 128);
		AnimationBackGroundMap item13s5 = new AnimationBackGroundMap(6130, 3418, (GameWorldMegamanX) b.getGame(),
				item13sdaoNguoc, 46, 128);
		AnimationBackGroundMap item13s6 = new AnimationBackGroundMap(7282, 1850, (GameWorldMegamanX) b.getGame(),
				item13sdaoNguoc, 46, 128);
		AnimationBackGroundMap item13s7 = new AnimationBackGroundMap(7136, 1850, (GameWorldMegamanX) b.getGame(),
				item13s, 46, 128);
		b.getListAnimationMap().add(item13s1);
		b.getListAnimationMap().add(item13s2);
		b.getListAnimationMap().add(item13s3);
		b.getListAnimationMap().add(item13s4);
		b.getListAnimationMap().add(item13s5);
		b.getListAnimationMap().add(item13s6);
		b.getListAnimationMap().add(item13s7);

		item14s = new Animation(b.getGame().getInputData().getListAnimation().get("introStageItem14"));
		item14sdaoNguoc = new Animation(b.getGame().getInputData().getListAnimation().get("introStageItem14"));
		item14sdaoNguoc.daoNguoc();
		AnimationBackGroundMap item14s1 = new AnimationBackGroundMap(1554, 2938, (GameWorldMegamanX) b.getGame(),
				item14s, 46, 32);
		AnimationBackGroundMap item14s2 = new AnimationBackGroundMap(1682, 2970, (GameWorldMegamanX) b.getGame(),
				item14s, 46, 32);
		AnimationBackGroundMap item14s3 = new AnimationBackGroundMap(2048, 2970, (GameWorldMegamanX) b.getGame(),
				item14sdaoNguoc, 46, 32);
		AnimationBackGroundMap item14s4 = new AnimationBackGroundMap(2258, 2970, (GameWorldMegamanX) b.getGame(),
				item14s, 46, 32);
		AnimationBackGroundMap item14s5 = new AnimationBackGroundMap(2034, 3482, (GameWorldMegamanX) b.getGame(),
				item14s, 46, 32);
		AnimationBackGroundMap item14s6 = new AnimationBackGroundMap(2464, 3098, (GameWorldMegamanX) b.getGame(),
				item14sdaoNguoc, 46, 32);
		AnimationBackGroundMap item14s7 = new AnimationBackGroundMap(2546, 3098, (GameWorldMegamanX) b.getGame(),
				item14s, 46, 32);
		AnimationBackGroundMap item14s8 = new AnimationBackGroundMap(2848, 3450, (GameWorldMegamanX) b.getGame(),
				item14sdaoNguoc, 46, 32);
		AnimationBackGroundMap item14s9 = new AnimationBackGroundMap(3026, 3450, (GameWorldMegamanX) b.getGame(),
				item14s, 46, 32);
		AnimationBackGroundMap item14s10 = new AnimationBackGroundMap(3104, 3386, (GameWorldMegamanX) b.getGame(),
				item14sdaoNguoc, 46, 32);
		AnimationBackGroundMap item14s11 = new AnimationBackGroundMap(3282, 3386, (GameWorldMegamanX) b.getGame(),
				item14s, 46, 32);
		AnimationBackGroundMap item14s12 = new AnimationBackGroundMap(3616, 3514, (GameWorldMegamanX) b.getGame(),
				item14sdaoNguoc, 46, 32);
		AnimationBackGroundMap item14s13 = new AnimationBackGroundMap(3858, 3514, (GameWorldMegamanX) b.getGame(),
				item14s, 46, 32);
		AnimationBackGroundMap item14s14 = new AnimationBackGroundMap(3570, 3994, (GameWorldMegamanX) b.getGame(),
				item14s, 46, 32);
		AnimationBackGroundMap item14s15 = new AnimationBackGroundMap(6080, 3706, (GameWorldMegamanX) b.getGame(),
				item14sdaoNguoc, 46, 32);
		AnimationBackGroundMap item14s16 = new AnimationBackGroundMap(6624, 3898, (GameWorldMegamanX) b.getGame(),
				item14sdaoNguoc, 46, 32);
		AnimationBackGroundMap item14s17 = new AnimationBackGroundMap(6432, 3386, (GameWorldMegamanX) b.getGame(),
				item14sdaoNguoc, 46, 32);
		AnimationBackGroundMap item14s18 = new AnimationBackGroundMap(6706, 3386, (GameWorldMegamanX) b.getGame(),
				item14s, 46, 32);
		AnimationBackGroundMap item14s19 = new AnimationBackGroundMap(6432, 3514, (GameWorldMegamanX) b.getGame(),
				item14sdaoNguoc, 46, 32);
		AnimationBackGroundMap item14s20 = new AnimationBackGroundMap(6706, 3514, (GameWorldMegamanX) b.getGame(),
				item14s, 46, 32);
		AnimationBackGroundMap item14s21 = new AnimationBackGroundMap(6130, 3098, (GameWorldMegamanX) b.getGame(),
				item14s, 46, 32);
		AnimationBackGroundMap item14s22 = new AnimationBackGroundMap(6562, 3002, (GameWorldMegamanX) b.getGame(),
				item14s, 46, 32);
		AnimationBackGroundMap item14s23 = new AnimationBackGroundMap(6706, 2906, (GameWorldMegamanX) b.getGame(),
				item14s, 46, 32);
		AnimationBackGroundMap item14s24 = new AnimationBackGroundMap(6706, 1754, (GameWorldMegamanX) b.getGame(),
				item14s, 46, 32);
		AnimationBackGroundMap item14s25 = new AnimationBackGroundMap(6706, 2042, (GameWorldMegamanX) b.getGame(),
				item14s, 46, 32);
		AnimationBackGroundMap item14s26 = new AnimationBackGroundMap(6706, 2554, (GameWorldMegamanX) b.getGame(),
				item14s, 46, 32);
		AnimationBackGroundMap item14s27 = new AnimationBackGroundMap(7072, 2042, (GameWorldMegamanX) b.getGame(),
				item14sdaoNguoc, 46, 32);
		AnimationBackGroundMap item14s28 = new AnimationBackGroundMap(7072, 2554, (GameWorldMegamanX) b.getGame(),
				item14sdaoNguoc, 46, 32);
		AnimationBackGroundMap item14s29 = new AnimationBackGroundMap(7072, 3066, (GameWorldMegamanX) b.getGame(),
				item14sdaoNguoc, 46, 32);
		AnimationBackGroundMap item14s30 = new AnimationBackGroundMap(8242, 1530, (GameWorldMegamanX) b.getGame(),
				item14s, 46, 32);
		AnimationBackGroundMap item14s31 = new AnimationBackGroundMap(8242, 1050, (GameWorldMegamanX) b.getGame(),
				item14s, 46, 32);
		AnimationBackGroundMap item14s32 = new AnimationBackGroundMap(8608, 1818, (GameWorldMegamanX) b.getGame(),
				item14sdaoNguoc, 46, 32);
		b.getListAnimationMap().add(item14s1);
		b.getListAnimationMap().add(item14s2);
		b.getListAnimationMap().add(item14s3);
		b.getListAnimationMap().add(item14s4);
		b.getListAnimationMap().add(item14s5);
		b.getListAnimationMap().add(item14s6);
		b.getListAnimationMap().add(item14s7);
		b.getListAnimationMap().add(item14s8);
		b.getListAnimationMap().add(item14s9);
		b.getListAnimationMap().add(item14s10);
		b.getListAnimationMap().add(item14s11);
		b.getListAnimationMap().add(item14s12);
		b.getListAnimationMap().add(item14s13);
		b.getListAnimationMap().add(item14s14);
		b.getListAnimationMap().add(item14s15);
		b.getListAnimationMap().add(item14s16);
		b.getListAnimationMap().add(item14s17);
		b.getListAnimationMap().add(item14s18);
		b.getListAnimationMap().add(item14s19);
		b.getListAnimationMap().add(item14s20);
		b.getListAnimationMap().add(item14s21);
		b.getListAnimationMap().add(item14s22);
		b.getListAnimationMap().add(item14s23);
		b.getListAnimationMap().add(item14s24);
		b.getListAnimationMap().add(item14s25);
		b.getListAnimationMap().add(item14s26);
		b.getListAnimationMap().add(item14s27);
		b.getListAnimationMap().add(item14s28);
		b.getListAnimationMap().add(item14s29);
		b.getListAnimationMap().add(item14s30);
		b.getListAnimationMap().add(item14s31);
		b.getListAnimationMap().add(item14s32);

		item15s = new Animation(b.getGame().getInputData().getListAnimation().get("introStageItem15"));
		AnimationBackGroundMap item15s1 = new AnimationBackGroundMap(1088, 3002, (GameWorldMegamanX) b.getGame(),
				item15s, 80, 10);
		AnimationBackGroundMap item15s2 = new AnimationBackGroundMap(1208, 3002, (GameWorldMegamanX) b.getGame(),
				item15s, 80, 10);
		AnimationBackGroundMap item15s3 = new AnimationBackGroundMap(1400, 2874, (GameWorldMegamanX) b.getGame(),
				item15s, 80, 10);
		AnimationBackGroundMap item15s4 = new AnimationBackGroundMap(1528, 2906, (GameWorldMegamanX) b.getGame(),
				item15s, 80, 10);
		AnimationBackGroundMap item15s5 = new AnimationBackGroundMap(1656, 2938, (GameWorldMegamanX) b.getGame(),
				item15s, 80, 10);
		AnimationBackGroundMap item15s6 = new AnimationBackGroundMap(1848, 3002, (GameWorldMegamanX) b.getGame(),
				item15s, 80, 10);
		AnimationBackGroundMap item15s7 = new AnimationBackGroundMap(2136, 2938, (GameWorldMegamanX) b.getGame(),
				item15s, 80, 10);
		AnimationBackGroundMap item15s8 = new AnimationBackGroundMap(2456, 2874, (GameWorldMegamanX) b.getGame(),
				item15s, 80, 10);
		AnimationBackGroundMap item15s9 = new AnimationBackGroundMap(2112, 3482, (GameWorldMegamanX) b.getGame(),
				item15s, 80, 10);
		AnimationBackGroundMap item15s10 = new AnimationBackGroundMap(2232, 3482, (GameWorldMegamanX) b.getGame(),
				item15s, 80, 10);
		AnimationBackGroundMap item15s11 = new AnimationBackGroundMap(2360, 3482, (GameWorldMegamanX) b.getGame(),
				item15s, 80, 10);
		AnimationBackGroundMap item15s12 = new AnimationBackGroundMap(2488, 3482, (GameWorldMegamanX) b.getGame(),
				item15s, 80, 10);
		AnimationBackGroundMap item15s13 = new AnimationBackGroundMap(2616, 3482, (GameWorldMegamanX) b.getGame(),
				item15s, 80, 10);
		AnimationBackGroundMap item15s14 = new AnimationBackGroundMap(2744, 3482, (GameWorldMegamanX) b.getGame(),
				item15s, 80, 10);
		AnimationBackGroundMap item15s15 = new AnimationBackGroundMap(2840, 3418, (GameWorldMegamanX) b.getGame(),
				item15s, 80, 10);
		AnimationBackGroundMap item15s16 = new AnimationBackGroundMap(2936, 3418, (GameWorldMegamanX) b.getGame(),
				item15s, 80, 10);
		AnimationBackGroundMap item15s17 = new AnimationBackGroundMap(3096, 3354, (GameWorldMegamanX) b.getGame(),
				item15s, 80, 10);
		AnimationBackGroundMap item15s18 = new AnimationBackGroundMap(3192, 3354, (GameWorldMegamanX) b.getGame(),
				item15s, 80, 10);
		AnimationBackGroundMap item15s19 = new AnimationBackGroundMap(3358, 3482, (GameWorldMegamanX) b.getGame(),
				item15s, 80, 10);
		AnimationBackGroundMap item15s20 = new AnimationBackGroundMap(3480, 3482, (GameWorldMegamanX) b.getGame(),
				item15s, 80, 10);
		AnimationBackGroundMap item15s21 = new AnimationBackGroundMap(3608, 3482, (GameWorldMegamanX) b.getGame(),
				item15s, 80, 10);
		AnimationBackGroundMap item15s22 = new AnimationBackGroundMap(3736, 3482, (GameWorldMegamanX) b.getGame(),
				item15s, 80, 10);
		AnimationBackGroundMap item15s23 = new AnimationBackGroundMap(6232, 3738, (GameWorldMegamanX) b.getGame(),
				item15s, 80, 10);
		AnimationBackGroundMap item15s24 = new AnimationBackGroundMap(6392, 3354, (GameWorldMegamanX) b.getGame(),
				item15s, 80, 10);
		AnimationBackGroundMap item15s25 = new AnimationBackGroundMap(6520, 3354, (GameWorldMegamanX) b.getGame(),
				item15s, 80, 10);
		AnimationBackGroundMap item15s26 = new AnimationBackGroundMap(6648, 3354, (GameWorldMegamanX) b.getGame(),
				item15s, 80, 10);
		AnimationBackGroundMap item15s27 = new AnimationBackGroundMap(7128, 1818, (GameWorldMegamanX) b.getGame(),
				item15s, 80, 10);
		AnimationBackGroundMap item15s28 = new AnimationBackGroundMap(7256, 1818, (GameWorldMegamanX) b.getGame(),
				item15s, 80, 10);
		AnimationBackGroundMap item15s29 = new AnimationBackGroundMap(8600, 1338, (GameWorldMegamanX) b.getGame(),
				item15s, 80, 10);
		AnimationBackGroundMap item15s30 = new AnimationBackGroundMap(7480, 1946, (GameWorldMegamanX) b.getGame(),
				item15s, 80, 10);
		AnimationBackGroundMap item15s31 = new AnimationBackGroundMap(7608, 1946, (GameWorldMegamanX) b.getGame(),
				item15s, 80, 10);
		AnimationBackGroundMap item15s32 = new AnimationBackGroundMap(7736, 1946, (GameWorldMegamanX) b.getGame(),
				item15s, 80, 10);
		AnimationBackGroundMap item15s33 = new AnimationBackGroundMap(7864, 1946, (GameWorldMegamanX) b.getGame(),
				item15s, 80, 10);
		AnimationBackGroundMap item15s34 = new AnimationBackGroundMap(7992, 1946, (GameWorldMegamanX) b.getGame(),
				item15s, 80, 10);
		AnimationBackGroundMap item15s35 = new AnimationBackGroundMap(8120, 1946, (GameWorldMegamanX) b.getGame(),
				item15s, 80, 10);
		AnimationBackGroundMap item15s36 = new AnimationBackGroundMap(8248, 1946, (GameWorldMegamanX) b.getGame(),
				item15s, 80, 10);
		AnimationBackGroundMap item15s37 = new AnimationBackGroundMap(8376, 1946, (GameWorldMegamanX) b.getGame(),
				item15s, 80, 10);
		b.getListAnimationMap().add(item15s1);
		b.getListAnimationMap().add(item15s2);
		b.getListAnimationMap().add(item15s3);
		b.getListAnimationMap().add(item15s4);
		b.getListAnimationMap().add(item15s5);
		b.getListAnimationMap().add(item15s6);
		b.getListAnimationMap().add(item15s7);
		b.getListAnimationMap().add(item15s8);
		b.getListAnimationMap().add(item15s9);
		b.getListAnimationMap().add(item15s10);
		b.getListAnimationMap().add(item15s11);
		b.getListAnimationMap().add(item15s12);
		b.getListAnimationMap().add(item15s13);
		b.getListAnimationMap().add(item15s14);
		b.getListAnimationMap().add(item15s15);
		b.getListAnimationMap().add(item15s16);
		b.getListAnimationMap().add(item15s17);
		b.getListAnimationMap().add(item15s18);
		b.getListAnimationMap().add(item15s19);
		b.getListAnimationMap().add(item15s20);
		b.getListAnimationMap().add(item15s21);
		b.getListAnimationMap().add(item15s22);
		b.getListAnimationMap().add(item15s23);
		b.getListAnimationMap().add(item15s24);
		b.getListAnimationMap().add(item15s25);
		b.getListAnimationMap().add(item15s26);
		b.getListAnimationMap().add(item15s27);
		b.getListAnimationMap().add(item15s28);
		b.getListAnimationMap().add(item15s29);
		b.getListAnimationMap().add(item15s30);
		b.getListAnimationMap().add(item15s31);
		b.getListAnimationMap().add(item15s32);
		b.getListAnimationMap().add(item15s33);
		b.getListAnimationMap().add(item15s34);
		b.getListAnimationMap().add(item15s35);
		b.getListAnimationMap().add(item15s36);
		b.getListAnimationMap().add(item15s37);

		item16s = new Animation(b.getGame().getInputData().getListAnimation().get("introStageItem16"));
		AnimationBackGroundMap item16s1 = new AnimationBackGroundMap(6308, 3234, (GameWorldMegamanX) b.getGame(),
				item16s, 120, 80);
		AnimationBackGroundMap item16s2 = new AnimationBackGroundMap(7460, 1730, (GameWorldMegamanX) b.getGame(),
				item16s, 120, 80);
		AnimationBackGroundMap item16s3 = new AnimationBackGroundMap(3650, 3670, (GameWorldMegamanX) b.getGame(),
				item16s, 120, 80);
		AnimationBackGroundMap item16s4 = new AnimationBackGroundMap(2328, 3332, (GameWorldMegamanX) b.getGame(),
				item16s, 120, 80);
		b.getListAnimationMap().add(item16s1);
		b.getListAnimationMap().add(item16s2);
		b.getListAnimationMap().add(item16s3);
		b.getListAnimationMap().add(item16s4);

		item17s = new Animation(b.getGame().getInputData().getListAnimation().get("introStageItem17"));
		AnimationBackGroundMap item17s1 = new AnimationBackGroundMap(3042, 3226, (GameWorldMegamanX) b.getGame(),
				item17s, 14, 30);
		AnimationBackGroundMap item17s2 = new AnimationBackGroundMap(3058, 3290, (GameWorldMegamanX) b.getGame(),
				item17s, 14, 30);
		AnimationBackGroundMap item17s3 = new AnimationBackGroundMap(3586, 3226, (GameWorldMegamanX) b.getGame(),
				item17s, 14, 30);
		AnimationBackGroundMap item17s4 = new AnimationBackGroundMap(3602, 3290, (GameWorldMegamanX) b.getGame(),
				item17s, 14, 30);
		AnimationBackGroundMap item17s5 = new AnimationBackGroundMap(6274, 3226, (GameWorldMegamanX) b.getGame(),
				item17s, 14, 30);
		AnimationBackGroundMap item17s6 = new AnimationBackGroundMap(6290, 3290, (GameWorldMegamanX) b.getGame(),
				item17s, 14, 30);
		AnimationBackGroundMap item17s7 = new AnimationBackGroundMap(6450, 3226, (GameWorldMegamanX) b.getGame(),
				item17s, 14, 30);
		AnimationBackGroundMap item17s8 = new AnimationBackGroundMap(6434, 3290, (GameWorldMegamanX) b.getGame(),
				item17s, 14, 30);
		AnimationBackGroundMap item17s9 = new AnimationBackGroundMap(7074, 1690, (GameWorldMegamanX) b.getGame(),
				item17s, 14, 30);
		AnimationBackGroundMap item17s10 = new AnimationBackGroundMap(7090, 1754, (GameWorldMegamanX) b.getGame(),
				item17s, 14, 30);
		AnimationBackGroundMap item17s11 = new AnimationBackGroundMap(7202, 1658, (GameWorldMegamanX) b.getGame(),
				item17s, 14, 30);
		AnimationBackGroundMap item17s12 = new AnimationBackGroundMap(7218, 1722, (GameWorldMegamanX) b.getGame(),
				item17s, 14, 30);
		AnimationBackGroundMap item17s13 = new AnimationBackGroundMap(7330, 1690, (GameWorldMegamanX) b.getGame(),
				item17s, 14, 30);
		AnimationBackGroundMap item17s14 = new AnimationBackGroundMap(7346, 1754, (GameWorldMegamanX) b.getGame(),
				item17s, 14, 30);
		AnimationBackGroundMap item17s15 = new AnimationBackGroundMap(8418, 1658, (GameWorldMegamanX) b.getGame(),
				item17s, 14, 30);
		AnimationBackGroundMap item17s16 = new AnimationBackGroundMap(8434, 1530, (GameWorldMegamanX) b.getGame(),
				item17s, 14, 30);
		AnimationBackGroundMap item17s17 = new AnimationBackGroundMap(8434, 1786, (GameWorldMegamanX) b.getGame(),
				item17s, 14, 30);
		b.getListAnimationMap().add(item17s1);
		b.getListAnimationMap().add(item17s2);
		b.getListAnimationMap().add(item17s3);
		b.getListAnimationMap().add(item17s4);
		b.getListAnimationMap().add(item17s5);
		b.getListAnimationMap().add(item17s6);
		b.getListAnimationMap().add(item17s7);
		b.getListAnimationMap().add(item17s8);
		b.getListAnimationMap().add(item17s9);
		b.getListAnimationMap().add(item17s10);
		b.getListAnimationMap().add(item17s11);
		b.getListAnimationMap().add(item17s12);
		b.getListAnimationMap().add(item17s13);
		b.getListAnimationMap().add(item17s14);
		b.getListAnimationMap().add(item17s15);
		b.getListAnimationMap().add(item17s16);
		b.getListAnimationMap().add(item17s17);

		item18s = new Animation(b.getGame().getInputData().getListAnimation().get("introStageItem18"));
		AnimationBackGroundMap item18s1 = new AnimationBackGroundMap(2930, 3454, (GameWorldMegamanX) b.getGame(),
				item18s, 12, 120);
		AnimationBackGroundMap item18s2 = new AnimationBackGroundMap(2962, 3454, (GameWorldMegamanX) b.getGame(),
				item18s, 12, 120);
		AnimationBackGroundMap item18s3 = new AnimationBackGroundMap(3218, 3390, (GameWorldMegamanX) b.getGame(),
				item18s, 12, 120);
		AnimationBackGroundMap item18s4 = new AnimationBackGroundMap(3186, 3390, (GameWorldMegamanX) b.getGame(),
				item18s, 12, 120);
		b.getListAnimationMap().add(item18s1);
		b.getListAnimationMap().add(item18s2);
		b.getListAnimationMap().add(item18s3);
		b.getListAnimationMap().add(item18s4);

		item19s = new Animation(b.getGame().getInputData().getListAnimation().get("introStageItem19"));
		AnimationBackGroundMap item19s1 = new AnimationBackGroundMap(1346, 3271, (GameWorldMegamanX) b.getGame(),
				item19s, 1024, 176);
		AnimationBackGroundMap item19s2 = new AnimationBackGroundMap(2212, 3271, (GameWorldMegamanX) b.getGame(),
				item19s, 1024, 176);
		b.getListAnimationBackEnd().add(item19s1);
		b.getListAnimationBackEnd().add(item19s2);

		item20s = new Animation(b.getGame().getInputData().getListAnimation().get("introStageItem20"));
		AnimationBackGroundMap item20s1 = new AnimationBackGroundMap(2100, 3785, (GameWorldMegamanX) b.getGame(),
				item20s, 1024, 176);
		AnimationBackGroundMap item20s2 = new AnimationBackGroundMap(2964, 3785, (GameWorldMegamanX) b.getGame(),
				item20s, 1024, 176);
		AnimationBackGroundMap item20s3 = new AnimationBackGroundMap(3828, 3785, (GameWorldMegamanX) b.getGame(),
				item20s, 1024, 176);
		b.getListAnimationBackEnd().add(item20s1);
		b.getListAnimationBackEnd().add(item20s2);
		b.getListAnimationBackEnd().add(item20s3);

		item21s = new Animation(b.getGame().getInputData().getListAnimation().get("introStageItem21"));
		AnimationBackGroundMap item21s1 = new AnimationBackGroundMap(1196, 2706, (GameWorldMegamanX) b.getGame(),
				item21s, 168, 6);
		AnimationBackGroundMap item21s2 = new AnimationBackGroundMap(1196, 2876, (GameWorldMegamanX) b.getGame(),
				item21s, 168, 6);
		AnimationBackGroundMap item21s3 = new AnimationBackGroundMap(1566, 2706, (GameWorldMegamanX) b.getGame(),
				item21s, 168, 6);
		AnimationBackGroundMap item21s4 = new AnimationBackGroundMap(1740, 2706, (GameWorldMegamanX) b.getGame(),
				item21s, 168, 6);
		AnimationBackGroundMap item21s5 = new AnimationBackGroundMap(1804, 2706, (GameWorldMegamanX) b.getGame(),
				item21s, 168, 6);
		AnimationBackGroundMap item21s6 = new AnimationBackGroundMap(1632, 2876, (GameWorldMegamanX) b.getGame(),
				item21s, 168, 6);
		AnimationBackGroundMap item21s7 = new AnimationBackGroundMap(1804, 2876, (GameWorldMegamanX) b.getGame(),
				item21s, 168, 6);
		AnimationBackGroundMap item21s8 = new AnimationBackGroundMap(2188, 2706, (GameWorldMegamanX) b.getGame(),
				item21s, 168, 6);
		AnimationBackGroundMap item21s9 = new AnimationBackGroundMap(2188, 2876, (GameWorldMegamanX) b.getGame(),
				item21s, 168, 6);
		AnimationBackGroundMap item21s10 = new AnimationBackGroundMap(8364, 1106, (GameWorldMegamanX) b.getGame(),
				item21s, 168, 6);
		AnimationBackGroundMap item21s11 = new AnimationBackGroundMap(8364, 1244, (GameWorldMegamanX) b.getGame(),
				item21s, 168, 6);
		AnimationBackGroundMap item21s12 = new AnimationBackGroundMap(8364, 1330, (GameWorldMegamanX) b.getGame(),
				item21s, 168, 6);
		AnimationBackGroundMap item21s13 = new AnimationBackGroundMap(8364, 1468, (GameWorldMegamanX) b.getGame(),
				item21s, 168, 6);
		b.getListAnimationMap().add(item21s1);
		b.getListAnimationMap().add(item21s2);
		b.getListAnimationMap().add(item21s3);
		b.getListAnimationMap().add(item21s4);
		b.getListAnimationMap().add(item21s5);
		b.getListAnimationMap().add(item21s6);
		b.getListAnimationMap().add(item21s7);
		b.getListAnimationMap().add(item21s8);
		b.getListAnimationMap().add(item21s9);
		b.getListAnimationMap().add(item21s10);
		b.getListAnimationMap().add(item21s11);
		b.getListAnimationMap().add(item21s12);
		b.getListAnimationMap().add(item21s13);

		item22s = new Animation(b.getGame().getInputData().getListAnimation().get("introStageItem22"));
		item22sDaoNguoc = new Animation(b.getGame().getInputData().getListAnimation().get("introStageItem22"));
		item22sDaoNguoc.daoNguoc();
		AnimationBackGroundMap item22s1 = new AnimationBackGroundMap(1150, 2712, (GameWorldMegamanX) b.getGame(),
				item22s, 18, 18);
		AnimationBackGroundMap item22s2 = new AnimationBackGroundMap(1392, 2712, (GameWorldMegamanX) b.getGame(),
				item22sDaoNguoc, 18, 18);
		AnimationBackGroundMap item22s3 = new AnimationBackGroundMap(1534, 2712, (GameWorldMegamanX) b.getGame(),
				item22s, 18, 18);
		AnimationBackGroundMap item22s4 = new AnimationBackGroundMap(2000, 2712, (GameWorldMegamanX) b.getGame(),
				item22sDaoNguoc, 18, 18);
		AnimationBackGroundMap item22s5 = new AnimationBackGroundMap(2142, 2712, (GameWorldMegamanX) b.getGame(),
				item22s, 18, 18);
		AnimationBackGroundMap item22s6 = new AnimationBackGroundMap(2386, 2712, (GameWorldMegamanX) b.getGame(),
				item22sDaoNguoc, 18, 18);
		AnimationBackGroundMap item22s7 = new AnimationBackGroundMap(2960, 3130, (GameWorldMegamanX) b.getGame(),
				item22sDaoNguoc, 18, 18);
		AnimationBackGroundMap item22s8 = new AnimationBackGroundMap(3136, 3130, (GameWorldMegamanX) b.getGame(),
				item22s, 18, 18);
		AnimationBackGroundMap item22s9 = new AnimationBackGroundMap(3504, 3130, (GameWorldMegamanX) b.getGame(),
				item22sDaoNguoc, 18, 18);
		AnimationBackGroundMap item22s10 = new AnimationBackGroundMap(3680, 3130, (GameWorldMegamanX) b.getGame(),
				item22s, 18, 18);
		AnimationBackGroundMap item22s11 = new AnimationBackGroundMap(6814, 1656, (GameWorldMegamanX) b.getGame(),
				item22s, 18, 18);
		AnimationBackGroundMap item22s12 = new AnimationBackGroundMap(6814, 1880, (GameWorldMegamanX) b.getGame(),
				item22s, 18, 18);
		AnimationBackGroundMap item22s13 = new AnimationBackGroundMap(6814, 2104, (GameWorldMegamanX) b.getGame(),
				item22s, 18, 18);
		AnimationBackGroundMap item22s14 = new AnimationBackGroundMap(6814, 2328, (GameWorldMegamanX) b.getGame(),
				item22s, 18, 18);
		AnimationBackGroundMap item22s15 = new AnimationBackGroundMap(6814, 2552, (GameWorldMegamanX) b.getGame(),
				item22s, 18, 18);
		AnimationBackGroundMap item22s16 = new AnimationBackGroundMap(6814, 2776, (GameWorldMegamanX) b.getGame(),
				item22s, 18, 18);
		AnimationBackGroundMap item22s17 = new AnimationBackGroundMap(6814, 3000, (GameWorldMegamanX) b.getGame(),
				item22s, 18, 18);
		AnimationBackGroundMap item22s18 = new AnimationBackGroundMap(6814, 3224, (GameWorldMegamanX) b.getGame(),
				item22s, 18, 18);
		AnimationBackGroundMap item22s19 = new AnimationBackGroundMap(6992, 1656, (GameWorldMegamanX) b.getGame(),
				item22sDaoNguoc, 18, 18);
		AnimationBackGroundMap item22s20 = new AnimationBackGroundMap(6992, 1880, (GameWorldMegamanX) b.getGame(),
				item22sDaoNguoc, 18, 18);
		AnimationBackGroundMap item22s21 = new AnimationBackGroundMap(6992, 2104, (GameWorldMegamanX) b.getGame(),
				item22sDaoNguoc, 18, 18);
		AnimationBackGroundMap item22s22 = new AnimationBackGroundMap(6992, 2328, (GameWorldMegamanX) b.getGame(),
				item22sDaoNguoc, 18, 18);
		AnimationBackGroundMap item22s23 = new AnimationBackGroundMap(6992, 2552, (GameWorldMegamanX) b.getGame(),
				item22sDaoNguoc, 18, 18);
		AnimationBackGroundMap item22s24 = new AnimationBackGroundMap(6992, 2776, (GameWorldMegamanX) b.getGame(),
				item22sDaoNguoc, 18, 18);
		AnimationBackGroundMap item22s25 = new AnimationBackGroundMap(6992, 3000, (GameWorldMegamanX) b.getGame(),
				item22sDaoNguoc, 18, 18);
		AnimationBackGroundMap item22s26 = new AnimationBackGroundMap(6992, 3224, (GameWorldMegamanX) b.getGame(),
				item22sDaoNguoc, 18, 18);
		AnimationBackGroundMap item22s27 = new AnimationBackGroundMap(8318, 1112, (GameWorldMegamanX) b.getGame(),
				item22s, 18, 18);
		AnimationBackGroundMap item22s28 = new AnimationBackGroundMap(8560, 1112, (GameWorldMegamanX) b.getGame(),
				item22sDaoNguoc, 18, 18);
		b.getListAnimationMap().add(item22s1);
		b.getListAnimationMap().add(item22s2);
		b.getListAnimationMap().add(item22s3);
		b.getListAnimationMap().add(item22s4);
		b.getListAnimationMap().add(item22s5);
		b.getListAnimationMap().add(item22s6);
		b.getListAnimationMap().add(item22s7);
		b.getListAnimationMap().add(item22s8);
		b.getListAnimationMap().add(item22s9);
		b.getListAnimationMap().add(item22s10);
		b.getListAnimationMap().add(item22s11);
		b.getListAnimationMap().add(item22s12);
		b.getListAnimationMap().add(item22s13);
		b.getListAnimationMap().add(item22s14);
		b.getListAnimationMap().add(item22s15);
		b.getListAnimationMap().add(item22s16);
		b.getListAnimationMap().add(item22s17);
		b.getListAnimationMap().add(item22s18);
		b.getListAnimationMap().add(item22s19);
		b.getListAnimationMap().add(item22s20);
		b.getListAnimationMap().add(item22s21);
		b.getListAnimationMap().add(item22s22);
		b.getListAnimationMap().add(item22s23);
		b.getListAnimationMap().add(item22s24);
		b.getListAnimationMap().add(item22s25);
		b.getListAnimationMap().add(item22s26);
		b.getListAnimationMap().add(item22s27);
		b.getListAnimationMap().add(item22s28);

		item23s = new Animation(b.getGame().getInputData().getListAnimation().get("introStageItem23"));
		item23sDaoNguoc = new Animation(b.getGame().getInputData().getListAnimation().get("introStageItem23"));
		item23sDaoNguoc.daoNguoc();
		AnimationBackGroundMap item23s1 = new AnimationBackGroundMap(1150, 2858, (GameWorldMegamanX) b.getGame(),
				item23s, 18, 18);
		AnimationBackGroundMap item23s2 = new AnimationBackGroundMap(1534, 2858, (GameWorldMegamanX) b.getGame(),
				item23s, 18, 18);
		AnimationBackGroundMap item23s3 = new AnimationBackGroundMap(2000, 2858, (GameWorldMegamanX) b.getGame(),
				item23sDaoNguoc, 18, 18);
		AnimationBackGroundMap item23s4 = new AnimationBackGroundMap(2142, 2858, (GameWorldMegamanX) b.getGame(),
				item23s, 18, 18);
		AnimationBackGroundMap item23s5 = new AnimationBackGroundMap(2386, 2858, (GameWorldMegamanX) b.getGame(),
				item23sDaoNguoc, 18, 18);
		AnimationBackGroundMap item23s6 = new AnimationBackGroundMap(2960, 3370, (GameWorldMegamanX) b.getGame(),
				item23sDaoNguoc, 18, 18);
		AnimationBackGroundMap item23s7 = new AnimationBackGroundMap(3504, 3434, (GameWorldMegamanX) b.getGame(),
				item23sDaoNguoc, 18, 18);
		AnimationBackGroundMap item23s8 = new AnimationBackGroundMap(3680, 3434, (GameWorldMegamanX) b.getGame(),
				item23s, 18, 18);
		AnimationBackGroundMap item23s9 = new AnimationBackGroundMap(6814, 1770, (GameWorldMegamanX) b.getGame(),
				item23s, 18, 18);
		AnimationBackGroundMap item23s10 = new AnimationBackGroundMap(6814, 1994, (GameWorldMegamanX) b.getGame(),
				item23s, 18, 18);
		AnimationBackGroundMap item23s11 = new AnimationBackGroundMap(6814, 2218, (GameWorldMegamanX) b.getGame(),
				item23s, 18, 18);
		AnimationBackGroundMap item23s12 = new AnimationBackGroundMap(6814, 2442, (GameWorldMegamanX) b.getGame(),
				item23s, 18, 18);
		AnimationBackGroundMap item23s13 = new AnimationBackGroundMap(6814, 2666, (GameWorldMegamanX) b.getGame(),
				item23s, 18, 18);
		AnimationBackGroundMap item23s14 = new AnimationBackGroundMap(6814, 2890, (GameWorldMegamanX) b.getGame(),
				item23s, 18, 18);
		AnimationBackGroundMap item23s15 = new AnimationBackGroundMap(6814, 3114, (GameWorldMegamanX) b.getGame(),
				item23s, 18, 18);
		AnimationBackGroundMap item23s16 = new AnimationBackGroundMap(6814, 3338, (GameWorldMegamanX) b.getGame(),
				item23s, 18, 18);
		AnimationBackGroundMap item23s17 = new AnimationBackGroundMap(6992, 1770, (GameWorldMegamanX) b.getGame(),
				item23sDaoNguoc, 18, 18);
		AnimationBackGroundMap item23s18 = new AnimationBackGroundMap(6992, 1994, (GameWorldMegamanX) b.getGame(),
				item23sDaoNguoc, 18, 18);
		AnimationBackGroundMap item23s19 = new AnimationBackGroundMap(6992, 2218, (GameWorldMegamanX) b.getGame(),
				item23sDaoNguoc, 18, 18);
		AnimationBackGroundMap item23s20 = new AnimationBackGroundMap(6992, 2442, (GameWorldMegamanX) b.getGame(),
				item23sDaoNguoc, 18, 18);
		AnimationBackGroundMap item23s21 = new AnimationBackGroundMap(6992, 2666, (GameWorldMegamanX) b.getGame(),
				item23sDaoNguoc, 18, 18);
		AnimationBackGroundMap item23s22 = new AnimationBackGroundMap(6992, 2890, (GameWorldMegamanX) b.getGame(),
				item23sDaoNguoc, 18, 18);
		AnimationBackGroundMap item23s23 = new AnimationBackGroundMap(6992, 3114, (GameWorldMegamanX) b.getGame(),
				item23sDaoNguoc, 18, 18);
		AnimationBackGroundMap item23s24 = new AnimationBackGroundMap(6992, 3338, (GameWorldMegamanX) b.getGame(),
				item23sDaoNguoc, 18, 18);
		AnimationBackGroundMap item23s25 = new AnimationBackGroundMap(8318, 1226, (GameWorldMegamanX) b.getGame(),
				item23s, 18, 18);
		AnimationBackGroundMap item23s26 = new AnimationBackGroundMap(8560, 1226, (GameWorldMegamanX) b.getGame(),
				item23sDaoNguoc, 18, 18);
		b.getListAnimationMap().add(item23s1);
		b.getListAnimationMap().add(item23s2);
		b.getListAnimationMap().add(item23s3);
		b.getListAnimationMap().add(item23s4);
		b.getListAnimationMap().add(item23s5);
		b.getListAnimationMap().add(item23s6);
		b.getListAnimationMap().add(item23s7);
		b.getListAnimationMap().add(item23s8);
		b.getListAnimationMap().add(item23s9);
		b.getListAnimationMap().add(item23s10);
		b.getListAnimationMap().add(item23s11);
		b.getListAnimationMap().add(item23s12);
		b.getListAnimationMap().add(item23s13);
		b.getListAnimationMap().add(item23s14);
		b.getListAnimationMap().add(item23s15);
		b.getListAnimationMap().add(item23s16);
		b.getListAnimationMap().add(item23s17);
		b.getListAnimationMap().add(item23s18);
		b.getListAnimationMap().add(item23s19);
		b.getListAnimationMap().add(item23s20);
		b.getListAnimationMap().add(item23s21);
		b.getListAnimationMap().add(item23s22);
		b.getListAnimationMap().add(item23s23);
		b.getListAnimationMap().add(item23s24);
		b.getListAnimationMap().add(item23s25);
		b.getListAnimationMap().add(item23s26);

		item24s = new Animation(b.getGame().getInputData().getListAnimation().get("introStageItem24"));
		AnimationBackGroundMap item24s1 = new AnimationBackGroundMap(6860, 1650, (GameWorldMegamanX) b.getGame(),
				item24s, 104, 6);
		AnimationBackGroundMap item24s2 = new AnimationBackGroundMap(6860, 1788, (GameWorldMegamanX) b.getGame(),
				item24s, 104, 6);
		AnimationBackGroundMap item24s3 = new AnimationBackGroundMap(6860, 1874, (GameWorldMegamanX) b.getGame(),
				item24s, 104, 6);
		AnimationBackGroundMap item24s4 = new AnimationBackGroundMap(6860, 2012, (GameWorldMegamanX) b.getGame(),
				item24s, 104, 6);
		AnimationBackGroundMap item24s5 = new AnimationBackGroundMap(6860, 2098, (GameWorldMegamanX) b.getGame(),
				item24s, 104, 6);
		AnimationBackGroundMap item24s6 = new AnimationBackGroundMap(6860, 2236, (GameWorldMegamanX) b.getGame(),
				item24s, 104, 6);
		AnimationBackGroundMap item24s7 = new AnimationBackGroundMap(6860, 2322, (GameWorldMegamanX) b.getGame(),
				item24s, 104, 6);
		AnimationBackGroundMap item24s8 = new AnimationBackGroundMap(6860, 2460, (GameWorldMegamanX) b.getGame(),
				item24s, 104, 6);
		AnimationBackGroundMap item24s9 = new AnimationBackGroundMap(6860, 2546, (GameWorldMegamanX) b.getGame(),
				item24s, 104, 6);
		AnimationBackGroundMap item24s10 = new AnimationBackGroundMap(6860, 2684, (GameWorldMegamanX) b.getGame(),
				item24s, 104, 6);
		AnimationBackGroundMap item24s11 = new AnimationBackGroundMap(6860, 2770, (GameWorldMegamanX) b.getGame(),
				item24s, 104, 6);
		AnimationBackGroundMap item24s12 = new AnimationBackGroundMap(6860, 2908, (GameWorldMegamanX) b.getGame(),
				item24s, 104, 6);
		AnimationBackGroundMap item24s13 = new AnimationBackGroundMap(6860, 2994, (GameWorldMegamanX) b.getGame(),
				item24s, 104, 6);
		AnimationBackGroundMap item24s14 = new AnimationBackGroundMap(6860, 3132, (GameWorldMegamanX) b.getGame(),
				item24s, 104, 6);
		AnimationBackGroundMap item24s15 = new AnimationBackGroundMap(6860, 3218, (GameWorldMegamanX) b.getGame(),
				item24s, 104, 6);
		AnimationBackGroundMap item24s16 = new AnimationBackGroundMap(6860, 3356, (GameWorldMegamanX) b.getGame(),
				item24s, 104, 6);
		b.getListAnimationMap().add(item24s1);
		b.getListAnimationMap().add(item24s2);
		b.getListAnimationMap().add(item24s3);
		b.getListAnimationMap().add(item24s4);
		b.getListAnimationMap().add(item24s5);
		b.getListAnimationMap().add(item24s6);
		b.getListAnimationMap().add(item24s7);
		b.getListAnimationMap().add(item24s8);
		b.getListAnimationMap().add(item24s9);
		b.getListAnimationMap().add(item24s10);
		b.getListAnimationMap().add(item24s11);
		b.getListAnimationMap().add(item24s12);
		b.getListAnimationMap().add(item24s13);
		b.getListAnimationMap().add(item24s14);
		b.getListAnimationMap().add(item24s15);
		b.getListAnimationMap().add(item24s16);

		npc = new Animation(b.getGame().getInputData().getListAnimation().get("introStageMapNPC"));
		b.getListAnimationMap()
				.add(new AnimationBackGroundMap(7712, 1592, (GameWorldMegamanX) b.getGame(), npc, 448, 322));
	}

	public Animation getItem1() {
		return item1;
	}

	public void setItem1(Animation item1) {
		this.item1 = item1;
	}

	public Animation getItem2() {
		return item2;
	}

	public void setItem2(Animation item2) {
		this.item2 = item2;
	}

	public Animation getItem3() {
		return item3;
	}

	public void setItem3(Animation item3) {
		this.item3 = item3;
	}

	public Animation getItem4() {
		return item4;
	}

	public void setItem4(Animation item4) {
		this.item4 = item4;
	}

	public Animation getItem5() {
		return item5;
	}

	public void setItem5(Animation item5) {
		this.item5 = item5;
	}

	public Animation getItem6() {
		return item6;
	}

	public void setItem6(Animation item6) {
		this.item6 = item6;
	}

	public Animation getItem7() {
		return item7;
	}

	public void setItem7(Animation item7) {
		this.item7 = item7;
	}

	public Animation getItem7daoNguoc() {
		return item7daoNguoc;
	}

	public void setItem7daoNguoc(Animation item7daoNguoc) {
		this.item7daoNguoc = item7daoNguoc;
	}

	public Animation getItem8() {
		return item8;
	}

	public void setItem8(Animation item8) {
		this.item8 = item8;
	}

	public Animation getItem8daoNguoc() {
		return item8daoNguoc;
	}

	public void setItem8daoNguoc(Animation item8daoNguoc) {
		this.item8daoNguoc = item8daoNguoc;
	}

	public Animation getItem9() {
		return item9;
	}

	public void setItem9(Animation item9) {
		this.item9 = item9;
	}

	public Animation getItem10() {
		return item10;
	}

	public void setItem10(Animation item10) {
		this.item10 = item10;
	}

	public Animation getItem10daoNguoc() {
		return item10daoNguoc;
	}

	public void setItem10daoNguoc(Animation item10daoNguoc) {
		this.item10daoNguoc = item10daoNguoc;
	}

	public Animation getItem12s() {
		return item12s;
	}

	public void setItem12s(Animation item12s) {
		this.item12s = item12s;
	}

	public Animation getItem13s() {
		return item13s;
	}

	public void setItem13s(Animation item13s) {
		this.item13s = item13s;
	}

	public Animation getItem13sdaoNguoc() {
		return item13sdaoNguoc;
	}

	public void setItem13sdaoNguoc(Animation item13sdaoNguoc) {
		this.item13sdaoNguoc = item13sdaoNguoc;
	}

	public Animation getItem14s() {
		return item14s;
	}

	public void setItem14s(Animation item14s) {
		this.item14s = item14s;
	}

	public Animation getItem14sdaoNguoc() {
		return item14sdaoNguoc;
	}

	public void setItem14sdaoNguoc(Animation item14sdaoNguoc) {
		this.item14sdaoNguoc = item14sdaoNguoc;
	}

	public Animation getItem15s() {
		return item15s;
	}

	public void setItem15s(Animation item15s) {
		this.item15s = item15s;
	}

	public Animation getItem16s() {
		return item16s;
	}

	public void setItem16s(Animation item16s) {
		this.item16s = item16s;
	}

	public Animation getItem17s() {
		return item17s;
	}

	public void setItem17s(Animation item17s) {
		this.item17s = item17s;
	}

	public Animation getItem18s() {
		return item18s;
	}

	public void setItem18s(Animation item18s) {
		this.item18s = item18s;
	}

	public Animation getItem19s() {
		return item19s;
	}

	public void setItem19s(Animation item19s) {
		this.item19s = item19s;
	}

	public Animation getItem20s() {
		return item20s;
	}

	public void setItem20s(Animation item20s) {
		this.item20s = item20s;
	}

	public Animation getItem21s() {
		return item21s;
	}

	public void setItem21s(Animation item21s) {
		this.item21s = item21s;
	}

	public Animation getItem22s() {
		return item22s;
	}

	public void setItem22s(Animation item22s) {
		this.item22s = item22s;
	}

	public Animation getItem22sDaoNguoc() {
		return item22sDaoNguoc;
	}

	public void setItem22sDaoNguoc(Animation item22sDaoNguoc) {
		this.item22sDaoNguoc = item22sDaoNguoc;
	}

	public Animation getItem23s() {
		return item23s;
	}

	public void setItem23s(Animation item23s) {
		this.item23s = item23s;
	}

	public Animation getItem23sDaoNguoc() {
		return item23sDaoNguoc;
	}

	public void setItem23sDaoNguoc(Animation item23sDaoNguoc) {
		this.item23sDaoNguoc = item23sDaoNguoc;
	}

	public Animation getItem24s() {
		return item24s;
	}

	public void setItem24s(Animation item24s) {
		this.item24s = item24s;
	}

	public Animation getNpc() {
		return npc;
	}

	public void setNpc(Animation npc) {
		this.npc = npc;
	}

	public BackGroundMapMegamanX getB() {
		return b;
	}

	public void setB(BackGroundMapMegamanX b) {
		this.b = b;
	}

}
