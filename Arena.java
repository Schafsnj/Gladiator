import java.util.Random;
import java.util.Scanner;

//Project 3
//Jayden Schafsnitz
//3/30/2021
//Arena Class - Sets up the game and runs the UI

public class Arena {
	Queue<Creature> north;
	Queue<Creature> east;
	Queue<Creature> south;
	Queue<Creature> west;
	Player player;
	
	int count;
	
	//*********** Arena Constructor **************
	public Arena()
	{
		north = new Queue<Creature>();
		east = new Queue<Creature>();
		south = new Queue<Creature>();
		west = new Queue<Creature>();
		player= new Player(100, 200);
		count = 0;
		
	}
	
	//*********** Game Loop Method **************
    //Runs the game loop
	public void gameLoop()
	{
		System.out.println("****** New Round *******");
		
		while(!(isGameOver()))
			{
				createMonster();
				playersTurn();
				monstersTurn();
				count++;
			}
		System.out.println("You surevived " + count + " rounds!");
	}
	
	//*********** isGameOver Method **************
	//Checks if the game is over
    //@return - Returns true if the game is over
	public boolean isGameOver()
	{
		if(player.isDead() ||  isAllEmpty() && count != 0) 
		{
			return true;
		}
		else
		{
			return false;
		}

	}
	
	//*********** createMonster Method **************
	//Creates a monster object and adds it to the queue
	public void createMonster()
	{
		Random rand = new Random();
		
		int health = 0;
		int strength = 0;
		int select;
		
		while(strength <= 0 && health <= 0) //If for some reason a negative value is set run again
		{
			strength = rand.nextInt(101); //Random strength from 0 to 100
			health = rand.nextInt(201); //Random health from 0 to 200
		}

		

		Creature temp = new Creature(strength, health);

		//Adding a monster by default if the turn count is 0 "Round 1"
		if(count == 0)
		{
			//20% chance of getting a spell
			if(hasSpell())
			{
				temp.giveSpell(giveSpell());
			}
			
			north.enqueue(temp); //Adding the creature to the north queue
			return; //Returning out of the method now so another creature does not get made
		}
		
		//Creating the 75% chance of getting a monster
		if(rand.nextInt(4) < 3) 
		{
			//20% chance of getting a spell
			if(hasSpell())
			{
				temp.giveSpell(giveSpell());
			}
			
			//using random number to assign monster
			select = rand.nextInt(4) + 1; //Random number between 1 - 4
			
			//Assigning values
			if(select == 1)
			{
				System.out.println("\nA new monster has been made in the east!");
				east.enqueue(new Creature(strength, health));
			}
			else if(select == 2)
			{
				System.out.println("\nA new monster has been made in the south!");
				south.enqueue(new Creature(strength, health));
			}
			else if(select == 3)
			{
				System.out.println("\nA new monster has been made in the west!");
				west.enqueue(new Creature(strength, health));
			}
			else if(select == 4)
			{
				System.out.println("\nA new monster has been made in the north!");
				north.enqueue(new Creature(strength, health));
			}
			
		}
	}
	
