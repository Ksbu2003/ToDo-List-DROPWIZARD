package com.example.todoapp.resources;

import com.example.todoapp.core.Task;
import com.example.todoapp.db.TaskDAO;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/tasks")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TaskResource {

    private final TaskDAO taskDAO;

    public TaskResource(TaskDAO taskDAO) {
        this.taskDAO = taskDAO;
    }

    @GET
    public List<Task> getAllTasks() {
        return taskDAO.getAllTasks();
    }

    @GET
    @Path("/{id}")
    public Task getTaskById(@PathParam("id") long id) {
        return taskDAO.getTaskById(id);
    }

    @POST
    public void createTask(Task task) {
        taskDAO.createTask(task);
    }

    @PUT
    @Path("/{id}")
    public void updateTask(@PathParam("id") long id, Task task) {
        taskDAO.updateTask(id, task);
    }

    @DELETE
    @Path("/{id}")
    public void deleteTaskById(@PathParam("id") long id) {
        taskDAO.deleteTaskById(id);
    }
}
