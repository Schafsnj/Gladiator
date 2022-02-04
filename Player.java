
public class Player extends Creature {
	private int xp;
	private int maxHP;
	
	//*********** Player Method **************
	//Constructor for player
    //@param - strength = Strenght of monster - health = health of monster
	public Player(int strength, int health)
	{
		super(strength, health);
		xp = 0;
		maxHP = 200;
	}
	
	//*********** AddXP Method **************
	//adds xp
    //@param - amt = amount of xp to add
	public void addXP(int amt)
	{
		xp += amt;
	}
	
	//*********** getXPXP Method **************
	//Gets the current xp
    //@return - Returns the amount xp currently
	public int getXP()
	{
		return xp;
	} 
	
	//*********** canLevelUp Method **************
	//checks if you can level up
    //@return - Returns true if the player can level up
	public boolean canLevelUP()
	{
		if(xp >= 100)
		{
			return true;
		}
		else 
		{
			return false;
		}
	}
	
	//*********** LevelUp Method **************
	//Levels the player up
    //@param - type = type of spell to give the player
	public void levelUp(SpellType type)
	{
		maxHP = 200;
		if(canLevelUP())
		{
			xp = xp - 100;
			strength += 5;
			maxHP += 10;
			health = maxHP;
			spells.add(type);
		}
	}
	
	//*********** heal Method **************
	//Overides other heal to heal the player to max health
    //@param - amt = amount to heal the player
	void heal(int amt)
	{
		health = health + amt;
		
		if(health > maxHP) //Checking for max health
		{
			health = maxHP;
		}
	}
}
