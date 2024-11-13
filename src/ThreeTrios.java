import model.PlayingCard;
import model.TTModel;
import model.ThreeTriosModel;
import view.TTGuiView;
import view.ThreeTriosFrame;
import view.ThreeTriosView;

/**
 * The placeholder class for our project, as described by HW6.
 */
public final class ThreeTrios {
  public static void main(String[] args) {
    ThreeTriosModel<PlayingCard> model = new TTModel();
    ThreeTriosFrame view = new TTGuiView(model);
    view.makeVisible();
  }
}
