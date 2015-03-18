package il.ac.huji.todolist;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;




/**
 * Created by  on 10/2/13.
 */
public class TodoAppAdapter extends ArrayAdapter<String> {
    private Context mycontext;
    private ArrayList<String> m_arrstrTodoItems;

    public TodoAppAdapter(Context context,ArrayList<String> p_arrlstTodoAppData)
    {
        super(context,R.layout.todo_list_item,p_arrlstTodoAppData);
        m_arrstrTodoItems = p_arrlstTodoAppData;
        this.mycontext=context;



    }

    @Override
    public void add(String p_NewTask)
    {
        m_arrstrTodoItems.add(p_NewTask);
        notifyDataSetChanged();
    }
    @Override
    public void remove(String p_strTodoTaskIndexAsStr)
    {
        m_arrstrTodoItems.remove(Integer.parseInt(p_strTodoTaskIndexAsStr));
        notifyDataSetChanged();
    }
    @Override
    public int getCount()
    {
        return m_arrstrTodoItems.size();
    }


    @Override
    public String getItem(int position)
    {
        return m_arrstrTodoItems.get(position);
    }


    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }


    @Override
    public View getView(int position,View convertView,ViewGroup parent)
    {
        View vwTodoTaskVisual = convertView;


        if (vwTodoTaskVisual ==null)
        {
            LayoutInflater inflater=((Activity)mycontext).getLayoutInflater();
            vwTodoTaskVisual =inflater.inflate(R.layout.todo_list_item,parent,false);
        }
        TextView txtvwItemContent = (TextView) vwTodoTaskVisual.findViewById(R.id.txtvwItemContent);
        txtvwItemContent.setText(m_arrstrTodoItems.get(position));
        if(position % 2 == 0)
        {
            txtvwItemContent.setTextColor(android.graphics.Color.RED);
            txtvwItemContent.setBackgroundColor(android.graphics.Color.BLUE);
        }
        else
        {
            txtvwItemContent.setTextColor(android.graphics.Color.BLUE);
            txtvwItemContent.setBackgroundColor(android.graphics.Color.RED);
        }
        return  vwTodoTaskVisual;
    }

}


