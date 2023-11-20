import java.util.Random;
import java.util.Scanner;

class Pokemon {
    String name;
    int health;
    int maxHealth;
    int attackPower;
    String[] attacks;

    Pokemon(String name, int maxHealth, int attackPower, String[] attacks) {
        this.name = name;
        this.maxHealth = maxHealth;
        this.health = maxHealth;
        this.attackPower = attackPower;
        this.attacks = attacks;
    }

    void attack(Pokemon opponent, int attackIndex) {
        int damage = new Random().nextInt(attackPower) + 1;
        opponent.health -= damage;
        System.out.println(name + " used " + attacks[attackIndex] + " and dealt " + damage + " damage to " + opponent.name + "!");
    }

    void displayStats() {
        System.out.println(name + " - Health: " + health + "/" + maxHealth);
    }
}

public class PokemonBattle {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the Pokémon Battle Simulator!");

        Pokemon playerPokemon = choosePokemon("Player");
        Pokemon computerPokemon = chooseRandomPokemon("Computer");

        System.out.println("\nPlayer chose " + playerPokemon.name + "!");
        System.out.println("Computer chose " + computerPokemon.name + "!\n");

        while (playerPokemon.health > 0 && computerPokemon.health > 0) {
            displayBattleStatus(playerPokemon, computerPokemon);

            int playerMove = chooseAttack(playerPokemon);
            int computerMove = chooseRandomAttack(computerPokemon);

            playerPokemon.attack(computerPokemon, playerMove);
            if (computerPokemon.health <= 0) {
                displayBattleStatus(playerPokemon, computerPokemon);
                System.out.println("Computer's " + computerPokemon.name + " fainted. You win!");
                break;
            }

            computerPokemon.attack(playerPokemon, computerMove);
            if (playerPokemon.health <= 0) {
                displayBattleStatus(playerPokemon, computerPokemon);
                System.out.println("Your " + playerPokemon.name + " fainted. You lose!");
                break;
            }
        }

        scanner.close();
    }

    private static Pokemon choosePokemon(String owner) {
        System.out.println(owner + ", choose your Pokémon:");

        Pokemon charmander = new Pokemon("Charmander", 100, 20, new String[]{"Ember", "Scratch"});
        Pokemon bulbasaur = new Pokemon("Bulbasaur", 120, 18, new String[]{"Vine Whip", "Tackle"});
        Pokemon squirtle = new Pokemon("Squirtle", 110, 22, new String[]{"Water Gun", "Bite"});

        System.out.println("1. " + charmander.name);
        System.out.println("2. " + bulbasaur.name);
        System.out.println("3. " + squirtle.name);

        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                return charmander;
            case 2:
                return bulbasaur;
            case 3:
                return squirtle;
            default:
                System.out.println("Invalid choice. Defaulting to Charmander.");
                return charmander;
        }
    }

    private static Pokemon chooseRandomPokemon(String owner) {
        Random random = new Random();
        int choice = random.nextInt(3) + 1;

        switch (choice) {
            case 1:
                return new Pokemon("Charmander", 100, 20, new String[]{"Ember", "Scratch"});
            case 2:
                return new Pokemon("Bulbasaur", 120, 18, new String[]{"Vine Whip", "Tackle"});
            case 3:
                return new Pokemon("Squirtle", 110, 22, new String[]{"Water Gun", "Bite"});
            default:
                System.out.println("Error in choosing computer's Pokémon. Defaulting to Charmander.");
                return new Pokemon("Charmander", 100, 20, new String[]{"Ember", "Scratch"});
        }
    }

    private static int chooseAttack(Pokemon pokemon) {
        System.out.println("Choose your attack:");

        for (int i = 0; i < pokemon.attacks.length; i++) {
            System.out.println((i + 1) + ". " + pokemon.attacks[i]);
        }

        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();

        if (choice < 1 || choice > pokemon.attacks.length) {
            System.out.println("Invalid choice. Defaulting to Attack 1.");
            return 0;
        }

        return choice - 1;
    }

    private static int chooseRandomAttack(Pokemon pokemon) {
        Random random = new Random();
        return random.nextInt(pokemon.attacks.length);
    }

    private static void displayBattleStatus(Pokemon player, Pokemon computer) {
        System.out.println("\n--- Battle Status ---");
        player.displayStats();
        computer.displayStats();
        System.out.println("---------------------\n");
    }
}
