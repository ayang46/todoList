public class Task {
    private String description;
    private boolean completed;
    private int priority;
    public Task(String str, int p){
       description = str;
       priority = p;
       completed = false;
    }

    public int getPriority(){
        return priority;
    }
    
    public void setComplete(){
        completed = true;
    }
    public String getDescription(){
        return description;
    }
    public void setPriority(int p){
        priority = p;
    }
    
    public String toString(){
        return ("Priority:" + priority + " Completed? " + completed + " Description: " + description );
    }

}
