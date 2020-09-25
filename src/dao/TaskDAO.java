package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

    public List<Task> getAllTasksNotFinished(){
        List<Task> list = new ArrayList<>();

        String sql = " insert into tasks"
                + "(name "
                + ",description"
                + ",finished"
                + ",finished_date)"
                + " values( ?, ?, ?, ? ) ";

        try(PreparedStatement prepared = getConnection().prepareStatement(sql);){

            prepared.setBoolean(1, false);

            ResultSet result = prepared.executeQuery();

            while(result.next()) {

                Task tarefa = new Task();

                tarefa.setId(result.getLong(1));

                tarefa.setName(result.getString(2));
                tarefa.setDescription(result.getString(3));
                tarefa.setFinished(result.getBoolean(4));

                java.sql.Date dataTemporario = result.getDate(5);
                if(dataTemporario != null) {
                    Date dataFinalizacao = new Date(dataTemporario.getTime());
                    tarefa.setDataFinishedDate(dataFinalizacao);
                }

                list.add(tarefa);
            }

        }catch(SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    public List<Task> getAll(){
        List<Task> list = new ArrayList<>();

        String sql = "select id, name, description, finished, finished_date "
                + " from tasks "
                + " order by id  ";

        //
        try(PreparedStatement prepared = getConnection().prepareStatement(sql);){

            ResultSet result = prepared.executeQuery();

            while(result.next()) {

                Task task = new Task();

                task.setId(result.getLong(1));

                task.setName(result.getString(2));
                task.setDescription(result.getString(3));
                task.setFinished(result.getBoolean(4));

                java.sql.Date dataTemporario = result.getDate(5);
                if(dataTemporario != null) {
                    Date dataFinalizacao = new Date(dataTemporario.getTime());
                    task.setDataFinishedDate(dataFinalizacao);
                }

                list.add(task);
            }

        }catch(SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    public Task getById(long id) {

        Task task = null;

        String sql = "select id, name, description, finished, finished_date "
                + " from tasks "
                + " where id = ? ";

        //
        try(PreparedStatement prepared = getConnection().prepareStatement(sql);){

            prepared.setLong(1, id);

            ResultSet result = prepared.executeQuery();

            if(result.next()) {

                task = new Task();

                task.setId(id);

                task.setName(result.getString(2));
                task.setDescription(result.getString(3));
                task.setFinished(result.getBoolean(4));

                java.sql.Date dataTemporario = result.getDate(5);
                if(dataTemporario != null) {
                    Date dataFinalizacao = new Date(dataTemporario.getTime());
                    task.setDataFinishedDate(dataFinalizacao);
                }
            }

        }catch(SQLException e) {
            e.printStackTrace();
        }

        return task;
    }

    public void deleteById(Task task) {
        String sql = " delete from tasks"
                + " where id = ? ";

        try(PreparedStatement prepared = getConnection().prepareStatement(sql);){

            prepared.setLong(1, task.getId());

            prepared.execute();

        }catch(SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateById(Task task) {
        String sql = " update tasks"
                + " set name = ? "
                + ",description = ?"
                + ",finished = ?"
                + ",finished_date = ?"
                + " where id = ? ";
        // try-with-resources
        try(PreparedStatement prepared = getConnection().prepareStatement(sql);){

            prepared.setString(1, task.getName());
            prepared.setString(2, task.getDescription());
            prepared.setBoolean(3, task.getFinished());
            if(task.getFinishedDate() != null) {
                long tempo = task.getFinishedDate().getTime();
                prepared.setDate(4, new java.sql.Date(tempo));
            }
            prepared.setLong(5, task.getId());

            prepared.execute();

        }catch(SQLException e) {
            e.printStackTrace();
        }
    }

    public void finalizarTarefa(Task task) {
        String sql = " update tasks"
                + " set finished = ?"
                + ",finished_date = ?"
                + " where id = ? ";
        // try-with-resources
        try(PreparedStatement prepared = getConnection().prepareStatement(sql);){

            prepared.setBoolean(1, true);
            if(task.getFinishedDate() != null) {
                long tempo = new Date().getTime();
                prepared.setDate(2, new java.sql.Date(tempo));
            }
            prepared.setLong(3, task.getId());

            prepared.execute();

        }catch(SQLException e) {
            e.printStackTrace();
        }
    }

}