package com.example.calculator_app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "CalculatorActivity";

    private Calculator calculator;
    private EditText operand1;
    private EditText operand2;

    private TextView resultText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        calculator = new Calculator();
        operand1 = findViewById(R.id.operand_one_edit_text);
        operand2 = findViewById(R.id.operand_two_edit_text);
        resultText = findViewById(R.id.operation_result_text_view);
    }

    public void onAdd(View view) {
        compute(Calculator.Operator.ADD);
    }

    private void compute(Calculator.Operator operator) {

        double operandOne;
        double operandTwo;
        try{
            operandOne = getOperand(operand1);
            operandTwo = getOperand(operand2);
        }
        catch (NumberFormatException nfe){
            Log.e(TAG,"numberFormatException",nfe);
            resultText.setText(getString(R.string.computationError));
            return;
        }

        String result;
        switch (operator){
            case ADD:
                result = String.valueOf(calculator.add(operandOne,operandTwo));
                break;
            case DIV:
                result = String.valueOf(calculator.div(operandOne,operandTwo));
                break;
            case SUB:
                result = String.valueOf(calculator.sub(operandOne,operandTwo));
                break;
            case MUL:
                result = String.valueOf(calculator.mul(operandOne,operandTwo));
                break;
            default:
                result = getString(R.string.computationError);
                break;
        }
        resultText.setText(result);
    }

    public void onSub(View view) {
        compute(Calculator.Operator.SUB);
    }

    public void onDiv(View view) {
        compute(Calculator.Operator.DIV);

    }

    public void onMul(View view) {
        compute(Calculator.Operator.MUL);

    }

    private static Double getOperand(EditText operandEditText) {
        String operandText = getOperandText(operandEditText);
        return Double.valueOf(operandText);
    }

    private static String getOperandText(EditText text){
        String operandText = text.getText().toString();
        if (TextUtils.isEmpty(operandText)) {
            throw new NumberFormatException("Operand cannot be empty!");
        }
        return operandText;
    }


}
