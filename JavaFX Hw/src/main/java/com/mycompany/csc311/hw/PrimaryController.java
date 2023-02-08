package com.mycompany.csc311.hw;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class PrimaryController {

    @FXML 
    private VBox vbox;
   
    @FXML
    private ListView listViewBooks;
    
    @FXML
    private TextField numBooks;
    
    @FXML
    public void handleCloseApp(){
        System.out.println("Menu closed");
        Stage stage = (Stage) vbox.getScene().getWindow();
        stage.close();
    }
    @FXML
    public void showNumBooks(){
        try{
            int bookCount = 0;
            String book;
            String tableName = "BookInfo";
            Statement stmt = openConnection().createStatement();
            ResultSet result = stmt.executeQuery("select * from " + tableName);
            
            while (result.next()){
                int ID = result.getInt("ID");
                String Title = result.getString("Title");
                double Price = result.getDouble("Price");
                book = String.format("%d %s %.2f\n", ID, Title, Price);
                ObservableList<String> items = listViewBooks.getItems();
                items.add(book);
                bookCount+=1;
            }
            numBooks.setText(String.format("%d", bookCount));
        }
        catch (SQLException except){
        }
        System.out.println("Showing current books in database");
        
    }
    
    @FXML    
    public void loadDataFromJson(){
        try{
           String delete = "DELETE FROM BookInfo";
           PreparedStatement preparedDeleteStatement = openConnection().prepareStatement(delete);
           preparedDeleteStatement.executeUpdate();
           String insert = "INSERT INTO BookInfo (Title, Price) VALUES (?, ?)";
           PreparedStatement preparedInsertStatement = openConnection().prepareStatement(insert);
           GsonBuilder builder = new GsonBuilder();
           Gson gson = builder.create();
           FileReader fr = null;
            try {
                fr = new FileReader("Books.json");
            } catch (FileNotFoundException ex) {
                System.out.println("File not found");
            }
           BookInformation[] books = gson.fromJson(fr, BookInformation[].class);
            for (BookInformation book : books) {
                preparedInsertStatement.setString(1,book.getTitle());
                preparedInsertStatement.setDouble(2, book.getPrice());
                int row = preparedInsertStatement.executeUpdate();
                if (row > 0) {
                    System.out.println("Row inserted");
                }
            }
       }
       catch(SQLException e){
       }
       System.out.println("Data loaded from JSON");
   }
   public static Connection openConnection(){
       Connection conn = null;
       try{
           String databaseURL = "jdbc:ucanaccess://.//Books.accdb";
           conn = DriverManager.getConnection(databaseURL);
       }
       catch (SQLException ex){
           System.out.println("Error occured");
       }
       return conn;
   }
    
}
