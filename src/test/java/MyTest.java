import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.HashSet;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.jupiter.api.DisplayName;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class MyTest {

	Player player = new Player();
	BetCard betCard = new BetCard();

	@Test
	public void testGetCard() {
		BetCard card = new BetCard(54);
		assertEquals(54, card.getCard());
	}

	@Test
	public void testSetSelected() {
		BetCard card = new BetCard(47);
		assertFalse(card.getSelected());
		card.setSelected(true);
		assertTrue(card.getSelected());
	}

	@Test
	public void testTwoSetSelected() {
		BetCard betCard1 = new BetCard(12);
		BetCard betCard2 = new BetCard(34);
		assertFalse(betCard1.getSelected());
		assertFalse(betCard2.getSelected());
		betCard1.setSelected(true);
		betCard2.setSelected(true);
		assertTrue(betCard1.getSelected());
		assertTrue(betCard2.getSelected());
	}

	@Test
	public void testGetRow() {
		BetCard betCard1 = new BetCard(12);
		BetCard betCard2 = new BetCard(37);
		assertEquals(1, betCard1.getRow());
		assertEquals(3, betCard2.getRow());
	}

	@Test
	public void testTwoGetRow() {
		BetCard betCard1 = new BetCard(52);
		BetCard betCard2 = new BetCard(77);
		assertEquals(5, betCard1.getRow());
		assertEquals(7, betCard2.getRow());
	}

	@Test
	public void testGetColumn() {
		BetCard card1 = new BetCard(71);
		BetCard card2 = new BetCard(43);
		assertEquals(1, card1.getColumn());
		assertEquals(3, card2.getColumn());
	}

	@Test
	public void testTwoGetColumn() {
		BetCard card1 = new BetCard(68);
		BetCard card2 = new BetCard(14);
		assertEquals(8, card1.getColumn());
		assertEquals(4, card2.getColumn());
	}


	@Test
	public void testSetNumSpots(){
		player.setNumSpots(5);
		assert player.getNumSpots() == 5 : "setNumSpots failed";
	}

	@Test
	public void testTwoSetNumSpots(){
		player.setNumSpots(31);
		assert player.getNumSpots() == 31 : "setNumSpots failed";
	}

	@Test
	public void testSetNumDrawings(){
		player.setNumDrawings(2);
		assert player.getNumDrawings() == 2 : "setNumDrawings failed";
	}

	@Test
	public void testTwoSetNumDrawings(){
		player.setNumDrawings(4);
		assert player.getNumDrawings() == 4 : "setNumDrawings failed";
	}

	@Test
	public void testAutoSelect(){
		player.setAutoSelect(true);
		player.quickPick(8);
		ArrayList<BetCard> selectedNums = player.getSelectedNums();
		assertEquals(8,selectedNums.size(),"wrong");

	}

	@Test
	public void testSelect(){
		player.setNumSpots(2);
		BetCard betCard1 = new BetCard(1);
		BetCard betCard2 = new BetCard(2);
		player.select(betCard1);
		player.select(betCard2);
		ArrayList<BetCard> selectedNums = player.getSelectedNums();
		assert selectedNums.size() == 2 && selectedNums.contains(betCard1) && selectedNums.contains(betCard2): "select failed";
	}

	@Test
	public void testTwoSelect(){
		player.setNumSpots(4);
		BetCard betCard1 = new BetCard(13);
		BetCard betCard2 = new BetCard(22);
		BetCard betCard3 = new BetCard(45);
		BetCard betCard4 = new BetCard(67);
		player.select(betCard1);
		player.select(betCard2);
		player.select(betCard3);
		player.select(betCard4);
		ArrayList<BetCard> selectedNums = player.getSelectedNums();
		assert selectedNums.size() == 4 && selectedNums.contains(betCard1) && selectedNums.contains(betCard2)
				&& selectedNums.contains(betCard3) && selectedNums.contains(betCard4): "select failed";
	}

	@Test
	public void testQuickPick(){
		player.quickPick(4);
		ArrayList<BetCard> selectedNums = player.getSelectedNums();
		assert selectedNums.size() == 4 : "quickPick failed";
	}

	@Test
	public void testTwoQuickPick(){
		player.quickPick(9);
		ArrayList<BetCard> selectedNums = player.getSelectedNums();
		assert selectedNums.size() == 9 : "quickPick failed";
	}

	@Test
	public void testRemoveSelectNumber(){
		player.setNumSpots(2);
		BetCard betCard1 = new BetCard(1);
		BetCard betCard2 = new BetCard(2);
		player.select(betCard1);
		player.select(betCard2);
		player.removeSelectNumber(betCard2);
		ArrayList<BetCard> selectedNums = player.getSelectedNums();
		assert selectedNums.size() == 1 && selectedNums.contains(betCard1) && !selectedNums.contains(betCard2) : "removeSelectNumber failed";
	}

	@Test
	public void testTwoRemoveSelectNumber(){
		player.setNumSpots(3);
		BetCard betCard1 = new BetCard(1);
		BetCard betCard2 = new BetCard(2);
		BetCard betCard3 = new BetCard(3);
		player.select(betCard1);
		player.select(betCard2);
		player.select(betCard3);
		player.removeSelectNumber(betCard1);
		player.removeSelectNumber(betCard2);
		ArrayList<BetCard> selectedNums = player.getSelectedNums();
		assert selectedNums.size() == 1 && selectedNums.contains(betCard3)
				&& !selectedNums.contains(betCard1) && !selectedNums.contains(betCard2) : "removeSelectNumber failed";
	}

	@Test
	public void testClear(){
		player.setNumSpots(2);
		BetCard betCard1 = new BetCard(1);
		BetCard betCard2 = new BetCard(2);
		player.select(betCard1);
		player.select(betCard2);
		ArrayList<BetCard> selectedNums = player.getSelectedNums();
		assert selectedNums.size() == 2 : "size error";
		player.clear();
		assert selectedNums.isEmpty() : "clear method is not working";
	}

//	@Test
//	public void testPlay(){
//		KenoGame keno = new KenoGame();
//		player.setNumSpots(4);
//		player.select(new BetCard(1));
//		player.select(new BetCard(2));
//		player.select(new BetCard(3));
//		player.select(new BetCard(4));
//		player.play(keno);
////		assert player.getWining() > 0 : "play failed";
////      assertEquals(9, player.getBalance(), "wrong");
//	}



	@Test
	public void testStartDrawing() {
		KenoGame game = new KenoGame();
		game.startDrawing();
		ArrayList<BetCard> drawingNums = game.getDrawingNums();
		assertEquals(20, drawingNums.size());
		HashSet<Integer> set = new HashSet<>();
		for (BetCard betCard : drawingNums) {
			int num = betCard.getCard();
			assertTrue(num >= 1 && num <= 80);
			assertFalse(set.contains(num));
			set.add(num);
		}
	}

	@Test
	public void testIsNumberDrawn() {
		KenoGame game = new KenoGame();
		game.startDrawing();
		ArrayList<BetCard> drawingNums = game.getDrawingNums();
		for (BetCard betCard : drawingNums) {
			assertTrue(game.isNumberDrawn(betCard.getCard()));
		}
		assertFalse(game.isNumberDrawn(0));
		assertFalse(game.isNumberDrawn(99));
	}

	@Test
	public void testGetPrizeAndOdds1() {
		KenoGame game = new KenoGame();
		game.startDrawing();
		// test 1 spot game
		assertEquals(2, game.getPrizeAndOdds(1, 1), "wrong");
	}
	@Test
	public void testGetPrizeAndOdds4() {
		KenoGame game = new KenoGame();
		game.startDrawing();
		// test 4 spot game
		assertEquals(1, game.getPrizeAndOdds(4, 2), "wrong");
		assertEquals(5, game.getPrizeAndOdds(4, 3), "wrong");
		assertEquals(75, game.getPrizeAndOdds(4, 4), "wrong");
	}

	@Test
	public void testGetPrizeAndOdds8() {
		KenoGame game = new KenoGame();
		game.startDrawing();
		// test 8 spot game
		assertEquals(2, game.getPrizeAndOdds(8, 4), "wrong");
		assertEquals(12, game.getPrizeAndOdds(8, 5), "wrong");
		assertEquals(50, game.getPrizeAndOdds(8, 6), "wrong");
		assertEquals(750, game.getPrizeAndOdds(8, 7), "wrong");
	}
	@Test
	public void testGetPrizeAndOdds10() {
		KenoGame game = new KenoGame();
		game.startDrawing();
		// test 10 spot game
		assertEquals(5, game.getPrizeAndOdds(10, 0), "wrong");
		assertEquals(2, game.getPrizeAndOdds(10, 5), "wrong");
		assertEquals(15, game.getPrizeAndOdds(10, 6), "wrong");
		assertEquals(40, game.getPrizeAndOdds(10, 7), "wrong");
		assertEquals(4250, game.getPrizeAndOdds(10, 9), "wrong");
		assertEquals(100000, game.getPrizeAndOdds(10, 10), "wrong");

	}

}

