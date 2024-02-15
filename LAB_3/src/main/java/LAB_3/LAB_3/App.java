package LAB_3.LAB_3;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
public class App 
{
	 public static void main( String[] args ) throws ClassNotFoundException, SQLException
	    {
		    Class.forName("com.mysql.cj.jdbc.Driver");

			String url = "jdbc:mysql://localhost:3306/bca_students";
			String username = "root";
			String pwd = "12345";
			Connection con = DriverManager.getConnection(url, username, pwd);
			Scanner sc = new Scanner(System.in);
			App stu = new App();
			while(true)
			{
				System.out.println("\nChoose the operation you want to perform :");
		        System.out.println("1. Create table");
		        System.out.println("2. Delete table");
		        System.out.println("3. Reading column from table");
		        System.out.println("4. Select records based on gender");
		        System.out.println("5. Select records based on marks");
		        System.out.println("6. Select records based on initial characters of the student's name");
		        System.out.println("7. Display no. of rows by sql aggregrate function count()");
		        System.out.println("8. Select highest marks student by sql aggregate function max()");
		        System.out.println("9. Select the student with nth highest marks");
		        System.out.println("10.Exit");
		        System.out.println("\nPlease enter your choice :");
		        
		        int choice = sc.nextInt();
		        
		        switch (choice) {
	            case 1:
	            	System.out.println("\nCreating a table(can only be done once)......\n");
	                stu.create_table(con);
	                break;
	            case 2:
	            	System.out.println("\nDeleting an existing table...........\n");
	                stu.delete_table(con);
	                break;
	            case 3:
	            	System.out.println("\nPerforming READ operation......\n");
	                stu.select_column(con);
	                break;
	            case 4:
	            	System.out.println("\nPerforming READ operation......\n");
	                stu.select_gender_coloumn(con,sc);
	                break;
	            case 5:
	            	System.out.println("\nPerforming READ operation......\n");
	            	stu.select_marks_coloumn(con,sc);
	                break;
	            case 6:
	            	System.out.println("\nPerforming READ operation......\n");
	            	stu.select_initial_charcacter_of_name(con,sc);
	                break;
	            case 7:
	            	System.out.println("\nPerforming READ operation......\n");
	                stu.count_function(con);
	                break;
	            case 8:
	            	System.out.println("\nPerforming READ operation......\n");
	                stu.max_function(con);
	                break;
	            case 9:
	            	System.out.println("\nPerforming READ operation......\n");
	                stu.nth_highest(con,sc);
	                break;
	            case 10:
	            	System.out.println("\nExiting......\n");
	            	sc.close();
	            	System.exit(0);
	                break;
	            default:
	                System.out.println("\nInvalid choice\n");
	        }

			}

		    
	    }
	    public void create_table(Connection con) throws SQLException 
	    {
	    	Statement st = con.createStatement();
	    	String query = "Create Table itstudents(id int, name varchar(50), address varchar(50))";
	    	int rows = st.executeUpdate(query);
	    	System.out.println(rows + " Table(s) created successfully!!!");
	    }  
	    public void delete_table(Connection con) throws SQLException 
	    {
	    	Statement st = con.createStatement();
	        String query = "drop table itstudents";
	        int rows = st.executeUpdate(query);
	    	System.out.println(rows + " Table(s) deleted successfully!!!");
	    }  
	    public void select_column(Connection con) throws SQLException 
	    {
	    	Statement st = con.createStatement();
	    	ResultSet rs = st.executeQuery("select name,age,marks from bca_student");
	    	while(rs.next()) {
				System.out.println(rs.getString(1)+"      " + rs.getInt(2) + "       " + rs.getDouble(3));
			}
	    }  
	    public void select_gender_coloumn(Connection con,Scanner sc) throws SQLException 
	    {
	    	Statement st = con.createStatement();
	    	System.out.println("Enter the student's gender: ");
			String gender = sc.next();
	    	String query = String.format("select * from bca_student where gender = '%s'",gender);
	    	ResultSet rs = st.executeQuery(query);
	    	while(rs.next())
	    	{
	    		System.out.println(rs.getInt(1)+"    " + rs.getString(2) + "  " + rs.getInt(3) + "  " + rs.getDouble(4));
	    	}
	    }  
	    public void select_marks_coloumn(Connection con,Scanner sc) throws SQLException 
	    {
	    	Statement st = con.createStatement();
	    	System.out.println("Enter the student's beginning marks: ");
			double beginmarks = sc.nextDouble();
			System.out.println("Enter the student's ending marks: ");
			double endmarks = sc.nextDouble();
	    	String query = String.format("select * from bca_student where marks > %f and marks < %f",beginmarks,endmarks);
	    	ResultSet rs = st.executeQuery(query);
	    	while(rs.next())
	    	{
	    		System.out.println(rs.getInt(1)+"    " + rs.getString(2) + "  " + rs.getInt(3) + "  " + rs.getDouble(4));
	    	}
	    }  
	    public void select_initial_charcacter_of_name(Connection con,Scanner sc) throws SQLException 
	    {
	    	Statement st = con.createStatement();
	    	System.out.println("Enter the student's initial character: ");
			String initialchar = sc.next();
	    	String query = String.format("select * from bca_student where name like '%s'",initialchar);
	    	ResultSet rs = st.executeQuery(query);
	    	while(rs.next())
	    	{
	    		System.out.println(rs.getInt(1)+"    " + rs.getString(2) + "  " + rs.getInt(3) + "  " + rs.getDouble(4));
	    	}
	    }  
	    public void count_function(Connection con) throws SQLException 
	    {
	    	Statement st = con.createStatement();
	    	ResultSet rs = st.executeQuery("select count(*) from bca_student");
	    	if (rs.next())
	    	{
	    	System.out.println(rs.getInt(1));
	    	}
	    }  
	    public void max_function(Connection con) throws SQLException 
	    {
	    	Statement st = con.createStatement();
	    	String query = "select * from bca_student where marks in (select max(marks) from bca_student)";
	    	ResultSet rs = st.executeQuery(query);
	    	if (rs.next())
	    	{
	    	System.out.println(rs.getInt(1)+"    " + rs.getString(2) + "  " + rs.getInt(3) + "  " + rs.getDouble(4));
	    	}
	    }  
	    public void nth_highest(Connection con,Scanner sc) throws SQLException 
	    {
	    	Statement st = con.createStatement();
	    	System.out.println("Enter the value of n: ");
			int n = sc.nextInt();
	    	String query = "select * from(select id,name,age,marks, rank() over (order by marks DESC) ranking from bca_student) where ranking = "+n;
	    	ResultSet rs = st.executeQuery(query);
	    	while(rs.next()) {
				System.out.println(rs.getInt(1)+"    " + rs.getString(2) + "  " + rs.getInt(3) + "  " + rs.getDouble(4));
			}
	    }  
}
