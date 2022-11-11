package com.dst.automappingexercise;

import com.dst.automappingexercise.executor.ExecutorService;
import com.dst.automappingexercise.util.exceptions.DataValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class ConsoleRunner implements CommandLineRunner {

    private final ExecutorService executorService;

    @Autowired
    public ConsoleRunner(ExecutorService executorService) {
        this.executorService = executorService;
    }

    @Override
    public void run(String... args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        String command = scanner.nextLine();

        while (true) {
            String result;
            try {
                result = executorService.execute(command);
            } catch (DataValidationException e) {
                result = e.getMessage();
            }

            if (result.equals("END")) {
                break;
            }

            System.out.println(result);
            command = scanner.nextLine();
        }
    }
}
