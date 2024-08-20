package com.project.learningmanagemtsystem.validations;

public class ValidationImplementaion {
    public static boolean mobileNumberValidation(String mobileNum){
        if (mobileNum.length() == 10 && !mobileNum.startsWith("0")){
            return !mobileNum.matches("\\d+");
        } else if (mobileNum.startsWith("0") && mobileNum.length() == 11) {
            String extractedNumber = mobileNum.substring(1);
            return !extractedNumber.matches("\\d+");

        } else if (mobileNum.startsWith("+91") && mobileNum.length() == 13) {
            String extractedNumber = mobileNum.substring(3);
            return !extractedNumber.matches("\\d+");

        } else {
            return true;
        }
    }
    public static boolean emailValidation(String email){
        String emailRegex = "^[a-zA-Z0-9]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        return !email.matches(emailRegex);

    }
}
