import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TestCreature {

	//Testing the constructor and getters and setters
	@Test
	void test1() {
		Creature a = new Creature(100, 100);
		
		//Checking the health and strength
		assertEquals(a.getHealth(), 100);
		assertEquals(a.getStrength(), 100);
		
		//Testing setters
		a.setHealth(50);
		a.setStrength(25);
		assertEquals(a.getHealth(), 50);
		assertEquals(a.getStrength(), 25);
	}
	
	//Testing strength excpetion
	@Test
	void testStrengthExc()
	{
		assertThrows(IllegalArgumentException.class, () -> {
		new Creature(-5, 100); // Exception
		});	
		
		assertThrows(IllegalArgumentException.class, () -> {
		new Creature(101, 100); // Exception
		});	
	}
	
	//Testing health excpetion
	@Test
	void testHealthExc()
	{
		assertThrows(IllegalArgumentException.class, () -> {
		new Creature(55, -5); // Exception
		});	
		
		assertThrows(IllegalArgumentException.class, () -> {
		new Creature(75, 205); // Exception
		});	
	}
	
	//Testing attack
	@Test
	void testAttack()
	{
		Creature a = new Creature(50, 156);
		int attack = a.attack();
		if(attack > a.getStrength() || attack < 0) //Checking if correct random values
		{
			System.out.println("Attack should be between 0 and strength");
		}
		
	}
	
	//Testing attack while froze and give spell and remove spell
	//Also test the freeze method and can cast and isFrozen and decrement
	@Test
	void testAttack2()
	{
		Creature a = new Creature(50, 156);
		a.giveSpell(SpellType.Frost);
		assertEquals(a.canCast(SpellType.Frost), true);
		//Testing toString()
		assertEquals(a.toString(), "This creature has 50 strength and 156 health and is a carrying [Frost] spells");
		
		a.freeze();
		assertEquals(a.attack(), 0); //Testing attack should be 0
		a.removeSpell(SpellType.Frost);
		assertEquals(a.toString(), "This creature has 50 strength and 156 health");

		//Testing if the spells was removed and that it now throws exception if ou try to cast it
		assertEquals(a.canCast(SpellType.Frost), false);

		
	}
	
	//Testing hurt and isDead
	@Test
	void testHurtDead()
	{
		Creature a = new Creature(50, 150);
		a.hurt(50);
		assertEquals(a.getHealth(), 100); //Testing new health
		assertEquals(a.isDead(), false);
		
		//Killing the creature
		a.hurt(100);
		assertEquals(a.isDead(), true);
		
		
	}
	
	//Testing the freeze counter
	@Test
	void testFreeze()
	{
		Creature a = new Creature(50, 150);
		
		//Using a for loop to test the random number a bunch of times
		for(int i = 0; i < 300; i++)
		{
			a.giveSpell(SpellType.Frost);
			a.freeze();
			
			//Checking correct values for frozen timer
			if(a.getFrozenTime() < 2 || a.getFrozenTime() > 4)
			{
				System.out.println("Time frozen must be between 2 - 4");
			}
		}
		
		//Testing if it is decremented
		a.giveSpell(SpellType.Frost);
		a.freeze();
		int temp = a.getFrozenTime();
		a.decFreezeTimer();
		assertEquals(a.getFrozenTime(), temp - 1);
	}
	
	//Testing all fire methods
	@Test
	void testFire()
	{
		Creature a = new Creature(50, 150);
		
		assertEquals(a.isOnFire(), false); //Testing that it is not on fire
		
		//Using a for loop to test the random number a bunch of times
		for(int i = 0; i < 300; i++)
		{
			a.giveSpell(SpellType.Fire);
			a.setOnFire();
			
			//Checking correct values for frozen timer
			if(a.getFireTime() < 2 || a.getFireTime() > 4)
			{
				System.out.println("Time on fire must be between 2 - 4");
			}
			
		}
		
		//Testing if decremented correctly
		a.giveSpell(SpellType.Fire);
		a.setOnFire(); //Spell should be gone now
		assertEquals(a.isOnFire(), true); //Testing the is on fire
		int temp = a.getFireTime();
		a.decFireTimer();
		assertEquals(a.getFireTime(), temp - 1);
		

	}
	
	//Testing the heal method
	@Test
	void testHeal()
	{
		Creature a = new Creature(50, 150);
		a.heal(25);
		
		assertEquals(a.getHealth(), 175);
		
		//Testing if healed over max
		a.heal(202);
		assertEquals(a.getHealth(), 200);
	}

}
