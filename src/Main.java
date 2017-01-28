import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Filip on 17.01.2017.
 */

public class Main extends Application {
    private HashMap<KeyCode, Boolean> keys = new HashMap<>();
    public static ArrayList<Rectangle> bonuses = new ArrayList<>();
    //pildi src http://www.gdunlimited.net/forums/topic/4360-resizing-vx-sprites/
    Image image = new Image(getClass().getResourceAsStream("ply4.png"));
    ImageView imageView = new ImageView(image);
    Person superplayer = new Person(imageView);
    static Pane root = new Pane();



    public void bonus(){
        int random = (int)Math.floor(Math.random()*100);
        int x = (int)Math.floor(Math.random()*600);
        int y = (int)Math.floor(Math.random()*600);
        if(random == 5){
            Rectangle rect = new Rectangle(18,18,Color.GREEN);
            rect.setX(x);
            rect.setY(y);
            bonuses.add(rect);
            root.getChildren().addAll(rect);
        }
    }
    //uuenda personaazi vaade juhul kui vajutatud nupp
    public void update() {
        int dx = 0, dy = 0;
        if (isPressed(KeyCode.UP)) {
            superplayer.animation.play();
            superplayer.animation.setOffsetY(96);
            superplayer.moveY(-2);
            dy -= 1;

        } else if (isPressed(KeyCode.DOWN)) {
            superplayer.animation.play();
            superplayer.animation.setOffsetY(0);
            superplayer.moveY(2);
            dy += 1;
        } else if (isPressed(KeyCode.RIGHT)) {
            superplayer.animation.play();
            superplayer.animation.setOffsetY(64);
            superplayer.moveX(2);
            dx += 1;
        } else if (isPressed(KeyCode.LEFT)) {
            superplayer.animation.play();
            superplayer.animation.setOffsetY(32);
            superplayer.moveX(-2);
            dx -= 1;
        }
        else{
            superplayer.animation.stop();
        }
        movePlayerBy(dx, dy);
    }
    public boolean isPressed(KeyCode key) {
        return keys.getOrDefault(key, false);
    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        root.setPrefSize(600,600);
        superplayer.relocate(15,30);
        root.getChildren().addAll(superplayer);

        Scene scene = new Scene(root);
        scene.setOnKeyPressed(event->keys.put(event.getCode(),true));
        scene.setOnKeyReleased(event-> {
            keys.put(event.getCode(), false);
        });
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                update();
                bonus();
            }
        };
        timer.start();
        primaryStage.setTitle("MinuMang");
        primaryStage.setScene(scene);
        primaryStage.show();

    }
    private void movePlayerBy(int dx, int dy) {
        if (dx == 0 && dy == 0) return;

        final double cx = superplayer.getBoundsInLocal().getWidth()  / 2;
        final double cy = superplayer.getBoundsInLocal().getHeight() / 2;

        double x = cx + superplayer.getLayoutX() + dx;
        double y = cy + superplayer.getLayoutY() + dy;



        movePlayerTo(x, y);
    }
    private void movePlayerTo(double x, double y) {
        final double cx = superplayer.getBoundsInLocal().getWidth() / 2;
        final double cy = superplayer.getBoundsInLocal().getHeight() / 2;


        if (x - cx >= 0 &&
                x + cx <= 600 &&
                y - cy >= 0 &&
                y + cy <= 600) {
            superplayer.relocate(x - cx, y - cy);
        }
    }
    public static void main(String[] args) {
        launch(args);
    }


}
