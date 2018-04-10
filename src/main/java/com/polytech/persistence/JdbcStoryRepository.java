package com.polytech.persistence;

import com.polytech.services.Story;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class JdbcStoryRepository implements StoryRepository {

    private Connection connection;

    public JdbcStoryRepository(Connection connection) {
        this.connection = connection;
    }

    public void save(Story story){
        String query = "INSERT INTO STORY VALUES ('"+story.getContent()+"')";
        try {
            Statement statement = connection.createStatement();
            statement.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Story> findAll(){
        String query = "SELECT * from STORY";
        List<Story> stories = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while(resultSet.next()){
                stories.add(new Story(resultSet.getString("CONTENT")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return stories;
    }
}
