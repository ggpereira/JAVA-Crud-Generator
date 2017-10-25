import java.util.Collection; 
import java.util.ArrayList;

public class Tabela 
{
	private String tablename; 
	private Collection<Coluna> columns = new ArrayList<Coluna>();	/*Armazena os objetos Coluna pertencentes as tabelas*/
	private String primary_key; 
	
	public void setTableName(String tablename)
	{
		this.tablename = tablename;
	}
	
	public String getTableName()
	{
		return tablename;
	}
	
	public void setColumn(Coluna c)
	{
		columns.add(c);
	}
	
	public Collection<Coluna> getColumns()
	{
		return columns;
	}
	
	public void setPrimaryKey(String primaryKey)
	{
		primary_key = primaryKey;
	}
	
	public String getPrimaryKey()
	{
		return primary_key;
	}
}
