import java.util.Collection; 
import java.util.ArrayList;


public class Main 
{
	public static void main(String[] args)
	{
		
		Collection<Tabela> t = new ArrayList<Tabela>();
		Metadados m = new Metadados();
		GeraSQL sql = new GeraSQL();
		
		t = m.tablesMetadata();
		
		for(Tabela table : t)
		{
			System.out.println(table.getTableName().toUpperCase());
			System.out.println("PRIMARY KEY: " + table.getPrimaryKey());
			
			for(Coluna column : table.getColumns())
			{
				System.out.println(column.getColumnName() + " " + column.getTypeName() + " " + column.getSize());
			}
			System.out.println(sql.createInsert(table));
			System.out.println(sql.createUpdate(table));
			System.out.println(sql.createDelete(table));
			System.out.println(sql.createSelect(table));
			System.out.println("\n");
		}
				
	}
}
