package com.example.usgchallange.volley;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MealDetailRequest {
    private MealDetail mealDetail;
    private Context context;
    private RequestQueue queue;
    public MealDetailRequest(Context ct){
        this.context = ct;
        try {
            if (queue == null) {
                queue = Volley.newRequestQueue(context);
            }
        }catch (Exception e){
            Log.e("Error","Queue Error");
        }
    }

    public interface VolleyResponseListener{
        void onError(String message);
        void onResponse(MealDetail mealDetail);
    }

    public void getMealDetail(String mealId, VolleyResponseListener volleyResponseListener ){

        String url ="https://www.themealdb.com/api/json/v1/1/lookup.php?i=";
        url +=mealId;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                ArrayList<String> ingredients = new ArrayList<>();
                ArrayList<String> measures = new ArrayList<>();
                mealDetail = new MealDetail();
                try {

                    JSONObject root = new JSONObject(response);
                    JSONArray meals = root.getJSONArray("meals");
                    JSONObject mealDetails= meals.getJSONObject(0);
                    for(int i =1 ; i< 21 ;i++){
                        String strIngredients="strIngredient";
                        String strMeasures="strMeasure";
                        strIngredients +=i;
                        strMeasures +=i;
                        if(!mealDetails.getString(strIngredients).equals("null") && !mealDetails.getString(strIngredients).equals(""))
                            ingredients.add(mealDetails.getString(strIngredients));

                        if(!mealDetails.getString(strMeasures).equals("null") && !mealDetails.getString(strMeasures).equals(""))
                            measures.add(mealDetails.getString(strMeasures));

                    }
                    mealDetail.setIngredients(ingredients);
                    mealDetail.setMeasures(measures);
                    mealDetail.setMealId(mealDetails.getString("idMeal"));
                    mealDetail.setArea(mealDetails.getString("strArea"));
                    mealDetail.setCategory(mealDetails.getString("strCategory"));
                    mealDetail.setMealName(mealDetails.getString("strMeal"));
                    mealDetail.setYoutubeUrl(mealDetails.getString("strYoutube"));
                    mealDetail.setMealImage(mealDetails.getString("strMealThumb"));
                    mealDetail.setInstructions(mealDetails.getString("strInstructions"));

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                volleyResponseListener.onResponse(mealDetail);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        queue.add(stringRequest);
      //  return mealDetail;
    }
}
