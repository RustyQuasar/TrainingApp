package scouting.training;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class CalculatorActivity extends AppCompatActivity {

    private Button clear, backspace, exponent, divide, one, two, three, multiply, four, five, six, add, seven, eight, nine, subtract, invert, zero, period, equals;
    private Button[] numPad, operatorPad;
    private String firstNumber = "", secondNumber = "";
    private int operatorIndex = -1;
    private TextView numberDisplay;
    private boolean continuedEquation = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calculator_activity);

        clear = findViewById(R.id.clear);
        backspace = findViewById(R.id.backspace);
        exponent = findViewById(R.id.exponent);
        divide = findViewById(R.id.divide);
        one = findViewById(R.id.one);
        two = findViewById(R.id.two);
        three = findViewById(R.id.three);
        multiply = findViewById(R.id.multiply);
        four = findViewById(R.id.four);
        five = findViewById(R.id.five);
        six = findViewById(R.id.siz);
        add = findViewById(R.id.add);
        seven = findViewById(R.id.seven);
        eight = findViewById(R.id.eight);
        nine = findViewById(R.id.nine);
        subtract = findViewById(R.id.subtract);
        invert = findViewById(R.id.invert);
        zero = findViewById(R.id.zero);
        period = findViewById(R.id.period);
        equals = findViewById(R.id.equals);
        numberDisplay = findViewById(R.id.numberDisplay);
        period = findViewById(R.id.period);
        invert = findViewById(R.id.invert);

        numPad = new Button[]{one, two, three, four, five, six, seven, eight, nine, zero, period};
        operatorPad = new Button[]{divide, multiply, add, subtract, exponent};

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (secondNumber.isEmpty()) {
                    firstNumber = "";
                    operatorIndex = -1;
                } else {
                    secondNumber = "";
                }
                updateNumberDisplay();
            }
        });
        backspace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (operatorIndex == -1) {
                    if (!firstNumber.isEmpty())
                        firstNumber = firstNumber.substring(0, firstNumber.length() - 1);
                } else {
                    if (!secondNumber.isEmpty())
                        secondNumber = secondNumber.substring(0, secondNumber.length() - 1);
                    else operatorIndex = -1;
                }
                updateNumberDisplay();
            }
        });
        for (Button button : numPad) {
            //System.out.println("Initialized numPad button " + button.getText());
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (continuedEquation) {
                        continuedEquation = false;
                        firstNumber = "";
                        operatorIndex = -1;
                    }
                    if (operatorIndex == -1) {
                        firstNumber += button.getText();
                    } else {
                        secondNumber += button.getText();
                    }
                    updateNumberDisplay();
                }
            });
        }
        for (int i = 0; i < operatorPad.length; i++) {
            int finalI = i;
            //System.out.println("Initialized operator index " + i + " named " + operatorPad[i].getText());
            operatorPad[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (continuedEquation) continuedEquation = false;
                    if (secondNumber.isEmpty()) {
                        operatorIndex = finalI;
                    } else {
                        calculate(finalI);
                        secondNumber = "";
                    }
                    //System.out.println("Clicked " + operatorPad[finalI].getText() + " at index " + finalI);
                    updateNumberDisplay();
                }
            });
        }
        equals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (calculate(-1)) continuedEquation = true;
                updateNumberDisplay();
            }
        });
        invert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (operatorIndex == -1) {
                    firstNumber = String.valueOf(Double.parseDouble(firstNumber) * -1).replaceAll("\\.0$", "");
                } else {
                    secondNumber = String.valueOf(Double.parseDouble(secondNumber) * -1).replaceAll("\\.0$", "");
                }
                updateNumberDisplay();
            }
        });

    }

    private void updateNumberDisplay() {
        if (operatorIndex == -1) {
            numberDisplay.setText(firstNumber);
            secondNumber = "";
        } else {
            numberDisplay.setText(String.format("%s %s %s", firstNumber, operatorPad[operatorIndex].getText(), secondNumber));
        }
    }

    private boolean calculate(int newIndex) {
        if (firstNumber.isEmpty() || secondNumber.isEmpty()) return false;
        double firstNum, secondNum, result;
        try {
            firstNum = Double.parseDouble(firstNumber);
            secondNum = Double.parseDouble(secondNumber);
        } catch (Exception e) {
            Toast.makeText(this, "Number out of bounds", Toast.LENGTH_SHORT).show();
            return false;
        }
        switch (operatorIndex) {
            case 0:
                result = firstNum / secondNum;
                break;
            case 1:
                result = firstNum * secondNum;
                break;
            case 2:
                result = firstNum + secondNum;
                break;
            case 3:
                result = firstNum - secondNum;
                break;
            case 4:
                result = Math.pow(firstNum, secondNum);
                break;
            default: return false;
        }
        if (result == Double.POSITIVE_INFINITY || result == Double.NEGATIVE_INFINITY || Double.isNaN(result)) {
            Toast.makeText(this, "Number out of bounds", Toast.LENGTH_SHORT).show();
            return false;
        }
        firstNumber = String.valueOf(result).replaceAll("\\.0$", "");
        operatorIndex = newIndex;
        return true;
    }

    /**
     * Populates the UI with data from the ScoutingForm.
     *
     * @param form The ScoutingForm object to populate the UI.
     */


}
