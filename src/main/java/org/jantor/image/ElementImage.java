package org.jantor.image;

public class ElementImage extends GreenfootImage {
    public ElementImage(String path, String fileName ) {
        super(path + fileName);
    }
    public ElementImage(String fileName) {
        this("resources/image/", fileName);
    }
}
