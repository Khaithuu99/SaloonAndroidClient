package com.example.salon_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.salon_app.Model.Customer;
import com.example.salon_app.Services.CustomerApi;
import com.example.salon_app.Services.RetrofitService;

import java.util.logging.Level;
import java.util.logging.Logger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeComponents();
    }

    private void initializeComponents() {
        EditText FName = findViewById(R.id.FName);
        EditText Email = findViewById(R.id.Email);
        EditText Phone = findViewById(R.id.Phone);
        Button Save = (Button) findViewById(R.id.Save);

        RetrofitService retrofitService = new RetrofitService();
        CustomerApi customerApi = retrofitService.getRetrofit().create(CustomerApi.class);

        Save.setOnClickListener(view -> {
            String name = String.valueOf(FName.getText());
            String email = String.valueOf(Email.getText());
            String phone = String.valueOf(Phone.getText());

            Customer customer = new Customer();
            customer.setName(name);
            customer.setEmail(email);
            customer.setPhone(phone);

            customerApi.save(customer)
                    .enqueue(new Callback<Customer>() {
                        @Override
                        public void onResponse(Call<Customer> call, Response<Customer> response) {
                            Toast.makeText(MainActivity.this, "Save Successfully.!", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onFailure(Call<Customer> call, Throwable t) {
                            Toast.makeText(MainActivity.this, "Failed to Save..!!!:", Toast.LENGTH_SHORT).show();
                            Logger.getLogger(MainActivity.class.getName()).log(Level.SEVERE, "Error Occurred", t);
                                }
                    });
        });



    }
}