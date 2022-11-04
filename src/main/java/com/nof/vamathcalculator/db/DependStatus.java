package com.nof.vamathcalculator.db;

public enum DependStatus {
    // With a dependent spouse or parent, but no children
    Alone_No_Depends("Basic veteran alone with no dependents"),
    Spouse_No_Depends("I have spouse and no parents or children"),
    Spouse_One_Parent("I have spouse and one dependent parent"),
    Spouse_Two_Parents("I have spouse and two dependent parents"),
    One_Parent("I have no spouse or children and one dependent parent"),
    Two_Parents("I have no spouse or children and two dependent parents"),

    // With dependents, including children
    Child_Only("I have at least 1 child and no dependent spouse or parents"),
    Child_and_Spouse("I have at least 1 child and a spouse"),
    Child_Spouse_One_Parent("I have at least 1 child, a spouse, and one dependent parent"),
    Child_Spouse_Two_Parents("I have at least 1 child, a spouse, and two dependent parents"),
    Child_One_Parent("I have at least 1 child and one parent but no spouse"),
    Child_Two_Parents("I have at least 1 child and two parents but no spouse"),

    // Additional amounts
    Spouse_Aid_Attendance("I requires help with daily activities"),
    Additional_Child("I have more than one child under 18"),
    Child_Education("I have at least one child in qualifying school program");
    private final String m_status;

    DependStatus(String status) {
        m_status = status;
    }

    public String getStatus() {
        return m_status;
    }
}
