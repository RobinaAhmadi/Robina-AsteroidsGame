module Core {
    requires Common;
    requires Player;
    requires Bullet;
    requires Asteroids;
    requires Collision;
    requires Enemy;
    requires javafx.controls;

    opens dk.sdu.mmmi.cbse.core to javafx.graphics;
}
