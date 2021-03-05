package com.androidproject.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.androidproject.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class AddVolleyActivity extends AppCompatActivity {

    EditText et_name,et_contact_no,et_address,et_username,et_password;
    Button btn_submit;

    String str_name,str_contact_no,str_address,str_username,str_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        setonclicklistener();
    }

    private void initViews()
    {
        et_name=findViewById(R.id.et_name);
        et_contact_no=findViewById(R.id.et_contact_no);
        et_address=findViewById(R.id.et_address);
        et_username=findViewById(R.id.et_username);
        et_password=findViewById(R.id.et_password);
        btn_submit=findViewById(R.id.btn_submit);

    }

    private  void setonclicklistener()
    {
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                str_name=et_name.getText().toString().trim();
                str_contact_no=et_contact_no.getText().toString().trim();
                str_address=et_address.getText().toString().trim();
                str_username=et_username.getText().toString().trim();
                str_password=et_password.getText().toString().trim();

                if (!str_name.isEmpty())
                {

                    if (!str_contact_no.isEmpty())
                    {

                     if (!str_address.isEmpty())
                     {

                         if (!str_username.isEmpty())
                         {
                             if (!str_password.isEmpty())
                             {


                               SendDataToServer(str_name,str_contact_no,str_address,str_username,str_password);

                             }else
                             {
                                 Toast.makeText(AddVolleyActivity.this, "Please enter password", Toast.LENGTH_SHORT).show();
                             }


                         }else
                         {
                             Toast.makeText(AddVolleyActivity.this, "Please enter username", Toast.LENGTH_SHORT).show();
                         }

                     }else
                     {
                         Toast.makeText(AddVolleyActivity.this, "Please enter address", Toast.LENGTH_SHORT).show();
                     }


                    }else
                    {
                        Toast.makeText(AddVolleyActivity.this, "Please enter contact no", Toast.LENGTH_SHORT).show();
                    }


                }else
                {
                    Toast.makeText(AddVolleyActivity.this, "Please enter name", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    private void SendDataToServer(final String str_name,final String str_contact_no, final String str_address,final String str_username,final String str_password)
    {
        String URL="http://onthefield.in/ConfluenceAssessment/AddDemoAPI.php";
        RequestQueue requestQueue= Volley.newRequestQueue(AddVolleyActivity.this);

        StringRequest stringRequest=new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jsonObject=new JSONObject(response);
                    String result=jsonObject.getString("response");
                     if (result.equalsIgnoreCase("failure"))
                     {
                         Toast.makeText(AddVolleyActivity.this, "Failed to submit", Toast.LENGTH_SHORT).show();
                     }else
                     {
                         Intent intent=new Intent(AddVolleyActivity.this,UserListVolley.class);
                         startActivity(intent);

                         Toast.makeText(AddVolleyActivity.this, "Added successfully", Toast.LENGTH_SHORT).show();
                     }


                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(AddVolleyActivity.this, ""+error, Toast.LENGTH_SHORT).show();

            }
        })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap params = new HashMap();
                params.put("name", str_name);
                params.put("contact_no", str_contact_no);
                params.put("email_id", str_address);
                params.put("username", str_username);
                params.put("password", str_password);
                Log.d("params", "" + params);
                return params;
            }
        };

        requestQueue.add(stringRequest);

    }

}