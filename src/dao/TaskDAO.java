package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import models.Task;

public class TaskDAO {
    protected Connection getConnection() {
        return ConnectionFab.getInstance().getConnection();
    }

    public void insert(Task task) {
        String sql = " insert into tasks"
                + "(name "
                + ",description"
                + ",finished"
                + ",finished_date)"
                + " values( ?, ?, ?, ? ) ";
        // try-with-resources
        try(PreparedStatement prepared = getConnection().prepareStatement(sql);){

            prepared.setString(1, task.getName());
            prepared.setString(2, task.getDescription());
            prepared.setBoolean(3, task.getFinished());
            if(task.getFinishedDate() != null) {
                long tempo = task.getFinishedDate().getTime();
                prepared.setDate(4, new java.sql.Date(tempo));
            }
            prepared.execute();

        }catch(SQLException e) {
            e.printStackTrace();
        }
    }

}