public class GeraExemplo {
	
	/*Gera exemplos para demonstração */
	
    private String geraExemplo;
    private String geraExemploInsert;
    private String geraExemploSearch;
    private String geraExemploDelete;
    private String geraExemploUpdate;

    public String geraExemplo(Tabela t) {
        geraExemplo = "public class " + t.getTableName().substring(0, 1).toUpperCase() + t.getTableName().substring(1) + "Exemplo {\n\tpublic static void  main(String[] args) {\n";
        geraExemplo += "\t\t" + t.getTableName().substring(0, 1).toUpperCase() + t.getTableName().substring(1) + "DAO cs = new " + t.getTableName().substring(0, 1).toUpperCase() + t.getTableName().substring(1) + "DAO();\n";
        geraExemplo += geraExemploInsert(t) + "\n";
        geraExemplo += geraExemploSearch(t) + "\n";
        geraExemplo += geraExemploUpdate(t) + "\n";
        geraExemplo += geraExemploDelete(t) + "\n";
        geraExemplo += "\t}\n}";
        return geraExemplo;
    }

    public String geraExemploInsert(Tabela t) {
        geraExemploInsert = "\n\t\tSystem.out.println(\"Inserindo " + t.getTableName().substring(0, 1).toUpperCase() + t.getTableName().substring(1) + "...\");\n";
        geraExemploInsert += "\t\t" + t.getTableName().substring(0, 1).toUpperCase() + t.getTableName().substring(1) + " t1 = new " + t.getTableName().substring(0, 1).toUpperCase() + t.getTableName().substring(1) + "();\n";
  
        /*Monta os sets necessários para preencher o objeto*/
        for(Coluna c : t.getColumns())
        {
        	geraExemploInsert += "\t\t" + "t1" + ".set" + c.getColumnName().substring(0, 1).toUpperCase() + c.getColumnName().substring(1) + "(" +  GeradorValores.getValor(c.getTypeName()) + ");" + "\n";
        	
        }
        
        
        geraExemploInsert += "\n\t\tcs.inserir(t1);";
        return geraExemploInsert;
    }

    public String geraExemploSearch(Tabela t) {
        
        geraExemploSearch = "\n\t\tSystem.out.println(\"Buscando " + t.getTableName().substring(0, 1).toUpperCase() + t.getTableName().substring(1) + "...\");\n";
        geraExemploSearch += "\t\tfor (" + t.getTableName().substring(0, 1).toUpperCase() + t.getTableName().substring(1) + " temp : cs.buscar()){\n";
        for (Coluna column : t.getColumns()) {
            geraExemploSearch += "\t\t\tSystem.out.println(temp.get" + column.getColumnName().substring(0, 1).toUpperCase() + column.getColumnName().substring(1) + "());\n";
        }
        geraExemploSearch += "\n\t\t};";
        return geraExemploSearch;
    }

    public String geraExemploUpdate(Tabela t) {
        geraExemploUpdate = "\n\t\tSystem.out.println(\"Alterando " + t.getTableName().substring(0, 1).toUpperCase() + t.getTableName().substring(1) + "...\");\n";
        for (Coluna column : t.getColumns()) {
            geraExemploUpdate += "\t\tt1.set" + column.getColumnName().substring(0, 1).toUpperCase() + column.getColumnName().substring(1) + "(" + GeradorValores.getValor(column.getTypeName()) + ");\n";
        }
        geraExemploUpdate += "\t\tcs.alterar(t1);";
        return geraExemploUpdate;
    }

    public String geraExemploDelete(Tabela t) {
        geraExemploDelete = "\n\t\tSystem.out.println(\"Apagando todos " + t.getTableName().substring(0, 1).toUpperCase() + t.getTableName().substring(1) + "...\");\n";
        geraExemploDelete += "\t\tfor (" + t.getTableName().substring(0, 1).toUpperCase() + t.getTableName().substring(1) + " temp : cs.buscar())\n";
        geraExemploDelete += "\t\t\tcs.deletar(temp);";
        return geraExemploDelete;
    }
    

}
