package com.androidproject.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.androidproject.Adapter.UserListAdapter;
import com.androidproject.Model.User;
import com.androidproject.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class UserListVolley extends AppCompatActivity {

    RecyclerView recyclerView;
    UserListAdapter userListAdapter;
    ArrayList<User> users=new ArrayList<>();
    String user_id="1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list_volley);

           initViews();
           GetUserListFromServer();

    }

    private void initViews()
    {
        recyclerView=findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(UserListVolley.this));
        recyclerView.setHasFixedSize(true);
        userListAdapter=new UserListAdapter(UserListVolley.this,users);


    }

    private  void GetUserListFromServer()
    {
        String URL="http://onthefield.in/ConfluenceAssessment/GetDemoAPI.php";
        RequestQueue requestQueue= Volley.newRequestQueue(UserListVolley.this);

        StringRequest stringRequest=new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jsonObject=new JSONObject(response);
                    String result=jsonObject.getString("response");

                    if (result.equalsIgnoreCase("Success"))
                    {
                        JSONArray jsonArray=new JSONArray(result);
                        for (int i=0;i<jsonArray.length();i++)
                        {
                            JSONObject object=jsonArray.getJSONObject(i);
                            String user_id=object.getString("user_id");
                            String name=object.getString("name");
                            String contact_no=object.getString("contact_no");
                            String address=object.getString("email_id");

                            //To set values in array list
                            User userlist=new User(user_id,name,contact_no,address);
                            users.add(userlist);

                        }
                          //To set list in adapter
                        recyclerView.setAdapter(userListAdapter);

                    }else
                    {
                        Toast.makeText(UserListVolley.this, "Failure", Toast.LENGTH_SHORT).show();
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(UserListVolley.this, ""+error, Toast.LENGTH_SHORT).show();
            }
        })
        {
            @Override
            protected Map<String,String>getParams() throws AuthFailureError{
                 HashMap params=new HashMap();
                 params.put("user_id",user_id);
                 return params;
            }
        };
       requestQueue.add(stringRequest);


    }


}