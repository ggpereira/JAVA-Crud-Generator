import java.sql.Connection; 
import java.sql.SQLException; 
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.Collection;
import java.util.ArrayList;

public class Metadados 
{
	public Connection open()
	{
		Connection c = null;
		
		try
		{
			Class.forName(DB.JDBC_DRIVER); 
		}
		catch(ClassNotFoundException e)
		{
			System.out.println("Não encontrou o driver chamado " + DB.JDBC_DRIVER + "na memória");
		}
		
		try {
			c = DriverManager.getConnection(DB.URL_CONEXAO, DB.USUARIO, DB.SENHA);
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		return c; 
	}
	
	public DatabaseMetaData getMetaData()
	{
		Connection connection = open();
		DatabaseMetaData metadata = null; 
		
		try
		{
			metadata = connection.getMetaData();
		}
		catch(SQLException e)
		{
			System.out.println("Não foi possível ler os metadados: " + e.getMessage());
		}
		
		
		return metadata;
	}
	
	public void printGeneralMetadata()
	{
		try
		{
			DatabaseMetaData m = getMetaData();
			System.out.println("Database Name: " + m.getDatabaseProductName());
			System.out.println("Database version: " + m.getDatabaseProductVersion());
			System.out.println("Logged User: " + m.getUserName());
			System.out.println("JDDBC Driver: " + m.getDriverName());
			System.out.println("Driver Version: " + m.getDriverVersion());
			System.out.println("\n");
		}
		catch(SQLException e)
		{
			System.out.println("Não foi possível encontrar os metadados..." + e.getMessage());
		}
	}
	
	public Collection<String>getTables() 
	{
		Collection<String> tables = new ArrayList<String>();
		DatabaseMetaData m = getMetaData();

		try 
		{
			String table[] = {"TABLE"};
			ResultSet rs = null; 
			rs = m.getTables(null, null, null, table);
		
			while(rs.next())
			{
				tables.add(rs.getString("TABLE_NAME"));
			}
		}
		catch(SQLException e)
		{
			System.out.println("Não foi possível encontrar os metadados..." + e.getMessage());
		}
		
		return tables;
	}
	
	
	public void getColumnsMetadata(Collection<String> tables) 
	{
		ResultSet rs = null; 
		DatabaseMetaData m = getMetaData();
		try {
			for(String current_t : tables)
			{
				rs = m.getColumns(null, null, current_t, null);
				System.out.println(current_t.toUpperCase());
				while(rs.next())
				{
					System.out.println(rs.getString("COLUMN_NAME") + " " + rs.getString("TYPE_NAME") + " " + rs.getString("COLUMN_SIZE"));
				}
				System.out.println("\n");
			}
		}
		catch(SQLException e)
		{
			System.out.println("Não foi possível encontrar os metadados..." + e.getMessage());
		}
	}
}
