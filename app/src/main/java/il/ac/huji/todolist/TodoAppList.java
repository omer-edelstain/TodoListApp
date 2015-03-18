package il.ac.huji.todolist;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.view.ContextMenu;
import 	android.widget.AdapterView;
import java.util.ArrayList;


public class TodoAppList extends ActionBarActivity {
    private final int MENU_CONTEXT_DELETE_ID = 1;
    private final int DELETE_FROM_MENU = -1;
    private ArrayList<String> m_arrstrTodoItems;
    private ListView lstvwTodoListElement;
    private EditText edttxtNewItem;
    private TodoAppAdapter adptDataToControlConnector;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo_app_list);
        lstvwTodoListElement = (ListView) findViewById(R.id.lstTodoItems);
        edttxtNewItem = (EditText)findViewById(R.id.edtNewItem);
        m_arrstrTodoItems = new ArrayList<>();
        adptDataToControlConnector = new TodoAppAdapter(this,m_arrstrTodoItems);

        lstvwTodoListElement.setAdapter(adptDataToControlConnector);
        registerForContextMenu(lstvwTodoListElement);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
        String title =  adptDataToControlConnector.getItem(info.position);
        menu.setHeaderTitle(title);

        menu.add(Menu.NONE, MENU_CONTEXT_DELETE_ID, Menu.NONE, "delete");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case MENU_CONTEXT_DELETE_ID:
                AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
                return DeleteItemFromList(info.position);
            default:
                return super.onContextItemSelected(item);
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_todo_app_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        boolean blnActionRes = false;
        switch(item.getItemId())
        {
            case R.id.menuitemAdd:
                blnActionRes =  AddItemToList();
                break;
            case R.id.menuitemDelete:
                blnActionRes = DeleteItemFromList(DELETE_FROM_MENU);
                break;
            case R.id.action_settings:
                blnActionRes = true;
        }
        return blnActionRes ;
    }

    private boolean AddItemToList() {
        if (!edttxtNewItem.getText().toString().matches(""))
        {
            adptDataToControlConnector.add(edttxtNewItem.getText().toString());
            edttxtNewItem.setText("");
        }

        return true;
    }

    private boolean DeleteItemFromList(int p_TaskId) {
        int nIndexOfItem = 0;
        if(p_TaskId >= 0) {
            nIndexOfItem = p_TaskId;
        }
        else
        {
            nIndexOfItem = 0;
        }
        adptDataToControlConnector.remove(String.valueOf(nIndexOfItem));
        return true;
    }
}
