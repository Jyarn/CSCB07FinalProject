# Course_Student
Stores course information relating to a student

Fields:
String ddbRef - a link in the database to general course information (prerequisites, session dates...)
boolean completed - whether the student has completed the course or not


# Run
Test file (to be deleted)


# Student inherits from User
Stores student information

Functions:
public Student
String url - root of the database
String username - username of the user to be initialized


public void register (String password) - register a student in the database
String password - password to be added to the database (to be deleted)


public boolean push () - Overwrite student courses in the database
returns true if push was successful


public void addCourse (Course_Student course) - add course to list of courses. No checks are made simply adds a course


public boolean pull () - updates courses
returns true if successful


# User
public boolean login (String password) - logs in a user (to be deleted?)
public void register (String password) - registers a user in the database
String password - password to be registered (to be deleted)

public boolean isRegistered () - returns true if user is registered in the database

public abstract boolean pull ()
public abstract boolean push ()