	//*********** PlayersTurn Method **************
	//Runs the players turn
	public void playersTurn()
	{
		System.out.println("\n ****** Players Turn *******");
		System.out.println("You have " + player.health + " health.");

		if(!(north.isEmpty())) //If there is at least 1 creature north
		{
			System.out.println("There is A Creature to the North!");
			System.out.println(north.peek().toString());
		} 
		
		if(!(east.isEmpty())) //If there is at least 1 creature east 
		{
			System.out.println("There is A Creature to the East!");
			System.out.println(east.peek().toString());
		}
		
		if(!(south.isEmpty())) //If there is at least 1 creature south
		{
			System.out.println("There is A Creature to the South!");
			System.out.println(south.peek().toString());
		}
		
		if(!(west.isEmpty())) //If there is at least 1 creature west
		{
			System.out.println("There is A Creature to the West!");
			System.out.println(west.peek().toString());
		}
		
		//Get the direction of the players attack
		System.out.println("Which Direction Would You Like to Attack? (N,E,S,W)");
		Scanner sc = new Scanner(System.in);
		String userIn = null;
		SpellType spell = null;
		Boolean spellCast = false;
		int damage = 0;
		userIn = sc.next();
		
		if(player.spells.size() == 0 || player.spells == null) //If no spells
		{
			System.out.println("You do not have any spells so you normal attack");
		}
		else if(player.spells.size() != 0) //If they have spells
		{
			System.out.println("You have spells " + player.spells + " would you like to cast a spell?(Y,N)");
			String answer = sc.next();
			
			//Checking there answer and getting answer if they want to use a spell
			if(answer.equalsIgnoreCase("Y"))
			{
				spell = getDesiredSpell();
				spellCast = true;
			}
		}
		
		//Now doing the actual attack
		if(spellCast == false)
		{
			//Switch statement to attack the direction
			switch(userIn.toUpperCase())
			{
				case "N":
					damage = player.attack();
					System.out.println("You did " + damage + " damage.");
					north.peek().hurt(damage); //Doing the damage to the creature
					
					//Checking to see if the monster is dead
					if(north.peek().isDead())
					{
						System.out.println("The Monster is Dead! You get 100 XP!");
						
						//Removing the element and setting xp
						north.dequeue();
						player.addXP(100);
					}
					break;
					
				case "E":
					damage = player.attack();
					System.out.println("You did " + damage + " damage.");
					east.peek().hurt(damage); //Doing the damage to the creature
					
					//Checking to see if the monster is dead
					if(east.peek().isDead())
					{
						System.out.println("The Monster is Dead! You get 100 XP!");
						
						//Removing the element and setting xp
						east.dequeue();
						player.addXP(100);
					}
					break;
					
				case "S":
					damage = player.attack();
					System.out.println("You did " + damage + " damage.");
					south.peek().hurt(damage); //Doing the damage to the creature
					
					//Checking to see if the monster is dead
					if(south.peek().isDead())
					{
						System.out.println("The Monster is Dead! You get 100 XP!");
						
						//Removing the element and setting xp
						south.dequeue();
						player.addXP(100);
					}
					break;
					
				case "W":
					damage = player.attack();
					System.out.println("You did " + damage + " damage.");
					west.peek().hurt(damage); //Doing the damage to the creature
					
					//Checking to see if the monster is dead
					if(west.peek().isDead())
					{
						System.out.println("The Monster is Dead! You get 100 XP!");
						
						//Removing the element and setting xp
						west.dequeue();
						player.addXP(100);
					}
					break;		
			}
		}
		else if(spellCast == true)
		{
			//Checking for heal
			if(spell == SpellType.Heal)
			{
				System.out.println("You have healed for 50 health!");
				player.heal(50);
				player.removeSpell(SpellType.Heal);
			
			}
			else
			{
				switch(userIn.toUpperCase()) //Using switch statement to do what user decided
				{
					case "N":
						//checking the spelltype
						if(spell == SpellType.Fire)
						{
							System.out.println("You set the creature on fire!");
							north.peek().setOnFire();
							player.removeSpell(SpellType.Fire);
							

						}
						else if(spell == SpellType.Frost)
						{
							System.out.println("You froze the creature!");
							north.peek().freeze();
							player.removeSpell(SpellType.Frost);
						}
						else if(spell == SpellType.Lightning)
						{
							System.out.println("You use the lightning spell to do 50 damage!");
							player.removeSpell(SpellType.Lightning);
							north.peek().hurt(50);
							
							//Checking if the creature is now dead
							if(north.peek().isDead())
							{
								System.out.println("The Monster is Dead! You get 100 XP!");
								
								//Removing the element and setting xp
								north.dequeue();
								player.addXP(100);
							}
						}
						break;
						
					case "E":
						//checking the spelltype
						if(spell == SpellType.Fire)
						{
							System.out.println("You set the creature on fire!");
							east.peek().setOnFire();
							player.removeSpell(SpellType.Fire);
							
						}
						else if(spell == SpellType.Frost)
						{
							System.out.println("You froze the creature!");
							east.peek().freeze();
							player.removeSpell(SpellType.Frost);
						}
						else if(spell == SpellType.Lightning)
						{
							System.out.println("You use the lightning spell to do 50 damage!");
							player.removeSpell(SpellType.Lightning);
							east.peek().hurt(50);
							player.removeSpell(SpellType.Lightning);
							
							//Checking if the creature is now dead
							if(east.peek().isDead())
							{
								System.out.println("The Monster is Dead! You get 100 XP!");
								
								//Removing the element and setting xp
								east.dequeue();
								player.addXP(100);
							}
						}
						break;
						
					case "S":
						//checking the spelltype
						if(spell == SpellType.Fire)
						{
							System.out.println("You set the creature on fire!");
							south.peek().setOnFire();
							player.removeSpell(SpellType.Fire);
							
						}
						else if(spell == SpellType.Frost)
						{
							System.out.println("You froze the creature!");
							south.peek().freeze();
							player.removeSpell(SpellType.Frost);
						}
						else if(spell == SpellType.Lightning)
						{
							System.out.println("You use the lightning spell to do 50 damage!");
							player.removeSpell(SpellType.Lightning);
							south.peek().hurt(50);
							player.removeSpell(SpellType.Lightning);
							
							//Checking if the creature is now dead
							if(south.peek().isDead())
							{
								System.out.println("The Monster is Dead! You get 100 XP!");
								
								//Removing the element and setting xp
								south.dequeue();
								player.addXP(100);
							}
						}
						break;
						
					case "W":
						//checking the spelltype
						if(spell == SpellType.Fire)
						{
							System.out.println("You set the creature on fire!");
							west.peek().setOnFire();
							player.removeSpell(SpellType.Fire);
							
						}
						else if(spell == SpellType.Frost)
						{
							System.out.println("You froze the creature!");
							west.peek().freeze();
							player.removeSpell(SpellType.Frost);
						}
						else if(spell == SpellType.Lightning)
						{
							System.out.println("You use the lightning spell to do 50 damage!");
							player.removeSpell(SpellType.Lightning);
							west.peek().hurt(50);
							player.removeSpell(SpellType.Lightning);
							
							//Checking if the creature is now dead
							if(west.peek().isDead())
							{
								System.out.println("The Monster is Dead! You get 100 XP!");
								
								//Removing the element and setting xp
								west.dequeue();
								player.addXP(100);
							}
						}
						break;
				}
			}
		}
		
		//Decrementing the frozen timer if player is frozen
		if(player.isFrozen())
		{
			System.out.println("You are frozen for " + player.getFrozenTime() + " more turns!");
			player.decFreezeTimer();
		}
		
		//Checking if player is on fire then doing that damage
		if(player.isOnFire())
		{
			System.out.println("You took 25 fire damage! You are on fire for " + player.getFireTime() + " more turns!");
			player.hurt(25);
			player.decFireTimer();
		}
		
		//Now checking if the player can level up
		if(player.canLevelUP())
		{
			System.out.println("You have leveled up! Your strength is increased by 5 and your health by 10!");
			SpellType given = giveSpell();
			player.levelUp(given);
			
			System.out.println("You have been given the" + given + " spell!");
		}
		
		System.out.println("****** Turn Over! *******");
		
	}
	
