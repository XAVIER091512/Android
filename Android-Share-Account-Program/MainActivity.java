package com.example.moneycalculate;

import static java.lang.Double.parseDouble;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    private EditText fee, student, teacher, average;
    private Switch service_charge;
    private TextView Total, additionally, self_cost, self_addition;
    private TextView studentfee, studentnumber, studentsum;
    private Button calculate, reset;
    boolean service_boolean = false;
    int mealfee, students, teachers;
    int averagecost, averageaddition;
    int selfcost, selfaddition;
    double total;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setID();

        service_charge.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @RequiresApi(api = Build.VERSION_CODES.S)
            @SuppressLint("StringFormatMatches")
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (fee.getText().toString().equals("")) {
                    Toast.makeText(MainActivity.this, "Please input the fee.", Toast.LENGTH_SHORT).show();
                    service_charge.setChecked(false);
                    return;
                }
                mealfee = Integer.parseInt(fee.getText().toString());

                if (isChecked)
                    mealfee = (int) Math.ceil(mealfee * 1.1);
                else
                    mealfee = (int) Math.floor((mealfee / 1.1));

                fee.setText(String.valueOf(mealfee));
            }
        });

        calculate.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {

                if (fee.getText().toString().equals("") ||
                        student.getText().toString().equals("") ||
                        teacher.getText().toString().equals("")) {
                    Toast.makeText(MainActivity.this, "Please fill all item.", Toast.LENGTH_SHORT).show();
                    return;
                }

                mealfee = Integer.parseInt(fee.getText().toString());
                students = Integer.parseInt((student.getText().toString()));
                teachers = Integer.parseInt((teacher.getText().toString()));


                if (service_boolean)
                    mealfee = (int) Math.ceil(mealfee * 1.1);

                total = mealfee * (students + teachers);


                averagecost = (int) (Math.floor(total / students));
                averageaddition = averagecost - mealfee;
                selfcost = averagecost + (int) Math.ceil(total - averagecost * students);
                selfaddition = selfcost - mealfee;


                Total.setText("$" + String.valueOf(total));
                average.setText(String.valueOf(averagecost));
                additionally.setText("(+$" + String.valueOf(averageaddition) + ")");
                self_cost.setText(String.valueOf(selfcost));
                self_addition.setText("(+$" + String.valueOf(selfaddition) + ")");

                studentfee.setText(String.valueOf(averagecost));
                studentnumber.setText(String.valueOf(students - 1));
                studentsum.setText("$" + (averagecost * (students - 1)));

            }
        });

        average.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {

            }

            @SuppressLint("SetTextI18n")
            @Override
            public void afterTextChanged(Editable editable) {

                if (average.getText().toString().equals("")) {
                    average.setText("0");
                    studentfee.setText("0");
                }

                averagecost = Integer.parseInt(average.getText().toString());
                averageaddition = averagecost - mealfee;
                selfcost = averagecost + (int) Math.ceil(total - averagecost * students);
                selfaddition = selfcost - mealfee;

                additionally.setText("(+$" + String.valueOf(averageaddition) + ")");
                self_cost.setText(String.valueOf(selfcost));
                self_addition.setText("(+$" + String.valueOf(selfaddition) + ")");
                studentfee.setText(String.valueOf(averagecost));
                studentsum.setText(String.valueOf(averagecost * (students - 1)));

            }
        });
    }

    private void setID() {
        fee = (EditText) findViewById(R.id.fee);
        student = (EditText) findViewById(R.id.student);
        teacher = (EditText) findViewById(R.id.teacher);
        average = (EditText) findViewById(R.id.average);
        service_charge = (Switch) findViewById(R.id.service_charge);
        Total = (TextView) findViewById(R.id.Total);
        self_cost = (TextView) findViewById(R.id.self_cost);
        self_addition = (TextView) findViewById(R.id.self_addition);
        additionally = (TextView) findViewById(R.id.additionally);
        studentfee = (TextView) findViewById(R.id.studentfee);
        studentnumber = (TextView) findViewById(R.id.studentnumber);
        studentsum = (TextView) findViewById(R.id.studentsum);
        calculate = (Button) findViewById(R.id.calculate);
        reset = (Button) findViewById(R.id.reset);

    }

    public void Initialize(View view) {
        fee.setText("");
        student.setText("");
        teacher.setText("");
        Total.setText("$0");
        average.setText("0");
        additionally.setText("(+$0)");
        self_cost.setText("0");
        self_addition.setText("(+$0)");
        service_charge.setChecked(false);
        studentfee.setText("0");
        studentnumber.setText("0");
        studentsum.setText("$0");
    }
}