package org.jantor.image;

public class EntityImage extends GreenfootImage {

    public EntityImage(String path, String fileName ) {
        super(path + fileName);
    }
    public EntityImage(String fileName) {
        this("resources/image/", fileName);
    }

}
