package com.example.table_list_widget;

import android.content.Context;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Tasks {
    private static Tasks instance;
    private ArrayList<String> tasks;
    private Context context;
    private String filename = "tasks";

    private Tasks(Context contextIn){
        context = contextIn;
        tasks = new ArrayList<>();

        try{
            FileInputStream fis = context.openFileInput(filename);
            InputStreamReader inputStreamReader = new InputStreamReader(fis);
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                tasks.add(line);
                line = reader.readLine();
            }
        } catch (FileNotFoundException e){
            File file = new File(context.getFilesDir(), filename);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Tasks getInstance(Context context){
        if(instance == null){
            instance = new Tasks(context);
        }
        return instance;
    }

    public ArrayList<String> getTasks(){
        if(tasks == null){
            tasks = new ArrayList<>();
        }
        return tasks;
    }

    public void addTask(String newTask){
        tasks.add(newTask);
        write();
    }

    public ArrayList<String> removeTask(ArrayList<Integer> rem){

        if(!tasks.isEmpty() && !rem.isEmpty()) {
            for (int i = 0; i < rem.size(); i++) {
                tasks.set(rem.get(i), "");
            }
            ArrayList<String> temp = new ArrayList<>();
            for (int i = 0; i < tasks.size(); i++) {
                if (!tasks.get(i).isEmpty()) {
                    temp.add(tasks.get(i));
                }
            }
            tasks = temp;
            write();
        }
        return tasks;
    }

    public void write(){
        try{
            FileOutputStream fos = context.openFileOutput(filename, Context.MODE_PRIVATE);
            for(int i=0; i<tasks.size(); i++){
                fos.write((tasks.get(i)+ "\n").getBytes());
            }

        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
