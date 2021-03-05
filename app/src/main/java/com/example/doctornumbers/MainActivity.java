package com.example.doctornumbers;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Spinner spinner;
    private ListView listView;
    String docName[] = {"Dr. Monirul Islam", "Dr. Hasan", "Dr. Yang", "Dr. Asif"};
    String docQualifications[] = {"MBBS, FCPS, DMC", "MBBS, FCPS, DMC", "MBBS, FCPS, DMC", "MBBS, FCPS, DMC"};
    String docSpecialized[] = {"Cardiology", "Cardiology", "Cardiology", "Cardiology"};
    String docLocation[] = {"Khilgaon", "Khilgaon", "Khilgaon", "Khilgaon"};
    String docPhone[] = {"01600000001", "01600000002", "01600000003", "01600000004"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinner = findViewById(R.id.spinner);
        listView = findViewById(R.id.listView);

        myAdapter myAdapter = new myAdapter(this, docName, docQualifications, docSpecialized, docLocation, docPhone);
        listView.setAdapter(myAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this, ""+docPhone[position], Toast.LENGTH_SHORT).show();
                Intent callIntent = new Intent(Intent.ACTION_DIAL);
                callIntent.setData(Uri.parse("tel:"+docPhone[position]));
                startActivity(callIntent);
            }
        });

        String[] text = getResources().getStringArray(R.array.category_list);
        ArrayAdapter adapter = new ArrayAdapter(this,
                R.layout.spinner_item, text);
        adapter.setDropDownViewResource(R.layout.spinner_item);
        spinner.setAdapter(adapter);

    }

    class myAdapter extends ArrayAdapter<String> {
        Context context;
        String name[];
        String qualifications[];
        String specialized[];
        String location[];
        String phone[];

        myAdapter( Context c, String name[], String qualifications[], String specialized[], String location[], String phone[] ) {
            super(c, R.layout.item_details, R.id.docName, name);
            this.context = c;
            this.name = name;
            this.qualifications = qualifications;
            this.specialized = specialized;
            this.location = location;
            this.phone = phone;

        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LayoutInflater layoutInflater = (LayoutInflater)getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View item_details = layoutInflater.inflate(R.layout.item_details, parent, false);
            TextView docName = item_details.findViewById(R.id.docName);
            TextView docQualifications = item_details.findViewById(R.id.docQualifications);
            TextView docSpecialized = item_details.findViewById(R.id.docSpecialized);
            TextView docLocation = item_details.findViewById(R.id.docLocation);

            docName.setText(name[position]);
            docQualifications.setText(qualifications[position]);
            docSpecialized.setText("Specialized in: "+specialized[position]);
            docLocation.setText("Location: "+location[position]);
            return item_details;
        }
    }
}