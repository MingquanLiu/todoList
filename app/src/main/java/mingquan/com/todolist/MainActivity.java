package mingquan.com.todolist;
import android.content.Context;
import android.content.DialogInterface;
import android.media.Image;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private ListView myTaskListView;
    private TasksAdapter myAdapter;
    private ArrayList<Task> myList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myList = new ArrayList<>();
        myAdapter = new TasksAdapter(this,myList);
        myTaskListView = (ListView) findViewById(R.id.list_todo);
        myTaskListView.setAdapter(myAdapter);
//        myList.add(new Task("Like","Monday","Your home","finished"));     Tester
//        myList.add(new Task("WTF","Monday","Here","d"));
//        myList.add(new Task("Mike",null,"Here","d"));
 //      myAdapter.notifyDataSetChanged();
    }
    private void updateUI() {
            myAdapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {             //Menu options
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {       // Select items when pressing the button for different cases
        switch (item.getItemId()) {
            case R.id.action_add_task:                          // First case for add task
                final EditText taskEditText = new EditText(this);
                AlertDialog dialog = new AlertDialog.Builder(this)
                        .setTitle("Add a new task")                     //Set up the interface
                        .setMessage("What do you want to do next?")
                        .setView(taskEditText)
                        .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String task = String.valueOf(taskEditText.getText());
                                Task newTask = new Task(task,"Monday","China","upcoming");
                                addTask(newTask);
                                Log.d(TAG, "Task to add: " + task);             // For testing the content of the task
                            }
                        })
                        .setNegativeButton("Cancel", null)
                        .create();
                dialog.show();

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }
    public void deleteTask(String task) {
        int index = myList.indexOf(task);
        for(int i = index;i<myList.size()-1;i++){
            myList.set(i,myList.get(i+1));
        }
        myList.set(myList.size()-1,null);
        updateUI();
    }
    public void addTask(Task task){
        myList.add(task);
        updateUI();
    }

    private class TasksAdapter extends ArrayAdapter<Task> {
        public TasksAdapter(Context context, ArrayList<Task> tasks) {
            super(context,0,tasks);
        }
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // Get the data item for this position

            Log.d(TAG, "Position "+ position);
            Task task = getItem(position);

            // Check if an existing view is being reused, otherwise inflate the view
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_todo, parent, false);
            }
            // Lookup view for data population
            TextView taskTitle = (TextView) convertView.findViewById(R.id.task_title);
            TextView taskTime = (TextView) convertView.findViewById(R.id.task_time);
            TextView taskLocation = (TextView) convertView.findViewById(R.id.task_location);
            ImageView taskState = (ImageView) convertView.findViewById(R.id.task_state);
            // Populate the data into the template view using the data object
            taskTitle.setText(task.getTitle());
            taskTime.setText(task.getTime());
            if(task.getTime()==null)                            // Checking for output
                taskLocation.setText(task.getLocation());
            else
                if(task.getLocation()==null)
                    taskLocation.setText(task.getLocation());
                else
                    taskLocation.setText(" | "+task.getLocation());

            if(task.getState()=="upcoming")                     //Checking for output picture
            taskState.setImageResource(R.drawable.pic1);
            else if(task.getState()=="finished")
                taskState.setImageResource(R.drawable.pic3);
            else
                taskState.setImageResource(R.drawable.pic2);
            // Return the completed view to render on screen
            return convertView;
        }
    }
}
