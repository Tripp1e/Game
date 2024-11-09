package org.jantor.level.element.entity;

import greenfoot.Actor;
import org.jantor.image.EntityImage;
import org.jantor.image.GreenfootImage;
import org.jantor.level.element.Element;
import org.jantor.level.element.entity.movement.Movement;

public abstract class Entity extends Element {
    public final EntityImage walking;
    public final EntityImage jumping;
    public final EntityImage crouching;

    public final GreenfootImage mirroredWalking;
    public final GreenfootImage mirroredJumping;

    Movement movement;
    final public int speed;

    public Entity(String initalImgPath, Movement movement, String walkImgPath, String jumpImgPath, String crouchImgPath, int speed) {
        super(initalImgPath);
        this.walking = new EntityImage(walkImgPath);
        this.jumping = new EntityImage(jumpImgPath);
        this.crouching = new EntityImage(crouchImgPath);

        mirroredWalking = walking.getMirroredHorizontally();
        mirroredJumping = jumping.getMirroredHorizontally();

        this.speed = speed;
        this.movement = movement;
    }
    public Entity(String initialImgPath, Movement movement, String walkImgPath, String jumpImgPath, String crouchImgPath) {
        this(initialImgPath, movement, walkImgPath, jumpImgPath, crouchImgPath, 5);
    }

    @Override
    public void act() {
        movement.act();
    }

    @Override
    public Actor getOneObjectAtOffset(int dx, int dy, Class<?> cls) {
        return super.getOneObjectAtOffset(dx, dy, cls);
    }

}
