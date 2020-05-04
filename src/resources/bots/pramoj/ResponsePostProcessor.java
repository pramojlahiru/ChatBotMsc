package resources.bots.pramoj;

public class ResponsePostProcessor {

    private static String GET_COVID19_SYMPTOMS      = "getCovid19Symptoms()";
    private static String GET_COVID19_PRECAUTIONS   = "getCovid19Precautions()";
    private static String GET_ALL_PATIENTS          = "getAllPatients()";
    private static String GET_ALL_HOSPITALS         = "getAllHospitals()";
    private static String GET_COVID_DISTRICTS       = "getCovidDistricts()";

    public static String appendSparqlResult(String response) {

        boolean isCount = response.toUpperCase().contains("HOW MANY") || response.toUpperCase().contains("COUNT");

        if (response.contains(GET_COVID19_SYMPTOMS)) {
            response = response.replace(GET_COVID19_SYMPTOMS, QueryRunner.runSparqlQuery(QueryRepo.FETCH_SYMPTOMS, QueryRepo.LiteralStrings.NAME, isCount));
        } else if (response.contains(GET_COVID19_PRECAUTIONS)) {
            response = response.replace(GET_COVID19_PRECAUTIONS, QueryRunner.runSparqlQuery(QueryRepo.FETCH_PRECAUTIONS, QueryRepo.LiteralStrings.NAME, isCount));
        } else if (response.contains(GET_ALL_PATIENTS)) {
            response = response.replace(GET_ALL_PATIENTS, QueryRunner.runSparqlQuery(QueryRepo.GET_PATIENTS, QueryRepo.LiteralStrings.NAME, isCount));
        } else if (response.contains(GET_ALL_HOSPITALS)) {
            response = response.replace(GET_ALL_HOSPITALS, QueryRunner.runSparqlQuery(QueryRepo.FETCH_HOSPITALS, QueryRepo.LiteralStrings.NAME, isCount));
        }else if (response.contains(GET_COVID_DISTRICTS)) {
            response = response.replace(GET_COVID_DISTRICTS, QueryRunner.runSparqlQuery(QueryRepo.FETCH_COVID_DISTRICTS, QueryRepo.LiteralStrings.NAME, isCount));
        }

        return response;

    }
}
