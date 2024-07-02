package com.example.todoapp;

import com.example.todoapp.core.Task;
import com.example.todoapp.db.TaskDAO;
import com.example.todoapp.resources.TaskResource;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.dropwizard.db.DataSourceFactory;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;

import java.util.List;

public class TodoApplication extends Application<TodoConfiguration> {

    public static void main(String[] args) throws Exception {
        new TodoApplication().run(args);
    }

    @Override
    public void initialize(Bootstrap<TodoConfiguration> bootstrap) {
        // Initialize any necessary components if needed
    }

    @Override
    public void run(TodoConfiguration configuration, Environment environment) {
        final Jdbi jdbi = Jdbi.create(configuration.getDataSourceFactory().build(environment.metrics(), "mysql"));
        jdbi.installPlugin(new SqlObjectPlugin());

        // TaskDAO interface implemented directly within TodoApplication
        TaskDAO taskDAO = jdbi.onDemand(TaskDAO.class);

        // Registering resources
        environment.jersey().register(new TaskResource(taskDAO));
    }
}
