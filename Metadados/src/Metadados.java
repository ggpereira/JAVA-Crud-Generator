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
	
	public void printGeneralMetadata()	//Imprime informações sobre o BD utilizado
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
	
	public Collection<Tabela>getTables() 	/*Retorna uma lista de tabelas do BD*/
	{
		Collection<Tabela> tables = new ArrayList<Tabela>();
		DatabaseMetaData m = getMetaData();

		try 
		{
			String table[] = {"TABLE"};
			ResultSet rs = null; 
			rs = m.getTables(null, null, null, table);
		
			while(rs.next())
			{
				Tabela t = new Tabela();
				t.setTableName(rs.getString("TABLE_NAME"));
				tables.add(t);
			}
		}
		catch(SQLException e)
		{
			System.out.println("Não foi possível encontrar os metadados..." + e.getMessage());
		}
		
		return tables;
	}
	
	
	public void getColumnsMetadata(Collection<Tabela> tables) /*Navega pelas tabelas acessando suas colunas*/
	{
		ResultSet rs = null; 
		DatabaseMetaData m = getMetaData();
		
		try {
			for(Tabela current_t : tables)
			{
				rs = m.getColumns(null, null, current_t.getTableName(), null);
				System.out.println(current_t.getTableName().toUpperCase());
				while(rs.next())
				{
					Coluna column = new Coluna();
					column.setColumnName(rs.getString("COLUMN_NAME"));
					column.setTypeName(rs.getString("TYPE_NAME"));
					column.setSize(rs.getString("COLUMN_SIZE"));
					current_t.setColumn(column);
					
					/*System.out.println(rs.getString("COLUMN_NAME") + " " + rs.getString("TYPE_NAME") + " " + rs.getString("COLUMN_SIZE"));*/
				}
				/*System.out.println("\n");*/
			}
		}
		catch(SQLException e)
		{
			System.out.println("Não foi possível encontrar os metadados..." + e.getMessage());
		}
	}
	
}
