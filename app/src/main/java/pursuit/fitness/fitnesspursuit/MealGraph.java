package pursuit.fitness.fitnesspursuit;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;
import android.widget.Toast;
import com.jjoe64.graphview.helper.StaticLabelsFormatter;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import java.util.ArrayList;
import java.util.Collections;

public class MealGraph extends AppCompatActivity {
    private Database db;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal_graph);

        TextView t = (TextView)findViewById(R.id.overview);
        t.setMovementMethod(new ScrollingMovementMethod());

        db = new Database(this);

        Bundle i = getIntent().getExtras();
        String selection = i.getString("selection");
        ArrayList<Double> nutrition = new ArrayList<Double>();
        CalendarDay day = new CalendarDay();

        if(day.getDay() >7) {
            nutrition = db.getNutritionGraph(day, selection);
            Toast.makeText(this,"More",Toast.LENGTH_LONG).show();
        }
        else if(day.getDay() <7) {
            String p = 30+"/"+(day.getMonth()-1)+"/"+day.getYear();
            nutrition = db.dateValidation(day, selection);
        }

        com.jjoe64.graphview.GraphView graph = (com.jjoe64.graphview.GraphView) findViewById(R.id.graph);


        // Title and horizontal labels
        StaticLabelsFormatter staticLabelsFormatter = new StaticLabelsFormatter(graph);
        String[] dateArray = getDates(day);
        staticLabelsFormatter.setHorizontalLabels(dateArray);

        graph.getGridLabelRenderer().setLabelFormatter(staticLabelsFormatter);
        double maxData = getMax(nutrition);
        double minData = getMin(nutrition);
        putTitle(selection,graph);
        setDimensions(maxData,minData,graph);

        if(nutrition.size() > 0) {
            LineGraphSeries<DataPoint> series = new LineGraphSeries<>(getDataPoint(nutrition));
            doColors(series);
            graph.addSeries(series);
            t.setText(graph.getTitle() + getNutrition(nutrition,dateArray));
        }
        else
            Toast.makeText(this, R.string.food_no_data_graph, Toast.LENGTH_SHORT).show();
    }


    protected double getMax(ArrayList<Double> nutrition){
        double theMax= Collections.max(nutrition);
        return theMax;
    }


    protected double getMin(ArrayList<Double>nutrition){
        double theMin = Collections.min(nutrition);
        return theMin;
    }

    protected DataPoint[] getDataPoint(ArrayList<Double> nutrition){
        DataPoint[] data = new DataPoint[]{
                new DataPoint(0, nutrition.get(0)),
                new DataPoint(1, nutrition.get(1)),
                new DataPoint(2, nutrition.get(2)),
                new DataPoint(3, nutrition.get(3)),
                new DataPoint(4, nutrition.get(4)),
                new DataPoint(5, nutrition.get(5)),
                new DataPoint(6, nutrition.get(6))
        };
        return data;
    }


    private String[] getDates(CalendarDay today) {
        String date1 = "", date2 = "", date3 = "", date4 = "", date5 = "", date6 = "", date7 = "";
        if (today.getDay() > 7) {
            date1 = today.getDay() + "";
            date2 = today.getDay() - 1 + "";
            date3 = today.getDay() - 2 + "";
            date4 = today.getDay() - 3 + "";
            date5 = today.getDay() - 4 + "";
            date6 = today.getDay() - 5 + "";
            date7 = today.getDay() - 6 + "";
        } else if (today.getDay() == 1) {
            date1 = today.getDay() + "";
            if (today.getDay() - 1 == 0 && today.getMonth() == 0 || today.getMonth() == 2 ||
                    today.getMonth() == 4 || today.getMonth() == 6 || today.getMonth() == 7
                    || today.getMonth() == 9 || today.getMonth() == 11) {
                CalendarDay Newday = new CalendarDay(today.getYear(), today.getMonth() - 1, 30);
                date2 = Newday.getDay() + "";
                date3 = Newday.getDay() - 1 + "";
                date4 = Newday.getDay() - 2 + "";
                date5 = Newday.getDay() - 3 + "";
                date6 = Newday.getDay() - 4 + "";
                date7 = Newday.getDay() - 5 + "";

            } else if (today.getDay() - 1 == 0 && today.getMonth() == 3 || today.getMonth() == 5
                    || today.getMonth() == 8 || today.getMonth() == 10) {
                CalendarDay Newday = new CalendarDay(today.getYear(), today.getMonth() - 1, 31);
                date2 = Newday.getDay() + "";
                date3 = Newday.getDay() - 1 + "";
                date4 = Newday.getDay() - 2 + "";
                date5 = Newday.getDay() - 3 + "";
                date6 = Newday.getDay() - 4 + "";
                date7 = Newday.getDay() - 5 + "";
            } else if (today.getDay() - 1 == 0 && today.getMonth() == 1) {
                CalendarDay Newday = new CalendarDay(today.getYear(), today.getMonth() - 1, 28);
                date2 = Newday.getDay() + "";
                date3 = Newday.getDay() - 1 + "";
                date4 = Newday.getDay() - 2 + "";
                date5 = Newday.getDay() - 3 + "";
                date6 = Newday.getDay() - 4 + "";
                date7 = Newday.getDay() - 5 + "";
            }


        } else if (today.getDay() == 2) {
            date1 = today.getDay() + "";
            date2 = today.getDay() - 1 + "";
            if (today.getDay() - 2 == 0 && today.getMonth() == 0 || today.getMonth() == 2 ||
                    today.getMonth() == 4 || today.getMonth() == 6 || today.getMonth() == 7
                    || today.getMonth() == 9 || today.getMonth() == 11) {
                CalendarDay Newday = new CalendarDay(today.getYear(), today.getMonth() - 1, 31);

                date3 = Newday.getDay() + "";
                date4 = Newday.getDay() - 1 + "";
                date5 = Newday.getDay() - 2 + "";
                date6 = Newday.getDay() - 3 + "";
                date7 = Newday.getDay() - 4 + "";

            } else if (today.getDay() - 2 == 0 && today.getMonth() == 3 || today.getMonth() == 5
                    || today.getMonth() == 8 || today.getMonth() == 10) {
                CalendarDay Newday = new CalendarDay(today.getYear(), today.getMonth() - 1, 30);

                date3 = Newday.getDay() + "";
                date4 = Newday.getDay() - 1 + "";
                date5 = Newday.getDay() - 2 + "";
                date6 = Newday.getDay() - 3 + "";
                date7 = Newday.getDay() - 4 + "";
            } else if (today.getDay() - 2 == 0 && today.getMonth() == 1) {
                CalendarDay Newday = new CalendarDay(today.getYear(), today.getMonth() - 1, 28);

                date3 = Newday.getDay() + "";
                date4 = Newday.getDay() - 1 + "";
                date5 = Newday.getDay() - 2 + "";
                date6 = Newday.getDay() - 3 + "";
                date7 = Newday.getDay() - 4 + "";
            }


        } else if (today.getDay() == 3) {
            date1 = today.getDay() + "";
            date2 = today.getDay() - 1 + "";
            date3 = today.getDay() - 2 + "";
            if (today.getDay() - 3 == 0 && today.getMonth() == 0 || today.getMonth() == 2 ||
                    today.getMonth() == 4 || today.getMonth() == 6 || today.getMonth() == 7
                    || today.getMonth() == 9 || today.getMonth() == 11) {
                CalendarDay Newday = new CalendarDay(today.getYear(), today.getMonth() - 1, 31);


                date4 = Newday.getDay() + "";
                date5 = Newday.getDay() - 1 + "";
                date6 = Newday.getDay() - 2 + "";
                date7 = Newday.getDay() - 3 + "";

            } else if (today.getDay() - 3 == 0 && today.getMonth() == 3 || today.getMonth() == 5
                    || today.getMonth() == 8 || today.getMonth() == 10) {
                CalendarDay Newday = new CalendarDay(today.getYear(), today.getMonth() - 1, 30);


                date4 = Newday.getDay() + "";
                date5 = Newday.getDay() - 1 + "";
                date6 = Newday.getDay() - 2 + "";
                date7 = Newday.getDay() - 3 + "";
            } else if (today.getDay() - 3 == 0 && today.getMonth() == 1) {
                CalendarDay Newday = new CalendarDay(today.getYear(), today.getMonth() - 1, 28);


                date4 = Newday.getDay() + "";
                date5 = Newday.getDay() - 1 + "";
                date6 = Newday.getDay() - 2 + "";
                date7 = Newday.getDay() - 3 + "";
            }

        } else if (today.getDay() == 4) {
            date1 = today.getDay() + "";
            date2 = today.getDay() - 1 + "";
            date3 = today.getDay() - 2 + "";
            date4 = today.getDay() - 3 + "";
            if (today.getDay() - 4 == 0 && today.getMonth() == 0 || today.getMonth() == 2 ||
                    today.getMonth() == 4 || today.getMonth() == 6 || today.getMonth() == 7
                    || today.getMonth() == 9 || today.getMonth() == 11) {
                CalendarDay Newday = new CalendarDay(today.getYear(), today.getMonth() - 1, 31);


                date5 = Newday.getDay() + "";
                date6 = Newday.getDay() - 1 + "";
                date7 = Newday.getDay() - 2 + "";

            } else if (today.getDay() - 4 == 0 && today.getMonth() == 3 || today.getMonth() == 5
                    || today.getMonth() == 8 || today.getMonth() == 10) {
                CalendarDay Newday = new CalendarDay(today.getYear(), today.getMonth() - 1, 30);


                date5 = Newday.getDay() + "";
                date6 = Newday.getDay() - 1 + "";
                date7 = Newday.getDay() - 2 + "";
            } else if (today.getDay() - 4 == 0 && today.getMonth() == 1) {
                CalendarDay Newday = new CalendarDay(today.getYear(), today.getMonth() - 1, 28);


                date5 = Newday.getDay() + "";
                date6 = Newday.getDay() - 1 + "";
                date7 = Newday.getDay() - 2 + "";
            }

        } else if (today.getDay() == 5) {
            date1 = today.getDay() + "";
            date2 = today.getDay() - 1 + "";
            date3 = today.getDay() - 2 + "";
            date4 = today.getDay() - 3 + "";
            date5 = today.getDay() - 4 + "";
            if (today.getDay() - 5 == 0 && today.getMonth() == 0 || today.getMonth() == 2 ||
                    today.getMonth() == 4 || today.getMonth() == 6 || today.getMonth() == 7
                    || today.getMonth() == 9 || today.getMonth() == 11) {
                CalendarDay Newday = new CalendarDay(today.getYear(), today.getMonth() - 1, 31);


                date6 = Newday.getDay() + "";
                date7 = Newday.getDay() - 1 + "";

            } else if (today.getDay() - 5 == 0 && today.getMonth() == 3 || today.getMonth() == 5
                    || today.getMonth() == 8 || today.getMonth() == 10) {
                CalendarDay Newday = new CalendarDay(today.getYear(), today.getMonth() - 1, 30);


                date6 = Newday.getDay() + "";
                date7 = Newday.getDay() - 1 + "";
            } else if (today.getDay() - 5 == 0 && today.getMonth() == 1) {
                CalendarDay Newday = new CalendarDay(today.getYear(), today.getMonth() - 1, 28);


                date6 = Newday.getDay() + "";
                date7 = Newday.getDay() - 1 + "";
            }

        } else if (today.getDay() == 6) {
            date1 = today.getDay() + "";
            date2 = today.getDay() - 1 + "";
            date3 = today.getDay() - 2 + "";
            date4 = today.getDay() - 3 + "";
            date5 = today.getDay() - 4 + "";
            date6 = today.getDay() - 5 + "";
            if (today.getDay() - 6 == 0 && today.getMonth() == 0 || today.getMonth() == 2 ||
                    today.getMonth() == 4 || today.getMonth() == 6 || today.getMonth() == 7
                    || today.getMonth() == 9 || today.getMonth() == 11) {
                CalendarDay Newday = new CalendarDay(today.getYear(), today.getMonth() - 1, 31);
                date7 = Newday.getDay() + "";

            } else if (today.getDay() - 6 == 0 && today.getMonth() == 3 || today.getMonth() == 5
                    || today.getMonth() == 8 || today.getMonth() == 10) {
                CalendarDay Newday = new CalendarDay(today.getYear(), today.getMonth() - 1, 30);
                date7 = Newday.getDay() + "";
            } else if (today.getDay() - 6 == 0 && today.getMonth() == 1) {
                CalendarDay Newday = new CalendarDay(today.getYear(), today.getMonth() - 1, 28);
                date7 = Newday.getDay() + "";
            } else if (today.getDay() == 6) {
                date1 = today.getDay() + "";
                date2 = today.getDay() - 1 + "";
                date3 = today.getDay() - 2 + "";
                date4 = today.getDay() - 3 + "";
                date5 = today.getDay() - 4 + "";
                date6 = today.getDay() - 5 + "";
                date7 = today.getDay() - 6 + "";
            }

        }
        String[] graphDates = {date7, date6, date5, date4, date3, date2, date1};
        return graphDates;
    }


    //set the title

    protected void putTitle(String selection, com.jjoe64.graphview.GraphView graph){
        if(selection.equals("calorie_count"))
            graph.setTitle(getString(R.string.food_calorie_graph));
        else if ((selection.equals("protein_count")))
            graph.setTitle(getString(R.string.food_protein_graph));
        else if ((selection.equals("carb_count")))
            graph.setTitle(getString(R.string.food_carbohydrate_graph));
        else if ((selection.equals("fat_count")))
            graph.setTitle(getString(R.string.food_fats_graph));
    }


    //graph properties

    protected void doColors(LineGraphSeries<DataPoint> series){
        //Coloured Dots
        series.setColor(Color.rgb(0,161,241));
        series.setDrawDataPoints(true);
        series.setDataPointsRadius(10);
        series.setThickness(8);
        //Color background
        series.setDrawBackground(true);
        series.setBackgroundColor(Color.rgb(158,207,232));
    }

    protected void setDimensions(double maxData, double minData, com.jjoe64.graphview.GraphView graph){
        graph.getViewport().setYAxisBoundsManual(true);
        graph.getViewport().setMinY(0);
        graph.getViewport().setMaxY(maxData);

        graph.getViewport().setXAxisBoundsManual(true);
        graph.getViewport().setMinX(0);
        graph.getViewport().setMaxX(6);

        // enable scaling and scrolling
        graph.getViewport().setScalable(false);
        graph.getViewport().setScalableY(true);
    }


    // text beneath graph

    private String getNutrition(ArrayList<Double> nutrition,String []dateArray) {
        String output = getString(R.string.food_today) + String.valueOf(nutrition.get(6))
                + "\n" + dateArray[5] + ":\t" + String.valueOf(nutrition.get(5))
                + "\n" + dateArray[4] + ":\t" + String.valueOf(nutrition.get(4))
                + "\n" + dateArray[3] + ":\t" + String.valueOf(nutrition.get(3))
                + "\n" + dateArray[2] + ":\t" + String.valueOf(nutrition.get(2))
                + "\n" + dateArray[1] + ":\t" + String.valueOf(nutrition.get(1))
                + "\n" + dateArray[0] + ":\t" + String.valueOf(nutrition.get(0));
        return output;
    }
}