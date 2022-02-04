import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TestPlayer {

	//Testing the constructor and getters and setters.
	@Test
	void testConstructor() {
		Player a = new Player(100, 150);
		assertEquals(a.getHealth(), 150);
		assertEquals(a.getStrength(), 100);
		assertEquals(a.getXP(), 0);
		a.heal(50);
		assertEquals(a.getHealth(), 200);
		a.heal(50);
		assertEquals(a.getHealth(), 200);
	}
	
	//Testing leveling up and getting xp
	@Test
	void testlevel() {
		Player a = new Player(100, 150);
		a.addXP(50);
		assertEquals(a.canLevelUP(), false); //Checking that you cannot level up yet
		
		//Leveling the player up and checking values
		a.addXP(51);
		a.levelUp(SpellType.Lightning);
		assertEquals(a.getXP(), 1);
		assertEquals(a.getHealth(), 210);
		assertEquals(a.getStrength(), 105);
	}

}
