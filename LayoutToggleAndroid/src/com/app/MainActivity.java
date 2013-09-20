package com.app;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends Activity
{
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        // Referencia o layout definido no xml
        LinearLayout layout = (LinearLayout)findViewById(R.id.layout);
        // Cria o toggle layout e define a orientação
        LinearLayoutToggle toggleLayout = new LinearLayoutToggle(this);
        toggleLayout.setOrientation(LinearLayout.VERTICAL);
        toggleLayout.setTypeToggle(LinearLayoutToggle.TO_UP_DOWN);
        
        int limit = layout.getChildCount();
        for (int i = 0; i < limit; i++) {
            View view = layout.getChildAt(0); 
            layout.removeViewAt(0);
            toggleLayout.addView(view);
        }
        
        
        // Texto que será exibido como titulo do layout
        toggleLayout.setTextLabel("Painel:  ");
        
        // Cria um chekbox e adiciona no toggle layout
        CheckBox ck = new CheckBox(this);
        ck.setText("teste aa");
        ck.setDuplicateParentStateEnabled(true);
        toggleLayout.addView(ck);
        
        // Cria um edit text+text view e agrupa em um layout
        EditText tt = new EditText(this);
        tt.setId(10);
        tt.setText("olaaaaaaa ");
        
        TextView te = new TextView(this);
        te.setText("Nome: ");
        
        ViewGroup vg = new LinearLayout(this);
        vg.addView(te);
        vg.addView(tt);
        
        // Adiciona o grupo o toggle layout e em seguida no layout do xml
        toggleLayout.addView(vg);
        layout.addView(toggleLayout);
        toggleLayout.hide();

        TextView ta = new TextView(this);
        ta.setText("Nome Texto: ");
        
        layout.addView(ta);
        
    }
}
