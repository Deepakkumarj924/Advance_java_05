package employee;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
public class operations_of_crud {

	public operations_of_crud()
	{
		super();
	}
	public void insert_employe(Connection con, Scanner sc) throws SQLException

	{
		Statement st = con.createStatement();
		System.out.println("Enter the employe Id: ");
		int id = sc.nextInt();

		System.out.println("Enter the employe name: ");
		String name = sc.next();

		System.out.println("Enter the employe city: ");
		String city = sc.next();

		System.out.println("Enter the employe state: ");
		String state = sc.next();

		System.out.println("Enter the employe salary: ");
		int salary = sc.nextInt();

		String query = String.format("insert into employe values(%d, '%s', '%s', '%s', '%d')", id, name, city,
				state, salary);

		int rowsAffected = st.executeUpdate(query);
		System.out.println();
		System.out.println(rowsAffected + " operatins sucessfull!");
	}

	public void display_employe(Connection con) throws SQLException {

		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery("select * from employe");
		while (rs.next()) {
			System.out.println(rs.getInt(1) + "  " + rs.getString(2) + "  " + rs.getString(3) + "  " + rs.getString(4)
					+ "  " + rs.getInt(5));
		}
	}

	public void update_employe_Name(Connection con, Scanner sc) throws SQLException {
		Statement st = con.createStatement();
		System.out.println("Enter the employe id: ");
		int id = sc.nextInt();
		System.out.println("Enter the employe new name: ");
		String name = sc.next();
		String query = String.format("update employe set e_name='%s' where e_id = %d", name, id);
		int rowsAffected = st.executeUpdate(query);
		System.out.println();
		System.out.println(rowsAffected + " update sucessfull!");
	}

	public void update_employe_state(Connection con, Scanner sc) throws SQLException {
		Statement st = con.createStatement();
		System.out.println("Enter the employe id: ");
		int id = sc.nextInt();
		System.out.println("Enter the employe new state: ");
		String state = sc.next();
		String query = String.format("update employe set state='%s' where e_id = %d", state, id);
		int rowsAffected = st.executeUpdate(query);
		System.out.println();
		System.out.println(rowsAffected + " update sucessfull!");
	}

	public void update_employe_salary(Connection con, Scanner sc) throws SQLException {
		Statement st = con.createStatement();
		System.out.println("Enter the employe id: ");
		int id = sc.nextInt();
		System.out.println("Enter the employe new salary: ");
		String salary = sc.next();
		String query = String.format("update employe set salary='%s' where e_id = %d", salary, id);
		int rowsAffected = st.executeUpdate(query);
		System.out.println();
		System.out.println(rowsAffected + " update sucessfull!");
	}

	public void remove_employe(Connection con, Scanner sc) throws SQLException {
		Statement st = con.createStatement();
		System.out.println("Enter the employe id: ");
		int id = sc.nextInt();
		int rowsAffected = st.executeUpdate("delete from employe where e_id = " + id);
		System.out.println();
		System.out.println(rowsAffected + " record deleted!");
	}
}
