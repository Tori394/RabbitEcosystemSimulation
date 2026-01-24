package Controller;

import Model.Entities.Carrot;
import Model.Entities.Entity;
import Model.Entities.EntityType;
import Model.Entities.Rabbit;

public class EntityFactory {
    public static Entity createEntity(EntityType type, int x, int y, int tileSize, int gen) {
        switch (type) {
            case RABBIT:
                return new Rabbit(x, y, tileSize, gen);
            case CARROT:
                return new Carrot(x, y, tileSize);
            default:
                throw new IllegalArgumentException("Nieznany typ: " + type);
        }
    }
}