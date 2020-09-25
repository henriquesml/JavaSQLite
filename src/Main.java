import dao.TaskDAO;
import models.Task;

public class Main {
    public static void main(String[] args) {
        Task task = new Task();
        task.setName("Inserir no BD com o DAO");
        task.setDescription("Registro de Tarefa");
        task.setFinished(false);
        task.setDataFinishedDate(null);

        TaskDAO dao = new TaskDAO();
        dao.insert(task);

    }
}