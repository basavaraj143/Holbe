package com.appsriv.holbe;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class SupplementFragment extends Fragment
{
	private ExpandListAdapterForItems ExpAdapter;
	private ArrayList<Group> ExpListItems;
	private ExpandableListView ExpandList;
	public static ArrayList<Group> list;
	private int flag[];
	private int progressBarRes[];
	String name[];
	String lineColour[] = new String[]{"#ABD14B","#3CC3AF","#1AA2DF","#AA68B4","#BD345E"};
	TextView overalll_compliance;
	ArrayList<Workout> work_list = null;
	ArrayList<Supplement> sup_list = null;
	ArrayList<LifeStyle> life_list = null;
	ArrayList<Food> food_list = null;
	ArrayList<Others> other_list = null;


	private ViewPager viewPager;
	int str_overalll_compliance;
	String workout_count, supplement_count, lifestyle_count, food_count, others_count;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{

		View view = inflater.inflate(R.layout.treatment_with_section, container, false);
		ExpandList = (ExpandableListView)view.findViewById(R.id.exp_list);
		overalll_compliance =(TextView)view.findViewById(R.id.overalll_compliance);
		String url = "http://192.185.26.69/~holbe/api/patient/get_treatment.php?id=1";
		new AsyncHttpTask().execute(url);
		return view;
	}


	public class AsyncHttpTask extends AsyncTask<String, Void, Integer>
	{

		ProgressDialog progressDialog;
		@Override
		protected void onPreExecute() {

			progressDialog = ProgressDialog.show(getActivity(),"Please wait","Loading...");
		}

		@Override
		protected Integer doInBackground(String... params)
		{
			InputStream inputStream = null;
			Integer result = 0;
			HttpURLConnection urlConnection = null;

			try {
                /* forming th java.net.URL object */
				URL url6 = new URL(params[0]);

				urlConnection = (HttpURLConnection) url6.openConnection();

                /* for Get request */
				urlConnection.setRequestMethod("GET");

				int statusCode = urlConnection.getResponseCode();

                /* 200 represents HTTP OK */
				if (statusCode ==  200)
				{
					System.out.println("Status code is:" + statusCode);
					BufferedReader r = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
					StringBuilder response = new StringBuilder();
					String line;
					while ((line = r.readLine()) != null) {
						response.append(line);
					}
					System.out.println("this is response" + response.toString());

					parseResult(response.toString());



					result = 1; // Successful

				}else
				{

					result = 0; //"Failed to fetch data!"// ;
					System.out.print("unable to fetch data");
				}

			} catch (Exception e) {


				//  Log.d(TAG, e.getLocalizedMessage());
			}
			finally {
				if (urlConnection!=null)
				{

				}
			}

			return result; //"Failed to fetch data!";
		}

		@Override
		protected void onPostExecute(Integer result)
		{

			//mProgressDialog.dismiss();

            /* Download complete. Lets update UI */
			if (result == 1)
			{
				//Log.e(TAG, "fetch data!");

               /* adapter6 = new MyRecyclerAdapter6(SearchActivity.this,feedItemList6);
                HRecyclerView.setAdapter(adapter6);*/

				DrawerActivity.top.setText(workout_count);
				DrawerActivity.top1.setText(supplement_count);
				DrawerActivity.top2.setText(lifestyle_count);
				DrawerActivity.top3.setText(food_count);
				DrawerActivity.top4.setText(others_count);

				overalll_compliance.setText(""+str_overalll_compliance+"%");
				ExpAdapter= new ExpandListAdapterForItems(getActivity(),list,ExpandList,progressBarRes);
				ExpandList.setAdapter(ExpAdapter);
				ExpandList.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
					@Override
					public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id)
					{

						Intent intent = new Intent(getActivity(),CominUpWithListview.class);
						intent.putExtra("groupPosition",groupPosition);
						intent.putExtra("childPosition",childPosition);
						startActivity(intent);
						//startActivity(new Intent(getActivity(),CominUpWithListview.class));
						return false;
					}
				});
				progressDialog.dismiss();
				ExpAdapter.notifyDataSetChanged();
				// Toast.makeText(getApplicationContext(), "your search results", Toast.LENGTH_LONG).show();
			}
			else
			{

				 Log.e("fail", "Failed to fetch data!");
				//Toast.makeText(, "", Toast.LENGTH_SHORT).show();
			}
		}
	}
	private void parseResult(String result)
	{
		list = new ArrayList<>();
		flag = new int[] { R.drawable.circle_suppliments, R.drawable.circle_workout, R.drawable.circle_heart, R.drawable.circlefoodanddrink,
				R.drawable.circle_others};
		progressBarRes = new int[]
				{ R.drawable.supplement_circle_progress_foregrnd, R.drawable.workout_circle_progress_foregrnd, R.drawable.lifestylr_circle_progress_foregrnd,
				R.drawable.foodanddrinks_circle_progress_foregrnd,
				R.drawable.others_circle_progress_foregrnd };

		name=new String[]{"Supplements","Workout","Lifestyle","Food & Drinks","Others"};

		lineColour = new String[]{"#ABD14B","#3CC3AF","#1AA2DF","#AA68B4","#BD345E"};

		try
		{

			JSONObject object = new JSONObject(result);

			if (object.length()!=0)
			{

				for (int j = 0; j <5; j++) {
					Group gru = new Group();
					gru.setName(name[j]);
					gru.setIcon(flag[j]);
					gru.setPercentage("91");
					gru.setSectionName("section"+j);

					work_list = new ArrayList<Workout>();

						for (int i = 0; i < 1; i++)
						{


							//treatment count
							JSONArray array = object.getJSONArray("treatment_count");
							for (int k = 0; k < array.length(); k++) {

								workout_count = array.getJSONObject(k).getString("workout_count");
								supplement_count = array.getJSONObject(k).getString("supplement_count");
								lifestyle_count = array.getJSONObject(k).getString("lifestyle_count");
								food_count = array.getJSONObject(k).getString("food_count");
								others_count = array.getJSONObject(k).getString("others_count");
							}

							//over all compliance
							JSONArray overAllCompArr = object.getJSONArray("overall_compliance");
							str_overalll_compliance = overAllCompArr.getJSONObject(0).getInt("overall_compliance");

							//workout

							Workout work;
							JSONArray workout = object.getJSONArray("workout");
							for (int x = 0; x < workout.length(); x++) {
								work = new Workout();
								work.setWorkout_mapping_id(workout.getJSONObject(x).getString("workout_mapping_id"));
								work.setReps(workout.getJSONObject(x).getString("reps"));
								work.setSets(workout.getJSONObject(x).getString("sets"));
								work.setWeight(workout.getJSONObject(x).getString("weight"));
								work.setWorkout_name(workout.getJSONObject(x).getString("workout_name"));
								work.setCompliance(workout.getJSONObject(x).getString("compliance") + "%");
								work.setInt_compliance(workout.getJSONObject(x).getInt("compliance"));
								work.setProgressBarRes(progressBarRes[j]);
								work.setColour(lineColour[j]);
								work_list.add(work);
							}

							// supplement

							Supplement supp = null;
							sup_list = new ArrayList<>();
							JSONArray supplement = object.getJSONArray("supplement");
							for (int x = 0; x < supplement.length(); x++) {
								supp = new Supplement();
								supp.setSupplement_mapping_id(supplement.getJSONObject(x).getString("supplement_mapping_id"));
								supp.setSupplement_name(supplement.getJSONObject(x).getString("supplement_name"));
								supp.setAmount(supplement.getJSONObject(x).getString("amount"));
								supp.setRepitition(supplement.getJSONObject(x).getString("repitition"));
								supp.setCompliance(supplement.getJSONObject(x).getString("compliance") + "%");
								supp.setWhen_time(supplement.getJSONObject(x).getString("when_time"));
								supp.setInt_compliance(supplement.getJSONObject(x).getInt("compliance"));
								supp.setProgressBarRes(progressBarRes[j]);
								supp.setColour(lineColour[j]);
								sup_list.add(supp);
							}

							//Food

							Food food1 = null;
							food_list = new ArrayList<>();
							JSONArray food = object.getJSONArray("food");
							for (int x = 0; x < food.length(); x++) {
								food1 = new Food();
								food1.setFood_mapping_id(food.getJSONObject(x).getString("food_mapping_id"));
								food1.setFood_name(food.getJSONObject(x).getString("food_name"));
								food1.setWhen(food.getJSONObject(x).getString("when"));
								food1.setCompliance(food.getJSONObject(x).getString("compliance") + "%");
								food1.setInt_compliance(food.getJSONObject(x).getInt("compliance"));
								food1.setProgressBarRes(progressBarRes[j]);
								food1.setColour(lineColour[j]);
								food_list.add(food1);
							}

							//lifestyle

							life_list = new ArrayList<>();
							LifeStyle style = null;
							JSONArray lifestyle = object.getJSONArray("lifestyle");
							for (int x = 0; x < lifestyle.length(); x++) {
								style = new LifeStyle();
								style.setLifestyle_mapping_id(lifestyle.getJSONObject(x).getString("lifestyle_mapping_id"));
								style.setLifestyle_name(lifestyle.getJSONObject(x).getString("lifestyle_name"));
								style.setTime(lifestyle.getJSONObject(x).getString("time"));
								style.setRepitition(lifestyle.getJSONObject(x).getString("repitition"));
								style.setWhen(lifestyle.getJSONObject(x).getString("when"));
								style.setCompliance(lifestyle.getJSONObject(x).getString("compliance") + "%");
								style.setInt_compliance(lifestyle.getJSONObject(x).getInt("compliance"));
								style.setProgressBarRes(progressBarRes[j]);
								style.setColour(lineColour[j]);
								life_list.add(style);
							}

							//others
							Others other = null;
							other_list = new ArrayList<>();
							JSONArray others = object.getJSONArray("others");
							for (int x = 0; x < others.length(); x++) {
								other = new Others();
								other.setOthers_mapping_id(others.getJSONObject(x).getString("others_mapping_id"));
								other.setOthers_name(others.getJSONObject(x).getString("others_name"));
								other.setDuration(others.getJSONObject(x).getString("duration"));
								other.setCompliance(others.getJSONObject(x).getString("compliance") + "%");
								other.setInt_compliance(others.getJSONObject(x).getInt("compliance"));
								other.setProgressBarRes(progressBarRes[j]);
								other.setColour(lineColour[j]);
								other_list.add(other);
							}



							JSONArray workout_compliance = object.getJSONArray("workout_compliance");
							gru.setWorkout_compliance(workout_compliance.getJSONObject(0).getInt("workout_compliance"));

							JSONArray supplement_compliance = object.getJSONArray("supplement_compliance");
							gru.setSupplement_compliance(supplement_compliance.getJSONObject(0).getInt("supplement_compliance"));

							JSONArray lifestyle_compliance = object.getJSONArray("lifestyle_compliance");
							gru.setWorkout_compliance(lifestyle_compliance.getJSONObject(0).getInt("lifestyle_compliance"));

							JSONArray food_compliance = object.getJSONArray("food_compliance");
							gru.setWorkout_compliance(food_compliance.getJSONObject(0).getInt("food_compliance"));

							JSONArray others_compliance = object.getJSONArray("others_compliance");
							gru.setWorkout_compliance(others_compliance.getJSONObject(0).getInt("others_compliance"));
						}

					gru.setItems(work_list);
					gru.setFood_Items(food_list);
					gru.setLife_Items(life_list);
					gru.setSup_Items(sup_list);
					gru.setOther_Items(other_list);
					list.add(gru);
				}

			}
			else
			{

			}

		} catch (JSONException j)
		{
			j.printStackTrace();
		}

	}
}

