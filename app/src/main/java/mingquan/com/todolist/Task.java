package mingquan.com.todolist;

/**
 * Created by lenovo on 2017/4/13.
 * This is for recording all kinds of task people may have
 */

public class Task {
    private String eventTitle;
    private String eventTime;
    private String eventLocation;
    private String eventState;
    public Task(String title,String time,String location, String state){
        eventTitle = title;
        eventTime = time;
        eventLocation = location;
        eventState = state;
    }

    public String getTitle(){
        return eventTitle;
    }
    public String getTime(){
        return eventTime;
    }
    public String getLocation(){
        return eventLocation;
    }
    public String getState(){
        return eventState;
    }

}
