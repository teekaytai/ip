import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);
        System.out.println("Hello there! I'm Duke!");
        System.out.println("How can I help you?");

        Scanner sc = new Scanner(System.in);
        Task[] tasks = new Task[100];
        int numTasks = 0;

        while (true) {
            String input = sc.nextLine();
            if (input.equals("bye")) {
                System.out.println("Ok, see you next time!");
                break;
            }

            String[] inputArgs = input.split(" ", 2);
            String command = inputArgs[0];
            switch (command) {
                case "list": {
                    for (int i = 0; i < numTasks; i++) {
                        System.out.printf("%d. %s%n", i + 1, tasks[i]);
                    }
                    break;
                }
                case "mark": {
                    int taskNum = Integer.parseInt(inputArgs[1]);
                    Task task = tasks[taskNum - 1];
                    task.markAsDone();
                    System.out.printf("Well done! I've marked task %d as done:%n", taskNum);
                    System.out.println(task);
                    break;
                }
                case "unmark": {
                    int taskNum = Integer.parseInt(inputArgs[1]);
                    Task task = tasks[taskNum - 1];
                    task.markAsUndone();
                    System.out.printf("Sure, I've marked task %d as not done:%n", taskNum);
                    System.out.println(task);
                    break;
                }
                case "todo": {
                    Todo todo = new Todo(inputArgs[1]);
                    tasks[numTasks++] = todo;
                    System.out.println("Got it, I've added this to-do:");
                    System.out.println(todo);
                    break;
                }
                case "deadline": {
                    Deadline deadline = new Deadline(inputArgs[1]);
                    tasks[numTasks++] = deadline;
                    System.out.println("Got it, I've added this deadline:");
                    System.out.println(deadline);
                    break;
                }
                case "event": {
                    Event event = new Event(inputArgs[1]);
                    tasks[numTasks++] = event;
                    System.out.println("Got it, I've added this event:");
                    System.out.println(event);
                    break;
                }
            }
        }
    }
}
