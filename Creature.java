
//Project 3
//Jayden Schafsnitz
//4/5/2021
//Creature Class - Creates a creature object

import java.util.ArrayList;
import java.util.Random;

public class Creature {
	
	//variables for the strength and health of the creature
	protected int strength;
	protected int health;
	private int FrozeTimer, FireTimer;
	
	//Arraylist for spells the creature has
	ArrayList<SpellType> spells;
	
	//*********** Creature Method **************
	//Constructor for creature
    //@param - strength1 = Strenght of monster - health1 = health of monster
	public Creature(int strength1, int health1)
	{
		//Checking for strength less than or equal to zero or greater than 100
		if(strength1 <= 0 || strength1 > 100)
		{
			throw new IllegalArgumentException("Stength must be between 0 - 100");
		}
		
		//Checking for correct health
		if(health1 <= 0 || health1 > 200)
		{
			throw new IllegalArgumentException("Health must be between 0 - 200");
		}
		
		strength = strength1;
		health = health1;
		spells = new ArrayList<SpellType>();
		FrozeTimer = 0;
		FireTimer = 0;

	}
	
	//*********** getHealth Method **************
	//Gets the creatures health
    //@return - Returns the creatures health
	public int getHealth()
	{
		return health;
	}
	
	//*********** getHealth Method **************
	//Gets the creatures strength
    //@return - Returns the creatures strength
	public int getStrength()
	{
		return strength;
	}
	
	//*********** setHealth Method **************
	//Sets the creatures health
    //@param - health1 = value for creatures health
	public void setHealth(int health1)
	{
		health = health1;
	}
	
	//*********** setStrength Method **************
	//Sets the creatures strength
    //@param - strength1 = value for creatures strength
	public void setStrength(int strength1)
	{
		strength = strength1;
	}	
	
	//*********** attack Method **************
	//Returns an attack between 0 and strength if the creature is not frozen
    //@return - Returns a random attack between 0 and strength
	public int attack()
	{
		Random rand = new Random();
		
		if(isFrozen())
		{
			System.out.println("You are frozen and cannot do this");
			return 0;
		}
		else
		{
			return(rand.nextInt(strength + 1));
		}
	}
	
	//*********** hurt Method **************
	//Takes health off creature
    //@param - damage = ammount of damage done
	public void hurt(int damage)
	{
		health -= damage;
	}
	
	//*********** isDead Method **************
	//Checks if the creature is dead
    //@return - Returns true if the creature is dead
	public boolean isDead()
	{
		if(health <= 0)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	//*********** giveSpell Method **************
	//Gives the creature a spell
    //@param - type = type of spell given
	public void giveSpell(SpellType type)
	{
		if(type != null)
		{
			spells.add(type);
		}

	}
	
	//*********** canCast Method **************
	//Checks if the creature can cast the desired spell
	//@param - type = type of spell to cast
    //@return - Returns true if the creature can cast desired spell
	public boolean canCast(SpellType type)
	{
		for(int i = 0; i < spells.size(); i++)
		{
			if(spells.get(i) == type)
			{
				return true;
			}
		}
		return false;
	}
	
	//*********** freeze Method **************
	//Freezes the creature
	public void freeze()
	{

			Random rand = new Random();
			FrozeTimer = rand.nextInt(5 - 2) + 2; //Sets the time on fire to 2 - 4
			
	}
	
	//*********** isFrozen Method **************
	//returns true if the creature is frozen
    //@return - Returns true if the creature is frozen
	public boolean isFrozen()
	{
		if(FrozeTimer <= 0)
		{
			return false;
		}
		else
		{
			return true;
		}
	}
	
	//*********** decFreezeTimer Method **************
	//Decrements the frozen timer
	public void decFreezeTimer()
	{
		if(FrozeTimer > 0) 
		{
			FrozeTimer -= 1;
		}

	}
	
	//*********** setOnFire Method **************
	//Sets the creature on fire
	public void setOnFire()
	{

			Random rand = new Random();
			FireTimer = rand.nextInt(5 - 2) + 2; //Sets the time on fire to 2 - 4
			

	}
	
	//*********** isOnFire Method **************
	//returns true if the creature is on fire
    //@return - Returns true if the creature is on fire
	public boolean isOnFire()
	{
		if(FireTimer <= 0)
		{
			return false;
		}
		else
		{
			return true;
		}
	}
	
	//*********** decFireTimer Method **************
	//Decrements the fire timer
	public void decFireTimer()
	{
		if(FireTimer > 0) 
		{
			FireTimer -= 1;
		}

	}
	
	//*********** heal Method **************
	//Heals the creature
	//@param - amt = ammount to heal creature
	void heal(int amt)
	{
		health = health + amt;
		
		if(health > 200) //Checking for max health
		{
			health = 200;
		}
	}
	
	//*********** removeSpell Method **************
	//Removes a spell from the arraylist
	//@param - type = type of spell to remove
	public void removeSpell(SpellType type)
	{
		spells.remove(type);
	}
	
	//Gets time left frozen
	public int getFrozenTime()
	{
		return FrozeTimer;
	}
	
	//Gets time left on fire
	public int getFireTime()
	{
		return FireTimer;
	}
	
	//*********** toString Method **************
	//Converts the creature object to a string
	public String toString()
	{
		//If the creature has a spell
		if(spells.size() != 0)
		{
			return ("This creature has " + strength + " strength and " + health + " health and is a carrying " + spells + " spells" );
		}
		else
		{
			return ("This creature has " + strength + " strength and " + health + " health" );
		}
	}
	
}
