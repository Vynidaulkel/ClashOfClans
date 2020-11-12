package com.game.model.Characters;

import com.game.model.*;
import com.game.model.Handles.HandlerGameObjects;

import java.awt.*;

public class ContactWarrior extends Warrior{

    public ContactWarrior(int x, int y, String imgPath, Team team, GameBoard gameBoard, HandlerGameObjects handlerGameObjects) {
        super(x, y, 40, 40, ID.BARBARIAN, team, gameBoard, handlerGameObjects);
        range = 1;
        strokePerTime = 2;
        setImage(Tools.getIcon.apply(imgPath)
                .getScaledInstance(40,40, Image.SCALE_SMOOTH));
    }


    @Override
    public void render(Graphics g) {
        super.render(g);
        super.displayHealthBar(g);
    }
    
    @Override
    public void upgrade() {
        System.out.println("Incrementando ataque a 10000");
    }
    
    @Override
    public void tick() {
        if(framesTimer == frames){

            if(isSomeoneInRange())attack();
            else move();

            framesTimer = 0;
        }else{
            framesTimer++;
        }
    }

    @Override
    public void move() {
        int tries = 8;

        while (tries-- >0) {
            int x = getMovement();
            int y = getMovement();
            nextMove = new Point(x + location.x, y + location.y);

            if (!gameBoard.isPositionOccupied(nextMove)) {
                gameBoard.moveCharacter(getLocation(), nextMove);
                setLocation(nextMove);
                return;
            }
        }
    }


    @Override
    public void hit(int damage) { // la razon de esta redundancia de metodos es que si se quiere poner recistencia de ataque por ejemplo, eso se har[ia aqui en hit
        reduceHealth(damage);
    }

    @Override
    public void die() {
        gameBoard.removeCharacter(this);
        handlerGameObjects.removeObject(this);
    }

    @Override
    public Point heuristic() {
        return null;
    }
}
