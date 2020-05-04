package resources.bots.pramoj;

public class QueryRepo {

    class LiteralStrings {

        final static String NAME = "name";
    }

    final static String FETCH_SYMPTOMS =
            "prefix covid19details: <http://covid19details.rdf#> " +
                    "   SELECT ?name " +
                    "   WHERE " +
                    "   { " +
                    "       ?symptom covid19details:symptom_name ?name. " +
                    "   }";

    final static String FETCH_PRECAUTIONS =
            "prefix covid19details: <http://covid19details.rdf#>" +
                    "   SELECT ?name" +
                    "   WHERE" +
                    "   {" +
                    "       ?precaution covid19details:precaution_name ?name." +
                    "   }";

    final static String GET_PATIENTS =
            "prefix covid19details: <http://covid19details.rdf#>" +
                    "   SELECT ?name" +
                    "   WHERE" +
                    "   {" +
                    "       ?hs covid19details:patient_name ?name." +
                    "   }";

    final static String FETCH_HOSPITALS =
            "prefix covid19details: <http://covid19details.rdf#>" +
                    "   SELECT ?name" +
                    "   WHERE" +
                    "   {" +
                    "       ?precaution covid19details:hospital_name ?name." +
                    "   }";

    final static String FETCH_COVID_DISTRICTS =
            "prefix covid19details: <http://covid19details.rdf#>" +
                    "   SELECT ?name" +
                    "   WHERE" +
                    "   {" +
                    "       ?district covid19details:district_name ?name." +
                    "   }";

}
