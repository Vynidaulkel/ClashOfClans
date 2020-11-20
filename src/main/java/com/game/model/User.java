package com.game.model;

import java.io.Serializable;
import java.util.ArrayList;

public class User implements Serializable {

    private String name;
    private String password;
    private int level;
    private int troops;
    private ArrayList<Warrior> characters; // el nivel de sus caracteres
    private ArrayList<Warrior> toPlay; // con los que va a jugar
    private ArrayList<Warrior> allCharacters;

    // partida/veremos


    /**
     * <h1>Nuevo usuario</h1>
     * */
    public User(String name, String password) {
        this.name = name;
        this.password = password;
        this.troops = 5;
        level = 1;
        characters  = new ArrayList<>();
        allCharacters = new ArrayList<>();
    }


    //setters/getters
    public ArrayList<Warrior> getCharacters() {
        return characters;
    }

    public void setCharacters(ArrayList<Warrior> characters) {
        this.characters = characters;
    }

    public ArrayList<Warrior> getAllCharacters() {
        return allCharacters;
    }

    public void setAllCharacters(ArrayList<Warrior> allCharacters) {
        this.allCharacters = allCharacters;
        this.characters.clear();
        allCharacters.forEach(c -> this.characters.add(c.clone(c)));
    }

    public void addCreatedCharacter(Warrior warrior){
        this.allCharacters.add(warrior);
        this.characters.add(warrior.clone(warrior));
    }
    
    // Mae la verdad tengo mucha pereza xd
    public String getName(){
        return name;
    }
    
    public int getLevel(){
        return level;
    }

    public void levelUp() {
        this.level++;
    }

    public int getTroops() {
        return troops + (level-1) * 3;
    }
    
    public boolean login(String pass){
        return password.equals(pass);
    }

    public ArrayList<Warrior> getToPlay() {
        return toPlay;
    }

    public void setToPlay(ArrayList<Warrior> toPlay) {
        this.toPlay = toPlay;
    }

    public Warrior getWarriorByName(String name){
        for (Warrior character : characters) {
            if (name.equals(character.getName())) {
                return character;
            }
        }

        return null;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append("User{" +
                "name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", level=" + level);

        allCharacters.forEach(w -> s.append("\n").append(w));

         return s.toString();
    }
}
