package Controller;

import Model.*;

public class EntityFactory {
    public static Entity createEntity(EntityType type, int x, int y, int tileSize) {
        switch (type) {
            case RABBIT:
                return new Rabbit(x, y, tileSize);
            case CARROT:
                return new Carrot(x, y, tileSize);
            default:
                throw new IllegalArgumentException("Nieznany typ: " + type);
        }
    }
}