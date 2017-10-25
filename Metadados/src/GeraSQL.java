import java.lang.StringBuilder;

public class GeraSQL 
{
	private String INSERT; 
	private String UPDATE; 
	private String DELETE; 
	private String SELECT; 
	
	public String createInsert(Tabela t)
	{
		INSERT = "INSERT INTO " + t.getTableName() + " " + "("; /*INSERT INTO "tablename" ( */
		
		for(Coluna column : t.getColumns())					
		{												
			INSERT += column.getColumnName() + ", ";	  /*INSERT INTO "tablename" column1, column2, column3... */
		}
		
		/*Remove Espaço extra e vírgula*/
		StringBuilder stringBuilder1 = new StringBuilder(INSERT);
		stringBuilder1.deleteCharAt(INSERT.length() - 1);
		stringBuilder1.deleteCharAt(INSERT.length() - 2);
		
		INSERT = stringBuilder1.toString();

		INSERT +=")" +  " VALUES (";	/*INSERT INTO "tablename" (column1, column2, column3...) VALUES */
		
		for(int i = 0; i < t.getColumns().size(); i++)
		{
			INSERT += "?, ";   /*INSERT INTO "tablename" column1, column2, column3... VALUES (? ? ? ?...*/
		}
		
		/*Remove Espaço extra e vírgula*/
		StringBuilder stringBuilder2 = new StringBuilder(INSERT);
		stringBuilder2.deleteCharAt(INSERT.length() - 1);
		stringBuilder2.deleteCharAt(INSERT.length() - 2);

		INSERT = stringBuilder2.toString();
		
		INSERT += ")";  /*INSERT INTO livros (codigo, autores, edicao, editora, nome, ano, isbn) VALUES (? ,? ,? ,? ,? ,? ,?)*/
		
		return INSERT;
	}
	
}
