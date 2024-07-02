package com.example.todoapp.db;

import com.example.todoapp.core.Task;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;

import java.util.List;

public interface TaskDAO {

    @SqlQuery("SELECT * FROM tasks")
    @RegisterBeanMapper(Task.class)
    List<Task> getAllTasks();

    @SqlQuery("SELECT * FROM tasks WHERE id = :id")
    @RegisterBeanMapper(Task.class)
    Task getTaskById(@Bind("id") long id);

    @SqlUpdate("INSERT INTO tasks (description, start_date, target_date, status) " +
            "VALUES (:description, :startDate, :targetDate, :status)")
    void createTask(@BindBean Task task);

    @SqlUpdate("UPDATE tasks SET description = :description, " +
            "start_date = :startDate, target_date = :targetDate, " +
            "status = :status WHERE id = :id")
    void updateTask(@Bind("id") long id, @BindBean Task task);

    @SqlUpdate("DELETE FROM tasks WHERE id = :id")
    void deleteTaskById(@Bind("id") long id);
}
