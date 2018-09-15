
public class UserInfo
{

// instance variables
   private String firstName;
   private String lastName;
   private String location;
   private int age;
   private int status;
   private static final int OFFLINE = 0, ONLINE = 1; 
      
//constructor
   public UserInfo(String firstNameIn, String lastNameIn) 
   // Uppercase all starting letters
   {
      firstName = firstNameIn;
      lastName = lastNameIn;
      location = "Not specified";
      age = 0;
      status = OFFLINE;
   } 
  
    
  
  
   
 //methods 
   public String toString()
   
   {
      String output = "Name: " + firstName + " " 
         + lastName + "\n";
      output += "Location: " + location + "\n";
      output += "Age: " + age + "\n";
      output += "Status: " ; 
      if (status == OFFLINE)
      {
         output += "Offline";   
      }
      else
      {
         output += "Online"; 
      }
      return output;
   
   }
   
   public void setLocation(String locationIn)
   {
      location = locationIn;
   }

   
   public boolean setAge(int ageIn)
   {
      boolean isSet = false;
      if (ageIn > 0)
      {
         age = ageIn;
         isSet = true;
      }
      
      return isSet;
   }
   
   public int getAge()
   {
      return age;
   }
   
   
   public String getLocation()
   {
      return location;
   }
   
   public void logOff()
   {
      status = OFFLINE;
   }
   
   public void logOn()
   {
      status = ONLINE;
   }
  
   
}