import javafx.beans.binding.Bindings;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.util.Duration;
import static javafx.application.Application.STYLESHEET_MODENA;

/**
 * Created by Filip on 17.01.2017.
 */

public class Person extends Pane{
    //maarame tykk pildist, milline kasutame
    ImageView imageView;
    int count = 3;
    int columns = 3;
    int offsetX = 0;
    int offsetY = 0;
    int width = 32;
    int height = 32;

    private LongProperty score = new SimpleLongProperty(0);
    Rectangle removeRect = null;
    Sprite animation;
    public Person(ImageView imageView){
        this.imageView = imageView;
        this.imageView.setViewport(new Rectangle2D(offsetX,offsetY,width,height));
        animation = new Sprite(imageView,Duration.millis(200),count,columns,offsetX,offsetY,width,height);
        getChildren().addAll(imageView);
    }
    //nihutame personaazi X telje jargi
    public void moveX(int x){
        boolean right = x>0?true:false;
        for(int i = 0; i < Math.abs(x); i++) {
            if (right) this.setTranslateX(this.getTranslateX() + 1);
            else this.setTranslateX(this.getTranslateX() - 1);
            isEatBonuse();
          //  Main.movePlayerBy(moveX, moveY);
        }
    }
    //nihutame personaazi Y telje jargi
    public void moveY(int y) {
        boolean down = y > 0 ? true : false;
        for (int i = 0; i < Math.abs(y); i++) {
            if (down) this.setTranslateY(this.getTranslateY() + 1);
            else this.setTranslateY(this.getTranslateY() - 1);
            isEatBonuse();
        }
    }
  //kontrollime kas ta soi oma suua ja arvutame score
    public void isEatBonuse(){
        Main.bonuses.forEach((rect) -> {
            if (this.getBoundsInParent().intersects(rect.getBoundsInParent())) {
                removeRect = rect;
                score.set(score.get() + 1);
                System.out.println("Score " + score);

            }

        });
        Main.bonuses.remove(removeRect);
        Main.root.getChildren().remove(removeRect);
        
        //prindime score
        Text scoretekst = new Text();
        Main.root.getChildren().addAll(scoretekst);
        scoretekst.setY(47);
        scoretekst.setX(100);
        scoretekst.setFont(Font.font(STYLESHEET_MODENA, FontWeight.BOLD, 25));
        scoretekst.setFill(Color.RED);
        scoretekst.textProperty().bind(Bindings.createStringBinding(() -> ("Score: " + score.get()), score));
    }
}
