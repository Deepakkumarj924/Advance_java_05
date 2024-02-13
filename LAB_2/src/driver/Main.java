package driver;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;
import employee.operations_of_crud;

public class Main {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");

		String url = "jdbc:mysql://localhost:3306/employee";
		String username = "root";
		String pwd = "12345";

		Connection con = DriverManager.getConnection(url, username, pwd);
		Scanner sc = new Scanner(System.in);
		operations_of_crud emp = new operations_of_crud();
		while (true) {
			System.out.println("\nChoose the operation you want to perform :");
			System.out.println("1. Insert Data");
			System.out.println("2. Read Data");
			System.out.println("3. Update employe name");
			System.out.println("4. Update employe state");
			System.out.println("5. Update employe salary");
			System.out.println("6. Delete employe");
			System.out.println("7. Exit");
			System.out.println("\nPlease enter your choice :");

			int choice = sc.nextInt();

			switch (choice) {
			case 1:
				System.out.println("\nPlease INSERT data......\n");
				emp.insert_employe(con, sc);
				break;
			case 2:
				System.out.println("\nPlease READ data......\n");
				emp.display_employe(con);
				break;
			case 3:
				System.out.println("\nplease Update employe name......\n");
				emp.update_employe_Name(con, sc);
				break;
			case 4:
				System.out.println("\nplease Update employe state......\n");
				emp.update_employe_state(con, sc);
				break;
			case 5:
				System.out.println("\nPlease Update employe salary......\n");
				emp.update_employe_salary(con, sc);
				break;
			case 6:
				System.out.println("\nDELETE data......\n");
				emp.remove_employe(con, sc);
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

}
