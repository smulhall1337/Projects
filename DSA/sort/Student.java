package sort;

/**
 * Represents a Student object. Students have a name(String), a SSN(String), and a GPA(float)
 * used for a lab
 * @author (Sean Mulhall) 
 * @version (10.8.2014)
 */
public class Student
implements Comparable
{
    String name;
    String ssn; 
    float gpa = (float) 0.0;
    public Student(String name, String ssn)
    {
        this.name = name;
        this.ssn = ssn;
    }

    public String toString()
    {
        return name+" SSN: "+ssn;
    }

    public int compareTo(Object object)
    {
        Student otherStudent = (Student) object;

        if(name.equals(otherStudent.name))
        {
            return ssn.compareTo(otherStudent.ssn);
        }
        return name.compareTo(otherStudent.name);
    }

    public int hashCode()
    {
        String result = name + ssn;
        return result.hashCode();
    }

    public boolean equals(Object object)
    {
        if (object instanceof Student)
        {
            if (hashCode() == object.hashCode())
            {
                return true;
            }
        }
        return false;
    }
}

