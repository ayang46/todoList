import java.util.Scanner;
import java.util.ArrayList;

public class todoList {
    private static Scanner in = new Scanner(System.in);
    private static ArrayList<Task> list = new ArrayList<>();

    public static void main(String[] args) {
        try {
            addTask();
            int menuChoice = 0;
            while (menuChoice != 9) {
                showMenu();
                menuChoice = getMenuChoice();
                handleMainMenuChoice(menuChoice);
            }
        } catch (Exception e) {
            System.out.println("Exception thrown: " + e);
        }
    }

    private static void showMenu() {
        System.out.println("----------------------------");
        System.out.println("1) Add Task");
        System.out.println("2) Delete Task");
        System.out.println("3) Mark Task Complete");
        System.out.println("4) Update Task Priority");
        System.out.println("5) Display To Do List");
        System.out.println("9) exit program");
        System.out.println();
    }

    private static int getMenuChoice() {
        System.out.print("Enter menu choice ---> ");
        int menuChoice = in.nextInt();
        return menuChoice;
    }
    private static void handleMainMenuChoice(int menuChoice) {
        switch (menuChoice) {
            case 1:
                addTask();
                return;
            case 2:
                deleteTask();
                return;
            case 3:
                markComplete();
                return;
            case 4:
                updatePriority();
                return;
            case 5:
                displayList();
                return;
            case 9:
                break;
            default:
                handleMenuError();
                break;
        }
    }

    private static void handleMenuError() {
        System.out.println("*** Invalid menu choice! ***");
    }

    private static void addTask() {
        System.out.print("Description of new task: ");
        String des = in.next();
        System.out.println();
        System.out.print("Priority: ");
        int pri = in.nextInt();
        Task aTask = new Task(des, pri);
        // add aTask to the arrayList of tasks given priority
        int currentPriority = pri;
        boolean found = false;

        if (list.size() != 0) {
            //make sure it doesn't run through for loop again when already put in list
            for (int i = 0; i < list.size() && !found; i++) {
                currentPriority = list.get(i).getPriority();
                if (aTask.getPriority() > currentPriority) {
                    list.add(i, aTask);
                    found = true;
                }else{
                    if(i == list.size()-1){
                        list.add(aTask);
                    }
                }
            }
        } else {
            list.add(aTask);
        }
    }

    private static void displayList() {
        int count = 0;
        for (Task t : list) {
            System.out.print("[" + count + "] ");
            System.out.println(t.toString());
            count++;
        }
    }

    private static void deleteTask() {
        System.out.println("Which task do you want to delete? Type the index of the task in the list starting from 0.");
        int taskInt = in.nextInt();
        if(taskInt>=0 && taskInt<list.size()){
        list.remove(taskInt);
        }else{
            System.out.println("Can't delete this task. Enter an appropriate index from the task list.");
        }
    }

    private static void markComplete() {
        System.out.println("Which task do you want to mark complete? Type the index of the task in the list starting from 0.");
        int taskInt = in.nextInt();
        Task task = list.get(taskInt);
        if(taskInt>=0 && taskInt <list.size()){
        for(Task t: list){
            if(t.getDescription().equals(task.getDescription())){
                task.setComplete();
            }
        }
        }else{
            System.out.println("Enter an approriate index of the task");
        }
    }

    private static void updatePriority() {
        System.out.println("Which task do you want to update? Type the index of the task in the list starting from 0.");
        int taskInt = in.nextInt();
        Task task = list.get(taskInt);
        System.out.println("What is the new priority?");
        int newPriority = in.nextInt();
        task.setPriority(newPriority);
        Task updatedTask = list.remove(taskInt);
        for (int i = 0; i < list.size(); i++) {
                int currentPriority = list.get(i).getPriority();
                if(newPriority == list.get(i).getPriority()){
                    list.add(i, updatedTask);
                    break;
                }
                else if (newPriority > currentPriority) {
                    list.add(i, updatedTask);
                    break;
                }else{
                    if(i == list.size()-1){
                        list.add(updatedTask);
                        break;
                    }
                }
            }
    }
}
