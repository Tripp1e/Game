package org.jantor.level.element.entity;

import greenfoot.Actor;
import org.jantor.image.EntityImage;
import org.jantor.image.GifImage;
import org.jantor.image.GreenfootImage;
import org.jantor.level.element.Element;
import org.jantor.level.element.entity.movement.Movement;

public abstract class Entity extends Element {
    public final GifImage walking;
    public final EntityImage jumping;
    public final EntityImage crouching;

    public final GifImage mirroredWalking;
    public final GreenfootImage mirroredJumping;

    Movement movement;
    final public int speed;

    public Entity(Movement movement, GifImage walkImg, GifImage walkImgMirrored, EntityImage jumpImg, EntityImage crouchImg, int speed) {
        super(crouchImg);
        this.walking = walkImg;
        this.jumping = jumpImg;
        this.crouching = crouchImg;

        mirroredWalking = walkImgMirrored;
        mirroredJumping = jumping.getMirroredHorizontally();

        this.speed = speed;
        this.movement = movement;
    }
    public Entity(Movement movement, GifImage walkImg, GifImage walkImgMirrored, EntityImage jumpImg, EntityImage crouchImg) {
        this(movement, walkImg, walkImgMirrored, jumpImg, crouchImg, 5);
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