	//*********** monstersTurn Method **************
	//Runs the monsters turn
	public void monstersTurn()
	{
		System.out.println("\n ****** Monsters Turn ******");
		
		//Sending to attack functions for each location
		if(!(north.isEmpty())) //north attack
		{
			System.out.println("The monster from the north is attacking!");
			northTurn();
		}
		
		if(!(east.isEmpty())) //east attack
		{
			System.out.println("The monster from the east is attacking!");
			eastTurn();
		}
		
		if(!(south.isEmpty())) //south attack
		{
			System.out.println("The monster from the south is attacking!");
			southTurn();
		}
		
		if(!(west.isEmpty())) //west attack
		{
			System.out.println("The monster from the west is attacking!");
			westTurn();
		}
	}
	
	//*********** northTurn Method **************
	//Attacks from the creature in the north
	public void northTurn()
	{
		//Checking if the creature has a spell
		if(north.peek().spells.size() != 0 && north.peek().spells != null)
		{
			SpellType spell;
			spell = north.peek().spells.get(0);

			
			//Casting the first spell
			switch(spell)
			{
				case Fire:
					System.out.println("The monster sets you on fire!");
					player.setOnFire();
					north.peek().removeSpell(spell);
					break; 
					
				case Frost:
					System.out.println("The monster freezes you");
					player.freeze();
					north.peek().removeSpell(spell);
					break;
					
				case Heal:
					System.out.println("The monster heals for 50");
					north.peek().heal(50);
					north.peek().removeSpell(spell);
					break;
					
				case Lightning:
					System.out.println("The monster uses a lightning spell");
					player.hurt(50);
					north.peek().removeSpell(spell);
					break;
			}
		}
		else
		{
			//attacking the player normally
			int damage = north.peek().attack();
			System.out.println("The monster attacks you for " + damage + " damage!");
			player.hurt(damage);
		}
		
		//Decrementing the frozen timer if they are frozen
		if(north.peek().isFrozen())
		{
			System.out.println("Monster has " + north.peek().getFrozenTime() + "round left frozen!");
			north.peek().decFreezeTimer();
		}
		
		//Decrementing the fire timer
		if(north.peek().isOnFire())
		{
			System.out.println("Monster has " + north.peek().getFireTime() + "round left on fire!");
			north.peek().hurt(25);
			north.peek().decFireTimer();
			
			if(north.peek().isDead())
			{
				System.out.println("The Monster is Dead! You get 100 XP!");
				
				//Removing the element and setting xp
				north.dequeue();
				player.addXP(100);
			}
		}
	}
	
