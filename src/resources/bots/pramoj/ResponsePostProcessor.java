package resources.bots.pramoj;

public class ResponsePostProcessor {

    public static String appendSparqlResult(String response) {

        if (response.contains("getCovid19Symptoms()")) {
            response = response.replace("getCovid19Symptoms()", QueryRunner.runSparqlQuery(QueryRepo.FETCH_SYMPTOMS, QueryRepo.LiteralStrings.NAME));
        } else if (response.contains("getCovid19Precautions()")) {
            response = response.replace("getCovid19Precautions()", QueryRunner.runSparqlQuery(QueryRepo.FETCH_PRECAUTIONS, QueryRepo.LiteralStrings.NAME));
        }

        return response;

    }
}
