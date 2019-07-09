package tracinggenealogies;

import java.io.BufferedReader;
import javafx.scene.control.Button;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javafx.stage.Window;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Ryan Newbold
 */
public class FXMLController implements Initializable 
{
    @FXML
    private Button openFile;
    
    @FXML
    private Button analyzeGenealogy;
    
    @FXML 
    private TextArea textArea;
    
    @FXML
    private TextArea textArea2;
    
    @FXML
    private TextArea textArea3;
    
    @FXML
    private TextField textField1;
    
    @FXML
    private TextField textField2;
    
    StringBuilder stringBuilder = new StringBuilder();
    LinkedQueue linkedQueue = new LinkedQueue();
    File selectedFile = null;
    InputStream inputStream;
    Reader reader;
    Reader buffer;
    Scanner sc;

    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
    
    }    
    
    @FXML
    protected void handleSubmitButtonAction(ActionEvent event) throws IOException, Exception
    {
        // Use the default Charset
        Charset charSet = Charset.defaultCharset();
        Window stage = new Stage();
        FileChooser fileChooser = new FileChooser();
        Scanner sc = null;
        Path path = null;
        
        
        if (event.getSource() == openFile)
        {
            textArea.clear();
            
            // Allow the user to open a file
            fileChooser.setTitle("Choose a text file");
            fileChooser.getExtensionFilters().addAll(new ExtensionFilter("Text Files", "*.txt"));
            selectedFile = fileChooser.showOpenDialog(stage);
            
            // Record the canonical path of the file the user selected
            path = Paths.get(selectedFile.getCanonicalPath());
            
            try                    
            {
                // Create the input stream from the file the user selected
                inputStream = new FileInputStream(selectedFile);                
                reader = new InputStreamReader(inputStream, charSet);
                buffer = new BufferedReader(reader);
                processChars(buffer);
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        else if (event.getSource() == analyzeGenealogy)            
        {
            textArea3.clear();
            LinkedStack linkedStack = new LinkedStack();
            LinkedQueue linkedQueue = new LinkedQueue();
                       
            try 
            {
                // Create the input stream from the selected file
                inputStream = new FileInputStream(selectedFile);                
                reader = new InputStreamReader(inputStream, charSet);
                BufferedReader bufferedReader = new BufferedReader(reader);
                
                // Scan from the bufferedReader
                sc = new Scanner(bufferedReader);
                int intCounter = Integer.parseInt(sc.next());
                String parentName;
                int numberOfKids = 0;
                String[] arrayOfKids;
                
                for (int i = 0; i < intCounter; i++)
                {  
                    // Get the parentName from the next line in the text file
                    parentName = sc.next();

                    // Create a variable to hold sc.next() temporarily
                    String temporarySc = sc.next();
                    
                    // If temporarySc is an int, then we know that the number of kids is the next thing to check
                    if (isInteger(temporarySc))
                        numberOfKids = Integer.parseInt(temporarySc);
                    arrayOfKids = new String[numberOfKids];
                    
                    for (int index = 0; index < numberOfKids; index++)
                    {
                       arrayOfKids[index] = sc.next();
                    }
                    
                    linkedStack.push(parentName, numberOfKids, arrayOfKids);               
                }
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
            
            String nameToSearch = textField1.getText();
            
//            if (isDescendantOf(linkedQueue, textField2.getText()))
//            {
//                
//            }
            if (nameToSearch != null)
            {                
                    while (nameToSearch != null)
                    {
                        linkedQueue.enqueue(nameToSearch);
                        nameToSearch = linkedStack.getParent(nameToSearch);                         
                    }
            }
        if (!linkedQueue.empty())
        textArea3.appendText(linkedQueue.dequeue() + " is a child of  ");
        
        String temporaryName = linkedQueue.dequeue();        
        boolean hasBeenFound = false;
        if (temporaryName.compareTo(textField2.getText()) == 0)
                {
                    hasBeenFound = true;
                }
        
        while (!linkedQueue.empty())
        {
            textArea3.appendText(temporaryName + ".\n " + temporaryName + " is a child of ");
            temporaryName = linkedQueue.dequeue();
            if (temporaryName.compareTo(textField2.getText()) == 0)
                hasBeenFound = true;
        }
        
         textArea3.appendText(temporaryName + ". ");
         if (hasBeenFound == true)
             textArea3.appendText("\nSo the answer is yes.");
         else
             textArea3.appendText("\nSo the answer is no.");         
        }       
    } 
    

//    public boolean isDescendantOf(LinkedQueue linkedQueue, String nameToSearch)
//        {
//            boolean isItTrue = false;
//            return isItTrue;            
//        }
//    
    private void processChars (Reader reader)
            throws IOException
    {
        int counter;
        
        while((counter = reader.read()) != -1)
        {
            char character = (char)counter;
            stringBuilder.append(character);
        }
        textArea.appendText(stringBuilder.toString());
    }

    public static boolean isInteger(String s) 
    {
      boolean isValidInteger = false;
      try
      {
        Integer.parseInt(s);
 
        isValidInteger = true;
      }
      catch (NumberFormatException ex)
      {
        
      } 
      return isValidInteger;
   }
}