	//*********** eastTurn Method **************
	//Attacks from the creature in the east
	public void eastTurn()
	{
		//Checking if the creature has a spell
		if(east.peek().spells.size() != 0 && east.peek().spells != null)
		{
			SpellType spell;
			spell = east.peek().spells.get(0);

			
			//Casting the first spell
			switch(spell)
			{
				case Fire:
					System.out.println("The monster sets you on fire!");
					player.setOnFire();
					east.peek().removeSpell(spell);
					break;
					
				case Frost:
					System.out.println("The monster freezes you!");
					player.freeze();
					east.peek().removeSpell(spell);
					break;
					
				case Heal:
					System.out.println("The monster heals for 50!");
					east.peek().heal(50);
					east.peek().removeSpell(spell);
					break;
					
				case Lightning:
					System.out.println("The monster uses a lightning spell on you!");
					player.hurt(50);
					east.peek().removeSpell(spell);
					break;
			}
		}
		else
		{
			//attacking the player normally
			int damage = east.peek().attack();
			System.out.println("The monster attacks you for " + damage + " damage!");
			player.hurt(damage);
		}
		
		//Decrementing the frozen timer if they are frozen
		if(east.peek().isFrozen())
		{
			System.out.println("Monster has " + east.peek().getFrozenTime() + "round left frozen!");
			east.peek().decFreezeTimer();
		}
		
		//Decrementing the fire timer
		if(east.peek().isOnFire())
		{
			System.out.println("Monster has " + east.peek().getFireTime() + "round left on fire!");
			east.peek().hurt(25);
			east.peek().decFireTimer();	
			
			if(east.peek().isDead())
			{
				System.out.println("The Monster is Dead! You get 100 XP!");
				
				//Removing the element and setting xp
				east.dequeue();
				player.addXP(100);
			}
		}
	}
	
	//*********** southTurn Method **************
	//Attacks from the creature in the south
	public void southTurn()
	{
		//Checking if the creature has a spell
		if(south.peek().spells.size() != 0 && south.peek().spells != null)
		{
			SpellType spell;
			spell = south.peek().spells.get(0);

			
			//Casting the first spell
			switch(spell)
			{
				case Fire:
					System.out.println("The monster sets you on fire!");
					player.setOnFire();
					south.peek().removeSpell(spell);
					break;
					
				case Frost:
					System.out.println("The monster freezes you!");
					player.freeze();
					south.peek().removeSpell(spell);
					break;
					
				case Heal:
					System.out.println("The monster heals for 50!");
					south.peek().heal(50);
					south.peek().removeSpell(spell);
					break;
					
				case Lightning:
					System.out.println("The monster uses a lightning spell on you!");
					player.hurt(50);
					south.peek().removeSpell(spell);
					break;
			}
		}
		else
		{
			//attacking the player normally
			int damage = south.peek().attack();
			System.out.println("The monster attacks you for " + damage + " damage!");
			player.hurt(damage);
		}
		
		//Decrementing the frozen timer if they are frozen
		if(south.peek().isFrozen())
		{
			System.out.println("Monster has " + south.peek().getFrozenTime() + "round left frozen!");
			south.peek().decFreezeTimer();
		}
		
		//Decrementing the fire timer
		if(south.peek().isOnFire())
		{
			System.out.println("Monster has " + south.peek().getFireTime() + "round left on fire!");
			south.peek().hurt(25);
			south.peek().decFireTimer();
			
			if(south.peek().isDead())
			{
				System.out.println("The Monster is Dead! You get 100 XP!");
				
				//Removing the element and setting xp
				south.dequeue();
				player.addXP(100);
			}
		}
	}
	
