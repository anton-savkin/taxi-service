package ru.digitalleague.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.digitalleague.common.TestClassFromCommon;


@SpringBootApplication
public class CoreApplication {

    public static void main(String[] args) {
        TestClassFromCommon.printHello();
        SpringApplication.run(CoreApplication.class, args);
    }

}
