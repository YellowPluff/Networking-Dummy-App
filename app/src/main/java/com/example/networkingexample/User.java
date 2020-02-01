package com.example.networkingexample;

public class User {
    public String email;
    public String password;
    public String name;
    public String major;

    public User()
    {

    }

    public User(String email, String password, String name, String major)
    {
        this.email = email;
        this.password = password;
        this.name = name;
        this.major = major;
    }

    public String formatEmail()
    {
        String formattedEmail = this.email;
        char[] badChars = {'.', '#', '$', '[', ']',};
        for (char c : badChars)
        {
            if(formattedEmail.contains(c+""))
            {
                formattedEmail = formattedEmail.replace(c+"", "");
            }
        }
        return formattedEmail;
    }

}
