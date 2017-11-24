
public class GeraSQL 
{
	private String INSERT; 
	private String UPDATE; 
	private String DELETE; 
	private String SELECT;
	private String SELECT_ALL;
	
	public String createInsert(Tabela t)
	{
		INSERT = "INSERT INTO " + t.getTableName() + " ("; /*INSERT INTO "nome da tabela" ( */
		int pos = t.getColumns().size() - 1;
		
		
		for(Coluna column : t.getColumns())					
		{									
			if(pos > 0)
			{
				INSERT += column.getColumnName() + ", ";	  /*INSERT INTO "nome da tabela" column1, column2, column3... */
			}
			else
			{
				INSERT += column.getColumnName();
			}
			
			pos--;
		}
		
		INSERT += ")";
		INSERT +=" VALUES (";
		
		pos = t.getColumns().size() - 1;
		
		for(int i = pos; i >= 0; i--)
		{
			if(i > 0)
			{
				INSERT += "?, ";
			}
			else
			{
				INSERT += "?";
			}
		}
		
		INSERT +=")";			/*INSERT INTO "nome da tabela" column1, column2, column3... VALUES(?, ?, ?, ?, ?....)*/
		
		return INSERT;
	}
	
	public String createUpdate(Tabela t)
	{
		UPDATE = "UPDATE" + " " + t.getTableName() + " " + "SET "; 
		
		int pos = t.getColumns().size() - 1;
		
		for(Coluna column : t.getColumns())
		{
			
			if(column.getColumnName().equals(t.getPrimaryKey()) == false)
			{
				if(pos > 0)
				{
					UPDATE += column.getColumnName() + " = ?, ";
				}
				else
				{
					UPDATE += column.getColumnName() + " = ?";
				}
			}
			
			pos--;
		}
		
		UPDATE += " WHERE" + " " + t.getPrimaryKey()  + " = ?";
		
		return UPDATE;
	}
	
	public String createDelete(Tabela t)
	{
		DELETE = "DELETE from " + t.getTableName() + " " + "WHERE " + t.getPrimaryKey() + " = ?";
		
		return DELETE;
	}
	
	
	public String createSelect(Tabela t)
	{
		SELECT = "SELECT * FROM " +  t.getTableName() + " " + "WHERE " + t.getPrimaryKey() + " = ?";
		
		return SELECT;
	}
	
	public String createSelectAll(Tabela t)
	{
		SELECT_ALL = SELECT = "SELECT * FROM " + t.getTableName();
		return SELECT_ALL;
	}
	
}
