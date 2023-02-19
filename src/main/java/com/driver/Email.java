package com.driver;

public class Email {

    private String emailId;
    private String password;

    public Email(String emailId){
        this.emailId = emailId;
        this.password = "Accio@123";
    }

    public Email()
    {
    }

    public String getEmailId() {
        return emailId;
    }

    public String getPassword() {
        return password;
    }

    public void changePassword(String oldPassword, String newPassword)
    {
        //Change password only if the oldPassword is equal to current password and the new password meets all of the following:
        // 1. It contains at least 8 characters
        // 2. It contains at least one uppercase letter
        // 3. It contains at least one lowercase letter
        // 4. It contains at least one digit
        // 5. It contains at least one special character. Any character apart from alphabets and digits is a special character

        if(this.password.equals(oldPassword) && isValid(newPassword) && newPassword.length()>=8)
        {
            this.password = newPassword;
        }
    }
    public boolean isValid(String s)
    {
        return containsUppercase(s) && containsDigit(s) && containsLowerCase(s) && containsSpecChar(s);
    }
    public boolean containsUppercase(String s)
    {
        for(int i=0; i<s.length(); i++)
        {
            char c = s.charAt(i);
            if(Character.isUpperCase(c)) return true;
        }
        return false;
    }
    public boolean containsLowerCase(String s)
    {
        for(int i=0; i<s.length(); i++)
        {
            char c = s.charAt(i);
            if(Character.isLowerCase(c)) return true;
        }
        return false;
    }
    public boolean containsDigit(String s)
    {
        for(int i=0; i<s.length(); i++)
        {
            char c = s.charAt(i);
            if(Character.isDigit(c)) return true;
        }
        return false;
    }
    public boolean containsSpecChar(String s)
    {
        for(int i=0; i<s.length(); i++)
        {
            char c = s.charAt(i);
            if(!Character.isLetterOrDigit(c)) return true;
        }
        return false;
    }

    public void setEmailId(String emailId)
    {
        this.emailId = emailId;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }
}
