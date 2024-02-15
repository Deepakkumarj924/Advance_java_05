import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
public class MAIN {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		
			
			Class.forName("com.mysql.cj.jdbc.Driver");

			String url = "jdbc:mysql://localhost:3306/student";
			String username = "root";
			String pwd = "12345";

			Connection con = DriverManager.getConnection(url, username, pwd);
			Scanner sc = new Scanner(System.in);
			MAIN stu = new MAIN();
			while (true) {
				System.out.println("\nChoose the operation you want to perform :");
				System.out.println("1. Insert data");
				System.out.println("2. Read data");
				System.out.println("3. Update student name");
				System.out.println("4. Update student age");
				System.out.println("5. Update student marks");
				System.out.println("6. Delete data");
				System.out.println("7. Exit");
				System.out.println("\nPlease enter your choice :");

				int choice = sc.nextInt();

				switch (choice) {
				case 1:
					System.out.println("\nPerforming INSERT operation......\n");
					stu.insert_students(con, sc);
					break;
				case 2:
					System.out.println("\nPerforming READ operation......\n");
					stu.display_students(con);
					break;
				case 3:
					System.out.println("\nUpdating student's name......\n");
					stu.update_student_Name(con, sc);
					break;
				case 4:
					System.out.println("\nUpdating student's age......\n");
					stu.update_student_age(con, sc);
					break;
				case 5:
					System.out.println("\nUpdating student's marks......\n");
					stu.update_student_marks(con, sc);
					break;
				case 6:
					System.out.println("\nPerforming DELETE operation......\n");
					stu.remove_student(con, sc);
					break;
				case 7:
					System.out.println("\nExiting......\n");
					sc.close();
					System.exit(0);
					break;
				default:
					System.out.println("\nInvalid choice\n");
				}

			}

		}

		public void insert_students(Connection con, Scanner sc) throws SQLException

		{
			Statement st = con.createStatement();
			System.out.println("Enter the student's Id: ");
			int id = sc.nextInt();

			System.out.println("Enter the student's name: ");
			String name = sc.next();

			System.out.println("Enter the student's gender: ");
			String gender = sc.next();

			System.out.println("Enter the student's age: ");
			int age = sc.nextInt();

			System.out.println("Enter the student's marks: ");
			int marks = sc.nextInt();

			String query = String.format("insert into students values(%d, '%s', '%d', '%d', '%s')", id, name, age,
					marks, gender);

			int rowsAffected = st.executeUpdate(query);
			System.out.println();
			System.out.println(rowsAffected + " record inserted!");
		}

		public void display_students(Connection con) throws SQLException {

			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select * from students");
			while (rs.next()) {
				System.out.println(rs.getInt(1) + "  " + rs.getString(2) + "  " + rs.getInt(3) + "  " + rs.getInt(4)
						+ "  " + rs.getString(5));
			}
		}

		public void update_student_Name(Connection con, Scanner sc) throws SQLException {
			Statement st = con.createStatement();
			System.out.println("Enter the student's id: ");
			int id = sc.nextInt();
			System.out.println("Enter the student's new name: ");
			String name = sc.next();
			String query = String.format("update students set student_Name='%s' where id = %d", name, id);
			int rowsAffected = st.executeUpdate(query);
			System.out.println();
			System.out.println(rowsAffected + " record updated!");
		}

		public void update_student_age(Connection con, Scanner sc) throws SQLException {
			Statement st = con.createStatement();
			System.out.println("Enter the student's id: ");
			int id = sc.nextInt();
			System.out.println("Enter the student's new age: ");
			String age = sc.next();
			String query = String.format("update students set age='%s' where id = %d", age, id);
			int rowsAffected = st.executeUpdate(query);
			System.out.println();
			System.out.println(rowsAffected + " record updated!");
		}

		public void update_student_marks(Connection con, Scanner sc) throws SQLException {
			Statement st = con.createStatement();
			System.out.println("Enter the student's id: ");
			int id = sc.nextInt();
			System.out.println("Enter the student's new marks: ");
			String marks = sc.next();
			String query = String.format("update students set marks='%s' where id = %d", marks, id);
			int rowsAffected = st.executeUpdate(query);
			System.out.println();
			System.out.println(rowsAffected + " record updated!");
		}

		public void remove_student(Connection con, Scanner sc) throws SQLException {
			Statement st = con.createStatement();
			System.out.println("Enter the student's id: ");
			int id = sc.nextInt();
			int rowsAffected = st.executeUpdate("delete from students where id = " + id);
			System.out.println();
			System.out.println(rowsAffected + " record deleted!");
		}

	}


