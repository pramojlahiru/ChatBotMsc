package resources.bots.pramoj;

public class ResponsePostProcessor {

    public static String appendSparqlResult(String response) {

        if (response.contains("getCovid19Symptoms()")) {
            response = response.replace("getCovid19Symptoms()", QueryRunner.runSparqlQuery(QueryRepo.FETCH_SYMPTOMS, QueryRepo.LiteralStrings.NAME));
        }

        return response;

    }
}
