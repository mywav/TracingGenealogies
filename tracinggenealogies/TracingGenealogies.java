
package tracinggenealogies;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Ryan.Newbold
 */
public class TracingGenealogies extends Application {
    
    @Override
    public void start(Stage primaryStage) throws Exception 
    {
        primaryStage.setTitle("Tracing Genealogies");
        Parent root = FXMLLoader.load(getClass().getResource("FXML.fxml"));
        
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
        
    }

    public static void main(String[] args) 
    {
        launch(args);
    }
    
}
