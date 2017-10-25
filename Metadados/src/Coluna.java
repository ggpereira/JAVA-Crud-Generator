
public class Coluna
{				
	/*Objeto guarda as informações relacionadas as colunas*/
	private String columnName;
	private String typename;
	private String size;
	
	public void setColumnName(String columnName)
	{
		this.columnName = columnName; 
	}
	
	public String getColumnName()
	{
		return columnName;
	}
	
	public void setTypeName(String typename)
	{
		this.typename = typename; 
	}
	
	public String getTypeName()
	{
		return typename;
	}
	
	
	public void setSize(String size)
	{
		this.size = size; 
	}
	
	public String getSize()
	{
		return size;
	}
}
