package scouting.training;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.appcompat.app.AppCompatActivity;

public class Sample extends AppCompatActivity {
    private Button sampleButton;
    private TextView sampleText;
    private ToggleButton sampleToggle;
    private EditText sampleEdit;
    private String sampleTextString;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*
        The code surrounding before comment is needed in pretty much every class you'll be making, since it initializes it as a file the app CAN use
        Before I teach you java code, take a quick look at manifests/AndroidManifest.xml - any and all java files that are connected to UI MUST be there as actvities
        The main file (Curretly listed as Sample.java) is what is ran on app launch; though in the main scouting app you shouldn't need to touch that.
        The other files, such as how CalculatorActivity.java is initialied - will be how you setup your files
        When you're done playing with the samples, the first thing you need to do is edit manifest for CalculatorActivity to be the main file
         */

        setContentView(R.layout.sample); //This is how you choose the xml file your java class will connect to, one of the first things you setup

        //Using the IDs set in your Ui files, you connect them with: element = findViewById(R.id.element_id)
        sampleButton = findViewById(R.id.button);
        sampleText = findViewById(R.id.textview);
        sampleToggle = findViewById(R.id.toggleButton);
        sampleEdit = findViewById(R.id.editText);

        //Buttons are unique in having changeable text, usually good tasks such as counters
        //Simple button click listener
        sampleButton.setOnClickListener(v -> {
            //Code written in these brackets are executed when the button is clicked
            //This is an example of Toast, a handy tool with Android where we can quickly display a message to the user
            Toast.makeText(this, "Button Clicked", Toast.LENGTH_SHORT).show();
        });

        //Toggle buttons are unique in.. being toggled, meaning you can call if they're active or not with .isChecked(), or set if they're active with .setChecked(boolean);
        sampleToggle.setOnClickListener(v -> {
                if (sampleToggle.isChecked()) System.out.println("Toggled");
                else System.out.println("Untoggled");
        });

        //We don't usually use text views as an on-click listener, but I'm doing it for the sake of simplicity
        sampleText.setOnClickListener(v -> {
            sampleText.setText( //Textview are best at displaying data, usually used similar to labels and titles - this is how you code what they display
                    sampleEdit.getText() //Edit texts are usually used to write in, such as for comments - this is how you parse it as a string to be saved elsewhere
            );
        });

    }
}
