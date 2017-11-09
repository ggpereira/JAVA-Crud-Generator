import java.util.Collection; 
import java.util.ArrayList; 


public class GeraMetodosDAO 
{
        private String DAO;
	private String metodoInserir; 
	private String metodoDeletar;
	private String metodoBuscar;
	private String metodoAlterar;
        private int contador=1;
        
        public String geraMetodosDAO(Tabela t){
                DAO = "import java.sql.Connection;\n" + "import java.sql.DriverManager;\n" + "import java.sql.PreparedStatement;\n" +
                "import java.sql.ResultSet;\n" + "import java.sql.SQLException;\n" + "import java.sql.Statement;\n" + "import java.util.ArrayList;\n" +
                "import java.util.Collection;\n\npublic class " + t.getTableName().substring(0,1).toUpperCase() + t.getTableName().substring(1) + "DAO {\n\n";
                DAO += "\tpublic Connection open() {\n\t\tConnection c = null;\n\t\ttry {\n\t\t\tClass.forName(DB.JDBC_DRIVER);\n";
                DAO += "\t\t} catch (ClassNotFoundException e) {\n\t\t\tSystem.out.println(\"Não encontrou o driver chamado\" + DB.JDBC_DRIVER + \"na memória.\");\n\t\t}\n";
                DAO += "\t\ttry {\n\t\t\tc = DriverManager.getConnection(DB.URL_CONEXAO, DB.USUARIO, DB.SENHA);\n\t\t} catch (SQLException e) {\n\t\t\te.printStackTrace();\n\t\t}\n";
                DAO += "\t\treturn c;\n\t}\n";
                DAO += geraInsert(t);
                DAO += "\n\n" + geraDelete(t);
                DAO += "\n\n" + geraSearch(t);
                DAO += "\n\n" + geraUpdate(t) + "\n}";
                return DAO;
        }
	
	public String geraInsert(Tabela t)
	{
                contador=1;
		GeraSQL sql = new GeraSQL();
		metodoInserir = "\tpublic void inserir(" + t.getTableName().substring(0,1).toUpperCase() + t.getTableName().substring(1) + " t) {\n";
		metodoInserir += "\t\tConnection conexao = open();\n";
		metodoInserir += "\t\ttry {\n\t\t\tPreparedStatement ps = conexao.prepareStatement(\"" + sql.createInsert(t) + "\");\n";
                for(Coluna column : t.getColumns())					
		{			
                    metodoInserir += "\t\t\tps.set" + column.getTypeName().substring(0,1).toUpperCase() + column.getTypeName().substring(1) + "(" + contador + ",t.get" + column.getColumnName().substring(0,1).toUpperCase() + (column.getColumnName()).substring(1) + "());\n";
                    contador++;
                }
                metodoInserir += "\t\t\tps.execute();\n";
                metodoInserir += "\t\t\tps.close();\n";
                metodoInserir += "\t\t\tconexao.close();\n";
                metodoInserir += "\t\t} catch (SQLException e) {\n";
                metodoInserir += "\t\t\te.printStackTrace();\n\t\t}\n\t}";	
		return metodoInserir;
	}
        
        public String geraUpdate(Tabela t)
        {
                contador=1;
                GeraSQL sql = new GeraSQL();
                metodoAlterar = "\tpublic void alterar(" + t.getTableName().substring(0,1).toUpperCase() + t.getTableName().substring(1) + " t) {\n";
		metodoAlterar += "\t\tConnection conexao = open();\n";
		metodoAlterar += "\t\ttry {\n\t\t\tPreparedStatement ps = conexao.prepareStatement(\"" + sql.createUpdate(t) + "\");\n";
                for(Coluna column : t.getColumns())					
		{			
                    metodoAlterar += "\t\t\tps.set" + column.getTypeName().substring(0,1).toUpperCase() + column.getTypeName().substring(1) + "(" + contador + ",t.get" + column.getColumnName().substring(0,1).toUpperCase() + (column.getColumnName()).substring(1) + "());\n";
                    contador++;
                }
                metodoAlterar += "\t\t\tps.execute();\n";
                metodoAlterar += "\t\t\tps.close();\n";
                metodoAlterar += "\t\t\tconexao.close();\n";
                metodoAlterar += "\t\t} catch (SQLException e) {\n";
                metodoAlterar += "\t\t\te.printStackTrace();\n\t\t}\n\t}";	
		return metodoAlterar;
        }
        
