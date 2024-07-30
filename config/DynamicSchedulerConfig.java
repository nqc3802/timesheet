package com.example.timesheet.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;

import com.example.timesheet.email.EmailService;
import com.example.timesheet.user.UserRepository;

import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

import java.time.Instant;
import java.time.ZoneId;
import java.util.List;
import java.util.concurrent.ScheduledFuture;

@Configuration
@EnableScheduling
public class DynamicSchedulerConfig implements SchedulingConfigurer {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmailService emailService;

    @Value("${scheduler.cron.expression}")
    private String cronExpression;

    private ThreadPoolTaskScheduler taskScheduler;
    private ScheduledFuture<?> scheduledFuture;

    @Bean
    public TaskScheduler taskScheduler() {
        if (taskScheduler == null) {
            taskScheduler = new ThreadPoolTaskScheduler();
            taskScheduler.setPoolSize(10);
            taskScheduler.initialize();
        }
        return taskScheduler;
    }

    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        taskRegistrar.setTaskScheduler(taskScheduler());
        scheduleTaskWithCronExpression();
    }

    private void scheduleTaskWithCronExpression() {
        if (scheduledFuture != null) {
            scheduledFuture.cancel(false);
        }
        scheduledFuture = taskScheduler.schedule(this::scheduledTask, triggerContext -> {
            CronTrigger cronTrigger = new CronTrigger(cronExpression);
            Instant nextExecution = cronTrigger.nextExecution(triggerContext).atZone(ZoneId.systemDefault())
                    .toInstant();
            return nextExecution;
        });
    }

    public void updateCronSchedule(String newCronExpression) {
        this.cronExpression = newCronExpression;
        scheduleTaskWithCronExpression();
    }

    public List<String> getEmail() {
        return userRepository.findAllEmail();
    }

    public void scheduledTask() {
        List<String> emails = getEmail();
        for (String email : emails) {
            emailService.sendEmail(email, "Timesheet", "ABCDEFGHIKLMNOPQRSTUVWXYZ");
            System.out.println("Sending email to " + email);
        }
    }
}
