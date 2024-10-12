import Entity.Product;
import Service.ConsoleService;
import Service.ConsoleServiceImpl;
import Service.OperationService;
import Service.OperationServiceImpl;

import java.io.*;
import java.util.Scanner;

public class Main {

    private static final OperationService operationService;
    private static final ConsoleService consoleService;

    static {
        operationService = new OperationServiceImpl();
        consoleService = new ConsoleServiceImpl(operationService);
        consoleService.loadProductsFromCSV();
    }

    public static void main(String[] args) {
        consoleService.run();
    }
}