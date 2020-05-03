package resources.bots.pramoj;

import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.apache.jena.rdf.model.Literal;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.util.FileManager;

public class QueryRunner {

    public static String runSparqlQuery (String queryStr, String literal) {

        FileManager.get().addLocatorClassLoader(QueryRunner.class.getClassLoader());
        Model model = FileManager.get().loadModel("src/resources/bots/pramoj/ontology/covid19details.rdf");

        Query query = QueryFactory.create(queryStr);
        QueryExecution qexec = QueryExecutionFactory.create(query, model);

        String finalResult = "";

        try {
            ResultSet results = qexec.execSelect();

            while (results.hasNext()) {
                QuerySolution soln = results.nextSolution();
                Literal name = soln.getLiteral(literal);
//                System.out.println(name);
                finalResult += name + ", ";
            }
        } finally {
            qexec.close();
        }
        return finalResult;
    }

//    public static void main(String[] args) {
//
//        QueryRunner qr = new QueryRunner();
//        qr.runSparqlQuery(QueryRepo.FETCH_SYMPTOMS, QueryRepo.LiteralStrings.NAME);
//    }
}
