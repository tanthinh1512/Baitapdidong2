package com.thinh.individualexercise2;

import android.content.Context;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.Iterator;

/**
 * Created by thonghuynh on 4/28/2017.
 */

public class FragmentResult extends Fragment {
    View v;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_result, container);
        LinearLayout linearLayout = (LinearLayout) v.findViewById(R.id.result_wrap);

        new MyGetData(getContext(), linearLayout).execute();

        return v;
    }

    public class MyGetData extends AsyncTask {
        private Context context;
        private LinearLayout linearLayout;

        MyGetData(Context c, LinearLayout ll) {
            this.context = c;
            this.linearLayout = ll;
        }
        @Override
        protected Object doInBackground(Object[] params) {
            String link = "http://thanhhungqb.tk:8080/kqxsmn";

            try {
                HttpClient client = new DefaultHttpClient();
                HttpGet request = new HttpGet(new URI(link));
                HttpResponse response = client.execute(request);

                BufferedReader in = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

                StringBuffer str = new StringBuffer("");
                String line = "";

                while ( (line = in.readLine()) != null ) {
                    str.append(line);
                }

                in.close();
                return str.toString();

            }catch (Exception e) {
                e.printStackTrace();
            }

            return "";
        }

        @Override
        protected void onPostExecute(Object o) {
            String data = (String)o;
            showResult(data);
        }
        public void showResult(String data) {
            //Parse JSON
            try {
                JSONObject jsonData = new JSONObject(data);
                Iterator<String> provinces = jsonData.keys();

                while ( provinces.hasNext() ) {
                    String province = (String)provinces.next();
                    //Log.d("provice", province);

                    JSONObject _dates = jsonData.getJSONObject(province);
                    Iterator<String> dates = _dates.keys();
                    while ( dates.hasNext() ) {
                        String date = (String) dates.next();
                        //Log.d("date", date);
                        //create TextView
                        TextView textView1 = new TextView(context);
                        LinearLayout.LayoutParams params0_0_1 = new LinearLayout.LayoutParams(
                                ViewGroup.LayoutParams.MATCH_PARENT,
                                ViewGroup.LayoutParams.WRAP_CONTENT
                        );
                        textView1.setText(province + " - " + date);
                        linearLayout.addView(textView1);

                        //create TableLayout
                        TableLayout tableLayout = new TableLayout(context);
                        LinearLayout.LayoutParams params0_0 = new LinearLayout.LayoutParams(
                                ViewGroup.LayoutParams.MATCH_PARENT,
                                ViewGroup.LayoutParams.WRAP_CONTENT
                        );
                        tableLayout.setLayoutParams(params0_0);
                        tableLayout.setBackgroundColor(Color.BLACK);
                        linearLayout.addView(tableLayout);

                        //create TableRow : Prize  and  Number
                        TableRow tableRow0 = new TableRow(context);
                        TableLayout.LayoutParams params0_1 = new TableLayout.LayoutParams(
                                ViewGroup.LayoutParams.MATCH_PARENT,
                                ViewGroup.LayoutParams.MATCH_PARENT, 1.0f
                        );
                        params0_1.setMargins(1, 1, 1, 1);
                        tableRow0.setBackgroundColor(Color.WHITE);
                        tableRow0.setLayoutParams(params0_1);
                        //add to TableLayout
                        tableLayout.addView(tableRow0);

                        TableRow.LayoutParams params0_2 = new TableRow.LayoutParams(
                                ViewGroup.LayoutParams.MATCH_PARENT,
                                ViewGroup.LayoutParams.MATCH_PARENT, 1.0f
                        );
                        TableRow.LayoutParams params0_3 = new TableRow.LayoutParams(
                                ViewGroup.LayoutParams.MATCH_PARENT,
                                ViewGroup.LayoutParams.MATCH_PARENT, 1.0f
                        );

                        //create 2 textView
                        TextView tv0_1 = new TextView(context);
                        tv0_1.setText("Prize");
                        tv0_1.setGravity(View.TEXT_ALIGNMENT_GRAVITY);
                        tv0_1.setLayoutParams(params0_2);
                        TextView tv0_2 = new TextView(context);
                        tv0_2.setText("Number");
                        tv0_2.setGravity(View.TEXT_ALIGNMENT_GRAVITY);
                        tv0_2.setLayoutParams(params0_3);
                        //add to tableRow0
                        tableRow0.addView(tv0_1);
                        tableRow0.addView(tv0_2);



                        JSONObject _prizes = _dates.getJSONObject(date);
                        Iterator<String> prizes = _prizes.keys();
                        while ( prizes.hasNext() ) {
                            String prize = (String)prizes.next();
                            //Log.d("Prize", prize);

                            //create TableRow
                            TableRow tableRow = new TableRow(context);
                            TableLayout.LayoutParams params = new TableLayout.LayoutParams(
                                    ViewGroup.LayoutParams.MATCH_PARENT,
                                    ViewGroup.LayoutParams.MATCH_PARENT, 1.0f
                            );
                            params.setMargins(1, 0, 1, 1);
                            tableRow.setLayoutParams(params);
                            tableRow.setBackgroundColor(Color.WHITE);
                            tableLayout.addView(tableRow);

                            TableRow.LayoutParams params2 = new TableRow.LayoutParams(
                                    ViewGroup.LayoutParams.MATCH_PARENT,
                                    ViewGroup.LayoutParams.MATCH_PARENT, 1.0f
                            );
                            TableRow.LayoutParams params3 = new TableRow.LayoutParams(
                                    ViewGroup.LayoutParams.MATCH_PARENT,
                                    ViewGroup.LayoutParams.MATCH_PARENT, 1.0f
                            );

                            //create 2 textView
                            TextView tv1 = new TextView(context);
                            tv1.setText(prize);
                            tv1.setGravity(View.TEXT_ALIGNMENT_GRAVITY);
                            tv1.setLayoutParams(params2);
                            TextView tv2 = new TextView(context);
                            tv2.setGravity(View.TEXT_ALIGNMENT_GRAVITY);
                            tv2.setLayoutParams(params3);
                            //add to tableRow
                            tableRow.addView(tv1);
                            tableRow.addView(tv2);

                            String sNumbers = "";
                            JSONArray arr = _prizes.getJSONArray(prize);
                            for (int i = 0; i < arr.length(); i++) {
                                //Log.d("--", arr.getString(i));
                                sNumbers += arr.getString(i);
                                if ((i % 3) == 0 && i != (arr.length() - 1))
                                    sNumbers += "\n";
                                else
                                    sNumbers += "  ";
                            }
                            tv2.setText(sNumbers);
                        }
                    }

                }

            }
            catch (JSONException e) {
                Log.d("JSON parse error", e.getMessage());
                e.printStackTrace();
            }
        }
    }

}