        public String geraSearch(Tabela t)
        {
                contador=1;
                GeraSQL sql = new GeraSQL();
                metodoBuscar = "\tpublic Collection<" + t.getTableName().substring(0,1).toUpperCase() + t.getTableName().substring(1) + "> buscar() {\n";
		metodoBuscar += "\t\tConnection conexao = open();\n";
                metodoBuscar += "\t\tCollection<" + t.getTableName().substring(0,1).toUpperCase() + t.getTableName().substring(1) + "> " + t.getTableName().substring(0,1).toUpperCase() + t.getTableName().substring(1) + " = new ArrayList<" + t.getTableName().substring(0,1).toUpperCase() + t.getTableName().substring(1) + ">();\n";
		metodoBuscar += "\t\ttry {\n\t\t\tStatement s = conexao.createStatement();\n";
                metodoBuscar += "\t\t\tResultSet rs = s.executeQuery(\"" + sql.createSelect(t) + "\");\n";
                metodoBuscar += "\t\t\twhile(rs.next()) {\n\t\t\t\t" + t.getTableName().substring(0,1).toUpperCase() + t.getTableName().substring(1) + " temp = new " + t.getTableName().substring(0,1).toUpperCase() + t.getTableName().substring(1) + "();\n";
                for(Coluna column : t.getColumns())					
		{			
                    metodoBuscar += "\t\t\t\ttemp.set" + column.getColumnName().substring(0,1).toUpperCase() + (column.getColumnName()).substring(1) + "(rs.get" + column.getTypeName().substring(0,1).toUpperCase() + column.getTypeName().substring(1) + "(\"" + column.getColumnName() + "\"));\n";
                    contador++;
                }
                metodoBuscar += "\t\t\t\t" + t.getTableName().substring(0,1).toUpperCase() + t.getTableName().substring(1) + ".add(temp);\n\t\t\t}\n";
                metodoBuscar += "\t\t\trs.close();\n";
                metodoBuscar += "\t\t\tconexao.close();\n";
                metodoBuscar += "\t\t} catch (SQLException e) {\n";
                metodoBuscar += "\t\t\te.printStackTrace();\n\t\t}\n";	
                metodoBuscar += "\t\treturn " + t.getTableName().substring(0,1).toUpperCase() + t.getTableName().substring(1) + ";\n\t}";
		return metodoBuscar;
        }
        
        public String geraDelete(Tabela t)
        {
                contador=1;
                GeraSQL sql = new GeraSQL();
                metodoDeletar = "\tpublic void deletar(" + t.getTableName().substring(0,1).toUpperCase() + t.getTableName().substring(1) + " t) {\n";
		metodoDeletar += "\t\tConnection conexao = open();\n";
                metodoDeletar += "\t\ttry {\n\t\t\tPreparedStatement ps = conexao.prepareStatement(\"" + sql.createDelete(t) + "\");\n";
                for(Coluna column : t.getColumns())					
		{			
                    if(column.getColumnName().equals(t.getPrimaryKey())){
                        metodoDeletar += "\t\t\tps.set" + column.getTypeName().substring(0,1).toUpperCase() + column.getTypeName().substring(1) + "(" + contador + ",t.get" + column.getColumnName().substring(0,1).toUpperCase() + (column.getColumnName()).substring(1) + "());\n";
                    }
                    contador++;
                }
                metodoDeletar += "\t\t\tps.execute();\n";
                metodoDeletar += "\t\t\tps.close();\n";
                metodoDeletar += "\t\t\tconexao.close();\n";
                metodoDeletar += "\t\t} catch (SQLException e) {\n";
                metodoDeletar += "\t\t\te.printStackTrace();\n\t\t}\n\t}";	
                return metodoDeletar;
        }
}
