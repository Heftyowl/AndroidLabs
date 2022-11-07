package algonquin.cst2335.lab4;

import static java.lang.Character.isDigit;
import static java.lang.Character.isLowerCase;
import static java.lang.Character.isUpperCase;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Bundle;

import algonquin.cst2335.lab4.R;
import algonquin.cst2335.lab4.MainViewModel;


public class MainActivity extends AppCompatActivity {

    private  MainViewModel model;
    //private ActivityMainBinding variableBinding;

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        model = new ViewModelProvider(this).get(MainViewModel.class);
        setContentView(R.layout.activity_main);

        TextView logInTextView = findViewById(R.id.textView3);
        EditText logInEditText = findViewById(R.id.editTextTextPassword);
        Button logInButton = findViewById(R.id.button);

        logInButton.setOnClickListener(clk -> {
            String password = logInEditText.getText().toString();

            checkPasswordComplexity(password);
            if(checkPasswordComplexity(password) == false){
                logInTextView.setText("BAD PASSWORD");
            }else{
                logInTextView.setText("Valid Password.");
            }
        });
        //Log.w( "MainActivity", "In onCreate() - Loading Widgets" );
    }


    boolean checkPasswordComplexity(String password){
        int passLen = password.length();
        boolean foundUpperCase = false;
        boolean foundLowerCase = false;
        boolean foundNumber = false;
        boolean foundSpecial = false;
        for(int i = 0; i < passLen; i++){
            Character c = password.charAt(i);
            if(isUpperCase(c)){
                foundUpperCase = true;
            }
            else if(isLowerCase(c)){
                foundLowerCase = true;
            }
            else if(isDigit(c)){
                foundNumber = true;
            }
            else if(isSpecialCharacter(c)){
                foundSpecial = true;
            }
        }
        if(passLen < 8){
            Toast.makeText(getApplicationContext(), "Password must be at least 8 characters long", Toast.LENGTH_SHORT).show();
            return false;
        }
        else if(!foundUpperCase){
            Toast.makeText(getApplicationContext(), "Password must contain an upper case letter", Toast.LENGTH_SHORT).show();
            return false;
        }
        else if(!foundLowerCase){
            Toast.makeText(getApplicationContext(), "Password must contain a lower case letter", Toast.LENGTH_SHORT).show();
            return false;
        }
        else if(!foundNumber){
            Toast.makeText(getApplicationContext(), "Password must contain a number", Toast.LENGTH_SHORT).show();
            return false;
        }
        else if(!foundSpecial){
            Toast.makeText(getApplicationContext(), "Password must contain a special character", Toast.LENGTH_SHORT).show();
            return false;
        }
        else{
            Toast.makeText(getApplicationContext(), "Valid Password.", Toast.LENGTH_SHORT).show();
            return true;
        }
    }

    boolean isSpecialCharacter(char c) {
        switch (c) {
            case '#':
            case '$':
            case '%':
            case '&':
            case '*':
            case '?':
            case '!':
            case '@':
            case '^':
                return true;
            default:
                return false;
        }

    }
}