	//*********** westTurn Method **************
	//Attacks from the creature in the west
	public void westTurn()
	{
		//Checking if the creature has a spell
		if(west.peek().spells.size() != 0 && west.peek().spells != null)
		{
			SpellType spell;
			spell = west.peek().spells.get(0);

			
			//Casting the first spell
			switch(spell)
			{
				case Fire:
					System.out.println("The monster sets you on fire!");
					player.setOnFire();
					west.peek().removeSpell(spell);
					break;
					
				case Frost:
					System.out.println("The monster freezes you!");
					player.freeze();
					west.peek().removeSpell(spell);
					break;
					
				case Heal:
					System.out.println("The monster heals for 50!");
					west.peek().heal(50);
					west.peek().removeSpell(spell);
					break;
					
				case Lightning:
					System.out.println("The monster uses lightning spell on you!");
					player.hurt(50);
					west.peek().removeSpell(spell);
					break;
			}
		}
		else
		{
			//attacking the player normally
			int damage = west.peek().attack();
			System.out.println("The monster attacks you for " + damage + " damage!");
			player.hurt(damage);
		}
		
		//Decrementing the frozen timer if they are frozen
		if(west.peek().isFrozen())
		{
			System.out.println("Monster has " + west.peek().getFrozenTime() + "round left frozen!");
			west.peek().decFreezeTimer();
		}
		
		//Decrementing the fire timer
		if(west.peek().isOnFire())
		{
			System.out.println("Monster has " + west.peek().getFireTime() + "round left on fire!");
			west.peek().hurt(25);
			west.peek().decFireTimer();	
			
			if(west.peek().isDead())
			{
				System.out.println("The Monster is Dead! You get 100 XP!");
				
				//Removing the element and setting xp
				west.dequeue();
				player.addXP(100);
			}
		}
	}
	
	//*********** isAllEmpty Method **************
	//Checks if "all" the Queues are empty
	//@return - Returns true if all the queues are empty
	public boolean isAllEmpty()
	{
		if(north.isEmpty() && east.isEmpty() && south.isEmpty() && west.isEmpty())
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	//*********** hasSpell Method **************
	//Checks to see if creature will get a spell
	//@return - Returns true if the creature will get a spell
	public boolean hasSpell()
	{
		Random rand = new Random();
		
		//20% chance this will happen
		if(rand.nextInt(5) == 0) //Random number between 0 - 4 20% chance is will be 0
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	//*********** giveSpell Method **************
	//Randomly assigns a spell
	//@return - Returns a random spell
	public SpellType giveSpell()
	{
		Random rand = new Random();
		
		//Random spell from the four choices
		int number;
		number = rand.nextInt(4) + 1; //Random number between 1 and 4
		
		//Using switch statement to assign and return spells
		switch(number)
		{
			case 1:
				return SpellType.Fire;
			
			case 2:
				return SpellType.Frost;
				
			case 3:
				return SpellType.Heal;
				
			case 4:
				return SpellType.Lightning;
				
			default:
				return SpellType.Lightning;
				
		}
	}
	
	//*********** getDesireSpell Method **************
	//Gets the spell the player would like to cast
	//@return - Returns a the spell the player would like to cast
	public SpellType getDesiredSpell()
	{
		System.out.println("You have: " + player.spells);
		
		//If you only have one spell
		if(player.spells.size() == 1)
		{
			System.out.println("You only have one spell so you use " + player.spells);
			return player.spells.get(0); //Returning the only spell
		}
		else
		{
			System.out.println("Make a Selection!");
			
			for(int i = 0; i < player.spells.size(); i++) //Looping through the spells so correct prints for different ammount of spells
			{
				System.out.print((i+1) + ". " + player.spells.get(i) + ": ");
			}
			System.out.println();
			
			//Scanning in the selection 
			Scanner sc = new Scanner(System.in);
			int selection = sc.nextInt();
			SpellType sel = player.spells.get(selection - 1);

			
			return sel;
		}	
	}
}
